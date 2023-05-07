package kr.co.dait.gamehub.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kr.co.dait.gamehub.constant.ResponseCode;
import kr.co.dait.gamehub.dto.UserDto;
import kr.co.dait.gamehub.service.UserService;
import kr.co.dait.gamehub.util.ResponseData;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/web")
@RestController
@RequiredArgsConstructor
public class UserController {

    //private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    //회원가입
    @PostMapping(value = "/join")
    public ResponseData addUser(@Valid UserDto userDto) throws Exception{
        userDto = userService.addUser(userDto);
        return new ResponseData(ResponseCode.OK,null);
    }
    
}
