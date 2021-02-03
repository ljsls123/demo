package demo.vo;

import java.util.List;

import demo.model.Item;
import lombok.Data;

@Data
public class GetItemVO {
    int totalPage;

    List<Item> list;
}
