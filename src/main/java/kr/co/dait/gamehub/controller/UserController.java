package kr.co.dait.gamehub.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.dait.gamehub.dto.UserDto;
import kr.co.dait.gamehub.entity.Game;
import kr.co.dait.gamehub.entity.User;
import kr.co.dait.gamehub.service.GameService;
import kr.co.dait.gamehub.service.UserService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final GameService gameService;


    @GetMapping(value = "/test")
    public List<UserDto> getUserTest(Model model, @AuthenticationPrincipal User user    ){
        List<UserDto> userList= userService.findAll();
        System.out.println(user.toString());
        return userList;
    }

    @GetMapping(value = "/list")
    public List<UserDto> getUserList(Model model){
        List<UserDto> userList= userService.findAll();
        return userList;
    }

    @PostMapping(value = "/add")
    public User addUser(Model model){
        User user = User.builder().userEmail("yysstory1@gmail.com").userName("윤용식").userPassword(passwordEncoder.encode("alwjr425")).build();
        return userService.addUser(user);
    }
    @GetMapping(value = "/add2")
    public User addUser2(Model model){
        User user = User.builder().userEmail("yysstory2@gmail.com").userName("윤용식").userPassword(passwordEncoder.encode("alwjr425")).build();
        return userService.addUser(user);
    }

    @GetMapping(value = "/find")
    public User findUser(Model model, String userEmail){
        User user = userService.findByUserEmail(userEmail);
        return user;
    }


}
