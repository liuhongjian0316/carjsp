package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.ShopPropertiesRepairWorker;
import work.aijiu.entity.ShopPropertiesRepairWorkerType;
import work.aijiu.dao.ShopPropertiesRepairWorkerTypeDao;
import work.aijiu.entity.ShopPropertiesWorkType;
import work.aijiu.service.ShopPropertiesRepairWorkerTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ShopPropertiesRepairWorkerType)表服务实现类
 *
 * @author makejava
 * @since 2020-10-21 15:26:26
 */
@Service("shopPropertiesRepairWorkerTypeService")
public class ShopPropertiesRepairWorkerTypeServiceImpl implements ShopPropertiesRepairWorkerTypeService {
    @Autowired
    private ShopPropertiesRepairWorkerTypeDao shopPropertiesRepairWorkerTypeDao;


    @Override
    public ShopPropertiesRepairWorkerType getById(String id) {
        QueryWrapper<ShopPropertiesRepairWorkerType> wrapper = new QueryWrapper<>();
        wrapper.eq("repair_worker_type_id",id);
        wrapper.eq("status","1");
        return shopPropertiesRepairWorkerTypeDao.selectOne(wrapper);
    }

    @Override
    public List<ShopPropertiesRepairWorkerType> getAll() {
        QueryWrapper<ShopPropertiesRepairWorkerType> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1");
        return shopPropertiesRepairWorkerTypeDao.selectList(wrapper);
    }

    @Override
    public IPage<ShopPropertiesRepairWorkerType> queryPage(Integer pageNum, Integer pageSize, String type) {
        QueryWrapper<ShopPropertiesRepairWorkerType> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1");
        if(!StringUtils.isEmpty(type)){
            wrapper.like("repair_worker_type_name", type);
        }
        return shopPropertiesRepairWorkerTypeDao.selectPage(new Page<>(pageNum, pageSize),wrapper);
    }

    @Override
    public Boolean add(ShopPropertiesRepairWorkerType type) {
        return shopPropertiesRepairWorkerTypeDao.insert(type)>0;
    }

    @Override
    public Boolean save(ShopPropertiesRepairWorkerType type) {
        UpdateWrapper<ShopPropertiesRepairWorkerType> wrapper = new UpdateWrapper<>();
        wrapper.eq("repair_worker_type_id",type.getRepairWorkerTypeId())
                .set("repair_worker_type_name",type.getRepairWorkerTypeName())
                .set("remark",type.getRemark());
        return shopPropertiesRepairWorkerTypeDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean deleteById(String id) {
        UpdateWrapper<ShopPropertiesRepairWorkerType> wrapper = new UpdateWrapper<>();
        wrapper.eq("repair_worker_type_id",id)
                .set("status","0");
        return shopPropertiesRepairWorkerTypeDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean checkAddType(String name) {
        QueryWrapper<ShopPropertiesRepairWorkerType> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1").eq("repair_worker_type_name",name);
        return shopPropertiesRepairWorkerTypeDao.selectList(wrapper).size()<=0;
    }

    @Override
    public Boolean checkEditType(String name, String id) {
        QueryWrapper<ShopPropertiesRepairWorkerType> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1").eq("repair_worker_type_name",name)
        .ne("repair_worker_type_id",id);
        return shopPropertiesRepairWorkerTypeDao.selectList(wrapper).size()<=0;
    }


}