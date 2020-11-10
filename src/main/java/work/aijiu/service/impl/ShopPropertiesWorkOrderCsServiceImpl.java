package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.ShopPropertiesWorkOrder;
import work.aijiu.entity.ShopPropertiesWorkOrderCs;
import work.aijiu.dao.ShopPropertiesWorkOrderCsDao;
import work.aijiu.service.ShopPropertiesWorkOrderCsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesWorkOrderCs)表服务实现类
 *
 * @author makejava
 * @since 2020-10-30 10:04:07
 */
@Service("shopPropertiesWorkOrderCsService")
public class ShopPropertiesWorkOrderCsServiceImpl implements ShopPropertiesWorkOrderCsService {
    @Autowired
    private ShopPropertiesWorkOrderCsDao shopPropertiesWorkOrderCsDao;

    @Override
    public Boolean applyRepairCs(ShopPropertiesWorkOrderCs order) {
        return shopPropertiesWorkOrderCsDao.insert(order)>0;
    }

    @Override
    public IPage<Map<String, Object>> queryHandling(Integer pageNum, Integer pageSize) {
        return shopPropertiesWorkOrderCsDao.queryHandling(new Page<>(pageNum,pageSize));
    }

    @Override
    public ShopPropertiesWorkOrderCs selectByOrderId(String id) {
        QueryWrapper<ShopPropertiesWorkOrderCs> wrapper = new QueryWrapper<>();
        wrapper.eq("work_order_cs_id",id);
        return shopPropertiesWorkOrderCsDao.selectOne(wrapper);
    }

    @Override
    public Boolean saveTelOrder(ShopPropertiesWorkOrderCs order) {
        UpdateWrapper<ShopPropertiesWorkOrderCs> wrapper = new UpdateWrapper<>();
        wrapper.eq("work_order_cs_id",order.getWorkOrderCsId())
                .set("work_type",order.getWorkType())
                .set("shop_name",order.getShopName())
                .set("apply_name",order.getApplyName())
                .set("tel",order.getTel())
                .set("reserve_time",order.getReserveTime())
                .set("address",order.getAddress())
                .set("remark",order.getRemark())
                .set("fault_description",order.getFaultDescription())
                .set("fault_description_content",order.getFaultDescriptionContent());
        return shopPropertiesWorkOrderCsDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean deleteTelOrder(String id) {
        UpdateWrapper<ShopPropertiesWorkOrderCs> wrapper = new UpdateWrapper<>();
        wrapper.eq("work_order_cs_id",id).set("status","0");
        return shopPropertiesWorkOrderCsDao.update(null,wrapper)>0;
    }

    @Override
    public IPage<Map<String, Object>> queryUntreat(Integer pageNum, Integer pageSize) {
        return shopPropertiesWorkOrderCsDao.queryUntreat(new Page<>(pageNum,pageSize));
    }

    @Override
    public Boolean updateWorker(String id, String worker) {
        UpdateWrapper<ShopPropertiesWorkOrderCs> wrapper = new UpdateWrapper<>();
        wrapper.eq("work_order_cs_id",id).set("worker",worker);
        return shopPropertiesWorkOrderCsDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean updateState(String id, String state) {
        UpdateWrapper<ShopPropertiesWorkOrderCs> wrapper = new UpdateWrapper<>();
        wrapper.eq("work_order_cs_id",id).set("state",state);
        return shopPropertiesWorkOrderCsDao.update(null,wrapper)>0;
    }

    @Override
    public IPage<ShopPropertiesWorkOrderCs> getFinishById(Integer pageNum, Integer pageSize, String id) {
        QueryWrapper<ShopPropertiesWorkOrderCs> wrapper = new QueryWrapper<>();
        wrapper.eq("shop_id",id).eq("status","1")
                .eq("state","unmark")
                .or()
                .eq("state","over");
        return shopPropertiesWorkOrderCsDao.selectPage(new Page<>(pageNum,pageSize),wrapper);
    }

    @Override
    public Boolean evaluateOrder(ShopPropertiesWorkOrderCs order) {
        UpdateWrapper<ShopPropertiesWorkOrderCs> wrapper = new UpdateWrapper<>();
        wrapper.eq("work_order_cs_id",order.getWorkOrderCsId())
                .set("react_speed",order.getReactSpeed())
                .set("profession_level",order.getProfessionLevel())
                .set("service_attitude",order.getServiceAttitude())
                .set("content_evaluation",order.getContentEvaluation())
                .set("content_evaluation_content",order.getContentEvaluationContent())
                .set("state","over");
        return shopPropertiesWorkOrderCsDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean selectById(String id) {
        QueryWrapper<ShopPropertiesWorkOrderCs> wrapper = new QueryWrapper<>();
        wrapper.eq("work_order_cs_id",id).eq("status","1");
        return shopPropertiesWorkOrderCsDao.selectList(wrapper).size()>0;
    }

    @Override
    public Boolean refusedOrder(ShopPropertiesWorkOrderCs order) {
        UpdateWrapper<ShopPropertiesWorkOrderCs> wrapper = new UpdateWrapper<>();
        wrapper.eq("work_order_cs_id",order.getWorkOrderCsId())
                .set("worker",order.getWorker())
                .set("cause",order.getCause())
                .set("cause_content",order.getCauseContent())
                .set("state","2");
        return shopPropertiesWorkOrderCsDao.update(null,wrapper)>0;
    }

    @Override
    public IPage<ShopPropertiesWorkOrderCs> queryUnaudit(Integer pageNum, Integer pageSize) {
        QueryWrapper<ShopPropertiesWorkOrderCs> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1").eq("state","2");
        return shopPropertiesWorkOrderCsDao.selectPage(new Page<>(pageNum, pageSize),wrapper);
    }

    @Override
    public ShopPropertiesWorkOrderCs getById(String id) {
        QueryWrapper<ShopPropertiesWorkOrderCs> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1").eq("work_order_cs_id",id);
        return shopPropertiesWorkOrderCsDao.selectOne(wrapper);
    }

    @Override
    public int getCountOrderById(String shopId) {
        QueryWrapper<ShopPropertiesWorkOrderCs> wrapper = new QueryWrapper<>();
        wrapper.select("count(*) as nums,").eq("shop_id",shopId);
        return shopPropertiesWorkOrderCsDao.selectCount(wrapper);
    }

    @Override
    public int countTotalTelOrder() {
        QueryWrapper<ShopPropertiesWorkOrderCs> wrapper = new QueryWrapper<>();
        return shopPropertiesWorkOrderCsDao.selectCount(wrapper);
    }
}