package com.cee.userrole.entities;

import java.util.ArrayList;
import java.util.List;

import com.cee.userrole.models.BaseEntity;
import com.fasterxml.jackson.annotation.JsonView;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "v2_user_roles")
public class UserRoleEntity extends BaseEntity {

    @Id
    @JsonView(value = { View.UserRoleView.Query.class })
    private String id;
    
    @NotBlank(message = "should not be blank")
    @JsonView(value = { View.UserRoleView.Post.class})
    @Field("application_id")
    private String applicationId;

    @NotBlank(message = "should not be blank")
    private String status;

    @NotBlank(message = "should not be blank")
    @JsonView(value = { View.UserRoleView.Post.class})
    private String role;
    
    @JsonView(value = { View.UserRoleView.Post.class})
    @Field("workflow_sequence_ids")
    private List<String> workflowSequenceIds = new ArrayList<>();

    public UserRoleEntity(){}

    public UserRoleEntity(String applicationId, String status, String role,
            List<String> workflowSequenceIds) {
        this.applicationId = applicationId;
        this.status = status;
        this.role = role;
        this.workflowSequenceIds = workflowSequenceIds;
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    

    public List<String> getWorkflowSequenceIds() {
        return workflowSequenceIds;
    }

    public void setWorkflowSequenceIds(List<String> workflowSequenceIds) {
        this.workflowSequenceIds = workflowSequenceIds;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserRoleEntity [applicationId=" + applicationId + ", id=" + id + ", role=" + role + ", status=" + status
                + ", workflowSequenceIds=" + workflowSequenceIds + "]";
    }

}