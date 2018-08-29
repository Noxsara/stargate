package com.sankuai.sjst.stargate.param;

import com.sankuai.sjst.stargate.plan.CommonParam;

public class UserParam extends CommonParam {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
