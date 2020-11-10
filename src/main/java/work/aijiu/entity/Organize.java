package work.aijiu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * (Organize)实体类
 *
 * @author makejava
 * @since 2020-10-20 22:02:58
 */
@Data
public class Organize implements Serializable {
    private static final long serialVersionUID = -71919936858306173L;
    /**
    * 组织id
    */
    public String organizeId;
    /**
    * 组织名称
    */
    public String organizeName;
    /**
    * 组织级别（总公司，分公司，部门等）（0，1，2）
    */
    public String organizeLevel;
    /**
    * 上级组织
    */
    public String organizeParent;
    /**
    * 数据状态
    */
    public String status;
    /**
    * 备注信息
    */
    public String remark;

    @TableField(exist = false)
    public List<Organize> subs;

    @TableField(exist = false)
    public List<Organize> children;

}