package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.ShopPropertiesWorkOrder;

import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesWorkOrder)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-25 23:55:05
 */
public interface ShopPropertiesWorkOrderDao extends BaseMapper<ShopPropertiesWorkOrder> {


    /**
     * 商户物业 商户查询正在处理的保修单
     * @param page
     * @return
     */
    @Select("SELECT \n" +
            "s1.work_order_id, \n" +
            "s1.work_type_id, \n" +
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
            "from shop_properties_work_order s1\n" +
            "LEFT JOIN shop_properties_work_type s2\n" +
            "on s1.work_type_id = s2.work_type_id\n" +
            "where s1.status = '1' and s2.status = '1' and \n" +
            "s1.state = '0' or s1.state = '1' or s1.state = '2' or s1.state ='3'  ORDER BY s1.create_time")
    IPage<Map<String,Object>> queryHandling(Page<Map<String,Object>> page);

    /**
     *  维修部经理 待审核列表
     * @param page
     * @return
     */
    @Select("SELECT \n" +
            "s1.work_order_id, \n" +
            "s1.work_type_id, \n" +
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
            "from shop_properties_work_order s1\n" +
            "LEFT JOIN shop_properties_work_type s2\n" +
            "on s1.work_type_id = s2.work_type_id\n" +
            "where s1.status = '1' and s2.status = '1' and \n" +
            "s1.state = '0' ORDER BY s1.create_time")
    IPage<Map<String,Object>> queryUntreat(Page<Map<String,Object>> page);

    /**
     * 商户物业 商户统计 根据id和 时间 查询订单
     * @param id
     * @param time
     * @return
     */
    @Select("select t1.日期,t1.普通订单,t2.悬赏订单,t3.电话订单 from \n" +
            "(\n" +
            "select  #{time} as 日期 , count(*) as 普通订单 \n" +
            "from shop_properties_work_order \n" +
            "where \n" +
            "shop_id = #{id} \n" +
            "and \n" +
            "date_format(create_time,'%Y-%m-%d') = #{time}\n" +
            ") t1,\n" +
            "(\n" +
            "select #{time}  as 日期 , count(*) as 悬赏订单 \n" +
            "from shop_properties_reward_work_order \n" +
            "where \n" +
            "shop_id = #{id} \n" +
            "and \n" +
            "date_format(create_time,'%Y-%m-%d') = #{time}\n" +
            ") t2,\n" +
            "\n" +
            "(\n" +
            "select  #{time} as 日期 , count(*) as 电话订单 \n" +
            "from shop_properties_work_order_cs \n" +
            "where \n" +
            "shop_id = #{id} \n" +
            "and \n" +
            "date_format(reserve_time,'%Y-%m-%d') = #{time}\n" +
            ") t3")
    Map<String,Object> getByTime(@Param("id") String id, @Param("time") String time);

    /**
     * 商户物业 商户统计普通订单总数
     * @param id
     * @return
     */
    @Select("select '普通订单' as 类型,count(*) as 总计 from shop_properties_work_order where status = '1' and shop_id = #{id}")
    Map<String,Object> getTotalOrder(@Param("id") String id);

    /**
     * 商户物业 商户统计悬赏订单总数
     * @param id
     * @return
     */
    @Select("select '悬赏订单' as 类型,count(*) as 总计 from shop_properties_reward_work_order where status = '1' and shop_id = '1'")
    Map<String,Object> getTotalRewardOrder(@Param("id") String id);

    /**
     * 商户物业 商户统计电话订单总数
     * @param id
     * @return
     */
    @Select("select '电话订单' as 类型,count(*) as 总计 from shop_properties_work_order_cs where status = '1' and shop_id = '1'")
    Map<String,Object> getTotalTelOrder(@Param("id") String id);

}