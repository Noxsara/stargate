package com.sankuai.sjst.stargate.external;

import com.sankuai.sjst.stargate.executor.ThreadPool;
import com.sankuai.sjst.stargate.param.OrderParam;
import com.sankuai.sjst.stargate.plan.CommonParam;
import com.sankuai.sjst.stargate.plan.QueryPlanSupport;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;


@Service("orderQueryService")
public class OrderQueryService implements QueryPlanSupport {

    @Override
    public Object plan(CommonParam param) {

        OrderParam orderParam = (OrderParam) param;

        List<Future> futures = new ArrayList<>();

        for (final Long orderId : orderParam.getOrderIds()) {
            Future f = ThreadPool.submit(new Callable() {
                @Override
                public Object call() throws Exception {
                    Thread.sleep(200);
                    return "order:" + orderId;
                }
            });

            futures.add(f);
        }

        return futures;
    }
}
