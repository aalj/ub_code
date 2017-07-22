package net.lll0.bus.ui.businfo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by stone on 17-7-22.
 */

public class RealTImeInfoEntity implements Serializable {
    /**
     * status : 1
     * data : [{"StationCName":"车坊首末站","ID":"10000697","Code":"MMV","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"淞泽家园七区","ID":"10002325","Code":"RDX","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"淞泽家园六区","ID":"10001042","Code":"DFE","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"新平街润泽街","ID":"10009441","Code":"PMS","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"淞泽家园九区","ID":"10009442","Code":"MFS","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"淞泽家园二区","ID":"10001134","Code":"CYE","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"车坊国土资源所","ID":"10003722","Code":"ECF","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"车坊","ID":"10002641","Code":"MNR","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"车斜三号桥","ID":"10004830","Code":"GRW","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"职业技术学院南","ID":"10005646","Code":"PUD","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"职业技术学院北","ID":"10005523","Code":"PUE","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"苏州评弹学校北","ID":"10005240","Code":"PUG","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"园区工业技术学校","ID":"10001977","Code":"RBK","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"松涛街创苑路北","ID":"10005789","Code":"PRB","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"苏大公寓南区","ID":"10000978","Code":"RBJ","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"苏大公寓北区","ID":"10001736","Code":"MMY","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"人大国际学院东","ID":"10000695","Code":"FZZ","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"莲花新村五区","ID":"10004261","Code":"MMZ","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"莲新桥南","ID":"10002800","Code":"MNB","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"荷韵新村南区","ID":"10005386","Code":"BRV","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"荷韵新村","ID":"10003589","Code":"ECR","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"大地乐章东","ID":"10004653","Code":"HHS","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"橄榄湾","ID":"10002503","Code":"CGK","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"金姬墩","ID":"10003844","Code":"FJA","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"西洲路","ID":"10000721","Code":"EEY","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"星湖首末站北","ID":"10003395","Code":"FHP","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"摩天轮公园","ID":"10003530","Code":"MCC","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"圆融时代广场","ID":"10004334","Code":"JWD","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"圆融天幕街","ID":"10003367","Code":"JWF","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"国际博览中心东","ID":"10003316","Code":"MGG","InTime":"22:40:37","OutTime":"","BusInfo":"苏E-12073"},{"StationCName":"国际博览中心","ID":"10002731","Code":"CGZ","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"艺术中心北","ID":"10004098","Code":"BRJ","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"凤鸣街南","ID":"10002259","Code":"ENX","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"沁苑小区","ID":"10002776","Code":"GMY","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"新加花园东","ID":"10002683","Code":"EBW","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"新加花园西","ID":"10004256","Code":"DWH","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"新城邻里中心","ID":"10001748","Code":"EVV","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"新城花园","ID":"10001595","Code":"AXT","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"印象城北","ID":"10000579","Code":"FBV","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"印象城西","ID":"10003223","Code":"NJJ","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"百安居西","ID":"10005570","Code":"NJK","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"夏园新村","ID":"10003440","Code":"BPN","InTime":"22:39:55","OutTime":"","BusInfo":"苏E-12075"},{"StationCName":"苏大东校区","ID":"10003882","Code":"HKU","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"葑门换乘站东","ID":"10002884","Code":"BYB","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"金鸡湖大道西","ID":"10003030","Code":"GVJ","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"东振路西","ID":"10001799","Code":"ANM","InTime":"22:40:02","OutTime":"","BusInfo":"苏E-3W803"},{"StationCName":"葑谊新村","ID":"10000700","Code":"DWS","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"南摆宴街","ID":"10004182","Code":"GAK","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"群力村","ID":"10003307","Code":"CWY","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"群星路","ID":"10003544","Code":"DCR","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"群星一路","ID":"10002799","Code":"CYT","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"群星二路","ID":"10004736","Code":"AWR","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"群星三路","ID":"10003050","Code":"EWB","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"通达路","ID":"10002290","Code":"DDB","InTime":"22:40:19","OutTime":"","BusInfo":"苏E-12071"},{"StationCName":"塘南村","ID":"10000727","Code":"FWV","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"独墅村","ID":"10001753","Code":"BBR","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"浮桥村","ID":"10000347","Code":"MNT","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"湾里村","ID":"10002963","Code":"JDN","InTime":"22:37:29","OutTime":"","BusInfo":"苏E-12097"},{"StationCName":"黄潦村","ID":"10002186","Code":"FBW","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"星湖街东方大道南","ID":"10005620","Code":"RGY","InTime":"","OutTime":"","BusInfo":""},{"StationCName":"车坊首末站","ID":"10002086","Code":"MMW","InTime":"22:21:02","OutTime":"","BusInfo":"苏E-12079"}]
     */

    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * StationCName : 车坊首末站
         * ID : 10000697
         * Code : MMV
         * InTime :
         * OutTime :
         * BusInfo :
         */

        private String StationCName;
        private String ID;
        private String Code;
        private String InTime;
        private String OutTime;
        private String BusInfo;

        public String getStationCName() {
            return StationCName;
        }

        public void setStationCName(String StationCName) {
            this.StationCName = StationCName;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public String getInTime() {
            return InTime;
        }

        public void setInTime(String InTime) {
            this.InTime = InTime;
        }

        public String getOutTime() {
            return OutTime;
        }

        public void setOutTime(String OutTime) {
            this.OutTime = OutTime;
        }

        public String getBusInfo() {
            return BusInfo;
        }

        public void setBusInfo(String BusInfo) {
            this.BusInfo = BusInfo;
        }
    }
    /*



     */


}
