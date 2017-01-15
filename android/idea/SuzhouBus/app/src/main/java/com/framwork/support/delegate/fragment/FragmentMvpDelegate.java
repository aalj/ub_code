package com.framwork.support.delegate.fragment;

import android.os.Bundle;
import android.view.View;

/**
 * Created by liangjun on 2017/1/15.
 */

public interface FragmentMvpDelegate {

    void onCreate(Bundle savedInstanceState);

    void onActivityCreated(Bundle saveInstanceState);

    void onCreateView(View view, Bundle saveInstanceState);

    void onStart();

    void onPause();

    void onResume();

    void onStop();

    void onDestroyView();

    void onDestroy();

    void onSaveInstanceState();

    void onAttach();

    //fragmnet和activity分离的时候会调用这个方法
    void onDetach();


}
