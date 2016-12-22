package ap.services.servicesimpl;

import ap.entity.User;
import ap.entity.Workout;
import ap.services.CreateXMLService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
@Component
public class CreateXMLServiceImpl implements CreateXMLService {


    @Override
    @Transactional
    public StringWriter getXML(User user) {
        StringWriter s = new StringWriter();
        try {
            for (Workout i : user.getWorkoutList()
                    ) {
                System.out.println(i.getName());
            }
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "Utf8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(user, s);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println(s.toString());
        return s;
    }
}
