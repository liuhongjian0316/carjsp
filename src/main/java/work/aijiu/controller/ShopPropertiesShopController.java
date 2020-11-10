package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.Community;
import work.aijiu.entity.ShopPropertiesShop;
import work.aijiu.service.ShopPropertiesShopService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (ShopPropertiesShop)表控制层
 *
 * @author makejava
 * @since 2020-10-20 10:24:55
 */
@RestController
public class ShopPropertiesShopController {
    /**
     * 服务对象
     */
    @Autowired
    private ShopPropertiesShopService shopPropertiesShopService;


    /**
     * 物业界面管理商户信息 分页
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/shop/page/{offset}/{limit}",method = RequestMethod.GET)
    public PageResult queryUnauditedPage(@PathVariable("offset")Integer offset, @PathVariable("limit")Integer limit){
        IPage<ShopPropertiesShop> shopIPage= shopPropertiesShopService.queryPage(offset, limit);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(shopIPage.getPages());
        grid.setRecords(shopIPage.getTotal());
        grid.setRows(shopIPage.getRecords());
        return grid;
    }

    /**
     * 管理员添加上商户
     * @param shopPropertiesShop
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/shop/insert",method = RequestMethod.POST)
    public JSONResult addShop(@RequestBody ShopPropertiesShop shopPropertiesShop){
        IdWorker worker = new IdWorker(1,1,1);
        shopPropertiesShop.setShopId(worker.nextId()+"");
        //默认数据状态
        shopPropertiesShop.setStatus("1");
        shopPropertiesShop.setCreateTime(new Date());
        if(shopPropertiesShopService.addShop(shopPropertiesShop)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 根据id获取商户信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/shop/id/{id}",method = RequestMethod.GET)
    public ShopPropertiesShop getById(@PathVariable("id") String id){
        return shopPropertiesShopService.getById(id);
    }

    /**
     * 保存信息
     * @param shopPropertiesShop
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/shop/save",method = RequestMethod.POST)
    public JSONResult save(@RequestBody ShopPropertiesShop shopPropertiesShop){
        if(shopPropertiesShopService.save(shopPropertiesShop)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 根据id删除商户信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/shop/delete/{id}",method = RequestMethod.GET)
    public JSONResult delete(@PathVariable("id") String id){
        if(shopPropertiesShopService.delete(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 模糊查询+分页
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/shop/search/{offset}/{limit}",method = RequestMethod.POST)
    public PageResult seachLike(@RequestBody ShopPropertiesShop shopPropertiesShop,@PathVariable("offset")Integer offset, @PathVariable("limit")Integer limit){
        IPage<ShopPropertiesShop> shopIPage= shopPropertiesShopService.queryLike(offset, limit,shopPropertiesShop);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(shopIPage.getPages());
        grid.setRecords(shopIPage.getTotal());
        grid.setRows(shopIPage.getRecords());
        return grid;
    }

    /**
     * 管理员添加商户时手机号查重
     * @param tel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/shop/checkTel/{tel}",method = RequestMethod.GET)
    public JSONResult checkAddTel(@PathVariable("tel")String tel){
        if(shopPropertiesShopService.checkTel(tel)){
            return JSONResult.ok(false);
        }
        return JSONResult.ok(true);
    }

    /**
     * 管理员编辑商户时手机号查重
     * @param tel
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/shop/checkTel/{tel}/{id}",method = RequestMethod.GET)
    public JSONResult checkEditTel(@PathVariable("tel")String tel,@PathVariable("id")String id){
        if(shopPropertiesShopService.checkEdit(tel,id)){
            return JSONResult.ok(false);
        }
        return JSONResult.ok(true);
    }
}