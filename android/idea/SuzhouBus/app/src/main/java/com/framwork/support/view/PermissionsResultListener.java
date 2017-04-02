package com.framwork.support.view;

/**
 * 运行是权限的回调接口
 */

public interface PermissionsResultListener {

    void onPermissionGranted();

    void onPermissionDenied();

}