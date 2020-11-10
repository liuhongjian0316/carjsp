package work.aijiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import work.aijiu.entity.ShopPropertiesWorkOrder;
import work.aijiu.entity.ShopPropertiesWorkOrderCs;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesWorkOrderCs)表服务接口
 *
 * @author makejava
 * @since 2020-10-30 10:04:07
 */
public interface ShopPropertiesWorkOrderCsService {

    /**
     * 商户物业 客服中心代替商户填写维修工单
     * @param order
     * @return
     */
    Boolean applyRepairCs(ShopPropertiesWorkOrderCs order);

    /**
     * 商户物业 商户查看自己正在进行的电话订单
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<Map<String,Object>> queryHandling(Integer pageNum, Integer pageSize);

    /**
     * 商户物业 商户查看订单的详细信息
     * @param id
     * @return
     */
    ShopPropertiesWorkOrderCs selectByOrderId(String id);

    /**
     * 商户物业 商户保存电话订单信息
     * @param order
     * @return
     */
    Boolean saveTelOrder(ShopPropertiesWorkOrderCs order);

    /**
     * 商户物业 商户删除自己正在处理的订单
     * @param id
     * @return
     */
    Boolean deleteTelOrder(String id);

    /**
     * 商户物业 维修部员工查看电话订单代派列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<Map<String,Object>> queryUntreat(Integer pageNum, Integer pageSize);

    /**
     * 商户物业 根据id 更改 worker
     * @param id
     * @param worker
     * @return
     */
    Boolean updateWorker(String id,String worker);

    /**
     * 商户物业 根据id 更改订单状态
     * @param id
     * @param state
     * @return
     */
    Boolean updateState(String id, String state);


    /**
     * 商户物业 商户查看 自己完成订单
     * @param pageNum
     * @param pageSize
     * @param id
     * @return
     */
    IPage<ShopPropertiesWorkOrderCs> getFinishById(Integer pageNum, Integer pageSize, String id);

    /**
     * 商户物业 商户评价订单
     * @param order
     * @return
     */
    Boolean evaluateOrder(ShopPropertiesWorkOrderCs order);

    /**
     * 商户物业 根据订单
     * @param id
     * @return
     */
    Boolean selectById(String id);

    /**
     * 商户物业 维修员拒绝电话订单
     * @param order
     * @return
     */
    Boolean refusedOrder(ShopPropertiesWorkOrderCs order);

    /**
     * 商户物业 维修部员工 查看待审核 拒单申请
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<ShopPropertiesWorkOrderCs> queryUnaudit(Integer pageNum, Integer pageSize);

    /**
     * 商户物业 根据电话订单id 查看订单编号
     * @param id
     * @return
     */
    ShopPropertiesWorkOrderCs getById(String id);

    /**
     *商户统计 电话订单数量
     * @param shopId
     * @return
     */
    int getCountOrderById(String shopId);

    /**
     * 商户物业管理员统计 客服处理的订单数
     * @return
     */
    int countTotalTelOrder();


}