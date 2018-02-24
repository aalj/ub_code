package com.bumu.arya.constant;

/**
 * 友盟自定义事件统计key
 */
public class UmengV3Constant {

    /***************************友盟统计自定义事件start******************************/
    public static final String V3_TAB_EVENT = "V3_TAB_EVENT";//首页

    public static final String V3_HOMEPAGE_HOME_TAB = "v3_homepage_home_tab";//首页
    public static final String V3_HOMEPAGE_OVERTIME_TAB = "v3_homepage_overtime_tab";    //记加班
    public static final String V3_HOMEPAGE_WAGE_TAB = "v3_homepage_wage_tab";    //工资
    public static final String V3_HOMEPAGE_MY_TAB = "v3_homepage_my_tab";    //我的

    /*********************************首页埋点 start ***************************************/
    public static final String V3_HOME_PAGE_EVENT = "V3_HOME_PAGE_EVENT";


    public static final String V3_HOMEPAGE_LOCATION_CLICK = "v3_homepage_location_click";    //定位按钮
    public static final String V3_HOMEPAGE_CHOOSE_CITY_PAGE = "v3_homepage_choose_city_page";    //选择城市页面

    public static final String V3_HOMEPAGE_MSG_CLICK = "v3_homepage_msg_click";    //消息中心按钮
    public static final String V3_HOMEPAGE_MSG_PAGE = "v3_homepage_msg_page";    //消息中心页面

    public static final String V3_HOMEPAGE_PUSH_CLICK = "v3_homepage_push_click";    //推送推送

    public static final String V3_HOMEPAGE_QUERY_SALARY_CLICK = "v3_homepage_query_salary_click";    //首页查薪资按钮

    public static final String V3_HOMEPAGE_SIGN_CLICK = "v3_homepage_sign_click";    //签到按钮

    public static final String V3_HOMEPAGE_ADS_CLICK = "v3_homepage_ads_click";    //广告位点击


    /*********************************首页埋点 end ***************************************/


    /***********************************查薪资流程  start**********************************************/
    public static final String V3_QUERY_SALARY_EVENT = "V3_QUERY_SALARY_EVENT";


    public static final String V3_SALARY_PAGE = "v3_salary_page";//差薪资页面到达数
    public static final String V3_SALARY_SIGN_CLICK = "v3_salary_sign_click";//	签收薪资条
    public static final String V3_SALARY_HELP_CLICK = "v3_salary_help_click";//查不到薪资的按钮


    /***********************************查薪资流程  end**********************************************/


    /***********************************记加班埋点 start**********************************************/
    public static final String V3_OVERTIME_EVENT = "V3_OVERTIME_EVENT";


    public static final String V3_NEXT_MONTH_CLICK = "v3_next_month_click";//	切换月份下一个月
    public static final String V3_AFTER_MONTH_CLICK = "v3_after_month_click";//	切换月份上一个月
    public static final String V3_MONTH_DAY_CLICK = "v3_month_day_click";//	日期点击
    public static final String V3_OVERTIME_SETTING_CLICK = "v3_overtime_setting_click";//	记加班设置
    public static final String V3_OVERTIME_SETTING_PAGE = "v3_overtime_setting_page";//记加班设置页面到达数
    public static final String V3_OVERTIME_SETTING_BACK = "v3_overtime_setting_back";//记加班设置页面返回按钮
    public static final String V3_OVERTIME_SETTING_BACK_DLG_OK = "v3_overtime_setting_back_dlg_ok";//	设置保存确定按钮
    public static final String V3_OVERTIME_SETTING_BACK_DLG_NO = "v3_overtime_setting_back_dlg_no";//设置保存取消按钮
    public static final String V3_OVERTIME_BLUE_CLICK = "v3_overtime_blue_click";//	记加班页面蓝色点我记加班按钮
    public static final String V3_OVERTIME_DLG_DELETE = "v3_overtime_dlg_delete";//删除按钮
    public static final String V3_OVERTIME_DLG_DELETE_OK = "v3_overtime_dlg_delete_ok";//	删除弹窗确定按钮
    public static final String V3_OVERTIME_DLG_DELETE_NO = "v3_overtime_dlg_delete_no";//删除弹窗取消按钮


    /***********************************记加班埋点 end**********************************************/


