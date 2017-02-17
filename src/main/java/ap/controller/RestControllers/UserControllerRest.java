package ap.controller.RestControllers;

import ap.entity.EntityForXML.PostXML;
import ap.entity.EntityForXML.UserXML;
import ap.entity.EntityForXML.WorkoutXML;
import ap.entity.Post;
import ap.entity.User;
import ap.services.CreateXMLService;
import ap.services.PostService;
import ap.services.UserServices;
import ap.services.WorkoutService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = {"/api/v.1/user/"})
public class UserControllerRest {
    @Autowired
    UserServices userServices;

    @Autowired
    CreateXMLService createXMLService;

    @Autowired
    WorkoutService workoutService;

    @Autowired
    PostService postService;

    /**
     * @param response 401 if user is not authenticated
     * @return xml UserXML{int userId, int userInfo, Date date, List<WorkoutXML>}
     */
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    @Transactional
    public
    @ResponseBody
    UserXML getUser(HttpServletResponse response) {
        User user = userServices.getLoggedUser();
        if (user != null) {
            user = userServices.getById(user.getId());
            UserXML userXML = createXMLService.getUserXML(user);
            response.setStatus(200);
            return userXML;
        } else {
            response.setStatus(401);
            return null;
        }
    }

    /**
     * @param id       id  of user in DB
     * @param response 404 if id is not found
     * @return xml UserXML
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @Transactional
    public
    @ResponseBody
    UserXML getUserByID(@PathVariable int id, HttpServletResponse response) {
        try {
            User user = userServices.getById(id);
            int i = user.getId();
            UserXML userXML = createXMLService.getUserXML(user);
            response.setStatus(200);
            return userXML;
        } catch (HibernateException e) {
            response.setStatus(404);
            return null;
        }
    }

    /**
     * @param userXML       xml with data type of <userXML><login></login><email></email><password></password></userXML>
     * @param bindingResult returm xml with invalid fields and messages
     * @param response      location with new user
     * @return xml type of <userXML><date></date><email></email><login></login><myUserId></myUserId><userInfoId></userInfoId></userXML>
     */
    @RequestMapping(method = RequestMethod.POST, headers = "Content-Type=application/xml",
            produces = {"application/xml; charset=UTF-8"})
    @Transactional
    public
    @ResponseBody
    UserXML createUser(@RequestBody @Valid UserXML userXML, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            Map<String, String> mapErrors = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                mapErrors.put(error.getField(), error.getDefaultMessage());
            }
            userXML.setLogin(mapErrors.get("login"));
            userXML.setEmail(mapErrors.get("email"));
            response.setStatus(400);
            return userXML;

        }
        userXML = userServices.registrationUser(userXML);
        response.setStatus(201);
        response.setHeader("Location", "/api/v.1/user/" + userXML.getUserId());
        return userXML;
    }


    @RequestMapping(value = "/auth/workout", method = RequestMethod.GET)
    @Transactional
    public
    @ResponseBody
    UserXML getUserWorkout(HttpServletResponse response) {
        User user = userServices.getLoggedUser();
        if (user != null) {
            user = userServices.getById(user.getId());
            UserXML userXML = createXMLService.getUserXML(user);
            response.setStatus(200);
            return userXML;
        } else {
            response.setStatus(401);
            return null;
        }
    }

    /**
     * @param workoutXML    it need not empty name in workoutXML
     * @param bindingResult
     * @param response      401   user is not authenticated
     *                      400 workoutXML has error
     * @return new workoutXML
     */
    @RequestMapping(value = "/auth/workout", method = RequestMethod.POST)
    @Transactional
    public
    @ResponseBody
    WorkoutXML createWorkout(@RequestBody @Valid WorkoutXML workoutXML, BindingResult bindingResult, HttpServletResponse response) {
        User user = userServices.getLoggedUser();
        if (user == null) {
            response.setStatus(401);
            return null;
        }
        if (bindingResult.hasErrors()) {
            response.setStatus(400);
            return workoutService.validWorkoutXML(workoutXML, bindingResult);
        }
        workoutXML = workoutService.createWorkout(workoutXML);

        response.setStatus(201);
        response.setHeader("Location", "/api/v.1/workout/" + workoutXML.getWorkoutId());
        return workoutXML;
    }


    /**
     * @param id       id  of user in DB
     * @param response 404 if id is not found
     * @return xml UserXML
     */
    @RequestMapping(value = "/{id}/workout", method = RequestMethod.GET)
    @Transactional
    public
    @ResponseBody
    UserXML getUserWorkoutByID(@PathVariable int id, HttpServletResponse response) {
        try {
            User user = userServices.getById(id);
            int i = user.getId();
            UserXML userXML = createXMLService.getUserXML(user);
            response.setStatus(200);
            return userXML;
        } catch (HibernateException e) {
            response.setStatus(404);
            return null;
        }
    }

    /**
     * @param id       is number in the list which is counting down the top 20 rated
     * @param response 404 if list is empty
     * @return userXML with workoutXML by rate
     */
    @RequestMapping(value = "/from/{id}", method = RequestMethod.GET)
    @Transactional
    public
    @ResponseBody
    UserXML getAllWorkoutByRatingFrom(@PathVariable int id, HttpServletResponse response) {
        int start = id;
        UserXML userXML = workoutService.getAllWorkout(start);
        if (userXML == null) {
            response.setStatus(404);
            return null;
        }
        response.setStatus(200);
        return userXML;
    }

    /**
     * @param postXML       postXML
     * @param bindingResult
     * @param response      401   user is not authenticated
     *                      403   user has not rules
     *                      400   error in postXML
     * @return new postXML
     */
    @RequestMapping(value = "/auth/post", method = RequestMethod.POST)
    @Transactional
    public
    @ResponseBody
    PostXML createPost(@RequestBody @Valid PostXML postXML, BindingResult bindingResult, HttpServletResponse response) {
        User user = userServices.getLoggedUser();
        if (user == null) {
            response.setStatus(401);
            return postXML;
        }
        if (!userServices.isAdmin()) {
            response.setStatus(403);
            return null;
        }
        if (bindingResult.hasErrors()) {
            postService.validPostXML(postXML, bindingResult);
            response.setStatus(400);
            return postXML;
        }
        postXML = postService.createPost(postXML);
        response.setStatus(201);
        response.setHeader("Location", "/api/v.1//post/" + postXML.getId());
        return postXML;
    }

    /**
     * @param response
     * @return userXML with All postXNL authenticated user
     */
    @RequestMapping(value = "/auth/post", method = RequestMethod.GET)
    @Transactional
    public
    @ResponseBody
    UserXML getPostAuthUser(HttpServletResponse response) {
        User user = userServices.getLoggedUser();
        if (user == null) {
            response.setStatus(401);
            return null;
        }
        user = userServices.getById(user.getId());
        UserXML userXML = createXMLService.getUserXMLWithPost(user);
        if (userXML != null) {
            response.setStatus(200);
            return userXML;
        }
        response.setStatus(404);
        return null;
    }


    /**
     * @param response 404  no one post in DB
     * @return userXML with all post in DB
     */
    @RequestMapping(value = "/post", method = RequestMethod.GET)
    @Transactional
    public
    @ResponseBody
    UserXML getAllPostUser(HttpServletResponse response) {
        UserXML userXML = postService.getALLPostXML();
        if (userXML == null) {
            response.setStatus(404);
            return null;
        }
        response.setStatus(200);
        return userXML;
    }


    /**
     * @param id       id of user in Db
     * @param response 404 no one posts are not found in DB for this user
     * @return userXML with all post in DB
     */
    @RequestMapping(value = "/{id}/post", method = RequestMethod.GET)
    @Transactional
    public
    @ResponseBody
    UserXML getPostUserById(@PathVariable int id, HttpServletResponse response) {
        User user = userServices.getById(id);
        if (user != null) {
            UserXML userXML = createXMLService.getUserXMLWithPost(user);
            if (userXML != null) {
                response.setStatus(200);
                return userXML;
            }

        }
        response.setStatus(404);
        return null;
    }



 /*   @RequestMapping(value = "/search", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"})
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public
    @ResponseBody
    String getResultSearch(@RequestParam Map<String, String> searchParams) {
        StringWriter xml;
        searchParams.remove("_");
        for (Map.Entry<String, String> p : searchParams.entrySet()) {
            System.err.println(p.getKey() + " " + p.getValue());
        }
        if (searchParams.size() < 2) {

            List list = userServices.getSearchUser(searchParams);
            if (list.size() == 0) {
                return null;
            } else {

                User user = (User) list.get(0);
                xml = createXMLService.getUserXML(user);
            }

            return xml.toString();
        }
        return null;

    }*/
}
