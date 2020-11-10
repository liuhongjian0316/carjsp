package work.aijiu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnAuthController {

    @RequestMapping(value = "/unAuth")
    public String unAuth(){
        return "你没有权限";
    }

}
