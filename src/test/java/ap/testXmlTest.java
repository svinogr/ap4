/*
package ap;

import ap.config.HibernateConfig;
import ap.dao.BasicDAO;
import ap.dao.WorkoutContainerDAO;
import ap.entity.Exercise;
import ap.entity.Try;
import ap.entity.Workout;
import ap.entity.WorkoutContainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = HibernateConfig.class)
public class testXmlTest {
    @Autowired
    BasicDAO basicDAO;

    @Autowired
    WorkoutContainerDAO workoutContainerDAO;

    @Test
    public void test() {
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(new File("C:\\Lab3\\ap4\\web\\test.xml"));
            Node root = document.getDocumentElement();

            NodeList list = root.getChildNodes();
            System.out.println(list.getLength());
            for (int i = 0; i < list.getLength(); i++) {
                if (list.item(i).getNodeType() != Node.TEXT_NODE) {
                    NodeList list1 = list.item(i).getChildNodes();
                    for (int j = 0; j < list1.getLength(); j++) {
                        System.out.println(list1.item(j).getNodeName());
                        String s = list1.item(j).getTextContent();
                        System.out.println(s);

                    }
                }
            }


        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testJBOnetoMany() {
      WorkoutContainer workoutContainer =  workoutContainerDAO.getById(450);
        System.out.println(workoutContainer.getWorkoutContainerId());
    }


    @Test
    public void testJBToXML() {
        Workout workout = new Workout();
        workout.setName("Бицепс");
        workout.setPosition(1);
        Exercise exercise = new Exercise();
        exercise.setName("Подьем штанги");
        exercise.setPosition(1);
        Try ttry = new Try();
        ttry.setPosition(1);
        ttry.setWeight(25);
        ttry.setRepeat(12);
        Try ttry2 = new Try();
        ttry2.setPosition(2);
        ttry2.setWeight(20);
        ttry2.setRepeat(14);
        exercise.getTryList().add(ttry);
        exercise.getTryList().add(ttry2);
        workout.getList().add(exercise);
        Workout workout2 = new Workout();
        workout2.setName("Бицепс");
        workout2.setPosition(2);
        Exercise exercise2 = new Exercise();
        exercise2.setName("Подьем штанги");
        exercise2.setPosition(2);
        Try ttry3 = new Try();
        ttry3.setPosition(1);
        ttry3.setWeight(25);
        ttry3.setRepeat(12);
        Try ttry4 = new Try();
        ttry4.setPosition(2);
        ttry4.setWeight(20);
        ttry4.setRepeat(14);
        exercise2.getTryList().add(ttry3);
        exercise2.getTryList().add(ttry4);
        workout2.getList().add(exercise2);
        WorkoutContainer workoutContainer = new WorkoutContainer();
        workoutContainer.getWorkoutList().add(workout);
        workoutContainer.getWorkoutList().add(workout2);

        try {
            File file = new File("C:\\Lab3\\ap4\\web\\testJB.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(WorkoutContainer.class);
            Marshaller jabMarshaller = jaxbContext.createMarshaller();

            jabMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jabMarshaller.marshal(workoutContainer, file);

            jabMarshaller.marshal(workoutContainer, System.out);

        } catch (JAXBException e) {

        }
    }

    @Test
    public void testJAXBFromXML() {
        WorkoutContainer workout= null;
        try {
            File file = new File("C:\\Lab3\\ap4\\web\\testJB.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(WorkoutContainer.class);

            Unmarshaller jaxbUnmarshaler = jaxbContext.createUnmarshaller();
            workout = (WorkoutContainer) jaxbUnmarshaler.unmarshal(file);

            System.out.println(workout.getWorkoutList().size());
            System.out.println(workout.getWorkoutList().toString());

        } catch (JAXBException e) {

        }
        basicDAO.add("hjhjh");
    }
}

*/
