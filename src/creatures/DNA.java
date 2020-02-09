package creatures;

import creatures.actions.Action;
import environment.Edible;
import environment.TileHoldable;

import java.util.ArrayList;
import java.util.HashMap;

public class DNA {

    /*
    TRAITS :
    Speed, color
     */
    public HashMap<String, Integer> traits = new HashMap<String, Integer>();
    /*
    TENDENCIES :
    Weighted actions + weighted action parameters
    Format "actionName.parameterName" for entry
     */
    public HashMap<Action, Double> tendencies = new HashMap<Action, Double>();
    public HashMap<String, Double> tendenciesParameters = new HashMap<String, Double>();
    public HashMap<Class<? extends TileHoldable>, Double> trackedEntities = new HashMap<Class<? extends TileHoldable>, Double>();
    public ArrayList<Class< ? extends Edible>> diet = new ArrayList<Class< ? extends Edible>>();

}
