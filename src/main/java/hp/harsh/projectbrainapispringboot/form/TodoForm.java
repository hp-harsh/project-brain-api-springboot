package hp.harsh.projectbrainapispringboot.form;

import javax.persistence.Column;

import lombok.Data;

@Data
public class TodoForm {
    private String username;

    @Column(name = "id")
    private Long ideaId;
}