package com.yienx.test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.AllArgsConstructor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author wangyanbo29
 * @Date 2024/1/15
 * @Description
 */
public class GuavaTest {
    public static void main(String[] args) throws Exception {
        LoadingCache<String, CachePromise<String>> cache = CacheBuilder
                .newBuilder()
                .initialCapacity(10)
                .maximumSize(10)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .build(CacheLoader.from(() -> new CachePromise<>(Thread.currentThread())));

        final CachePromise<String> promise = cache.getUnchecked("a");
        if (promise.shouldProduce()) {
            promise.complete("a");
        }
    }


}

@AllArgsConstructor
class CachePromise<V> {
    final Thread producer;
    final CompletableFuture<V> promise = new CompletableFuture<>();
    final AtomicBoolean producing = new AtomicBoolean();

    public boolean shouldProduce() {
        return producer == Thread.currentThread() && producing.compareAndSet(false, true) && !promise.isDone();
    }

    public boolean complete(V value) {
        return promise.complete(value);
    }

    public boolean completeExceptionally(Throwable cause) {
        return promise.completeExceptionally(cause);
    }

    public CompletableFuture<V> promise() {
        return promise;
    }
}
