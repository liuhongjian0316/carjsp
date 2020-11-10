package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.Building;
import work.aijiu.entity.ShopPropertiesWorkOrder;
import work.aijiu.entity.ShopPropertiesWorkOrderCs;
import work.aijiu.service.ShopPropertiesDistributeOrderService;
import work.aijiu.service.ShopPropertiesRepairWorkerService;
import work.aijiu.service.ShopPropertiesWorkOrderCsService;
import work.aijiu.service.ShopPropertiesWorkOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * (ShopPropertiesWorkOrder)表控制层
 *
 * @author makejava
 * @since 2020-10-25 23:55:05
 */
@RestController
public class ShopPropertiesWorkOrderController {
    /**
     * 服务对象
     */
    @Autowired
    private ShopPropertiesWorkOrderService shopPropertiesWorkOrderService;
    @Autowired
    private ShopPropertiesWorkOrderCsService shopPropertiesWorkOrderCsService;
    @Autowired
    private ShopPropertiesDistributeOrderService shopPropertiesDistributeOrderService;
    @Autowired
    private ShopPropertiesRepairWorkerService shopPropertiesRepairWorkerService;
    /**
     * 商户物业申请报修单
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/applayOrder", method = RequestMethod.POST)
    public JSONResult applyOrder(@RequestBody ShopPropertiesWorkOrder order){
        IdWorker worker = new IdWorker(1,1,1);
        order.setWorkOrderId(worker.nextId()+"");
        order.setWorkNumber(order.getWorkOrderId());
        //未受理
        order.setState("0");
        //数据状态
        order.setStatus("1");
        order.setCreateTime(new Date());
        if(shopPropertiesWorkOrderService.shopApplyOrder(order)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 商户查询 正在处理的保修单
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/handing/{offset}/{limit}", method = RequestMethod.GET)
    public PageResult queryHandlingPage(@PathVariable("offset")Integer offset, @PathVariable("limit")Integer limit){
        IPage<Map<String, Object>> mapIPage = shopPropertiesWorkOrderService.queryHandling(offset, limit);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(mapIPage.getPages());
        grid.setRecords(mapIPage.getTotal());
        grid.setRows(mapIPage.getRecords());
        return grid;
    }

    /**
     * 商户物业 商户查询自己的订单信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/order/{id}", method = RequestMethod.GET)
    public ShopPropertiesWorkOrder getById(@PathVariable("id")String id){
        return shopPropertiesWorkOrderService.getById(id);
    }

    /**
     * 商户物业 商户修改自己的订单
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/order/save", method = RequestMethod.POST)
    public JSONResult savaOrder(@RequestBody ShopPropertiesWorkOrder order){
        if(shopPropertiesWorkOrderService.saveOrder(order)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 商户删除自己的订单
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/order/delete/{id}", method = RequestMethod.GET)
    public JSONResult deleteOrder(@PathVariable("id")String id){
        if(shopPropertiesWorkOrderService.deleteOrderS(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 维修部审核人员待审核列表
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/manager/untreat/{offset}/{limit}", method = RequestMethod.GET)
    public PageResult queryUntreat(@PathVariable("offset")Integer offset, @PathVariable("limit")Integer limit){
        IPage<Map<String, Object>> mapIPage = shopPropertiesWorkOrderService.queryUntreat(offset, limit);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(mapIPage.getPages());
        grid.setRecords(mapIPage.getTotal());
        grid.setRows(mapIPage.getRecords());
        return grid;
    }

    /**
     * 商户物业，商户查看自己已完成的订单
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/order/finish/{offset}/{limit}/{id}", method = RequestMethod.GET)
    public PageResult queryFinish(@PathVariable("offset")Integer offset,
                                  @PathVariable("limit")Integer limit,
                                  @PathVariable("id")String id){
        IPage<ShopPropertiesWorkOrder> page = shopPropertiesWorkOrderService.getFinishById(offset, limit, id);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(page.getPages());
        grid.setRecords(page.getTotal());
        grid.setRows(page.getRecords());
        return grid;
    }

    /**
     * 商户物业 商户评价订单
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/order/evaluate", method = RequestMethod.POST)
    public JSONResult evaluateOrder(@RequestBody ShopPropertiesWorkOrder order){
        if(shopPropertiesWorkOrderService.evaluateOrder(order)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业，维修员 拒绝订单
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/worker/order/refuse", method = RequestMethod.POST)
    public JSONResult refuseOrder(@RequestBody ShopPropertiesWorkOrder order){
        //判断订单是否为
        if(shopPropertiesWorkOrderCsService.selectById(order.getWorkOrderId())){
            ShopPropertiesWorkOrderCs orderCs = new ShopPropertiesWorkOrderCs();
            orderCs.setWorkOrderCsId(order.getWorkOrderId());
            orderCs.setWorker(order.getWorker());
            orderCs.setCause(order.getCause());
            orderCs.setCauseContent(order.getCauseContent());
            orderCs.setState(order.getState());
            if(shopPropertiesWorkOrderCsService.refusedOrder(orderCs)){
                return JSONResult.ok();
            }else {
                return JSONResult.errorMsg("操作失败");
            }

        }else{
            if(shopPropertiesWorkOrderService.refusedOrder(order)){
                return JSONResult.ok();
            }else{
                return JSONResult.errorMsg("操作失败");
            }
        }
    }

    /**
     * 审核人员 待审核拒单列表
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/manager/order/auditRefused/{offset}/{limit}", method = RequestMethod.GET)
    public PageResult queryUnaudit(@PathVariable("offset")Integer offset,
                                  @PathVariable("limit")Integer limit){
        IPage<ShopPropertiesWorkOrder> page = shopPropertiesWorkOrderService.queryUnaudit(offset,limit);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(page.getPages());
        grid.setRecords(page.getTotal());
        grid.setRows(page.getRecords());
        return grid;
    }

    /**
     * 商户物业 审核员工 同意 拒单
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/manager/order/auditRefused/access", method = RequestMethod.POST)
    public JSONResult accessOrder(@RequestBody ShopPropertiesWorkOrder order){
        //订单状态结束
        //派单状态结束
        //维修员状态空闲
        //判断订单是否为订单id
        if(shopPropertiesWorkOrderCsService.selectById(order.getWorkOrderId())){
            if(
                    shopPropertiesWorkOrderCsService.updateState(order.getWorkOrderId(),"over")&&
                            shopPropertiesDistributeOrderService.updateStateByOrderId(order.getWorkOrderId(),"over")&&
                            shopPropertiesRepairWorkerService.updateState(order.getWorker(),"1")
            ){
                return JSONResult.ok();
            }else{
                return JSONResult.errorMsg("操作失败");
            }
        }else{
            if(
                    shopPropertiesWorkOrderService.updateState(order.getWorkOrderId(),"over")&&
                            shopPropertiesDistributeOrderService.updateStateByOrderId(order.getWorkOrderId(),"over")&&
                            shopPropertiesRepairWorkerService.updateState(order.getWorker(),"1")
            ){
                return JSONResult.ok();
            }else{
                return JSONResult.errorMsg("操作失败");
            }
        }
    }


    /**
     * 商户物业 审核员工 回绝 拒单
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/manager/order/auditRefused/noaccess", method = RequestMethod.POST)
    public JSONResult noAccessOrder(@RequestBody ShopPropertiesWorkOrder order){
        //订单状态状态 维修中
        //派单状态改为 维修中
        //判断订单是否为电话订单
        if(shopPropertiesWorkOrderCsService.selectById(order.getWorkOrderId())){
            if(
                    shopPropertiesWorkOrderCsService.updateState(order.getWorkOrderId(),"3")&&
                            shopPropertiesDistributeOrderService.updateStateByOrderId(order.getWorkOrderId(),"3")
            ){
                return JSONResult.ok();
            }else {
                return JSONResult.errorMsg("操作失败");
            }
        }else {
            if(
                    shopPropertiesWorkOrderService.updateState(order.getWorkOrderId(),"3")&&
                            shopPropertiesDistributeOrderService.updateStateByOrderId(order.getWorkOrderId(),"3")
            ){
                return JSONResult.ok();
            }else {
                return JSONResult.errorMsg("操作失败");
            }
        }
    }


    /**
     * 商户物业 商户撤回维修中的订单
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/order/recall/{id}", method = RequestMethod.GET)
    public JSONResult recall(@PathVariable("id") String id){
        //更改订单状态为撤回订单订单
        //更改派单状态为撤回订单订单
        if(
        shopPropertiesWorkOrderService.updateState(id,"4")&&
        shopPropertiesDistributeOrderService.updateStateByOrderId(id,"4")
        ){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }



}