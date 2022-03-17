package com.kenzie.unit.two.iam.models;

import com.kenzie.unit.two.iam.entities.Roles;

import com.kenzie.ata.ExcludeFromJacocoGeneratedReport;

import java.util.List;

@ExcludeFromJacocoGeneratedReport
public class FunctionalRole {

    // Roles required to do this action
    private List<Roles> roles;

    public FunctionalRole(List<Roles> roles) {
        this.roles = roles;
    }

    // Compare incoming list to the ones required for this class.
    public Boolean matches(List<Role> roleList) {
        // TODO Task 5 - Write your code here ...
        boolean matchCreateInvoiceRole = false;
        boolean matchViewClientRole = false;
        if (roleList != null) {
            if (roleList.contains(Roles.CREATE_INVOICE.getRoleName())) {
                matchCreateInvoiceRole = true;
                }
            if (roleList.contains(Roles.VIEW_CLIENT.getRoleName())) {
                matchViewClientRole = true;
                }
        }
        return matchCreateInvoiceRole && matchViewClientRole;
    }

//        if (roleList != null) {
//                if (roleList.contains(Roles.CREATE_INVOICE.getRoleName())) {
//                    return true;
//                }
//                if (roleList.contains(Roles.VIEW_CLIENT.getRoleName())) {
//                    return true;
//                }
//        }
//        return false;
//    }
}
