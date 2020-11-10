package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.Company;

/**
 * (Company)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-14 18:55:15
 */
public interface CompanyDao extends BaseMapper<Company> {

    /**
     * 未审核的公司分页
     * @param page
     * @return
     */
    @Select("select * from company where status =1 and state = 0")
    IPage<Company> queryUnaudited(Page<Company> page);






}