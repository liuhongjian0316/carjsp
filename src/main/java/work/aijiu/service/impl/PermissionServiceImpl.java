package work.aijiu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.MenuUtils;
import work.aijiu.entity.Permission;
import work.aijiu.dao.PermissionDao;
import work.aijiu.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Permission)表服务实现类
 *
 * @author makejava
 * @since 2020-09-18 09:27:12
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;


    @Override
    public List<Permission> showMenu(String username) {
        return MenuUtils.buildTree(permissionDao.queryListByName(username));
    }

    @Override
    public List<Permission> loadPers(String username) {
        return permissionDao.queryPermissions(username);
    }

    @Override
    public List<Permission> loadAllMenu() {
        return MenuUtils.buildTree2(permissionDao.queryAllPermissions());
    }

    @Override
    public List<Permission> spMenu(String roleId) {
        return MenuUtils.buildTree(permissionDao.spMenu(roleId));
    }
}