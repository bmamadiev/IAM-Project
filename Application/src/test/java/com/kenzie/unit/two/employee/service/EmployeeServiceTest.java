package com.kenzie.unit.two.employee.service;

import com.kenzie.unit.two.employee.lambda.models.ViewEmployeePayCheckRequest;
import com.kenzie.unit.two.employee.service.models.Employee;
import com.kenzie.unit.two.iam.models.Department;
import com.kenzie.unit.two.iam.models.Role;
import com.kenzie.unit.two.iam.models.User;
import com.kenzie.unit.two.iam.service.RoleService;
import com.kenzie.unit.two.iam.service.UserRoleService;
import com.kenzie.unit.two.iam.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    EmployeeService service;
    RoleService roleService;
    UserService userService;
    UserRoleService userRoleService;
    Logger logger;
    MockedStatic<LogManager> logManagerMock;

    @BeforeEach
    void beforeEach() {
        logManagerMock = mockStatic(LogManager.class);
        this.logger = mock(Logger.class);
        logManagerMock.when(LogManager::getLogger).thenReturn(logger);

        this.userRoleService = mock(UserRoleService.class);
        this.userService = mock(UserService.class);
        this.roleService = mock(RoleService.class);
        this.service = new EmployeeService(userRoleService, userService, roleService);
    }

    @AfterEach
    void afterEach() {
        logManagerMock.close();
    }

    @Test
    void getEmployeePayCheck() {
        Department department = new Department(UUID.randomUUID(), "Marketing");
        User user = new User(UUID.randomUUID(), "margaretparis", department);
        Role role = new Role(UUID.randomUUID(), "view-paycheck");

        when(this.userService.getUserByUserName(any())).thenReturn(user);
        when(this.roleService.getRoleByRoleName(any())).thenReturn(role);
        when(this.userRoleService.doesUserHaveRole(any(), any())).thenReturn(true);

        EmployeeService spy = Mockito.spy(this.service);

        ViewEmployeePayCheckRequest request = new ViewEmployeePayCheckRequest();
        request.setEmployeeUserName("margaretparis");
        request.setRequesterUserName("margaretparis");

        Employee employee = spy.getEmployeePayCheck(request);
        Employee result = service.getEmployeePayCheck(request);

        assertEquals(employee.getUserName(), "margaretparis");
        assertEquals(employee.getDepartment(), department);
        assertEquals(result.getUserName(), "margaretparis");
        assertEquals(result.getPayCheck(), "90000");
    }
    @Test
    void getEmployeePayCheckThrowsException() {
        Department department = new Department(UUID.randomUUID(), "Marketing");
        User user = new User(UUID.randomUUID(), "margaretparis", department);

        when(this.userService.getUserByUserName(any())).thenReturn(user);
        when(this.roleService.getRoleByRoleName(any())).thenReturn(null);

        ViewEmployeePayCheckRequest request = new ViewEmployeePayCheckRequest();

        assertThrows(UserOrRoleNotFoundException.class, () -> service.getEmployeePayCheck(request));
    }

    @Test
    void testTheCorrectUser() {
        String requestingUserName  = "requestingUserName";
        String userName = "requestingUserName";
        boolean result = service.theCorrectUser(requestingUserName, userName);
        assertTrue(result);
    }

    @Test
    void testInTheSameDepartment() {
        String requestingDepartment  = "requestingDepartment";
        String userDepartment = "requestingDepartment";
        boolean result = service.inTheSameDepartment(requestingDepartment, userDepartment);
        assertTrue(result);
    }
}