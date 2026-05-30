package com.example.simplebatch.batch;


import com.example.simplebatch.entity.AfterEntity;
import com.example.simplebatch.entity.BeforeEntity;
import com.example.simplebatch.repository.AfterRepository;
import com.example.simplebatch.repository.BeforeRepository;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.data.RepositoryItemReader;
import org.springframework.batch.infrastructure.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Map;

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

    @Bean
    public Step firstStep() {
        return new StepBuilder("firstStep", jobRepository)
                .<BeforeEntity, AfterEntity> chunk(10, platformTransactionManager)
                .reader(beforeReader())
                .processor()
                .writer()
                .build();

    }

    @Bean
    public RepositoryItemReader<BeforeEntity> beforeReader() {
        return new RepositoryItemReaderBuilder<BeforeEntity>()
                .name("beforeReader")
                .pageSize(10)
                .methodName("findAll")
                .repository(beforeRepository)
                .sorts(Map.of("id", Sort.Direction.ASC))
                .build();
    }

}
