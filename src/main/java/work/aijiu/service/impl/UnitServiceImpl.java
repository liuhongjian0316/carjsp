package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.Unit;
import work.aijiu.dao.UnitDao;
import work.aijiu.service.UnitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.List;

/**
 * (Unit)表服务实现类
 *
 * @author makejava
 * @since 2020-10-16 09:12:14
 */
@Service("unitService")
public class UnitServiceImpl implements UnitService {
    @Autowired
    private UnitDao unitDao;

    @Override
    public IPage<Unit> queryPage(Integer pageNum, Integer pageSize,String cid,String bid) {
        return unitDao.queryPage(new Page<>(pageNum, pageSize),cid,bid);
    }

    @Override
    public Boolean add(Unit unit) {
        return unitDao.insert(unit)>0;
    }

    @Override
    public Unit getById(String id) {
        QueryWrapper<Unit> wrapper = new QueryWrapper<>();
        wrapper.eq("unit_id",id);
        return unitDao.selectOne(wrapper);
    }

    @Override
    public Boolean save(Unit unit) {
        UpdateWrapper<Unit> wrapper = new UpdateWrapper<>();
        wrapper.eq("unit_id",unit.getUnitId());
        wrapper.set("unit_name",unit.getUnitName());
        wrapper.set("unit_number",unit.getUnitNumber());
        wrapper.set("haslift",unit.getHaslift());
        wrapper.set("unit_floor_number",unit.getUnitFloorNumber());
        wrapper.set("remark",unit.getRemark());
        return unitDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean delete(String id) {
        UpdateWrapper<Unit> wrapper = new UpdateWrapper<>();
        wrapper.eq("unit_id",id);
        wrapper.set("status","0");
        return unitDao.update(null,wrapper)>0;
    }

    @Override
    public List<Unit> queryByCidBid(String cid, String bid) {
        QueryWrapper<Unit> wrapper = new QueryWrapper<>();
        wrapper.eq("community_id",cid)
                .eq("building_id",bid)
                .eq("status","1");
        return unitDao.selectList(wrapper);
    }

}