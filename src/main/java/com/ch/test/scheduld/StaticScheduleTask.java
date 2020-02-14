package com.ch.test.scheduld;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * @ClassName: StaticScheduleTask
 * @Description: TODO
 * @Author: caihao
 * @Date: 2020/2/14 14:18
 */
@Component
@EnableScheduling
public class StaticScheduleTask {
    /**
     * @Scheduled ：是单线程的串行的执行定时任务，如果是单个的任务这样做是可行的；
     *              当任务增多，如果一个任务卡死了，会导致其他的任务也不能执行。
     */

    /**默认是fixedDelay 上一次执行完毕时间后执行下一轮*/
    @Scheduled(cron = "0/5 * * * * *")
    private void run1() throws InterruptedException {
        //x线程阻塞6秒，定时任务是每隔6秒执行一次
        Thread.sleep(6000);
        System.out.println(Thread.currentThread().getName() +"=====>>>>>使用cron {} "+ LocalTime.now());
    }

    /**fixedRate:上一次开始执行时间点之后5秒再执行*/
    @Scheduled(fixedRate = 5000)
    public void run2() throws InterruptedException {
        Thread.sleep(6000);
        System.out.println(Thread.currentThread().getName() + "=====>>>>>使用fixedRate {} " + LocalTime.now());
    }

    /**fixedDelay:上一次执行完毕时间点之后5秒再执行*/
    @Scheduled(fixedDelay = 5000)
    public void run3() throws InterruptedException {
        Thread.sleep(7000);
        System.out.println(Thread.currentThread().getName() + "=====>>>>>使用fixedDelay {} " + LocalTime.now());
    }

    /**第一次延迟2秒后执行，之后按fixedDelay的规则每5秒执行一次*/
    @Scheduled(initialDelay = 2000, fixedDelay = 5000)
    public void run4(){
        System.out.println(Thread.currentThread().getName() + "=====>>>>>使用initialDelay  {}" + LocalTime.now());
    }




}
