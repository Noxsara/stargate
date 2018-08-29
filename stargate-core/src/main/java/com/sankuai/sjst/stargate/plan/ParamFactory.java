package com.sankuai.sjst.stargate.plan;

import java.util.HashMap;
import java.util.Map;

public abstract class ParamFactory<T> {

    private Map<String, CommonParam> paramMap = new HashMap<>();

    protected void add(String node, CommonParam param) {
        paramMap.put(node, param);
    }

    public CommonParam get(String node) {
        return paramMap.get(node);
    }

    protected abstract void initialize(T params);
}
