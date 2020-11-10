package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.Landlords;
import work.aijiu.entity.LandlordsChild;
import work.aijiu.service.LandlordsChildService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (LandlordsChild)表控制层
 *
 * @author makejava
 * @since 2020-10-18 23:50:16
 */
@RestController
public class LandlordsChildController {
    /**
     * 服务对象
     */
    @Autowired
    private LandlordsChildService landlordsChildService;

    /**
     * 根据业主id查询业主成员分页
     * @param offset
     * @param limit
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/landlordsChild/page/{offset}/{limit}/{pid}",method = RequestMethod.GET)
    public PageResult queryUnPage(@PathVariable("offset")Integer offset, @PathVariable("limit")Integer limit,@PathVariable("pid")String pid){
        IPage<LandlordsChild> landlordsChildIPage = landlordsChildService.queryPageByPid(offset, limit,pid);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(landlordsChildIPage.getPages());
        grid.setRecords(landlordsChildIPage.getTotal());
        grid.setRows(landlordsChildIPage.getRecords());
        return grid;
    }

    /**
     * 添加业主成员
     * @param landlordsChild
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/landlordsChild/insert",method = RequestMethod.POST)
    public JSONResult insert(@RequestBody LandlordsChild landlordsChild){
        IdWorker worker = new IdWorker(1,1,1);
        landlordsChild.setLandlordsChildId(worker.nextId()+"");
        //默认数据状态
        landlordsChild.setStatus("1");
        if(landlordsChildService.insertLandordsChild(landlordsChild)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 根据 id 获取成员信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/landlordsChild/id/{id}",method = RequestMethod.GET)
    public LandlordsChild getChildById(@PathVariable("id") String id){
        return landlordsChildService.getChildById(id);
    }

    /**
     * 保存成员信息
     * @param landlordsChild
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/landlordsChild/save",method = RequestMethod.POST)
    public JSONResult save(@RequestBody LandlordsChild landlordsChild){
        if(landlordsChildService.save(landlordsChild)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 删除业主成员
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/landlordsChild/delete/{id}",method = RequestMethod.GET)
    public JSONResult delete(@PathVariable("id") String id){
        if(landlordsChildService.delete(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }
}