package Projet7.batchMail.batch.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Map;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class QuartzJobLauncher extends QuartzJobBean {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobLocator jobLocator;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Job jobProcessor;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            jobLocator = applicationContext.getBean(JobLocator.class);
            jobLauncher = applicationContext.getBean(JobLauncher.class);
            JobParameters jobParameters=new JobParametersBuilder()
                    .addLong("time",System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(jobProcessor,jobParameters);
            //log.info("########### Status: " + jobProcessor.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
