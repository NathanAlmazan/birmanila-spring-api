package org.bir.rrmanila.statistics;

import java.util.HashMap;
import java.util.Map;

public interface CosineDistance {

    default double computeCosineDistance(String query, String result) {
        // remove special characters, convert to lower case, and split by words
        String[] wordsA = query.replaceAll("[^A-Za-z0-9]","").toLowerCase().split(" ");
        String[] wordsB = result.replaceAll("[^A-Za-z0-9]","").toLowerCase().split(" ");

        // create hash map to store the words' count
        HashMap<String, Integer> setA = new HashMap<>();
        HashMap<String, Integer> setB = new HashMap<>();

        for (String word : wordsA) {
            // if exist update count else add word to set A
            if (setA.containsKey(word)) setA.computeIfPresent(word, (k, v) -> v + 1);
            else setA.put(word, 1);

            // add words to second set
            if (!setB.containsKey(word)) setB.put(word, 0);
        }

        for (String word : wordsB) {
            // if exist update count else add word to set B
            if (setB.containsKey(word)) setB.computeIfPresent(word, (k, v) -> v + 1);
            else setB.put(word, 1);

            // add words to first set
            if (!setA.containsKey(word)) setA.put(word, 0);
        }

        // Get L2 Norm
        double setANorm = 0.0;
        double setBNorm = 0.0;

        // Summing up squares of each frequency
        for (Integer value : setA.values()) setANorm += Math.pow(value, 2.0);
        for (Integer value : setB.values()) setBNorm += Math.pow(value, 2.0);

        // taking a square root of summation
        setANorm = Math.sqrt(setANorm);
        setBNorm = Math.sqrt(setBNorm);

        double cosineDistance = 0.0;

        // compute cosine distance
        for (Map.Entry<String, Integer> entry : setA.entrySet()) {
            Integer countA = entry.getValue();
            Integer countB = setB.get(entry.getKey());
            if (countB != null && countA > 0 && countB > 0) {
                cosineDistance += (countA / setANorm) * (countB / setBNorm);
            }
        }

        return cosineDistance;
    }
}
