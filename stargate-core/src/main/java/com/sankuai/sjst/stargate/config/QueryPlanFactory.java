package com.sankuai.sjst.stargate.config;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QueryPlanFactory {

    private static Map<String, List<QueryNode>> queryPlans = new ConcurrentHashMap<>();

    public static List<QueryNode> getQueryPlan(String bizType) {
        return queryPlans.get(bizType);
    }

    public static void loadQueryPlans(Map<String, List<QueryNode>> queryPlans) {
        QueryPlanFactory.queryPlans = queryPlans;
    }
}
