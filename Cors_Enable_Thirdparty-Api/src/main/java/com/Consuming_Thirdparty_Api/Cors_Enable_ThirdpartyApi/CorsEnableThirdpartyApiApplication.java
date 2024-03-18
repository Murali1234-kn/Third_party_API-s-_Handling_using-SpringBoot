package com.Consuming_Thirdparty_Api.Cors_Enable_ThirdpartyApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CorsEnableThirdpartyApiApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(CorsEnableThirdpartyApiApplication.class, args);
	}

}
