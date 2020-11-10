package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.ShopPropertiesInnerEmployees;
import work.aijiu.dao.ShopPropertiesInnerEmployeesDao;
import work.aijiu.service.ShopPropertiesInnerEmployeesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesInnerEmployees)表服务实现类
 *
 * @author makejava
 * @since 2020-10-24 12:42:26
 */
@Service("shopPropertiesInnerEmployeesService")
public class ShopPropertiesInnerEmployeesServiceImpl implements ShopPropertiesInnerEmployeesService {

    @Autowired
    private ShopPropertiesInnerEmployeesDao shopPropertiesInnerEmployeesDao;

    @Override
    public IPage<Map<String, Object>> queryPage(Integer pageNum, Integer pageSize, String terms) {
        return shopPropertiesInnerEmployeesDao.queryPage(new Page<>(pageNum, pageSize),"%"+terms+"%");
    }

    @Override
    public Boolean checkAddTel(String tel) {
        QueryWrapper<ShopPropertiesInnerEmployees> wrapper = new QueryWrapper<>();
        wrapper.eq("tel",tel).eq("status","1");
        return shopPropertiesInnerEmployeesDao.selectList(wrapper).size()<=0;
    }

    @Override
    public Boolean checkEditTel(String tel, String id) {
        QueryWrapper<ShopPropertiesInnerEmployees> wrapper = new QueryWrapper<>();
        wrapper.eq("tel",tel).eq("status","1").ne("employee_id",id);
        return shopPropertiesInnerEmployeesDao.selectList(wrapper).size()<=0;
    }

    @Override
    public Boolean addEmployee(ShopPropertiesInnerEmployees employees) {
        return shopPropertiesInnerEmployeesDao.insert(employees)>0;
    }

    @Override
    public Boolean deleteEmployeeById(String id) {
        UpdateWrapper<ShopPropertiesInnerEmployees> wrapper = new UpdateWrapper<>();
        wrapper.eq("employee_id",id).set("status","0");
        return shopPropertiesInnerEmployeesDao.update(null,wrapper)>0;
    }

    @Override
    public ShopPropertiesInnerEmployees getById(String id) {
        QueryWrapper<ShopPropertiesInnerEmployees> wrapper = new QueryWrapper<>();
        wrapper.eq("employee_id",id).eq("status","1");
        return shopPropertiesInnerEmployeesDao.selectOne(wrapper);
    }

    @Override
    public Boolean saveEmployee(ShopPropertiesInnerEmployees employees) {
        UpdateWrapper<ShopPropertiesInnerEmployees> wrapper = new UpdateWrapper<>();
        wrapper.eq("employee_id",employees.getEmployeeId())
                .set("real_name",employees.getRealName())
                .set("sex",employees.getSex())
                .set("age",employees.getAge())
                .set("tel",employees.getTel())
                .set("address",employees.getAddress())
                .set("organize_id",employees.getOrganizeId())
                .set("role_id",employees.getRoleId())
                .set("remark",employees.getRemark());
        return shopPropertiesInnerEmployeesDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean resetPwd(String id, String param) {
        UpdateWrapper<ShopPropertiesInnerEmployees> wrapper = new UpdateWrapper<>();
        wrapper.eq("employee_id",id).set("pwd",param);
        return shopPropertiesInnerEmployeesDao.update(null,wrapper)>0;
    }

    @Override
    public List<ShopPropertiesInnerEmployees> login(String username, String pwd) {
        QueryWrapper<ShopPropertiesInnerEmployees> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",username).eq("pwd",pwd)
                .eq("status","1");
        return shopPropertiesInnerEmployeesDao.selectList(wrapper);
    }
}