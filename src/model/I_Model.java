 package model;

/**I_Model interface*/
public interface I_Model {
	
    public Player addPlayer(String name, String password);

    public boolean addGame(Player player);
}
