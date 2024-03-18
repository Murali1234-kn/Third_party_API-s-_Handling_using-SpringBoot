package com.Consuming_Thirdparty_Api.Cors_Enable_ThirdpartyApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;
    @GetMapping("/api")
    public String consumeApi() {
        return apiService.consumeAndCacheApi();
    }

}