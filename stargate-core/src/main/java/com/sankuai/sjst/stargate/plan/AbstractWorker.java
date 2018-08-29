package com.sankuai.sjst.stargate.plan;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

abstract class AbstractWorker {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractWorker.class);

    @SuppressWarnings("unchecked")
    Map<String, Object> getBizResult(Map<String, Object> queryResult, Map<String, Long> timeouts) {
        Map<String, Object> bizResult = new HashMap<>();
        for (Map.Entry<String, Object> pair : queryResult.entrySet()) {
            String node = pair.getKey();
            Object result = pair.getValue();
            Long timeout = timeouts.get(node);

            //1.单次异步请求
            if (result instanceof Future) {
                Future future = (Future) result;
                try {
                    result = future.get(timeout, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    LOGGER.warn("future timeout, node:{}", node);
                    result = null;
                }
            }

            //2.分阶段异步请求
            if (result instanceof List) {
                List resultList = (List) result;
                if (!CollectionUtils.isEmpty(resultList) && resultList.get(0) instanceof Future) {
                    List<Object> tempResult = new ArrayList<>();

                    List<Future> futures = (List<Future>) resultList;
                    for(Future future : futures) {
                        try {
                            tempResult.add(future.get(timeout, TimeUnit.MILLISECONDS));
                        } catch (Exception e) {
                            LOGGER.warn("future timeout, node:{}", node);
                        }
                    }
                    result = tempResult;
                }
            }

            //3.非异步请求
            bizResult.put(node, result);
        }
        return bizResult;
    }

}
