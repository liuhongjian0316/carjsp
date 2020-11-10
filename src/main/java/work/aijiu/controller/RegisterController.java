package work.aijiu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.phoneVerify.service.SMSService;
import work.aijiu.entity.User;
import work.aijiu.service.UserService;

@RestController
public class RegisterController  {

    @Autowired
    private SMSService smsService;

    @Autowired
    private UserService userService;

//    /**
//     * 注册用户
//     * @param users
//     * @return
//     */
//    @PostMapping("register")
//    public CarJSONResult register(@RequestBody User users){
//        int result = userService.insert(users);
//        if(result > 0){
//            return CarJSONResult.ok();
//        }
//        return CarJSONResult.errorMsg("注册失败");
//    }
//
//
//    /**
//     * 手机号检测
//     * @param phone
//     * @return 不存在 -- ok()     存在 -- errorMsg("该手机号已被注册")
//     */
//    @GetMapping("phoneCheck")
//    public CarJSONResult phoneCheck(@RequestParam("phone") String phone){
//        int result = userService.checkphone(phone);
//        if(result == 0){
//            return CarJSONResult.ok();
//        }else{
//            return CarJSONResult.errorMsg("该手机号已被注册");
//        }
//
//    }
//
//
//    /**
//     * 用户检测
//     * @param username 用户名称
//     * @return 不存在 -- ok()     存在 -- errorMsg("该用户已存在")
//     */
//    @GetMapping("usernameCheck")
//    public CarJSONResult usernameCheck(@RequestParam("username") String username){
//        int result = userService.checkrealname(username);
//        if(result == 0){
//            return CarJSONResult.ok();
//        }else{
//            return CarJSONResult.errorMsg("该用户已存在");
//        }
//    }
//
//
//    /**
//     *  获取验证码(点击按钮) -- 注册
//     * @param phone 手机号
//     * @return
//     */
//    @GetMapping("getCode")
//    public CarJSONResult getCode(@RequestParam("phone") String phone){
//        //获取的手机号写入redis
//        //设置失效时间
//        String s = smsService.sendMesModel(phone, 0);
//        System.out.println(s);
//        if(s.equals("OK")){
//            return CarJSONResult.ok();
//        }else{
//            return CarJSONResult.errorMsg("获取验证码失败");
//        }
//    }
//
//    /**
//     *  获取验证码(五分钟输入正确验证码即可)
//     * @param phone 手机号
//     * @return
//     */
//    @GetMapping("getCodeReflush")
//    public CarJSONResult getCodeReflush(@RequestParam("phone") String phone){
//        //从redis中获取验证码
////        if(redisOperator.hasKey(Constant.USER_PHONE_CODE+phone)){
////            return CarJSONResult.ok(redisOperator.get(Constant.USER_PHONE_CODE+phone));
////        }else{
////            return CarJSONResult.errorMsg("验证码失效");
////        }
//        return null;
//    }
}
