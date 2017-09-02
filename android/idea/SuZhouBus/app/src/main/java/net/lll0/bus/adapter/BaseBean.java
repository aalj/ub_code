package net.lll0.bus.adapter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liang on 2017/8/24.
 */

public class BaseBean implements Parcelable {
   private  int type=-1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.type);
    }

    public BaseBean() {
    }

    protected BaseBean(Parcel in) {
        this.type = in.readInt();
    }

    public static final Parcelable.Creator<BaseBean> CREATOR = new Parcelable.Creator<BaseBean>() {
        @Override
        public BaseBean createFromParcel(Parcel source) {
            return new BaseBean(source);
        }

        @Override
        public BaseBean[] newArray(int size) {
            return new BaseBean[size];
        }
    };
}
