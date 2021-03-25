package demo.model;

import lombok.Data;

@Data
public class Ordered {
    private int id;

    private int userId;

    private int itemId;

    private String orderStatus;
}
