package com.template.application.async;

import android.os.Handler;

import androidx.annotation.NonNull;

@SuppressWarnings({"WeakerAccess", "unused"})
public class AsyncTask {

    public static void repeat(@NonNull Runnable runnable, long delayMillis) {
        Handler handler = new Handler();
        handler.postDelayed(() -> {

            runnable.run();
            repeat(runnable, delayMillis);

        }, delayMillis);
    }
}
