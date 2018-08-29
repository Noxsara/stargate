package com.sankuai.sjst.stargate.plan;

import com.sankuai.sjst.stargate.config.QueryNode;
import com.sankuai.sjst.stargate.config.QueryPlanFactory;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryWorker extends AbstractWorker{

    private ParamFactory paramFactory;

    private <T> QueryWorker(T param, ParamFactory<T> paramFactory) {
        this.paramFactory = paramFactory;
        paramFactory.initialize(param);
    }

    public static <T> QueryWorker getInstance(T param, ParamFactory<T> paramFactory) {
        return new QueryWorker(param, paramFactory);
    }

    public Map<String, Object> start(String bizType) {
        Map<String, Object> queryResult = new HashMap<>();
        Map<String, Long> timeouts = new HashMap<>();

        List<QueryNode> queryNodes = QueryPlanFactory.getQueryPlan(bizType);
        if (CollectionUtils.isEmpty(queryNodes)) {
            return queryResult;
        }

        for (QueryNode node : queryNodes) {
            QueryPlanSupport queryPlanSupport = node.getQueryPlanSupport();
            if (queryPlanSupport != null) {
                CommonParam param = paramFactory.get(node.getName());
                Object result = queryPlanSupport.plan(param);
                queryResult.put(node.getName(), result);
                timeouts.put(node.getName(), node.getTimeout());
            }
        }
        return getBizResult(queryResult, timeouts);
    }
}
