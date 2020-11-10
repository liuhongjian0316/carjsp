package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.Community;
import work.aijiu.entity.Company;
import work.aijiu.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * (Company)表控制层
 *
 * @author makejava
 * @since 2020-10-14 18:55:15
 */
@RestController
public class CompanyController {
    /**
     * 服务对象
     */
    @Autowired
    private CompanyService companyService;

    /**
     * 申请公司
     * @param company
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/company/apply",method = RequestMethod.POST)
    public JSONResult apply(@RequestBody Company company){
        IdWorker worker = new IdWorker(1,1,1);
        company.setCompanyId(worker.nextId()+"");
        company.setCreateTime(new Date());
        //默认数据状态1（可用）
        company.setStatus("1");
        //默认审核状态0（未审核）
        company.setState("0");
        System.out.println(company);
        if(companyService.apply(company)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("添加失败");
    }

    /**
     * 查询未审核的公司
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/company/unaudited/{offset}/{limit}",method = RequestMethod.GET)
    public PageResult queryUnauditedPage(@PathVariable("offset")Integer offset, @PathVariable("limit")Integer limit){
        IPage<Company> communityIPage = companyService.queryUnauditedPage(offset, limit);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(communityIPage.getPages());
        grid.setRecords(communityIPage.getTotal());
        grid.setRows(communityIPage.getRecords());
        return grid;
    }

    /**
     * 根据id查询未审核公司的详细信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/company/unaudited/{id}",method = RequestMethod.GET)
    public Company queryById(@PathVariable("id")String id){
        return companyService.queryById(id);
    }

    /**
     * 审核公司
     * @param company
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/company/audit",method = RequestMethod.POST)
    public JSONResult audit(@RequestBody Company company){
        if(companyService.audit(company)){
            return  JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    @ResponseBody
    @RequestMapping(value = "/company/delete/{id}",method = RequestMethod.GET)
    public JSONResult delete(@PathVariable("id") String id){
        if(companyService.delete(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }
}