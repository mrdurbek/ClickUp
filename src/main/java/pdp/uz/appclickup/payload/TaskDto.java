package pdp.uz.appclickup.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Integer status;

    @NotNull
    private Integer category;

    @NotNull
    private Integer priority;

    private Integer parentTask;

    @NotNull
    private String startedDate;

    @NotNull
    private String dueDate;

    @NotNull
    private Long estimateTime;

    private String activeDate;

}
