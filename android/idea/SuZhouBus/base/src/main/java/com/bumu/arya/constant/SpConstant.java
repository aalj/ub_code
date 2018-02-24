package com.bumu.arya.constant;

/**
 * SharedPreferences key常量
 * Created by bumu-zhz on 2015/10/8.
 */
public class SpConstant {

    /**
     * 系统模式 1：测试环境 2：正式环境
     */
    public static final String SYSTEM_TYPE = "system_type";

    /**
     * 新功能介绍标识  1:非第一次
     */
    public static final String WELCOME_FIRST_FLAG = "welcome_first_flag";
    /**
     * 首页第一次进入进行新手引导
     */
    public static final String FIRST_BOOT_HOME_FLAG = "first_boot_home_flag";
    /**
     * 记加班第一次进入进行新手引导
     */
    public static final String FIRST_BOOT_OVERTIME_FLAG = "first_boot_overtime_flag";
    /**
     * 工资模块第一次进入进行新手引导
     */
    public static final String FIRST_BOOT_WAGE_FLAG = "first_boot_wage_flag";


    /**
     * 记加班新功能介绍标识  1:非第一次
     */
    public static final String OVERTIME_FIRST_FLAG = "overtime_first_flag";

    /**
     * 获取预定义社保基数列表 version
     */
    public static final String SOIN_BASE_VERSION = "soin_base_version";

    /**
     * 获取预定义企业需求 version
     */
    public static final String ENTERPRICE_NEED_VERSION = "enterprice_need_version";

    /**
     * 默认消息一份已经存在flag
     **/
    public static final String MSG_CENTER_DEFAULT_FLAG = "msg_center_default_flag";

    /**
     * 默认消息一份已经存在flag 一键入职
     **/
    public static final String MSG_CENTER_DEFAULT_ENTRY_FLAG = "msg_center_default_entry_flag";
    /**
     * 默认首页滚动条显示
     **/
    public static final String MSG_CENTER_DEFAULT_ENTRY = "msg_center_default_entry";

    /**
     * 企业信息
     **/
    public static final String EMPLOYE_INFO_BEAN = "employe_info_bean";

    /**
     * 广告banner
     **/
    public static final String SP_AD_BANNER_STR = "sp_ad_banner_STR";

    /**
     * 确认入职弹框flag
     **/
    public static final String SP_ENTRY_CONFIRM_CANCEL = "sp_entry_confirm_cancel";

    /**
     * 用户绑定邮箱
     */
    public static final String SP_BINDING_EMAIL = "sp_binding_email";

    /**
     * 记加班薪资基础数据
     */
    public static final String SP_OVERTIME_BASE_DATA = "sp_overtime_base_data";

    /**
     * 薪资查询无密码提示框
     */
    public static final String SP_SALARY_NO_PWD_FLAG = "sp_salary_no_pwd_flag_v2";

    /**
     * 我的页面上一次刷新余额时间
     */
    public static final String SP_REFRESH_BALANCE_FLAG = "sp_refresh_balance_flag";
    /**
     * 首页页面上一次自动刷新天气时间
     */
    public static final String SP_HOME_REF = "sp_home_ref";
    /**
     * 工资模块首页余额是否显示
     */
    public static final String SP_WAGE_IS_SHOW_BALANCE = "sp_wage_is_show_balance";
    /**
     * 是否设置支付密码密码
     */
    public static final String SP_PAY_PWD = "sp_pay_pwd";
    /**
     * 手势密码时是否被锁定 时间
     */
    public static final String SP_GESTURE_IS_LOCK = "sp_gesture_is_lock";


    /**
     * WelcomeActivity 页面默认请求 请求时间
     */
    public static final String SP_DEFAULT_HTTP_TIME = "SP_DEFAULT_HTTP_TIME";
    /**
     * 设备唯一标识符
     */
    public static final String SP_DEVICES_ID = "sp_devices_id";
    /**
     * 记加班游客模式
     */
    public static final String SP_OVERTIME_VISITOR = "sp_overtime_visitor";

    /**
     * 签到游客模式
     */
    public static final String SP_SIGN_IN_VISITOR = "sp_sign_in_visitor";

//	/** 首页一键入职考勤按钮是否是第一次运行*/
//	public static final String SP_ATTENDANCE_ACTION_FIRSH = "sp_attendance_action_firsh";
}
