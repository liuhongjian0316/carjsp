package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.Community;
import work.aijiu.dao.CommunityDao;
import work.aijiu.service.CommunityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Community)表服务实现类
 *
 * @author makejava
 * @since 2020-10-12 16:46:57
 */
@Service("communityService")
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityDao communityDao;

    @Override
    public IPage<Community> queryPage(Integer pageNum, Integer pageSize) {
        return communityDao.queryPage(new Page<>(pageNum, pageSize));
    }

    @Override
    public IPage<Community> queryUnauditedPage(Integer pageNum, Integer pageSize) {
        return communityDao.queryUnaudited(new Page<>(pageNum, pageSize));
    }

    @Override
    public Boolean insert(Community community) {
        return communityDao.insert(community)>0;
    }

    @Override
    public Community getById(String id) {
        QueryWrapper<Community> wrapper = new QueryWrapper();
        wrapper.eq("community_id",id);
        return communityDao.selectOne(wrapper);
    }

    @Override
    public Boolean edit(Community community) {
        UpdateWrapper<Community> wrapper = new UpdateWrapper<>();
        wrapper.eq("community_id",community.getCommunityId());
        return communityDao.update(community,wrapper)>0;
    }

    @Override
    public Boolean auditreturn(String id) {
        UpdateWrapper<Community> wrapper = new UpdateWrapper<>();
        wrapper.eq("community_id",id);
        wrapper.set("state","0");
        return communityDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean audit(Community community) {
        UpdateWrapper<Community> wrapper = new UpdateWrapper<>();
        wrapper.eq("community_id",community.getCommunityId());
        wrapper.set("state",community.getState());
        wrapper.set("remark",community.getRemark());
        return communityDao.update(null,wrapper)>0;
    }

    @Override
    public Boolean delete(String id) {
        UpdateWrapper<Community> wrapper = new UpdateWrapper<>();
        wrapper.eq("community_id",id);
        wrapper.set("status","0");
        return communityDao.update(null,wrapper)>0;
    }
}