package app.user.service;

import app.user.model.Country;
import app.web.dto.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserInit implements CommandLineRunner {

    private final UserService userService;

    @Autowired
    public UserInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        if(!userService.getAllUsers().isEmpty()){
            return;
        }

        RegisterRequest registerRequest = RegisterRequest.builder()
                .username("TestUser")
                .password("123123")
                .country(Country.BULGARIA)
                .build();

        userService.register(registerRequest);
    }
}
