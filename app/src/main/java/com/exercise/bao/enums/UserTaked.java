package com.exercise.bao.enums;

/**
 * 取票状态
 *
 */
public enum UserTaked {
    UNTAKED("不取票"),
    SUCCESS("已取票"),
    DESIRE("待取票");
    
    /** 状态名称 */
    private final String stateName;
    
    public String getStateName(){
        return stateName;
    }
    
    private UserTaked( String stateName ){
        this.stateName = stateName;
    }
}