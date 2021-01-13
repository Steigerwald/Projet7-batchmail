package Projet7.batchMail.batch;

import Projet7.batchMail.form.LoginForm;
import Projet7.batchMail.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ItemReaderLogin implements ItemReader<String> {

    @Autowired
    public UserService userService;

    public LoginForm utilisateur;

    @Override
    public String  read () throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        LoginForm utilisateur = new LoginForm();
        utilisateur.setMotDePasse("coco");
        utilisateur.setUserName("user@gmail.com");
        String token =userService.getTokenByMailAndMotDePasse(utilisateur);
        return token;
    }

}
