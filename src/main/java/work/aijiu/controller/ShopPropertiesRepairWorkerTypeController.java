package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.ShopPropertiesRepairWorkerType;
import work.aijiu.entity.ShopPropertiesWorkType;
import work.aijiu.service.ShopPropertiesRepairWorkerService;
import work.aijiu.service.ShopPropertiesRepairWorkerTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.xml.bind.util.JAXBSource;
import java.util.List;

/**
 * (ShopPropertiesRepairWorkerType)表控制层
 *
 * @author makejava
 * @since 2020-10-21 15:26:26
 */
@RestController
public class ShopPropertiesRepairWorkerTypeController {
    /**
     * 服务对象
     */
    @Autowired
    private ShopPropertiesRepairWorkerTypeService shopPropertiesRepairWorkerTypeService;

    /**
     * 商户物业根据id查询维修员类型
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/workertype/id/{id}", method = RequestMethod.GET)
    public ShopPropertiesRepairWorkerType getById(@PathVariable("id") String id){
        return shopPropertiesRepairWorkerTypeService.getById(id);
    }

    /**
     * 商户物业根据id查询维修员类型
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/workertype/idlist/{id}", method = RequestMethod.GET)
    public String getByIdList(@PathVariable("id") String id ){
        String name = "";
        System.out.println(id);
        if(id.contains(",")){
            String[] ids = id.split(",");
            for (int i = 0; i < ids.length; i++) {
                name = name + " " + shopPropertiesRepairWorkerTypeService.getById(ids[i]).getRepairWorkerTypeName();
            }
            return name;
        }
        return shopPropertiesRepairWorkerTypeService.getById(id).getRepairWorkerTypeName();
    }

    /**
     * 获取全部可用维修工类型
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/workertype/all", method = RequestMethod.GET)
    public List<ShopPropertiesRepairWorkerType> getAll(){
        return shopPropertiesRepairWorkerTypeService.getAll();
    }


    /**
     * 商户物业 维修员类型分页
     * @param offset
     * @param limit
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/repairtype/page/{offset}/{limit}", method = RequestMethod.GET)
    public PageResult queryPage(@PathVariable("offset")Integer offset,
                                @PathVariable("limit")Integer limit,
                                @RequestParam("type")String type){
        IPage<ShopPropertiesRepairWorkerType> page = shopPropertiesRepairWorkerTypeService.queryPage(offset, limit, type);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(page.getPages());
        grid.setRecords(page.getTotal());
        grid.setRows(page.getRecords());
        return  grid;
    }

    /**
     * 商户物业 增加工种类型
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/repairtype/add", method = RequestMethod.POST)
    public JSONResult addType(@RequestBody ShopPropertiesRepairWorkerType type){
        IdWorker worker = new IdWorker(1,1,1);
        type.setStatus("1");
        type.setRepairWorkerTypeId(worker.nextId()+"");
        if(shopPropertiesRepairWorkerTypeService.add(type)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 保存工种类型
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/repairtype/save", method = RequestMethod.POST)
    public JSONResult save(@RequestBody ShopPropertiesRepairWorkerType type){
        if(shopPropertiesRepairWorkerTypeService.save(type)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 根据id 删除工单类型
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/repairtype/delete/{id}", method = RequestMethod.GET)
    public JSONResult deleteById(@PathVariable("id")String id){
        if(shopPropertiesRepairWorkerTypeService.deleteById(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 添加工种类型查重
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/repairtype/checkAddName", method = RequestMethod.GET)
    public Boolean checkAddName(@RequestParam("name")String name){
        return shopPropertiesRepairWorkerTypeService.checkAddType(name);
    }

    /**
     * 商户物业 编辑工种类型查重
     * @param name
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/repairtype/checkEditName/{name}/{id}", method = RequestMethod.GET)
    public Boolean checkEditName(@PathVariable("name")String name,@PathVariable("id")String id){
        return shopPropertiesRepairWorkerTypeService.checkEditType(name,id);
    }
}