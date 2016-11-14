package ap.services.servicesimpl;

import ap.entity.Workout;
import ap.entity.Xmlable;
import ap.services.CreateWorkoutXMLService;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import static org.osgi.util.measurement.Unit.s;

public class CreateWorkoutXMLServiceImpl implements CreateWorkoutXMLService {

    @Override
    @Transactional
    public StringWriter getXML(Xmlable xmlable) {
        StringWriter s = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Workout.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    @Transactional
    public Workout getWorkoutFromXML(Workout workout) {
       StringWriter stringWriter = new StringWriter();
        Workout copyWorkout = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Workout.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(workout, stringWriter);
            StringReader stringReader = new StringReader(stringWriter.toString());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            copyWorkout = (Workout) unmarshaller.unmarshal(stringReader);
            copyWorkout.setCopy(true);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return copyWorkout;
    }
}
