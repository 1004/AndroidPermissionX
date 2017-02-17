package com.kcode.permissionslib.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by caik on 2017/2/17.
 */

public class RequestActivity extends AppCompatActivity {

    private static final String TAG = "RequestActivity";
    private static final String REQUEST_CODE = "requestCode";
    private static final String PERMISSIONS = "permissions";

    private String[] mPermissions;

    public static Intent newIntent(Context context, int requestCode,
                                   String[] permissions, OnRequestPermissionsCallBack callBack) {
        Intent intent = new Intent(context, RequestActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArray(PERMISSIONS, permissions);
        bundle.putInt(REQUEST_CODE, requestCode);
        intent.putExtras(bundle);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        checkPermission();
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        mPermissions = bundle.getStringArray(PERMISSIONS);
    }

    private void checkPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(getApplicationContext(),
                mPermissions[0]);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.CAMERA)) {
                Log.d(TAG, "拒绝过了");
                ActivityCompat.requestPermissions(this,
                        mPermissions, 1000);
            } else {
                Log.d(TAG, "开始请求权限");
                ActivityCompat.requestPermissions(this,
                        mPermissions, 1000);
            }
        } else {
            Log.d(TAG, "已经有权限");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000:

                Intent intent = new Intent();
                Bundle args = new Bundle();
                args.putStringArray("permissions", permissions);
                args.putIntArray("grantResults", grantResults);
                intent.putExtras(args);
                intent.setAction(getPackageName());
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

                finish();
                break;
        }
    }
}
