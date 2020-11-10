package work.aijiu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.TiimeUtils;
import work.aijiu.service.ShopPropertiesDistributeOrderService;
import work.aijiu.service.ShopPropertiesRewardWorkOrderService;
import work.aijiu.service.ShopPropertiesWorkOrderCsService;
import work.aijiu.service.ShopPropertiesWorkOrderService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 维修员统计
 */
@RestController
public class RepairStatisticsController {

    @Autowired
    private ShopPropertiesDistributeOrderService shopPropertiesDistributeOrderService;

    /**
     * 商户物业 商户的统计
     * @param time
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/statistics/orderDetails",method = RequestMethod.POST)
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
        List<Map<String,Object>> data = new ArrayList<>();
        //判断分段size
        Iterator<String> iterator =intervalTimeList.iterator();
        while(iterator.hasNext()) {
            data.add(shopPropertiesDistributeOrderService.repairOrderByTime(id,iterator.next()));
        }
        if(data.size() > 0){
            return JSONResult.ok(data);
        }
        return JSONResult.errorMsg("请选择合法时间");
    }

    /**
     * 统计维修员总共处理的订单
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/statistics/totalOrders/{id}",method = RequestMethod.GET)
    public int totalOrders(@PathVariable("id")String id){
        return shopPropertiesDistributeOrderService.totalOrders(id);
    }

    /**
     * 维修员统计 统计维修员处理的各个类型的订单
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/statistics/reparirTypeOrder/{id}",method = RequestMethod.GET)
    public List<Map<String,Object>> reparirTypeOrder(@PathVariable("id")String id){
        return shopPropertiesDistributeOrderService.reparirTypeOrder(id);
    }

    /**
     * 维修员统计 处理各类订单（普通，电话，悬赏）
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/statistics/repairMethodOrder/{id}",method = RequestMethod.GET)
    public List<Map<String,Object>> repairMethodOrder(@PathVariable("id")String id){
        List<Map<String,Object>> data = new ArrayList<>();
        //普通订单数量map
        Map<String, Object> countOrder = shopPropertiesDistributeOrderService.countOrder(id);
        countOrder.put("反应速度好评",shopPropertiesDistributeOrderService.numOfOrderReact(id));
        countOrder.put("专业水平好评",shopPropertiesDistributeOrderService.numOfOrderProfession(id));
        countOrder.put("服务态度好评",shopPropertiesDistributeOrderService.numOfOrderAttitude(id));
        data.add(countOrder);
        //电话订单数量map
        Map<String, Object> countCsOrder = shopPropertiesDistributeOrderService.countCsOrder(id);
        countCsOrder.put("反应速度好评",shopPropertiesDistributeOrderService.numOfCsOrderReact(id));
        countCsOrder.put("专业水平好评",shopPropertiesDistributeOrderService.numOfCsOrderProfession(id));
        countCsOrder.put("服务态度好评",shopPropertiesDistributeOrderService.numOfCsOrderAttitude(id));
        data.add(countCsOrder);
        //悬赏订单数量map
        Map<String, Object> countRwOrder = shopPropertiesDistributeOrderService.countRwOrder(id);
        countRwOrder.put("反应速度好评",shopPropertiesDistributeOrderService.numOfRwOrderReact(id));
        countRwOrder.put("专业水平好评",shopPropertiesDistributeOrderService.numOfRwOrderProfession(id));
        countRwOrder.put("服务态度好评",shopPropertiesDistributeOrderService.numOfRwOrderAttitude(id));
        data.add(countRwOrder);
        return data;
    }

    /**
     * 维修员统计昨日收入
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/statistics/yesterdayEarnings/{id}",method = RequestMethod.GET)
    public int yesterdayEarnings(@PathVariable("id")String id){
        return shopPropertiesDistributeOrderService.yesterdayEarnings(id);
    }

    /**
     * 维修员统计近30天收入
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/statistics/monthEarnings/{id}",method = RequestMethod.GET)
    public int monthEarnings(@PathVariable("id")String id){
        return shopPropertiesDistributeOrderService.monthEarnings(id);
    }

    /**
     * 维修员统计昨本月收入
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/statistics/currentEarnings/{id}",method = RequestMethod.GET)
    public int currentEarnings(@PathVariable("id")String id){
        return shopPropertiesDistributeOrderService.currentEarnings(id);
    }

    /**
     * 维修员统计总共收入
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/statistics/totalEarnings/{id}",method = RequestMethod.GET)
    public int totalEarnings(@PathVariable("id")String id){
        return shopPropertiesDistributeOrderService.totalEarnings(id);
    }

    /**
     * 维修员统计可提现收入
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/statistics/canCarryEarnings/{id}",method = RequestMethod.GET)
    public int canCarryEarnings(@PathVariable("id")String id){
        return shopPropertiesDistributeOrderService.canCarryEarnings(id);
    }


    /**
     * 维修员统计 维修员收入曲线图（时间段分割）
     * @param time
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/repair/statistics/earningsDetails",method = RequestMethod.POST)
    public JSONResult earningsDetails(@RequestBody List<Date> time,@RequestParam("id")String id){
        /**************************数据类型************************************
         * [                                                                 *
         *   {'日期': '1/1', '收入': 1200, '悬赏订单':1000,'电话订单':1000},  *
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
        List<Map<String,Object>> data = new ArrayList<>();
        //判断分段size
        Iterator<String> iterator =intervalTimeList.iterator();
        while(iterator.hasNext()) {
            String next = iterator.next();
            Map<String,Object> map = new HashMap<>();
            map.put("日期",next);
            map.put("收入",shopPropertiesDistributeOrderService.repairEarnByTime(id,next));
            data.add(map);
        }
        if(data.size() > 0){
            return JSONResult.ok(data);
        }
        return JSONResult.errorMsg("请选择合法时间");
    }


    /**
     * 管理员统计订单
     * @param time
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/statistics/order/adminOrderDetails",method = RequestMethod.POST)
    public JSONResult adminOrderDetails(@RequestBody List<Date> time){
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
        List<Map<String,Object>> data = new ArrayList<>();
        //判断分段size
        Iterator<String> iterator =intervalTimeList.iterator();
        while(iterator.hasNext()) {
            data.add(shopPropertiesDistributeOrderService.adminOrderByTime(iterator.next()));
        }
        if(data.size() > 0){
            return JSONResult.ok(data);
        }
        return JSONResult.errorMsg("请选择合法时间");
    }

}