    /***********************************我的模块埋点 start**********************************************/

    public static final String V3_MY_EVENT = "V3_MY_EVENT";

    public static final String V3_MY_SETTING_CLICK = "v3_my_setting_click";//	设置按钮点击
    public static final String V3_MY_SETTING_PAGE = "v3_my_setting_page";//	设置页面到达数
    public static final String V3_MY_SIGN_CLICK = "v3_my_sign_click";//	签到按钮
    public static final String V3_MY_INTEGRAL_CLICK = "v3_my_integral_click";//当前积分按钮
    public static final String V3_MY_INTEGRAL_PAGE = "v3_my_integral_page";//积分明细到达页面
    public static final String V3_MY_PERSON_ICON_CLICK = "v3_my_person_icon_click";//头像点击
    public static final String V3_MY_PERSON_ICON_PAGE = "v3_my_person_icon_page";//	修改个人信息页面
    public static final String V3_MY_REAL_NAME_CLICK = "v3_my_real_name_click";//实名认证按钮
    public static final String V3_MY_REAL_NAME_DLG_OK = "v3_my_real_name_dlg_ok";//没有实名认证弹窗确定按钮
    public static final String V3_MY_REAL_NAME_DLG_NO = "v3_my_real_name_dlg_no";//	没有实名认证弹窗取消按钮
    public static final String V3_MY_FEEDBACK_CLICK = "v3_my_feedback_click";//在线客服按钮
    public static final String V3_MY_FEEDBACK_PAGE = "v3_my_feedback_page";//	在线客服页面
    public static final String V3_MY_ABOUT_AS_CLICK = "v3_my_about_as_click";//	关于我们按钮
    public static final String V3_MY_ABOUT_AS_PAGE = "v3_my_about_as_page";//	关于我们页面
    public static final String V3_SETTING_LOGGIN_IN_CLICK = "v3_setting_loggin_in_click";//	关于我们页面


    /***********************************我的模块埋点 end**********************************************/


    /***********************************签到  start**********************************************/
    public static final String V3_SIGN_EVENT = "V3_SIGN_EVENT";


    public static final String V3_SIGN_PAGE = "v3_sign_page";//	签到页面到达数
    public static final String V3_SIGN_LOTTERY_CLICK = "v3_sign_lottery_click";//	签到点击按钮
    public static final String V3_SIGN_ACTIVITY_CLICK = "v3_sign_activity_click";//	签到页面活动点击按钮

    /***********************************签到  end**********************************************/


    /***********************************设置页面 start**********************************************/
    public static final String V3_SETTING_EVENT = "V3_SETTING_EVENT";


    public static final String V3_SETTING_CHANGE_BIND_PHONE_CLICK = "v3_setting_change_bind_phone_click";//	绑定手机号按钮
    public static final String V3_SETTING_INPUT_PHONE_CODE_PAGE = "v3_setting_input_phone_code_page";//	修改绑定手机号输入验证码页面
    public static final String V3_SETTING_INPUT_PHONE_CODE_MEXT_CLICK = "v3_setting_input_phone_code_mext_click";//	修改绑定手机号输入验证码页面下一步按钮
    public static final String V3_SETTING_INPUT_NEW_PHONE_PAGE = "v3_setting_input_new_phone_page";//	修改绑定手机号输入新手机号页面
    public static final String V3_SETTING_INPUT_NEW_PHONE_NEXT_CLICK = "v3_setting_input_new_phone_next_click";//	修改绑定手机号输入新手机号页面下一步按钮
    public static final String V3_SETTING_INPUT_NEW_PHONE_SUCCESS_PAGE = "v3_setting_input_new_phone_success_page";//	修改绑定手机号更换成功页面
    public static final String V3_SETTING_BIND_EMAIL_CLICK = "v3_setting_bind_email_click";//	绑定邮箱按钮
    public static final String V3_SETTING_BIND_EMAIL_PAGE = "v3_setting_bind_email_page";//	绑定邮箱输入邮箱页面
    public static final String V3_SETTING_BIND_EMAIL_NEXT_CLICK = "v3_setting_bind_email_next_click";//	绑定邮箱输入邮箱页面下一步按钮
    public static final String V3_SETTING_BIND_EMAIL_SUCCESS_PAGE = "v3_setting_bind_email_success_page";//	绑定邮箱成功页面
    public static final String V3_SETTING_CLEAR_CACHE_CLICK = "v3_setting_clear_cache_click";//	清除缓存按钮
    public static final String V3_SETTING_SIGN_OUT_CLICK = "v3_setting_sign_out_click";//	退出登录按钮
    public static final String V3_SETTING_CHANGE_PWD_CLICK = "v3_setting_change_pwd_click";//	修改登录密码


