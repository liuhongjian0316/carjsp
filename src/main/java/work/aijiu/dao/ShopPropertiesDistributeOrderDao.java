package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.ShopPropertiesDistributeOrder;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesDistributeOrder)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-26 18:30:25
 */
public interface ShopPropertiesDistributeOrderDao extends BaseMapper<ShopPropertiesDistributeOrder> {


    /**
     * 维修员统计 某一天处理的订单数量
     * @param id 维修员id
     * @param time 时间
     * @return
     */
    @Select("select #{time} as 日期 , count(*) as 订单 from \n" +
            "(\n" +
            "select a.*,substring_index(substring_index(a.worker,',',b.help_topic_id+1),',',-1) workers \n" +
            "from shop_properties_distribute_order a join\n" +
            "mysql.help_topic b\n" +
            "on b.help_topic_id < (length(a.worker) - length(replace(a.worker,',',''))+1)\n" +
            "order by a.distribute_order_id\n" +
            ") a\n" +
            "where a.workers = #{id} and date_format(a.reserve_time,'%Y-%m-%d') = #{time} and a.status = '1'")
    Map<String,Object> repairOrderByTime(@Param("id") String id,@Param("time") String time);

    /**
     * 维修员统计 总共处理的订单
     * @param id 维修员id
     * @return
     */
    @Select("select count(*)\n" +
            "from\n" +
            "(\n" +
            "select a.*,substring_index(substring_index(a.worker,',',b.help_topic_id+1),',',-1) workers \n" +
            "from shop_properties_distribute_order a join\n" +
            "mysql.help_topic b\n" +
            "on b.help_topic_id < (length(a.worker) - length(replace(a.worker,',',''))+1)\n" +
            "order by a.distribute_order_id\n" +
            ")  a\n" +
            "where a.workers = #{id} and a.status = '1'")
    int totalOrders(@Param("id") String id);

    /**
     * 统计维修员 处理各种类型的订单数量
     * @param id
     * @return
     */
    @Select("select b.work_type_name as 类型, count(*) as 订单 \n" +
            "from\n" +
            "(\n" +
            "select a.*,substring_index(substring_index(a.worker,',',b.help_topic_id+1),',',-1) workers \n" +
            "from shop_properties_distribute_order a join\n" +
            "mysql.help_topic b\n" +
            "on b.help_topic_id < (length(a.worker) - length(replace(a.worker,',',''))+1)\n" +
            "order by a.distribute_order_id\n" +
            ")  a , shop_properties_work_type b \n" +
            "where a.workers = #{id} and a.status = 1 and a.work_type = b.work_type_id\n" +
            "GROUP BY b.work_type_name")
    List<Map<String,Object>> reparirTypeOrder(@Param("id") String id);

    /**
     * 维修员统计 处理普通订单数量
     * @param id
     * @return
     */
    @Select("select '普通订单' as 方式,count(*) as 总计\n" +
            "from\n" +
            "(\n" +
            "select a.*,substring_index(substring_index(a.worker,',',b.help_topic_id+1),',',-1) workers \n" +
            "from shop_properties_distribute_order a join\n" +
            "mysql.help_topic b\n" +
            "on b.help_topic_id < (length(a.worker) - length(replace(a.worker,',',''))+1)\n" +
            "order by a.distribute_order_id\n" +
            ")  a ,  shop_properties_work_order b\n" +
            "where a.workers = #{id} and a.status = '1' and b.status = '1' and a.work_order_id = b.work_order_id\n")
    Map<String,Object> countOrder(@Param("id") String id);

