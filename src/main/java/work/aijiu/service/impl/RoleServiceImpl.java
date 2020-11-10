package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.TablePage;
import work.aijiu.entity.Role;
import work.aijiu.dao.RoleDao;
import work.aijiu.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Role)表服务实现类
 *
 * @author makejava
 * @since 2020-09-18 09:26:58
 */
@Service("roleService")
public class RoleServiceImpl  extends ServiceImpl<RoleDao, Role> implements RoleService {


    @Autowired
    private RoleDao roleDao;

    /**
     * 角色分页
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public TablePage RolePape( Integer offset, Integer limit) {
        offset = (offset -1) * offset;
        List<Role> roleList = roleDao.queryAllByLimit(offset, limit);
        return new TablePage(roleDao.queryAll(new Role()).size(),offset,limit,roleList);
    }

    /**
     * 角色分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public IPage<Role> queryRolePage(Integer pageNum, Integer pageSize) {
        return roleDao.queryRolePage(new Page<>(pageNum, pageSize));
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Integer id) {
        return this.roleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Role> queryAllByLimit(int offset, int limit) {
        return this.roleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role insert(Role role) {
        this.roleDao.insert(role);
        return role;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role role) {
        this.roleDao.update(role);
        return this.queryById(role.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.roleDao.deleteById(id) > 0;
    }
}