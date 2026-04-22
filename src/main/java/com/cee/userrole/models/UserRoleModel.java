package com.cee.userrole.models;

import java.util.ArrayList;
import java.util.List;

public class UserRoleModel {

    private String id;

    private String applicationId;

    private String role;

    private List<String> workflowSequenceIds = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getWorkflowSequenceIds() {
        return workflowSequenceIds;
    }

    public void setWorkflowSequenceIds(List<String> workflowSequenceIds) {
        this.workflowSequenceIds = workflowSequenceIds;
    }

}
