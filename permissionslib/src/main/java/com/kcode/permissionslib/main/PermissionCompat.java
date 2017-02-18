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
    private String mExplain;
    private String[] mPermissions;
    private OnRequestPermissionsCallBack mCallBack;
    private CallBackBroadcastReceiver mCallBackBroadcastReceiver = new CallBackBroadcastReceiver();

    public void request() {

        if (mPermissions == null || mPermissions.length == 0) {
            throw new NullPointerException(mPermissions == null
                    ? "mPermissions is null"
                    : "mPermissions is empty");
        }

        Intent intent = RequestActivity.newIntent(mContext, mPermissions, mExplain, mCallBack);
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

        public Builder addPermissionRationale(String explain) {
            mPermissionCompat.mExplain = explain;
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

    class CallBackBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("BroadcastReceiver", intent.getAction().toString());

            if (mCallBack == null) {
                return;
            }

            int flag = intent.getIntExtra(Constants.REQUEST_FLAG, 0);
            switch (flag) {
                case Constants.REQUEST_RESULT:
                    String[] permissions = intent.getStringArrayExtra(Constants.PERMISSIONS);
                    int[] grantResults = intent.getIntArrayExtra(Constants.GRANT_RESULTS);
                    mCallBack.onResult(permissions, grantResults);
                    break;
                case Constants.AUTHORIZED:
                    mCallBack.onAuthorized();
                    break;
            }

            LocalBroadcastManager.getInstance(mContext).unregisterReceiver(mCallBackBroadcastReceiver);
        }
    }
}
