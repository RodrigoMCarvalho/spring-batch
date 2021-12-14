package com.rodrigo.primeiroprojetospringbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;


    @Bean
    public Job imprimeOlaJob(Step imprimeOlaStep) {
        return jobBuilderFactory
                .get("imprimeOlaJob")
                .start(imprimeOlaStep)
                //.incrementer(new RunIdIncrementer())  //executar mais de uma vez
                .build();
    }

//    public Step imprimeOlaStep() {
//        return stepBuilderFactory
//                .get("imprimeOlaStep")
//                .tasklet(imprimeTasket( null)).build();
//    }

//    @Bean
//    @StepScope
//    public Tasklet imprimeTasket(@Value("#{jobParameters['nome']}") String nome) { //Parametro passado pela IDE na execução
//        return new Tasklet() {
//            @Override
//            public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
//                System.out.println(String.format("Olá, %s", nome));
//                return RepeatStatus.FINISHED;
//            }
//        };
//    }
}

