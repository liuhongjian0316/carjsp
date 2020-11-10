package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.ShopPropertiesRewardWorkOrder;
import work.aijiu.dao.ShopPropertiesRewardWorkOrderDao;
import work.aijiu.entity.ShopPropertiesWorkOrder;
import work.aijiu.service.ShopPropertiesRewardWorkOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesRewardWorkOrder)表服务实现类
 *
 * @author makejava
 * @since 2020-11-01 10:39:58
 */
@Service("shopPropertiesRewardWorkOrderService")
public class ShopPropertiesRewardWorkOrderServiceImpl implements ShopPropertiesRewardWorkOrderService {
    @Autowired
    private ShopPropertiesRewardWorkOrderDao shopPropertiesRewardWorkOrderDao;


    @Override
    public Boolean publishRepairOrder(ShopPropertiesRewardWorkOrder order) {
        return shopPropertiesRewardWorkOrderDao.insert(order)>0;
    }

    @Override
    public IPage<Map<String,Object>> queryHandling(Integer pageNum, Integer pageSize, String shopId) {
        return shopPropertiesRewardWorkOrderDao.queryHandling(new Page<>(pageNum,pageSize),shopId);
    }

    @Override
    public ShopPropertiesRewardWorkOrder getById(String id) {
        QueryWrapper<ShopPropertiesRewardWorkOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("reward_work_oder_id",id);
        return shopPropertiesRewardWorkOrderDao.selectOne(wrapper);
    }

    @Override
    public Boolean deleteById(String id) {
        UpdateWrapper<ShopPropertiesRewardWorkOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("reward_work_oder_id",id).set("status","0");
        return shopPropertiesRewardWorkOrderDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean saveOrder(ShopPropertiesRewardWorkOrder order) {
        UpdateWrapper<ShopPropertiesRewardWorkOrder> wrapper =new UpdateWrapper<>();
        wrapper.eq("reward_work_oder_id",order.getRewardWorkOderId())
                .set("work_type_id",order.getWorkTypeId())
                .set("work_number",order.getWorkNumber())
                .set("apply_name",order.getApplyName())
                .set("tel",order.getTel())
                .set("create_time",order.getCreateTime())
                .set("address",order.getAddress())
                .set("remark",order.getRemark())
                .set("fault_description",order.getFaultDescription())
                .set("fault_description_content",order.getFaultDescriptionContent());
        return shopPropertiesRewardWorkOrderDao.update(null,wrapper)>0;
    }

    @Override
    public IPage<Map<String, Object>> queryUngrabOrderList(Integer pageNum, Integer pageSize) {
        return shopPropertiesRewardWorkOrderDao.queryUngrabOrderList(new Page<>(pageNum,pageSize));
    }

    @Override
    public Boolean updateState(String id, String state) {
        UpdateWrapper<ShopPropertiesRewardWorkOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("reward_work_oder_id",id)
                .set("state",state);
        return shopPropertiesRewardWorkOrderDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean updateWorker(String id, String worker) {
        UpdateWrapper<ShopPropertiesRewardWorkOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("reward_work_oder_id",id)
                .set("worker",worker);
        return shopPropertiesRewardWorkOrderDao.update(null,wrapper)>0;
    }

    @Override
    public IPage<Map<String, Object>> queryRWHandling(Integer pageNum, Integer pageSize, String worker) {
        return shopPropertiesRewardWorkOrderDao.queryRWHandling(new Page<>(pageNum,pageSize),worker);
    }

    @Override
    public int getCountOrderById(String shopId) {
        QueryWrapper<ShopPropertiesRewardWorkOrder> wrapper = new QueryWrapper<>();
        wrapper.select("count(*) as nums,").eq("shop_id",shopId);
        return shopPropertiesRewardWorkOrderDao.selectCount(wrapper);
    }
}