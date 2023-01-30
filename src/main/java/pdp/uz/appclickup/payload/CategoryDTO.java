package pdp.uz.appclickup.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.appclickup.entity.Project;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    @NotNull(message = "name bosh bolishi mumkin emas")
    private String name;

    @NotNull(message = "color bosh bolishi mumkin emas")
    private String color;

    @NotNull(message = "project bosh bolishi mumkin emas")
    private Integer project;

    @NotNull(message = "accessType bosh bolishi mumkin emas")
    private String accessType;

    @NotNull(message = "archived bosh bolishi mumkin emas")
    private String archived;
}
