package creatures;

public interface LivingEntity {
    public double getHealth();
    public void addHealth(double health);
    public void setHealth(double health);
    public Creature reproduce();
    public boolean isAlive();
    public void die();
}
