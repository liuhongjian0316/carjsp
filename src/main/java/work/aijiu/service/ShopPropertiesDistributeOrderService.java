package work.aijiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import work.aijiu.entity.ShopPropertiesDistributeOrder;

import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesDistributeOrder)表服务接口
 *
 * @author makejava
 * @since 2020-10-26 18:30:25
 */
public interface ShopPropertiesDistributeOrderService {

    /**
     * 商户物业 添加派单
     * @param order
     * @return
     */
    Boolean addOrder(ShopPropertiesDistributeOrder order);

    /**
     * 商户物业 维修员根据自己的id 查询自己的待办
     * @param id
     * @return
     */
    IPage<ShopPropertiesDistributeOrder> getBySelfId(Integer pageNum, Integer pageSize, String id);

    /**
     * 商户物业 维修员查看派单详情
     * @param id
     * @return
     */
    ShopPropertiesDistributeOrder getDetailsById(String id);

    /**
     * 根据派单id更改派单状态
     * @param id
     * @param state
     * @return
     */
    Boolean updateStateById(String id,String state);

    /**
     * 商户物业 查询维修员正在处理的订单
     * @param pageNum
     * @param pageSize
     * @param id
     * @return
     */
    IPage<ShopPropertiesDistributeOrder> getHandlingBySelfId(Integer pageNum, Integer pageSize, String id);

    /**
     * 商户物业 根据订单id更改状态
     * @param id
     * @param state
     * @return
     */
    Boolean updateStateByOrderId(String id,String state);

    /**
     * 商户物业 根据id 更改增援状态
     * @param id
     * @param state
     * @return
     */
    Boolean updateReinforceById(String id, String state);

    /**
     * 查看待增援的派单
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<ShopPropertiesDistributeOrder> queryUnReinforce(Integer pageNum, Integer pageSize);

    /**
     * 根据派单id 获取派单信息
     * @param id
     * @return
     */
    ShopPropertiesDistributeOrder getByid(String id);

    /**
     * 商户物业 维修部 指派维修员
     * @param id
     * @param workers
     * @return
     */
    Boolean reinforceWorker(String id,String workers);


    /**
     * 查看全部维修中订单
     * @return
     */
    List<ShopPropertiesDistributeOrder> selALl();

    /**
     * 商户物业 根据派单id 更改派单的增援状态
     * @param id
     * @param state
     * @return
     */
    Boolean updateRsseinforceStateByDid(String id, String state);

    /**
     * 商户物业 维修员 待处理撤单列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<ShopPropertiesDistributeOrder> queryUnRecall(Integer pageNum, Integer pageSize);

    /**
     * 维修员统计 某一天处理的订单数
     * @param id
     * @param time
     * @return
     */
    Map<String,Object> repairOrderByTime(String id, String time);

    /**
     * 统计维修员 总计处理的订单
     * @param id
     * @return
     */
    int totalOrders(String id);

    /**
     * 维修员统计 统计各个类型的订单
     * @param id
     * @return
     */
    List<Map<String,Object>> reparirTypeOrder(String id);

    /**
     * 维修员统计 处理普通订单数量
     * @param id 维修员id
     * @return
     */
    Map<String,Object> countOrder(String id);

    /**
     * 维修员统计 处理电话订单数量
     * @param id 维修员id
     * @return
     */
    Map<String,Object> countCsOrder(String id);

    /**
     * 维修员统计 处理悬赏订单数量
     * @param id 维修员id
     * @return
     */
    Map<String,Object> countRwOrder(String id);

    /**********************************************************************
     * **************维修员统计 各种订单 好评  *******************************
     * **************@param id  维修员id    *******************************
     * **************@return   int（订单数） *******************************
     *********************************************************************/
    int numOfOrderReact(String id);
    int numOfOrderProfession(String id);
    int numOfOrderAttitude(String id);
    int numOfCsOrderReact(String id);
    int numOfCsOrderProfession(String id);
    int numOfCsOrderAttitude(String id);
    int numOfRwOrderReact(String id);
    int numOfRwOrderProfession(String id);
    int numOfRwOrderAttitude(String id);

    /**********************************************************************
     * **************维修员统计 收入   *******************************
     * **************@param id  维修员id    *******************************
     * **************@return   int（订单数） *******************************
     *********************************************************************/

    /**
     * 昨日收入
     * @param id
     * @return
     */
    int yesterdayEarnings(String id);

    /**
     * 近30天收入
     * @param id
     * @return
     */
    int monthEarnings(String id);

    /**
     * 当月收入
     * @param id
     * @return
     */
    int currentEarnings(String id);

    /**
     * 总计收入
     * @param id
     * @return
     */
    int totalEarnings(String id);

    /**
     * 可提现收入
     * @param id
     * @return
     */
    int canCarryEarnings(String id);

    /**
     * 根据id 和时间 获取收入
     * @param id
     * @param time
     * @return
     */
    int repairEarnByTime(String id,String time);

    /**
     * 物业管理员统计 按时间统计订单数
     * @param time
     * @return
     */
    Map<String,Object> adminOrderByTime(String time);
}