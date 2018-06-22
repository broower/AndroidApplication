package com.example.maksim_zakharenka.broowerandroidapplication;

import android.app.Activity;
import android.os.Build;

public final class Utils {

    public static void setStatusBarColor(final Activity pActivity, final int pColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            pActivity.getWindow().setStatusBarColor(pColor);
        }
    }
}
