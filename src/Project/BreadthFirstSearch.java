package Project;

import java.util.*;

public class BreadthFirstSearch {
    Queue<Step> frontier;
    Queue<ArrayList> visited = new LinkedList<>();
    Search search;
    Step currentStateBFS;


    public boolean BreadthFirstSearching (){


        //initializing our frontier queue and clearing up variables
        frontier = new LinkedList<>();
        search=new Search();
        visited.clear();

         //adding to frontier our input
         frontier.add(new Step(search.insertT()));

          while (!frontier.isEmpty()){

              currentStateBFS=frontier.remove();
              long start = System.nanoTime();


              //updating move count,printing current state, and adding to visited queue our current state
              search.moves++;
              visited.add(currentStateBFS.currentState);
              search.printMatrix(currentStateBFS.currentState,search.moves);


              if (currentStateBFS.currentState.equals(search.GoalTest)){


                  //prints Moves,Nodes Expanded,Cost,Depth and Time
                  long elapsed = System.nanoTime() - start;
                  System.out.println("Elapsed Time in Milliseconds = " + elapsed/1e+6);
                  search.Success(search.moves,currentStateBFS.Gn,currentStateBFS.Gn,currentStateBFS.depth);
                 // System.out.println("Path To Goal Moves Count\n"+currentStateBFS.Gn);
                  search.printFinalPath(currentStateBFS,currentStateBFS.pathToGoal);


                  return true;
              }

              //this adds a new "State" to the direction it can find, adds to to its depth,cost,
              //and updates its tiles
              addSuccessor(currentStateBFS.moveUP());
              addSuccessor(currentStateBFS.moveDown());
              addSuccessor(currentStateBFS.moveLeft());
              addSuccessor(currentStateBFS.moveRight());

          }

          return false;
    }



    void addSuccessor(Step neighbour) {
        if (neighbour != null && !visited.contains(neighbour.currentState) &&!frontier.contains(neighbour)){
            frontier.add(neighbour);
            //search.numberNodesExpanded++;
        }

    }

}




