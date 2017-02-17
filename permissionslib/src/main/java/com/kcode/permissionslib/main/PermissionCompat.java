package com.kcode.permissionslib.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

/**
 * Created by caik on 2017/2/17.
 */

public class PermissionCompat {

    private Context mContext;
    private String[] mPermissions;
    private String checkPermission;
    private OnRequestPermissionsCallBack mCallBack;
    private CallBackBroadcastReceiver mCallBackBroadcastReceiver = new CallBackBroadcastReceiver();

    public void request() {

        Intent intent = RequestActivity.newIntent(mContext, 1000, mPermissions, mCallBack);
        mContext.startActivity(intent);
        LocalBroadcastManager.getInstance(mContext)
                .registerReceiver(mCallBackBroadcastReceiver,
                        new IntentFilter(mContext.getPackageName()));

    }

    public static class Builder {

        public PermissionCompat mPermissionCompat = new PermissionCompat();

        public Builder(Context context) {
            mPermissionCompat.mContext = context;
        }

        public Builder addCheckPermission(String permission) {
            mPermissionCompat.checkPermission = permission;
            return this;
        }

        public Builder addPermissions(String[] permissions) {
            mPermissionCompat.mPermissions = permissions;
            return this;
        }

        public Builder addRequestPermissionsCallBack(OnRequestPermissionsCallBack callBack) {
            mPermissionCompat.mCallBack = callBack;
            return this;
        }

        public PermissionCompat build() {
            return mPermissionCompat;
        }
    }

    class CallBackBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("BroadcastReceiver", intent.getAction().toString());

            String[] permissions = intent.getStringArrayExtra("permissions");
            int[] grantResults = intent.getIntArrayExtra("grantResults");

            if (mCallBack != null) {
                mCallBack.onResult(permissions,grantResults);
            }

            //取消注册
            LocalBroadcastManager.getInstance(mContext).unregisterReceiver(mCallBackBroadcastReceiver);
        }
    }
}
