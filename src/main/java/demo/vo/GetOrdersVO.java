package demo.vo;

import java.util.ArrayList;
import java.util.List;

import demo.model.Item;
import demo.model.Ordered;
import demo.model.User;
import lombok.Data;

@Data
public class GetOrdersVO {

    List<OrderVO> list = new ArrayList<>();

    @Data
    public static class OrderVO {
        private Ordered ordered;

        private User user;

        private Item item;
    }
}
