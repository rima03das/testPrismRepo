package com.cee.userrole.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.cee.userrole.entities.UserRoleEntity;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface UserRoleRepository extends MongoRepository<UserRoleEntity, String> {

	@Query(value = "{ 'applicationId' : ?0, 'status' : ?1 }", fields = "{ 'id' : 1, 'applicationId': 1, 'role' : 1, 'workflowSequenceIds': 1}")
	List<UserRoleEntity> findByApplicationIdAndStatusOrderByAuditingEntitiesCreateDateAsc(String applicationId,
			String status);
//	@Query(value = "{ 'applicationId' : ?0, 'status' : ?1 }", fields = "{ 'id' : 1, 'applicationId': 1, 'role' : 1, 'workflowSequenceIds': 1}")
	List<UserRoleEntity> findByApplicationIdAndStatusOrderByAuditingEntitiesUpdateDateDesc(String applicationId,
			String status);

	@Query(value = "{ 'id' : ?0, 'status' : ?1 }", fields = "{ 'id' : 1, 'applicationId': 1, 'role' : 1, 'workflowSequenceIds': 1}")
	Optional<UserRoleEntity> findByIdAndStatus(String id, String status);

	@Query(value = "{ 'applicationId' : ?0, 'role' : ?1, 'status' : ?2 }", fields = "{ 'id' : 1, 'applicationId': 1, 'role' : 1, 'workflowSequenceIds': 1}")
	Optional<UserRoleEntity> findByApplicatioIdAndRoleAndStatus(String applicationId, String role, String status);

	List<UserRoleEntity> findByApplicationIdAndStatus(String applId, String name);
	
	/** Methods for dashboard count starts**/
	Long countByApplicationIdAndStatus(String applicationId, String status);
	/** Methods for dashboard count ends**/

}
