package ap.controller.RestControllers;

import ap.entity.EntityForXML.PostXML;
import ap.entity.EntityForXML.UserXML;
import ap.entity.User;
import ap.services.PostService;
import ap.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v.1/post")
public class PostControllerRest {
    @Autowired
    UserServices userServices;

    @Autowired
    PostService postService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/xml; charset=UTF-8"})
    @Transactional
    public
    @ResponseBody
    PostXML getPostById(@PathVariable int id, HttpServletResponse response) {
        PostXML postXML= postService.getPostXML(id);
        if(postXML!= null){
            response.setStatus(200);
            return postXML;
        }
        response.setStatus(404);
        return null;
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @Transactional
    public
    @ResponseBody
    PostXML getTryById(@RequestBody @Valid PostXML postXML, BindingResult bindingResult, @PathVariable int id, HttpServletResponse response) {
        User user = userServices.getLoggedUser();
        if (user==null){
            response.setStatus(404);
            return null;
        }
        if(!userServices.isAdmin()){
          response.setStatus(403);
          return  postXML;
      }
        if(bindingResult.hasErrors()) {
            postXML = postService.validPostXML(postXML, bindingResult);
            response.setStatus(400);
            return postXML;
        }
        postXML.setId(id);
        if(postService.changePost(postXML)){
            response.setStatus(200);
            return postXML;
        } else {
            response.setStatus(404);
            return postXML;
        }


    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @Transactional
    public
    @ResponseBody
    void  deletePostById(@PathVariable int id, HttpServletResponse response) {
        User user = userServices.getLoggedUser();
        if (user == null) {
            response.setStatus(401);
            return;
        }
        if (!userServices.isAdmin()) {
            response.setStatus(403);
            return;
        }

        if(postService.deletePost(id)){
            response.setStatus(200);
        } else response.setStatus(404);
    }


    @RequestMapping(value = "/from/{id}", method = RequestMethod.GET)
    @Transactional
    public
    @ResponseBody
    UserXML getPostFrom(@PathVariable int id, HttpServletResponse response) {
        int start = id;
        UserXML userXML = postService.getAllPost(start);
        if (userXML == null) {
            response.setStatus(404);
            return null;
        }
        response.setStatus(200);
        return userXML;
    }

    /**
     * @param response
     * @return int quantity of post in DB
     */
    @RequestMapping(value = "/quantity", method = RequestMethod.GET)
    @Transactional
    public
    @ResponseBody
    int getQuantityPostUser(HttpServletResponse response) {
        return postService.getQuantityRow();
    }

}