package com.share.boot.communicator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;

@Component
public class RestTemplateClientCommunicator {

    @Autowired
    private DiscoveryClient discoveryClient;
    
    @CircuitBreaker(name = "hello", fallbackMethod = "helloFallback")
    public String getName(String id) {

        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("service2");

        if (instances.size()==0) return null;
        /** 인스턴스들 중 0번째 클라이언트에 요청 */
        String serviceUri = String.format("%s/menu/%s",instances.get(0).getUri().toString(), id);
    
        ResponseEntity<String> restExchange =
                restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null, String.class, id);

        return  restExchange.getBody() + "배달주문 접수~~";
    }
    
    private String helloFallback(Throwable t) {
        return "서킷브레이커 동작!!!!!!!!!!! : " + t.getClass();
    }
}