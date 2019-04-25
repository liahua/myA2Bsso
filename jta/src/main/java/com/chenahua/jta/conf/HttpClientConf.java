package com.chenahua.jta.conf;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConf {
    @Bean("httpClient")
    public HttpClient getHttpClient(@Autowired PoolingHttpClientConnectionManager poolingHttpClientConnectionManager){
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);
        HttpClient httpClient = httpClientBuilder.build();
        return httpClient;
    }

    @Bean(name="httpClientConnectionManager",destroyMethod = "close")
    public PoolingHttpClientConnectionManager getHttpClientConnectionManager(){
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        poolingHttpClientConnectionManager.setMaxTotal(200);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(100);
        System.out.println("this is test");
        return poolingHttpClientConnectionManager;
    }


}
