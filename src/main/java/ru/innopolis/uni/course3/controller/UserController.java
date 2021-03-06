package ru.innopolis.uni.course3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.uni.course3.exception.WrongProcessingOfUserException;
import ru.innopolis.uni.course3.model.Role;
import ru.innopolis.uni.course3.model.User;
import ru.innopolis.uni.course3.service.PasswordAuthentication;
import ru.innopolis.uni.course3.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;

/**
 *
 */
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService service;
    private PasswordAuthentication authentication;
    private UserDetailsService userDetailsService;

    public UserController() {
    }

    public UserController(UserService service, PasswordAuthentication authentication, UserDetailsService userDetailsService) {
        this.service = service;
        this.authentication = authentication;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/users")
    public String showList(Model model){
        model.addAttribute("users", service.getAll());
        logger.info("User controller: have get all records");
        return "users";
    }

    @GetMapping("/users/delete/{userId}")
    public String deleteById(@PathVariable Integer userId, Model model) throws WrongProcessingOfUserException {
        service.delete(userId);
        logger.info("User controller: have deleted user with id {}", userId);
        return "redirect:/users";
    }

    @GetMapping("/users/update/{userId}")
    public String updateById(Model model, @PathVariable Integer userId) throws WrongProcessingOfUserException {
        User user = service.get(userId);
        model.addAttribute("user", user);
        logger.info("User controller: have updated user with id {}", userId);
        return "user";
    }

    @GetMapping("/users/create/new")
    public String add(Model model){
        User user = new User("", "", "", new Date(), true, 0, null);
        model.addAttribute("user", user);
        logger.info("User controller: have created a new user");
        return "user";
    }

    @GetMapping("/users/signup/new")
    public String signUp(Model model){
        User user = new User("", "", "", new Date(), true, 0, null);
        model.addAttribute("user", user);
        logger.info("User controller: a new user have signed up");
        return "user";
    }

    @GetMapping("/users/profile/show")
    public String showProfile(Model model) throws WrongProcessingOfUserException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return "redirect:/books";
        }
        String email = String.valueOf(auth.getPrincipal());
        User user = service.getByEmail(email);
        logger.info("User controller: user {} is watching his profile", user);
        model.addAttribute("user", user);
        logger.info("User controller: the profile of logged user is showed");
        return "user";
    }

    @GetMapping("/users/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        logger.info("User controller: logout");
        return "redirect:/books";
    }

    @PostMapping("users/*/save")
    public String processEditing(@RequestParam Integer id, @RequestParam Integer version, @RequestParam String name,
            @RequestParam String email, @RequestParam String password, @RequestParam String role) throws WrongProcessingOfUserException {
        User user = null;
        if ("ROLE_ADMIN".equals(role)) {
            user = new User(id, name, email, password, new Date(), true, version, Role.valueOf(role), Role.valueOf("ROLE_USER"));
        } else {
            user = new User(id, name, email, password, new Date(), true, version, Role.valueOf(role));
        }
        if(user.isNew() ){
            service.add(user);
        } else {
            service.update(user);
        }
        boolean hasRedirectedToUsers = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
            hasRedirectedToUsers = authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        logger.info("User controller: have saved user with id {}", id);
        return hasRedirectedToUsers ? "redirect:/users" : "redirect:/books";
    }

    @PostMapping("users/signin")
    public String processEditing(@RequestParam String email, @RequestParam String password) throws WrongProcessingOfUserException {
        if (validateLogin(email, password)){
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            Authentication auth = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
            logger.info("User controller: sing in user with email {}", email);
            return "redirect:/books";
        } else {
            logger.info("User controller: email {} is no validated", email);
            return "redirect:/login?error=true";
        }
    }

    @GetMapping("login")
    public String login(Model model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message)  {
        model.addAttribute("error", error);
        model.addAttribute("message", message);
        logger.info("User controller: login");
        return "login";
    }

    private boolean validateLogin(String email, String password) throws WrongProcessingOfUserException {

        // All parameters must be valid
        if (email == null || password == null){
            return false;
        }
        // Get a user by key
        User user = service.getByEmail(email);
        if (user == null){
            return false;
        }
        // Check if the password is valid
        if (!authentication.isExpectedPassword(password, user.getSalt(), user.getPassword())){
            return false;
        }
        return true;
    }

}
