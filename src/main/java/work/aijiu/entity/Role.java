package work.aijiu.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2020-09-18 09:26:58
 */
@Data
public class Role implements Serializable {
    private static final long serialVersionUID = 182926100091034662L;
    
    private Integer id;
    /**
    * 角色id
    */
    private String roleId;
    /**
    * 角色名称
    */
    private String name;
    /**
     * 权限字段
     */
    private String perm;
    /**
    * 角色描述
    */
    private String description;
    /**
    * 状态：1有效；2删除
    */
    private Integer status;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;


}