package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.ShopPropertiesWorkType;
import work.aijiu.dao.ShopPropertiesWorkTypeDao;
import work.aijiu.service.ShopPropertiesWorkTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ShopPropertiesWorkType)表服务实现类
 *
 * @author makejava
 * @since 2020-10-25 22:16:50
 */
@Service("shopPropertiesWorkTypeService")
public class ShopPropertiesWorkTypeServiceImpl implements ShopPropertiesWorkTypeService {
    @Autowired
    private ShopPropertiesWorkTypeDao shopPropertiesWorkTypeDao;

    @Override
    public List<ShopPropertiesWorkType> QueryTypeList() {
        QueryWrapper<ShopPropertiesWorkType> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1");
        return shopPropertiesWorkTypeDao.selectList(wrapper);
    }

    @Override
    public IPage<ShopPropertiesWorkType> queryPage(Integer pageNum, Integer pageSize, String type) {
        QueryWrapper<ShopPropertiesWorkType> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1");
        if(!StringUtils.isEmpty(type)){
            wrapper.like("work_type_name", type);
        }
        return shopPropertiesWorkTypeDao.selectPage(new Page<>(pageNum, pageSize),wrapper);
    }

    @Override
    public Boolean addType(ShopPropertiesWorkType type) {
        return shopPropertiesWorkTypeDao.insert(type)>0;
    }

    @Override
    public ShopPropertiesWorkType getById(String id) {
        QueryWrapper<ShopPropertiesWorkType> wrapper = new QueryWrapper<>();
        wrapper.eq("work_type_id",id).eq("status","1");
        return shopPropertiesWorkTypeDao.selectOne(wrapper);
    }

    @Override
    public Boolean deleteById(String id) {
        UpdateWrapper<ShopPropertiesWorkType> wrapper = new UpdateWrapper<>();
        wrapper.eq("work_type_id",id).set("status","0");
        return shopPropertiesWorkTypeDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean save(ShopPropertiesWorkType type) {
        UpdateWrapper<ShopPropertiesWorkType> wrapper = new UpdateWrapper<>();
        wrapper.eq("work_type_id",type.getWorkTypeId())
                .set("work_type_name",type.getWorkTypeName())
                .set("remark",type.getRemark());
        return shopPropertiesWorkTypeDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean checkAddType(String name) {
        QueryWrapper<ShopPropertiesWorkType> wrapper = new QueryWrapper<>();
        wrapper.eq("work_type_name",name).eq("status","1");
        return shopPropertiesWorkTypeDao.selectList(wrapper).size()<=0;
    }

    @Override
    public Boolean checkEditType(String name,String id) {
        QueryWrapper<ShopPropertiesWorkType> wrapper = new QueryWrapper<>();
        wrapper.eq("work_type_name",name).eq("status","1")
                .ne("work_type_id",id);
        return shopPropertiesWorkTypeDao.selectList(wrapper).size()<=0;
    }
}