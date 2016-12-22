package ap.services.servicesimpl;

import ap.entity.Exercise;
import ap.entity.Xmlable;
import ap.services.CreateExerciseXMLService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class CreateExerciseXMLServiceImpl implements CreateExerciseXMLService {
    @Override
    @Transactional
    public StringWriter  getXML(Xmlable xmlable) {
        StringWriter s=new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Exercise.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(xmlable, s);
            System.out.println("делаю XML");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return s;
    }
}
