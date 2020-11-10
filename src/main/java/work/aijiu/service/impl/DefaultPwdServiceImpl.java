package work.aijiu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.DefaultPwd;
import work.aijiu.dao.DefaultPwdDao;
import work.aijiu.service.DefaultPwdService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DefaultPwd)表服务实现类
 *
 * @author makejava
 * @since 2020-10-23 09:38:07
 */
@Service("defaultPwdService")
public class DefaultPwdServiceImpl implements DefaultPwdService {
    @Autowired
    private DefaultPwdDao defaultPwdDao;

    @Override
    public DefaultPwd queryByParm(String parm) {
        QueryWrapper<DefaultPwd> wrapper = new QueryWrapper<>();
        wrapper.eq("parm",parm);
        return defaultPwdDao.selectOne(wrapper);
    }
}