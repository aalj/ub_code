package net.lll0.bus.database.bean;

import android.os.Parcel;
import android.os.Parcelable;

import net.lll0.bus.adapter.BaseBean;

/**
 * Created by stone on 17-7-23.
 */

public class StationInfoBean extends BaseBean implements Parcelable {
    /**
     * 线路名称
     */
    public String lineName;
    /**
     * 公交的实时信息
     */
    public String realTimeInfo;
    /**
     * 公交始发站
     */
    public String startStation;
    /**
     * 对应公交的详细的查询链接
     */
    public String lineUrl;
    /**
     * 公交的id
     */
    public String lineId;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.lineName);
        dest.writeString(this.realTimeInfo);
        dest.writeString(this.startStation);
        dest.writeString(this.lineUrl);
        dest.writeString(this.lineId);
    }

    public StationInfoBean() {
    }

    protected StationInfoBean(Parcel in) {
        this.lineName = in.readString();
        this.realTimeInfo = in.readString();
        this.startStation = in.readString();
        this.lineUrl = in.readString();
        this.lineId = in.readString();
    }

    public static final Parcelable.Creator<StationInfoBean> CREATOR = new Parcelable.Creator<StationInfoBean>() {
        @Override
        public StationInfoBean createFromParcel(Parcel source) {
            return new StationInfoBean(source);
        }

        @Override
        public StationInfoBean[] newArray(int size) {
            return new StationInfoBean[size];
        }
    };
}