    /**
     * 维修员统计 处理电话订单数量
     * @param id
     * @return
     */
    @Select("select '电话订单' as 方式,count(*) as 总计\n" +
            "from\n" +
            "(\n" +
            "select a.*,substring_index(substring_index(a.worker,',',b.help_topic_id+1),',',-1) workers \n" +
            "from shop_properties_distribute_order a join\n" +
            "mysql.help_topic b\n" +
            "on b.help_topic_id < (length(a.worker) - length(replace(a.worker,',',''))+1)\n" +
            "order by a.distribute_order_id\n" +
            ")  a ,  shop_properties_work_order_cs b\n" +
            "where a.workers = #{id} and a.status = '1' and b.status = '1' and a.work_order_id = b.work_order_cs_id")
    Map<String,Object> countCsOrder(@Param("id") String id);

    /**
     * 维修员统计 处理悬赏订单数量
     * @param id
     * @return
     */
    @Select("select '悬赏订单' as 方式,count(*) as 总计\n" +
            "from\n" +
            "(\n" +
            "select a.*,substring_index(substring_index(a.worker,',',b.help_topic_id+1),',',-1) workers \n" +
            "from shop_properties_distribute_order a join\n" +
            "mysql.help_topic b\n" +
            "on b.help_topic_id < (length(a.worker) - length(replace(a.worker,',',''))+1)\n" +
            "order by a.distribute_order_id\n" +
            ")  a ,  shop_properties_reward_work_order b\n" +
            "where a.workers = #{id} and a.status = '1' and b.status = '1' and a.work_order_id = b.reward_work_oder_id")
    Map<String,Object> countRwOrder(@Param("id") String id);

    /**
     * 普通订单 反应速度好评
     * @param id
     * @return
     */
    @Select("select count(*) as 总计\n" +
            "from\n" +
            "(\n" +
            "select a.*,substring_index(substring_index(a.worker,',',b.help_topic_id+1),',',-1) workers \n" +
            "from shop_properties_distribute_order a join\n" +
            "mysql.help_topic b\n" +
            "on b.help_topic_id < (length(a.worker) - length(replace(a.worker,',',''))+1)\n" +
            "order by a.distribute_order_id\n" +
            ")  a ,  shop_properties_work_order b\n" +
            "where a.workers = #{id} and a.status = '1' and b.status = '1' and a.work_order_id = b.work_order_id \n" +
            "and b.react_speed > 3 ")
    int numOfOrderReact(@Param("id")String id);

    /**
     * 普通订单 专业程度好评
     * @param id
     * @return
     */
    @Select("select count(*) as 总计\n" +
            "from\n" +
            "(\n" +
            "select a.*,substring_index(substring_index(a.worker,',',b.help_topic_id+1),',',-1) workers \n" +
            "from shop_properties_distribute_order a join\n" +
            "mysql.help_topic b\n" +
            "on b.help_topic_id < (length(a.worker) - length(replace(a.worker,',',''))+1)\n" +
            "order by a.distribute_order_id\n" +
            ")  a ,  shop_properties_work_order b\n" +
            "where a.workers = #{id} and a.status = '1' and b.status = '1' and a.work_order_id = b.work_order_id \n" +
            "and b.profession_level > 3 ")
    int numOfOrderProfession(@Param("id")String id);

    /**
     * 普通订单 服务态度好评
     * @param id
     * @return
     */
    @Select("select count(*) as 总计\n" +
            "from\n" +
            "(\n" +
            "select a.*,substring_index(substring_index(a.worker,',',b.help_topic_id+1),',',-1) workers \n" +
            "from shop_properties_distribute_order a join\n" +
            "mysql.help_topic b\n" +
            "on b.help_topic_id < (length(a.worker) - length(replace(a.worker,',',''))+1)\n" +
            "order by a.distribute_order_id\n" +
            ")  a ,  shop_properties_work_order b\n" +
            "where a.workers = #{id} and a.status = '1' and b.status = '1' and a.work_order_id = b.work_order_id \n" +
            "and b.service_attitude > 3 ")
    int numOfOrderAttitude(@Param("id")String id);

