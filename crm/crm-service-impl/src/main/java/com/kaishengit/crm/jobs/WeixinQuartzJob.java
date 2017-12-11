package com.kaishengit.crm.jobs;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class WeixinQuartzJob implements Job {

    private Logger logger = LoggerFactory.getLogger(WeixinQuartzJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String message = (String) jobDataMap.get("message");
        Integer staffId = jobDataMap.getInt("staffId");
        try {
            ApplicationContext context = (ApplicationContext) jobExecutionContext.getScheduler().getContext().get("springApplicationContext");
            JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
            jmsTemplate.send("test-message", new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    String json = "{\"id\":"+staffId+",\"message\":\""+message+"\"}";
                    TextMessage textMessage = session.createTextMessage(json);
                    return textMessage;
                }
            });
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
