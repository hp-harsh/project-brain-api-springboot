package hp.harsh.projectbrainapispringboot.form;

import javax.persistence.Column;

import lombok.Data;

@Data
public class BrainFollowForm {
    
    @Column(name="username")
    private String username;

    @Column(name="username")
    private String usernameToBeFollowed;
}