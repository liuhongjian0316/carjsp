package work.aijiu.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import work.aijiu.entity.LandlordsChild;

/**
 * (LandlordsChild)表服务接口
 *
 * @author makejava
 * @since 2020-10-18 23:50:16
 */
public interface LandlordsChildService {

    /**
     * 根据物业id 查询物业成员id分页
     * @param pageNum
     * @param pageSize
     * @param pid
     * @return
     */
    IPage<LandlordsChild> queryPageByPid(Integer pageNum, Integer pageSize, String pid);

    /**
     * 添加业主成员
     * @param landlordsChild
     * @return
     */
    Boolean insertLandordsChild(LandlordsChild landlordsChild);

    /**
     * 根据id 查询业主成员信息
     * @param id
     * @return
     */
    LandlordsChild getChildById(String id);

    /**
     * 修改业主成员信息
     * @param landlordsChild
     * @return
     */
    Boolean save(LandlordsChild landlordsChild);

    /**
     * 根据id 删除业主成员
     * @param id
     * @return
     */
    Boolean delete(String id);
}