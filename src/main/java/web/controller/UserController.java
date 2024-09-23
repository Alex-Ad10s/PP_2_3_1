package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import javax.validation.Valid;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String userList(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "/users";
    }

    @PostMapping("/search")
    public String searchUserId(@RequestParam int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "/showUserId";
    }

    @GetMapping("/create")
    public String newUser(@ModelAttribute("user") User user) {
        return "/create";
    }

    @PostMapping("/create")
    public String createNewUser(@ModelAttribute("user") @Valid User user,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "/create";
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String editUser(@RequestParam("id") int id, Model model) {
        model.addAttribute("user",userService.getUser(id));
        return "/edit";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult, @RequestParam("id") int id) {
        if (bindingResult.hasErrors())
            return "/edit";
        userService.updateUser(id, user);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }

}
