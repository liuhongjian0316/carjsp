package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.Unit;

/**
 * (Unit)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-16 09:12:14
 */
public interface UnitDao extends BaseMapper<Unit> {

    @Select("select * from unit where status =1 and community_id = #{cid} and building_id = #{bid}")
    IPage<Unit> queryPage(Page<Unit> page, @Param("cid") String cid, @Param("bid") String bid);

}