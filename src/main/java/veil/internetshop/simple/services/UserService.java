package veil.internetshop.simple.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import veil.internetshop.simple.data.UserRegistrationData;
import veil.internetshop.simple.models.User;
import veil.internetshop.simple.security.DefaultUserDetails;

public interface UserService extends UserDetailsService{

	DefaultUserDetails getCurrentUserDetails();

	User getCurrentUser();

	User getUser(DefaultUserDetails userDetails);

	User register(UserRegistrationData registrationInfo);

	void save(User user);
}
