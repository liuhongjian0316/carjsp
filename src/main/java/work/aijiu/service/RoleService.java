package work.aijiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import work.aijiu.common.utils.TablePage;
import work.aijiu.entity.Role;
import java.util.List;

/**
 * (Role)表服务接口
 *
 * @author makejava
 * @since 2020-09-18 09:26:58
 */
public interface RoleService  extends IService<Role> {


    /**
     * 角色分页
     * @param offset
     * @param limit
     * @return
     */
    TablePage RolePape(Integer offset,Integer limit);


    /**
     * 角色分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<Role> queryRolePage(Integer pageNum, Integer pageSize);




    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Role queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Role> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role insert(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}