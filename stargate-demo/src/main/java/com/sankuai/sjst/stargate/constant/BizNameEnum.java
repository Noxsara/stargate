package com.sankuai.sjst.stargate.constant;

public enum BizNameEnum {
    QUERY_ITEM("query_item", "查询商品"),
    QUERY_ORDER("query_order", "查订单"),
    QUERY_USER("query_user", "查用户"),
    ;

    BizNameEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    private final String name;

    private final String desc;

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
