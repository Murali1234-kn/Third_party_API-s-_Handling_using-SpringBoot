package com.Consuming_Thirdparty_Api.Cors_Enable_ThirdpartyApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/cache")
public class Cachememory
{

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/info")
    public Map<String, Object> getCacheInfo() {
        Map<String, Object> cacheInfo = new HashMap<>();
        Cache cache = cacheManager.getCache("apiDataCache");

        Cache.ValueWrapper cachedData = cache.get("apiData");  // Check if data is in cache and retrieve it

        if (cachedData != null) {
            cacheInfo.put("dataInCache", cachedData.get());
        } else {
            cacheInfo.put("dataInCache", "No data in cache");
        }

        Cache.ValueWrapper expirationTime = cache.get("apiDataExpiration");  // Check remaining time in cache

        if (expirationTime != null)
        {
            long expirationTimeMillis = (long) expirationTime.get();
            long currentTimeMillis = System.currentTimeMillis();
            long remainingTimeMillis = expirationTimeMillis - currentTimeMillis;
            long remainingTimeSeconds = TimeUnit.MILLISECONDS.toSeconds(remainingTimeMillis);
            cacheInfo.put("remainingTimeSeconds", remainingTimeSeconds);
        } else {
            cacheInfo.put("remainingTimeSeconds", "Cache entry not found");
        }

        return cacheInfo;
    }
}
