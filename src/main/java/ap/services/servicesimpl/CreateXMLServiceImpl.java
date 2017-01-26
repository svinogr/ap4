package ap.services.servicesimpl;

import ap.entity.EntityForXML.ExerciseXML;
import ap.entity.EntityForXML.TryXML;
import ap.entity.EntityForXML.UserXML;
import ap.entity.EntityForXML.WorkoutXML;
import ap.entity.Exercise;
import ap.entity.Try;
import ap.entity.User;
import ap.entity.Workout;
import ap.services.CreateXMLService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@Component
public class CreateXMLServiceImpl<T> implements CreateXMLService<T> {
    @Transactional
    public StringWriter getXML(T value) {

        StringWriter s = new StringWriter();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(value.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "Utf8");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(value, s);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println(s.toString());
        return s;
    }

    @Override
    @Transactional
    public StringWriter getUserXML(User user) {
        List<WorkoutXML> list = new ArrayList<>();
        List<Workout> workoutList = user.getWorkoutList();
        for (Workout workout : workoutList) {
            WorkoutXML workoutXML = new WorkoutXML(workout);
            workoutXML.setExerciseList(null);
            list.add(workoutXML);
        }
        UserXML userXML = new UserXML(user);
        userXML.setWorkoutXMLs(list);
        StringWriter stringWriter = getXML((T) userXML);
        return stringWriter;
    }

    @Override
    @Transactional
    public StringWriter getWorkoutXML(Workout workout) {
        List<ExerciseXML> list = new ArrayList<>();
        List<Exercise> exerciseList = workout.getExerciseList();
        for (Exercise exercise:exerciseList){
            ExerciseXML exerciseXML=new ExerciseXML(exercise);
            exerciseXML.setTryList(exercise.getTryList());
            list.add(exerciseXML);
        }
        WorkoutXML workoutXML = new WorkoutXML();
        workoutXML.setExerciseXMLList(list);
        StringWriter stringWriter = getXML((T) workoutXML);
        return stringWriter;
    }

    @Override
    @Transactional
    public StringWriter getExerciseXML(Exercise exercise) {
        List<TryXML> tryXMLList = new ArrayList<>(0);
        List<Try> list = exercise.getTryList();
        for (Try tries:list){
            TryXML tryXML = new TryXML(tries);
            tryXMLList.add(tryXML);
        }
        ExerciseXML exerciseXML = new ExerciseXML(exercise);
        exerciseXML.setList(tryXMLList);
        StringWriter stringWriter = getXML((T) exerciseXML);
        return stringWriter;
    }

    @Override
    public StringWriter getTryXML(Try tries) {
        return null;
    }

}
