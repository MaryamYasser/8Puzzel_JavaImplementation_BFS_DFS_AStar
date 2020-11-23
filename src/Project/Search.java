package Project;

import java.util.*;

public class Search {

    int zeroPosition;
    ArrayList<Integer> GoalTest;
    ArrayList<Integer> InitialState;
    Queue<ArrayList> visited;
   // Stack<ArrayList> pathToGoal = new Stack<>();
    int moves;
    double cost;
    int numberNodesExpanded=1;
    Integer[][] matrix = new Integer[3][3];

    //this search class has functions used by all three searches & variables


    public  Search(){
        GoalTest = new ArrayList<>(Arrays.asList(0,1,2,3,4,5,6,7,8));
        InitialState = new ArrayList<>(9);
        zeroPosition = -1;
        moves = -1;
        cost = -1;
        visited = new LinkedList<>();

    }



    //takes user input of initial numbers and returns it as arraylist of integers of "initial state"
    public ArrayList<Integer> insertT(){

         InitialState.clear();
         moves = -1;
        Scanner scan = new Scanner(System.in);
        System.out.println("Insert the Integers 0-8 in Random Order");
        Integer[] Array = new Integer[9];

        for (int i = 0;i<9;i++){
            int n = scan.nextInt();
            Array[i]=n;
            InitialState.add(n); }

        if(!checkSolvable(Array)){
            System.out.println("NOT SOLVABLE\n");
       System.exit(0);}


        return InitialState;
    }

    public void Update(ArrayList<Integer> currentState){
        cost++;
        moves++;
        visited.add(currentState);
        printMatrix(currentState,moves);
    }

     //prints number of moves,nodes expanded,cost,and depth
    public void Success(int noOfMoves,int Gn,double cost,int depth){
        System.out.println("Success! Total Number of Moves: "+noOfMoves);
        System.out.println("Path To Goal: "+Gn);
        System.out.println("Success! Number of Total Nodes Expanded: "+(noOfMoves+1));
        System.out.println("Cost of Path:"+cost);
        System.out.println("Depth: "+depth);
    }


    public void printFinalPath(Step FinalStep,Stack<ArrayList> pathToGoal){

        Step printStep = FinalStep;

        pathToGoal.push(printStep.currentState);

      while(printStep.previousStep!=null){
          pathToGoal.push(printStep.previousStep.currentState);
          printStep = printStep.previousStep;}

          System.out.println("Final Path Only:\n");
      int n = pathToGoal.size();
      for(int i = 0;i<n ;i++)
                printMatrix(pathToGoal.pop(),i);
        }


    public void printFinalCostlyPath(CostlyStep FinalStep,Stack<ArrayList> pathToGoal){

        CostlyStep printStep = FinalStep;

        pathToGoal.push(printStep.currentState);

        while(printStep.previousStep!=null){
            pathToGoal.push(printStep.previousStep.currentState);
            printStep = printStep.previousStep;}

        System.out.println("Final Path Only:\n");
        int n = pathToGoal.size();
        for(int i = 0;i<n ;i++)
            printMatrix(pathToGoal.pop(),i);
    }











     //checks if initial input is solvable
    public boolean checkSolvable(Integer[] board){
        int inversions = 0;
        for(int i = 0; i < board.length-1; ++i) {
            for(int j = i+1; j < board.length; ++j) {
                //Make sure not to count the 0 tile
                if(board[i] != 0 && board[j] != 0 && board[i] > board[j])
                    inversions++;
            }
        }
        return inversions%2 == 0;
    }


    //takes user input and returns which heuristic type was selected
    public String returnHeuristicType(){
        System.out.println("Choose Heuristic Type: (1) Manhattan / (2) Euclidean ");
        Scanner scan = new Scanner(System.in);
        //scan.useDelimiter("\n");
        int n = scan.nextInt();
        if(n!=1 && n!=2)
            System.out.println("Only Type (1) for Manhattan or (2) for Euclidean Please");
        if(n==1)
            return "M";


            return "E";
    }


    //prints state as a matrix
    public void printMatrix(ArrayList<Integer> state,int movements) {

        System.out.println("Your Current State is : "+movements);
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = state.get(index);
                index++;
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

}