    /**
     * 电话订单 反应速度好评
     * @param id
     * @return
     */
    @Select("select count(*) as 总计\n" +
            "from\n" +
            "(\n" +
            "select a.*,substring_index(substring_index(a.worker,',',b.help_topic_id+1),',',-1) workers \n" +
            "from shop_properties_distribute_order a join\n" +
            "mysql.help_topic b\n" +
            "on b.help_topic_id < (length(a.worker) - length(replace(a.worker,',',''))+1)\n" +
            "order by a.distribute_order_id\n" +
            ")  a ,  shop_properties_work_order_cs b\n" +
            "where a.workers = #{id} and a.status = '1' and b.status = '1' and a.work_order_id = b.work_order_cs_id \n" +
            "and b.react_speed > 3 ")
    int numOfCsOrderReact(@Param("id")String id);

    /**
     * 电话订单 专业程度好评
     * @param id
     * @return
     */
    @Select("select count(*) as 总计\n" +
            "from\n" +
            "(\n" +
            "select a.*,substring_index(substring_index(a.worker,',',b.help_topic_id+1),',',-1) workers \n" +
            "from shop_properties_distribute_order a join\n" +
            "mysql.help_topic b\n" +
            "on b.help_topic_id < (length(a.worker) - length(replace(a.worker,',',''))+1)\n" +
            "order by a.distribute_order_id\n" +
            ")  a ,  shop_properties_work_order_cs b\n" +
            "where a.workers = #{id} and a.status = '1' and b.status = '1' and a.work_order_id = b.work_order_cs_id \n" +
            "and b.profession_level > 3 ")
    int numOfCsOrderProfession(@Param("id")String id);

    /**
     * 电话订单 服务态度好评
     * @param id
     * @return
     */
    @Select("select count(*) as 总计\n" +
            "from\n" +
            "(\n" +
            "select a.*,substring_index(substring_index(a.worker,',',b.help_topic_id+1),',',-1) workers \n" +
            "from shop_properties_distribute_order a join\n" +
            "mysql.help_topic b\n" +
            "on b.help_topic_id < (length(a.worker) - length(replace(a.worker,',',''))+1)\n" +
            "order by a.distribute_order_id\n" +
            ")  a ,  shop_properties_work_order_cs b\n" +
            "where a.workers = #{id} and a.status = '1' and b.status = '1' and a.work_order_id = b.work_order_cs_id \n" +
            "and b.service_attitude > 3 ")
    int numOfCsOrderAttitude(@Param("id")String id);

    /**
     * 悬赏订单 反应速度好评
     * @param id
     * @return
     */
    @Select("select count(*) as 总计\n" +
            "from\n" +
            "(\n" +
            "select a.*,substring_index(substring_index(a.worker,',',b.help_topic_id+1),',',-1) workers \n" +
            "from shop_properties_distribute_order a join\n" +
            "mysql.help_topic b\n" +
            "on b.help_topic_id < (length(a.worker) - length(replace(a.worker,',',''))+1)\n" +
            "order by a.distribute_order_id\n" +
            ")  a ,  shop_properties_reward_work_order b\n" +
            "where a.workers = #{id} and a.status = '1' and b.status = '1' and a.work_order_id = b.reward_work_oder_id \n" +
            "and b.react_speed > 3 ")
    int numOfRwOrderReact(@Param("id")String id);

    /**
     * 悬赏订单 专业程度好评
     * @param id
     * @return
     */
    @Select("select count(*) as 总计\n" +
            "from\n" +
            "(\n" +
            "select a.*,substring_index(substring_index(a.worker,',',b.help_topic_id+1),',',-1) workers \n" +
            "from shop_properties_distribute_order a join\n" +
            "mysql.help_topic b\n" +
            "on b.help_topic_id < (length(a.worker) - length(replace(a.worker,',',''))+1)\n" +
            "order by a.distribute_order_id\n" +
            ")  a ,  shop_properties_reward_work_order b\n" +
            "where a.workers = #{id} and a.status = '1' and b.status = '1' and a.work_order_id = b.reward_work_oder_id \n" +
            "and b.profession_level > 3 ")
    int numOfRwOrderProfession(@Param("id")String id);

