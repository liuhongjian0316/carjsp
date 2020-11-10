package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.Community;
import work.aijiu.service.CommunityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * (Community)表控制层
 *
 * @author makejava
 * @since 2020-10-12 16:46:57
 */
@RestController
public class CommunityController {
    /**
     * 服务对象
     */
    @Autowired
    private CommunityService communityService;

    /**
     * 审核或未审核的小区分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/community/unaudited/{offset}/{limit}",method = RequestMethod.GET)
    public PageResult queryUnauditedPage(@PathVariable("offset")Integer offset,@PathVariable("limit")Integer limit){
        IPage<Community> communityIPage = communityService.queryUnauditedPage(offset, limit);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(communityIPage.getPages());
        grid.setRecords(communityIPage.getTotal());
        grid.setRows(communityIPage.getRecords());
        return grid;
    }

    /**
     * 审核或未审核的小区分页查询
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/community/page/{offset}/{limit}",method = RequestMethod.GET)
    public PageResult queryUnPage(@PathVariable("offset")Integer offset,@PathVariable("limit")Integer limit){
        IPage<Community> communityIPage = communityService.queryPage(offset, limit);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(communityIPage.getPages());
        grid.setRecords(communityIPage.getTotal());
        grid.setRows(communityIPage.getRecords());
        return grid;
    }


    /**
     * 新增小区
     * @param community
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/community/insert",method = RequestMethod.POST)
    public JSONResult insert(@RequestBody Community community){
        //默认数据状态在用
        community.setStatus("1");
        //默认审核状态未审核
        community.setState("0");
        community.setCreateTime(new Date());
        community.setUpdateTime(new Date());
        IdWorker worker = new IdWorker(1,1,1);
        community.setCommunityId(worker.nextId()+"");
        if(communityService.insert(community)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("添加失败");
    }

    /**
     * 根据id获取小区
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/community/id/{id}",method = RequestMethod.GET)
    public Community getone(@PathVariable("id")String id){
        return communityService.getById(id);
    }

    /**
     * 根据id获取小区信息
     * @param community
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/community/save/",method = RequestMethod.POST)
    public JSONResult save(@RequestBody Community community){
        if(communityService.edit(community)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败！");
    }

    /**
     * 撤回审核
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/community/auditreturn/{id}",method = RequestMethod.GET)
    public JSONResult auditreturn(@PathVariable("id")String id){
        if(communityService.auditreturn(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败！");
    }

    /**
     * 审核小区
     * @param community
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/community/audit",method = RequestMethod.POST)
    public JSONResult audit(@RequestBody Community community){
        if(communityService.audit(community)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("审核失败");
    }

    /**
     * 删除小区
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/community/delete/{id}",method = RequestMethod.GET)
    public JSONResult delete(@PathVariable("id")String id){
        if(communityService.delete(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }


}