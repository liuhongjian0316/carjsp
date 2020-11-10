package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.ShopPropertiesRepairWorkerType;
import org.apache.ibatis.annotations.Param;
import java.util.Map;

/**
 * (ShopPropertiesRepairWorkerType)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-21 15:26:25
 */
public interface ShopPropertiesRepairWorkerTypeDao extends BaseMapper<ShopPropertiesRepairWorkerType> {

}