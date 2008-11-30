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
public class TestResultItem implements Serializable {

    String pattern;
    String string;
    boolean matches;
    Result table;
    Result NFA;
    Result DFA;
    Result minGraph;

    public TestResultItem(String pattern, String string, boolean matches) {
        this.pattern = pattern;
        this.string = string;
        this.matches = matches;
    }

    public Result getDFA() {
        return DFA;
    }

    public Result getNFA() {
        return NFA;
    }

    public boolean isMatches() {
        return matches;
    }

    public Result getMinGraph() {
        return minGraph;
    }

    public String getPattern() {
        return pattern;
    }

    public String getString() {
        return string;
    }

    public Result getTable() {
        return table;
    }

    public void setDFA(Result DFA) {
        this.DFA = DFA;
    }

    public void setNFA(Result NFA) {
        this.NFA = NFA;
    }

    public void setMinGraph(Result minGraph) {
        this.minGraph = minGraph;
    }

    public void setTable(Result table) {
        this.table = table;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TestResultItem other = (TestResultItem) obj;
        if (this.pattern != other.pattern && (this.pattern == null || !this.pattern.equals(other.pattern))) {
            return false;
        }
        if (this.string != other.string && (this.string == null || !this.string.equals(other.string))) {
            return false;
        }
        if (this.matches != other.matches) {
            return false;
        }
        if (this.table != other.table && (this.table == null || !this.table.equals(other.table))) {
            return false;
        }
        if (this.NFA != other.NFA && (this.NFA == null || !this.NFA.equals(other.NFA))) {
            return false;
        }
        if (this.DFA != other.DFA && (this.DFA == null || !this.DFA.equals(other.DFA))) {
            return false;
        }
        if (this.minGraph != other.minGraph && (this.minGraph == null || !this.minGraph.equals(other.minGraph))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.pattern != null ? this.pattern.hashCode() : 0);
        hash = 67 * hash + (this.string != null ? this.string.hashCode() : 0);
        hash = 67 * hash + (this.matches ? 1 : 0);
        hash = 67 * hash + (this.table != null ? this.table.hashCode() : 0);
        hash = 67 * hash + (this.NFA != null ? this.NFA.hashCode() : 0);
        hash = 67 * hash + (this.DFA != null ? this.DFA.hashCode() : 0);
        hash = 67 * hash + (this.minGraph != null ? this.minGraph.hashCode() : 0);
        return hash;
    }
    
    
}
