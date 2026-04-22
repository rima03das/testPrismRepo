package cee.wfe.userrole.helper;

import org.bson.BsonValue;

import com.mongodb.client.result.UpdateResult;

public class TestMongoTemplateStub extends UpdateResult {

	@Override
	public boolean wasAcknowledged() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getMatchedCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	//@Override
	public boolean isModifiedCountAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getModifiedCount() {
		// TODO Auto-generated method stub
		System.out.println("in getModifiedCount() ");
		return 1;
	}

	@Override
	public BsonValue getUpsertedId() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
