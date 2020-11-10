package work.aijiu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (DefaultPwd)实体类
 *
 * @author makejava
 * @since 2020-10-23 09:38:07
 */
@Data
public class DefaultPwd implements Serializable {
    private static final long serialVersionUID = -93014755341906474L;
    /**
    * 主键
    */
    private Integer id;
    /**
    * 默认密码
    */
    private String defaultPwd;
    /**
    * 数据状态
    */
    private String status;
    /**
    * 备注
    */
    private String remark;

}