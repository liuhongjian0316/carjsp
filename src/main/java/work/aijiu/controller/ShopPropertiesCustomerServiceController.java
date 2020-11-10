package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.Building;
import work.aijiu.entity.ShopPropertiesCustomerService;
import work.aijiu.service.DefaultPwdService;
import work.aijiu.service.ShopPropertiesCustomerServiceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * (ShopPropertiesCustomerService)表控制层
 *
 * @author makejava
 * @since 2020-10-22 13:31:34
 */
@RestController
public class ShopPropertiesCustomerServiceController {
    /**
     * 服务对象
     */
    @Autowired
    private ShopPropertiesCustomerServiceService shopPropertiesCustomerServiceService;
    @Autowired
    private DefaultPwdService defaultPwdService;

    /**
     * 商户物业 客服分页
     * @param offset
     * @param limit
     * @param terms
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/service/page/{offset}/{limit}", method = RequestMethod.GET)
    public PageResult queryPage(@PathVariable("offset")Integer offset,
                                @PathVariable("limit")Integer limit,
                                @RequestParam("trems")String terms){
        IPage<Map<String, Object>> mapIPage = shopPropertiesCustomerServiceService.queryPage(offset, limit, terms);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(mapIPage.getPages());
        grid.setRecords(mapIPage.getTotal());
        grid.setRows(mapIPage.getRecords());
        return  grid;
    }

    /**
     * 商户物业 增加客服
     * @param service
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/service/insert", method = RequestMethod.POST)
    public JSONResult addCustomerService(@RequestBody ShopPropertiesCustomerService service){
        IdWorker worker = new IdWorker(1,1,1);
        service.setCustomerServiceId(worker.nextId()+"");
        service.setUsername(service.getTel());
        service.setPwd(defaultPwdService.queryByParm("sp_service").getDefaultPwd());
        //数据状态
        service.setStatus("1");
        //在线状态
        service.setState("0");
        service.setCreateTime(new Date());
        if(shopPropertiesCustomerServiceService.addService(service)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 根据id 获取客服信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/service/id/{id}", method = RequestMethod.GET)
    public ShopPropertiesCustomerService getById(@PathVariable("id")String id){
        return shopPropertiesCustomerServiceService.getById(id);
    }

    /**
     * 商户客服 保存客服信息
     * @param service
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/service/save", method = RequestMethod.POST)
    public JSONResult saveService(@RequestBody ShopPropertiesCustomerService service){
        if(shopPropertiesCustomerServiceService.saveService(service)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 根据id 删除客服信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/service/delete/{id}", method = RequestMethod.GET)
    public JSONResult delService(@PathVariable("id")String id){
        if(shopPropertiesCustomerServiceService.deleteService(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 重置客服密码
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/service/resetpwd/{id}", method = RequestMethod.GET)
    public JSONResult resetPwd(@PathVariable("id")String id){
        if(shopPropertiesCustomerServiceService.resetPwd(id,
                defaultPwdService.queryByParm("sp_service").getDefaultPwd())){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户客服 增加客服 手机号查重
     * @param tel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/service/checkAddTel/{tel}", method = RequestMethod.GET)
    public Boolean checkAddTel(@PathVariable("tel")String tel){
        return shopPropertiesCustomerServiceService.checkAddTel(tel);
    }

    /**
     * 商户客服 编辑客服 手机号查重
     * @param tel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/service/checkEditTel/{tel}/{id}", method = RequestMethod.GET)
    public Boolean checkEditTel(@PathVariable("tel")String tel,@PathVariable("id")String id){
        return shopPropertiesCustomerServiceService.checkEditTel(tel,id);
    }

}