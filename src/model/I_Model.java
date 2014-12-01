 package model;


/**I_Model interface*/
public interface I_Model {
	
    public boolean addTuple(String startPhoneNumber, String endPhoneNumber, String first, String last);

    public boolean modifyTuple(String startPhoneNumber, String endPhoneNumber, String first, String last , String phoneBeforeChanging);

    public boolean deleteTuple(String startPhoneNumber, String endPhoneNumber);
    
}
