package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.ShopPropertiesInnerEmployees;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesInnerEmployees)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-24 12:42:25
 */
public interface ShopPropertiesInnerEmployeesDao extends BaseMapper<ShopPropertiesInnerEmployees> {

    /**
     * 商户物业查询内部员工
     * @param page
     * @param terms
     * @return
     */
    @Select("SELECT  \n" +
            "s.employee_id,\n" +
            "s.real_name,\n" +
            "s.sex,\n" +
            "s.age,\n" +
            "s.tel,\n" +
            "s.address,\n" +
            "s.organize_id,\n" +
            "s.role_id,\n" +
            "s.status,\n" +
            "s.remark,\n" +
            "o.organize_name,\n" +
            "r.name\n" +
            "from \n" +
            "shop_properties_inner_employees s,\n" +
            "organize o,\n" +
            "role r\n" +
            "where  \n" +
            "s.role_id = r.role_id\n" +
            "and s.organize_id = o.organize_id\n" +
            "and s.status = '1'\n" +
            "and o.status = '1'\n" +
            "and r.status = '1'\n" +
            "and CONCAT(\n" +
            "IFNULL(s.employee_id,''),\n" +
            "IFNULL(s.real_name, ''),\n" +
            "IFNULL(s.sex, ''),\n" +
            "IFNULL(s.age, ''),\n" +
            "IFNULL(s.tel, ''),\n" +
            "IFNULL(s.address, ''),\n" +
            "IFNULL(s.role_id, ''),\n" +
            "IFNULL(s.remark, ''),\n" +
            "IFNULL(r.name,''),\n" +
            "IFNULL(o.organize_name,'')\n" +
            ") LIKE #{terms}\n")
    IPage<Map<String,Object>> queryPage(Page<Map<String,Object>> page, @Param("terms")String terms);

}