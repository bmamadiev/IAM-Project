package com.kenzie.unit.two;

import com.kenzie.unit.two.employee.lambda.models.ViewEmployeePayCheckRequest;
import com.kenzie.unit.two.employee.service.EmployeeService;
import com.kenzie.unit.two.employee.service.UserOrRoleNotFoundException;
import com.kenzie.unit.two.iam.models.Role;
import com.kenzie.unit.two.iam.models.User;
import com.kenzie.unit.two.iam.models.UserRoles;
import com.kenzie.unit.two.iam.service.RoleService;
import com.kenzie.unit.two.iam.service.UserRoleService;
import com.kenzie.unit.two.iam.service.UserService;
import com.kenzie.unit.two.iam.storage.Storage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class UserRoleCustomTest {

    Storage storage;
    EmployeeService service;
    RoleService roleService;
    UserService userService;
    UserRoleService userRoleService;
    UserRoles userRoles;
    Logger logger;
    User user = null;
    Role role = null;
    MockedStatic<LogManager> logManagerMock;

    @BeforeEach
    void beforeEach() {
        logManagerMock = mockStatic(LogManager.class);
        this.logger = mock(Logger.class);
        logManagerMock.when(LogManager::getLogger).thenReturn(logger);

        this.userRoleService = mock(UserRoleService.class);
        this.userService = mock(UserService.class);
        this.roleService = mock(RoleService.class);
        this.storage = mock(Storage.class);
        this.userRoles = mock(UserRoles.class);
        this.user = mock(User.class);
        this.role = mock(Role.class);
        this.service = new EmployeeService(userRoleService, userService, roleService);
    }

    @AfterEach
    void afterEach() {
        logManagerMock.close();
    }

    @Test
    void missingUserRoleThrowsException_TASK_6() {
        // TODO - write this test
        // Hint - Look at the test in missingUserRoleThrowsException_TASK_2.
        // This will demonstrate how to assert an exception has been thrown
        when(this.userService.getUserByUserName(any())).thenReturn(null);
        when(this.roleService.getRoleByRoleName(any())).thenReturn(null);
        when(storage.getUserRoles(user)).thenReturn(null);
        when(userRoles.getRoles()).thenReturn(null);

        Exception exception = assertThrows(UserOrRoleNotFoundException.class,
                () -> this.userRoleService.doesUserHaveRole(user, role));
    }
}
