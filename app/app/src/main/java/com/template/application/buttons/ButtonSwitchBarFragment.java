package com.template.application.buttons;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.template.application.R;

public class ButtonSwitchBarFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_button_switch_bar, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Must be here; cannot be in onCreateView because linking buttons requires
        // finding views which must have been created beforehand
        linkButtons();
    }

    private void linkButtons() {
        final Context context = this.getActivity();

        // Set button behaviour
        final Button btn_switch_0 = getView().findViewById(R.id.btn_switch_0);
        final Button btn_switch_1 = getView().findViewById(R.id.btn_switch_1);
        final Button btn_switch_2 = getView().findViewById(R.id.btn_switch_2);

        btn_switch_0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activateButton(context, btn_switch_0);
                deactivateButton(context, btn_switch_1);
                deactivateButton(context, btn_switch_2);
            }
        });

        btn_switch_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deactivateButton(context, btn_switch_0);
                activateButton(context, btn_switch_1);
                deactivateButton(context, btn_switch_2);
            }
        });

        btn_switch_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                deactivateButton(context, btn_switch_0);
                deactivateButton(context, btn_switch_1);
                activateButton(context, btn_switch_2);
            }
        });
    }

    private void activateButton(Context c, Button b) {
        b.setTextAppearance(c, R.style.ButtonSwitchBarActive);
        b.setBackgroundColor(ContextCompat.getColor(c, R.color.colorPrimary));
    }

    private void deactivateButton(Context c, Button b) {
        b.setTextAppearance(c, R.style.ButtonSwitchBarInactive);
        b.setBackgroundColor(ContextCompat.getColor(c, R.color.colorOffWhite));
    }

}
