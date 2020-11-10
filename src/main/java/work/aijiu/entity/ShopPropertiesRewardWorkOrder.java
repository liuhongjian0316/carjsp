package work.aijiu.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (ShopPropertiesRewardWorkOrder)实体类
 *
 * @author makejava
 * @since 2020-11-01 10:39:58
 */
@Data
public class ShopPropertiesRewardWorkOrder implements Serializable {
    private static final long serialVersionUID = 354224509561700926L;
    /**
    * 悬赏工单id
    */
    private String rewardWorkOderId;
    /**
    * 工单类型
    */
    private String workTypeId;
    /**
    * 工单编号
    */
    private String workNumber;
    /**
    * 商铺Id
    */
    private String shopId;
    /**
     * 商铺名称
     */
    private String shopName;
    /**
    * 申请人
    */
    private String applyName;
    /**
    * 联系电话
    */
    private String tel;
    /**
    * 详细地址
    */
    private String address;
    /**
    * 受理状态（已接单)
    */
    private String state;
    /**
    * 数据状态
    */
    private String status;
    /**
    * 备注信息
    */
    private String remark;
    /**
    * 支付状态
    */
    private String payState;
    /**
    * 支付金额
    */
    private String totalMoney;
    /**
    * 故障描述html
    */
    private Object faultDescription;
    /**
    * 故障描述content
    */
    private Object faultDescriptionContent;
    /**
    * 悬赏金额
    */
    private String rewardMoney;
    /**
    * 反应速度
    */
    private String reactSpeed;
    /**
    * 专业等级
    */
    private String professionLevel;
    /**
    * 服务态度
    */
    private String serviceAttitude;
    /**
    * 评价内容html
    */
    private Object contentEvaluation;
    /**
    * 评价内容content
    */
    private Object contentEvaluationContent;
    /**
    * 维修员
    */
    private String worker;
    /**
    * 创建时间
    */
    private Date createTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRewardWorkOderId() {
        return rewardWorkOderId;
    }

    public void setRewardWorkOderId(String rewardWorkOderId) {
        this.rewardWorkOderId = rewardWorkOderId;
    }

    public String getWorkTypeId() {
        return workTypeId;
    }

    public void setWorkTypeId(String workTypeId) {
        this.workTypeId = workTypeId;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Object getFaultDescription() {
        return faultDescription;
    }

    public void setFaultDescription(Object faultDescription) {
        this.faultDescription = faultDescription;
    }

    public Object getFaultDescriptionContent() {
        return faultDescriptionContent;
    }

    public void setFaultDescriptionContent(Object faultDescriptionContent) {
        this.faultDescriptionContent = faultDescriptionContent;
    }

    public String getRewardMoney() {
        return rewardMoney;
    }

    public void setRewardMoney(String rewardMoney) {
        this.rewardMoney = rewardMoney;
    }

    public String getReactSpeed() {
        return reactSpeed;
    }

    public void setReactSpeed(String reactSpeed) {
        this.reactSpeed = reactSpeed;
    }

    public String getProfessionLevel() {
        return professionLevel;
    }

    public void setProfessionLevel(String professionLevel) {
        this.professionLevel = professionLevel;
    }

    public String getServiceAttitude() {
        return serviceAttitude;
    }

    public void setServiceAttitude(String serviceAttitude) {
        this.serviceAttitude = serviceAttitude;
    }

    public Object getContentEvaluation() {
        return contentEvaluation;
    }

    public void setContentEvaluation(Object contentEvaluation) {
        this.contentEvaluation = contentEvaluation;
    }

    public Object getContentEvaluationContent() {
        return contentEvaluationContent;
    }

    public void setContentEvaluationContent(Object contentEvaluationContent) {
        this.contentEvaluationContent = contentEvaluationContent;
    }

    public String getWorker() {
        return worker;
    }

    public void setWorker(String worker) {
        this.worker = worker;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}