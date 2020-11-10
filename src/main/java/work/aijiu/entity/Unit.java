package work.aijiu.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Unit)实体类
 *
 * @author makejava
 * @since 2020-10-16 09:12:14
 */
@Data
public class Unit implements Serializable {
    private static final long serialVersionUID = -91749628296941829L;
    /**
    * 单元id
    */
    private String unitId;
    /**
    * 楼栋id
    */
    private String buildingId;
    /**
    * 小区id
    */
    private String communityId;
    /**
    * 单元名称
    */
    private String unitName;
    /**
    * 单元编号
    */
    private String unitNumber;
    /**
    * 是否有电梯
    */
    private String haslift;
    /**
    * 单元楼层
    */
    private String unitFloorNumber;
    /**
    * 备注信息
    */
    private String remark;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 数据状态
    */
    private String status;

}