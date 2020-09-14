package com.bksx.mobile.common.ganor;


import androidx.annotation.NonNull;

public interface PermissionListener {
    /**
     * 通过授权
     * @param permission
     *
     */
    void permissionGranted(@NonNull String... permission);

    /**
     * 拒绝授权
     * @param permission
     */
    void permissionDenied(String... permission);
}
