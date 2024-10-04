package com.nt.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.nt.binding.CitizenAppRegistationIntput;
import com.nt.entity.CitizenAppRegistartionEntity;
import com.nt.exception.InvalidSSNNumber;
import com.nt.repo.ICitizenAppRepo;

import reactor.core.publisher.Mono;

@Service
public class CitizenAppRegServiceImpl implements ICitizenAppRegService {

	@Autowired
	private ICitizenAppRepo repo;
	
	@Value("${ssh.url}")
	private String url;
	
	@Value("${ssh.state}")
	private String state;
	
//	@Autowired
//	private AppConfig app;
//	
//	@Autowired
//	private RestTemplate temp;
	
	@Autowired
	private WebClient web;
	
	
	@Override
	public Integer registerCitizenApplication(CitizenAppRegistationIntput inputs) {
		// get the state name of the applicant by usigng the ssn number off the above inputs class 
//		ResponseEntity<String> res = app.createTemplete().exchange(url,HttpMethod.GET,null,String.class,inputs.getSsn());
//		ResponseEntity<String> res = app.createTemplete().exchange(url,HttpMethod.GET,null,String.class,inputs.getSsn());
		Mono<String> res = web.get()
                .uri(url, inputs.getSsn())
                .retrieve()
                .onStatus(HttpStatus.BAD_REQUEST::equals, response -> 
                    response.bodyToMono(String.class)
                            .map(body -> new InvalidSSNNumber("Invalid SSN Number")))
                .bodyToMono(String.class);
         String stateName = res.block();
		if(state.equalsIgnoreCase(stateName)) {
			CitizenAppRegistartionEntity entity = new CitizenAppRegistartionEntity();
			BeanUtils.copyProperties(inputs, entity);
			entity.setStateName(stateName);
			return repo.save(entity).getAppId();
		}
		throw new InvalidSSNNumber("Invalid SSN Number");
	}

}
