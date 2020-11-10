package work.aijiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import work.aijiu.entity.ShopPropertiesNotice;
import java.util.List;
import java.util.Map;

/**
 * (ShopPropertiesNotice)表服务接口
 *
 * @author makejava
 * @since 2020-11-04 22:30:09
 */
public interface ShopPropertiesNoticeService {

    /**
     * 商户物业 通知分页
     * @param pageNum
     * @param pageSize
     * @param notice
     * @return
     */
    IPage<ShopPropertiesNotice> queryPage(Integer pageNum, Integer pageSize,ShopPropertiesNotice notice);

    /**
     * 发布通知
     * @param notice
     * @return
     */
    Boolean addNotice(ShopPropertiesNotice notice);

    /**
     * 根据id 删除通知
     * @param id
     * @return
     */
    Boolean deleteById(String id);

    /**
     * 根据id 获取通知信息
     * @param id
     * @return
     */
    ShopPropertiesNotice getById(String id);

    /**
     * 保存公告信息
     * @param notice
     * @return
     */
    Boolean saveNotice(ShopPropertiesNotice notice);

    /**
     * 商户管理员统计 按类型统计通知
     * @return
     */
    List<Map<String,Object>> countByType();

    /**
     * 总计通知数
     * @return
     */
    int numOfNotice();

    /**
     * 通知类型
     * @param type
     * @return
     */
    int numOfNoticeByType(String type);

}