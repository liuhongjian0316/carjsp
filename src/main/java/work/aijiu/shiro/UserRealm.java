package work.aijiu.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.Permission;
import work.aijiu.entity.Role;
import work.aijiu.entity.User;
import work.aijiu.service.PermissionService;
import work.aijiu.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 自定义Realm
 */

public class UserRealm  extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;


    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //给资源进行授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        //查询当前用户的资源
        List<Permission> permissions = permissionService.loadPers(user.getUsername());
        permissions.forEach(permission -> info.addStringPermission(permission.perms));
        //查询当前用户的角色
        List<Role> rolelist = userService.queryRoleById(user.getId());
        Set<String> roles = new HashSet<>();
        rolelist.forEach(role->roles.add(role.getPerm().toString()));
        info.setRoles(roles);
        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //查修用户名
        User user = userService.queryByName(token.getUsername());
        if(user == null){
            //底层抛出UnKnowAccountException
            return null;
        }
       return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
