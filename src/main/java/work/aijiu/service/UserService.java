package work.aijiu.service;

import org.apache.ibatis.annotations.Param;
import work.aijiu.common.utils.TablePage;
import work.aijiu.entity.Role;
import work.aijiu.entity.User;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2020-09-18 09:26:33
 */
public interface UserService {

    /**
     * 用户分页数据
     * @param offset
     * @param limit
     * @return
     */
    TablePage userPage(int offset, int limit);


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * 根基用户名查询用户信息
     * @param username
     * @return
     */
    User queryByName(String username);

    /**
     * 根据当前用户id获取角色
     * @param id
     * @return
     */
    List<Role> queryRoleById(Integer id);

}