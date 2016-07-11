package com.exercise.bao.receivers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(value=RetentionPolicy.RUNTIME)
public @interface BcAnno {
    
    /** 命令结果 */
    public static final String TypeResult = "ActionResult" ;    
    /** 销售状态 */
    public static final String TypeOnSaleStatus = "OnSaleStatus" ;    
    /** 登录状态 */
    public static final String TypeLoginStatus = "LoginStatus" ;    
    /** 登录信息  */
    public static final String TypeLoginInfo = "LoginInfo" ;
    
    public static enum Type {
        Null( null ),
        Result( TypeResult ),
        OnSaleStatus( TypeOnSaleStatus ),
        LoginStatus( TypeLoginStatus ),
        LoginInfo( TypeLoginInfo );
        
        private Type( String typeStr ){
            this.typeStr = typeStr ;
        }
        
        public String getType() {
            return this.typeStr ;
        }
        
        private String typeStr ;
    }
        
    /** 事件的类型，默认是 Result */
    public Type type() default Type.Result ;
    
    /** 产生事件的操作类型，默认为空 */
    public String action() default "" ;
        
    public static enum Scope {
        OnCreate(), // only for Activity onCreate / onDestroy, fragment onCreateView / onDestroyView
        OnStart(),  // OnStart / OnStop
        OnResume()  // OnResumt / OnPause
        ;        
        private Scope(){}
    } ;
    
    /** 初始化位置，默认为onCreate  */
    public Scope scope() default Scope.OnCreate ; 
    
    
    
}
