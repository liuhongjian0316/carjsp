package work.aijiu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * (Permission)实体类
 *
 * @author makejava
 * @since 2020-09-18 11:28:51
 */
@Data
public class Permission implements Serializable {
    private static final long serialVersionUID = 562434461464741568L;
    
    public Integer id;
    /**
    * 权限id
    */
    public String permissionId;
    /**
    * 权限名称
    */
    public String name;
    /**
    * 权限描述
    */
    public String description;
    /**
    * 权限访问路径
    */
    public String url;
    /**
    * 权限标识
    */
    public String perms;
    /**
    * 父级权限id
    */
    public Integer parentId;
    /**
    * 类型   0：目录   1：菜单   2：按钮
    */
    public Integer type;
    /**
    * 排序
    */
    public Integer orderNum;
    /**
    * 图标
    */
    public String icon;
    /**
    * 状态：1有效；2删除
    */
    public Integer status;

    public Date createTime;

    public Date updateTime;

    @TableField(exist = false)
    public List<Permission> subs;

    @TableField(exist = false)
    public List<Permission> children;

}