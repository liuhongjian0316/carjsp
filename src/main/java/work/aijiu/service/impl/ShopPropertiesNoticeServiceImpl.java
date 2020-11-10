package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.ShopPropertiesNotice;
import work.aijiu.dao.ShopPropertiesNoticeDao;
import work.aijiu.service.ShopPropertiesNoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesNotice)表服务实现类
 *
 * @author makejava
 * @since 2020-11-04 22:30:09
 */
@Service("shopPropertiesNoticeService")
public class ShopPropertiesNoticeServiceImpl implements ShopPropertiesNoticeService {
    @Autowired
    private ShopPropertiesNoticeDao shopPropertiesNoticeDao;

    @Override
    public IPage<ShopPropertiesNotice> queryPage(Integer pageNum, Integer pageSize, ShopPropertiesNotice notice) {
        QueryWrapper<ShopPropertiesNotice> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1");
        if(!StringUtils.isEmpty(notice.getState())){
            wrapper.eq("state",notice.getState());
        }
        if(!StringUtils.isEmpty(notice.getCustomerServiceId())){
            wrapper.eq("customer_service_id",notice.getCustomerServiceId());
        }
        if(!StringUtils.isEmpty(notice.getType())){
            wrapper.eq("type", notice.getType());
        }
        if(!StringUtils.isEmpty(notice.getTitle())){
            wrapper.like("title", notice.getTitle());
        }
        if(!StringUtils.isEmpty(notice.getType())){
            wrapper.like("notice_id", notice.getNoticeId());
        }
        return shopPropertiesNoticeDao.selectPage(new Page<>(pageNum, pageSize),wrapper);
    }

    @Override
    public Boolean addNotice(ShopPropertiesNotice notice) {
        return shopPropertiesNoticeDao.insert(notice)>0;
    }

    @Override
    public Boolean deleteById(String id) {
        UpdateWrapper<ShopPropertiesNotice> wrapper = new UpdateWrapper<>();
        wrapper.eq("notice_id",id).set("status","0");
        return shopPropertiesNoticeDao.update(null,wrapper)>0;
    }

    @Override
    public ShopPropertiesNotice getById(String id) {
        QueryWrapper<ShopPropertiesNotice> wrapper = new QueryWrapper<>();
        wrapper.eq("notice_id",id).eq("status","1");
        return shopPropertiesNoticeDao.selectOne(wrapper);
    }

    @Override
    public Boolean saveNotice(ShopPropertiesNotice notice) {
        UpdateWrapper<ShopPropertiesNotice> wrapper = new UpdateWrapper<>();
        wrapper.eq("notice_id",notice.getNoticeId())
                .set("title",notice.getTitle())
                .set("type",notice.getType())
                .set("img",notice.getImg())
                .set("content_html",notice.getContentHtml())
                .set("content",notice.getContent())
                .set("begin_time",notice.getBeginTime())
                .set("end_time",notice.getEndTime())
                .set("remark",notice.getRemark());
        return shopPropertiesNoticeDao.update(null,wrapper)>0;
    }

    @Override
    public List<Map<String, Object>> countByType() {
        QueryWrapper<ShopPropertiesNotice> wrapper = new QueryWrapper<>();
        wrapper.select("if(type='1','商户通知','维修员通知' )as 类型 ,IFNULL(count(*),0)as 数量")
                .eq("status","1").groupBy("type");
        return shopPropertiesNoticeDao.selectMaps(wrapper);
    }

    @Override
    public int numOfNotice() {
        QueryWrapper<ShopPropertiesNotice> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1");
        return shopPropertiesNoticeDao.selectCount(wrapper);
    }

    @Override
    public int numOfNoticeByType(String type) {
        QueryWrapper<ShopPropertiesNotice> wrapper = new QueryWrapper<>();
        wrapper.eq("status","1").eq("type",type);
        return shopPropertiesNoticeDao.selectCount(wrapper);
    }
}