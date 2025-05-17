package com.example.demo.SchedulerConcept;


import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
public class Scheduler {
    //this function is exceuted using corn expressino , the bellow runs for every second concept
    // cron is a expression which says to scheduler what time , month ,day , year , second , hour , minutes after the schedule to start
    @Scheduled(cron = "* * * ? * *")
    public void startTheScheduler()
    {
        System.out.println("The Scheduler is started = "+ LocalDateTime.now());
    }
    //this function will run for every 2s recursively
    @Scheduled(fixedRate = 20000)
    public void startTheScheduler2WithFixedRate()
    {
        System.out.println("The 2 nd Scheduler is started with fixed rate = "+ LocalDateTime.now());
    }


}
