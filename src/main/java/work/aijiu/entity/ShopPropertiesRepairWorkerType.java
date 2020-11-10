package work.aijiu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (ShopPropertiesRepairWorkerType)实体类
 *
 * @author makejava
 * @since 2020-10-21 15:26:25
 */
@Data
public class ShopPropertiesRepairWorkerType implements Serializable {
    private static final long serialVersionUID = 364532558490925042L;
    /**
    * 维修工类型id
    */
    private String repairWorkerTypeId;
    /**
    * 维修工类型名称
    */
    private String repairWorkerTypeName;
    /**
    * 数据状态
    */
    private String status;
    /**
    * 备注信息
    */
    private String remark;

}