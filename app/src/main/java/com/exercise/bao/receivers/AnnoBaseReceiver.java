package com.exercise.bao.receivers;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class AnnoBaseReceiver extends BroadcastReceiver {
    
    static class ObjectAction {
        Object    obj ;
        Method method ;
        BcAnno param ;
    }
    
    private List<ObjectAction> actions = new ArrayList<ObjectAction>();   
    
    private static String ClassTag = "AnnoBaseReceiver" ;

    public int register( Object obj, BcAnno.Scope scope ) {
        //Log.v( ClassTag, "Trying Register: " + scope + ", " + obj ) ;
        if( obj==null ) {
            return 0 ;
        }
        
        int count = 0 ;
        Method[] methods = obj.getClass().getDeclaredMethods();
        for( Method m : methods ) {
            // Annotation[] arr = m.getAnnotations();
            //Log.i( ClassTag, " annotation " + m.getName() + " number: " + arr.length );
            BcAnno param = m.getAnnotation( BcAnno.class );
            if( param==null ) {
                Log.v( ClassTag, "skip no anno method: " + m.getName() + " by " + obj );
                continue ;
            }
            if( param.scope()!=scope ) {
                Log.v( ClassTag, "skip scope method: " + m.getName() + " by " + obj );
                continue ;
            }

            ObjectAction act = new ObjectAction();
            act.obj = obj ;
            act.method = m ;
            act.param = param ;
            this.actions.add( act );
            count ++ ;
            Log.d( ClassTag, "Registered: " + obj.getClass().getSimpleName() + ","+m.getName()
                    +" by "+param.type().getType()+","+param.action() );
        }
        return count ;
    }
    
    public void unregister( BcAnno.Scope scope ) {
        Iterator<ObjectAction> iter = this.actions.iterator() ;
        while( iter.hasNext() ) {
            ObjectAction act = iter.next() ;
            if( act.param.scope() == scope ) {
                iter.remove() ;
            }
        }
    }
    
    @Override
    public void onReceive( Context context, Intent intent ) {    
        
        Bundle bundle = intent.getExtras() ;
        String type = bundle.getString("type");
        String actionStr = bundle.getString("action");
        
        if( Log.isLoggable(ClassTag, Log.VERBOSE) ) {
            Log.v( ClassTag, "Receiving: type=" + type + ", action=" + actionStr + " by " + this ) ;
        }
        
        if( type==null ) {
            Log.v( ClassTag, "skip no type intent.");
            return ;
        }
        
        if( null==actions || actions.isEmpty() ) {
            Log.v( ClassTag, "Bad recievier no action found.");
            return ;
        }
        
        boolean found = false ;
        for( ObjectAction act : actions ) {
            try {
                if( !type.equals(act.param.type().getType()) ) {
                    continue ;
                }
                
                String action = act.param.action() ;
                if( !action.isEmpty() && action!=null && !action.equals(actionStr)  ) {
                    continue ;
                }
                found = true ;
                        
                if( Log.isLoggable(ClassTag, Log.VERBOSE) ) {
                    Log.v( ClassTag, "invoking by " + act.param ) ;
                }
                act.method.invoke( act.obj, bundle );                
            } catch( Exception e ) {
                e.printStackTrace();
            }
        } 
        
        if( !found ) {
            long seqId = intent.getLongExtra( "seqId", -1L );
            Log.d( ClassTag, "No receiver: type="+type + ", action="+actionStr + ", seqId=" + seqId + " by " + this );
        }
        
    }
    
    
    
}
