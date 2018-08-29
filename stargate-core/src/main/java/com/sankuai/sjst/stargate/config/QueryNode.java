package com.sankuai.sjst.stargate.config;

import com.sankuai.sjst.stargate.plan.QueryPlanSupport;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("QueryNode")
public class QueryNode {

    @XStreamAlias("Name")
    private String name;

    @XStreamAlias("Service")
    private String service;

    @XStreamAlias("Timeout")
    private Long timeout;

    private QueryPlanSupport queryPlanSupport;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public QueryPlanSupport getQueryPlanSupport() {
        return queryPlanSupport;
    }

    public void setQueryPlanSupport(QueryPlanSupport queryPlanSupport) {
        this.queryPlanSupport = queryPlanSupport;
    }
}
