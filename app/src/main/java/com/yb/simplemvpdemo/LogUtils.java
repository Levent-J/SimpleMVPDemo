package com.yb.simplemvpdemo;

import android.util.Log;

/**
 * Created by Administrator on 2016/4/17.
 */
public class LogUtils {


    public static void log(String tag){
        Log.d("MainActivity", tag+" Id is:" + Thread.currentThread().getId());
    }
}
