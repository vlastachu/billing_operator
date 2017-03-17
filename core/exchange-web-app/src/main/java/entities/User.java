package entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
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

    public User(String userId) {
        this.userId = userId;
    }

    public User(String userId, String userPswd, String groupId) {
        this.userId = userId;
        this.userPswd = userPswd;
        this.groupId = groupId;
        this.money = 0D;
    }
}