    /**
     * 悬赏订单 服务态度好评
     * @param id
     * @return
     */
    @Select("select count(*) as 总计\n" +
            "from\n" +
            "(\n" +
            "select a.*,substring_index(substring_index(a.worker,',',b.help_topic_id+1),',',-1) workers \n" +
            "from shop_properties_distribute_order a join\n" +
            "mysql.help_topic b\n" +
            "on b.help_topic_id < (length(a.worker) - length(replace(a.worker,',',''))+1)\n" +
            "order by a.distribute_order_id\n" +
            ")  a ,  shop_properties_reward_work_order b\n" +
            "where a.workers = #{id} and a.status = '1' and b.status = '1' and a.work_order_id = b.reward_work_oder_id \n" +
            "and b.service_attitude > 3 ")
    int numOfRwOrderAttitude(@Param("id")String id);

    /**
     * 统计维修员某日收入
     * @param id
     * @param time
     * @return
     */
    @Select("select \n" +
            "IFNULL(sum(a.total_money),0)\n" +
            "from  \n" +
            "shop_properties_reward_work_order a\n" +
            "where \n" +
            "a.status = '1' and a.worker = #{id} \n" +
            "and \n" +
            "date_format(a.create_time,'%Y-%m-%d') = #{time}")
    int repairEarnByTime(@Param("id")String id, @Param("time")String time);

    /**
     * 统计维修员时间段收入（日）
     * @param id
     * @param beginTime
     * @param endTime
     * @return
     */
    @Select("select \n" +
            "IFNULL(sum(a.total_money),0)\n" +
            "from  \n" +
            "shop_properties_reward_work_order a\n" +
            "where \n" +
            "a.status = '1' and a.worker = #{id}\n" +
            "and \n" +
            "date_format(a.create_time,'%Y-%m-%d')\n" +
            "BETWEEN\n" +
            "#{beginTime}\n" +
            "and\n" +
            "#{endTime}")
    int repairEarnBetweenTime(@Param("id")String id,
                              @Param("beginTime")String beginTime,
                              @Param("endTime")String endTime);

    /**
     * 维修员统计 某个时间段收入（月）
     * @param id
     * @param beginTime
     * @param endTime
     * @return
     */
    @Select("select \n" +
            "IFNULL(sum(a.total_money),0)\n" +
            "from  \n" +
            "shop_properties_reward_work_order a\n" +
            "where \n" +
            "a.status = '1' and a.worker = #{id}\n" +
            "and \n" +
            "date_format(a.create_time,'%Y-%m')\n" +
            "BETWEEN\n" +
            "#{beginTime}\n" +
            "and\n" +
            "#{endTime}")
    int repairEarnBetweenMonth(@Param("id")String id,
                              @Param("beginTime")String beginTime,
                              @Param("endTime")String endTime);

    /**
     * 维修员统计 总计收入
     * @param id
     * @return
     */
    @Select("select \n" +
            "IFNULL(sum(a.total_money),0)\n" +
            "from  \n" +
            "shop_properties_reward_work_order a\n" +
            "where \n" +
            "a.status = '1' and a.worker = #{id}")
    int totalEarn(@Param("id")String id);


    /**
     * 管理员统计 某一天处理的订单数量
     * @param time 时间
     * @return
     */
    @Select("select #{time} as 日期 , count(*) as 订单 from \n" +
            "(\n" +
            "select a.*,substring_index(substring_index(a.worker,',',b.help_topic_id+1),',',-1) workers \n" +
            "from shop_properties_distribute_order a join\n" +
            "mysql.help_topic b\n" +
            "on b.help_topic_id < (length(a.worker) - length(replace(a.worker,',',''))+1)\n" +
            "order by a.distribute_order_id\n" +
            ") a\n" +
            "where date_format(a.reserve_time,'%Y-%m-%d') = #{time} and a.status = '1'")
    Map<String,Object> adminOrderByTime(@Param("time") String time);

}