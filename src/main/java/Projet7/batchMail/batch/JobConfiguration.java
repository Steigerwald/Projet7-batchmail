package Projet7.batchMail.batch;

import Projet7.batchMail.batch.step.CommentTasklet;
import Projet7.batchMail.batch.step.HelloWordTaskLet;
import Projet7.batchMail.batch.step.ItemReaderLogin;
import Projet7.batchMail.dto.ReservationDTO;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class JobConfiguration {

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
    public ItemReader<String> itemReader() throws IOException, InterruptedException {
        return new ItemReaderLogin();
    }
    /*
        @Bean
        public ItemReader<ReservationDTO> reservationDTOItemReader(){
            return new ItemReaderReservation();
        }
    */
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
                .next(reservationStep())
                .build();
    }

}
