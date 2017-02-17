package ap.services;

import ap.entity.EntityForXML.UserXML;
import ap.entity.EntityForXML.WorkoutXML;
import ap.entity.Exercise;
import ap.entity.Try;
import ap.entity.User;
import ap.entity.Workout;

import java.io.StringWriter;

public interface CreateXMLService<T> {


    UserXML getUserXMLWithPost(User user);

    UserXML getUserXML(User user);

    StringWriter getWorkoutXML(Workout workout);

    StringWriter getExerciseXML(Exercise exercise);

    StringWriter getTryXML(Try tries);

    User getUserFromXML(String xml);
}
