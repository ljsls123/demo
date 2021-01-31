package demo.model;

import demo.dto.CreateItemDTO;
import lombok.Data;

@Data
public class Item {
    private Integer id;

    private Integer userId;

    private String title;

    private String type;

    private String description;

    private String price;

    private String img;

    private String online;

    public Item(CreateItemDTO createItemDTO, Integer userId, String img) {
        this.userId = userId;
        this.title = createItemDTO.getTitle();
        this.type = createItemDTO.getType();
        this.description = createItemDTO.getDescription();
        this.price = createItemDTO.getPrice();
        this.img = img;
        this.online = "1";
    }
}
