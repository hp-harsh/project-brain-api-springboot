package hp.harsh.projectbrainapispringboot.form;

import lombok.Data;

@Data
public class NewIdeaForm {
    private String originalId;
    private String username;
    private String title;
    private String context;
    private String content;
}