package com.sankuai.sjst.stargate.config;


import com.sankuai.sjst.stargate.plan.QueryPlanSupport;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class QueryPlanLoader implements InitializingBean, ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueryPlanLoader.class);

    private static final String config = "stargate.xml";

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() {
        LOGGER.info("start loading query plans.");

        XStream xStream = new XStream(new DomDriver());
        xStream.processAnnotations(QueryPlan.class);
        QueryPlan queryPlan = (QueryPlan) xStream.fromXML(this.getClass().getClassLoader().getResourceAsStream(config));
        if (queryPlan == null || CollectionUtils.isEmpty(queryPlan.getQueryConfigs())) {
            return;
        }

        Map<String, List<QueryNode>> queryPlanMap = new ConcurrentHashMap<>();
        for (QueryConfig queryConfig : queryPlan.getQueryConfigs()) {
            String bizType = queryConfig.getBizType();

            List<QueryNode> rawNodes = queryConfig.getQueryNodes();
            if (CollectionUtils.isEmpty(rawNodes)) {
                continue;
            }

            List<QueryNode> nodes = new ArrayList<>();
            for (QueryNode queryNode : rawNodes) {
                QueryNode node = new QueryNode();
                node.setName(queryNode.getName());
                node.setService(queryNode.getService());
                node.setTimeout(queryNode.getTimeout());
                node.setQueryPlanSupport((QueryPlanSupport) applicationContext.getBean(queryNode.getService()));
                nodes.add(node);
            }

            queryPlanMap.put(bizType, nodes);
            QueryPlanFactory.loadQueryPlans(queryPlanMap);
        }

        LOGGER.info("query plan load complete.");
    }
}
