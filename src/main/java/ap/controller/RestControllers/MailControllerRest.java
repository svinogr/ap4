package ap.controller.RestControllers;

import ap.entity.MailForm;
import ap.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v.1/mailto")
public class MailControllerRest {
    @Autowired
    MailService mailService;

    @RequestMapping(method = RequestMethod.POST)
    public MailForm sendMailToDeveloper(@RequestBody @Valid MailForm mailForm, BindingResult bindingResult, HttpServletResponse response, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "error");
            response.setStatus(400);
            return new MailForm();
        }
        mailService.sendMailToDeveloper(mailForm.getFrom(),mailForm.getBody());
        response.setStatus(200);
        return mailForm;
    }
}
