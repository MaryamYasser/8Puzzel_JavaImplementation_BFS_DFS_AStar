package Project;

import java.util.*;

public class DepthFirstSearch {
    Stack<Step> frontier;
    Queue<ArrayList> visited = new LinkedList<>();
    Search search = new Search();
    Step currentStateDFS;


    public boolean DepthFirstSearching (){

        //initializing our frontier stack and clearing up variables
        frontier = new Stack<>();
        //search=new Search();
        visited.clear();


        frontier.push(new Step(search.insertT()));

        while (!frontier.empty()){

            currentStateDFS=frontier.pop();
            long start = System.nanoTime();


            //updating move count,printing current state, and adding to visited queue our current state
            search.moves++;
            visited.add(currentStateDFS.currentState);
            search.printMatrix(currentStateDFS.currentState,search.moves);


            if (currentStateDFS.currentState.equals(search.GoalTest)){


                //prints Moves,Nodes Expanded,Cost,Depth and Time
                search.Success(search.moves,currentStateDFS.Gn,search.moves,currentStateDFS.depth);
                long elapsed = System.nanoTime() - start;
                System.out.println("Elapsed Time in Milliseconds = " + elapsed/1e+6);
                //search.printFinalPath(currentStateDFS,currentStateDFS.pathToGoal);

                return true;
            }



            //this adds a new "State" to the direction it can find, adds to to its depth,cost,
            //and updates its tiles
            //the adding function is in the opposite direction from BFS

            addSuccessor(currentStateDFS.moveDown());
            addSuccessor(currentStateDFS.moveRight());
            addSuccessor(currentStateDFS.moveLeft());
            addSuccessor(currentStateDFS.moveUP());

        }

        return false;


    }

    void addSuccessor(Step neighbour) {
        if (neighbour != null && !visited.contains(neighbour.currentState) && !frontier.contains(neighbour)){
            frontier.push(neighbour);
            search.numberNodesExpanded++;}
    }


}
