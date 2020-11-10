package work.aijiu.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (ShopPropertiesDistributeOrder)实体类
 *
 * @author makejava
 * @since 2020-10-26 18:30:25
 */
@Data
public class ShopPropertiesDistributeOrder implements Serializable {
    private static final long serialVersionUID = 179606634697586212L;
    /**
    * 派单id
    */
    private String distributeOrderId;
    /**
    * 工单id
    */
    private String workOrderId;
    /**
    * 工单类型
    */
    private String workType;
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
    * 状态（0,）
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
    * 故障描述（含图片）html
    */
    private Object faultDescription;
    /**
    * 故障报修（content）
    */
    private Object faultDescriptionContent;
    /**
    * 维修员（逗号分格）
    */
    private String worker;
    /**
    * 维修说明
    */
    private String repairDescription;
    /**
    * 默认（0，1增援 ）
    */
    private String isReinforce;
    /**
     * 审批人
     */
    private String employeeId;
    /**
     * 电话订单标志
     */
    private  String telmark;
    /**
     * 悬赏订单标志
     */
    private  String rewardmark;

}