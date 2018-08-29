package com.sankuai.sjst.stargate.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.List;

@XStreamAlias("QueryConfig")
public class QueryConfig {

    @XStreamAsAttribute
    @XStreamAlias("bizType")
    private String bizType;

    @XStreamAsAttribute
    @XStreamAlias("description")
    private String description;

    @XStreamAlias("QueryNodes")
    private List<QueryNode> queryNodes;

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QueryNode> getQueryNodes() {
        return queryNodes;
    }

    public void setQueryNodes(List<QueryNode> queryNodes) {
        this.queryNodes = queryNodes;
    }
}
