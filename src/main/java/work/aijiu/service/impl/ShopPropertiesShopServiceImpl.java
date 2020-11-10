package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.entity.ShopPropertiesShop;
import work.aijiu.dao.ShopPropertiesShopDao;
import work.aijiu.entity.ShopPropertiesWorkOrder;
import work.aijiu.service.ShopPropertiesShopService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesShop)表服务实现类
 *
 * @author makejava
 * @since 2020-10-20 10:24:55
 */
@Service("shopPropertiesShopService")
public class ShopPropertiesShopServiceImpl implements ShopPropertiesShopService {
    @Autowired
    private ShopPropertiesShopDao shopPropertiesShopDao;

    @Override
    public IPage<ShopPropertiesShop> queryPage(Integer pageNum, Integer pageSize) {
        return shopPropertiesShopDao.queryPage(new Page<>(pageNum, pageSize));
    }

    @Override
    public Boolean addShop(ShopPropertiesShop shopPropertiesShop) {
        return shopPropertiesShopDao.insert(shopPropertiesShop)>0;
    }

    @Override
    public ShopPropertiesShop getById(String id) {
        QueryWrapper<ShopPropertiesShop> wrapper = new QueryWrapper<>();
        wrapper.eq("shop_id",id);
        return shopPropertiesShopDao.selectOne(wrapper);
    }

    @Override
    public Boolean save(ShopPropertiesShop shopPropertiesShop) {
        UpdateWrapper<ShopPropertiesShop> wrapper = new UpdateWrapper<>();
        wrapper.eq("shop_id",shopPropertiesShop.getShopId())
                .set("name",shopPropertiesShop.getName())
                .set("tel",shopPropertiesShop.getTel())
                .set("realname",shopPropertiesShop.getRealname())
                .set("address",shopPropertiesShop.getAddress())
                .set("landmarks",shopPropertiesShop.getLandmarks())
                .set("remark",shopPropertiesShop.getRemark());
        return shopPropertiesShopDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean delete(String id) {
        UpdateWrapper<ShopPropertiesShop> wrapper = new UpdateWrapper<>();
        wrapper.eq("shop_id",id)
                .set("status","0");
        return shopPropertiesShopDao.update(null,wrapper)>0;
    }

    @Override
    public IPage<ShopPropertiesShop> queryLike(Integer pageNum, Integer pageSize,
                                               ShopPropertiesShop shopPropertiesShop) {
        QueryWrapper<ShopPropertiesShop> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1");
        if(!StringUtils.isEmpty(shopPropertiesShop.getShopId())){
            wrapper.like("shop_id", shopPropertiesShop.getShopId());
        }
        if(!StringUtils.isEmpty(shopPropertiesShop.getName())){
            wrapper.like("name", shopPropertiesShop.getName());
        }
        if(!StringUtils.isEmpty(shopPropertiesShop.getTel())){
            wrapper.like("tel", shopPropertiesShop.getTel());
        }
        if(!StringUtils.isEmpty(shopPropertiesShop.getAddress())){
            wrapper.like("address", shopPropertiesShop.getAddress());
        }
        return shopPropertiesShopDao.selectPage(new Page<>(pageNum, pageSize),wrapper);
    }

    @Override
    public Boolean checkTel(String tel) {
        QueryWrapper<ShopPropertiesShop> wrapper = new QueryWrapper<>();
        wrapper.eq("tel",tel)
                .eq("status","1");
        return shopPropertiesShopDao.selectList(wrapper).size()>0;
    }

    @Override
    public Boolean checkEdit(String tel, String id) {
        QueryWrapper<ShopPropertiesShop> wrapper = new QueryWrapper<>();
        wrapper.eq("tel",tel)
                .eq("status","1")
                .ne("shop_id",id);
        return shopPropertiesShopDao.selectList(wrapper).size()>0;
    }

    @Override
    public List<ShopPropertiesShop> login(String username, String pwd) {
        QueryWrapper<ShopPropertiesShop> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username)
                .eq("pwd",pwd)
                .eq("status","1");
        return shopPropertiesShopDao.selectList(wrapper);
    }

    @Override
    public List<Map<String, Object>> countShopByAddress() {
        QueryWrapper<ShopPropertiesShop> wrapper = new QueryWrapper<>();
        wrapper.select("left(address,2) as 位置,count(*) as 数量")
                .eq("status","1").groupBy("address");
        return shopPropertiesShopDao.selectMaps(wrapper);
    }
}