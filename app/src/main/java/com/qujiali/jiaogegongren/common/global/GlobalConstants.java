package com.qujiali.jiaogegongren.common.global;


/**
 * @Date on 2019/1/8.
 * @Author by xrf05.
 */
public class GlobalConstants {

//    public static final String BASE_SERVER_URL = "http://192.168.1.142/mobile";


    public static final String BASE_SERVER_URL = "https://www.jiaogegongren.com/mobile";


//    public static final String BASE_SERVER_URL = "http://192.168.1.71:8096";


    public static final String BASE_QI_NIU_URL = "http://media.xiangzq.cn/";


    /**
     * 登录
     */
    public static final String APP_LOGIN_INTERFACE = BASE_SERVER_URL + "/anon/login";

    /**
     * 验证码
     */
    public static final String APP_LOAD_CAPTCHA = BASE_SERVER_URL + "/anon/verify/byType";

    /**
     * 首页Banner
     */
    public static final String BANNER_LIST = BASE_SERVER_URL + "/anon/adview/query";

    /**
     * 首页数据
     */
    public static final String App_HOME_PAGE_DATA = BASE_SERVER_URL + "/anon/home/worker";

    /**
     * 新闻资讯
     */
    public static final String NEWS_LIST = BASE_SERVER_URL + "/anon/messages/list";

    /**
     * 查询招工信息列表(招工信息)
     */
    public static final String APP_RECRUITMENT_LIST = BASE_SERVER_URL + "/anon/recruit/list";

    /**
     * 查询招工信息列表 详情
     */
    public static final String APP_RECRUITMENT_DETAIL_LIST = BASE_SERVER_URL + "/anon/recruit/";

    /**
     * 查询工人发布 标签
     */
    public static final String APP_RECRUITMENT_LABEL_LIST = BASE_SERVER_URL + "/system/recruit/workers/proficiency";

    /**
     * 发布工人信息
     */
    public static final String APP_RECRUITMENT_POST_WORKER_INFORMATION = BASE_SERVER_URL + "/system/recruit/workers";

    /**
     * 获取个人信息
     */
    public static final String APP_GETUSERINFO = BASE_SERVER_URL + "/system/mobileUser/info";


    /**
     * 查询关注列表
     */
    public static final String APP_QUERY_WATCH_LIST = BASE_SERVER_URL + "/system/focus/worker/list";

    /**
     * 取消关注  (企业)
     */
    public static final String APP_ENTERPRISE_CANCELATTENTION = BASE_SERVER_URL + "/system/focus/enterprise/";


    /**
     * 取消关注  (工人)
     */
    public static final String APP_WORKER_CANCELATTENTION = BASE_SERVER_URL + "/system/focus/worker/";


    /**
     * 查询是否关注 工人
     */
    public static final String APP_WORKER_WORKER_GETINFO = BASE_SERVER_URL + "/system/focus/worker/getInfo/";


    /**
     * 查询是否关注 企业
     */
    public static final String APP_WORKER_ENTERPRISE_GETINFO = BASE_SERVER_URL + "/system/focus/enterprise/getInfo/";

    /**
     * 关注 工人
     */
    public static final String APP_WORKER_FOCUS = BASE_SERVER_URL + "/system/focus/worker/";

    /**
     * 关注 企业
     */
    public static final String APP_ENTERPRISE_FOCUS = BASE_SERVER_URL + "/system/focus/enterprise/";


    /**
     * 查询我的发布
     */
    public static final String APP_MY_POST = BASE_SERVER_URL + "/system/recruit/workers/list/";

    /**
     * 招工信息下线
     */
    public static final String APP_WORKERS_INSERTINGWINDING = BASE_SERVER_URL + "/system/recruit/workers/insertingWinding/";


    /**
     * 获取当前用户角色信息
     */
    public static final String APP_QUEERY_ROLE = BASE_SERVER_URL + "/system/mobileUser/settled";

    /**
     * 获取技能熟练程度
     */
    public static final String APP_WORKER_PROFICIENCY = BASE_SERVER_URL + "/system/settled/worker/proficiency";

