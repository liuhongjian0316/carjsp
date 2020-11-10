package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.ShopPropertiesCustomerService;
import work.aijiu.dao.ShopPropertiesCustomerServiceDao;
import work.aijiu.service.ShopPropertiesCustomerServiceService;
import org.springframework.stereotype.Service;
import work.aijiu.service.ShopPropertiesShopService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesCustomerService)表服务实现类
 *
 * @author makejava
 * @since 2020-10-22 13:31:34
 */
@Service("shopPropertiesCustomerServiceService")
public class ShopPropertiesCustomerServiceServiceImpl implements ShopPropertiesCustomerServiceService {
    @Autowired
    private ShopPropertiesCustomerServiceDao shopPropertiesCustomerServiceDao;

    @Override
    public IPage<Map<String, Object>> queryPage(Integer pageNum, Integer pageSize, String terms) {
        return shopPropertiesCustomerServiceDao.queryPage(new Page<>(pageNum, pageSize),"%"+terms+"%");
    }

    @Override
    public Boolean addService(ShopPropertiesCustomerService customerService) {
        return shopPropertiesCustomerServiceDao.insert(customerService)>0;
    }

    @Override
    public ShopPropertiesCustomerService getById(String id) {
        QueryWrapper<ShopPropertiesCustomerService> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_service_id",id);
        wrapper.eq("status","1");
        return shopPropertiesCustomerServiceDao.selectOne(wrapper);
    }

    @Override
    public Boolean saveService(ShopPropertiesCustomerService customerService) {
        UpdateWrapper<ShopPropertiesCustomerService> wrapper = new UpdateWrapper<>();
        wrapper.eq("customer_service_id",customerService.getCustomerServiceId())
                .set("customer_service_name",customerService.getCustomerServiceName())
                .set("organize_id",customerService.getOrganizeId())
                .set("real_name",customerService.getRealName())
                .set("sex",customerService.getSex())
                .set("tel",customerService.getTel())
                .set("address",customerService.getAddress())
                .set("role_id",customerService.getRoleId())
                .set("remark",customerService.getRemark());
        return shopPropertiesCustomerServiceDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean deleteService(String id) {
        UpdateWrapper<ShopPropertiesCustomerService> wrapper = new UpdateWrapper<>();
        wrapper.eq("customer_service_id",id)
                .set("status","0");
        return shopPropertiesCustomerServiceDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean resetPwd(String id, String pwd) {
        UpdateWrapper<ShopPropertiesCustomerService> wrapper = new UpdateWrapper<>();
        wrapper.eq("customer_service_id",id)
                .set("pwd",pwd);
        return shopPropertiesCustomerServiceDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean checkAddTel(String tel) {
        QueryWrapper<ShopPropertiesCustomerService> wrapper = new QueryWrapper<>();
        wrapper.eq("tel",tel).eq("status","1");
        return shopPropertiesCustomerServiceDao.selectList(wrapper).size()<=0;
    }

    @Override
    public Boolean checkEditTel(String tel, String id) {
        QueryWrapper<ShopPropertiesCustomerService> wrapper = new QueryWrapper<>();
        wrapper.eq("tel",tel).eq("status","1").ne("customer_service_id",id);
        return shopPropertiesCustomerServiceDao.selectList(wrapper).size()<=0;
    }

    @Override
    public List<ShopPropertiesCustomerService> login(String username, String pwd) {
        QueryWrapper<ShopPropertiesCustomerService> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username).eq("pwd",pwd).eq("status","1");
        return shopPropertiesCustomerServiceDao.selectList(wrapper);
    }

    @Override
    public int countNumOfService() {
        QueryWrapper<ShopPropertiesCustomerService> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1");
        return shopPropertiesCustomerServiceDao.selectCount(wrapper);
    }
}