package Projet7.batchMail.batch;

import Projet7.batchMail.form.LoginForm;
import Projet7.batchMail.service.AuthService;
import Projet7.batchMail.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginTasklet implements Tasklet {

    @Autowired
    public UserService userService;

    @Autowired
    public AuthService authService;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception,NullPointerException {
        LoginForm utilisateur = new LoginForm();
        utilisateur.setMotDePasse("coco");
        utilisateur.setUserName("batch@gmail.com");
        String token = new String();
        token =(userService.getTokenByMailAndMotDePasse(utilisateur));
        authService.setMemoireToken(token);
        System.out.println(authService.getMemoireToken());
        return RepeatStatus.FINISHED;
    }
}
