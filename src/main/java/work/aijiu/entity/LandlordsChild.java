package work.aijiu.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (LandlordsChild)实体类
 *
 * @author makejava
 * @since 2020-10-18 23:50:16
 */
@Data
public class LandlordsChild implements Serializable {
    private static final long serialVersionUID = -48366326500387742L;
    /**
    * 业主成员id
    */
    private String landlordsChildId;
    /**
    * 业主id
    */
    private String landlordsId;
    /**
    * 业主成员类型（租客，家庭成员，其他）
    */
    private String landlordsChildType;
    /**
    * 姓名
    */
    private String landlordsChildName;
    /**
    * 性别
    */
    private String landlordsChildSex;
    /**
    * 年龄
    */
    private String landlordsChildAge;
    /**
    * 身份证
    */
    private String landlordsChildCard;
    /**
    * 手机号
    */
    private String landlordsChildTel;
    /**
    * 微信
    */
    private String landlordsChildWx;
    /**
    * qq
    */
    private String landlordsChildQq;
    /**
    * 数据状态
    */
    private String status;
    /**
    * 业主头像
    */
    private String landlordsChildAvatar;
    /**
    * 备注
    */
    private String remark;

}