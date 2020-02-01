package com.template.application.display;

import android.app.Activity;
import android.view.View;
import android.widget.EditText;

@SuppressWarnings("unused")
public class TextAndViewManager {

    public static String getValueOfInputField(Activity context, int resourceId) {
        EditText text = context.findViewById(resourceId);
        return text.getText().toString();
    }

    public static void setVisible(Activity context, int resourceId) {
        context.findViewById(resourceId).setVisibility(View.VISIBLE);
    }

    public static void setInvisible(Activity context, int resourceId) {
        context.findViewById(resourceId).setVisibility(View.INVISIBLE);
    }

    public static void setGone(Activity context, int resourceId) {
        context.findViewById(resourceId).setVisibility(View.GONE);
    }

    public static void toggleVisibility(Activity context, int resourceId) {
        View t = context.findViewById(resourceId);
        if (t.getVisibility() == View.VISIBLE) {
            t.setVisibility(View.INVISIBLE);
        }
        else {
            t.setVisibility(View.VISIBLE);
        }
    }

}
