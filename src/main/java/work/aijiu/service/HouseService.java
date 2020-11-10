package work.aijiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import work.aijiu.entity.House;

import java.util.Map;

/**
 * (House)表服务接口
 *
 * @author makejava
 * @since 2020-10-16 19:47:10
 */
public interface HouseService {

    /**
     * 房屋分页
     * @param pageNum
     * @param pageSize
     * @param cid
     * @return
     */
    IPage<Map<String,Object>> queryPage(Integer pageNum, Integer pageSize, String cid);

    /**
     * 添加房屋
     * @param house
     * @return
     */
    Boolean addHouse(House house);

    /**
     * 根据房屋id获取房屋信息
     * @param id
     * @return
     */
    House getById(String id);

    /**
     * 修改房屋信息
     * @param house
     * @return
     */
    Boolean save(House house);

    /**
     * 根据id 删除房屋信息
     * @param id
     * @return
     */
    Boolean delete(String id);
}