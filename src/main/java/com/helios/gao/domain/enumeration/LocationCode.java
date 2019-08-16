package com.helios.gao.domain.enumeration;

/**
 * @author : gaozhiwen
 * @date : 2019/1/1
 */
public enum LocationCode {
    SHANGHAI("CHN031000000", "上海"),
    BEIJING("CHN011000000", "北京"),
    CHANGZHOU("CHN032004000", "常州"),
    CHANGSHA("CHN043001000", "长沙"),
    XUZHOU("CHN032003000", "徐州"),
    SHENZHEN("CHN044003000", "深圳"),
    ZHUZHOU("CHN043002000", "株洲"),
    GUANGZHOU("CHN044001000", "广州"),
    HANGZHOU("CHN033001000", "杭州"),
    WUHAN("CHN042001000", "武汉"),
    SUZHOU("CHN032005000", "苏州"),
    HEFEI("CHN034001000", "合肥"),
    CIXI("CHN033002082", "慈溪"),
    FOSHAN("CHN044006000", "佛山"),
    QINGDAO("CHN037002000", "青岛"),
    CHONGQING("CHN050000000", "重庆"),
    EERDUOSI("CHN015006000", "鄂尔多斯"),
    HUIZHOU("CHN044013000", "惠州"),
    WUXI("CHN032002000", "无锡"),
    ;

    private String code;

    private String name;

    LocationCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public static LocationCode parse(String name) {
        if (name == null) {
            return null;
        }
        for (LocationCode locationCode : LocationCode.values()) {
            if (locationCode.name.equals(name)) {
                return locationCode;
            }
        }
        return null;
    }
}
