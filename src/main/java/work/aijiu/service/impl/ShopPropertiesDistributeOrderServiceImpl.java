package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.ShopPropertiesDistributeOrder;
import work.aijiu.dao.ShopPropertiesDistributeOrderDao;
import work.aijiu.service.ShopPropertiesDistributeOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesDistributeOrder)表服务实现类
 *
 * @author makejava
 * @since 2020-10-26 18:30:25
 */
@Service("shopPropertiesDistributeOrderService")
public class ShopPropertiesDistributeOrderServiceImpl implements ShopPropertiesDistributeOrderService {
    @Autowired
    private ShopPropertiesDistributeOrderDao shopPropertiesDistributeOrderDao;

    @Override
    public Boolean addOrder(ShopPropertiesDistributeOrder order) {
        return shopPropertiesDistributeOrderDao.insert(order)>0;
    }

    @Override
    public IPage<ShopPropertiesDistributeOrder> getBySelfId(Integer pageNum, Integer pageSize, String id) {
        QueryWrapper<ShopPropertiesDistributeOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("state","1").eq("status","1")
                .apply(id != null,"FIND_IN_SET ("+id+",worker)");
        return shopPropertiesDistributeOrderDao.selectPage(new Page<>(pageNum, pageSize),wrapper);
    }

    @Override
    public ShopPropertiesDistributeOrder getDetailsById(String id) {
        QueryWrapper<ShopPropertiesDistributeOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("distribute_order_id",id);
        return shopPropertiesDistributeOrderDao.selectOne(wrapper);
    }

    @Override
    public Boolean updateStateById(String id, String state) {
        UpdateWrapper<ShopPropertiesDistributeOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("distribute_order_id",id).set("state",state);
        return shopPropertiesDistributeOrderDao.update(null,wrapper)>0;
    }

    @Override
    public IPage<ShopPropertiesDistributeOrder> getHandlingBySelfId(Integer pageNum, Integer pageSize, String id) {
        QueryWrapper<ShopPropertiesDistributeOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("state","3").eq("status","1").
                apply(id != null,"FIND_IN_SET ("+id+",worker)");
        return shopPropertiesDistributeOrderDao.selectPage(new Page<>(pageNum, pageSize),wrapper);
    }

