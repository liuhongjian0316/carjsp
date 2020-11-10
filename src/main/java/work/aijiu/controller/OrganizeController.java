package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.Community;
import work.aijiu.entity.Organize;
import work.aijiu.service.OrganizeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Organize)表控制层
 *
 * @author makejava
 * @since 2020-10-20 22:02:58
 */
@RestController
public class OrganizeController {
    /**
     * 服务对象
     */
    @Autowired
    private OrganizeService organizeService;

    /**
     * 查询本公司下单的所哟部门
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/organize/getCurrent/{id}",method = RequestMethod.GET)
    public List<Organize> queryCurrentOrganizeById(@PathVariable("id")String id){
        return organizeService.queryCurrentOrganizeById(id);
    }

    /**
     * 商户物业添加部门时，部门名称查重
     * @param id
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/organize/checkAddName/{name}/{id}",method = RequestMethod.GET)
    public JSONResult checkAddName(@PathVariable("name")String name,
                                   @PathVariable("id")String id){
        if(organizeService.checkAddName(name,id)){
            return JSONResult.ok(false);
        }
        return JSONResult.ok(true);
    }

    /**
     * 商户物业添加部门
     * @param organize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/organize/insert",method = RequestMethod.POST)
    public JSONResult insert(@RequestBody Organize organize){
        IdWorker worker = new IdWorker(1,1,1);
        organize.setOrganizeId(worker.nextId()+"");
        //默认状态
        organize.setStatus("1");
        if(organizeService.addOrganize(organize)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业根据id获取组织信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/organize/id/{id}",method = RequestMethod.GET)
    public Organize getOrganizeById(@PathVariable String id){
        return organizeService.getById(id);
    }

    /**
     * 商户物业保存信息
     * @param organize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/organize/save",method = RequestMethod.POST)
    public JSONResult save(@RequestBody Organize organize){
        if(organizeService.saveOrganize(organize)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业编辑信息名称查重
     * @param name
     * @param id
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/organize/checkEditName/{name}/{id}/{pid}",method = RequestMethod.GET)
    public JSONResult checkEditName(@PathVariable("name")String name,
                                    @PathVariable("id")String id,
                                    @PathVariable("pid")String pid){
        if(organizeService.checkEditName(name,id,pid)){
            return JSONResult.ok(false);
        }
        return JSONResult.ok(true);
    }

    /**
     * 商户物业删除组织信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/organize/delete/{id}",method = RequestMethod.GET)
    public JSONResult delete(@PathVariable("id")String id){
        if(organizeService.deleteById(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }
}