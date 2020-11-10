package work.aijiu.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Company)实体类
 *
 * @author makejava
 * @since 2020-10-14 18:55:15
 */
@Data
public class Company implements Serializable {
    private static final long serialVersionUID = 328189559884575320L;
    /**
    * 公司id
    */
    private String companyId;
    /**
    * 公司名称
    */
    private String name;
    /**
    * 公司地址
    */
    private String address;
    /**
    * 联系电话
    */
    private String tel;
    /**
    * 公司业务(物业，代理商，商铺)
    */
    private String service;
    /**
    * 地标
    */
    private String landmarks;
    /**
    * 城市代码
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
    * 申请时间
    */
    private Date createTime;
    /**
    * 法人
    */
    private String  legalPerson;
    /**
    * 注册资本
    */
    private String capital;
    /**
    * 成立时间
    */
    private Date setUpTime;
    /**
    * 登记机关
    */
    private String registrationOffice;
    /**
    * 经营范围
    */
    private String scopeBusiness;
    /**
    * 营业证件号码
    */
    private String idNumber;
    /**
    * 有效期
    */
    private Date validityTime;
    /**
    * 营业执照照片
    */
    private String businessLicensePhoto;
    /**
    * 数据状态
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
    /**
     * 密码
     */
    private String pwd;
    /**
     * 角色
     */
    private String roleId;
    /**
     * 组织
     */
    private String organizeId;


}