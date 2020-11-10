package work.aijiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import work.aijiu.entity.Community;
import work.aijiu.entity.Company;
import java.util.List;

/**
 * (Company)表服务接口
 *
 * @author makejava
 * @since 2020-10-14 18:55:15
 */
public interface CompanyService {

    /**
     * 申请公司
     * @param company
     * @return
     */
    public Boolean apply(Company company);

    /**
     * 查询未审核的公司名称
     * @param pageNum
     * @param pageSize
     * @return
     */
    public IPage<Company> queryUnauditedPage(Integer pageNum, Integer pageSize);

    /**
     * 根据id 查询未审核的公司的详情
     * @param id
     * @return
     */
    public Company queryById(String id);

    /**
     * 审核公司
     * @param company
     * @return
     */
    public boolean audit(Company company);

    /**
     * 删除公司
     * @param id
     * @return
     */
    public boolean delete(String id);

    /**
     * 商户物业 商户登录
     * @param tel
     * @param pwd
     * @return
     */
    List<Company> login(String tel,String pwd);
}