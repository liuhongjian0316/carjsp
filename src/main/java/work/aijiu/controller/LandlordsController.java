package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.Community;
import work.aijiu.entity.Landlords;
import work.aijiu.service.LandlordsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Landlords)表控制层
 *
 * @author makejava
 * @since 2020-10-17 16:53:36
 */
@RestController
public class LandlordsController {
    /**
     * 服务对象
     */
    @Autowired
    private LandlordsService landlordsService;

    /**
     * 业主分页查询
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/landlords/page/{offset}/{limit}",method = RequestMethod.GET)
    public PageResult queryUnPage(@PathVariable("offset")Integer offset, @PathVariable("limit")Integer limit){
        IPage<Landlords> landlordsIPage = landlordsService.queryPage(offset, limit);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(landlordsIPage.getPages());
        grid.setRecords(landlordsIPage.getTotal());
        grid.setRows(landlordsIPage.getRecords());
        return grid;
    }

    /**
     * 增加业主
     * @param landlords
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/landlords/insert", method = RequestMethod.POST)
    public JSONResult insert(@RequestBody Landlords landlords){
        IdWorker worker = new IdWorker(1,1,1);
        landlords.setLandlordsId(worker.nextId()+"");
        landlords.setStatus("1");
        if(landlordsService.addLandlords(landlords)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 根据id获取业主信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/landlords/id/{id}", method = RequestMethod.GET)
    public Landlords getById(@PathVariable("id")String id){
        return landlordsService.getById(id);
    }

    /**
     * 保存业主信息
     * @param landlords
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/landlords/save", method = RequestMethod.POST)
    public JSONResult save(@RequestBody Landlords landlords){
        if(landlordsService.save(landlords)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 根据id删除物业信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/landlords/delete/{id}", method = RequestMethod.GET)
    public JSONResult delete(@PathVariable("id") String id){
        if(landlordsService.delete(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }
    
    /**
     * 查询业主信息列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/landlords/list", method = RequestMethod.GET)
    public List<Landlords> queryList(){
        return landlordsService.queryList();
    }
}