package Projet7.batchMail.batch;

import Projet7.batchMail.form.LoginForm;
import Projet7.batchMail.service.AuthService;
import Projet7.batchMail.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ItemReaderLogin implements ItemReader<String> {

    @Autowired
    public UserService userService;

    @Autowired
    public AuthService authService;

    LoginForm utilisateur=new LoginForm();
    int nextItemIndex;

    public ItemReaderLogin() throws IOException, InterruptedException {
    }
    public void initialize() throws IOException, InterruptedException {
        utilisateur.setMotDePasse("coco");
        utilisateur.setUserName("batch@gmail.com");
        nextItemIndex = 0;
    }

    @Override
    public String  read () throws IOException, InterruptedException {
        List <String> items = new ArrayList<String>();
        initialize();
        String token = userService.getTokenByMailAndMotDePasse(utilisateur);
        authService.setMemoireToken(token);
        items.add(token);
        String nextItem = null;
        if (nextItemIndex < items.size()) {
            nextItemIndex++;
        }
        else {
            nextItemIndex = 0;
        }
        return nextItem;
    }
}
