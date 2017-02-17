package ap.services.servicesimpl;

import ap.dao.ExersiceDAO;
import ap.dao.WorkoutDAO;
import ap.entity.EntityForXML.ExerciseXML;
import ap.entity.EntityForXML.TryXML;
import ap.entity.Exercise;
import ap.entity.Try;
import ap.entity.Workout;
import ap.services.ExerciseService;
import ap.services.UserServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExerciseServiceImpl implements ExerciseService {
    @Autowired
    ExersiceDAO exersiceDAO;
    @Autowired
    WorkoutDAO workoutDAO;
    @Autowired
    UserServices userServices;

    @Override
    @Transactional
    public ExerciseXML createExercise(ExerciseXML exerciseXML) throws HibernateException {
        Exercise exercise = new Exercise();
        exercise.setName(exerciseXML.getName());
        if (workoutDAO.checkItBD(exerciseXML.getWorkoutId())) {
            Workout workout = workoutDAO.getById(exerciseXML.getWorkoutId());
            if (userServices.allow(workout.getParentid().getId())) {
                exercise.setParentid(workout);
                try {
                    exerciseXML.setPosition(workout.getExerciseList().size());
                } catch (NullPointerException e) {
                    exercise.setPosition(0);
                }
                int id = exersiceDAO.add(exercise);
                exerciseXML.setExerciseId(id);
                return exerciseXML;
            }
            return null;
        }
        return null;
    }

    @Override
    @Transactional
    public boolean changeExercise(ExerciseXML exerciseXML) {
        if (exersiceDAO.checkItBD(exerciseXML.getExerciseId())) {
            Exercise exercise = exersiceDAO.getById(exerciseXML.getExerciseId());
            if (userServices.allow(exercise.getParentid().getParentid().getId())) {
                exercise.setName(exerciseXML.getName());
                exersiceDAO.update(exercise);
                return true;
            }
        }
        return false;

    }

    @Override

    public boolean deleteExercise(int id) {
        if (exersiceDAO.checkItBD(id)) {
            Exercise exercise = exersiceDAO.getById(id);
            if (userServices.allow(exercise.getParentid().getParentid().getId())) {
                exercise.setId(id);//??
                exersiceDAO.delete(exercise);
                return true;
            }
        }
        return false;

    }

    @Override
    @Transactional
    public ExerciseXML getExerciseXML(int id) {
        if (exersiceDAO.checkItBD(id)) {
            Exercise exercise = exersiceDAO.getById(id);
            List<Try> tryList = exercise.getTryList();
            List<TryXML> tryXMLList = new ArrayList<>();
            for (Try tries : tryList) {
                TryXML tryXML = new TryXML(tries);
                tryXMLList.add(tryXML);
            }
            ExerciseXML exerciseXML = new ExerciseXML(exercise);
            exerciseXML.setList(tryXMLList);
            return exerciseXML;

        }
        return null;
    }

    @Override
    public ExerciseXML validExerciseXML(ExerciseXML exerciseXML, BindingResult bindingResult) {
        Map<String, String> mapErrors = new HashMap<>();

        for (FieldError error : bindingResult.getFieldErrors()) {
            mapErrors.put(error.getField(), error.getDefaultMessage());
        }
        exerciseXML.setName(mapErrors.get("name"));
        return exerciseXML;
    }
}
