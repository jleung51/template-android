package com.template.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.template.application.device_services.NfcService;
import com.template.application.preferences.SettingsActivity;

public class NfcScanActivity extends AppCompatActivity {

    private NfcService nfcService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scan);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Link Settings button
        final ImageButton button = findViewById(R.id.settings_button);
        button.setOnClickListener(v -> startActivity(new Intent(
                NfcScanActivity.this, SettingsActivity.class)
        ));

        // Instantiate private members

        try {
            nfcService = new NfcService(this);
        }
        catch(NfcService.NfcException e) {
            Toast.makeText(this, e.getReason().getText(), Toast.LENGTH_LONG).show();
            if(e.getReason().equals(NfcService.NfcException.Reason.NOT_SUPPORTED)) {
                finish();
            }
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        nfcService.pause(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        if(nfcService != null) {
            nfcService.resume(this);
        }
    }

    // Receive an NFC reading
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        String data;
        try {
            data = nfcService.readTagFromIntent(intent);
        } catch (NfcService.NfcException e) {
            processError(e);
            return;
        }

        if (data == null || data.isEmpty()) {
            Toast.makeText(this, "No information found on tag.", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        processData(data);
    }

    @SuppressWarnings("unused")
    private void processError(NfcService.NfcException e) {
        // TODO: Do something with an error
    }

    @SuppressWarnings("unused")
    private void processData(String data) {
        // TODO: Do something with it
    }

}
