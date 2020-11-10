package work.aijiu.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.JwtTokenUtils;
import work.aijiu.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;



    @PostMapping("/login")
    public JSONResult login(HttpServletResponse response, @RequestBody Map<String, String> map){
        String userAccount = map.get("userAccount");
        String pwd = map.get("pwd");
        //添加认证信息 shiro
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userAccount,pwd);
        try {
            subject.login(usernamePasswordToken);
            //将token存入过期时间
            response.setHeader("Authorization",JwtTokenUtils.TOKEN_PREFIX +JwtTokenUtils.createToken(userAccount, null, false));
            return JSONResult.ok();
        }catch (UnknownAccountException e){
            return JSONResult.errorException("用户名不存在");
        }catch (IncorrectCredentialsException e){
            return JSONResult.errorException("密码错误");
        }
//        if("admin".equals(userAccount) && "admin".equals(pwd)){
//            User user = new User();
//            user.setUseraccount("admin");
//            user.setPassword("admin");
//            return "success";
//        }
//        return "error";
    }
}
