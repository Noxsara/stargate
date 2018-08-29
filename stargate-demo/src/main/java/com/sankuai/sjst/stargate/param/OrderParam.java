package com.sankuai.sjst.stargate.param;

import com.sankuai.sjst.stargate.plan.CommonParam;

import java.util.List;

public class OrderParam extends CommonParam{

    private List<Long> orderIds;

    public List<Long> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }
}
