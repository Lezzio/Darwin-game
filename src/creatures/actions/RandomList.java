package creatures.actions;

import creatures.BoundedDouble;

import java.util.HashMap;
import java.util.NavigableMap;
import java.util.TreeMap;

public class RandomList<E> {

    private final NavigableMap<Double, E> randomMap = new TreeMap<Double, E>();
    private double totalWeight = 0;

    public static <K> RandomList<K> from(HashMap<K, BoundedDouble> hashMap) {
        RandomList<K> randomList = new RandomList<>();
        hashMap.keySet().forEach(key -> randomList.add(key, hashMap.get(key).getValue()));
        return randomList;
    }
    public void add(E element, double weight) {
        totalWeight += weight;
        randomMap.put(totalWeight, element);
    }
    public E getRandomElement() {
        double value = Math.random() * totalWeight;
        return randomMap.higherEntry(value).getValue();
    }
}