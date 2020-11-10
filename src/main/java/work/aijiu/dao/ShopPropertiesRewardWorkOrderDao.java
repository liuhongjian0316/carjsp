package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.ShopPropertiesRewardWorkOrder;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesRewardWorkOrder)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-01 10:39:58
 */
public interface ShopPropertiesRewardWorkOrderDao extends BaseMapper<ShopPropertiesRewardWorkOrder> {

    /**
     * 商户物业 商户查询正在处理的保修单
     * @param page
     * @return
     */
    @Select("SELECT \n" +
            "s1.reward_work_oder_id, \n" +
            "s1.work_type_id, \n" +
            "s1.work_number, \n" +
            "s2.work_type_name,\n" +
            "s1.shop_name,\n" +
            "s1.shop_id,\n" +
            "s1.apply_name,\n" +
            "s1.tel,\n" +
            "s1.create_time,\n" +
            "s1.address,\n" +
            "s1.remark,\n" +
            "s1.fault_description,\n" +
            "s1.fault_description_content,\n" +
            "s1.reward_money,\n" +
            "s1.state\n" +
            "from shop_properties_reward_work_order s1\n" +
            "LEFT JOIN shop_properties_work_type s2\n" +
            "on s1.work_type_id = s2.work_type_id\n" +
            "where s1.shop_id = #{shopId} and s1.status = '1' and s2.status = '1' and \n" +
            "s1.state = '0' or s1.state = '1' or s1.state = '2'or s1.state = '3' ORDER BY s1.create_time")
    IPage<Map<String,Object>> queryHandling(Page<Map<String,Object>> page,@Param("shopId") String shopId);

    /**
     * 商户物业 待抢单列表
     * @param page
     * @return
     */
    @Select("SELECT \n" +
            "s1.reward_work_oder_id, \n" +
            "s1.work_type_id, \n" +
            "s1.work_number, \n" +
            "s2.work_type_name,\n" +
            "s1.shop_name,\n" +
            "s1.shop_id,\n" +
            "s1.apply_name,\n" +
            "s1.tel,\n" +
            "s1.create_time,\n" +
            "s1.address,\n" +
            "s1.remark,\n" +
            "s1.fault_description,\n" +
            "s1.fault_description_content,\n" +
            "s1.reward_money,\n" +
            "s1.worker,\n" +
            "s1.state\n" +
            "from shop_properties_reward_work_order s1\n" +
            "LEFT JOIN shop_properties_work_type s2\n" +
            "on s1.work_type_id = s2.work_type_id\n" +
            "where s1.status = '1' and s2.status = '1' and \n" +
            "s1.state = '0'  ORDER BY s1.create_time")
    IPage<Map<String,Object>> queryUngrabOrderList(Page<Map<String,Object>> page);

    /**
     * 商户物业 商户查看自己的正在处理的悬赏订单
     * @param page
     * @param worker
     * @return
     */
    @Select("SELECT \n" +
            "s1.reward_work_oder_id, \n" +
            "s1.work_type_id, \n" +
            "s1.work_number, \n" +
            "s2.work_type_name,\n" +
            "s1.shop_name,\n" +
            "s1.shop_id,\n" +
            "s1.apply_name,\n" +
            "s1.tel,\n" +
            "s1.create_time,\n" +
            "s1.address,\n" +
            "s1.remark,\n" +
            "s1.fault_description,\n" +
            "s1.fault_description_content,\n" +
            "s1.reward_money,\n" +
            "s1.worker,\n" +
            "s1.state\n" +
            "from shop_properties_reward_work_order s1\n" +
            "LEFT JOIN shop_properties_work_type s2\n" +
            "on s1.work_type_id = s2.work_type_id\n" +
            "where s1.worker = #{worker} and  s1.status = '1' and s2.status = '1' and \n" +
            "s1.state = '1' or s1.state = '3'  ORDER BY s1.create_time")
    IPage<Map<String,Object>> queryRWHandling(Page<Map<String,Object>> page,@Param("worker") String worker);
}