package ap.controller;

import ap.entity.MailForm;
import ap.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


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
    public String  sendMailToDeveloper(@Valid @ModelAttribute("form") MailForm mailForm, BindingResult bindingResult, Model model){
        model.addAttribute("form", new MailForm());
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "error");
            model.addAttribute("result", "форма заполнена с ошибками");
            return "mailto";
        }
        mailService.sendMailToDeveloper(mailForm.getFrom(),mailForm.getBody());
        model.addAttribute("result", "сообщение отправлено");
        return "mailto";
    }
}
