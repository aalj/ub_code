package net.lll0.framwork.support.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;

import net.lll0.framwork.mvpbase.presenter.MvpPresenter;
import net.lll0.framwork.mvpbase.view.MvpView;
import net.lll0.framwork.support.delegate.activity.ActivityMvpDelegate;
import net.lll0.framwork.support.delegate.activity.ActivityMvpDelegateCallBack;
import net.lll0.framwork.support.delegate.activity.ActivityMvpDelegateImpl;
import net.lll0.bus.suzhoubus.R;


/**
 * Created by liangjun on 2017/2/3.
 */

public abstract class MvpActivity<V extends MvpView, P extends MvpPresenter<V>>
        extends AppCompatActivity implements ActivityMvpDelegateCallBack<V, P>, MvpView {

    private P presenter;
    private ActivityMvpDelegate<V, P> activityMvpDelegate;
    //是否保存数据
    private boolean retainInstance;

    protected ActivityMvpDelegate<V, P> getActivityMvpDelegate() {
        if (this.activityMvpDelegate == null) {
            this.activityMvpDelegate = new ActivityMvpDelegateImpl<V, P>(this);
        }

        return this.activityMvpDelegate;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        getActivityMvpDelegate().onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        getActivityMvpDelegate().onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        getActivityMvpDelegate().onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getActivityMvpDelegate().onStart();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        getActivityMvpDelegate().onContentChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getActivityMvpDelegate().onStop();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityMvpDelegate().onCreate(savedInstanceState);
    }


    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    public void setPresenter(P presenter) {
        this.presenter = presenter;

    }

    @Override
    public P getPresenter() {
        return this.presenter;
    }


    /**
     * 获取保存的实例
     * 该方法我们是通过静态代理，对FragmentActivity中的方法进行代理，处理相关逻辑
     * 保存我们自己想要的数据
     */

    @Override
    public Object getLastCustomNonConfigurationInstance() {
        return getActivityMvpDelegate().getLastCustomNonConfigurationInstance();
    }

    @Override
    public void setRetainInstance(boolean retaionInstance) {
       this.retainInstance = retaionInstance;
    }

    @Override
    public boolean isRetainInstance() {
        return retainInstance;
    }

    @Override
    public boolean shouldInstanceBeRetained() {
        return this.retainInstance&& isChangingConfigurations();
    }

    @Override
    public Object getNonLastCustomNonConfigurationInstance() {
        return null;
    }


    private PermissionsResultListener mListener;

    private int mRequestCode;

    /**
     * 其他 Activity 继承 BaseActivity 调用 performRequestPermissions 方法
     *
     * @param desc        首次申请权限被拒绝后再次申请给用户的描述提示
     * @param permissions 要申请的权限数组
     * @param requestCode 申请标记值
     * @param listener    实现的接口
     */
    public void performRequestPermissions(String desc, String[] permissions, int requestCode,
                                          PermissionsResultListener listener) {
        if (permissions == null || permissions.length == 0) {
            return;
        }
        mRequestCode = requestCode;
        mListener = listener;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkEachSelfPermission(permissions)) {// 检查是否声明了权限
                requestEachPermissions(desc, permissions, requestCode);
            } else {// 已经申请权限
                if (mListener != null) {
                    mListener.onPermissionGranted();
                }
            }
        } else {
            if (mListener != null) {
                mListener.onPermissionGranted();
            }
        }
    }

    /**
     * 申请权限前判断是否需要声明
     *
     * @param desc
     * @param permissions
     * @param requestCode
     */
    private void requestEachPermissions(String desc, String[] permissions, int requestCode) {
        if (shouldShowRequestPermissionRationale(permissions)) {// 需要再次声明
            showRationaleDialog(desc, permissions, requestCode);
        } else {
            ActivityCompat.requestPermissions(MvpActivity.this, permissions, requestCode);
        }
    }

    /**
     * 弹出声明的 Dialog
     *
     * @param desc
     * @param permissions
     * @param requestCode
     */
    private void showRationaleDialog(String desc, final String[] permissions, final int requestCode) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.tips))
                .setMessage(desc)
                .setPositiveButton(getResources().getString(R.string.confrim), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(MvpActivity.this, permissions, requestCode);
                    }
                })
                .setNegativeButton(getResources().getString(R.string.cancle), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setCancelable(false)
                .show();
    }


    /**
     * 再次申请权限时，是否需要声明
     *
     * @param permissions
     * @return
     */
    private boolean shouldShowRequestPermissionRationale(String[] permissions) {
        for (String permission : permissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 检察每个权限是否申请
     *
     * @param permissions
     * @return true 需要申请权限,false 已申请权限
     */
    private boolean checkEachSelfPermission(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

    /**
     * 申请权限结果的回调
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == mRequestCode) {
            if (checkEachPermissionsGranted(grantResults)) {
                if (mListener != null) {
                    mListener.onPermissionGranted();
                }
            } else {// 用户拒绝申请权限
                if (mListener != null) {
                    mListener.onPermissionDenied();
                }
            }
        }
    }

    /**
     * 检查回调结果
     *
     * @param grantResults
     * @return
     */
    private boolean checkEachPermissionsGranted(int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }






}
