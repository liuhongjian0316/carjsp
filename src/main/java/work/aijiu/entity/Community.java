package work.aijiu.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Community)实体类
 *
 * @author liuhongjian
 * @since 2020-10-12 16:40:08
 */
@Data
public class Community implements Serializable {
    private static final long serialVersionUID = -12822677776039949L;
    /**
    * 小区id
    */
    private String communityId;
    /**
    * 小区名称
    */
    private String name;
    /**
    * 小区地址
    */
    private String address;
    /**
    * 地标
    */
    private String nearbyLandmarks;
    /**
    * 城市代码根据定位获取城市编码
    */
    private String cityCode;
    /**
    * 经度
    */
    private String mapX;
    /**
    * 维度
    */
    private String mapY;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 0保存，1在用，2失效
    */
    private String status;
    /**
    * 审核状态
    */
    private String state;
    /**
    * 备注信息
    */
    private String remark;

}