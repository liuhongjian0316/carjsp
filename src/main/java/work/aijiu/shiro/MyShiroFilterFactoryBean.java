package work.aijiu.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.UrlPattern;
import work.aijiu.dao.PermissionDao;
import work.aijiu.entity.Permission;

import java.util.*;

public class MyShiroFilterFactoryBean extends ShiroFilterFactoryBean {

    @Autowired
    private PermissionDao permissionDao;

    /**
     * 可以配置加载数据库的备注，省去了配置 xml，初始化的时候会加载
     * @param filterChainDefinitionMap
     */
    @Override
    public void setFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap) {
        Map<String,String> filterMap=new HashMap<>();

        //从数据库中查询权限
        List<Permission> permissions = permissionDao.queryUrlPermissions();
        //过滤出合法的url和perm
        permissions.forEach(permission ->{
            if(UrlPattern.UrlFilterUtil(permission.url)){
                filterMap.put(permission.url,"perms["+'"'+permission.perms+'"'+"]");
            }
        });
        filterMap.put("/**","authc");
//        /**
//         * /menuBtnlist = authc
//         *                 /menuBtnlist = perms["menu:showmenu"]
//         *                 /menuBtnlist = roles["admin"]
//         *                 /** = authc
//         */
//        //再细致的拼装可以自己改
//        filterMap.put("/permisson/menuBtnlist","perms[menu:showmenu]");
//        filterMap.put("/**","authc");
        super.setFilterChainDefinitionMap(filterMap);
    }
}
