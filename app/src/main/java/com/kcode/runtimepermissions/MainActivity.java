package com.kcode.runtimepermissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.kcode.permissionslib.main.OnRequestPermissionsCallBack;
import com.kcode.permissionslib.main.PermissionCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openCamera(View view) {

        new PermissionCompat.Builder(this)
                .addPermissions(new String[]{Manifest.permission.CAMERA})
                .addRequestPermissionsCallBack(new OnRequestPermissionsCallBack() {
                    @Override
                    public void onResult(String[] permissions, int[] grantResults) {
                        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            Log.d(TAG, "success");
                        } else {
                            Log.d(TAG, "falied");
                        }
                    }
                }).build().request();
    }

}
