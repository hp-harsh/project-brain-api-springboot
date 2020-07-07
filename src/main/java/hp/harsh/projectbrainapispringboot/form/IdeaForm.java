package hp.harsh.projectbrainapispringboot.form;

import lombok.Data;

@Data
public class IdeaForm {
    private String id;
    private String title;
    private String context;
    private String content;

}