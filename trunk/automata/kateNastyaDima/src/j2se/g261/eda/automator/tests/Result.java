/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package j2se.g261.eda.automator.tests;

import java.io.Serializable;

/**
 *
 * @author nastya
 */
public class Result implements Serializable {

    boolean matches;
    long averageTime;
    long minTime;
    long maxTime;

    public Result(boolean matches, long averageTime, long minTime, long maxTime) {
        this.matches = matches;
        this.averageTime = averageTime;
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    public long getAverageTime() {
        return averageTime;
    }

    public boolean isMatches() {
        return matches;
    }

    public long getMaxTime() {
        return maxTime;
    }

    public long getMinTime() {
        return minTime;
    }

    @Override
    public String toString() {
        return averageTime + " (" + minTime + ", " + maxTime + ")";
    }
}
