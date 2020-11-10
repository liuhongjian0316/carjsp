package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.ShopPropertiesWorkOrder;
import work.aijiu.entity.ShopPropertiesWorkOrderCs;
import work.aijiu.service.ShopPropertiesDistributeOrderService;
import work.aijiu.service.ShopPropertiesWorkOrderCsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * (ShopPropertiesWorkOrderCs)表控制层
 *
 * @author makejava
 * @since 2020-10-30 10:04:07
 */
@RestController
public class ShopPropertiesWorkOrderCsController {
    /**
     * 服务对象
     */
    @Autowired
    private ShopPropertiesWorkOrderCsService shopPropertiesWorkOrderCsService;
    @Autowired
    private ShopPropertiesDistributeOrderService shopPropertiesDistributeOrderService;

    /**
     * 商户客服 客服代填维修工单（电话报修）
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/service/applayOrder")
    public JSONResult applayOrder(@RequestBody ShopPropertiesWorkOrderCs order){
        //id
        IdWorker worker = new IdWorker(1,1,1);
        order.setWorkOrderCsId(worker.nextId()+"");
        //填单时间
        order.setFillingTime(new Date());
        //默认工单状态
        order.setState("0");
        //默认数据状态
        order.setStatus("1");
        if(shopPropertiesWorkOrderCsService.applyRepairCs(order)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 商户查看自己正在处理的电话订单
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/tel/handing/{offset}/{limit}",  method = RequestMethod.GET)
    public PageResult queryHanlding(@PathVariable("offset")Integer offset, @PathVariable("limit")Integer limit){
        IPage<Map<String, Object>> mapIPage = shopPropertiesWorkOrderCsService.queryHandling(offset, limit);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(mapIPage.getPages());
        grid.setRecords(mapIPage.getTotal());
        grid.setRows(mapIPage.getRecords());
        return grid;
    }

    /**
     * 商户物业 商户查看自己的订单的详情
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/telorder/{id}",  method = RequestMethod.GET)
    public ShopPropertiesWorkOrderCs selectByOrderId(@PathVariable("id")String id){
        return shopPropertiesWorkOrderCsService.selectByOrderId(id);
    }

    /**
     * 商户物业 商户保存电话订单信息
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/telorder/save",  method = RequestMethod.POST)
    public JSONResult saveOrder(@RequestBody ShopPropertiesWorkOrderCs order){
        if(shopPropertiesWorkOrderCsService.saveTelOrder(order)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 商户删除正在处理的订单
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "sp/shop/telorder/delete/{id}",method = RequestMethod.GET)
    public JSONResult deleteOrder(@PathVariable("id")String id){
        if(shopPropertiesWorkOrderCsService.deleteTelOrder(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户无物业 维修部员工查看电话订单代派列表
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/manager/tel/untreat/{offset}/{limit}",  method = RequestMethod.GET)
    public PageResult queryUntreat(@PathVariable("offset")Integer offset, @PathVariable("limit")Integer limit){
        IPage<Map<String, Object>> mapIPage = shopPropertiesWorkOrderCsService.queryUntreat(offset, limit);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(mapIPage.getPages());
        grid.setRecords(mapIPage.getTotal());
        grid.setRows(mapIPage.getRecords());
        return grid;
    }

    /**
     * 商户物业 商户查看自己已完成订单（待评价）
     * @param offset
     * @param limit
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/tel/order/finish/{offset}/{limit}/{id}", method = RequestMethod.GET)
    public PageResult queryFinish(@PathVariable("offset")Integer offset,
                                  @PathVariable("limit")Integer limit,
                                  @PathVariable("id")String id){
        IPage<ShopPropertiesWorkOrderCs> page = shopPropertiesWorkOrderCsService.getFinishById(offset, limit, id);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(page.getPages());
        grid.setRecords(page.getTotal());
        grid.setRows(page.getRecords());
        return grid;
    }

    /**
     *  商户物业 商户评价订单
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/tel/order/evaluate", method = RequestMethod.POST)
    public JSONResult evaluateOrder(@RequestBody ShopPropertiesWorkOrderCs order){
        if(shopPropertiesWorkOrderCsService.evaluateOrder(order)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 维修部员工 待审核电话订单
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/manager/tel/order/auditRefused/{offset}/{limit}", method = RequestMethod.GET)
    public PageResult queryUnaudit(@PathVariable("offset")Integer offset,
                                   @PathVariable("limit")Integer limit){
        IPage<ShopPropertiesWorkOrderCs> page = shopPropertiesWorkOrderCsService.queryUnaudit(offset,limit);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(page.getPages());
        grid.setRecords(page.getTotal());
        grid.setRows(page.getRecords());
        return grid;
    }

    /**
     * 商户物业 根据订单id获取订单详情
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/tel/order/{id}", method = RequestMethod.GET)
    public ShopPropertiesWorkOrderCs getById(@PathVariable("id")String id){
        return shopPropertiesWorkOrderCsService.getById(id);
    }

    /**
     * 商户物业 商户撤销订单
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/tel/order/recall/{id}", method = RequestMethod.GET)
    public JSONResult recall(@PathVariable("id") String id){
        //更改订单状态为撤回订单订单
        //更改派单状态为撤回订单订单
        if(
                shopPropertiesWorkOrderCsService.updateState(id,"4")&&
                        shopPropertiesDistributeOrderService.updateStateByOrderId(id,"4")
        ){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

}