package pdp.uz.appclickup.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pdp.uz.appclickup.entity.WorkSpace;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {
    @NotNull
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String color;

    @NotNull
    private Integer workSpace;
}
