package work.aijiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sun.org.apache.xpath.internal.operations.Bool;
import work.aijiu.entity.ShopPropertiesRepairWorker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesRepairWorker)表服务接口
 *
 * @author makejava
 * @since 2020-10-21 15:25:02
 */
public interface ShopPropertiesRepairWorkerService {

    /**
     * 商户物业维修员分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<HashMap<String,Object>> queryPage(Integer pageNum, Integer pageSize, String terms);

    /**
     * 商户物业添加
     * @param shopPropertiesRepairWorker
     * @return
     */
    Boolean addWorker(ShopPropertiesRepairWorker shopPropertiesRepairWorker);

    /**
     * 商户物业添加 维修员信息时 手机号查重
     * @param tel
     * @return
     */
    Boolean checkAddWorkerTel(String tel);

    /**
     * 商户物业添加 维修员信息时 身份证号查重
     * @param card
     * @return
     */
    Boolean checkAddWorkerCard(String card);

    /**
     * 商户物业根据id获取维修员信息
     * @param id
     * @return
     */
    ShopPropertiesRepairWorker getById(String id);

    /**
     * 商户物业编辑维修员手机号查重
     * @param tel
     * @param id
     * @return
     */
    Boolean checkEditWorkerTel(String tel,String id);

    /**
     * 商户物业编辑维修员身份证号查重
     * @param card
     * @param id
     * @return
     */
    Boolean checkEditWorkerCard(String card,String id);

    /**
     * 修改维修员信息
     * @param worker
     * @return
     */
    Boolean saveWorker(ShopPropertiesRepairWorker worker);

    /**
     * 商户物业 根据id删除维修员
     * @param id
     * @return
     */
    Boolean deleteWorkerById(String id);

    /**
     * 商户物业 重置维修员密码
     * @param id
     * @param pwd
     * @return
     */
    Boolean resetpwd(String id, String pwd);


    /**
     * 商户物业 审核人员 根据id修改维修员状态
     * @param id
     * @param state
     * @return
     */
    Boolean updateState(String id,String state);

    /**
     * 商户物业 维修员登录
     * @param username
     * @param pwd
     * @return
     */
    List<ShopPropertiesRepairWorker> login(String username,String pwd);

    /**
     * 商户物业 商户物业后台统计 维修员地域分布
     * @return
     */
    List<Map<String,Object>> countRepairByAddress();

    /**
     * 商户物业 商户物业后台统计 维修员性别分布
     * @return
     */
    List<Map<String,Object>> countRepairBySex();

    /**
     * 商户物业 商户物业后台统计 维修员年龄分布
     * @param younger
     * @param older
     * @return
     */
    int countRepairBetweenAge(Integer younger,Integer older);

}