package net.lll0.bus.database.bean;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by stone on 17-1-3.
 */

public class LineInfoBean implements  Parcelable {
    /**
     *站台序号
     */
    public String index;
    /**
     * 查询站台详情的链接
     */
    public String lineUrl;
    /**
     *站台名称
     */
    public String stationName;
    /**
     *站台的编号
     */
    public String stationNum;
    /**
     *站台的id
     */
    public String stationId;
    /**
     *公交车牌号
     */
    public String carNumber;
    /**
     * 到站时间
     */
    public String time;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.index);
        dest.writeString(this.lineUrl);
        dest.writeString(this.stationName);
        dest.writeString(this.stationNum);
        dest.writeString(this.stationId);
        dest.writeString(this.carNumber);
        dest.writeString(this.time);
    }

    public LineInfoBean() {
    }

    protected LineInfoBean(Parcel in) {
        this.index = in.readString();
        this.lineUrl = in.readString();
        this.stationName = in.readString();
        this.stationNum = in.readString();
        this.stationId = in.readString();
        this.carNumber = in.readString();
        this.time = in.readString();
    }

    public static final Parcelable.Creator<LineInfoBean> CREATOR = new Parcelable.Creator<LineInfoBean>() {
        @Override
        public LineInfoBean createFromParcel(Parcel source) {
            return new LineInfoBean(source);
        }

        @Override
        public LineInfoBean[] newArray(int size) {
            return new LineInfoBean[size];
        }
    };
}
