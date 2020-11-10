package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.PageResult;
import work.aijiu.common.utils.TablePage;
import work.aijiu.entity.Role;
import work.aijiu.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Role)表控制层
 *
 * @author makejava
 * @since 2020-09-18 09:26:58
 */
@RestController
public class RoleController {
    /**
     * 服务对象
     */
    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping(value = "/role/page/{offset}/{limit}",method = RequestMethod.GET)
    public PageResult  selectTablePage(@PathVariable("offset")Integer offset,@PathVariable("limit")Integer limit ) {
        IPage<Role> roleIPage = roleService.queryRolePage(offset,limit);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(roleIPage.getPages());
        grid.setRecords(roleIPage.getTotal());
        grid.setRows(roleIPage.getRecords());
        return grid;
    }

}