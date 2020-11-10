package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.Building;
import work.aijiu.entity.ShopPropertiesRepairWorker;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesRepairWorker)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-21 15:25:02
 */
public interface ShopPropertiesRepairWorkerDao extends BaseMapper<ShopPropertiesRepairWorker> {


    /**
     * 商户物业维修员分页
     * @param page
     * @return
     */
    @Select("select \n" +
            "s1.repair_worker_id, \n" +
            "s1.user_name, \n" +
            "s1.pwd, \n" +
            "s1.real_name, \n" +
            "s1.sex, \n" +
            "s1.age, \n" +
            "s1.tel, \n" +
            "s1.card, \n" +
            "s1.address, \n" +
            "s1.repair_worker_type_id, \n" +
            "s1.organize_id, \n" +
            "s1.role_id, \n" +
            "s1.status, \n" +
            "s1.state, \n" +
            "s1.remark,\n" +
            "s1.create_time,\n" +
            "s1.last_login_time,"+
            "r.name,\n" +
            "o.organize_name\n" +
            "from shop_properties_repair_worker s1,role r,organize o\n" +
            "where \n" +
            "r.role_id = s1.role_id\n" +
            "and o.organize_id = s1.organize_id \n" +
            "and s1.status = '1'\n" +
            "and r.status = '1'\n" +
            "and o.status = '1'\n" +
            "and \n" +
            "CONCAT(\n" +
            "IFNULL(s1.repair_worker_id,''),\n" +
            "IFNULL(s1.user_name, ''),\n" +
            "IFNULL(s1.pwd,''),\n" +
            "IFNULL(s1.real_name,''),\n" +
            "IFNULL(s1.sex,''),\n" +
            "IFNULL(s1.age,''),\n" +
            "IFNULL(s1.tel,''),\n" +
            "IFNULL(s1.card,''),\n" +
            "IFNULL(s1.repair_worker_type_id,''),\n" +
            "IFNULL(s1.organize_id,''),\n" +
            "IFNULL(s1.role_id,''),\n" +
            "IFNULL(s1.status,''),\n" +
            "IFNULL(s1.state,''),\n" +
            "IFNULL(s1.remark,''),\n" +
            "IFNULL(s1.last_login_time,''),\n" +
            "IFNULL(r.name,''),\n" +
            "IFNULL(o.organize_name,'')\n" +
            ") LIKE #{terms} ")
    IPage<HashMap<String,Object>> queryPage(Page<HashMap<String,Object>> page, @Param("terms")String terms);

}