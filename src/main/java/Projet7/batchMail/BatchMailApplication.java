package Projet7.batchMail;

import Projet7.batchMail.batch.*;
import Projet7.batchMail.dto.ReservationDTO;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.util.Properties;

@EnableBatchProcessing
@SpringBootApplication
public class BatchMailApplication {
/*
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private ItemReader<String> itemReader;
	@Autowired
	private ItemReader<ReservationDTO> reservationDTOItemReader;
	@Autowired
	private ItemProcessor<String,String> itemProcessor;
	@Autowired
	private ItemProcessor<ReservationDTO,ReservationDTO> itemProcessorReservation;
	@Autowired
	private ItemWriter<String> itemWriter;
	@Autowired
	private ItemWriter<ReservationDTO> itemWriterReservation;


	@Bean
	public ItemReader<String>itemReader() throws IOException, InterruptedException {
		return new ItemReaderLogin();
	}

 */
/*
	@Bean
	public ItemReader<ReservationDTO> reservationDTOItemReader(){
		return new ItemReaderReservation();
	}
*/
/*
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

	@Bean
	public Step reservationStep(){
		return stepBuilderFactory.get("Step4")
				.<ReservationDTO,ReservationDTO>chunk(1)
				.reader(reservationDTOItemReader)
				.processor(itemProcessorReservation)
				.writer(itemWriterReservation)
				.build();
	}

 */


/*
	@Bean
	public Step connectingStep(){
		return stepBuilderFactory.get("Step3")
				.tasklet(new LoginTasklet())
				.build();
	}
*/
/*
	@Bean
	public Job helloWordJob(){
		return jobBuilderFactory.get("Job")
				.start(helloWordStep())
				.next(connectingStep())
				.next(commentStep())
				.next(reservationStep())
				.build();
	}

 */

	/*
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("my.gmail@gmail.com");
		mailSender.setPassword("password");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

	 */

	public static void main(String[] args) {
		SpringApplication.run(BatchMailApplication.class, args);
	}

}
