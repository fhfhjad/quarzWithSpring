package com.sundoctor.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("simpleService")
public class SimpleService {

	private static final Logger logger = LoggerFactory.getLogger(SimpleService.class);

	public void testMethod(String triggerName) {
		// 这里执行定时调度业务
		logger.info(triggerName);
	}

}
