package net.lll0.bus.ui.feedback;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.sdk.android.feedback.impl.FeedbackAPI;

import net.lll0.bus.contstant.BaseConstant;
import net.lll0.bus.suzhoubus.R;

import java.util.concurrent.Callable;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FeedbackAPI.init(getApplication(), BaseConstant.ALIBAICUAN_APP_KEY,BaseConstant.ALIBAICUAN_APPSECRET);
        setContentView(R.layout.fragment_feedback);
        //用fragment来规避状态栏的问题

        FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction transaction = fm.beginTransaction();
        final Fragment feedback = FeedbackAPI.getFeedbackFragment();
        // must be called
        FeedbackAPI.setFeedbackFragment(new Callable() {
            @Override
            public Object call() throws Exception {
                transaction.replace(R.id.content, feedback);
                transaction.commit();
                return null;
            }
        }, null);
        FeedbackAPI.setBackIcon(R.mipmap.ic_back);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        FeedbackAPI.cleanFeedbackFragment();
        FeedbackAPI.cleanActivity();
    }
}
