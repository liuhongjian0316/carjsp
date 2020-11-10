package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.ShopPropertiesRepairWorker;
import work.aijiu.dao.ShopPropertiesRepairWorkerDao;
import work.aijiu.entity.ShopPropertiesShop;
import work.aijiu.service.ShopPropertiesRepairWorkerService;
import org.springframework.stereotype.Service;
import work.aijiu.service.ShopPropertiesRepairWorkerTypeService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesRepairWorker)表服务实现类
 *
 * @author makejava
 * @since 2020-10-21 15:25:02
 */
@Service("shopPropertiesRepairWorkerService")
public class ShopPropertiesRepairWorkerServiceImpl implements ShopPropertiesRepairWorkerService {
    @Autowired
    private ShopPropertiesRepairWorkerDao shopPropertiesRepairWorkerDao;

    @Override
    public IPage<HashMap<String, Object>> queryPage(Integer pageNum, Integer pageSize,
                                                    String trems) {
        return shopPropertiesRepairWorkerDao.queryPage(new Page<>(pageNum, pageSize),"%"+trems+"%");
    }

    @Override
    public Boolean addWorker(ShopPropertiesRepairWorker shopPropertiesRepairWorker) {
        return shopPropertiesRepairWorkerDao.insert(shopPropertiesRepairWorker)>0;
    }

    @Override
    public Boolean checkAddWorkerTel(String tel) {
        QueryWrapper<ShopPropertiesRepairWorker> wrapper = new QueryWrapper<>();
        wrapper.eq("tel",tel);
        return shopPropertiesRepairWorkerDao.selectList(wrapper).size()<=0;
    }

    @Override
    public Boolean checkAddWorkerCard(String card) {
        QueryWrapper<ShopPropertiesRepairWorker> wrapper = new QueryWrapper<>();
        wrapper.eq("card",card);
        return shopPropertiesRepairWorkerDao.selectList(wrapper).size()<=0;
    }

    @Override
    public ShopPropertiesRepairWorker getById(String id) {
        QueryWrapper<ShopPropertiesRepairWorker> wrapper = new QueryWrapper<>();
        wrapper.eq("repair_worker_id",id);
        return shopPropertiesRepairWorkerDao.selectOne(wrapper);
    }

    @Override
    public Boolean checkEditWorkerTel(String tel, String id) {
        QueryWrapper<ShopPropertiesRepairWorker> wrapper = new QueryWrapper<>();
        wrapper.eq("tel",tel).ne("repair_worker_id",id);
        return shopPropertiesRepairWorkerDao.selectList(wrapper).size()<=0;
    }

    @Override
    public Boolean checkEditWorkerCard(String card, String id) {
        QueryWrapper<ShopPropertiesRepairWorker> wrapper = new QueryWrapper<>();
        wrapper.eq("card",card).ne("repair_worker_id",id);
        return shopPropertiesRepairWorkerDao.selectList(wrapper).size()<=0;
    }

    @Override
    public Boolean saveWorker(ShopPropertiesRepairWorker worker) {
        UpdateWrapper<ShopPropertiesRepairWorker> wrapper = new UpdateWrapper<>();
        wrapper.eq("repair_worker_id",worker.getRepairWorkerId())
                .set("real_name",worker.getRealName())
                .set("sex",worker.getSex())
                .set("age",worker.getAge())
                .set("tel",worker.getTel())
                .set("card",worker.getCard())
                .set("address",worker.getAddress())
                .set("repair_worker_type_id",worker.getRepairWorkerId())
                .set("organize_id",worker.getOrganizeId())
                .set("role_id",worker.getRoleId())
                .set("remark",worker.getRemark());
        return shopPropertiesRepairWorkerDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean deleteWorkerById(String id) {
        UpdateWrapper<ShopPropertiesRepairWorker> wrapper = new UpdateWrapper<>();
        wrapper.eq("repair_worker_id",id).set("status","0");
        return shopPropertiesRepairWorkerDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean resetpwd(String id, String pwd) {
        UpdateWrapper<ShopPropertiesRepairWorker> wrapper = new UpdateWrapper<>();
        wrapper.eq("repair_worker_id",id).set("pwd",pwd);
        return shopPropertiesRepairWorkerDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean updateState(String id, String state) {
        UpdateWrapper<ShopPropertiesRepairWorker> wrapper = new UpdateWrapper<>();
        wrapper.eq("repair_worker_id",id).set("state",state);
        return shopPropertiesRepairWorkerDao.update(null,wrapper)>0;
    }

    @Override
    public List<ShopPropertiesRepairWorker> login(String username, String pwd) {
        QueryWrapper<ShopPropertiesRepairWorker> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",username).eq("pwd",pwd)
                .eq("status","1");
        return shopPropertiesRepairWorkerDao.selectList(wrapper);
    }

    @Override
    public List<Map<String, Object>> countRepairByAddress() {
        QueryWrapper<ShopPropertiesRepairWorker> wrapper = new QueryWrapper<>();
        wrapper.select("left(address,2) as 位置,count(*) as 数量")
                .eq("status","1").groupBy("address");
        return shopPropertiesRepairWorkerDao.selectMaps(wrapper);
    }

    @Override
    public List<Map<String, Object>> countRepairBySex() {
        QueryWrapper<ShopPropertiesRepairWorker> wrapper = new QueryWrapper<>();
        wrapper.select("if(sex='0','男','女' ) as 性别,count(sex) as 人数")
                .eq("status","1").groupBy("sex");
        return shopPropertiesRepairWorkerDao.selectMaps(wrapper);
    }

    @Override
    public int countRepairBetweenAge(Integer younger, Integer older) {
        QueryWrapper<ShopPropertiesRepairWorker> wrapper = new QueryWrapper<>();
        wrapper
                .eq("status","1")
                .gt("age",younger)
                .le("age",older);
//                .and(i->i.gt("age",younger)
//                        .le("age",older));
        return shopPropertiesRepairWorkerDao.selectCount(wrapper);
    }
}