    /***********************************设置页面 end**********************************************/


    /***********************************福库流程 start**********************************************/
    public static final String V3_WELFARE_EVENT = "V3_WELFARE_EVENT";

    public static final String V3_V1_WELFARE_EVENT = "V3_V1_WELFARE_EVENT";

    public static final String V3_WELFARE_LIST_PAGE = "v3_welfare_list_page";//	福库列表页
    public static final String V3_WELFARE_LIST_CLICK = "v3_welfare_list_click";//	福库商品点击
    public static final String V3_WELFARE_DETAIL_PAGE = "v3_welfare_detail_page";//	福库商品详情页面
    public static final String V3_WELFARE_DETAIL_SETTLE_CLICK = "v3_welfare_detail_settle_click";//	商品详情去结算按钮
    public static final String V3_WELFARE_PERSON_PAGE = "v3_welfare_person_page";//	填写个人信息页面
    public static final String V3_WELFARE_PERSON_NEXT_CLICK = "v3_welfare_person_next_click";//	填写个人信息页面下一步按钮
    public static final String V3_WELFARE_CONFIRM_PAGE = "v3_welfare_confirm_page";//	确认订单页面
    public static final String V3_WELFARE_CONFIRM_SETTLE_CLICK = "v3_welfare_confirm_settle_click";//	确认订单页面去结算按钮
    public static final String V3_WELFARE_CONFIRM_PAY_ORDER_PAGE = "v3_welfare_confirm_pay_order_page";//	确认付款页面
    public static final String V3_WELFARE_PAY_ORDER_CLICK = "v3_welfare_pay_order_click";//	立即付款按钮
    public static final String V3_WELFARE_ORDER_SUCCESS_PAGE = "v3_welfare_order_success_page";//	购买成功结果页面
    public static final String V3_WELFARE_APPLY_REFUND_CLICL = "v3_welfare_apply_refund_clicl";//	订单页面申请退款按钮
    public static final String V3_WELFARE_CANCEL_ORDER_CLICK = "v3_welfare_cancel_order_click";//取消订单按钮
    public static final String V3_WELFARE_CANCEL_ORDER_RESULT = "v3_welfare_cancel_order_click";//	取消订单成功结果
    public static final String V3_WELFARE_APPLY_REFUND_PAGE = "v3_welfare_apply_refund_page";//	申请退款页面
    public static final String V3_WELFARE_ORDER_PAY_CLICK = "v3_welfare_order_pay_click";//	订单页面立即付款按钮
    public static final String V3_WELFARE_ORDER_PAY_CANCEL_ORDER_CLICK = "v3_welfare_order_pay_cancel_order_click";//	订单页面取消订单按钮


    /***********************************福库流程 end**********************************************/


    /***********************************钱包  start**********************************************/

    public static final String V3_WAGE_EVENT = "V3_WAGE_EVENT";


