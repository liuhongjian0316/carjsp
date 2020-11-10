package work.aijiu.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (ShopPropertiesCustomerService)实体类
 *
 * @author makejava
 * @since 2020-10-22 13:31:34
 */
@Data
public class ShopPropertiesCustomerService implements Serializable {
    private static final long serialVersionUID = 358220703359044087L;
    /**
    * 客服名称
    */
    private String customerServiceId;
    /**
    * 客服名称
    */
    private String customerServiceName;
    /**
    * 账号
    */
    private String username;
    /**
    * 密码
    */
    private String pwd;
    /**
    * 组织id
    */
    private String organizeId;
    /**
    * 真实姓名
    */
    private String realName;
    /**
    * 性别
    */
    private String sex;
    /**
    * 电话
    */
    private String tel;
    /**
    * 地址
    */
    private String address;
    /**
    * 角色id
    */
    private String roleId;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 客服状态（忙碌，在线，离线）
    */
    private String state;
    /**
    * 数据状态
    */
    private String status;
    /**
    * 备注信息
    */
    private String remark;
    /**
    * 最后一次登录时间
    */
    private Date lastLoginTime;



}