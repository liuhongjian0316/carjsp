package work.aijiu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (ShopPropertiesInnerEmployees)实体类
 *
 * @author makejava
 * @since 2020-10-24 12:42:25
 */
@Data
public class ShopPropertiesInnerEmployees implements Serializable {
    private static final long serialVersionUID = 395214158918068113L;
    /**
    * 内部员工id
    */
    private String employeeId;
    /**
    * 账号
    */
    private String userName;
    /**
    * 密码
    */
    private String pwd;
    /**
    * 真实姓名
    */
    private String realName;
    /**
    * 性别
    */
    private String sex;
    /**
    * 年龄
    */
    private String age;
    /**
    * 联系电话
    */
    private String tel;
    /**
    * 住址
    */
    private String address;
    /**
    * 组织
    */
    private String organizeId;
    /**
    * 角色
    */
    private String roleId;
    /**
    * 数据状态
    */
    private String status;
    /**
    * 备注信息
    */
    private String remark;

}