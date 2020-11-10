package work.aijiu.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import work.aijiu.entity.ShopPropertiesInnerEmployees;

import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesInnerEmployees)表服务接口
 *
 * @author makejava
 * @since 2020-10-24 12:42:25
 */
public interface ShopPropertiesInnerEmployeesService{

    /**
     * 商户物业 内部员工分页查询
     * @param pageNum
     * @param pageSize
     * @param terms
     * @return
     */
    public IPage<Map<String, Object>> queryPage(Integer pageNum, Integer pageSize, String terms);

    /**
     * 商户物业增加员工手机号查重
     * @param tel
     * @return
     */
    Boolean checkAddTel(String tel);

    /**
     * 商户物业编辑员工查重
     * @param tel
     * @param id
     * @return
     */
    Boolean checkEditTel(String tel, String id);

    /**
     * 商户客服添加员工
     * @param employees
     * @return
     */
    Boolean addEmployee(ShopPropertiesInnerEmployees employees);


    /**
     * 根据id 删除员工
     * @param id
     * @return
     */
    Boolean deleteEmployeeById(String id);

    /**
     * 商户物业 根据id 获取员工信息
     * @param id
     * @return
     */
    ShopPropertiesInnerEmployees getById(String id);

    /**
     * 商户物业 保存员工信息
     * @param employees
     * @return
     */
    Boolean saveEmployee(ShopPropertiesInnerEmployees employees);

    /**
     * 商户物业 重置密码
     * @param id
     * @param param
     * @return
     */
    Boolean resetPwd(String id, String param);

    /**
     * 内部员工登录
     * @param username
     * @param pwd
     * @return
     */
    List<ShopPropertiesInnerEmployees> login(String username, String pwd);
}