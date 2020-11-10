package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.Company;
import work.aijiu.dao.CompanyDao;
import work.aijiu.service.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Company)表服务实现类
 *
 * @author makejava
 * @since 2020-10-14 18:55:15
 */
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;

    @Override
    public Boolean apply(Company company) {
        return companyDao.insert(company)>0;
    }

    @Override
    public IPage<Company> queryUnauditedPage(Integer pageNum, Integer pageSize) {
        return companyDao.queryUnaudited(new Page<>(pageNum, pageSize));
    }

    @Override
    public Company queryById(String id) {
        QueryWrapper<Company> wrapper = new QueryWrapper<>();
        wrapper.eq("company_id",id);
        return companyDao.selectOne(wrapper);
    }

    @Override
    public boolean audit(Company company) {
        UpdateWrapper<Company> wrapper = new UpdateWrapper<>();
        wrapper.eq("company_id",company.getCompanyId());
        wrapper.set("state",company.getState());
        wrapper.set("remark",company.getRemark());
        return companyDao.update(null,wrapper)>0;
    }

    @Override
    public boolean delete(String id) {
        UpdateWrapper<Company> wrapper = new UpdateWrapper<>();
        wrapper.eq("company_id",id);
        wrapper.set("status","0");
        return companyDao.update(null,wrapper)>0;
    }

    @Override
    public List<Company> login(String tel, String pwd) {
        QueryWrapper<Company> wrapper = new QueryWrapper<>();
        wrapper.eq("tel",tel).eq("pwd",pwd)
                .eq("status","1").eq("state","1");
        return companyDao.selectList(wrapper);
    }
}