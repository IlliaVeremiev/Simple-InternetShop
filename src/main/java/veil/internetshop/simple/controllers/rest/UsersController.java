package veil.internetshop.simple.controllers.rest;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import veil.internetshop.simple.data.UserRegistrationData;
import veil.internetshop.simple.models.User;
import veil.internetshop.simple.security.DefaultUserDetails;
import veil.internetshop.simple.services.UserService;
import veil.internetshop.simple.view.IdentificationView;
import veil.internetshop.simple.view.jsonviews.BaseUserView;

@RestController
@RequestMapping("/api/users")
public class UsersController{

    @Autowired
    private UserService userService;

    /**
     * Registers new user
     *
     * @param registrationInfo - user registration data
     * @return registrated user info
     */
    @PostMapping
    @JsonView(IdentificationView.class)
    public User registrate(UserRegistrationData registrationInfo){
        return userService.register(registrationInfo);
    }

    /**
     * Returns current user
     *
     * @return current user
     */
    @GetMapping("/me")
    @JsonView(BaseUserView.class)
    public User getCurrentUser(@AuthenticationPrincipal DefaultUserDetails userDetails){
        return userService.getUser(userDetails);
    }
}
