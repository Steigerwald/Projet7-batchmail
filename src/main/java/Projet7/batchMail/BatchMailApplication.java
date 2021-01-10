package Projet7.batchMail;

import Projet7.batchMail.batch.CommentTasklet;
import Projet7.batchMail.batch.HelloWordTaskLet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class BatchMailApplication {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step HelloWordStep(){
		return stepBuilderFactory.get("Step1")
				.tasklet(new HelloWordTaskLet())
				.build();
	}

	@Bean
	public Step CommentStep(){
		return stepBuilderFactory.get("Step2")
				.tasklet(new CommentTasklet())
				.build();
	}

	@Bean
	public Job HelloWordJob(){
		return jobBuilderFactory.get("Job")
				.start(HelloWordStep())
				.next(CommentStep())
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(BatchMailApplication.class, args);
	}

}
