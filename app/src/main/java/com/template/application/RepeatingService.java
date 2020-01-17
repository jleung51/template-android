package com.template.application;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class RepeatingService extends Service {

    private static final String TAG = RepeatingService.class.getName();

    // TODO: Set the interval
    private static final int ASYNC_TASK_DELAY_MS = 2000;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "Initialized.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        asyncRepeatOperation();

        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    private void asyncRepeatOperation() {
        final Context context = this;
        Handler handler = new Handler();
        handler.postDelayed(() -> {

            // TODO: Do something here

            //noinspection Convert2MethodRef
            asyncRepeatOperation();

        }, ASYNC_TASK_DELAY_MS);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
