package com.example.simplebatch.controller;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class MainController {

    private final JobLauncher jobLauncher;

    private final JobRegistry jobRegistry;

    public MainController(JobLauncher jobLauncher, JobRegistry jobRegistry) {
        this.jobLauncher = jobLauncher;
        this.jobRegistry = jobRegistry;
    }

    @GetMapping("/first")
    public String firstApi(@RequestParam("value") String value) throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("date", value) // 의도: 특정 일자에 실행시키고 겹치는 날짜면 실행 X
                .toJobParameters();

        jobLauncher.run(jobRegistry.getJob("firstJob"), jobParameters);
        // JobRegistry에 등록되어 있는 firstJob이라는 Bean으로 적의해둔 이름을 들고오면 됨

        return "ok";
    }
}
