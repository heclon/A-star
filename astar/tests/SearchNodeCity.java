package astar.tests;
import java.util.*;

import astar.ASearchNode;
import astar.ISearchNode;

/**
 * Test case from wikipedia
 * http://de.wikipedia.org/wiki/A*-Algorithmus
 */

public class SearchNodeCity extends ASearchNode{
    private String name;
    private SearchNodeCity parent;
    private SearchNodeCity goal;
    // adjacency matrix
    private HashMap<String, HashMap<String, Integer>> adjacencyMatrix = 
                            new HashMap<String,HashMap<String, Integer>>();
    public SearchNodeCity(String name) {
        this.name = name; 
        this.fillAdjacencyMatrix();

    }
    private void fillAdjacencyMatrix() {
        HashMap<String, Integer> fromSaarbrucken = new HashMap<String, Integer>();
        fromSaarbrucken.put("Orlando", 70);
        fromSaarbrucken.put("Karlsruhe", 145);
        HashMap<String, Integer> fromOrlando = new HashMap<String, Integer>();
        fromOrlando.put("Sarasota", 103);
        fromOrlando.put("Ludwigshafen", 53);
        HashMap<String, Integer> fromKarlsruhe = new HashMap<String, Integer>();
        fromKarlsruhe.put("Heilbronn", 84);
        HashMap<String, Integer> fromSarasota = new HashMap<String, Integer>();
        fromSarasota.put("Naples", 116);
        HashMap<String, Integer> fromLudwigshafen = new HashMap<String, Integer>();
        fromLudwigshafen.put("Naples", 183);
        HashMap<String, Integer> fromHeilbronn = new HashMap<String, Integer>();
        fromHeilbronn.put("Naples", 102);
        adjacencyMatrix.put("Miami", fromSaarbrucken);
        adjacencyMatrix.put("Orlando",fromOrlando);
        adjacencyMatrix.put("Sarasota", fromSarasota);
        adjacencyMatrix.put("Karlsruhe", fromKarlsruhe);
        adjacencyMatrix.put("Ludwigshafen", fromLudwigshafen);
        adjacencyMatrix.put("Heilbronn", fromHeilbronn);
    }
    
    //heuristic cost to the goal node
    public double h() {
        switch(this.name) {
            case "Miami":     return 222;
            case "Orlando":  return 158;
            case "Karlsruhe":       return 140;
            case "Sarasota":       return 96;
            case "Ludwigshafen":    return 108;
            case "Heilbronn":       return 87;
            case "Naples":        return 0;
            default:                return 0;
        }
    }
    //costs to a successor
    public double c(ISearchNode successor) {
        return this.adjacencyMatrix.get(this.name)
            .get(this.castToSearchNodeCity(successor).getName());
    }
    // a node possesses or computes his successors
    public ArrayList<ISearchNode> getSuccessors() {
        ArrayList<ISearchNode> successors = new ArrayList<ISearchNode>();
        Set<String> successorNamesSet = this.adjacencyMatrix.get(this.name).keySet();
        for(String successorName : successorNamesSet) {
            successors.add(new SearchNodeCity(successorName));
        }
        return successors;
    }
    // get parent of node in a path
    public SearchNodeCity getParent() {
        return this.parent;
    }
    //set parent
    public void setParent(ISearchNode parent){
        this.parent = this.castToSearchNodeCity(parent);
    }
    
    public Integer keyCode() {
    	return this.name.hashCode();
    }

    public boolean equals(Object other) {
         if(other instanceof SearchNodeCity) {
            SearchNodeCity otherNode = (SearchNodeCity) other;
            return (this.name == otherNode.getName());
        }
        return false;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String getName() {
        return this.name;
    }
    private SearchNodeCity castToSearchNodeCity(ISearchNode other) {
        return (SearchNodeCity) other;
    }
    public String toString() {
        return this.name + ",f:" + this.f();
    }

}
