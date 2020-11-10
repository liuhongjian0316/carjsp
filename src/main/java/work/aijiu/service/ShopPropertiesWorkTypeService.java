package work.aijiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sun.org.apache.xpath.internal.operations.Bool;
import work.aijiu.entity.ShopPropertiesWorkType;

import javax.management.Query;
import java.util.List;

/**
 * (ShopPropertiesWorkType)表服务接口
 *
 * @author makejava
 * @since 2020-10-25 22:16:50
 */
public interface ShopPropertiesWorkTypeService {

    /**
     * 查询维修类型
     * @return
     */
    public List<ShopPropertiesWorkType> QueryTypeList();

    /**
     * 商户物业 工单类型分页
     * @param pageNum
     * @param pageSize
     * @param type
     * @return
     */
    IPage<ShopPropertiesWorkType> queryPage(Integer pageNum, Integer pageSize,String type);

    /**
     * 商户物业 增加工单类型
     * @param type
     * @return
     */
    Boolean addType(ShopPropertiesWorkType type);

    /**
     * 商户物业 根据id获取工单类型
     * @param id
     * @return
     */
    ShopPropertiesWorkType getById(String id);

    /**
     * 商户物业 根据id删除工单类型
     * @param id
     * @return
     */
    Boolean deleteById(String id);

    /**
     * 商户物业 保存工单类型
     * @param type
     * @return
     */
    Boolean save(ShopPropertiesWorkType type);

    /**
     * 商户物业 添加工单类型 查重
     * @param name
     * @return
     */
    Boolean checkAddType(String name);

    /**
     * 商户物业 编辑工单 查重
     * @param name
     * @return
     */
    Boolean checkEditType(String name,String id);
}