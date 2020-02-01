package com.template.application.device_services;

import android.app.Activity;
import android.app.AlertDialog;

import com.template.application.R;

@SuppressWarnings("unused")
public class Dialogs {

    public static void requireLocation(final Activity thisActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(thisActivity);

        builder.setTitle(R.string.dialog_location_request_title)
                .setMessage(R.string.dialog_location_request_message)

                .setPositiveButton(
                        R.string.go_to_settings,
                        (dialog, id) -> Navigation.goToLocationSettings(thisActivity)
                )

                .setNegativeButton(
                        R.string.exit,
                        (dialog, id) -> Navigation.exitApplication(thisActivity)
                )

                .setCancelable(false);

        builder.create().show();
    }

    public static void exitWithoutLocationPermissions(final Activity thisActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(thisActivity);

        builder.setTitle(R.string.dialog_location_exit_title)
                .setMessage(R.string.dialog_location_exit_message)

                .setPositiveButton(R.string.exit, (dialog, id) ->
                        Navigation.exitApplication(thisActivity)
                )

                .setCancelable(false);

        builder.create().show();
    }

}
