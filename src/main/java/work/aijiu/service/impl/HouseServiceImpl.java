package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.entity.Building;
import work.aijiu.entity.House;
import work.aijiu.dao.HouseDao;
import work.aijiu.service.HouseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (House)表服务实现类
 *
 * @author makejava
 * @since 2020-10-16 19:47:10
 */
@Service("houseService")
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseDao houseDao;

    @Override
    public IPage<Map<String,Object>> queryPage(Integer pageNum, Integer pageSize, String cid) {
        return houseDao.queryPage(new Page<>(pageNum, pageSize),cid);
    }

    @Override
    public Boolean addHouse(House house) {
        return houseDao.insert(house)>0;
    }

    @Override
    public House getById(String id) {
        QueryWrapper<House> wrapper = new QueryWrapper<>();
        wrapper.eq("house_id",id);
        return houseDao.selectOne(wrapper);
    }

    @Override
    public Boolean save(House house) {
        UpdateWrapper<House> wrapper = new UpdateWrapper<>();
        wrapper.eq("house_id",house.getHouseId())
                .set("house_num",house.getHouseNum())
                .set("house_name",house.getHouseName())
                .set("house_type",house.getHouseType())
                .set("house_area",house.getHouseArea())
                .set("house_cost_factor",house.getHouseCostFactor())
                .set("is_shop",house.getIsShop())
                .set("is_commercial_house",house.getIsCommercialHouse())
                .set("remark",house.getRemark());
        return houseDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean delete(String id) {
        UpdateWrapper<House> wrapper = new UpdateWrapper();
        wrapper.eq("house_id",id)
                .set("status","0");
        return houseDao.update(null,wrapper)>0;
    }
}