package work.aijiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import work.aijiu.entity.ShopPropertiesDistributeOrder;
import work.aijiu.entity.ShopPropertiesWorkOrder;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesWorkOrder)表服务接口
 *
 * @author makejava
 * @since 2020-10-25 23:55:05
 */
public interface ShopPropertiesWorkOrderService {

    /**
     * 商户物业 商户填写维修单
     * @param order
     * @return
     */
    Boolean shopApplyOrder(ShopPropertiesWorkOrder order);

    /**
     * 商户物业 商户查询正在处理的保修单
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<Map<String,Object>> queryHandling(Integer pageNum, Integer pageSize);

    /**
     * 商户物业 商户根据id 查询订单信息
     * @param id
     * @return
     */
    ShopPropertiesWorkOrder getById(String id);

    /**
     * 商户物业 商户修改订单信息
     * @param workOrder
     * @return
     */
    Boolean saveOrder(ShopPropertiesWorkOrder workOrder);

    /**
     * 商户物业 商户根据id删除订单
     * @param id
     * @return
     */
    Boolean deleteOrderS(String id);

    /**
     * 商户物业 审核人员 未处理列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<Map<String,Object>> queryUntreat(Integer pageNum, Integer pageSize);

    /**
     * 商户物业 更改报单状态
     * @param id
     * @param state
     * @return
     */
    Boolean updateState(String id,String state);

    /**
     * 商户物业 更改报单的维修员
     * @param id
     * @param workerId
     * @return
     */
    Boolean updateWorker(String id,String workerId);

    /**
     * 商户物业 商户查询自己已完成的订单
     * @param pageNum
     * @param pageSize
     * @param id
     * @return
     */
    IPage<ShopPropertiesWorkOrder> getFinishById(Integer pageNum, Integer pageSize, String id);

    /**
     * 商户物业 商户对订单的评价
     * @param order
     * @return
     */
    Boolean evaluateOrder(ShopPropertiesWorkOrder order);


    /**
     * 商户物业 维修员拒绝订单提交
     * @param order
     * @return
     */
    Boolean refusedOrder(ShopPropertiesWorkOrder order);

    /**
     * 商户物业 审核员工 ，审核拒单
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<ShopPropertiesWorkOrder> queryUnaudit(Integer pageNum, Integer pageSize);


    /**
     * 商户物业 商户统计 根据时间统计订单
     * @param time
     * @param id
     * @return
     */
    Map<String,Object> selByTime(String time,String id);

    /**
     * 商户物业 商户统计 普通订单总数
     * @param id
     * @return
     */
    Map<String,Object> getTotalOrder(String id);

    /**
     * 商户物业 商户统计 悬赏订单总数
     * @param id
     * @return
     */
    Map<String,Object> getTotalRewardOrder(String id);

    /**
     * 商户物业 商户统计 电话订单总数
     * @param id
     * @return
     */
    Map<String,Object> getTotalTelOrder(String id);

    /**
     * 商户统计 统计商户的订单数量
     * @param shopId
     * @return
     */
    int getCountOrderById(String shopId);

}