package com.zss.superShop.utils;

public class ShutDownHookLevel{
    private int level;
    private ShutDownHookLevel(int level){
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public static ShutDownHookLevel LoggingHookLevel = new ShutDownHookLevel(1);
    public static ShutDownHookLevel MetricHookLevel = new ShutDownHookLevel(2);
    public static ShutDownHookLevel HttpHookLevel = new ShutDownHookLevel(999);
}
