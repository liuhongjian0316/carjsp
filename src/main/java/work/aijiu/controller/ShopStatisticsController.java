package work.aijiu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.TiimeUtils;
import work.aijiu.entity.ShopPropertiesWorkOrder;
import work.aijiu.service.ShopPropertiesRewardWorkOrderService;
import work.aijiu.service.ShopPropertiesWorkOrderCsService;
import work.aijiu.service.ShopPropertiesWorkOrderService;
import work.aijiu.service.impl.ShopPropertiesWorkOrderCsServiceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 商户统计
 */
@RestController
public class ShopStatisticsController {

    @Autowired
    private ShopPropertiesWorkOrderService shopPropertiesWorkOrderService;
    @Autowired
    private ShopPropertiesRewardWorkOrderService shopPropertiesRewardWorkOrderService;
    @Autowired
    private ShopPropertiesWorkOrderCsService shopPropertiesWorkOrderCsService;

    /**
     * 商户物业 商户的统计
     * @param time
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/statistics/orderDetails",method = RequestMethod.POST)
    public JSONResult orderDetails(@RequestBody List<Date> time,@RequestParam("id")String id){
        /**************************数据类型************************************
         * [                                                                 *
         *   {'日期': '1/1', '普通订单': 1200, '悬赏订单':1000,'电话订单':1000},  *
         *   {'日期': '1/2', '普通订单': 1100, '悬赏订单':1340,'电话订单':1100},  *
         *   {'日期': '1/3', '普通订单': 1400, '悬赏订单':1050,'电话订单':1200},  *
         * ]                                                                 *
         * ********************************************************************
         */
        //接收数据
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String begin = format.format(time.get(0));
        String end = format.format(time.get(1));
        //将日期分段 分成一天一天的
        List<String> intervalTimeList = TiimeUtils.getIntervalTimeList(begin, end, 1);
        intervalTimeList.forEach(i-> System.err.println(i));
        List<Map<String,Object>> data = new ArrayList<>();
        //判断分段size
        Iterator<String> iterator =intervalTimeList.iterator();
        while(iterator.hasNext()) {
            //System.err.println(shopPropertiesWorkOrderService.selByTime(iterator.next(),id));
            data.add(shopPropertiesWorkOrderService.selByTime(iterator.next(),id));
        }
        if(data.size() > 0){
            return JSONResult.ok(data);
        }
        return JSONResult.errorMsg("请选择合法时间");
    }

    /**
     * 商户物业 商户获取各个订单类型的总数
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/statistics/totalOrder",method = RequestMethod.GET)
    public JSONResult totalOrder(@RequestParam("id")String id){
        List<Map<String,Object>> data = new ArrayList<>();
        data.add(shopPropertiesWorkOrderService.getTotalOrder(id));
        data.add(shopPropertiesWorkOrderService.getTotalRewardOrder(id));
        data.add(shopPropertiesWorkOrderService.getTotalTelOrder(id));
        if(data.size()>0){
            return JSONResult.ok(data);
        }
        return JSONResult.errorMsg("数据获取失败");
    }

    /**
     * 商户物业 商户统计自己的所有订单订总数
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/shop/statistics/totalOrders",method = RequestMethod.GET)
    public Integer totalOrders(@RequestParam("id")String id){
        Map<String, Object> totalOrder = shopPropertiesWorkOrderService.getTotalOrder(id);
        Map<String, Object> totalRewardOrder = shopPropertiesWorkOrderService.getTotalRewardOrder(id);
        Map<String, Object> totalTelOrder = shopPropertiesWorkOrderService.getTotalTelOrder(id);
        return Integer.parseInt(totalOrder.get("总计").toString()) +
                Integer.parseInt(totalRewardOrder.get("总计").toString()) +
                Integer.parseInt(totalTelOrder.get("总计").toString()) ;
    }



}
