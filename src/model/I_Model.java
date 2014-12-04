 package model;

import java.util.Date;


/**I_Model interface*/
public interface I_Model {
	
    public boolean addPlayer(String name, String password);

    public boolean addGame(Player player);
}
