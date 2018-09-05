package com.zss.superShop.constants;


import java.nio.charset.Charset;

/**
 * Created with IntelliJ IDEA.
 * UserInfo: tangcl
 * Date: 2017/5/9
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 * Desc:
 * Version: 1.0
 * Copyright: http://www.invstone.cn/
 */
public interface SysConstant {

    public static final String CHARSET_UTF8_NAME = "UTF-8";

    public static final Charset CHARSET_UTF8 = Charset.forName(CHARSET_UTF8_NAME);

    public static final String JSON_CONTENT_TYPE = "application/json; charset=utf-8";

    public static final String REQUEST_HEAD_PARAM_ACCESSTOKEN = "accessToken";
    public static final String REQUEST_HEAD_PARAM_CLIENTID = "clientId";
    public static final String REQUEST_HEAD_PARAM_TIMESTAMP = "timestamp";
    public static final String REQUEST_HEAD_PARAM_MODEL = "model";
    public static final String REQUEST_HEAD_PARAM_VERSION = "version";
    public static final String REQUEST_HEAD_PARAM_IMEI = "imei";
    public static final String REQUEST_HEAD_PARAM_APPVERSION = "appversion";
    public static final String REQUEST_PARAM_DATA = "data";
    public static final String ATTRIBUTE_HEADER = "header";
    public static final String DBEI_API_CACHE_KEY_PREFIX = "dbei_api_cache_key";
//    public static final NullResponse NULL_RESPONSE = new NullResponse();
    public static final String DBEI_API_ACCESSTOKEN_SUFFIX = "_dbeiApi";
    public static final String USER_MENU_CACHE_PREFIX = "sysUserMenu";
    public static final long DEFAULT_CACHE_TIME = 30 * 60;


}
