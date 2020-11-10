package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.House;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * (House)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-16 19:47:10
 */
public interface HouseDao extends BaseMapper<House> {

    /**
     * 房屋分页
     * @param page
     * @param cid
     * @return
     */
    @Select("SELECT " +
            "b.building_num buildingNum,u.unit_number unitNumber, h.house_id houseId,h.house_floor houseFloor,h.house_num houseNum,h.house_name houseName,h.house_type houseType,h.house_area houseArea,h.house_cost_factor houseCostFactor,h.is_shop isShop, h.is_commercial_house isCommercialHouse,h.status,h.state,h.remark " +
            "from " +
            "community c, building b, unit u, house h " +
            "where b.community_id = c.community_id and u.building_id = b.building_id and h.unit_id = u.unit_number " +
            "and c.state = '1' and c.status = '1' and b.status = '1' and u.status = '1' and h.status = '1' " +
            "and h.community_id= #{cid}  ")
    IPage<Map<String,Object>> queryPage(Page<House> page,@Param("cid")String cid);
}