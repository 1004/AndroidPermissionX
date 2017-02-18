package com.kcode.permissionslib.main;

import android.support.annotation.NonNull;

/**
 * 请求权限回调
 * Created by caik on 2017/2/17.
 */

public interface OnRequestPermissionsCallBack{
    /**
     *
     * @param permissions       请求的权限列表
     * @param grantResults      对应的返回结果
     */
    void onResult(@NonNull String[] permissions, @NonNull int[] grantResults);

    void onAuthorized();
}
