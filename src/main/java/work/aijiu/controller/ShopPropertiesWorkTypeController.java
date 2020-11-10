package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.ShopPropertiesDistributeOrder;
import work.aijiu.entity.ShopPropertiesWorkOrder;
import work.aijiu.entity.ShopPropertiesWorkType;
import work.aijiu.service.ShopPropertiesWorkTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (ShopPropertiesWorkType)表控制层
 *
 * @author makejava
 * @since 2020-10-25 22:16:50
 */
@RestController
public class ShopPropertiesWorkTypeController {
    /**
     * 服务对象
     */
    @Autowired
    private ShopPropertiesWorkTypeService shopPropertiesWorkTypeService;

    /**
     * 商户物业 商户选择报修类型列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/worktype/list", method = RequestMethod.GET)
    public List<ShopPropertiesWorkType> getList(){
        return shopPropertiesWorkTypeService.QueryTypeList();
    }

    /**
     * 商户物业 工单类型分页
     * @param offset
     * @param limit
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/worktype/page/{offset}/{limit}", method = RequestMethod.GET)
    public PageResult queryPage(@PathVariable("offset")Integer offset,
                                                  @PathVariable("limit")Integer limit,
                                                  @RequestParam("type")String type){
        IPage<ShopPropertiesWorkType> page = shopPropertiesWorkTypeService.queryPage(offset, limit, type);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(page.getPages());
        grid.setRecords(page.getTotal());
        grid.setRows(page.getRecords());
        return  grid;
    }

    /**
     * 商户物业 增加工单类型
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/worktype/add", method = RequestMethod.POST)
    public JSONResult addType(@RequestBody ShopPropertiesWorkType type){
        IdWorker worker = new IdWorker(1,1,1);
        type.setStatus("1");
        type.setWorkTypeId(worker.nextId()+"");
        if(shopPropertiesWorkTypeService.addType(type)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 根据id获取工单类型
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/worktype/id/{id}", method = RequestMethod.GET)
    public ShopPropertiesWorkType getById(@PathVariable("id") String id){
        return shopPropertiesWorkTypeService.getById(id);
    }

    /**
     * 商户物业 保存工单类型
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/worktype/save", method = RequestMethod.POST)
    public JSONResult save(@RequestBody ShopPropertiesWorkType type){
        if(shopPropertiesWorkTypeService.save(type)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }


    /**
     * 商户物业 删除工单类型
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/worktype/delete/{id}", method = RequestMethod.GET)
    public JSONResult delete(@PathVariable("id") String id){
        if(shopPropertiesWorkTypeService.deleteById(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 添加工单类型查重
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/worktype/checkAddName", method = RequestMethod.GET)
    public Boolean checkAddName(@RequestParam("name")String name){
        return shopPropertiesWorkTypeService.checkAddType(name);
    }

    /**
     * 商户物业 编辑工单类型查重
     * @param name
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/worktype/checkEditName/{name}/{id}", method = RequestMethod.GET)
    public Boolean checkEditName(@PathVariable("name")String name,@PathVariable("id")String id){
        return shopPropertiesWorkTypeService.checkEditType(name,id);
    }






}