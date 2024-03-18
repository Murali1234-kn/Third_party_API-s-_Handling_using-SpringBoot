package com.Consuming_Thirdparty_Api.Cors_Enable_ThirdpartyApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class ApiService
{
    @Autowired
    private CacheManager cacheManager;
    @Cacheable(key = "'apiData'", cacheNames = "apiDataCache")
    public String consumeAndCacheApi()
    {
        // Checking  the data is already in cache
        Cache cache = cacheManager.getCache("apiDataCache");
        Cache.ValueWrapper cachedData = cache.get("apiData");

        if (cachedData != null) {

            return (String) cachedData.get();   // If data is found in cache, return it

        }
        else
        {
            String apiResponse = fetchDataFromApi(); // If data is not in cache, fetch it from the third-party API


            cache.put("apiData", apiResponse);  // Cache the API response

            // Calculate the expiration time (1 hour from now)
            long expirationTimeMillis = System.currentTimeMillis() + TimeUnit.HOURS.toMillis(1);

            cache.put("apiDataExpiration", expirationTimeMillis);// Cache the expiration time

            return apiResponse;
        }
    }

    private String fetchDataFromApi()
    {

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", String.class);
    }
}
