package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.LandlordsChild;
import work.aijiu.dao.LandlordsChildDao;
import work.aijiu.service.LandlordsChildService;
import org.springframework.stereotype.Service;


/**
 * (LandlordsChild)表服务实现类
 *
 * @author makejava
 * @since 2020-10-18 23:50:16
 */
@Service("landlordsChildService")
public class LandlordsChildServiceImpl implements LandlordsChildService {
    @Autowired
    private LandlordsChildDao landlordsChildDao;

    @Override
    public IPage<LandlordsChild> queryPageByPid(Integer pageNum, Integer pageSize, String pid) {
        return landlordsChildDao.queryPage(new Page<>(pageNum, pageSize),pid);
    }

    @Override
    public Boolean insertLandordsChild(LandlordsChild landlordsChild) {
        return landlordsChildDao.insert(landlordsChild)>0;
    }

    @Override
    public LandlordsChild getChildById(String id) {
        QueryWrapper<LandlordsChild> wrapper = new QueryWrapper<>();
        wrapper.eq("landlords_child_id",id);
        return landlordsChildDao.selectOne(wrapper);
    }

    @Override
    public Boolean save(LandlordsChild landlordsChild) {
        UpdateWrapper<LandlordsChild> wrapper = new UpdateWrapper<>();
        wrapper.eq("landlords_child_id",landlordsChild.getLandlordsChildId())
                .set("landlords_child_type",landlordsChild.getLandlordsChildType())
                .set("landlords_child_name",landlordsChild.getLandlordsChildName())
                .set("landlords_child_sex",landlordsChild.getLandlordsChildSex())
                .set("landlords_child_age",landlordsChild.getLandlordsChildAge())
                .set("landlords_child_card",landlordsChild.getLandlordsChildCard())
                .set("landlords_child_tel",landlordsChild.getLandlordsChildTel())
                .set("landlords_child_wx",landlordsChild.getLandlordsChildWx())
                .set("landlords_child_qq",landlordsChild.getLandlordsChildQq())
                .set("landlords_child_avatar",landlordsChild.getLandlordsChildAvatar())
                .set("remark",landlordsChild.getRemark());
        return landlordsChildDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean delete(String id) {
        UpdateWrapper<LandlordsChild> wrapper = new UpdateWrapper<>();
        wrapper.eq("landlords_child_id",id)
                .set("status","0");
        return landlordsChildDao.update(null,wrapper)>0;
    }
}