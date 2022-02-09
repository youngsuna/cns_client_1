package com.share.boot.discovery;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.share.boot.communicator.RestTemplateClientCommunicator;

import org.springframework.cloud.client.discovery.DiscoveryClient;

@Service
public class DiscoveryService {
	@Autowired
	private DiscoveryClient discoveryClient;
	 
	public List getServices(){
		List<String> services = new ArrayList<String>();
	 
	        /** 람다스트림 표현 */
	    discoveryClient.getServices().forEach(serviceName -> {
	        discoveryClient.getInstances(serviceName).forEach(instance->{
	        	services.add( String.format("%s:%s",serviceName,instance.getUri()));
	        });
	    });
	    return services;
	}
	 @Autowired
	    RestTemplateClientCommunicator restTemplateClientCommunicator;
	 
	    public String resttemplate(String id) {

	        return restTemplateClientCommunicator.getName(id);
	    }
}
