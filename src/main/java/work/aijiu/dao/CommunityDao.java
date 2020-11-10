package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.Community;

/**
 * (Community)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-12 16:46:57
 */
public interface CommunityDao extends BaseMapper<Community> {

    /**
     * 小区状态0或1的分页（审核 或 未审核）
     * @param page
     * @return
     */
    @Select("select * from community where status = 1 and (state = 0 or state = 1 or state = 2)")
    IPage<Community> queryPage(Page<Community> page);

    /**
     * 小区状态0的分页（未审核）
     * @param page
     * @return
     */
    @Select("select * from community where status =1 and state = 0")
    IPage<Community> queryUnaudited(Page<Community> page);

}