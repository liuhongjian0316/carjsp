package work.aijiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import work.aijiu.entity.ShopPropertiesRepairWorker;
import work.aijiu.entity.ShopPropertiesRepairWorkerType;
import java.util.List;

/**
 * (ShopPropertiesRepairWorkerType)表服务接口
 *
 * @author makejava
 * @since 2020-10-21 15:26:25
 */
public interface ShopPropertiesRepairWorkerTypeService {


    /**
     * 商户物业 根据id 获取维修员类型
     * @param id
     * @return
     */
    ShopPropertiesRepairWorkerType getById(String id);

    /**
     * 获取所有可用维修员类型
     * @return
     */
    List<ShopPropertiesRepairWorkerType> getAll();

    /**
     * 商户物业 维修员类型分页
     * @param pageNum
     * @param pageSize
     * @param type
     * @return
     */
    IPage<ShopPropertiesRepairWorkerType> queryPage(Integer pageNum, Integer pageSize, String type);


    /**
     * 商户物业 增加工种类型
     * @param type
     * @return
     */
    Boolean add(ShopPropertiesRepairWorkerType type);


    /**
     * 商户物业 保存工种信息
     * @param type
     * @return
     */
    Boolean save(ShopPropertiesRepairWorkerType type);

    /**
     * 商户物业 根据id 删除商户信息
     * @param id
     * @return
     */
    Boolean deleteById(String id);

    /**
     * 商户物业 添加工种 查重
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