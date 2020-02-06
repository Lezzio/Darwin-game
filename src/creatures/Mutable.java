package creatures;

public interface Mutable {
    public DNA getDNA();
    public void setDNA();
    public DNA mutate();
}
