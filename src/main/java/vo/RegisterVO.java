package vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author roger
 * @since 2021/1/14 下午6:31
 */
@Data
@AllArgsConstructor
public class RegisterVO implements Serializable {

    private static final long serialVersionUID = -2945961149863574453L;
    private Integer userId;

    private LocalDateTime createAt;
}
