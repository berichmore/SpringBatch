package com.example.simplebatch.schedule;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class FirstSchedule {

    private final JobLauncher jobLauncher;

    private final JobRegistry jobRegistry;

    public FirstSchedule(JobLauncher jobLauncher, JobRegistry jobRegistry) {
        this.jobRegistry = jobRegistry;
        this.jobLauncher = jobLauncher;
    }


    /*
    * * * * * *
    │     │     │     │     │     └─ 요일 (0-7 또는 SUN-SAT, 0과 7은 모두 일요일)
    │     │     │     │     └─ 월 (1-12 또는 JAN-DEC)
    │     │     │     └─ 일 (1-31)
    │     │     └─ 시 (0-23)
    │     └─ 분 (0-59)
    └─ 초 (0-59)
     */
    @Scheduled(cron = "10 0 0  * * *",zone = "Asia/Seoul")
    public void runFirstJob() throws Exception{

        System.out.println("first schedule start");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = dateFormat.format(new Date());

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("date", date)
                .toJobParameters();

        jobLauncher.run(jobRegistry.getJob("firstJob"), jobParameters);

    }
}
