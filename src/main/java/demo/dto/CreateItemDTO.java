package demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateItemDTO {
    private String title;

    private String type;

    private String description;

    private String price;
}
