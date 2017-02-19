package com.kcode.permissionslib.main;

import android.content.pm.PackageManager;

/**
 * Created by caik on 2017/2/19.
 */

public class PermissionUtils {

    public static int verifyPermissions(int[] grantResults) {

        int index = -1;
        if(grantResults.length < 1){
            return index;
        }

        // Verify that each required permission has been granted, otherwise return false.
        for (int i=0;i<grantResults.length;i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                return i;
            }
        }

        return index;
    }
}
