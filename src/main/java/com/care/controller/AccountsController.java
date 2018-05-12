package com.care.controller;

import com.care.component.OnPasswordResetRequestEvent;
import com.care.model.users.User;
import com.care.service.securityService.SecurityService;
import com.care.service.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/accounts")
@SessionAttributes("user")
public class AccountsController {

    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.GET)
    public ModelAndView requestPasswordReset() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forget-password");
        return modelAndView;
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public ModelAndView resetPassword(@RequestParam("email") String userEmail, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            modelAndView.addObject("message",
                    "That e-mail address or username doesn't have an associated user account.");
            modelAndView.setViewName("forget-password");
            return modelAndView;
        }
        else {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnPasswordResetRequestEvent(user, appUrl));

            modelAndView.addObject("message",
                    "Password recovery e-mail sent successfully.");
            modelAndView.setViewName("forget-password");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public ModelAndView changePasswordPage(@RequestParam("id") Integer id, @RequestParam("token") String token) {
        ModelAndView modelAndView = new ModelAndView();
        String result = securityService.validatePasswordResetToken(id, token);
        if (!result.equals("Valid token")) {
            modelAndView.addObject("message", result);
            modelAndView.setViewName("login");
            return modelAndView;
        }
        return new ModelAndView("redirect:/accounts/updatePassword");
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
    public ModelAndView uPasswordPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update-password");
        return modelAndView;
    }
}
