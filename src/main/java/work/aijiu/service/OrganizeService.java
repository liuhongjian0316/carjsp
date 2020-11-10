package work.aijiu.service;

import work.aijiu.entity.Organize;
import java.util.List;

/**
 * (Organize)表服务接口
 *
 * @author makejava
 * @since 2020-10-20 22:02:58
 */
public interface OrganizeService {

    /**
     * 根据 id查询该组织下的所有部门
     * @param id
     * @return
     */
    List<Organize> queryCurrentOrganizeById(String id);

    /**
     * 商户物业添加组织时名称查重
     * @param name
     * @param id
     * @return
     */
    Boolean checkAddName(String name,String id);

    /**
     * 商户物业添加组织信息
     * @param organize
     * @return
     */
    Boolean addOrganize(Organize organize);

    /**
     * 商户物业根据id查询组织信息
     * @param id
     * @return
     */
    Organize getById(String id);

    /**
     * 商户物业修改组织信息
     * @param organize
     * @return
     */
    Boolean saveOrganize(Organize organize);

    /**
     * 商户物业修改组织名称查重
     * @param name
     * @param id
     * @param pid
     * @return
     */
    Boolean checkEditName(String name,String id,String pid);

    /**
     * 商户物业根据id删除组织信息
     * @param id
     * @return
     */
    Boolean deleteById(String id);
}