package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.ShopPropertiesNotice;
import work.aijiu.entity.ShopPropertiesShop;
import work.aijiu.service.ShopPropertiesNoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (ShopPropertiesNotice)表控制层
 *
 * @author makejava
 * @since 2020-11-04 22:30:09
 */
@RestController
public class ShopPropertiesNoticeController {
    /**
     * 服务对象
     */
    @Autowired
    private ShopPropertiesNoticeService shopPropertiesNoticeService;

    /**
     * 商户物业 通知分页
     * @param notice
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/service/notices/page/{offset}/{limit}",method = RequestMethod.POST)
    public PageResult queryPage(@RequestBody ShopPropertiesNotice notice,
                                @PathVariable("offset")Integer offset,
                                @PathVariable("limit")Integer limit){
        IPage<ShopPropertiesNotice> iPage = shopPropertiesNoticeService.queryPage(offset, limit, notice);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(iPage.getPages());
        grid.setRecords(iPage.getTotal());
        grid.setRows(iPage.getRecords());
        return grid;
    }

    /**
     * 发布通知
     * @param notice
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/service/notices/insert",method = RequestMethod.POST)
    public JSONResult addNotice(@RequestBody ShopPropertiesNotice notice){
        IdWorker worker = new IdWorker(1,1,1);
        notice.setNoticeId(worker.nextId()+"");
        //未发布
        notice.setState("0");
        notice.setStatus("1");
        notice.setCreateTime(new Date());
        if(shopPropertiesNoticeService.addNotice(notice)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业  删除通知
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/service/notices/delete/{id}",method = RequestMethod.GET)
    public JSONResult delete(@PathVariable("id")String id){
        if(shopPropertiesNoticeService.deleteById(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 获取通知信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/service/notices/edit/{id}",method = RequestMethod.GET)
    public ShopPropertiesNotice edit(@PathVariable("id")String id){
        return shopPropertiesNoticeService.getById(id);
    }

    /**
     * 保存公告
     * @param notice
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/service/notices/save",method = RequestMethod.POST)
    public JSONResult saveNotice(@RequestBody ShopPropertiesNotice notice){
        if(shopPropertiesNoticeService.saveNotice(notice)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 通知类型计算
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/service/notices/type/{type}",method = RequestMethod.GET)
    public int numOfNoticeByType(@PathVariable("type") String type){
        return shopPropertiesNoticeService.numOfNoticeByType(type);
    }
}