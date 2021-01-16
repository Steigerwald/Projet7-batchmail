package Projet7.batchMail;

import Projet7.batchMail.batch.CommentTasklet;
import Projet7.batchMail.batch.HelloWordTaskLet;
import Projet7.batchMail.batch.ItemReaderLogin;
import Projet7.batchMail.batch.LoginTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@EnableBatchProcessing
@SpringBootApplication
public class BatchMailApplication {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private ItemReader<String> itemReader;
	@Autowired
	private ItemProcessor<String,String> itemProcessor;
	@Autowired
	private ItemWriter<String> itemWriter;


	@Bean
	public ItemReader<String>itemReader() throws IOException, InterruptedException {
		return new ItemReaderLogin();
	}

	@Bean
	public Step helloWordStep(){
		return stepBuilderFactory.get("Step1")
				.tasklet(new HelloWordTaskLet())
				.build();
	}

	@Bean
	public Step commentStep(){
		return stepBuilderFactory.get("Step2")
				.tasklet(new CommentTasklet())
				.build();
	}

	@Bean
	public Step connectingStep(){
		return stepBuilderFactory.get("Step3")
				.<String,String>chunk(1)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
	}
/*
	@Bean
	public Step connectingStep(){
		return stepBuilderFactory.get("Step3")
				.tasklet(new LoginTasklet())
				.build();
	}
*/

	@Bean
	public Job helloWordJob(){
		return jobBuilderFactory.get("Job")
				.start(helloWordStep())
				.next(connectingStep())
				.next(commentStep())
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(BatchMailApplication.class, args);
	}

}
