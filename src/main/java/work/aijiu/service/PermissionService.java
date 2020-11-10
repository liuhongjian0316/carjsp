package work.aijiu.service;

import work.aijiu.entity.Permission;
import java.util.List;

/**
 * (Permission)表服务接口
 *
 * @author makejava
 * @since 2020-09-18 09:27:12
 */
public interface PermissionService {

    /**
     * 加载菜单
     * @param username
     * @return
     */
    List<Permission> showMenu(String username);

    /**
     * 加载权限
     * @param username
     * @return
     */
    List<Permission> loadPers(String username);

    /**
     * 查看全部菜单
     * @return
     */
    List<Permission> loadAllMenu();

    /**
     * 商户物业 加载商户菜单
     * @param roleId
     * @return
     */
    List<Permission> spMenu(String roleId);

}