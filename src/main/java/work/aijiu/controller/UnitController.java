package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.Building;
import work.aijiu.entity.Unit;
import work.aijiu.service.UnitService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (Unit)表控制层
 *
 * @author makejava
 * @since 2020-10-16 09:12:14
 */
@RestController
public class UnitController {
    /**
     * 服务对象
     */
    @Autowired
    private UnitService unitService;

    /**
     * 单元分页
     * @param offset
     * @param limit
     * @param cid
     * @param bid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/unit/page/{offset}/{limit}/{cid}/{bid}",method = RequestMethod.GET)
    public PageResult queryUnauditedPage(@PathVariable("offset")Integer offset,
                                         @PathVariable("limit")Integer limit,
                                         @PathVariable("cid")String cid,
                                         @PathVariable("bid")String bid){
        IPage<Unit> unitIPage = unitService.queryPage(offset, limit,cid,bid);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(unitIPage.getPages());
        grid.setRecords(unitIPage.getTotal());
        grid.setRows(unitIPage.getRecords());
        return grid;
    }

    /**
     * 添加单元
     * @param unit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/unit/insert",method = RequestMethod.POST)
    public JSONResult add(@RequestBody Unit unit){
        IdWorker worker = new IdWorker(1,1,1);
        unit.setUnitId(worker.nextId()+"");
        //默认状态
        unit.setStatus("1");
        unit.setCreateTime(new Date());
        if(unitService.add(unit)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 根据id获取单元信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/unit/id/{id}",method = RequestMethod.GET)
    public Unit getById(@PathVariable("id")String id){
        return unitService.getById(id);
    }

    /**
     * 保存单元信息
     * @param unit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/unit/save",method = RequestMethod.POST)
    public JSONResult save(@RequestBody Unit unit){
        if(unitService.save(unit)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 根据id 删除单元信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/unit/delete/{id}",method = RequestMethod.GET)
    public JSONResult delete(@PathVariable("id") String id){
        if(unitService.delete(id)){
         return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    @ResponseBody
    @RequestMapping(value = "/unit/list/{cid}/{bid}",method = RequestMethod.GET)
    public List<Unit> choiceBuilding (@PathVariable("cid")String cid,@PathVariable("bid")String bid){
        return unitService.queryByCidBid(cid,bid);
    }

}