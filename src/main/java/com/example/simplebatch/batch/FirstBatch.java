package com.example.simplebatch.batch;


import com.example.simplebatch.repository.AfterRepository;
import com.example.simplebatch.repository.BeforeRepository;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class FirstBatch {
    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    private final BeforeRepository beforeRepository;
    private final AfterRepository afterRepository;

    public FirstBatch(
            JobRepository jobRepository,
            PlatformTransactionManager platformTransactionManager,
            BeforeRepository beforeRepository,
            AfterRepository afterRepository) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
        this.beforeRepository = beforeRepository;
        this.afterRepository = afterRepository;
    }

    @Bean
    public Job firstJob() {

        System.out.println("fist job");

        return new JobBuilder("first job", jobRepository)
                .start()
                .build();

    }



}
