package ap.controller;

import ap.entity.MailForm;
import ap.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MailController {

    @Autowired
    MailService mailService;

    @RequestMapping(value = "/mailto", method = RequestMethod.GET)
    public ModelAndView mailToDeveloper(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("form", new MailForm());
        modelAndView.setViewName("mailto");
        return modelAndView;
    }
    @RequestMapping(value = "/mailto", method = RequestMethod.POST)
    public ModelAndView sendMailToDeveloper(@ModelAttribute("form") MailForm mailForm){

        mailService.sendMailToDeveloper(mailForm.getFrom(),mailForm.getBody());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("form", new MailForm());
        modelAndView.addObject("result", "сообщение отправлено");
        modelAndView.setViewName("mailto");
        return modelAndView;
    }
}
