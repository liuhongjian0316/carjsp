package work.aijiu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (ShopPropertiesRepairWorker)实体类
 *
 * @author makejava
 * @since 2020-10-21 15:25:02
 */
@Data
public class ShopPropertiesRepairWorker implements Serializable {
    private static final long serialVersionUID = 177532873668563287L;
    /**
    * 维修员id
    */
    private String repairWorkerId;
    /**
    * 维修员姓名
    */
    private String userName;
    /**
    * 维修员密码
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
    * 手机号
    */
    private String tel;
    /**
    * 身份证
    */
    private String card;
    /**
    * 详细住址
    */
    private String address;
    /**
    * 维修员类别
    */
    private String repairWorkerTypeId;
    /**
    * 组织id
    */
    private String organizeId;
    /**
    * 角色id
    */
    private String roleId;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 数据状态
    */
    private String status;
    /**
    * 状态（维修中等）
    */
    private String state;
    /**
    * 备注信息
    */
    private String remark;
    /**
    * 最后登录时间
    */
    private Date lastLoginTime;


}