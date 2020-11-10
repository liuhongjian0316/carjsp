package work.aijiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import work.aijiu.entity.Unit;

import java.util.List;

/**
 * (Unit)表服务接口
 *
 * @author makejava
 * @since 2020-10-16 09:12:14
 */
public interface UnitService {

    /**
     * 单元分页
     * @param pageNum
     * @param pageSize
     * @param cid
     * @param bid
     * @return
     */
    IPage<Unit> queryPage(Integer pageNum, Integer pageSize,String cid,String bid);

    /**
     * 增加单元
     * @param unit
     * @return
     */
    Boolean add(Unit unit);

    /**
     * 根据id 获取单元信息
     * @param id
     * @return
     */
    Unit getById(String id);

    /**
     * 修改单元信息
     * @param unit
     * @return
     */
    Boolean save(Unit unit);

    /**
     * 根据id删除单元
     * @param id
     * @return
     */
    Boolean delete(String id);

    /**
     * 添加房屋时选择单元
     * @param cid
     * @param bid
     * @return
     */
    List<Unit> queryByCidBid(String cid, String bid);

}