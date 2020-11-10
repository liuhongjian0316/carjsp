package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.Building;
import work.aijiu.entity.Landlords;

/**
 * (Landlords)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-17 16:53:36
 */
public interface LandlordsDao extends BaseMapper<Landlords> {

    /**
     * 业主分页查询
     * @param page
     * @return
     */
    @Select("select * from landlords where status =1")
    IPage<Landlords> queryPage(Page<Landlords> page);
}