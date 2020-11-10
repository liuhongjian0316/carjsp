package work.aijiu.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import work.aijiu.entity.Organize;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Organize)表数据库访问层
 *
 * @author makejava
 * @since 2020-10-20 22:02:58
 */
public interface OrganizeDao extends BaseMapper<Organize> {

    /**
     * 根据id查询该id下的所有部门
     * @param id
     * @return
     */
    @Select("SELECT organize_id, organize_name,organize_level,organize_parent ,status FROM\n" +
            "(\n" +
            "SELECT t1.organize_id, t1.organize_name,organize_level, status,organize_parent, IF(FIND_IN_SET(organize_parent, @organize_parent) > 0, @organize_parent := CONCAT(@organize_parent, ',',organize_id), 0) AS ischild\n" +
            "FROM\n" +
            "(SELECT organize_id, organize_parent, organize_name , organize_level ,status FROM organize t ORDER BY organize_parent, organize_id) t1,\n" +
            "(SELECT @organize_parent := #{id}) t2\n" +
            ")\n" +
            "t3\n" +
            "WHERE organize_level != 0 and organize_level != 1 and status = '1'")
    List<Organize> selectCurrentOrganizeById(@Param("id")String id);


    /**
     * 商户物业增加组织时名称查重
     * @param name
     * @param id
     * @return
     */
    @Select("SELECT organize_id, organize_name,organize_level,organize_parent ,status FROM\n" +
            "(\n" +
            "SELECT t1.organize_id, t1.organize_name,organize_level, status,organize_parent, IF(FIND_IN_SET(organize_parent, @organize_parent) > 0, @organize_parent := CONCAT(@organize_parent, ',',organize_id), 0) AS ischild\n" +
            "FROM\n" +
            "(SELECT organize_id, organize_parent, organize_name , organize_level ,status FROM organize t ORDER BY organize_parent, organize_id) t1,\n" +
            "(SELECT @organize_parent := #{id}) t2\n" +
            ")\n" +
            "t3\n" +
            "WHERE organize_level != 0 and organize_level != 1 and status = '1' and organize_name = #{name}")
    List<Organize> checkAddName(@Param("name") String name,@Param("id") String id);

    /**
     * 商户物业修改组织名称时查重
     * @param name
     * @param id
     * @param pid
     * @return
     */
    @Select("SELECT organize_id, organize_name,organize_level,organize_parent ,status FROM\n" +
            "(\n" +
            "SELECT t1.organize_id, t1.organize_name,organize_level, status,organize_parent, IF(FIND_IN_SET(organize_parent, @organize_parent) > 0, @organize_parent := CONCAT(@organize_parent, ',',organize_id), 0) AS ischild\n" +
            "FROM\n" +
            "(SELECT organize_id, organize_parent, organize_name , organize_level ,status FROM organize t ORDER BY organize_parent, organize_id) t1,\n" +
            "(SELECT @organize_parent := #{pid}) t2\n" +
            ")\n" +
            "t3\n" +
            "WHERE organize_level != 0 and organize_level != 1 and status = '1' and organize_name = #{name} and organize_id !=#{id}")
    List<Organize> checkEditName(@Param("name") String name,@Param("id") String id,@Param("pid") String pid);

}