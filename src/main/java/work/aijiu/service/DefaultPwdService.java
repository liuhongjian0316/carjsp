package work.aijiu.service;

import work.aijiu.entity.DefaultPwd;
import java.util.List;

/**
 * (DefaultPwd)表服务接口
 *
 * @author makejava
 * @since 2020-10-23 09:38:07
 */
public interface DefaultPwdService {


    /**
     * 根据标志查询 默认密码
     * @param parm
     * @return
     */
    public DefaultPwd queryByParm(String parm);

}