    /**
     * 新增工人入驻 完善信息
     */
    public static final String APP_WORKER_PERFECT_INFORMATION = BASE_SERVER_URL + "/system/settled/worker/add";

    /**
     * 新增企业入驻 完善信息
     */
    public static final String APP_ENTERPRISE_PERFECT_INFORMATION = BASE_SERVER_URL + "/system/settled/enterprise/add";


    /**
     * 工人入驻 技能介绍
     */
    public static final String APP_WORKER_SKILL = BASE_SERVER_URL + "/system/settled/worker/skill";

    /**
     * 企业入驻 技能介绍
     */
    public static final String APP_ENTERPRISE_SKILL = BASE_SERVER_URL + "/system/settled/enterprise/skill";


    /**
     * 工人入驻 新增项目
     */
    public static final String APP_WORKER_ADD_PROJECT = BASE_SERVER_URL + "/system/project/worker/add";
    /**
     * 企业入驻 新增项目
     */
    public static final String APP_ENTERPRISE_ADD_PROJECT = BASE_SERVER_URL + "/system/project/enterprise/add";


    /**
     * 工人入驻 获取所有信息
     */
    public static final String APP_WORKER_GETINFO = BASE_SERVER_URL + "/system/settled/worker/getInfo";

    /**
     * 企业入驻 获取所有信息
     */
    public static final String APP_ENTERPRISE_GETINFO = BASE_SERVER_URL + "/system/settled/enterprise/getInfo";


    /**
     * 照片单张上传
     */
    public static final String APP_UPLOAD = BASE_SERVER_URL + "/image/upload";


    /**
     * 照片多张上传
     */
    public static final String APP_MULTIUPLOAD = BASE_SERVER_URL + "/image/multiUpload";

    /**
     * 注销工人身份
     */
    public static final String APP_SETTLED_WORKER = BASE_SERVER_URL + "/system/settled/worker";

    /**
     * 注销企业身份
     */
    public static final String APP_SETTLED_ENTERPRISE = BASE_SERVER_URL + "/system/settled/enterprise";

    /**
     * 新增技能认证
     */
    public static final String APP_SETTLED_AUTHENTICATION = BASE_SERVER_URL + "/system/authentication";

    /**
     * 获取技能证书列表
     */
    public static final String APP_SKILLCERTIFICATIONLIST = BASE_SERVER_URL + "/system/authentication/list";


    /**
     * 用户实名认证
     */
    public static final String APP_USER_AUTHENTICATION = BASE_SERVER_URL + "/system/user/authentication";

    /**
     * 用户企业认证
     */
    public static final String APP_USER_COMPANY_AUTHENTICATION = BASE_SERVER_URL + "/system/company/authentication";


    /**
     * 修改个人信息
     */
    public static final String APP_MODIFY_USER_INFORMATION = BASE_SERVER_URL + "/system/mobileUser";


    /**
     * 退出登录
     */
    public static final String APP_LOGOUT = BASE_SERVER_URL + "/logout";

    /**
     * 微信登录
     */
    public static final String APP_WXLOGIN = BASE_SERVER_URL + "/anon/wxLogin";

    /**
     * 获取企业详细信息
     */
    public static final String APP_MOBILE_ANON_ENTERPRISE = BASE_SERVER_URL + "/anon/enterprise/";

    /**
     * 获取工人详细信息
     */
    public static final String APP_MOBILE_ANON_WORKER = BASE_SERVER_URL + "/anon/worker/";


    /**
     * 用户协议
     */
    public static final String AGREEMENT_URL = "https://www.jiaogegongren.com/h5/agreement/user_privacy.html";


    /**
     * 隐私政策
     */
    public static final String PRIVACYPOLICY_URL = "https://www.jiaogegongren.com/h5/agreement/user_serving.html";

    /**
     * 问题反馈
     */
    public static final String USER_ADUSERFEEDBACK = BASE_SERVER_URL + "/anon/feedback";

    /**
     * 版本更新
     */
    public static final String APP_VERSION_UPDATE = "http://www.jiaogegongren.com/mobile/anon/version?appId=1";

    /**
     * 绑定手机号
     */
    public static final String APP_BIND_PHONE=BASE_SERVER_URL+"/system/mobileUser/wxBind";

}






