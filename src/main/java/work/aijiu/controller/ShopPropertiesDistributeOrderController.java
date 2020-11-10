package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import work.aijiu.common.utils.DateUtil;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.ShopPropertiesDistributeOrder;
import work.aijiu.entity.ShopPropertiesRepairWorker;
import work.aijiu.service.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesDistributeOrder)表控制层
 *
 * @author makejava
 * @since 2020-10-26 18:30:25
 */
@RestController
public class ShopPropertiesDistributeOrderController {
    /**
     * 服务对象
     */
    @Autowired
    private ShopPropertiesDistributeOrderService shopPropertiesDistributeOrderService;
    @Autowired
    private ShopPropertiesWorkOrderService shopPropertiesWorkOrderService;
    @Autowired
    private ShopPropertiesRepairWorkerService shopPropertiesRepairWorkerService;
    @Autowired
    private ShopPropertiesWorkOrderCsService shopPropertiesWorkOrderCsService;


    /**
     * 商户物业处理报修单
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/manager/handleOrder", method = RequestMethod.POST)
    public JSONResult handleOrder(@RequestBody HashMap<String,Object> map){
        //string 时间戳 转换成 时间
        String reserve_time = map.get("reserve_time").toString();
        String dateToString = DateUtil.getDateToString(Long.valueOf(reserve_time));
        Date stringToDate = DateUtil.getStringToDate(dateToString);

        //将报修单的信息录入派单中
        IdWorker worker = new IdWorker(1,1,1);
        ShopPropertiesDistributeOrder order = new ShopPropertiesDistributeOrder();
        order.setDistributeOrderId(worker.nextId()+"");
        order.setWorkOrderId(map.get("work_order_id").toString());
        order.setWorkType(map.get("work_type_id").toString());
        order.setWorkNumber(map.get("work_order_id").toString());
        order.setShopId(map.get("shop_id").toString());
        order.setShopName(map.get("shop_name").toString());
        order.setApplyName(map.get("apply_name").toString());
        order.setTel(map.get("tel").toString());
        order.setWorker(map.get("repair_worker_id").toString());
        order.setAddress(map.get("address").toString());
        order.setReserveTime(stringToDate);
        order.setState("1");
        order.setStatus("1");
        order.setRemark(map.get("remark").toString());
        order.setFaultDescription(map.get("fault_description"));
        order.setFaultDescriptionContent(map.get("fault_description_content"));
        order.setIsReinforce("0");
        order.setEmployeeId(map.get("employee_id").toString());
        if(
        shopPropertiesRepairWorkerService.updateState(map.get("repair_worker_id").toString(),"0")&&
                shopPropertiesWorkOrderService.updateWorker(map.get("work_order_id").toString(),map.get("repair_worker_id").toString())&&
        shopPropertiesWorkOrderService.updateState(map.get("work_order_id").toString(),"1")&&
        shopPropertiesDistributeOrderService.addOrder(order)
        ){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("处理失败");
    }

    /**
     * 商户物业 维修员查看自己的待办
     * @param offset
     * @param limit
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/untreat/{offset}/{limit}/{id}", method = RequestMethod.GET)
    public PageResult queryPage(@PathVariable("offset")Integer offset,
                                @PathVariable("limit")Integer limit,
                                @PathVariable("id")String id){
        IPage<ShopPropertiesDistributeOrder> order = shopPropertiesDistributeOrderService.getBySelfId(offset, limit, id);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(order.getPages());
        grid.setRecords(order.getTotal());
        grid.setRows(order.getRecords());
        return  grid;
    }

    /**
     * 商户物业 维修员根据派单id查派单详情
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/order/{id}", method = RequestMethod.GET)
    public ShopPropertiesDistributeOrder getDatilsByid(@PathVariable("id")String id){
        return shopPropertiesDistributeOrderService.getDetailsById(id);
    }

    /**
     * 商户物业 维修员接单
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/order/receive", method = RequestMethod.POST)
    public JSONResult receiveOrder(@RequestBody ShopPropertiesDistributeOrder order){
        //判断是否为电话订单
        if("1".equals(order.getTelmark())){
            System.err.println("走着了");
            if(
                    shopPropertiesDistributeOrderService.updateStateById(order.getDistributeOrderId(),"3")&&
                            shopPropertiesWorkOrderCsService.updateState(order.getWorkOrderId(),"3")
            ){
                return JSONResult.ok();
            }else{
                return JSONResult.errorMsg("操作失败");
            }
        }else{
            if(
                    shopPropertiesDistributeOrderService.updateStateById(order.getDistributeOrderId(),"3")&&
                            shopPropertiesWorkOrderService.updateState(order.getWorkOrderId(),"3")
            ){
                return JSONResult.ok();
            }else{
                return JSONResult.errorMsg("操作失败");
            }
        }
    }

    /**
     * 商户物业 维修员查看自己的正在处理列表
     * @param offset
     * @param limit
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/handling/{offset}/{limit}/{id}", method = RequestMethod.GET)
    public PageResult queryHandlingPage(@PathVariable("offset")Integer offset,
                                @PathVariable("limit")Integer limit,
                                @PathVariable("id")String id){
        IPage<ShopPropertiesDistributeOrder> order = shopPropertiesDistributeOrderService.getHandlingBySelfId(offset, limit, id);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(order.getPages());
        grid.setRecords(order.getTotal());
        grid.setRows(order.getRecords());
        return  grid;
    }

    /**
     * 商户物业 维修员完成订单
     * @param order
     * @param id 维修员id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/order/finish/{id}", method = RequestMethod.POST)
    public JSONResult finishOrder(@PathVariable("id")String id,@RequestBody ShopPropertiesDistributeOrder order){
        //将派单和订单的状态改为 为评论（unramk）
        //维修员的状态改为空闲（1）
        //判断是否为电话订单
        if("1".equals(order.getTelmark())){
            if(
                    shopPropertiesDistributeOrderService.updateStateById(order.getDistributeOrderId(),"unmark")&&
                            shopPropertiesWorkOrderCsService.updateState(order.getWorkOrderId(),"unmark")&&
                            shopPropertiesRepairWorkerService.updateState(id,"1")
            ){
                return JSONResult.ok();
            }else {
                return JSONResult.errorMsg("操作失败");
            }
        }else{
            if(
                    shopPropertiesDistributeOrderService.updateStateById(order.getDistributeOrderId(),"unmark")&&
                            shopPropertiesWorkOrderService.updateState(order.getWorkOrderId(),"unmark")&&
                            shopPropertiesRepairWorkerService.updateState(id,"1")
            ){
                return JSONResult.ok();
            }else {
                return JSONResult.errorMsg("操作失败");
            }
        }
        }

    /**
     * 商户物业，维修员申请增援
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/order/applayReinforce/{id}",method = RequestMethod.GET)
    public JSONResult applayReinforce(@PathVariable("id") String id){
        if(shopPropertiesDistributeOrderService.updateReinforceById(id,"1")){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 维修部员工查看 待处理增援列表
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/manager/unReinforce/page/{offset}/{limit}/{id}",method = RequestMethod.GET)
    public PageResult unReinforcePage(@PathVariable("offset")Integer offset,
                                      @PathVariable("limit")Integer limit){
        IPage<ShopPropertiesDistributeOrder> order = shopPropertiesDistributeOrderService.queryUnReinforce(offset, limit);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(order.getPages());
        grid.setRecords(order.getTotal());
        grid.setRows(order.getRecords());
        return  grid;
    }

    /**
     * 商户用户 派发增援维修工
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/manager/reinforce/{id}",method = RequestMethod.POST)
    public JSONResult choiceWorker(@PathVariable("id")String id,@RequestBody List<String> ids){
        //根据id 更改维修员的状态
        ids.forEach(
                i->shopPropertiesRepairWorkerService.updateState(i,"0")
        );
        //根据id 查看 原来的维修员工id
        ShopPropertiesDistributeOrder order = shopPropertiesDistributeOrderService.getByid(id);
        String worker = order.getWorker();
        //将前端传来的ids 数组 和 原来的id 拼接 以逗号分割
        for (int i = 0; i <ids.size() ; i++) {
            worker = worker+","+ids.get(i);
        }
        //派单的增援状态改为0
        if(shopPropertiesDistributeOrderService.reinforceWorker(id,worker)&&
            shopPropertiesDistributeOrderService.updateRsseinforceStateByDid(id,"0")
        ){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }


    /**
     * 商户物业 维修员撤单处理列表
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/recall/list/{offset}/{limit}",method = RequestMethod.GET)
    public PageResult recall(@PathVariable("offset")Integer offset,
                       @PathVariable("limit")Integer limit){
        IPage<ShopPropertiesDistributeOrder> order = shopPropertiesDistributeOrderService.queryUnRecall(offset, limit);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(order.getPages());
        grid.setRecords(order.getTotal());
        grid.setRows(order.getRecords());
        return  grid;
    }

    /**
     * 商户物业 维修员同意商户撤单
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/order/accessRecall",method = RequestMethod.POST)
    public JSONResult accessRecall(@RequestBody ShopPropertiesDistributeOrder order){
        //将订单 和 派单 的状态 改为 结束over 维修员状态更改为1
        //判断是否为电话订单
        if(shopPropertiesWorkOrderCsService.selectById(order.getWorkOrderId())){
            if(shopPropertiesDistributeOrderService.updateStateById(order.getDistributeOrderId(),"over")&&
                    shopPropertiesWorkOrderCsService.updateState(order.getWorkOrderId(),"over")&&
                    shopPropertiesRepairWorkerService.updateState(order.getWorker(),"1")
            ){
                return JSONResult.ok();
            }else{
                return  JSONResult.errorMsg("操作失败");
            }
        }else{
            if(shopPropertiesDistributeOrderService.updateStateById(order.getDistributeOrderId(),"over")&&
                    shopPropertiesWorkOrderService.updateState(order.getWorkOrderId(),"over")&&
                    shopPropertiesRepairWorkerService.updateState(order.getWorker(),"1")
            ){
                return JSONResult.ok();
            }else{
                return  JSONResult.errorMsg("操作失败");
            }
        }
    }

    /**
     * 商户物业 维修员拒绝商户撤单
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/order/refusedRecall",method = RequestMethod.POST)
    public JSONResult refusedRecall(@RequestBody ShopPropertiesDistributeOrder order){
        //将订单 和 派单 的状态 改为 结束over
        if(shopPropertiesDistributeOrderService.updateStateById(order.getDistributeOrderId(),"3")&&
                shopPropertiesWorkOrderService.updateState(order.getWorkOrderId(),"3")
        ){
            return JSONResult.ok();
        }
        return  JSONResult.errorMsg("操作失败");
    }


    @ResponseBody
    @RequestMapping(value = "/sp/manager/tel/handleOrder", method = RequestMethod.POST)
    public JSONResult handleTelOrder(@RequestBody HashMap<String,Object> map){
        //string 时间戳 转换成 时间
        String reserve_time = map.get("reserve_time").toString();
        String dateToString = DateUtil.getDateToString(Long.valueOf(reserve_time));
        Date stringToDate = DateUtil.getStringToDate(dateToString);

        //将报修单的信息录入派单中
        IdWorker worker = new IdWorker(1,1,1);
        ShopPropertiesDistributeOrder order = new ShopPropertiesDistributeOrder();
        order.setDistributeOrderId(worker.nextId()+"");
        order.setWorkOrderId(map.get("work_order_cs_id").toString());
        order.setWorkType(map.get("work_type").toString());
        order.setWorkNumber(map.get("work_order_cs_id").toString());
        order.setShopId(map.get("shop_id").toString());
        order.setShopName(map.get("shop_name").toString());
        order.setApplyName(map.get("apply_name").toString());
        order.setTel(map.get("tel").toString());
        order.setWorker(map.get("repair_worker_id").toString());
        order.setAddress(map.get("address").toString());
        order.setReserveTime(stringToDate);
        order.setState("1");
        order.setStatus("1");
        order.setRemark(map.get("remark").toString());
        order.setFaultDescription(map.get("fault_description"));
        order.setFaultDescriptionContent(map.get("fault_description_content"));
        order.setIsReinforce("0");
        order.setTelmark("1");
        order.setEmployeeId(map.get("employee_id").toString());
        if(
                shopPropertiesRepairWorkerService.updateState(map.get("repair_worker_id").toString(),"0")&&
                        shopPropertiesWorkOrderCsService.updateWorker(map.get("work_order_cs_id").toString(),map.get("repair_worker_id").toString())&&
                        shopPropertiesWorkOrderCsService.updateState(map.get("work_order_cs_id").toString(),"1")&&
                        shopPropertiesDistributeOrderService.addOrder(order)
        ){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("处理失败");
    }
}