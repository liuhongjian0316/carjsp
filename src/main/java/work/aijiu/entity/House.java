package work.aijiu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (House)实体类
 *
 * @author makejava
 * @since 2020-10-16 19:47:10
 */
@Data
public class House implements Serializable {
    private static final long serialVersionUID = 297373084693620446L;
    /**
    * 房屋id
    */
    private String houseId;
    /**
    * 小区id
    */
    private String communityId;
    /**
    * 楼栋id
    */
    private String buildingId;
    /**
    * 单元id
    */
    private String unitId;
    /**
    * 房屋楼层
    */
    private String houseFloor;
    /**
    * 房屋编号
    */
    private String houseNum;
    /**
     * 房屋名称
     */
    private String houseName;
    /**
    * 房屋类型 一室一厅
    */
    private String houseType;
    /**
    * 使用面积
    */
    private String houseArea;
    /**
    * 算费系数
    */
    private String houseCostFactor;
    /**
    * 是否是商铺
    */
    private String isShop;
    /**
    * 是否是商品房
    */
    private String isCommercialHouse;
    /**
     * 房屋状态
     */
    private String state;
    /**
    * 数据状态
    */
    private String status;
    /**
    * 备注
    */
    private String remark;



}