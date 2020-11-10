package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.MenuUtils;
import work.aijiu.entity.Organize;
import work.aijiu.dao.OrganizeDao;
import work.aijiu.service.OrganizeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Organize)表服务实现类
 *
 * @author makejava
 * @since 2020-10-20 22:02:58
 */
@Service("organizeService")
public class OrganizeServiceImpl implements OrganizeService {
    @Autowired
    private OrganizeDao organizeDao;

    @Override
    public List<Organize> queryCurrentOrganizeById(String id) {
        return MenuUtils.buildTree3(organizeDao.selectCurrentOrganizeById(id),id);
    }

    @Override
    public Boolean checkAddName(String name, String id) {
        return organizeDao.checkAddName(name,id).size()>0;
    }

    @Override
    public Boolean addOrganize(Organize organize) {
        return organizeDao.insert(organize)>0;
    }

    @Override
    public Organize getById(String id) {
        QueryWrapper<Organize> wrapper = new QueryWrapper<>();
        wrapper.eq("organize_id",id);
        return organizeDao.selectOne(wrapper);
    }

    @Override
    public Boolean saveOrganize(Organize organize) {
        UpdateWrapper<Organize> wrapper = new UpdateWrapper<>();
        wrapper.eq("organize_id",organize.getOrganizeId())
                .set("organize_name",organize.getOrganizeName())
                .set("organize_level",organize.getOrganizeLevel())
                .set("organize_parent",organize.getOrganizeParent())
                .set("remark",organize.getRemark());
        return organizeDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean checkEditName(String name, String id, String pid) {
        return organizeDao.checkEditName(name,id,pid).size()>0;
    }

    @Override
    public Boolean deleteById(String id) {
        UpdateWrapper<Organize> wrapper = new UpdateWrapper<>();
        wrapper.eq("organize_id",id)
                .set("status","0");
        return organizeDao.update(null,wrapper)>0;
    }
}