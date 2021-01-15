package vo;


import java.io.Serializable;
import java.time.LocalDateTime;

import demo.model.User;
import lombok.Data;

@Data
public class LoginVO implements Serializable {

    private static final long serialVersionUID = 4112257946047140561L;

    private Integer userId;

    private String email;

    private String nickname;

    private String address;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public LoginVO(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.address = user.getAddress();
        this.createAt = user.getCreateAt();
        this.updateAt = user.getUpdateAt();
    }
}
