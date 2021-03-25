package demo.model;

import lombok.Data;

@Data
public class UserComment {
    private int id;

    private int userId;

    private int itemId;

    private String nickName;

    private String detail;
}
