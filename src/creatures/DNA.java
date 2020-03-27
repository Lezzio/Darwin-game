package creatures;

import creatures.actions.Action;
import environment.Edible;
import environment.TileHoldable;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class DNA implements Cloneable {

    /*
    TRAITS :
    Speed, color
     */
    public HashMap<String, BoundedDouble> traits = new HashMap<String, BoundedDouble>();
    /*
    TENDENCIES :
    Weighted actions + weighted action parameters
    Format "actionName.parameterName" for entry
     */
    public HashMap<Action, BoundedDouble> tendencies = new HashMap<Action, BoundedDouble>();
    public HashMap<String, BoundedDouble> tendenciesParameters = new HashMap<String, BoundedDouble>();
    public HashMap<Class<? extends TileHoldable>, BoundedDouble> trackedEntities = new HashMap<Class<? extends TileHoldable>, BoundedDouble>();
    public HashMap<Class<? extends TileHoldable>, BoundedDouble> fledEntities = new HashMap<Class<? extends TileHoldable>, BoundedDouble>();
    public ArrayList<Class< ? extends Edible>> diet = new ArrayList<Class< ? extends Edible>>();

    /**
     * Clone the DNA and the BoundedDouble inside each HashMap (don't copy the same BoundedDouble reference or it'd be useless)
     * @return
     */
    public DNA clone() throws CloneNotSupportedException {
        DNA dna = new DNA();

        //Add the existing elements
        dna.traits.putAll(traits);
        dna.tendencies.putAll(tendencies);
        dna.tendenciesParameters.putAll(tendenciesParameters);
        dna.trackedEntities.putAll(trackedEntities);
        dna.fledEntities.putAll(fledEntities);
        dna.diet = diet; //Just pass reference, the diet can't evolve

        //Clone the BoundedDouble
        dna.traits.replaceAll((k, v) -> v.clone());
        dna.tendencies.replaceAll((k, v) -> v.clone());
        dna.tendenciesParameters.replaceAll((k, v) -> v.clone());
        dna.trackedEntities.replaceAll((k, v) -> v.clone());
        dna.fledEntities.replaceAll((k, v) -> v.clone());

        return dna;
    }
}