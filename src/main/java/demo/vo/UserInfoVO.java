package demo.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import demo.model.User;
import lombok.Data;

@Data
public class UserInfoVO implements Serializable {

    private static final long serialVersionUID = -2996204418686525265L;
    private Integer userId;

    private String email;

    private String nickName;

    private String userName;

    private String gender;

    private String telephone;

    private String userType;

    private String address;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public UserInfoVO(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.nickName = user.getNickname();
        this.address = user.getAddress();
        this.createAt = user.getCreateAt();
        this.updateAt = user.getUpdateAt();
        this.userName = user.getUserName();
        if ("1".equals(user.getGender())) {
            this.gender = "男";
        } else {
            this.gender = "女";
        }
        this.telephone = user.getTelephone();
        if ("1".equals(user.getUserType())) {
            this.userType = "用户";
        } else {
            this.userType = "装修方";
        }
    }
}
