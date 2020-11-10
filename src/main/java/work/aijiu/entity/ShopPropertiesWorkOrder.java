package work.aijiu.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (ShopPropertiesWorkOrder)实体类
 *
 * @author makejava
 * @since 2020-10-25 23:55:05
 */
@Data
public class ShopPropertiesWorkOrder implements Serializable {
    private static final long serialVersionUID = -89183965833241704L;
    /**
    * 工单id
    */
    private String workOrderId;
    /**
    * 工单类型
    */
    private String workTypeId;
    /**
    * 工单编号
    */
    private String workNumber;
    /**
    * 商铺id
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
    * 预约时间
    */
    private Date reserveTime;
    /**
    * 详细位置
    */
    private String address;
    /**
    * 申请状态（未受理，已受理）
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
    * 总价金额
    */
    private String totalMoney;
    /**
    * 故障描述（含图片）
    */
    private Object faultDescription;
    /**
     * 故障描述（含图片）content
     */
    private Object faultDescriptionContent;
    /**
    * 维修工人
    */
    private String worker;
    /**
    * 原因
    */
    private Object cause;
    /**
     * 原因
     */
    private Object causeContent;
    /**
    * 负责人
    */
    private String chargePerson;
    /**
    * 拒单说明
    */
    private String faultCause;
    /**
    * 反应速度
    */
    private String reactSpeed;
    /**
    * 专业水平
    */
    private String professionLevel;
    /**
    * 服务态度html
    */
    private String serviceAttitude;
    /**
    * 评价内容
    */
    private Object contentEvaluation;
    /**
     * 评价内容content
     */
    private Object contentEvaluationContent;
    /**
    * 创建时间
    */
    private Date createTime;


}