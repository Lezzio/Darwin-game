package creatures;

public abstract class Creature implements Livable, Mutable {

    private int id;
    private DNA dna;
    private double health;

    public Creature() {

    }

    /**
     * Called by the game system to perform a behaviour
     */
    public void update() {

    }
    @Override
    public void reproduce() {

    }

    @Override
    public double getHealth() {
        return 0;
    }

    @Override
    public double modifyHealth() {
        return 0;
    }

    @Override
    public double setHealth() {
        return 0;
    }

    @Override
    public boolean isAlive() {
        return false;
    }

    @Override
    public boolean die() {
        return false;
    }

    @Override
    public DNA getDNA() {
        return null;
    }

    @Override
    public DNA setDNA() {
        return null;
    }

    @Override
    public DNA mutate() {
        return null;
    }
}
