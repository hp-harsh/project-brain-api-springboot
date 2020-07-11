package hp.harsh.projectbrainapispringboot.form;

import java.util.Set;

import hp.harsh.projectbrainapispringboot.model.Brain;
import lombok.Data;

@Data
public class BrainResponseForm {
    private Set<Brain> data;
}