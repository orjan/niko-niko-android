package com.example.niko;

import android.os.Build;

public class NikoNiko {
    private static NikoNiko ourInstance = new NikoNiko();

    public static NikoNiko getInstance() {
        return ourInstance;
    }

    private NikoNiko() {
    }

    public boolean hasSupportForHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }
}
