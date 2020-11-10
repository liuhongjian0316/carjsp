package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.Building;
import work.aijiu.entity.Community;
import work.aijiu.service.BuildingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (Building)表控制层
 *
 * @author makejava
 * @since 2020-10-15 19:33:35
 */
@RestController
public class BuildingController {
    /**
     * 服务对象
     */
    @Autowired
    private BuildingService buildingService;

    /**
     * 楼栋分页
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/building/page/{offset}/{limit}",method = RequestMethod.GET)
    public PageResult queryUnauditedPage(@PathVariable("offset")Integer offset, @PathVariable("limit")Integer limit){
        IPage<Building> buildingIPage = buildingService.queryPage(offset, limit);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(buildingIPage.getPages());
        grid.setRecords(buildingIPage.getTotal());
        grid.setRows(buildingIPage.getRecords());
        return grid;
    }

    /**
     * 根据id 获取楼栋id
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/building/id/{id}",method = RequestMethod.GET)
    public Building getOne(@PathVariable("id")String id){
        return buildingService.queryById(id);
    }

    /**
     * 添加楼栋
     * @param building
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/building/insert",method = RequestMethod.POST)
    public JSONResult add(@RequestBody Building building){
        IdWorker worker = new IdWorker(1,1,1);
        building.setBuildingId(worker.nextId()+"");
        //默认可用
        building.setStatus("1");
        building.setCreateTime(new Date());
        if(buildingService.addBuilding(building)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("添加失败");
    }

    /**
     * 修改楼栋
     * @param building
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/building/save",method = RequestMethod.POST)
    public JSONResult save(@RequestBody Building building){
        if(buildingService.save(building)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 删除楼栋
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/building/delete/{id}",method = RequestMethod.GET)
    public JSONResult delete(@PathVariable("id")String id){
        if(buildingService.delete(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 添加房屋时选择楼栋
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/building/list/{id}",method = RequestMethod.GET)
    public List<Building> choiceBuilding (@PathVariable("id")String id){
        return buildingService.queryListByCid(id);
    }


}