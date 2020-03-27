package creatures;

public interface Mutable {
    public DNA getDNA();
    public void setDNA(DNA dna);
    public DNA mutate();
}
