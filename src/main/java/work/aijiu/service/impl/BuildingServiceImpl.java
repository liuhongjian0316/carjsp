package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.Building;
import work.aijiu.dao.BuildingDao;
import work.aijiu.service.BuildingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Building)表服务实现类
 *
 * @author makejava
 * @since 2020-10-15 19:33:35
 */
@Service("buildingService")
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingDao buildingDao;

    @Override
    public IPage<Building> queryPage(Integer pageNum, Integer pageSize) {
        return buildingDao.queryPage(new Page<>(pageNum, pageSize));
    }

    @Override
    public Building queryById(String id) {
        QueryWrapper<Building> wrapper = new QueryWrapper<>();
        wrapper.eq("building_id",id);
        return buildingDao.selectOne(wrapper);
    }

    @Override
    public Boolean addBuilding(Building building) {
        return buildingDao.insert(building)>0;
    }

    @Override
    public Boolean save(Building building) {
        UpdateWrapper<Building> wrapper = new UpdateWrapper<>();
        wrapper.eq("building_id",building.getBuildingId());
        wrapper.set("building_num",building.getBuildingNum());
        wrapper.set("building_name",building.getBuildingName());
        wrapper.set("buiding_floor_number",building.getBuidingFloorNumber());
        wrapper.set("remark",building.getRemark());
        return buildingDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean delete(String id) {
        UpdateWrapper<Building> wrapper = new UpdateWrapper<>();
        wrapper.eq("building_id",id);
        wrapper.set("status","0");
        return buildingDao.update(null,wrapper)>0;
    }

    @Override
    public List<Building> queryListByCid(String id) {
        QueryWrapper<Building> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1")
                .eq("community_id",id);
        return buildingDao.selectList(wrapper);
    }
}