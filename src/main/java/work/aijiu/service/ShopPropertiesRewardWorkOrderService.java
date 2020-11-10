package work.aijiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sun.org.apache.xpath.internal.operations.Bool;
import work.aijiu.entity.ShopPropertiesRewardWorkOrder;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesRewardWorkOrder)表服务接口
 *
 * @author makejava
 * @since 2020-11-01 10:39:58
 */
public interface ShopPropertiesRewardWorkOrderService {

    /**
     * 商户物业 商户发布悬赏订单
     * @param order
     * @return
     */
    Boolean publishRepairOrder(ShopPropertiesRewardWorkOrder order);

    /**
     * 商户物业 商户查看自己发布的正在进行抢单
     * @param pageNum
     * @param pageSize
     * @param shopId
     * @return
     */
    IPage<Map<String,Object>> queryHandling(Integer pageNum,
                                            Integer pageSize, String shopId);
    /**
     * 商户物业 商户根据id查询订单详情
     * @param id
     * @return
     */
    ShopPropertiesRewardWorkOrder getById(String id);

    /**
     * 商户物业 商户删除订单
     * @param id
     * @return
     */
    Boolean deleteById(String id);

    /**
     * 商户物业 商户保存自己的订单
     * @param order
     * @return
     */
    Boolean saveOrder(ShopPropertiesRewardWorkOrder order);

    /**
     * 商户物业 待抢单列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<Map<String,Object>> queryUngrabOrderList(Integer pageNum, Integer pageSize);

    /**
     * 商户物业 根据订单id 更改状态
     * @param id
     * @param state
     * @return
     */
    Boolean updateState(String id,String state);

    /**
     * 商户物业 根据订单id 更改维修员
     * @param id
     * @param worker
     * @return
     */
    Boolean updateWorker(String id,String worker);

    /**
     * 商户物业 维修员查看自己正在处理的订单
     * @param pageNum
     * @param pageSize
     * @param worker
     * @return
     */
    IPage<Map<String,Object>> queryRWHandling(Integer pageNum, Integer pageSize,String worker);

    /**
     * 商户统计 悬赏订单数量
     * @param id
     * @return
     */
    int getCountOrderById(String id);


}