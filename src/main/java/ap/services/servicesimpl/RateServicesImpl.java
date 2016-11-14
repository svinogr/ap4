package ap.services.servicesimpl;

import ap.dao.WorkoutDAO;
import ap.dao.WorkoutRatingDAO;
import ap.entity.Workout;
import ap.entity.WorkoutRating;
import ap.services.RateServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RateServicesImpl implements RateServices {

    @Autowired
    WorkoutDAO workoutDAO;

    @Autowired
    WorkoutRatingDAO workoutRatingDAO;

    @Override
    @Transactional
    public void rate(int  idWorkout, int rate, int userId ) throws HibernateException {
        WorkoutRating workoutRating;
        Workout workout;
        workoutRating = workoutRatingDAO.getRate(userId, idWorkout);
        workout = workoutDAO.getById(idWorkout);
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
            newWorkoutRating.setWorkoutId(idWorkout);
            newWorkoutRating.setUserId(userId);
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
    }
}