    @Override
    public Boolean updateStateByOrderId(String id,String state) {
        UpdateWrapper<ShopPropertiesDistributeOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("work_order_id",id).set("state",state);
        return shopPropertiesDistributeOrderDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean updateReinforceById(String id, String state) {
        UpdateWrapper<ShopPropertiesDistributeOrder> wrapper= new UpdateWrapper<>();
        wrapper.eq("distribute_order_id",id).set("is_reinforce",state);
        return shopPropertiesDistributeOrderDao.update(null,wrapper)>0;
    }

    @Override
    public IPage<ShopPropertiesDistributeOrder> queryUnReinforce(Integer pageNum, Integer pageSize) {
        QueryWrapper<ShopPropertiesDistributeOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1").eq("is_reinforce","1");
        return shopPropertiesDistributeOrderDao.selectPage(new Page<>(pageNum, pageSize),wrapper);
    }

    @Override
    public ShopPropertiesDistributeOrder getByid(String id) {
        QueryWrapper<ShopPropertiesDistributeOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("distribute_order_id",id);
        return shopPropertiesDistributeOrderDao.selectOne(wrapper);
    }

    @Override
    public Boolean reinforceWorker(String id, String workers) {
        UpdateWrapper<ShopPropertiesDistributeOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("distribute_order_id",id).set("worker",workers);
        return shopPropertiesDistributeOrderDao.update(null,wrapper)>0;
    }

    @Override
    public List<ShopPropertiesDistributeOrder> selALl() {
        QueryWrapper<ShopPropertiesDistributeOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1").eq("state","3");
        return shopPropertiesDistributeOrderDao.selectList(wrapper);
    }

    @Override
    public Boolean updateRsseinforceStateByDid(String id, String state) {
        UpdateWrapper<ShopPropertiesDistributeOrder> wrapper = new UpdateWrapper<>();
        wrapper.eq("distribute_order_id",id).set("is_reinforce",state);
        return shopPropertiesDistributeOrderDao.update(null,wrapper)>0;
    }

    @Override
    public IPage<ShopPropertiesDistributeOrder> queryUnRecall(Integer pageNum, Integer pageSize) {
        QueryWrapper<ShopPropertiesDistributeOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("state","4");
        return shopPropertiesDistributeOrderDao.selectPage(new Page<>(pageNum, pageSize),wrapper);
    }

    @Override
    public Map<String, Object> repairOrderByTime(String id, String time) {
        return shopPropertiesDistributeOrderDao.repairOrderByTime(id,time);
    }

    @Override
    public int totalOrders(String id) {
        return shopPropertiesDistributeOrderDao.totalOrders(id);
    }

    @Override
    public List<Map<String,Object>> reparirTypeOrder(String id) {
        return shopPropertiesDistributeOrderDao.reparirTypeOrder(id);
    }

    @Override
    public Map<String, Object> countOrder(String id) {
        return shopPropertiesDistributeOrderDao.countOrder(id);
    }

    @Override
    public Map<String, Object> countCsOrder(String id) {
        return shopPropertiesDistributeOrderDao.countCsOrder(id);
    }

    @Override
    public Map<String, Object> countRwOrder(String id) {
        return shopPropertiesDistributeOrderDao.countRwOrder(id);
    }

    @Override
    public int numOfOrderReact(String id) {
        return shopPropertiesDistributeOrderDao.numOfOrderReact(id);
    }

    @Override
    public int numOfOrderProfession(String id) {
        return shopPropertiesDistributeOrderDao.numOfOrderProfession(id);
    }

    @Override
    public int numOfOrderAttitude(String id) {
        return shopPropertiesDistributeOrderDao.numOfOrderAttitude(id);
    }

    @Override
    public int numOfCsOrderReact(String id) {
        return shopPropertiesDistributeOrderDao.numOfCsOrderReact(id);
    }

    @Override
    public int numOfCsOrderProfession(String id) {
        return shopPropertiesDistributeOrderDao.numOfCsOrderProfession(id);
    }

    @Override
    public int numOfCsOrderAttitude(String id) {
        return shopPropertiesDistributeOrderDao.numOfCsOrderAttitude(id);
    }

    @Override
    public int numOfRwOrderReact(String id) {
        return shopPropertiesDistributeOrderDao.numOfRwOrderReact(id);
    }

    @Override
    public int numOfRwOrderProfession(String id) {
        return shopPropertiesDistributeOrderDao.numOfRwOrderProfession(id);
    }

    @Override
    public int numOfRwOrderAttitude(String id) {
        return shopPropertiesDistributeOrderDao.numOfRwOrderAttitude(id);
    }

    @Override
    public int yesterdayEarnings(String id) {
        Date today = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //获取昨天日期
        String yesterday = simpleDateFormat.format(today);
        return shopPropertiesDistributeOrderDao.repairEarnByTime(id,yesterday);
    }

    @Override
    public int monthEarnings(String id) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String endTime = simpleDateFormat.format(now);
        Date beginTime2 = DateUtils.addDays(now, -30);
        String beginTime = simpleDateFormat.format(beginTime2);
        return shopPropertiesDistributeOrderDao.repairEarnBetweenTime(id,beginTime,endTime);
    }

    @Override
    public int currentEarnings(String id) {
        //获取本月第一天
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date time1 = calendar.getTime();
        //获取最后第一天
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        calendar2.set(Calendar.DAY_OF_MONTH, calendar2.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date time2 = calendar2.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        String beginTime = simpleDateFormat.format(time1);
        String endTime = simpleDateFormat.format(time2);
        return shopPropertiesDistributeOrderDao.repairEarnBetweenTime(id,beginTime,endTime);
    }

    @Override
    public int totalEarnings(String id) {
        return shopPropertiesDistributeOrderDao.totalEarn(id);
    }

    @Override
    public int canCarryEarnings(String id) {
        return shopPropertiesDistributeOrderDao.totalEarn(id);
    }

    @Override
    public int repairEarnByTime(String id, String time) {
        return shopPropertiesDistributeOrderDao.repairEarnByTime(id,time);
    }

    @Override
    public Map<String, Object> adminOrderByTime(String time) {
        return shopPropertiesDistributeOrderDao.adminOrderByTime(time);
    }


}