package ma.emsi.glpi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name ="Username",unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;


    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();


    @Override
    public String toString() {
        return "User{id=" + userId + ", roles :" + roles + ", username='" + username  + "'}";
    }


}
