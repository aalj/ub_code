package net.lll0.bus.database.bean;

import java.io.Serializable;

/**
 * Created by stone on 17-1-3.
 */

public class LineInfoBean implements Serializable{
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



}
