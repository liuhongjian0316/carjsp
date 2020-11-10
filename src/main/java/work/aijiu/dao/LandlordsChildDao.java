package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.Landlords;
import work.aijiu.entity.LandlordsChild;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (LandlordsChild)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-18 23:50:16
 */
public interface LandlordsChildDao extends BaseMapper<LandlordsChild> {

    /**
     * 根据id 查询业主成员id
     * @param page
     * @param id
     * @return
     */
    @Select("select * from landlords_child where status =1 and landlords_id = #{id}")
    IPage<LandlordsChild> queryPage(Page<LandlordsChild> page,@Param("id")String id);
}