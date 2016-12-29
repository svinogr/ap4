package ap.services.servicesimpl;

import ap.dao.WorkoutDAO;
import ap.entity.Workout;
import ap.entity.Xmlable;
import ap.services.CreateWorkoutXMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@Component
public class CreateWorkoutXMLServiceImpl implements CreateWorkoutXMLService {

    @Autowired
    WorkoutDAO workoutDAO;

    @Override
    @Transactional
    public StringWriter getXML(Xmlable xmlable) {
        StringWriter s = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Workout.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(xmlable, s);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return s;
    }


}
