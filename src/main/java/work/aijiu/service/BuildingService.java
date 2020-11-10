package work.aijiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import work.aijiu.entity.Building;

import java.util.List;

/**
 * (Building)表服务接口
 *
 * @author makejava
 * @since 2020-10-15 19:33:35
 */
public interface BuildingService {

    /**
     * 楼栋分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<Building> queryPage(Integer pageNum, Integer pageSize);

    /**
     * 根据id 获取楼栋信息
     * @param id
     * @return
     */
    Building queryById(String id);

    /**
     * 添加楼栋
     * @param building
     * @return
     */
    Boolean addBuilding(Building building);

    /**
     * 修稿楼栋信息
     * @param building
     * @return
     */
    Boolean save(Building building);

    /**
     * 根据id删除楼栋信息
     * @param id
     * @return
     */
    Boolean delete(String id);

    /**
     * 根据小区id获取楼栋信息
     * @param id
     * @return
     */
    List<Building> queryListByCid(String id);
}