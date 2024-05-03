package ma.emsi.glpi.Services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ma.emsi.glpi.Entity.Role;
import ma.emsi.glpi.Entity.Ticket;
import ma.emsi.glpi.Entity.User;
import ma.emsi.glpi.Reposetories.RoleRepos;
import ma.emsi.glpi.Reposetories.TicketRepos;
import ma.emsi.glpi.Reposetories.UserRepos;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServImpl implements UserServ {

    private final TicketRepos ticketRepos;
    private UserRepos userRepos;
    private RoleRepos roleRepos;





    @Override
    public User addNewUser(User user) {
        String pw = user.getPassword();
        return userRepos.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepos.save(role);
    }





    @Override
    public User findUserByUserName(String username) {
        return userRepos.findByUsername(username);
    }

    @Override
    public List<User> ListUsers() {
        return userRepos.findAll();
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepos.findByRoleName(roleName);
    }


    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = findUserByUserName(username);
        System.out.printf("method addRoleToUser user object : "+ user + "\n");
        Role role = findRoleByRoleName(roleName);
        System.out.printf("method addRoleToUser Role object :"+ role+ "\n");
        if(user != null ) {
            if(user.getRoles()!=null) {
                user.getRoles().add(role);
                role.getUsers().add(user);
            }
            userRepos.save(user);
            roleRepos.save(role);
        }else {
            System.out.printf("user not found: %s\n", username);
        }

    }


}
