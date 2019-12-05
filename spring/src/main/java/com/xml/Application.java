package com.xml;

import com.xml.service.TestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Description: Application</p>
 *
 * <p>Copyright: © 2018-至今 .All rights reserved.</p>
 *
 * @author yangsanchao
 * *
 * @date 2019-09-24 11:25:25
 **/
@Log4j2
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationfile.xml");

        String applicationName = context.getApplicationName();
        String displayName = context.getDisplayName();
        Long startupDate = context.getStartupDate();
        String contextId = context.getId();

        log.info(applicationName);
        log.info(displayName);
        log.info(startupDate);
        log.info(contextId);

        TestService testService = context.getBean(TestService.class);
        testService.print();

    }

}