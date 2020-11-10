package work.aijiu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.common.utils.DateUtil;
import work.aijiu.common.utils.IdWorker;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.PageResult;
import work.aijiu.entity.ShopPropertiesDistributeOrder;
import work.aijiu.entity.ShopPropertiesRewardWorkOrder;
import work.aijiu.service.ShopPropertiesDistributeOrderService;
import work.aijiu.service.ShopPropertiesRepairWorkerService;
import work.aijiu.service.ShopPropertiesRewardWorkOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * (ShopPropertiesRewardWorkOrder)表控制层
 *
 * @author makejava
 * @since 2020-11-01 10:39:58
 */
@RestController
public class ShopPropertiesRewardWorkOrderController {
    /**
     * 服务对象
     */
    @Autowired
    private ShopPropertiesRewardWorkOrderService shopPropertiesRewardWorkOrderService;
    @Autowired
    private ShopPropertiesDistributeOrderService shopPropertiesDistributeOrderService;
    @Autowired
    private ShopPropertiesRepairWorkerService shopPropertiesRepairWorkerService;

    /**
     * 商户用户 商户发布悬赏订单
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/reward/order/publish", method = RequestMethod.POST)
    public JSONResult appluOrder(@RequestBody ShopPropertiesRewardWorkOrder order){
        IdWorker worker = new IdWorker(1,1,1);
        order.setRewardWorkOderId(worker.nextId()+"");
        order.setWorkNumber(order.getRewardWorkOderId());
        order.setCreateTime(new Date());
        //设置默认订单状态（0 未受理）
        order.setState("0");
        //设置默认数据状态 （1 可用）
        order.setStatus("1");
        //支付状态
        order.setPayState("1");
        //支付金额
        order.setTotalMoney(order.getRewardMoney());
        if(shopPropertiesRewardWorkOrderService.publishRepairOrder(order)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 商户查看自动的正在进行的悬赏订单
     * @param offset
     * @param limit
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/reward/handing/{offset}/{limit}/{id}", method = RequestMethod.GET)
    public PageResult queryHandlingPage(@PathVariable("offset")Integer offset,
                                        @PathVariable("limit")Integer limit,@PathVariable("id")String id){
        IPage<Map<String, Object>> mapIPage = shopPropertiesRewardWorkOrderService.queryHandling(offset, limit,id);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(mapIPage.getPages());
        grid.setRecords(mapIPage.getTotal());
        grid.setRows(mapIPage.getRecords());
        return grid;
    }


    /**
     * 商户物业 商户查看自己的悬赏订单
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/reward/order/{id}", method = RequestMethod.GET)
    public ShopPropertiesRewardWorkOrder getById(@PathVariable("id")String id){
        return shopPropertiesRewardWorkOrderService.getById(id);
    }

    /**
     * 商户物业 商户删除订单
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/reward/order/delete/{id}", method = RequestMethod.GET)
    public JSONResult deleteOrder(@PathVariable("id")String id){
        if(shopPropertiesRewardWorkOrderService.deleteById(id)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 商户保存悬赏订单
     * @param order
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/reward/order/save", method = RequestMethod.POST)
    public JSONResult save(@RequestBody ShopPropertiesRewardWorkOrder order){
        if(shopPropertiesRewardWorkOrderService.saveOrder(order)){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 待抢单列表
     * @param offset
     * @param limit
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/grabOrder/list/{offset}/{limit}", method = RequestMethod.GET)
    public PageResult queryUnGrabOrderList(@PathVariable("offset")Integer offset, @PathVariable("limit")Integer limit){
        IPage<Map<String, Object>> mapIPage = shopPropertiesRewardWorkOrderService.queryUngrabOrderList(offset, limit);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(mapIPage.getPages());
        grid.setRecords(mapIPage.getTotal());
        grid.setRows(mapIPage.getRecords());
        return grid;
    }


    /**
     * 商户物业 维修员接单
     * @param id
     * @param worker
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/reward/order/receive/{id}/{worker}", method = RequestMethod.GET)
    public JSONResult receiveOrder  (@PathVariable("id")String id, @PathVariable("worker")String worker){
        //将悬赏订单状态更新为已接单（1）
        //将悬赏订单的维修员更改为本身
        //维修员状态改为维修中
        if(shopPropertiesRewardWorkOrderService.updateState(id,"1")&&
        shopPropertiesRewardWorkOrderService.updateWorker(id,worker)&&
        shopPropertiesRepairWorkerService.updateState(worker,"0")){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 维修员查看自己正在进行的悬赏订单
     * @param offset
     * @param limit
     * @param worker
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/grabOrder/hanlding/{offset}/{limit}/{worker}", method = RequestMethod.GET)
    public PageResult queryRWHandling  (@PathVariable("offset")Integer offset,@PathVariable("limit")Integer limit, @PathVariable("worker")String worker){
        IPage<Map<String, Object>> mapIPage = shopPropertiesRewardWorkOrderService.queryRWHandling(offset, limit,worker);
        PageResult grid = new PageResult();
        grid.setPage(offset);
        grid.setTotal(mapIPage.getPages());
        grid.setRecords(mapIPage.getTotal());
        grid.setRows(mapIPage.getRecords());
        return grid;
    }

    /**
     * 商户用户 维修员退单（没维修之前）
     * @param id
     * @param worker
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/reward/order/chargeBack/{id}/{worker}", method = RequestMethod.GET)
    public JSONResult chargeBack  (@PathVariable("id")String id, @PathVariable("worker")String worker){
        //将悬赏订单状态更新为（0）
        //将悬赏订单的维修员更改为""
        //维修员状态改为正常1
        if(shopPropertiesRewardWorkOrderService.updateState(id,"0")&&
                shopPropertiesRewardWorkOrderService.updateWorker(id,"")&&
                shopPropertiesRepairWorkerService.updateState(worker,"1")
        ){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     *商户物业 维修员退单 维修中等土突发情况退单
     * @param id
     * @param worker
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/reward/order/chargeBackAfter/{id}/{worker}", method = RequestMethod.GET)
    public JSONResult chargeBackAfter  (@PathVariable("id")String id, @PathVariable("worker")String worker){
        //将悬赏订单状态更新为（over）
        //将悬赏订单的维修员更改为"over"
        //维修员状态改为正常1
        if(shopPropertiesRewardWorkOrderService.updateState(id,"over")&&
                shopPropertiesRepairWorkerService.updateState(worker,"1")&&
                shopPropertiesDistributeOrderService.updateStateByOrderId(id,"over")
        ){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 维修员开始维修悬赏订单
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/reward/order/beginRepair/{worker}", method = RequestMethod.POST)
    public JSONResult beginRepair(@RequestBody Map<String,Object> map,@PathVariable("worker")String worker){
        String create_time = map.get("create_time").toString();
        String dateToString = DateUtil.getDateToString(Long.valueOf(create_time));
        Date stringToDate = DateUtil.getStringToDate(dateToString);
        //订单状态更改为3
        //shopPropertiesRewardWorkOrderService.updateState(map.get("reward_work_oder_id").toString(),"3");
        //订单维修员更改为worker
        //shopPropertiesRewardWorkOrderService.updateWorker(map.get("reward_work_oder_id").toString(),worker);
        //将订单数据填充到为派单数据
        ShopPropertiesDistributeOrder order = new ShopPropertiesDistributeOrder();
        IdWorker idWorker = new IdWorker(1,1,1);
        //设置派单id
        order.setDistributeOrderId(idWorker.nextId()+"");
        order.setWorkOrderId(map.get("reward_work_oder_id").toString());
        order.setWorkType(map.get("work_type_id").toString());
        order.setWorkNumber(map.get("work_number").toString());
        order.setShopId(map.get("shop_id").toString());
        order.setShopName(map.get("shop_name").toString());
        order.setApplyName(map.get("apply_name").toString());
        order.setTel(map.get("tel").toString());
        order.setReserveTime(stringToDate);
        order.setAddress(map.get("address").toString());
        order.setState("3");
        order.setRemark(map.get("remark").toString());
        order.setStatus("1");
        order.setFaultDescriptionContent(map.get("fault_description_content").toString());
        order.setFaultDescription(map.get("fault_description").toString());
        order.setWorker(worker);
        order.setTelmark("0");
        order.setRewardmark("1");
        order.setIsReinforce("0");
        order.setEmployeeId(worker);
        if(shopPropertiesRewardWorkOrderService.updateState(map.get("reward_work_oder_id").toString(),"3")&&
                shopPropertiesRewardWorkOrderService.updateWorker(map.get("reward_work_oder_id").toString(),worker)&&
                shopPropertiesDistributeOrderService.addOrder(order)
        ){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }

    /**
     * 商户物业 维修员完成维修 订单结束
     * @param map
     * @param worker
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/reward/order/finish/{worker}", method = RequestMethod.POST)
    public JSONResult finish(@RequestBody Map<String,Object> map,@PathVariable("worker")String worker){
        //悬赏订单 派单 状态更改为 unmark
        //维修员状态改为正常状态 1
        if(
                shopPropertiesRewardWorkOrderService.updateState(map.get("reward_work_oder_id").toString(),"unmark")&&
                        shopPropertiesRepairWorkerService.updateState(worker,"1")&&
                        shopPropertiesDistributeOrderService.updateStateByOrderId(map.get("reward_work_oder_id").toString(),"unmark")
        ){
            return JSONResult.ok();
        }
        return JSONResult.errorMsg("操作失败");
    }
}