package demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 5985899178281518140L;
    private Integer id;

    private String email;

    private String password;

    private String nickname;

    private String address;

    private String salt;

    private String userType;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
