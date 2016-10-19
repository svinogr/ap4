package ap.services.servicesimpl;

import ap.entity.User;
import ap.entity.Xmlable;
import ap.services.CreateXMLService;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.StringWriter;

public class CreateXMLServiceImpl implements CreateXMLService {


    @Override
    public StringWriter  getXML(User user) {
        StringWriter s=new StringWriter();
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(user, s);
            System.out.println(s);


        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return s;
    }



}
