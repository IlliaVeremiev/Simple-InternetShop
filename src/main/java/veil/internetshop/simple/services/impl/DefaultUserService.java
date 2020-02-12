package veil.internetshop.simple.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import veil.internetshop.simple.data.UserRegistrationData;
import veil.internetshop.simple.exceptions.service.ResourceConflictException;
import veil.internetshop.simple.exceptions.service.UnauthorizedException;
import veil.internetshop.simple.models.Cart;
import veil.internetshop.simple.models.User;
import veil.internetshop.simple.repositories.UserRepository;
import veil.internetshop.simple.security.DefaultUserDetails;
import veil.internetshop.simple.services.CartService;
import veil.internetshop.simple.services.UserService;

public class DefaultUserService implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CartService cartService;

    @Override
    public DefaultUserDetails getCurrentUserDetails(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof DefaultUserDetails){
            return (DefaultUserDetails)principal;
        }
        return null;
    }

    @Override
    public User getCurrentUser(){
        DefaultUserDetails userDetails = getCurrentUserDetails();
        if(userDetails == null){
            return null;
        }
        return userDetails.getUser();
    }

    @Override
    public User getUser(DefaultUserDetails userDetails){
        User user;
        if(userDetails == null || (user = userDetails.getUser()) == null){
            throw new UnauthorizedException("exception.user.not_found");
        }
        return user;
    }

    @Override
    public User register(UserRegistrationData registrationInfo){
        User user = new User();
        user.setEmail(registrationInfo.getUsername());
        user.setPassword(passwordEncoder.encode(registrationInfo.getPassword()));
        user.setEnabled(true);
        user.setLocked(false);
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new ResourceConflictException("exception.user.registration.username_exists");
        }
        createCart(user);
        return userRepository.save(user);
    }

    private void createCart(User user){
        Cart cart = cartService.createCart();
        cart.setUser(user);
        user.setCart(cart);
    }

    @Override
    public void save(User user){
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        if(username == null){
            throw new UsernameNotFoundException("exception.user.not_found");
        }
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return new DefaultUserDetails(user);
    }
}
