package astar.tests;
import org.junit.Test;

import astar.*;
import astar.tests.*;
import static org.junit.Assert.*;

import java.util.*; 

public class AStarTest {
    @Test
    public void SearchNodeTest2D() {
        GoalNode2D goalNode = new GoalNode2D(3, 3);
        SearchNode2D initialNode = new SearchNode2D(1, 1, null, goalNode);
        ArrayList<ISearchNode> path = new AStar().shortestPath(initialNode, goalNode);
        assertEquals(path.size(), 5);
    }
    @Test
    public void SearchNodeCityTest() {
        ISearchNode initialNode = new SearchNodeCity("Miami");
        ArrayList<ISearchNode> path = new AStar().shortestPath(initialNode, new GoalNodeCity("Naples"));
        double e = 0.00001;
        assertEquals(path.get(0).f(), 222.0, e);
        assertEquals(path.get(1).f(), 228, e);
        assertEquals(path.get(2).f(), 269, e);
        assertEquals(path.get(3).f(), 289, e);
        assertEquals(path.toString(), "[Miami,f:222.0, Orlando,f:228.0, Sarasota,f:269.0, Naples,f:289.0]");
    }
}

