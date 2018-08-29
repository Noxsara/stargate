package com.sankuai.sjst.stargate.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPool {

    private static final int threads = 8;

    private static ExecutorService executor = Executors.newFixedThreadPool(threads);

    public static Future submit(Callable callable) {
        return executor.submit(callable);
    }
}