    public static final String V3_WAGE_BIND_INPUT_CARD_PAGE = "v3_wage_bind_input_card_page";//	输入银行卡页面
    public static final String V3_WAGE_BIND_INPUT_CARD_NEXT_CLICK = "v3_wage_bind_input_card_next_click";//	输入银行卡页面下一步按钮
    public static final String V3_WAGE_BIND_INPUT_INFO_PAGE = "v3_wage_bind_input_info_page";//	填写银行卡信息页面
    public static final String V3_WAGE_BIND_INPUT_INFO_NEXT_CLICK = "v3_wage_bind_input_info_next_click";//	填写银行卡信息页面下一步按钮
    public static final String V3_WAGE_BIND_INPUT_PHONE_CODE_PAGE = "v3_wage_bind_input_phone_code_page";//	输入验证码页面
    public static final String V3_WAGE_BIND_INPUT_PHONE_CODE_NEXT_CLICK = "v3_wage_bind_input_phone_code_next_click";//	输入验证码下一步要按钮
    public static final String V3_WAGE_MY_CARD_PAGE = "v3_wage_my_card_page";//我的银行卡页面
    public static final String V3_WAGE_HIDE_BANLCE_CLICK = "v3_wage_hide_banlce_click";//	钱包隐藏余额按钮
    public static final String V3_WAGE_SHOW_BANLCE_CLICK = "v3_wage_show_banlce_click";//	钱包显示余额按钮
    public static final String V3_WAGE_BILLS_LIST_CLICK = "v3_wage_bills_list_click";//	钱包明细按钮
    public static final String V3_WAGE_BILLS_LIST_PAGE = "v3_wage_bills_list_page";//	钱包明细页面
    public static final String V3_WAGE_WITHDRAW_CLICK = "v3_wage_withdraw_click";//	提现按钮
    public static final String V3_WAGE_RECHARGE_CLICK = "v3_wage_recharge_click";//	充值按钮
    public static final String V3_WAGE_WITHDRAW_PAGE = "v3_wage_withdraw_page";//	提现页面
    public static final String V3_WAGE_WITHDRAW_NEXT_CLICK = "v3_wage_withdraw_next_click";//	提现按钮
    public static final String V3_WAGE_WITHDRAW_ALL_BLANCE_CLICK = "v3_wage_withdraw_all_blance_click";//	全部提现按钮
    public static final String V3_WAGE_WITHDRAW_REXULT_PAGE = "v3_wage_withdraw_rexult_page";//提现结果页面
    public static final String V3_WAGE_RECHARGE_PAGE = "v3_wage_recharge_page";//充值页面
    public static final String V3_WAGE_RECHARGE_NEXT_CLICK = "v3_wage_recharge_next_click";//	充值按钮
    public static final String V3_WAGE_RECHARGE_RESULT_PAGE = "v3_wage_recharge_result_page";//	充值页面
    public static final String V3_WAGE_RECHARGE_PHONE_CODE_PAGE = "v3_wage_recharge_phone_code_page";//	充值输入验证页面
    public static final String V3_WAGE_RECHARGE_PHONE_CODE_CONFIRM_CLICK = "v3_wage_recharge_phone_code_confirm_click";//	确认充值验证码按钮
    public static final String V3_WAGE_RECHARGE_GET_PHONE_CODE_CLICK = "v3_wage_recharge_get_phone_code_click";//	获取验证码页面
    public static final String V3_WAGE_QUERY_SALARY_CLICK = "v3_wage_query_salary_click";//查看薪资按钮
    public static final String V3_WAGE_MY_CARD_CLICK = "v3_wage_my_card_click";//	我的银行卡按钮
    public static final String V3_WAGE_MY_CARD_UNBIND__CLICK = "v3_wage_my_card_unbind__click";//	解绑银行卡按钮
    public static final String V3_WAGE_MY_CARD_BIND_NEW_CARD_CLICK = "v3_wage_my_card_bind_new_card_click";//	绑定新的银行卡
    public static final String V3_WAGE_MY_CARD_INPUT_CARD_NUM_PAGE = "v3_wage_my_card_input_card_num_page";//	输入银行卡号页面
    public static final String V3_WAGE_MY_CARD_INPUT_CARD_NUM_NEXT_CLICK = "v3_wage_my_card_input_card_num_next_click";//	输入银行卡号下一步按钮
    public static final String V3_WAGE_MY_CARD_INPUT_CARD_INFO_PAGE = "v3_wage_my_card_input_card_info_page";//	银行卡绑定输入个人信息页面
    public static final String V3_WAGE_MY_CARD_INPUT_CARD_INFO_NEXT_CLICK = "v3_wage_my_card_input_card_info_next_click";//	输入信息页面下一步按钮
    public static final String V3_WAGE_MY_CARD_PHONE_CODE_PAGE = "v3_wage_my_card_phone_code_page";//绑定银行卡验证手机号页面
    public static final String V3_WAGE_MY_CARD_PHONE_CODE_NEXT_CLICK = "v3_wage_my_card_phone_code_next_click";//	确认绑定银行卡输入手机验证码页面
    public static final String V3_WAGE_MY_CARD_RESET_GET_PHONE_CODE_CLICK = "v3_wage_my_card_reset_get_phone_code_click";//	重新获取绑定银行卡预留手机号验证码按钮
    public static final String V3_WAGE_SETTING_CLICK = "v3_wage_setting_click";//	钱包设置按钮
    public static final String V3_WAGE_SETTING_PAGE = "v3_wage_setting_page";//	钱包设置页面
    public static final String V3_WAGE_SETTING_PAY_CLICK = "v3_wage_setting_pay_click";//设置支付密码按钮
    public static final String V3_WAGE_VAILD_PHONE_CODE_PAGE = "v3_wage_vaild_phone_code_page";//	验证手机验证码页面
    public static final String V3_WAGE_VAILD_PHONE_CODE_NEXT_CLICK = "v3_wage_vaild_phone_code_next_click";//	验证手机号下一步按钮
    public static final String V3_WAGE_VAILD_RESET_PHONE_CODE_CLICK = "v3_wage_vaild_reset_phone_code_click";//	设置支付密码重新获取验证码
    public static final String V3_WAGE_SETTING_PAY_PWD_PAGE = "v3_wage_setting_pay_pwd_page";//设置支付密码页面
    public static final String V3_WAGE_SETTING_PAY_PWD_SUCCESS = "v3_wage_setting_pay_pwd_success";//	支付密码设置成功
    public static final String V3_WAGE_RESET_PAY_PWD_CLICK = "v3_wage_reset_pay_pwd_click";//	重置支付密码按钮
    public static final String V3_WAGE_RESET_PWD_VAILD_PWD_PAGE = "v3_wage_reset_pwd_vaild_pwd_page";//	验证支付密码页面
    public static final String V3_WAGE_FORGET_PAY_PWD_CLICK = "v3_wage_forget_pay_pwd_click";//	忘记支付密码
    public static final String V3_WAGE_CHOOSE_FIND_PAY_PWD_WAY_PAGE = "v3_wage_choose_find_pay_pwd_way_page";//	选择找回密码页面
    public static final String V3_WAGE_FORGET_BANK_CLICK = "v3_wage_forget_bank_click";//选择银行卡找回密码
    public static final String V3_WAGE_FORGET_VAILD_BANK_PAGE = "v3_wage_forget_vaild_bank_page";//验证银行卡号页面
    public static final String V3_WAGE_FORGET_VAILD_BANK_NEXT_CLICK = "v3_wage_forget_vaild_bank_next_click";//	验证银行卡下一步按钮
    public static final String V3_WAGE_FORGET_SMS_CLICK = "v3_wage_forget_sms_click";//选择短信加身份证找回密码


