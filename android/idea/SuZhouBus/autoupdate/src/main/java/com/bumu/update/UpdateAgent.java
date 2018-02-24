package com.bumu.update;

import android.app.Activity;
import android.content.Intent;

import com.bumu.update.bean.Update;
import com.bumu.update.download.IUpdateExecutor;
import com.bumu.update.download.OnlineCheckWorker;
import com.bumu.update.download.UpdateExecutor;
import com.bumu.update.download.UpdateNoUrlWorker;
import com.bumu.update.download.UpdateWorker;
import com.bumu.update.listener.OnlineCheckListener;
import com.bumu.update.listener.UpdateListener;
import com.bumu.update.server.DownloadingService;
import com.bumu.update.type.RequestType;
import com.bumu.update.type.UpdateType;
import com.bumu.update.util.UpdateConstants;
import com.bumu.update.view.UpdateDialogActivity;

public class UpdateAgent {
    private static UpdateAgent updater;
    private IUpdateExecutor executor;

    private UpdateAgent() {
        executor = UpdateExecutor.getInstance();
    }

    public static UpdateAgent getInstance() {
        if (updater == null) {
            updater = new UpdateAgent();
        }
        return updater;
    }


    /**
     * check out whether or not there is a new version on internet
     *
     * @param activity The activity who need to show update dialog
     */
    public void onlineCheck(Activity activity) {
        OnlineCheckWorker checkWorker = new OnlineCheckWorker();
        RequestType requestType = UpdateHelper.getInstance().getRequestMethod();
        checkWorker.setRequestMethod(requestType);
        checkWorker.setUrl(UpdateHelper.getInstance().getOnlineUrl());
        checkWorker.setParams(UpdateHelper.getInstance().getOnlineParams());
        checkWorker.setParser(UpdateHelper.getInstance().getOnlineJsonParser());

        final OnlineCheckListener mOnlinelistener = UpdateHelper.getInstance().getOnlineCheckListener();
        checkWorker.setUpdateListener(new OnlineCheckListener() {

            @Override
            public void hasParams(String value) {
                if (mOnlinelistener != null) {
                    mOnlinelistener.hasParams(value);
                }
            }
        });
        executor.onlineCheck(checkWorker);

    }

    /**
     * check out whether or not there is a new version on internet
     *
     * @param activity The activity who need to show update dialog
     */
    public void checkUpdate(final Activity activity) {
        UpdateWorker checkWorker = new UpdateWorker();
        RequestType requestType = UpdateHelper.getInstance().getRequestMethod();
        checkWorker.setRequestMethod(requestType);
        checkWorker.setUrl(UpdateHelper.getInstance().getCheckUrl());
        checkWorker.setParams(UpdateHelper.getInstance().getCheckParams());
        checkWorker.setParser(UpdateHelper.getInstance().getCheckJsonParser());

        final UpdateListener mUpdate = UpdateHelper.getInstance().getUpdateListener();
        checkWorker.setUpdateListener(new UpdateListener() {
            @Override
            public void hasUpdate(Update update) {
                if (mUpdate != null) {
                    mUpdate.hasUpdate(update);
                }
                if (UpdateHelper.getInstance().getUpdateType() == UpdateType.autowifidown) {
                    Intent intent = new Intent(activity, DownloadingService.class);
                    intent.putExtra(UpdateConstants.DATA_ACTION, UpdateConstants.START_DOWN);
                    intent.putExtra(UpdateConstants.DATA_UPDATE, update);
                    activity.startService(intent);
                    return;
                }

                Intent intent = new Intent(activity, UpdateDialogActivity.class);
                intent.putExtra(UpdateConstants.DATA_UPDATE, update);
                intent.putExtra(UpdateConstants.DATA_ACTION, UpdateConstants.UPDATE_TIE);
                intent.putExtra(UpdateConstants.START_TYPE, true);
                activity.startActivity(intent);
            }

            @Override
            public void noUpdate() {
                if (mUpdate != null) {
                    mUpdate.noUpdate();
                }
            }

            @Override
            public void onCheckError(int code, String errorMsg) {
                if (mUpdate != null) {
                    mUpdate.onCheckError(code, errorMsg);
                }
            }

            @Override
            public void onUserCancel() {
                if (mUpdate != null) {
                    mUpdate.onUserCancel();
                }
            }

            @Override
            public void onUserCancelDowning() {
                if (mUpdate != null) {
                    mUpdate.onUserCancelDowning();
                }
            }

            @Override
            public void onUserCancelInstall() {
                if (mUpdate != null) {
                    mUpdate.onUserCancelDowning();
                }
            }
        });
        executor.check(checkWorker);
    }

    public void checkNoUrlUpdate(final Activity activity) {
        UpdateNoUrlWorker checkWorker = new UpdateNoUrlWorker();
        checkWorker.setRequestResultData(UpdateHelper.getInstance().getRequestResultData());
        checkWorker.setParser(UpdateHelper.getInstance().getCheckJsonParser());

        final UpdateListener mUpdate = UpdateHelper.getInstance().getUpdateListener();
        checkWorker.setUpdateListener(new UpdateListener() {
            @Override
            public void hasUpdate(Update update) {
                if (mUpdate != null) {
                    mUpdate.hasUpdate(update);
                }
                if (UpdateHelper.getInstance().getUpdateType() == UpdateType.autowifidown) {
                    Intent intent = new Intent(activity, DownloadingService.class);
                    intent.putExtra(UpdateConstants.DATA_ACTION, UpdateConstants.START_DOWN);
                    intent.putExtra(UpdateConstants.DATA_UPDATE, update);
                    activity.startService(intent);
                    return;
                }

                Intent intent = new Intent(activity, UpdateDialogActivity.class);
                intent.putExtra(UpdateConstants.DATA_UPDATE, update);
                intent.putExtra(UpdateConstants.DATA_ACTION, UpdateConstants.UPDATE_TIE);
                intent.putExtra(UpdateConstants.START_TYPE, true);
                activity.startActivity(intent);
            }

            @Override
            public void noUpdate() {
                if (mUpdate != null) {
                    mUpdate.noUpdate();
                }
            }

            @Override
            public void onCheckError(int code, String errorMsg) {
                if (mUpdate != null) {
                    mUpdate.onCheckError(code, errorMsg);
                }
            }

            @Override
            public void onUserCancel() {
                if (mUpdate != null) {
                    mUpdate.onUserCancel();
                }
            }

            @Override
            public void onUserCancelDowning() {
                if (mUpdate != null) {
                    mUpdate.onUserCancelDowning();
                }
            }

            @Override
            public void onUserCancelInstall() {
                if (mUpdate != null) {
                    mUpdate.onUserCancelDowning();
                }
            }
        });
        executor.checkNoUrl(checkWorker);
    }

}
