package Project;

import java.util.*;

public class AStarSearch {

    PriorityQueue<CostlyStep> frontier;
    Queue<CostlyStep> visited = new LinkedList<>();
    CostlyStep currentCostlyState;
    Search search = new Search();
    Double totalCost=0.0;
    ArrayList<Integer> initialState;

    public boolean AStarSearching (){

        //priority queue comparing between "priority" which is Fn = Hn + Gn
        frontier = new PriorityQueue<>((o1, o2)->o1.priority()-(o2.priority()));
        search=new Search();
        //reading input
        initialState = search.insertT();


         //frontier adding initial step with input as currentState (constructor calculates the heuristics & initial cost)
        frontier.add(new CostlyStep(initialState,search.returnHeuristicType()));


        while (!frontier.isEmpty()){

            long start = System.nanoTime();

           currentCostlyState=frontier.remove();

           //calculating overall cost
           totalCost+=currentCostlyState.getFn();



           visited.add(currentCostlyState);
            search.moves++;
            search.printMatrix(currentCostlyState.currentState,search.moves);



            if (currentCostlyState.currentState.equals(search.GoalTest)){

                 //prints Moves,Nodes Expanded,Cost,Depth and Time
                search.Success(search.moves,currentCostlyState.Gn,totalCost,currentCostlyState.depth);
                long elapsed = System.nanoTime() - start;
                System.out.println("Elapsed Time in Milliseconds = " + elapsed/1e+6);
                search.printFinalCostlyPath(currentCostlyState,currentCostlyState.pathToGoal);

                return true;
            }


            //this adds a new "Costly State" to the direction it can find, adds to to its depth,cost,
            //and updates its tiles
            addSuccessor(currentCostlyState.moveUp());
            addSuccessor(currentCostlyState.moveLeft());
            addSuccessor(currentCostlyState.moveRight());
            addSuccessor(currentCostlyState.moveDown());
        }

        return false; }


    //checks in that new costly state is not null (couldn't move),and that it isn't already visited
    void addSuccessor(CostlyStep neighbour) {
        if (neighbour != null && !visited.contains(neighbour) && !frontier.contains(neighbour)){
            frontier.add(neighbour);
        search.numberNodesExpanded++;}
    }
}

