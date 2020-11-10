package work.aijiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import work.aijiu.entity.ShopPropertiesShop;

import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesShop)表服务接口
 *
 * @author makejava
 * @since 2020-10-20 10:24:55
 */
public interface ShopPropertiesShopService {

    /**
     * 商户分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<ShopPropertiesShop> queryPage(Integer pageNum, Integer pageSize);


    /**
     * 商户物业添加商户
     * @param shopPropertiesShop
     * @return
     */
    Boolean addShop(ShopPropertiesShop shopPropertiesShop);

    /**
     * 根据id 获取商户数据
     * @param id
     * @return
     */
    ShopPropertiesShop getById(String id);

    /**
     * 物业修改商户信息
     * @param shopPropertiesShop
     * @return
     */
    Boolean save(ShopPropertiesShop shopPropertiesShop);

    /**
     * 根据id 删除商户信息
     * @param id
     * @return
     */
    Boolean delete(String id);

    /**
     * 模糊查询+分页
     * @param pageNum
     * @param pageSize
     * @param shopPropertiesShop
     * @return
     */
    IPage<ShopPropertiesShop> queryLike(Integer pageNum, Integer pageSize,ShopPropertiesShop shopPropertiesShop);

    /**
     *  添加手机号查重
     * @param tel
     * @return
     */
    Boolean checkTel(String tel);

    /**
     * 编辑商户时查重
     * @param tel
     * @param id
     * @return
     */
    Boolean checkEdit(String tel,String id);

    /**
     * 商户物业 商户登录
     * @param username
     * @param pwd
     * @return
     */
    List<ShopPropertiesShop> login(String username,String pwd);

    /**
     * 商户物业 按地区统计商户
     * @return
     */
    List<Map<String,Object>> countShopByAddress();
}