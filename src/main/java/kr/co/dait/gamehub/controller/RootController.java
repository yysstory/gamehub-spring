package kr.co.dait.gamehub.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController implements ErrorController{
    //spa 새로고침 대응
    @GetMapping("/error")
    public String redirectRoot() {
        return "forward:/index.html";
    }
}
