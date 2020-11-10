package work.aijiu.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (ShopPropertiesNotice)实体类
 *
 * @author makejava
 * @since 2020-11-04 22:30:09
 */
@Data
public class ShopPropertiesNotice implements Serializable {
    private static final long serialVersionUID = -18719904183123998L;
    /**
    * 公告id
    */
    private String noticeId;
    /**
    * 员工id
    */
    private String customerServiceId;
    /**
    * 标题
    */
    private String title;
    /**
    * 公告类型（1商户2维修员）
    */
    private String type;
    /**
    * 头部图片
    */
    private String img;
    /**
    * 发布内容
    */
    private Object contentHtml;
    /**
    * 发布内容
    */
    private Object content;
    /**
    * 开始时间
    */
    private Date beginTime;
    /**
    * 结束时间
    */
    private Date endTime;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 数据状态
    */
    private String status;
    /**
    * 通知状态
    */
    private String state;
    /**
    * 备注信息
    */
    private String remark;


}