package demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private Integer id;

    private Integer userId;

    private String title;

    private String type;

    private String description;

    private String price;

    private String img;

    private String online;

}
