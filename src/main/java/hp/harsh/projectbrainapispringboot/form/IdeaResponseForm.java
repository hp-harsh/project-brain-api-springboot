package hp.harsh.projectbrainapispringboot.form;

import java.util.Set;

import hp.harsh.projectbrainapispringboot.model.Idea;
import lombok.Data;

@Data
public class IdeaResponseForm {
    private Set<Idea> data;
}