    public static final String V3_WAGE_FORGET_PHONE_CODE_PAGE = "v3_wage_forget_phone_code_page";//	忘记密码验证手机验证码页面
    public static final String V3_WAGE_FORGET_PHONE_CODE_NEXT_CLICK = "v3_wage_forget_phone_code_next_click";//	忘记密码验证手机号下一步按钮
    public static final String V3_WAGE_FORGET_RESET_PHONE_CODE_CLICK = "v3_wage_forget_reset_phone_code_click";//	忘记密码设置支付密码重新获取验证码


    public static final String V3_WAGE_VAILD_ID_CARD_PAGE = "v3_wage_vaild_id_card_page";//验证身份证号页面
    public static final String V3_WAGE_VAILD_ID_CARD_NEXT_CLICK = "v3_wage_vaild_id_card_next_click";//验证身份证下一步按钮
    public static final String V3_WAGE_OPEN_GESTURE_PWD_CLICK = "v3_wage_open_gesture_pwd_click";//	开启手势密码
    public static final String V3_WAGE_CLOSE_GESTURE_PWD_CLICK = "v3_wage_close_gesture_pwd_click";//	关闭手势密码
    public static final String V3_WAGE_OPEN_GESTURE_PWD_SUCCESS = "v3_wage_open_gesture_pwd_success";//开启手势密码成功
    public static final String V3_WAGE_HELP_CLICK = "v3_wage_help_click";//帮助说明按钮


    /***********************************钱包  end**********************************************/


    /***************************友盟统计自定义事件end******************************/


}
