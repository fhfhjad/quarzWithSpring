package com.sundoctor.example.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sundoctor.quartz.service.SchedulerService;

public class MainTest {

	/**
	 * @param args
	 * @throws SchedulerException
	 */
	public static void main(String[] args) throws SchedulerException {

		ApplicationContext springContext = new ClassPathXmlApplicationContext(new String[] {
				"classpath:applicationContext.xml", "classpath:applicationContext-quartz.xml" });
		SchedulerService schedulerService = springContext.getBean("schedulerService", SchedulerService.class);

		// 执行业务逻辑...

		// 设置高度任务
		// 每10秒中执行调试一次
		schedulerService.schedule("0/10 * * ? * * *");

		Date startTime = parse("2014-08-19 16:33:00");
		Date endTime = parse("2014-08-22 21:10:00");

		// 2014-08-19 16:33:00开始执行调度
		schedulerService.schedule(startTime);

		// 2014-08-19 16:33:00开始执行调度，2014-08-22 21:10:00结束执行调试
		schedulerService.schedule(startTime, endTime);

		// 2014-08-19 16:33:00开始执行调度，执行5次结束
		schedulerService.schedule(startTime, 5);

		// 2014-08-19 16:33:00开始执行调度，每隔20秒执行一次，执行5次结束
		schedulerService.schedule(startTime, 5, 20);

		// 等等，查看com.sundoctor.quartz.service.SchedulerService

	}

	private static Date parse(String dateStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return format.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

}
