package Projet7.batchMail.batch.step;

import Projet7.batchMail.dto.ReservationDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ItemProcessorUser implements ItemProcessor<ReservationDTO,ReservationDTO> {

    @Override
    public ReservationDTO process(ReservationDTO s) throws Exception {
            System.out.println("ceci est le processor de " + s.getUser().getNomUser());
            System.out.println("le mail est: " + s.getUser().getMailUser());
            return s;
    }
}
