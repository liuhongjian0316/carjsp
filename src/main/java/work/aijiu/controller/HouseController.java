package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.Building;
import work.aijiu.entity.House;
import work.aijiu.service.HouseService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (House)表控制层
 *
 * @author makejava
 * @since 2020-10-16 19:47:10
 */
@RestController
public class HouseController {
    /**
     * 服务对象
     */
    @Autowired
    private HouseService houseService;


    /**
     * 房屋分页
     * @param offset
     * @param limit
     * @param cid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/house/page/{offset}/{limit}/{cid}",method = RequestMethod.GET)
    public PageResult queryUnauditedPage(@PathVariable("offset")Integer offset,
                                         @PathVariable("limit")Integer limit,
                                         @PathVariable("cid")String cid){
        IPage<Map<String,Object>> houseIPage = houseService.queryPage(offset, limit,cid);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(houseIPage.getPages());
        grid.setRecords(houseIPage.getTotal());
        grid.setRows(houseIPage.getRecords());
        return grid;
    }

    /**
     * 增加房屋
     * @param house
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/house/insert", method = RequestMethod.POST)
    public JSONResult insert(@RequestBody House house){
        IdWorker worker = new IdWorker(1,1,1);
        house.setHouseId(worker.nextId()+"");
        house.setStatus("1");
        house.setState("0");
        if(houseService.addHouse(house)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 根据房屋id获取房屋信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/house/id/{id}", method = RequestMethod.GET)
    public House getById(@PathVariable("id") String id){
        return houseService.getById(id);
    }

    /**
     * 编辑房屋信息
     * @param house
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/house/save", method = RequestMethod.POST)
    public JSONResult save(@RequestBody House house){
        if(houseService.save(house)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 根据id 删除房屋信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/house/delete/{id}", method = RequestMethod.GET)
    public JSONResult delete(@PathVariable("id") String id){
        if(houseService.delete(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }
}