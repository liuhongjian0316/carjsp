package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.ShopPropertiesInnerEmployees;
import work.aijiu.service.DefaultPwdService;
import work.aijiu.service.ShopPropertiesInnerEmployeesService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (ShopPropertiesInnerEmployees)表控制层
 *
 * @author makejava
 * @since 2020-10-24 12:42:26
 */
@RestController
public class ShopPropertiesInnerEmployeesController {
    /**
     * 服务对象
     */
    @Autowired
    private ShopPropertiesInnerEmployeesService shopPropertiesInnerEmployeesService;
    @Autowired
    private DefaultPwdService defaultPwdService;

    /**
     * 商户物业员工分页
     * @param offset
     * @param limit
     * @param terms
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/employee/page/{offset}/{limit}", method = RequestMethod.GET)
    public PageResult queryPage(@PathVariable("offset")Integer offset,
                                @PathVariable("limit")Integer limit,
                                @RequestParam("trems")String terms){
        IPage<Map<String, Object>> mapIPage = shopPropertiesInnerEmployeesService.queryPage(offset, limit, terms);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(mapIPage.getPages());
        grid.setRecords(mapIPage.getTotal());
        grid.setRows(mapIPage.getRecords());
        return  grid;
    }

    /**
     * 商户物业 增加员工手机号查重
     * @param tel
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/employee/checkAddTel/{tel}", method = RequestMethod.GET)
    public Boolean checkAddTel(@PathVariable("tel")String tel){
        return shopPropertiesInnerEmployeesService.checkAddTel(tel);
    }

    /**
     * 商户物业添加员工手机号查重
     * @param tel
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/employee/checkEditTel/{tel}/{id}", method = RequestMethod.GET)
    public Boolean checkEditTel(@PathVariable("tel")String tel,@PathVariable("id")String id){
        return shopPropertiesInnerEmployeesService.checkEditTel(tel,id);
    }


    /**
     * 商户物业添加员工
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/employee/insert", method = RequestMethod.POST)
    public JSONResult addEmployee(@RequestBody ShopPropertiesInnerEmployees employee){
        IdWorker worker = new IdWorker(1,1,1);
        employee.setEmployeeId(worker.nextId()+"");
        employee.setUserName(employee.getTel());
        employee.setPwd(defaultPwdService.queryByParm("sp_employee").getDefaultPwd());
        employee.setStatus("1");
        if(shopPropertiesInnerEmployeesService.addEmployee(employee)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业删除员工
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/employee/delete/{id}", method = RequestMethod.GET)
    public JSONResult deleteEmployee(@PathVariable("id") String id){
        if(shopPropertiesInnerEmployeesService.deleteEmployeeById(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业根据 id 查询员工信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/employee/id/{id}", method = RequestMethod.GET)
    public ShopPropertiesInnerEmployees getEmployee(@PathVariable("id") String id){
        return shopPropertiesInnerEmployeesService.getById(id);
    }

    /**
     * 商户物业 保存员工信息
     * @param employees
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/employee/save", method = RequestMethod.POST)
    public JSONResult saveEmployee(@RequestBody ShopPropertiesInnerEmployees employees){
        if(shopPropertiesInnerEmployeesService.saveEmployee(employees)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 重置员工密码
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "sp/admin/employee/resetpwd/{id}", method = RequestMethod.GET)
    public JSONResult resetPwd(@PathVariable("id") String id){
        if(shopPropertiesInnerEmployeesService.resetPwd(id,
                defaultPwdService.queryByParm("sp_employee").getDefaultPwd())){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

}