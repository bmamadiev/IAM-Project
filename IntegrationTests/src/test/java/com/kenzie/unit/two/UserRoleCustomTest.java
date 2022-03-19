package com.kenzie.unit.two;

import com.kenzie.unit.two.employee.service.UserOrRoleNotFoundException;
import com.kenzie.unit.two.iam.models.Role;
import com.kenzie.unit.two.iam.models.User;
import com.kenzie.unit.two.iam.service.RoleService;
import com.kenzie.unit.two.iam.service.UserRoleService;
import com.kenzie.unit.two.iam.service.UserService;
import com.kenzie.unit.two.iam.storage.Storage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserRoleCustomTest {
    //Instance Variables
//    @Mock
//    RoleService roleService;
//    @Mock
//    UserService userService;
//    @Mock
//    UserRoleService userRoleService;
//    @Mock
//    Storage storage = new Storage();
//
//    @BeforeEach
//    void beforeEach() {
//        MockitoAnnotations.openMocks(this);
//        this.userService = new UserService(storage);
//        this.roleService = new RoleService(storage);
//        this.userRoleService = new UserRoleService(storage);
//    }
//
//    @Test
//    void missingUserRoleThrowsException_TASK_6() {
//
//        when(this.userService.getUserByUserName(any())).thenReturn(null);
//        when(this.roleService.getRoleByRoleName(any())).thenReturn(null);
//
//        User testUser = userService.getUserByUserName("user");
//        Role testRole = roleService.getRoleByRoleName("role");
//
//        assertThrows(UserOrRoleNotFoundException.class,
//                () -> this.userRoleService.doesUserHaveRole(testUser, testRole));
//    }
}