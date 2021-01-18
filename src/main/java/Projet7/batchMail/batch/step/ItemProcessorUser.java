package Projet7.batchMail.batch.step;

import Projet7.batchMail.dto.ReservationDTO;
import Projet7.batchMail.service.ReservationService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemProcessorUser implements ItemProcessor<ReservationDTO,ReservationDTO> {

    @Autowired
    private ReservationService reservationService;

    @Override
    public ReservationDTO process(ReservationDTO s) throws Exception {
            System.out.println("ceci est le processor de " + s.getUser().getNomUser());
            System.out.println("le mail est: " + s.getUser().getMailUser());
            reservationService.getReservationsRelancees().add(s);
            return s;
    }
}
