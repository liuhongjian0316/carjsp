package work.aijiu.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import work.aijiu.entity.Permission;
import work.aijiu.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Map;

/**
 * (Permission)表控制层
 *
 * @author makejava
 * @since 2020-09-18 09:27:12
 */
@RestController
@RequestMapping ( "/permisson" )
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 显示菜单
     * @return
     */
    @RequestMapping(value="showMenu", method=RequestMethod.GET)
    public List<Permission> permissionList(@RequestParam("username") String username){
        return permissionService.showMenu(username);
    }

    /**
     * 权限列表
     * @param username
     * @return
     */
    @RequestMapping(value="perslist", method=RequestMethod.GET)
    public List<Permission> permissionLists(@RequestParam("username") String username){
        return permissionService.loadPers(username);
    }

    /**
     * 加载全部菜单按钮
     * @return
     */
    @RequestMapping(value="menuBtnlist", method=RequestMethod.GET)
    public List<Permission> permissionLists(){
        return permissionService.loadAllMenu();
    }


    /**
     * 商户物业 加载商户物业admin菜单
     * @param roleId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="spMenu/{roleId}", method=RequestMethod.GET)
    public List<Permission> spMenu(@PathVariable("roleId")String roleId){
        return permissionService.spMenu(roleId);
    }

}