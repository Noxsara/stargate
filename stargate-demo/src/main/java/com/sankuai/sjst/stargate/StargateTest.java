package com.sankuai.sjst.stargate;

import com.sankuai.sjst.stargate.constant.BizNameEnum;
import com.sankuai.sjst.stargate.param.AllParam;
import com.sankuai.sjst.stargate.param.ItemParam;
import com.sankuai.sjst.stargate.param.OrderParam;
import com.sankuai.sjst.stargate.param.UserParam;
import com.sankuai.sjst.stargate.plan.ParamFactory;
import com.sankuai.sjst.stargate.plan.QueryWorker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-context.xml")
public class StargateTest {

    @Test
    public void testStargate(){

        AllParam allParam = new AllParam();
        allParam.setItemId(123L);
        allParam.setUserId(456L);

        List<Long> orderIds = new ArrayList<>();
        orderIds.add(1L);
        orderIds.add(2L);
        allParam.setOrderIds(orderIds);

        QueryWorker worker = QueryWorker.getInstance(allParam, new ParamFactory<AllParam>() {
            @Override
            protected void initialize(AllParam params) {
                UserParam userParam = new UserParam();
                userParam.setUserId(params.getUserId());
                add(BizNameEnum.QUERY_USER.getName(), userParam);

                OrderParam orderParam = new OrderParam();
                orderParam.setOrderIds(params.getOrderIds());
                add(BizNameEnum.QUERY_ORDER.getName(), orderParam);

                ItemParam itemParam = new ItemParam();
                itemParam.setItemId(params.getItemId());
                add(BizNameEnum.QUERY_ITEM.getName(), itemParam);

            }
        });

        long start = System.currentTimeMillis();
        Map<String, Object> result = worker.start("query_list");
        System.out.println(result);
        System.out.println(System.currentTimeMillis() - start);


    }
}
