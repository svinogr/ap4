package ap.controller.RestControllers;

import ap.entity.EntityForXML.ExerciseXML;
import ap.entity.EntityForXML.TryXML;
import ap.entity.User;
import ap.services.ExerciseService;
import ap.services.TryService;
import ap.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v.1/exercise")
public class ExerciseControllerRest {
    @Autowired
    ExerciseService exerciseService;
    @Autowired
    UserServices userServices;
    @Autowired
    TryService tryService;


    /**
     * @param id       id exercise in BD
     * @param response 404 exercise is not found
     * @return exerciseXML
     */
    @RequestMapping(value = "/{id}/try", method = RequestMethod.GET)
    public
    @Transactional
    @ResponseBody
    ExerciseXML getExerciseById(@PathVariable int id, HttpServletResponse response) {
        ExerciseXML exerciseXML = exerciseService.getExerciseXML(id);
        if (exerciseXML == null) {
            response.setStatus(404);
            return null;
        }
        response.setStatus(200);
        return exerciseXML;
    }

    /**
     * @param tryXML        tryXML witt not empty fields weight and repeat
     * @param id            id exercise in DB
     * @param response      401 user is not authenticated
     *                      404 exercise is not found for this authenticated user
     *                      400 invalid fields tryXML
     * @return new tryXML
     */
    @RequestMapping(value = "/{id}/try", method = RequestMethod.POST)
    @Transactional
    public
    @ResponseBody
    TryXML createTry(@RequestBody @Valid TryXML tryXML, @PathVariable int id, HttpServletResponse response) {
        User user = userServices.getLoggedUser();
        if (user == null) {
            response.setStatus(401);
            return null;
        }
        tryXML = tryService.validTryXML(tryXML);
        if(tryXML.getWeight()==0 || tryXML.getRepeat()==0){
            response.setStatus(400);
            return tryXML;
        }
        tryXML.setExerciseId(id);
        tryXML = tryService.createTry(tryXML);

        if (tryXML == null) {
            response.setStatus(404);
            return null;
        }
        response.setStatus(201);
        response.setHeader("Location", "/api/v.1/try/" + tryXML.getTryId());
        return tryXML;
    }

    /**
     * @param id id of exercise in DB
     * @param response 401 user is not authenticated
     *                 404 exercise is not found for this user
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public
    @Transactional
    @ResponseBody
    void deleteWorkoutsById(@PathVariable int id, HttpServletResponse response) {
            User user = userServices.getLoggedUser();
            if (user == null) {
                response.setStatus(401);
                return;

            }
            boolean flsg = false;
            flsg = exerciseService.deleteExercise(id);
            if (flsg) {
                response.setStatus(200);
            } else response.setStatus(404);
    }


    /**
     * @param exerciseXML   with data for change(name)
     * @param bindingResult
     * @param id            id workout in db
     * @param response      401 if user is not authenticated
     *                      403 this id workout is not id for authenticated user
     *                      404 error in name
     * @return changed exerciseXM:
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public
    @Transactional
    @ResponseBody
    ExerciseXML changeWorkoutsById(@RequestBody @Valid ExerciseXML exerciseXML, BindingResult bindingResult, @PathVariable int id, HttpServletResponse response) {
        User user = userServices.getLoggedUser();
        if (user == null) {
            response.setStatus(401);
            return null;
        }
        if (bindingResult.hasErrors()) {
            response.setStatus(400);
            return exerciseService.validExerciseXML(exerciseXML, bindingResult);
        }
        exerciseXML.setExerciseId(id);
        if (exerciseService.changeExercise(exerciseXML)) {
            response.setStatus(200);
            return exerciseXML;
        } else {
            response.setStatus(404);
            return exerciseXML;
        }
    }




}
