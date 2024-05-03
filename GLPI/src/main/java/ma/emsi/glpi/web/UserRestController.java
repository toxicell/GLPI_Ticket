package ma.emsi.glpi.web;


import lombok.Data;
import ma.emsi.glpi.Entity.Role;
import ma.emsi.glpi.Entity.User;
import ma.emsi.glpi.Services.UserServ;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class UserRestController {
        private final UserServ userServ;


    public UserRestController(UserServ userServ) {
        this.userServ = userServ;
    }


    @GetMapping(path = "/users")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public List<User> userlist(){
         return userServ.ListUsers();
    }

    @PostMapping(path = "/users")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public User saveUser(@RequestBody User user){
        return userServ.addNewUser(user);
    }


    @GetMapping(path = "/users/{username}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public User getUserByUsername(@PathVariable String username) {
        return userServ.findUserByUserName(username);
    }

    @GetMapping(path = "/users/{username}/roles")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public List<Role> getUserRoles(@PathVariable String username) {
        User user = userServ.findUserByUserName(username);
        return user != null ? user.getRoles() : Collections.emptyList();
    }

    

    @PostMapping(path = "/addRoletoUser")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public void addRoleToUser(@RequestBody AddRoleForm request){
        userServ.addRoleToUser(request.getUserName(), request.getRoleName());
    }



}

@Data
class AddRoleForm{
    private String userName;
    private String roleName;



    public String getRoleName() {
        System.out.printf("roleName: %s\n", roleName);
        return roleName;
    }

    public String getUserName() {
        System.out.printf("userName: %s\n", userName);
        return userName;
    }
}