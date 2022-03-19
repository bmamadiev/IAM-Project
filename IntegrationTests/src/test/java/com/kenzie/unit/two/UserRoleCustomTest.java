package com.kenzie.unit.two;
import com.kenzie.unit.two.iam.storage.Storage;
import com.kenzie.unit.two.employee.service.UserOrRoleNotFoundException;
import com.kenzie.unit.two.iam.models.Role;
import com.kenzie.unit.two.iam.models.User;
import com.kenzie.unit.two.iam.service.RoleService;
import com.kenzie.unit.two.iam.service.UserRoleService;
import com.kenzie.unit.two.iam.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserRoleCustomTest {

    private UserRoleService userRoleService;
    private UserService userService;
    private RoleService roleService;
    private Storage storage;
    MockedStatic<LogManager> logManagerMock;

    @BeforeEach
    void beforeEach() {
        logManagerMock = mockStatic(LogManager.class);
        this.storage = new Storage();
        this.userService = mock(UserService.class);
        this.roleService = mock(RoleService.class);
        this.userRoleService = new UserRoleService(storage);
    }

    @Test
    void missingUserRoleThrowsException_TASK_6() {
        // TODO - write this test
        // Hint - Look at the test in missingUserRoleThrowsException_TASK_2.
        // This will demonstrate how to assert an exception has been thrown

        when(this.userService.getUserByUserName(any())).thenReturn(null);
        when(this.roleService.getRoleByRoleName(any())).thenReturn(null);

        User user = this.userService.getUserByUserName("sampleUser");
        Role role = this.roleService.getRoleByRoleName("sampleRole");

        assertThrows(UserOrRoleNotFoundException.class,
                () -> this.userRoleService.doesUserHaveRole(user,role));
    }
}