package creatures;

public interface Livable {
    public double getHealth();
    public double modifyHealth();
    public double setHealth();
    public void reproduce();
    public boolean isAlive();
    public boolean die();
}
