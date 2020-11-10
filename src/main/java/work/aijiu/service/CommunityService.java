package work.aijiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import work.aijiu.entity.Community;


/**
 * (Community)表服务接口
 *
 * @author makejava
 * @since 2020-10-12 16:46:57
 */
public interface CommunityService {

    /**
     * 审核或未审核的小区分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<Community> queryPage(Integer pageNum, Integer pageSize);

    /**
     * 审核或未审核的小区分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<Community> queryUnauditedPage(Integer pageNum, Integer pageSize);

    /**
     * 添加小区
     * @param community
     * @return
     */
    Boolean insert(Community community);

    /**
     * 根据id获取小区数据
     * @param id
     * @return
     */
    Community getById(String id);

    /**
     * 保存小区信息
     * @param community
     * @return
     */
    Boolean edit(Community community);

    /**
     * 撤回审核
     * @param id
     * @return
     */
    Boolean auditreturn(String id);

    /**
     * 审核小区
     * @param community
     * @return
     */
    Boolean audit(Community community);

    /**
     * 删除小区
     * @param id
     * @return
     */
    Boolean delete(String id);
}