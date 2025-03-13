package app.web;

import app.user.model.User;
import app.user.service.UserService;
import app.web.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
public class IndexController {

    private final UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
        public String getIndexPage(){

        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(){

        return "login";
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage(){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("registerRequest",new RegisterRequest());

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerNewUser(@Valid RegisterRequest registerRequest , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ModelAndView("register");
        }

        User registeredUser = userService.register(registerRequest);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("user",registeredUser);

        return modelAndView;
    }

    @GetMapping("/home")
    public ModelAndView getHomePage(){

        User user = userService.getById(UUID.fromString("fc815143-d8a2-41d2-b1d3-8fff90dede74"));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        modelAndView.addObject("user", user);

        return modelAndView;
    }

}
