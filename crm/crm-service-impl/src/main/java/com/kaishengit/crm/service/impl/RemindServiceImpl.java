package com.kaishengit.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.Example.RemindExample;
import com.kaishengit.crm.entity.Remind;
import com.kaishengit.crm.entity.Staff;
import com.kaishengit.crm.jobs.WeixinQuartzJob;
import com.kaishengit.crm.mapper.RemindMapper;
import com.kaishengit.crm.service.RemindService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RemindServiceImpl implements RemindService {
    private Logger logger = LoggerFactory.getLogger(RemindServiceImpl.class);
    @Autowired
    private RemindMapper remindMapper;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    @Override
    public PageInfo<Remind> findAllByStaffId(Staff staff, Integer pageNo) {
        PageHelper.startPage(pageNo,15);
        List<Remind> reminds = remindMapper.findAllByStaffId(staff.getId());
        return new PageInfo<>(reminds);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveNewRemind(Remind remind) {
        remind.setDone((byte)0);
        remind.setCreateTime(new Date().toString());
        remindMapper.insert(remind);
        logger.debug("创建新的代办事项 {}",remind.getContent());
        //添加新的调度任务
        if (StringUtils.isNotEmpty(remind.getRemindTime())){
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.putAsString("staffId",remind.getStaffId());
            jobDataMap.put("message",remind.getContent());

            JobDetail jobDetail = JobBuilder.newJob(WeixinQuartzJob.class)
                    .setJobData(jobDataMap)
                    .withIdentity(new JobKey("remindId:" + remind.getId(),"sendMessage"))
                    .build();

            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
            DateTime dateTime = formatter.parseDateTime(remind.getRemindTime());

            StringBuilder cron = new StringBuilder("0")
                    .append(" ")
                    .append(dateTime.getMinuteOfHour())
                    .append(" ")
                    .append(dateTime.getHourOfDay())
                    .append(" ")
                    .append(dateTime.getDayOfMonth())
                    .append(" ")
                    .append(dateTime.getMonthOfYear())
                    .append(" ? ")
                    .append(dateTime.getYear());
            logger.debug("CRON EX： {}",cron.toString());

            ScheduleBuilder scheduleBuilder =
                    CronScheduleBuilder.cronSchedule(cron.toString());
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withSchedule(scheduleBuilder)
                    .build();
            Scheduler scheduler = schedulerFactoryBean.getScheduler();

            try {
                scheduler.scheduleJob(jobDetail,trigger);
            }catch (Exception e){
                logger.error("执行添加代办事项时出错，异常是 {}",e.getMessage());
            }

        }

    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void deleById(Integer id) {
        Remind remind = remindMapper.selectByPrimaryKey(id);
        remindMapper.deleteByPrimaryKey(id);
        //删除定时任务
        if (StringUtils.isNotEmpty(remind.getRemindTime())){
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            try{
                scheduler.deleteJob(new JobKey("remindId:" +id,"sendMessage"));
                logger.debug("删除定时任务{}","remindId:" +id,"sendMessage");
            }catch (Exception e){
                throw new RuntimeException(e.getMessage());
            }

        }
    }

    @Override
    public List<Remind> findAllByChanceId(Integer id) {
        RemindExample remindExample = new RemindExample();
        remindExample.createCriteria().andChanceIdEqualTo(id);
        return remindMapper.selectByExample(remindExample);
    }

    @Override
    public List<Remind> findAllByCustId(Integer id) {
        RemindExample remindExample = new RemindExample();
        remindExample.createCriteria().andCustIdEqualTo(id);
        return remindMapper.selectByExample(remindExample);
    }

    @Override
    public void doneRemind(Integer id) {
        Remind remind = remindMapper.selectByPrimaryKey(id);
        remind.setDone((byte)1);
        remindMapper.updateByPrimaryKey(remind);
    }

}
