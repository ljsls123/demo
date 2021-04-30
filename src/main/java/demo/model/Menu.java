package demo.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Menu {
    private Integer id;

    private Integer pid;

    private String name;

    private String url;

    private List<Menu> child = new ArrayList<>();
}
