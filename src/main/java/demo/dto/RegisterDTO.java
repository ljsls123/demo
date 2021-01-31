package demo.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO implements Serializable {
    private static final long serialVersionUID = 8982825841435810603L;
    private String email;

    private String password;

    private String name;

    private String gender;

    private String telephone;

    private String nickname;

    private String address;

    private String type;
}
