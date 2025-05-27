package edu.kata.spring_rest.auth;

import edu.kata.spring_rest.model.Role;
import edu.kata.spring_rest.model.User;
import edu.kata.spring_rest.model.UserDTO;
import edu.kata.spring_rest.service.RoleService;
import edu.kata.spring_rest.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DefaultUsersInitializer {
    private final UserService userService;
    private final RoleService roleService;

    public DefaultUsersInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        try {
            if (userService.getUsersList().isEmpty()) {
                Role roleAdmin = new Role("ROLE_ADMIN");
                Role roleUser = new Role("ROLE_USER");

                roleService.saveRole(roleAdmin);
                roleService.saveRole(roleUser);

                User admin = new User("admin", "admin");
                admin.setPassword("admin");
                List<Role> adminRoles = new ArrayList<>();
                adminRoles.add(roleAdmin);
                admin.setRoles(adminRoles);

                User user = new User("test_user", "test_user");
                user.setPassword("user");
                List<Role> userRoles = new ArrayList<>();
                userRoles.add(roleUser);
                user.setRoles(userRoles);

                userService.createUser(UserDTO.fromUser(admin));
                userService.createUser(UserDTO.fromUser(user));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
