package com.sankuai.sjst.stargate.external;

import com.sankuai.sjst.stargate.executor.ThreadPool;
import com.sankuai.sjst.stargate.param.UserParam;
import com.sankuai.sjst.stargate.plan.CommonParam;
import com.sankuai.sjst.stargate.plan.QueryPlanSupport;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;


@Service("userQueryService")
public class UserQueryService implements QueryPlanSupport {

    @Override
    public Object plan(CommonParam param) {
        final UserParam userParam = (UserParam) param;

        return ThreadPool.submit(new Callable() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(300);
                return userParam.getUserId();
            }
        });
    }
}
