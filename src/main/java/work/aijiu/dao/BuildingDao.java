package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.Building;
import org.apache.ibatis.annotations.Param;
import work.aijiu.entity.Company;

import java.util.List;

/**
 * (Building)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-15 19:33:35
 */
public interface BuildingDao extends BaseMapper<Building> {

    /**
     * 查询小区分页
     * @param page
     * @return
     */
    @Select("select * from building where status =1")
    IPage<Building> queryPage(Page<Building> page);

}