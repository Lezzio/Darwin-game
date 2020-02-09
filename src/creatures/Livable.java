package creatures;

public interface Livable {
    public double getHealth();
    public void setHealth(double health);
    public Creature reproduce();
    public boolean isAlive();
    public boolean die();
}
