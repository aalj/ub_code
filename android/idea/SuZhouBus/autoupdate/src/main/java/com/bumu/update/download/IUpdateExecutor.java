package com.bumu.update.download;

/**
 */
public interface IUpdateExecutor {

    /**
     * check if is new version exist;
     */
    void check(UpdateWorker worker);

    /**
     * check if is new version exist;
     */
    void checkNoUrl(UpdateNoUrlWorker worker);

    /**
     * request download new version apk
     */
    void onlineCheck(OnlineCheckWorker worker);
}
