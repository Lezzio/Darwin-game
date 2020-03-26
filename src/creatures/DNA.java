package creatures;

import creatures.actions.Action;
import environment.Edible;
import environment.TileHoldable;

import java.util.ArrayList;
import java.util.HashMap;

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
    public HashMap<String, Object> tendenciesParameters = new HashMap<String, Object>();
    public HashMap<Class<? extends TileHoldable>, BoundedDouble> trackedEntities = new HashMap<Class<? extends TileHoldable>, BoundedDouble>();
    public ArrayList<Class< ? extends Edible>> diet = new ArrayList<Class< ? extends Edible>>();

    public DNA clone() throws CloneNotSupportedException {
        return (DNA) super.clone();
    }

}