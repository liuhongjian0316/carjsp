package work.aijiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import work.aijiu.entity.Building;
import work.aijiu.entity.Landlords;
import java.util.List;

/**
 * (Landlords)表服务接口
 *
 * @author makejava
 * @since 2020-10-17 16:53:36
 */
public interface LandlordsService {

    /**
     * 业主分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<Landlords> queryPage(Integer pageNum, Integer pageSize);

    /**
     * 添加业主
     * @param landlords
     * @return
     */
    Boolean addLandlords(Landlords landlords);

    /**
     * 根据id 获取业主信息
     * @param id
     * @return
     */
    Landlords getById(String id);


    /**
     * 保存业主信息
     * @param landlords
     * @return
     */
    Boolean save(Landlords landlords);

    /**
     * 根据id 删除物业信息
     * @param id
     * @return
     */
    Boolean delete(String id);

    /**
     * 查询业主信息
     * @return
     */
    List<Landlords> queryList();

}