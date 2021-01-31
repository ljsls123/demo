package demo.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordDTO implements Serializable {
    private static final long serialVersionUID = -2083323796835245977L;

    private String oldPassword;

    private String newPassword;
}
