package ap.controller;

import ap.dao.ExersiceDAO;
import ap.dao.PostDAO;
import ap.dao.TryDAO;
import ap.dao.WorkoutDAO;
import ap.entity.*;
import ap.services.CreateXMLService;
import ap.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TestController {
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    CreateXMLService createXMLService;

    @Autowired
    UserServices userServices;

    @Autowired
    WorkoutDAO workoutDAO;

    @Autowired
    ExersiceDAO exersiceDAO;

    @Autowired
    TryDAO tryDAO;
    @Autowired
    PostDAO postDAO;

    @RequestMapping("/test")
    public ModelAndView getTestPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("test");
        System.err.println("вызов тест контроллер");
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println(user.getId());

        } catch (ClassCastException e) {
        }

        return modelAndView;
    }

    @RequestMapping("/post")
    @Transactional
    public void testPost() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Post post = new Post();
        Images images = new Images();
        images.setImage("картинка");
        images.setParentId(post);
        //post.getImage().add(images);
        post.setParentId(user);
        postDAO.add(post);


    }

    @RequestMapping(value = "XML")
    public ModelAndView getXMLPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("xmltest");
        return modelAndView;
    }

    @RequestMapping(value = "/confidential/modal")
    public String modal() {
        return "modal";
    }

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public String image() {
        return "image";
    }

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public String imageSave(@RequestParam(value = "file", required = false) MultipartFile file) {
        System.err.println(file.getSize());

        return "image";
    }


    @RequestMapping(value = "/aut")
    public void aut() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getId());
    }

    private int getLoginUserId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int idLoginUser = user.getId();
        return idLoginUser;
    }

    private int getPosition(List list) {
        return list.size();
    }
}
