package com.kcode.runtimepermissions;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
                .addPermissionRationale("say why need the permission")
                .addRequestPermissionsCallBack(new OnRequestPermissionsCallBack() {
                    @Override
                    public void onResult(String[] permissions, int[] grantResults) {
                        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            Log.d(TAG, "success");
                            startCamera();
                        } else {
                            Log.d(TAG, "failed");
                            Toast.makeText(getApplicationContext(), "permission denied", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onAuthorized() {
                        Log.d(TAG, "Authorized");
                        startCamera();
                    }
                }).build().request();
    }

    private void startCamera() {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        startActivity(intent);
    }

}
