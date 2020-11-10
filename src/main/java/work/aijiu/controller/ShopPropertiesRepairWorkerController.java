package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.Building;
import work.aijiu.entity.ShopPropertiesRepairWorker;
import work.aijiu.service.DefaultPwdService;
import work.aijiu.service.ShopPropertiesRepairWorkerService;
import org.springframework.web.bind.annotation.*;
import work.aijiu.service.ShopPropertiesRepairWorkerTypeService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesRepairWorker)表控制层
 *
 * @author makejava
 * @since 2020-10-21 15:25:02
 */
@RestController
public class ShopPropertiesRepairWorkerController {
    /**
     * 服务对象
     */
    @Autowired
    private ShopPropertiesRepairWorkerService shopPropertiesRepairWorkerService;
    @Autowired
    private ShopPropertiesRepairWorkerTypeService shopPropertiesRepairWorkerTypeService;
    @Autowired
    private DefaultPwdService defaultPwdService;


    @ResponseBody
    @RequestMapping(value = "/sp/admin/worker/page/{offset}/{limit}",method = RequestMethod.GET)
    public PageResult queryPage(@PathVariable("offset")Integer offset,
                                @PathVariable("limit")Integer limit,
                                @RequestParam("trems")String terms){
        IPage<HashMap<String, Object>> mapIPage = shopPropertiesRepairWorkerService.queryPage(offset, limit, terms);
        List<HashMap<String, Object>> records = mapIPage.getRecords();
        for (int i = 0; i < records.size(); i++) {
            if(records.get(i).get("repair_worker_type_id").toString().contains(",")){
                String[] ids = records.get(i).get("repair_worker_type_id").toString().split(",");
                String name = "";
                for (String id: ids) {
                    name = name + " " + shopPropertiesRepairWorkerTypeService.getById(id).getRepairWorkerTypeName();
                }
                records.get(i).put("repair_worker_type_name",name);
            }else{
                records.get(i).put("repair_worker_type_name",shopPropertiesRepairWorkerTypeService.getById(records.get(i).get("repair_worker_type_id").toString()).getRepairWorkerTypeName());
            }
        }
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(mapIPage.getPages());
        grid.setRecords(mapIPage.getTotal());
        grid.setRows(records);
        return grid;
    }

    /**
     * 商户物业添加维修员
     * @param worker
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/worker/insert",method = RequestMethod.POST)
    public JSONResult addWorker(@RequestBody ShopPropertiesRepairWorker worker){
        IdWorker idWorker = new IdWorker(1,1,1);
        worker.setRepairWorkerId(idWorker.nextId()+"");
        //设置默认数据状态（可用）
        worker.setStatus("1");
        //设置默认密码
        worker.setPwd(defaultPwdService.queryByParm("sp_worker").getDefaultPwd());
        //设置创造时间
        worker.setCreateTime(new Date());
        worker.setUserName(worker.getTel());
        if(shopPropertiesRepairWorkerService.addWorker(worker)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 添加维修员信息 手机号查重
     * @param tel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/worker/checkTel/{tel}",method = RequestMethod.GET)
    public Boolean checkAddWorkerTel(@PathVariable("tel") String tel){
        return shopPropertiesRepairWorkerService.checkAddWorkerTel(tel);
    }

    /**
     * 商户物业 添加维修员 身份证号 查重
     * @param card
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/worker/checkCard/{card}",method = RequestMethod.GET)
    public Boolean checkAddWorkerCard(@PathVariable("card") String card){
        return shopPropertiesRepairWorkerService.checkAddWorkerCard(card);
    }

    /**
     * 商户物业 根据id 获取维修员信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/worker/id/{id}",method = RequestMethod.GET)
    public ShopPropertiesRepairWorker getWorkerById(@PathVariable("id") String id){
        return shopPropertiesRepairWorkerService.getById(id);
    }

    /**
     * 商户物业 编辑维修员 手机号查重
     * @param tel
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/worker/checkEditTel/{tel}/{id}",method = RequestMethod.GET)
    public Boolean checkEditWorkerTel(@PathVariable("tel") String tel,@PathVariable("id")String id){
        return shopPropertiesRepairWorkerService.checkEditWorkerTel(tel,id);
    }

    /**
     * 商户物业 编辑维修员 身份证号查重
     * @param card
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/worker/checkEditCard/{card}/{id}",method = RequestMethod.GET)
    public Boolean checkEditWorkerCard(@PathVariable("card") String card,@PathVariable("id")String id){
        return shopPropertiesRepairWorkerService.checkEditWorkerCard(card,id);
    }

    /**
     * 商户物业 保存维修员信息
     * @param worker
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/worker/save",method = RequestMethod.POST)
    public JSONResult save(@RequestBody ShopPropertiesRepairWorker worker){
        if(shopPropertiesRepairWorkerService.saveWorker(worker)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业删除维修员
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/worker/delete/{id}",method = RequestMethod.GET)
    public JSONResult deleteWorker(@PathVariable("id") String id){
        if(shopPropertiesRepairWorkerService.deleteWorkerById(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 重置维修员密码
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/worker/resetpwd/{id}",method = RequestMethod.GET)
    public JSONResult resetPwd(@PathVariable("id") String id){
        if(shopPropertiesRepairWorkerService.resetpwd(id,defaultPwdService.queryByParm("sp_worker").getDefaultPwd())){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }
}