package work.aijiu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (ShopPropertiesWorkType)实体类
 *
 * @author makejava
 * @since 2020-10-25 22:16:50
 */
@Data
public class ShopPropertiesWorkType implements Serializable {
    private static final long serialVersionUID = -42865218087115773L;
    /**
    * 工单类型id
    */
    private String workTypeId;
    /**
    * 类型名称
    */
    private String workTypeName;
    /**
    * 数据状态
    */
    private String status;
    /**
    * 备注信息(比如电路系统包括)
    */
    private String remark;


}