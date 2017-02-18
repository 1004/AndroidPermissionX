package com.kcode.permissionslib.main;

/**
 * request permission callback
 * Created by caik on 2017/2/17.
 */

public interface OnRequestPermissionsCallBack{
    /**
     *
     * @param permissions       permission array
     * @param grantResults      request result array
     */
    void onResult(String[] permissions, int[] grantResults);

    void onAuthorized();
}
