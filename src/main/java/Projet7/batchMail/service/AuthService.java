package Projet7.batchMail.service;

import Projet7.batchMail.dto.UserDTO;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Data
@Service
public class AuthService {

    Logger logger = (Logger) LoggerFactory.getLogger(AuthService.class);

    private String memoireToken;
    private Boolean authentification;
    private UserDTO userConnecte;


}
