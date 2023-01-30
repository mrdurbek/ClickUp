package pdp.uz.appclickup.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.appclickup.entity.Space;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

    @NotNull
    private String name;

    @NotNull
    private String color;

    @NotNull
    private Integer space;

    @NotNull
    private String accessType;

    @NotNull
    private String archived;
}
