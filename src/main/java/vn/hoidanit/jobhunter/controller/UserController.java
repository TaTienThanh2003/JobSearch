package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/create")
    public String CreateUser() {
        User user = new User();
        user.setEmail("thanhkhongkhoc@gmail.com");
        user.setName("Ta Tien Thanh");
        user.setPassword("20032003");
        this.userService.handleCreateUser(user);
        return "create user";
    }
}
