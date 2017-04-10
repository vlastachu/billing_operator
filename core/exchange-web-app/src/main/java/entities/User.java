package entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@Entity
@Cacheable(false)
@Table(name = "USERS_TABLE")
@NamedQueries({
        @NamedQuery(name = "User.getById", query = "SELECT user FROM User user WHERE user.userId = :id")
})
public class User implements Serializable{
    @Id
    @Column(name = "USER_ID", nullable = false)
    private String userId;

    @Column(name = "USER_PSWD")
    private String userPswd;

    @Column(name = "GROUP_ID")
    private String groupId;

    @Column(name = "MONEY", nullable = false)
    private Double money;

    public User() {
    }
}
