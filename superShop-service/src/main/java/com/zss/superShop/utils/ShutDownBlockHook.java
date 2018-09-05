package com.zss.superShop.utils;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;


public class ShutDownBlockHook {
    public interface ShutDownRunning{
        void  run();
    }
    public static TreeMap<Integer,LinkedList<ShutDownRunning>> shutDownTask = new TreeMap<>();
    private static Object registerLock = new Object();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                if (shutDownTask.size()>0) {
                    for (Map.Entry<Integer, LinkedList<ShutDownRunning>> entry : shutDownTask.entrySet()) {
                        if(entry.getValue()!=null){
                            for (ShutDownRunning running :entry.getValue()){
                                if (running!=null){
                                    running.run();
                                }
                            }
                        }
                    }
                }
            }
        }));
    }

    public static void registerHook(ShutDownRunning running,ShutDownHookLevel level){
        synchronized (registerLock) {
            if (shutDownTask.containsKey(level.getLevel())) {
                shutDownTask.get(level.getLevel()).push(running);
            } else {
                LinkedList linkedList = new LinkedList();
                linkedList.push(running);
                shutDownTask.put(level.getLevel(),linkedList);
            }
        }
    }
}
