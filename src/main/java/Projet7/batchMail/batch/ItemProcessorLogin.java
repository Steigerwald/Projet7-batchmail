package Projet7.batchMail.batch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ItemProcessorLogin implements ItemProcessor<String,String> {
    @Override
    public String process(String s) throws Exception {
        System.out.println(s);
        return s;
    }
}
