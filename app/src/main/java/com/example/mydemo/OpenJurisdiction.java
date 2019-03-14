package com.example.mydemo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

public class OpenJurisdiction {
    //要申请的权限
    //读写权限
    private static String[] PERMISSIONS_STORAGE = {android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.RECORD_AUDIO};
    private static int REQUEST_PERMISSION_CODE = 3;

    public void open(Activity obj){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(obj,
                    Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(obj, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }

    }
}
