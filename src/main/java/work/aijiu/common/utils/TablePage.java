package work.aijiu.common.utils;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/**
 * bootstrap table 分页类型
 */
@Data
public class TablePage<T> {
    private long total;
    private long offset;
    private long limit;
    private List<?> rows;

    public  TablePage(long total, long offset, long limit, List<?> rows) {
        this.total = total;
        this.offset = offset;
        this.limit = limit;
        this.rows = rows;
    }
}
