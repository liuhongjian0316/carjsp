package work.aijiu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import work.aijiu.common.utils.JSONResult;
import work.aijiu.common.utils.TiimeUtils;
import work.aijiu.entity.ShopPropertiesRepairWorker;
import work.aijiu.service.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 商户物业管理员 统计
 */
@RestController
public class AdminStatisticsController {

    @Autowired
    private ShopPropertiesShopService shopPropertiesShopService;
    @Autowired
    private ShopPropertiesRepairWorkerService shopPropertiesRepairWorkerService;
    @Autowired
    private ShopPropertiesCustomerServiceService shopPropertiesCustomerServiceService;
    @Autowired
    private ShopPropertiesWorkOrderCsService shopPropertiesWorkOrderCsService;
    @Autowired
    private ShopPropertiesNoticeService shopPropertiesNoticeService;


    /**
     * 商户管理员统计 商户地域分布
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/statistics/shop/countByAddress",method = RequestMethod.GET)
    public List<Map<String,Object>> addressDetails(){
        return shopPropertiesShopService.countShopByAddress();
    }

    /**
     * 商户物业管理员统计 性别分布
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/statistics/repair/countBySex",method = RequestMethod.GET)
    public List<Map<String,Object>> countRepairBySex(){
        return shopPropertiesRepairWorkerService.countRepairBySex();
    }

    /**
     * 商户物业管理员统计 年龄分布
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/statistics/repair/countRepairByAges",method = RequestMethod.GET)
    public List<Map<String,Object>> countRepairByAges(){
        //年龄分段
        //分段 0-18   18-23 24-30 31-40 41-50 50+
        List<Map<String,Object>> data = new ArrayList<>();
        Map<String,Object> map1 = new HashMap<>();
        map1.put("年龄","0-18");
        map1.put("人数",shopPropertiesRepairWorkerService.countRepairBetweenAge(0,18));
        Map<String,Object> map2 = new HashMap<>();
        map2.put("年龄","18-23");
        map2.put("人数",shopPropertiesRepairWorkerService.countRepairBetweenAge(18,23));
        Map<String,Object> map3 = new HashMap<>();
        map3.put("年龄","24-30");
        map3.put("人数",shopPropertiesRepairWorkerService.countRepairBetweenAge(24,30));
        Map<String,Object> map4 = new HashMap<>();
        map4.put("年龄","31-40");
        map4.put("人数",shopPropertiesRepairWorkerService.countRepairBetweenAge(31,40));
        Map<String,Object> map5 = new HashMap<>();
        map5.put("年龄","41-50");
        map5.put("人数",shopPropertiesRepairWorkerService.countRepairBetweenAge(41,50));
        Map<String,Object> map6 = new HashMap<>();
        map6.put("年龄","50+");
        map6.put("人数",shopPropertiesRepairWorkerService.countRepairBetweenAge(50,160));
        data.add(map1);
        data.add(map2);
        data.add(map3);
        data.add(map4);
        data.add(map5);
        data.add(map6);
        return data;
    }

    /**
     * 商户物业管理员统计 地域分布
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/statistics/repair/countByAddress",method = RequestMethod.GET)
    public List<Map<String,Object>> countRepairByAddress(){
        return shopPropertiesRepairWorkerService.countRepairByAddress();
    }

    /**
     * 商户物业管理员统计 客服总数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/statistics/service/total",method = RequestMethod.GET)
    public int totalService(){
        return shopPropertiesCustomerServiceService.countNumOfService();
    }

    /**
     * 商户物业管理员统计 客服处理的订单数
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/statistics/service/orders",method = RequestMethod.GET)
    public int totalTelOrder(){
        return shopPropertiesWorkOrderCsService.countTotalTelOrder();
    }

    /**
     * 商户物业 管理员统计 客服发布的通知
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/statistics/service/countNoticeType",method = RequestMethod.GET)
    public List<Map<String, Object>> countNoticeByType(){
        return shopPropertiesNoticeService.countByType();
    }
    /**
     * 商户物业管理员统计 客服发布的通知
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sp/admin/statistics/service/numOfNotice",method = RequestMethod.GET)
    public int totalNotice(){
        return shopPropertiesNoticeService.numOfNotice();
    }
}
