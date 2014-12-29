 package model;

/**I_Model interface*/
public interface I_Model {
	
    public Player addPlayer(String name, String password);

    public boolean addGame(Player player);
    
    public int loginProcess(String name, String pass);
    
    public Game deal(Player player);
    
    public Player getCurrentPlayer();
    
    public void hit(Game game);
    
    public boolean isBusted(Game game);
    
    public Card stand(Game game);
    
    public boolean whoWon(Game game);
    
    public String getFact();
}
