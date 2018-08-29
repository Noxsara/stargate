package com.sankuai.sjst.stargate.external;

import com.sankuai.sjst.stargate.param.ItemParam;
import com.sankuai.sjst.stargate.plan.CommonParam;
import com.sankuai.sjst.stargate.plan.QueryPlanSupport;
import org.springframework.stereotype.Service;

@Service("itemQueryService")
public class ItemQueryService implements QueryPlanSupport {

    @Override
    public Object plan(CommonParam param) {
        ItemParam itemParam = (ItemParam) param;

        return itemParam.getItemId();
    }
}
