package demo.vo;

import java.util.List;

import demo.model.Item;
import lombok.Data;

/**
 * @author roger
 * @since 2021/2/3 22:32
 */
@Data
public class SearchItemsVO {

    private List<SearchItems> list;

    private int totalPage;

    @Data
    public static class SearchItems {
        private Item item;

        private String nickName;
    }
}
