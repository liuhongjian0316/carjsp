package work.aijiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import work.aijiu.entity.ShopPropertiesCustomerService;

import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesCustomerService)表服务接口
 *
 * @author makejava
 * @since 2020-10-22 13:31:34
 */
public interface ShopPropertiesCustomerServiceService {

    /**
     * 商户物业 查询客服分页
     * @param pageNum
     * @param pageSize
     * @param terms
     * @return
     */
    IPage<Map<String,Object>> queryPage(Integer pageNum, Integer pageSize,String terms);

    /**
     * 商户物业 增加客服
     * @param customerService
     * @return
     */
    Boolean addService(ShopPropertiesCustomerService customerService);

    /**
     * 商户物业 根据id获取客服信息
     * @param id
     * @return
     */
    ShopPropertiesCustomerService getById(String id);

    /**
     * 商户客服  保存客服信息
     * @param customerService
     * @return
     */
    Boolean saveService(ShopPropertiesCustomerService customerService);

    /**
     * 商户物业根据id删除客服
     * @param id
     * @return
     */
    Boolean deleteService(String id);

    /**
     * 商户物业 重置客服密码
     * @param id
     * @return
     */
    Boolean resetPwd(String id,String pwd);

    /**
     * 商户物业增加客服手机号查重
     * @param tel
     * @return
     */
    Boolean checkAddTel(String tel);

    /**
     * 商户物业编辑客服 手机号查重
     * @param tel
     * @param id
     * @return
     */
    Boolean checkEditTel(String tel,String id);

    /**
     * 商户物业 客服登录
     * @param username
     * @param pwd
     * @return
     */
    List<ShopPropertiesCustomerService> login(String username, String pwd);

    /**
     * 商户管理员统计 客服数量
     * @return
     */
    int countNumOfService();
}