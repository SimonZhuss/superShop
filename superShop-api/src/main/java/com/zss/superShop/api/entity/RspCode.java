package com.zss.superShop.api.entity;

public enum RspCode {

    SUCCESS("成功", "000"),
    SYS_ERROR("系统异常", "001"),
    CODE_ERROR("程序异常", "002"),
    OPT_FAILED("操作失败", "003"),
    OPT_TIMEOUT("操作超时", "004"),
    DATA_NOT_EXIST("数据不存在", "070"),
    DATA_REPEAT("数据重复", "071"),
    REQUEST_ILLEGAL("非法请求", "072"),
    DATA_ERROR("数据异常", "073"),
    USER_INVALID("无效用户,请重新登录", "074"),
    ID_INVALID("无效的主键ID", "075"),
    PARAM_INVALID("参数异常,必要字段为空", "076");

    private String message;

    private String code;

    RspCode(String message, String code) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static RspCode getEnum(String value) {
        RspCode[] crc = RspCode.values();
        for (int i = 0; i < crc.length; i++) {
            if (crc[i].getCode().equals(value)) {
                return crc[i];
            }
            i++;
        }
        return null;
    }
}
