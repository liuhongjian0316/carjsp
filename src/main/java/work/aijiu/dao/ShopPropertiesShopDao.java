package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.ShopPropertiesShop;


/**
 * (ShopPropertiesShop)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-20 10:24:55
 */
public interface ShopPropertiesShopDao extends BaseMapper<ShopPropertiesShop> {

    /**
     * 商户分页
     * @param page
     * @return
     */
    @Select("select * from shop_properties_shop where status = 1")
    IPage<ShopPropertiesShop> queryPage(Page<ShopPropertiesShop> page);


}