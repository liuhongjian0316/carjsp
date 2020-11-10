package work.aijiu.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (ShopPropertiesShop)实体类
 *
 * @author makejava
 * @since 2020-10-20 10:24:55
 */
@Data
public class ShopPropertiesShop implements Serializable {
    private static final long serialVersionUID = -56036992149875949L;
    /**
    * 商户id
    */
    private String shopId;
    /**
    * 用户名
    */
    private String username;

    /**
     * 商户名称
     */
    private String name;
    /**
    * 密码
    */
    private String pwd;
    /**
    * 手机号
    */
    private String tel;
    /**
    * 真实姓名
    */
    private String realname;
    /**
    * 地址（详细到银座52号 1楼 16号商铺）
    */
    private String address;
    /**
    * 附近地标
    */
    private String landmarks;
    /**
    * 城市编码
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
    * 数据状态
    */
    private String status;
    /**
    * 备注信息
    */
    private String remark;
    /**
     * 角色
     */
    private String roleId;
    /**
     * 组织
     */
    private String organizeId;

}