package work.aijiu.dao;

import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.Permission;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Permission)表数据库访问层
 *
 * @author makejava
 * @since 2020-09-18 09:27:12
 */
public interface PermissionDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Permission queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Permission> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param permission 实例对象
     * @return 对象列表
     */
    List<Permission> queryAll(Permission permission);

    /**
     * 新增数据
     *
     * @param permission 实例对象
     * @return 影响行数
     */
    int insert(Permission permission);

    /**
     * 修改数据
     *
     * @param permission 实例对象
     * @return 影响行数
     */
    int update(Permission permission);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


    /**
     * 通过姓名查询菜单列表
     * @param username
     * @return
     */
    List<Permission> queryListByName(@Param("username") String username);


    /**
     * 通过姓名查询权限字段
     * @param username
     * @return
     */
    List<Permission> queryPermissions(@Param("username") String username);

    /**
     * 查询所有权限菜单
     * @return
     */
    List<Permission> queryAllPermissions();


    /**
     * 查询所有url权限字段
     * @return
     */
    List<Permission> queryUrlPermissions();


    /**
     * 商户物业 加载商户物业菜单
     * @param roleId
     * @return
     */
    @Select("SELECT * FROM permission p , role_permission rp\n" +
            "where rp.role_id = #{roleId} and rp.permission_id = p.id")
    List<Permission> spMenu(String roleId);

}