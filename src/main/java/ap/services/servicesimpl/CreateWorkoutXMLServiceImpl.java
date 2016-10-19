package ap.services.servicesimpl;

import ap.entity.User;
import ap.entity.Workout;
import ap.entity.Xmlable;
import ap.services.CreateWorkoutXMLService;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

public class CreateWorkoutXMLServiceImpl implements CreateWorkoutXMLService {

        @Override
        @Transactional
        public StringWriter  getXML(Xmlable xmlable) {
            StringWriter s=new StringWriter();
            try {

                JAXBContext jaxbContext = JAXBContext.newInstance(Workout.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                marshaller.marshal(xmlable, s);
                System.out.println(s);


            } catch (JAXBException e) {
                e.printStackTrace();
            }
            return s;
    }
}
