package edu.upf.model;

import java.io.Serializable;

/**
 * Represents a BiGram, which is a pair of two consecutive words.
 * This class is Serializable to support serialization.
 */

public class BiGram implements Serializable{
    // Two consecutive words
    private final String word1;
    private final String word2;

    /**
     * Constructs a BiGram object with two words.
     *
     * @param word1 The first word.
     * @param word2 The second word.
     */
    public BiGram(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;
    }

    /**
     * Gets the first word of the BiGram.
     *
     * @return The first word.
     */
    public String getWord1() {
        return word1;
    }

    /**
     * Gets the second word of the BiGram.
     *
     * @return The second word.
     */
    public String getWord2() {
        return word2;
    }

    /**
     * Compares this BiGram to the specified object.
     * Returns true if the object is also a BiGram and has the same pair of words
     * irrespective of their order.
     *
     * @param obj The object to compare.
     * @return True if the BiGrams are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BiGram biGram = (BiGram) obj;
        return (word1.equals(biGram.word1) && word2.equals(biGram.word2)) ||
               (word1.equals(biGram.word2) && word2.equals(biGram.word1));
    }

    /**
     * Generates a hash code for the BiGram.
     *
     * @return The hash code value for this BiGram.
     */
    @Override
    public int hashCode() {
        return word1.hashCode() + word2.hashCode();
    }
}