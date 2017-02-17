package ap.controller.RestControllers;

import ap.entity.EntityForXML.ExerciseXML;
import ap.entity.EntityForXML.WorkoutXML;
import ap.entity.User;
import ap.services.ExerciseService;
import ap.services.UserServices;
import ap.services.WorkoutService;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v.1/workout")
public class WorkoutControllerRest {

    @Autowired
    UserServices userServices;

    @Autowired
    WorkoutService workoutService;

    @Autowired
    ExerciseService exerciseService;

    /**
     * @param id       id of workout in BD
     * @param response 404 if workout is nit found
     * @return all exercise in WorkoutXML
     */
    @RequestMapping(value = "/{id}/exercise", method = RequestMethod.GET)
    public
    @ResponseBody
    WorkoutXML getWorkoutsById(@PathVariable int id, HttpServletResponse response) {
        WorkoutXML workoutXML;
        try {
            workoutXML = workoutService.getWorkoutXML(id);
        } catch (NullPointerException | ObjectNotFoundException e) {
            response.setStatus(404);
            return null;
        }
        response.setStatus(200);
        return workoutXML;
    }

    /**
     * @param exerciseXML   it need not empty name in exerciseXML
     * @param bindingResult
     * @param id            id of workout in BD
     * @param response      401   user is not authenticated
     *                      400 exerciseXML has error
     *                      404 workout not found for this user
     * @return new exercise
     */
    @RequestMapping(value = "/{id}/exercise", method = RequestMethod.POST, produces = {"application/xml; charset=UTF-8"})
    @Transactional
    public
    @ResponseBody
    ExerciseXML createExercise(@RequestBody @Valid ExerciseXML exerciseXML, BindingResult bindingResult, @PathVariable int id, HttpServletResponse response) {
        User user = userServices.getLoggedUser();
        System.err.println(user);
        System.err.println(exerciseXML);
        if (user == null) {
            response.setStatus(401);
            return null;
        }
        if (bindingResult.hasErrors()) {
            response.setStatus(400);
            return exerciseService.validExerciseXML(exerciseXML, bindingResult);
        }
        exerciseXML.setWorkoutId(id);
        exerciseXML = exerciseService.createExercise(exerciseXML);
        if (exerciseXML == null) {
            response.setStatus(404);
            return null;
        }
        response.setStatus(201);
        response.setHeader("Location", "/api/v.1/exercise/" + exerciseXML.getExerciseId());
        return exerciseXML;
    }


    /**
     * @param id       id of workout in BD
     * @param response 401  if user is not authenticated
     *                 404 workout with id is not found for authenticated user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public
    @Transactional
    @ResponseBody
    void deleteWorkoutsById(@PathVariable int id, HttpServletResponse response) {
        try {
            User user = userServices.getLoggedUser();
            if (user == null) {
                response.setStatus(401);
                return;
            }
            boolean flsg = false;
            flsg = workoutService.deleteWorkout(id);
            if (flsg) {
                response.setStatus(200);
            } else response.setStatus(404);

        } catch (HibernateException e) {
        }
    }

    /**
     * @param workoutXML    workoutXML with data for change(name)
     * @param bindingResult
     * @param id            id workout in db
     * @param response      401 if user is not authenticated
     *                      403 this id workout is not id for authenticated user
     *                      404 error in name
     * @return changed workoutXML
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/xml; charset=UTF-8"})
    public
    @Transactional
    @ResponseBody
    WorkoutXML changeWorkoutsById(@RequestBody @Valid WorkoutXML workoutXML, BindingResult bindingResult, @PathVariable int id, HttpServletResponse response) {
        User user = userServices.getLoggedUser();
        if (user == null) {
            response.setStatus(401);
            return null;
        }
        if (bindingResult.hasErrors()) {
            response.setStatus(400);
            return workoutService.validWorkoutXML(workoutXML, bindingResult);
        }
        workoutXML.setWorkoutId(id);
        if (workoutService.changeWorkout(workoutXML)) {
            response.setStatus(200);
            return workoutXML;
        } else {
            response.setStatus(403);
            return workoutXML;
        }

    }

    /**
     * @param id       id copying workout
     * @param response 401 user is not authenticated
     *                 404 workout is not found in DB
     * @return new workoutXML
     */
    @RequestMapping(value = "/{id}/copy", method = RequestMethod.POST, produces = {"application/xml; charset=UTF-8"})
    public
    @Transactional
    @ResponseBody
    WorkoutXML copyWorkoutsById(@PathVariable int id, HttpServletResponse response) {
        User user = userServices.getLoggedUser();
        WorkoutXML workoutXML = null;
        if (user != null) {
            workoutXML = workoutService.copyWorkout(id);
            response.setStatus(201);
            response.setHeader("Location", "/api/v.1/workout/" + workoutXML.getWorkoutId());
            return workoutXML;
        } else {
            response.setStatus(401);
            return null;
        }
    }

    /**
     * @param workoutXML it need not empty rate workoutXML(0-minus rate, 1- plus rate)
     * @param id         id workout for rate
     * @param response   400 field rate in workoutXML is not 0 or 1
     *                   401 user is not authenticated
     *                   404 workout is not found
     * @return           workoutXML with final rate
     */
    @RequestMapping(value = "/{id}/rate", method = RequestMethod.PUT, produces = {"application/xml; charset=UTF-8"})
    public
    @Transactional
    @ResponseBody
    WorkoutXML rateWorkoutsById(@RequestBody WorkoutXML workoutXML, @PathVariable int id, HttpServletResponse response) {
        if (workoutXML.getRate() > 1 || workoutXML.getRate() < -1) {
            response.setStatus(400);
            return null;
        }
        User user = userServices.getLoggedUser();
        if (user != null) {
            workoutXML.setUserId(user.getId());
            workoutXML.setWorkoutId(id);
            workoutXML = workoutService.rateWorkout(workoutXML);
            if (workoutXML == null) {
                response.setStatus(404);
                return null;
            } else {
                response.setStatus(200);
                return workoutXML;
            }
        } else {
            response.setStatus(401);
            return null;
        }
    }

}
