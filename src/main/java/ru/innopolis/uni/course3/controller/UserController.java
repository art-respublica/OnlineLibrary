package ru.innopolis.uni.course3.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.uni.course3.model.Role;
import ru.innopolis.uni.course3.model.User;
import ru.innopolis.uni.course3.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 *
 */
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public String showList(Model model){
        logger.info("User controller: get all records");
        model.addAttribute("users", service.getAll());
        return "users";
    }

    @GetMapping("/users/delete/{userId}")
    public String deleteById(@PathVariable Integer userId){
        logger.info("User controller: delete user with id {}", userId);
        service.delete(userId);
        return "redirect:/users";
    }

    @GetMapping("/users/update/{userId}")
    public String updateById(Model model, @PathVariable Integer userId){
        User user = service.get(userId);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/users/create/new")
    public String add(Model model){
        User user = new User("", "", "", new Date(), true, "");
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/users/signup/new")
    public String signUp(Model model){
        User user = new User("", "", "", new Date(), true, "");
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/users/profile/{userId}")
    public String showProfile(Model model, @PathVariable Integer userId){
        User user = service.get(userId);
        logger.info("User controller: user {} is watching his profile", user);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/users/logout")
    public String logout(HttpSession session){
        logger.info("User controller: user {} logout", session.getAttribute("user"));
        session.setAttribute("user", null);
        session.setAttribute("isLibrarian", false);
        return "redirect:/books";
    }

    @PostMapping("users/*/save")
    public String processEditing(@RequestParam Integer id, @RequestParam String name,
                                 @RequestParam String email, @RequestParam String password,
                                 @RequestParam String registered, @RequestParam String enabled,
                                 @RequestParam String role, HttpSession session) {

        Boolean enabledValue = "true".equals(enabled) ? true : false;
        User user = new User(id, name, email, password, new Date(), enabledValue, role);
        logger.info("User controller:  " + (user.isNew() ? "create of/sign up " : "update of ") +  user);
        if(user.isNew() ){
            service.add(user);
        } else {
            service.update(user);
        }

        if((boolean) session.getAttribute("isLibrarian")) {
            return "redirect:/users";
        } else {
            return "redirect:/books";
        }
    }

    @PostMapping("users/signin")
    public String processEditing(@RequestParam String email, @RequestParam String password,
                                 HttpSession session) {
        User user = validateLogin(email, password);
        if (user == null){
            return "login-error";
        } else {
            logger.info("User controller: sing in user with email {}", email);
            session.setAttribute("user", user);
            session.setAttribute("isLibrarian", user.getRole().equals(Role.ROLE_LIBRARIAN.toString()));
            return "redirect:/books";
        }
    }

    private User validateLogin(String email, String password) {

        // All parameters must be valid
        if (email == null || password == null){
            return null;
        }
        // Get a user by key
        User user = service.getByEmail(email);
        if (user == null){
            return null;
        }
        // Check if the password is valid
        if (!user.getPassword().equals(password.trim())){
            return null;
        }
        return user;
    }
}
