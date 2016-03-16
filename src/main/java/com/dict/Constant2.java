package com.dict;


public class Constant2 extends com.common.dict.Constant2 {
	
	
	
	
	/***
	 * jackson的过滤器
	 */
	public static final String SIMPLEFILTER_JACKSON_PAPERNEWS="newsFilter";
	/***
	 * 报料
	 */
	public static final String SIMPLEFILTER_JACKSON_TIPOFF="tipOffFilter";
	/***
	 * 点播的过滤
	 */
	public static final String SIMPLEFILTER_JACKSON_BROADCAST="broadcastFilter";
	public static final int STATUS_ACTIVE=1;
	public static final int STATUS_UNACTIVE=2;
	
	/***
	 * 用户添加报料时title不能为空
	 */
	public static final int ADD_TIPS_RESULT_TITLE_EMPTY=40;
	/***
	 * 新闻
	 */
	public static final int TYPE_NEWS=2;
	/***
	 * 报料
	 */
	public static final int TYPE_TIPS=1;
	/***
	 * 监利
	 */
	public static final int TYPE_JIANLI=3;
	/***
	 * 商讯
	 */
	public static final int TYPE_BUSINESS=4;
	/***
	 * 政务
	 */
	public static final int TYPE_GOVERNMENT=5;
	/***
	 * 民生
	 */
	public static final int TYPE_PEOPLE=6;
	/***
	 * 博客
	 */
	public static final int TYPE_BLOG=11;
	/***
	 * 用户添加用户反馈时content不能为空
	 */
	public static final int ADD_FEEDBACK_RESULT_CONTENT_EMPTY=50;
	/***
	 * 增加论坛的评论时评论内容为空
	 */
	public static final int ADD_BBS_REPLY_RESULT_FOLLOWCARDCONTENT_EMPTY=53;
//	public static int settings_cardcontent_max=Integer.parseInt(DictionaryParam.get("bbs_settings","cardcontent_max"));
	public static final int COMMENT_TARGET_TYPE_NEWS=1;
	/***
	 * 报料
	 */
	public static final int COMMENT_TARGET_TYPE_TIPS=2;
	/***
	 * 点播
	 */
	public static final int VIDEO_TYPE_BROADCAST=1;
	/***
	 * 2:直播TV
	 */
	public static final int VIDEO_TYPE_LIVES_TV=2;
	/***
	 * 3:直播广播
	 */
	public static final int VIDEO_TYPE_LIVES_RADIO=3;
	/***
	 * 记住用户名,cookie中的key
	 */
	public static final String COOKIE_KEY_USERNAME="username2";
	/***
	 * 记住密码,cookie中的key
	 */
	public static final String COOKIE_KEY_PASSWORD="password2";
	/***
	 * 论坛
	 */
	public static final String DICTIONARY_GROUPID_BBS_GROUP="bbs_group";
	/***
	 * 新闻的栏目
	 */
	public static final String DICTIONARY_GROUPID_NEWS_SORT_GROUP="sort_group";
	/***
	 * 点播的栏目
	 */
	public static final String DICTIONARY_GROUPID_BROADCAST_GROUP="broadcast_group";
	/***
	 * 监利模块
	 */
	public static final String DICTIONARY_GROUPID_JL_GROUP="jl_group";
	/***
	 * 商讯
	 */
	public static final String DICTIONARY_GROUPID_business_GROUP="sx_group";
	/***
	 * 政务
	 */
	public static final String DICTIONARY_GROUPID_government_GROUP="zw_group";
	/***
	 * 民生
	 */
	public static final String DICTIONARY_GROUPID_PEOPLE_LIVELIHOOD_GROUP="ms_group";
	
	/***
	 * 下载文件
	 */
	public static final int LOGS_ACCESS_TYPE_DOWNLOAD=4;
	
	public static final String SESSION_KEY_LOGINED_ADMIN="admin";
	
	
	/***
	 * 注册的标识
	 */
	public static final String REQUESTTARGET_REGISTER="user:register";
	/***
	 * 静态资源(html,css,js)的位置:local or remote
	 */
	public static final String  KEY_IOS_STATIC_RES_LOC="static_res_loc";
	public static final String DICTIONARY_GROUPID_IOS_APPSTORE="ios_appstore";
	/***
	 * ios远程静态资源的地址
	 */
	public static final String IOS_REMOTE_STATIC_DOMAIN_URL="http://hbjltv.com/ios_www/www/index.html";
	/***
	 * ios远程静态资源的地址
	 */
	public static final String IOS_REMOTE_STATIC_IP_URL="http://123.57.250.51/ios_www/www/index.html";
}
