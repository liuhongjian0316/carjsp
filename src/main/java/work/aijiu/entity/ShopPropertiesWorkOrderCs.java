package work.aijiu.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (ShopPropertiesWorkOrderCs)实体类
 *
 * @author makejava
 * @since 2020-10-30 10:04:07
 */
@Data
public class ShopPropertiesWorkOrderCs implements Serializable {
    private static final long serialVersionUID = -31362223738420587L;
    /**
    * 客服受理填写的维修单id
    */
    private String workOrderCsId;
    /**
    * 受理客服id
    */
    private String customerServiceId;
    /**
    * 受理客服名称
    */
    private String customerServiceName;
    /**
    * 工单类型
    */
    private String workType;
    /**
    * 商户id
    */
    private String shopId;
    /**
    * 商户名称
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
    * 填单时间
    */
    private Date fillingTime;
    /**
    * 详细地址
    */
    private String address;
    /**
    * 状态
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
    * 故障描述(html)
    */
    private String faultDescription;
    /**
    * 故障照片（content）
    */
    private String faultDescriptionContent;
    /**
    * 维修工人
    */
    private String worker;
    /**
    * 原因html
    */
    private Object cause;
    /**
    * 原因content
    */
    private Object causeContent;
    /**
    * 负责人
    */
    private String chargePerson;
    /**
    * 拒单原因
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

}