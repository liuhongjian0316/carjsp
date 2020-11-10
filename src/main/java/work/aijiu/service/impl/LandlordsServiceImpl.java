package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.Landlords;
import work.aijiu.dao.LandlordsDao;
import work.aijiu.service.LandlordsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Landlords)表服务实现类
 *
 * @author makejava
 * @since 2020-10-17 16:53:36
 */
@Service("landlordsService")
public class LandlordsServiceImpl implements LandlordsService {
    @Autowired
    private LandlordsDao landlordsDao;

    @Override
    public IPage<Landlords> queryPage(Integer pageNum, Integer pageSize) {
        return landlordsDao.queryPage(new Page<>(pageNum, pageSize));
    }

    @Override
    public Boolean addLandlords(Landlords landlords) {
        return landlordsDao.insert(landlords)>0;
    }

    @Override
    public Landlords getById(String id) {
        QueryWrapper<Landlords> wrapper = new QueryWrapper<>();
        wrapper.eq("landlords_id",id);
        return landlordsDao.selectOne(wrapper);
    }

    @Override
    public Boolean save(Landlords landlords) {
        UpdateWrapper<Landlords> wrapper = new UpdateWrapper<>();
        wrapper.eq("landlords_id",landlords.getLandlordsId())
                .set("landlords_name",landlords.getLandlordsName())
                .set("landlords_sex",landlords.getLandlordsSex())
                .set("landlords_age",landlords.getLandlordsAge())
                .set("landlords_card",landlords.getLandlordsCard())
                .set("landlords_tel",landlords.getLandlordsTel())
                .set("landlords_wx",landlords.getLandlordsWx())
                .set("landlords_qq",landlords.getLandlordsQq())
                .set("landlords_avatar",landlords.getLandlordsAvatar())
                .set("remark",landlords.getRemark());
        return landlordsDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean delete(String id) {
        UpdateWrapper<Landlords> wrapper = new UpdateWrapper<>();
        wrapper.eq("landlords_id",id)
                .set("status","0");
        return landlordsDao.update(null,wrapper)>0;
    }

    @Override
    public List<Landlords> queryList() {
        QueryWrapper<Landlords> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1");
        return landlordsDao.selectList(wrapper);
    }
}