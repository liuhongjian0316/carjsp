package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.ShopPropertiesDistributeOrder;
import work.aijiu.entity.ShopPropertiesWorkOrder;
import work.aijiu.dao.ShopPropertiesWorkOrderDao;
import work.aijiu.service.ShopPropertiesWorkOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesWorkOrder)表服务实现类
 *
 * @author makejava
 * @since 2020-10-25 23:55:05
 */
@Service("shopPropertiesWorkOrderService")
public class ShopPropertiesWorkOrderServiceImpl implements ShopPropertiesWorkOrderService {
    @Autowired
    private ShopPropertiesWorkOrderDao shopPropertiesWorkOrderDao;

    @Override
    public Boolean shopApplyOrder(ShopPropertiesWorkOrder order) {
        return shopPropertiesWorkOrderDao.insert(order)>0;
    }

    @Override
    public IPage<Map<String, Object>> queryHandling(Integer pageNum, Integer pageSize) {
        return shopPropertiesWorkOrderDao.queryHandling(new Page<>(pageNum, pageSize));
    }

    @Override
    public ShopPropertiesWorkOrder getById(String id) {
        QueryWrapper<ShopPropertiesWorkOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("work_order_id",id);
        return shopPropertiesWorkOrderDao.selectOne(wrapper);
    }

    @Override
    public Boolean saveOrder(ShopPropertiesWorkOrder workOrder) {
        UpdateWrapper<ShopPropertiesWorkOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("work_order_id",workOrder.getWorkOrderId())
                .set("shop_name",workOrder.getShopName())
                .set("work_type_id",workOrder.getWorkTypeId())
                .set("apply_name",workOrder.getApplyName())
                .set("tel",workOrder.getTel())
                .set("reserve_time",workOrder.getReserveTime())
                .set("address",workOrder.getAddress())
                .set("remark",workOrder.getRemark())
                .set("fault_description",workOrder.getFaultDescription())
                .set("fault_description_content",workOrder.getFaultDescriptionContent());
        return shopPropertiesWorkOrderDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean deleteOrderS(String id) {
        UpdateWrapper<ShopPropertiesWorkOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("work_order_id",id)
                .set("status","0");

        return shopPropertiesWorkOrderDao.update(null,wrapper)>0;
    }

    @Override
    public IPage<Map<String, Object>> queryUntreat(Integer pageNum, Integer pageSize) {
        return shopPropertiesWorkOrderDao.queryUntreat(new Page<>(pageNum, pageSize));
    }

    @Override
    public Boolean updateState(String id, String state) {
        UpdateWrapper<ShopPropertiesWorkOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("work_order_id",id).set("state",state);
        return shopPropertiesWorkOrderDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean updateWorker(String id, String workerId) {
        UpdateWrapper<ShopPropertiesWorkOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("work_order_id",id).set("worker",workerId);
        return shopPropertiesWorkOrderDao.update(null,wrapper)>0;
    }

    @Override
    public IPage<ShopPropertiesWorkOrder> getFinishById(Integer pageNum, Integer pageSize, String id) {
        QueryWrapper<ShopPropertiesWorkOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("shop_id",id).eq("status","1")
                .eq("state","unmark")
                .or()
                .eq("state","over");
        return shopPropertiesWorkOrderDao.selectPage(new Page<>(pageNum, pageSize),wrapper);
    }

    @Override
    public Boolean evaluateOrder(ShopPropertiesWorkOrder order) {
        UpdateWrapper<ShopPropertiesWorkOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("work_order_id",order.getWorkOrderId())
                .set("react_speed",order.getReactSpeed())
                .set("profession_level",order.getProfessionLevel())
                .set("service_attitude",order.getServiceAttitude())
                .set("content_evaluation",order.getContentEvaluation())
                .set("content_evaluation_content",order.getContentEvaluationContent())
                .set("state","over");
        return shopPropertiesWorkOrderDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean refusedOrder(ShopPropertiesWorkOrder order) {
        UpdateWrapper<ShopPropertiesWorkOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("work_order_id",order.getWorkOrderId())
                .set("worker",order.getWorker())
                .set("cause",order.getCause())
                .set("cause_content",order.getCauseContent())
                .set("state","2");
        return shopPropertiesWorkOrderDao.update(null,wrapper)>0;
    }

    @Override
    public IPage<ShopPropertiesWorkOrder> queryUnaudit(Integer pageNum, Integer pageSize) {
        QueryWrapper<ShopPropertiesWorkOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1").eq("state","2");
        return shopPropertiesWorkOrderDao.selectPage(new Page<>(pageNum, pageSize),wrapper);
    }

    @Override
    public Map<String, Object> selByTime(String time, String id) {
        return shopPropertiesWorkOrderDao.getByTime(id,time);
    }

    @Override
    public Map<String, Object> getTotalOrder(String id) {
        return shopPropertiesWorkOrderDao.getTotalOrder(id);
    }

    @Override
    public Map<String, Object> getTotalRewardOrder(String id) {
        return shopPropertiesWorkOrderDao.getTotalRewardOrder(id);
    }

    @Override
    public Map<String, Object> getTotalTelOrder(String id) {
        return shopPropertiesWorkOrderDao.getTotalTelOrder(id);
    }

    @Override
    public int getCountOrderById(String shopId) {
        QueryWrapper<ShopPropertiesWorkOrder> wrapper = new QueryWrapper<>();
        wrapper.select("count(*) as nums,").eq("shop_id",shopId);
        return shopPropertiesWorkOrderDao.selectCount(wrapper);
    }

}