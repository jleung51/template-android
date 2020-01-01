package com.template.application.location;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.template.application.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LocationRecorderActivity extends AppCompatActivity {

    private static final String TAG = LocationRecorderActivity.class.getName();
    private static final int REQUEST_CODE = 1;

    private FusedLocationProviderClient fusedLocationClient = null;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    private LocationRecorder locationRecorder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);  // TODO: Create new layout

        Log.d(TAG, "Instantiating");

        startLocationRecording();

        linkOutputButton();
    }

    private void startLocationRecording() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(
                getApplicationContext()
        );

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(500);
        locationRequest.setFastestInterval(500);

        locationRecorder = new LocationRecorder();

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }

//                 Toast.makeText(
//                         getApplicationContext(),
//                         "Received location.",
//                         Toast.LENGTH_SHORT
//                 ).show();

                for (Location l : locationResult.getLocations()) {
                    Log.d(TAG, "Location: " + l.getLatitude() + "," + l.getLongitude());

                    Location gridLoc = roundToGrid(l);
                    Log.d(TAG,
                            "Location rounded to: " +
                                    gridLoc.getLatitude() +
                                    "," +
                                    gridLoc.getLongitude()
                    );
                    locationRecorder.recordAt(gridLoc);
                }
            }
        };

        boolean permissionAccessFineLocationApproved =
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED;

        if (!permissionAccessFineLocationApproved) {
            // App doesn't have access to the device's location at all.
            // Make full request for permission.
            ActivityCompat.requestPermissions(this, new String[] {
                            Manifest.permission.ACCESS_FINE_LOCATION
                    },
                    0);
        }
    }

    private Location roundToGrid(Location l) {
        final Double gridSize = 0.00005d;

        Double latitude = l.getLatitude();
        Double longitude = l.getLongitude();

        // Round down to nearest grid size
        latitude -= latitude % gridSize;
        longitude -= longitude % gridSize;

        // Move to center of grid square
        latitude += gridSize/2;
        longitude += gridSize/2;

        // Return
        Location res = new Location("");
        res.setLatitude(latitude);
        res.setLongitude(longitude);
        return res;
    }

    private void linkOutputButton() {
        // Set button behaviour
        final Button button = findViewById(R.id.create_output_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createOutputLog(locationRecorder.dump());
            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private void createOutputLog(String content) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE
                );
            }
        }

        try {
            File locationLog = new File(
                    getApplicationContext().getExternalFilesDir(null),
                    "location_log.csv"
            );
            FileWriter writer = new FileWriter(locationLog);
            writer.write(content);
            writer.flush();
            writer.close();

            Log.d(TAG, "Created location log file at " + locationLog.getPath());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Toast.makeText(this, "Created location log file.", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null);

    }

}
