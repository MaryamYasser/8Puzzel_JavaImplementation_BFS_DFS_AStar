package Project;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    System.out.println("hello world");


        BreadthFirstSearch BFS = new BreadthFirstSearch();
        DepthFirstSearch DFS = new DepthFirstSearch();
        AStarSearch aStar = new AStarSearch();
        Scanner scanner = new Scanner(System.in);



        while(true){
        System.out.println("Choose Search Algorithm\n 1) BreadthFirstSearch \n 2) Depth First Search \n 3) A*");



        int n = scanner.nextInt();
                if (n == 1)
                    BFS.BreadthFirstSearching();
                if(n==2)
                    DFS.DepthFirstSearching();
                if(n==3)
                    aStar.AStarSearching();






    }


}}
