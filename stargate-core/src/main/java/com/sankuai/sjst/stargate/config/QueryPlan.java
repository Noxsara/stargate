package com.sankuai.sjst.stargate.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("QueryPlan")
public class QueryPlan {

    @XStreamImplicit
    private List<QueryConfig> queryConfigs;

    public List<QueryConfig> getQueryConfigs() {
        return queryConfigs;
    }

    public void setQueryConfigs(List<QueryConfig> queryConfigs) {
        this.queryConfigs = queryConfigs;
    }
}
