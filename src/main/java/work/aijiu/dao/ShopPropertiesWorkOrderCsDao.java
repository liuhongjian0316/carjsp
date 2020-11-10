package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.ShopPropertiesWorkOrderCs;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesWorkOrderCs)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-30 10:04:07
 */
public interface ShopPropertiesWorkOrderCsDao extends BaseMapper<ShopPropertiesWorkOrderCs> {

    /**
     * 商户用户 商户查看自己正在进行的订单（客服帮填写的订单）
     * @param page
     * @return
     */
    @Select("SELECT \n" +
            "s1.work_order_cs_id, \n" +
            "s1.work_type, \n" +
            "s2.work_type_name,\n" +
            "s1.shop_name,\n" +
            "s1.shop_id,\n" +
            "s1.apply_name,\n" +
            "s1.tel,\n" +
            "s1.reserve_time,\n" +
            "s1.address,\n" +
            "s1.remark,\n" +
            "s1.fault_description,\n" +
            "s1.fault_description_content,\n" +
            "s1.state\n" +
            "from shop_properties_work_order_cs s1\n" +
            "LEFT JOIN shop_properties_work_type s2\n" +
            "on s1.work_type = s2.work_type_id\n" +
            "where s1.status = '1' and s2.status = '1' and \n" +
            "s1.state = '0' or s1.state = '1' or s1.state = '2' or s1.state ='3'  ORDER BY s1.filling_time")
    IPage<Map<String,Object>> queryHandling(Page<Map<String,Object>> page);

    /**
     * 商户物业 维修部员工 查看电话订单待派列表
     * @param page
     * @return
     */
    @Select("SELECT \n" +
            "s1.work_order_cs_id, \n" +
            "s1.work_type, \n" +
            "s2.work_type_name,\n" +
            "s1.shop_name,\n" +
            "s1.shop_id,\n" +
            "s1.apply_name,\n" +
            "s1.tel,\n" +
            "s1.reserve_time,\n" +
            "s1.address,\n" +
            "s1.remark,\n" +
            "s1.fault_description,\n" +
            "s1.fault_description_content,\n" +
            "s1.state\n" +
            "from shop_properties_work_order_cs s1\n" +
            "LEFT JOIN shop_properties_work_type s2\n" +
            "on s1.work_type = s2.work_type_id\n" +
            "where s1.status = '1' and s2.status = '1' and \n" +
            "s1.state = '0' ORDER BY s1.filling_time")
    IPage<Map<String,Object>> queryUntreat(Page<Map<String,Object>> page);

}