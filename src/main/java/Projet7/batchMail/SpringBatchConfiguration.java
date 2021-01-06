package Projet7.batchMail;


import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private ItemReader<Reservation> reservationItemReader;
    @Autowired
    private ItemWriter<User> userItemWriter;
    @Autowired
    private ItemProcessor<Reservation,User>itemProcessor;


    public org.springframework.batch.core.Job mailRelanceJob(){
      Step step1=stepBuilderFactory.get("RecuperationReservation")
              .<Reservation,User>chunk(4)
              .reader(reservationItemReader)
              .processor(itemProcessor)
              .writer(userItemWriter)
              .build();
      return jobBuilderFactory.get("UserConcerne")
              .start(step1)
              .build();
    }

}
