package work.aijiu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Landlords)实体类
 *
 * @author makejava
 * @since 2020-10-17 16:53:36
 */
@Data
public class Landlords implements Serializable {
    private static final long serialVersionUID = 194529329391508766L;
    /**
    * 业主id
    */
    private String landlordsId;
    /**
    * 业主姓名
    */
    private String landlordsName;
    /**
    * 业主性别
    */
    private String landlordsSex;
    /**
    * 业主性别
    */
    private String landlordsAge;
    /**
    * 业主身份证
    */
    private String landlordsCard;
    /**
    * 业主电话
    */
    private String landlordsTel;
    /**
    * 业主微信
    */
    private String landlordsWx;
    /**
    * 业主qq
    */
    private String landlordsQq;
    /**
    * 数据状态
    */
    private String status;
    /**
    * 业主头像
    */
    private String landlordsAvatar;
    /**
    * 备注
    */
    private String remark;

}