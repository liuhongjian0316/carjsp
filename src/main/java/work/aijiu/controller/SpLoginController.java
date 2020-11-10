package work.aijiu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.entity.*;
import work.aijiu.service.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 商户物业各种用户的登录
 */
@RestController
public class SpLoginController {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private ShopPropertiesShopService shopPropertiesShopService;
    @Autowired
    private ShopPropertiesCustomerServiceService shopPropertiesCustomerServiceService;
    @Autowired
    private ShopPropertiesRepairWorkerService shopPropertiesRepairWorkerService;
    @Autowired
    private ShopPropertiesInnerEmployeesService shopPropertiesInnerEmployeesService;

    /**
     * 商户物业 商户物业登录
     * @param response
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/login", method = RequestMethod.POST)
    public JSONResult spLogin(HttpServletResponse response, @RequestBody Map<String, String> map){
        String tel = map.get("tel");
        String pwd = map.get("pwd");
        List<Company> login = companyService.login(tel, pwd);
        if(login.size()>0){
            return JSONResult.ok(login.get(0));
        }
        return JSONResult.errorMsg("账号或密码错误");
    }

    /**
     * 商户物业 商户登录
     * @param response
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/login", method = RequestMethod.POST)
    public JSONResult shopLogin(HttpServletResponse response, @RequestBody Map<String, String> map){
        String username = map.get("username");
        String pwd = map.get("pwd");
        List<ShopPropertiesShop> login = shopPropertiesShopService.login(username, pwd);
        if(login.size()>0){
            return JSONResult.ok(login.get(0));
        }
        return JSONResult.errorMsg("账号或密码错误");
    }

    /**
     * 商户物业 客服登录
     * @param response
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/service/login", method = RequestMethod.POST)
    public JSONResult serviceLogin(HttpServletResponse response, @RequestBody Map<String, String> map){
        String username = map.get("username");
        String pwd = map.get("pwd");
        System.out.println(pwd);
        List<ShopPropertiesCustomerService> login = shopPropertiesCustomerServiceService.login(username, pwd);
        if(login.size()>0){
            return JSONResult.ok(login.get(0));
        }
        return JSONResult.errorMsg("账号或密码错误");
    }

    /**
     * 商户物业 维修员登录
     * @param response
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/login", method = RequestMethod.POST)
    public JSONResult repairWorkerLogin(HttpServletResponse response, @RequestBody Map<String, String> map){
        String username = map.get("username");
        String pwd = map.get("pwd");
        System.out.println(pwd);
        List<ShopPropertiesRepairWorker> login = shopPropertiesRepairWorkerService.login(username, pwd);
        if(login.size()>0){
            return JSONResult.ok(login.get(0));
        }
        return JSONResult.errorMsg("账号或密码错误");
    }
    /**
     * 商户物业 内部员工登录
     * @param response
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/manager/login", method = RequestMethod.POST)
    public JSONResult employeeLogin(HttpServletResponse response, @RequestBody Map<String, String> map){
        String username = map.get("username");
        String pwd = map.get("pwd");
        System.out.println(pwd);
        List<ShopPropertiesInnerEmployees> login = shopPropertiesInnerEmployeesService.login(username, pwd);
        if(login.size()>0){
            return JSONResult.ok(login.get(0));
        }
        return JSONResult.errorMsg("账号或密码错误");
    }



}
