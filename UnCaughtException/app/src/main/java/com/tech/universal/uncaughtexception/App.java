package com.tech.universal.uncaughtexception;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by asher.ansari on 6/17/2018.
 */

public class App extends Application {
    private Thread.UncaughtExceptionHandler mHandler;
    Context mContext;


    @Override
    public void onCreate() {
//        mHandler = Thread.getDefaultUncaughtExceptionHandler();
        mContext = this;
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughExceptionHandler());
        super.onCreate();
    }


    class MyUncaughExceptionHandler implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            Intent intent = new Intent(mContext, Reset.class);
            PendingIntent mPendingIntent = PendingIntent.getActivity(mContext, 123456, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            AlarmManager mgr = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 10, mPendingIntent);
            System.exit(1);
//            mHandler.uncaughtException(thread, ex);
        }
    }
}
