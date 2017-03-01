package ap.services.servicesimpl;

import ap.dao.WorkoutDAO;
import ap.dao.WorkoutRatingDAO;
import ap.entity.*;
import ap.entity.EntityForXML.ExerciseXML;
import ap.entity.EntityForXML.TryXML;
import ap.entity.EntityForXML.UserXML;
import ap.entity.EntityForXML.WorkoutXML;
import ap.services.InfoUserService;
import ap.services.UserServices;
import ap.services.WorkoutService;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkoutServiceImpl implements WorkoutService {

    @Autowired
    WorkoutDAO workoutDAO;

    @Autowired
    UserServices userServices;

    @Autowired
    WorkoutRatingDAO workoutRatingDAO;
    @Autowired
    InfoUserService infoUserService;

    @Override
    @Transactional
    public WorkoutXML createWorkout(WorkoutXML workoutXML) throws HibernateException{
        Workout workout = new Workout();
        workout.setName(workoutXML.getName());
        User user = userServices.getLoggedUser();
        user = userServices.getById(user.getId());
        workout.setParentid(user);
        UserInfo userInfo = infoUserService.getUserInfoByParentId(user.getId());
        workout.setAuthor(userInfo.getName());
        try {
            workout.setPosition(user.getWorkoutList().size());
        }catch (NullPointerException e)
        {
            workout.setPosition(0);
        }
        int id = workoutDAO.add(workout);
        workoutXML.setAuthor(workout.getAuthor());
        workoutXML.setWorkoutId(id);
        workoutXML.setUserId(user.getId());
        return workoutXML;
    }

    @Override
    @Transactional
    public boolean changeWorkout(WorkoutXML workoutXML) {
        if(workoutDAO.checkItBD(workoutXML.getWorkoutId())) {
            Workout workout = workoutDAO.getById(workoutXML.getWorkoutId());
            if (userServices.allow(workout.getParentid().getId())) {
                workout.setName(workoutXML.getName());
                workoutDAO.update(workout);
                return true;
            }
        }
        return false;

    }

    @Override
    @Transactional
    public boolean deleteWorkout(int id) {
        try {
            if (workoutDAO.checkItBD(id)) {
                Workout workout = workoutDAO.getById(id);
                int userid = workout.getParentid().getId();
                if (userServices.allow(userid)) {
                    workoutDAO.delete(workout);
                    return true;
                }
            }
            return false;
        } catch (HibernateException e) {
            return false;
        }
    }

    @Override
    public WorkoutXML copyWorkout(int id) {
        User user = userServices.getLoggedUser();
        user = userServices.getById(user.getId());
        if (workoutDAO.checkItBD(id)) {
            try {
                Workout newWorkout = workoutDAO.copyWorkout(id, user);
                WorkoutXML workoutXML = new WorkoutXML(newWorkout);
                return workoutXML;
            } catch (HibernateException e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public WorkoutXML rateWorkout(WorkoutXML workoutXML) {
        if (workoutDAO.checkItBD(workoutXML.getWorkoutId())) {
            WorkoutRating workoutRating = workoutRatingDAO.getRate(workoutXML.getUserId(), workoutXML.getWorkoutId());
            Workout workout = workoutDAO.getById(workoutXML.getWorkoutId());
            int rate= workoutXML.getRate();
            if (workoutRating != null) {
                int status = workoutRating.getStatus();
                if (status == 0 && rate == 0) {
                    System.out.println("уже голосовал так");
                } else if (status == 0 && rate == 1) {
                    workoutRating.setStatus(1);
                    workout.setRate(workout.getRate() + 1);
                    workoutRatingDAO.delete(workoutRating);
                }
                if (status == 1 && rate == 1) {
                    System.out.println("уже голосовал так");
                } else if (status == 1 && rate == 0) {
                    workoutRating.setStatus(0);
                    workout.setRate(workout.getRate() - 1);
                    workoutRatingDAO.delete(workoutRating);
                }
            } else {
                WorkoutRating newWorkoutRating = new WorkoutRating();
                newWorkoutRating.setWorkoutId(workoutXML.getWorkoutId());
                newWorkoutRating.setUserId(workoutXML.getUserId());
                newWorkoutRating.setStatus(rate);
                if(rate ==0){
                    workout.setRate(workout.getRate()-1);
                }
                if(rate ==1){
                    workout.setRate(workout.getRate()+1);
                }
                System.err.println(newWorkoutRating.toString());
                workoutRatingDAO.add(newWorkoutRating);
            }
            return new WorkoutXML(workout);
        }
        return null;
    }


    @Override
    @Transactional
    public WorkoutXML getWorkoutXML(int id) throws NullPointerException, ObjectNotFoundException {
        Workout workout = workoutDAO.getById(id);
        if (workout == null) {
            throw new NullPointerException();
        }
        List<ExerciseXML> list = new ArrayList<>();
        List<Exercise> exerciseList = workout.getExerciseList();
        for (Exercise exercise : exerciseList) {
            ExerciseXML exerciseXML = new ExerciseXML(exercise);
            for (Try tries : exercise.getTryList()) {
                TryXML tryXML = new TryXML(tries);
                exerciseXML.getList().add(tryXML);
            }
            list.add(exerciseXML);
        }
        WorkoutXML workoutXML = new WorkoutXML(workout);
        workoutXML.setExerciseXMLList(list);
        return workoutXML;
    }

    @Override
    public WorkoutXML validWorkoutXML(WorkoutXML workoutXML, BindingResult bindingResult) {
        Map<String, String> mapErrors = new HashMap<>();

        for (FieldError error : bindingResult.getFieldErrors()) {
            mapErrors.put(error.getField(), error.getDefaultMessage());
        }
        workoutXML.setName(mapErrors.get("name"));
        return workoutXML;
    }

    @Override
    @Transactional
    public UserXML getAllWorkout(int start) {
        List<Workout> workoutList = workoutDAO.getListAllWorkout(start);
        List<WorkoutXML> workoutXMLList=new ArrayList<>();
        if(workoutList.size()>0){
            System.err.println(workoutList.size());
            for (Workout workout:workoutList){
                WorkoutXML workoutXML = new WorkoutXML(workout);
                workoutXMLList.add(workoutXML);
            }
            UserXML userXML = new UserXML();
            userXML.setWorkoutXML(workoutXMLList);
            return userXML;
        }
        return null;
    }
}
