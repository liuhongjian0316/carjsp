package work.aijiu.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Building)实体类
 *
 * @author makejava
 * @since 2020-10-15 19:33:35
 */
public class Building implements Serializable {
    private static final long serialVersionUID = -35363182193452959L;
    /**
    * 楼栋id
    */
    private String buildingId;
    /**
    * 小区id
    */
    private String communityId;
    /**
    * 楼顶号
    */
    private String buildingNum;
    /**
    * 楼栋名称
    */
    private String buildingName;
    /**
    * 建筑面积
    */
    private String buidingArea;
    /**
    * 楼层数
    */
    private String buidingFloorNumber;
    /**
    * 备注信息
    */
    private String remark;
    /**
    * 创建人
    */
    private String buidingCreater;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 数据状态
    */
    private String status;


    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(String buildingNum) {
        this.buildingNum = buildingNum;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuidingArea() {
        return buidingArea;
    }

    public void setBuidingArea(String buidingArea) {
        this.buidingArea = buidingArea;
    }

    public String getBuidingFloorNumber() {
        return buidingFloorNumber;
    }

    public void setBuidingFloorNumber(String buidingFloorNumber) {
        this.buidingFloorNumber = buidingFloorNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBuidingCreater() {
        return buidingCreater;
    }

    public void setBuidingCreater(String buidingCreater) {
        this.buidingCreater = buidingCreater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}