package work.aijiu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import work.aijiu.entity.DefaultPwd;
import work.aijiu.service.DefaultPwdService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (DefaultPwd)表控制层
 *
 * @author makejava
 * @since 2020-10-23 09:38:08
 */
@RestController
public class DefaultPwdController {
    /**
     * 服务对象
     */
    @Autowired
    private DefaultPwdService defaultPwdService;
    

}