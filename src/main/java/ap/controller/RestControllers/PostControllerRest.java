package ap.controller.RestControllers;

import ap.entity.EntityForXML.PostXML;
import ap.entity.EntityForXML.TryXML;
import ap.entity.Post;
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


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/xml; charset=UTF-8"})
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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/xml; charset=UTF-8"})
    @Transactional
    public
    @ResponseBody
    void  deletePostById(@PathVariable int id, HttpServletResponse response) {
        User user = userServices.getLoggedUser();
        if(user!= null){
            response.setStatus(401);
            return;
                    }
        if(!userServices.isAdmin()){
            response.setStatus(403);
            return;
        }
        if(postService.deletePost(id)){
            response.setStatus(200);
        } else response.setStatus(404);
    }



}