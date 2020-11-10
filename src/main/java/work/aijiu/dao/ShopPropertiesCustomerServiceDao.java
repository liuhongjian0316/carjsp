package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.ShopPropertiesCustomerService;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesCustomerService)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-22 13:31:34
 */
public interface ShopPropertiesCustomerServiceDao extends BaseMapper<ShopPropertiesCustomerService> {

    /**
     * 商户物业客服信息分页
     * @param page
     * @return
     */
    @Select("SELECT  \n" +
            "s.customer_service_id,\n" +
            "s.customer_service_name,\n" +
            "s.username,\n" +
            "s.pwd,\n" +
            "s.organize_id,\n" +
            "s.real_name,\n" +
            "s.sex,\n" +
            "s.tel,\n" +
            "s.address,\n" +
            "s.role_id,\n" +
            "s.create_time,\n" +
            "s.state,\n" +
            "s.status,\n" +
            "s.remark,\n" +
            "s.last_login_time,\n" +
            "o.organize_name,\n" +
            "r.name\n" +
            "from \n" +
            "shop_properties_customer_service s,\n" +
            "organize o,\n" +
            "role r\n" +
            "where  \n" +
            "s.role_id = r.role_id\n" +
            "and s.organize_id = o.organize_id\n" +
            "and s.status = '1'\n" +
            "and o.status = '1'\n" +
            "and r.status = '1'\n" +
            "and CONCAT(\n" +
            "IFNULL(s.customer_service_id,''),\n" +
            "IFNULL(s.customer_service_name, ''),\n" +
            "IFNULL(s.username, ''),\n" +
            "IFNULL(s.organize_id, ''),\n" +
            "IFNULL(s.real_name, ''),\n" +
            "IFNULL(s.tel, ''),\n" +
            "IFNULL(s.sex, ''),\n" +
            "IFNULL(s.address, ''),\n" +
            "IFNULL(s.role_id, ''),\n" +
            "IFNULL(s.create_time, ''),\n" +
            "IFNULL(s.state, ''),\n" +
            "IFNULL(s.status, ''),\n" +
            "IFNULL(s.remark, ''),\n" +
            "IFNULL(s.last_login_time, ''),\n" +
            "IFNULL(r.name,''),\n" +
            "IFNULL(o.organize_name,'')\n" +
            ") LIKE #{terms} \n")
    IPage<Map<String,Object>> queryPage(Page<Map<String,Object>> page, @Param("terms")String terms);
}