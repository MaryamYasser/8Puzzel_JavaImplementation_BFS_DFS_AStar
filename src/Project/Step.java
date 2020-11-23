package Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

public class Step {

    // step is our "node" for the uninformed searches(BFS & DFS),
    // it initializes its "root" with our initial array


    public ArrayList<Integer> currentState;
    int Gn;
    int zeroPosition;
    Step previousStep;
    //Step nextStep;
     int depth;
    Stack<ArrayList> pathToGoal = new Stack<>();


    public Step(ArrayList<Integer> state){
        this.currentState = state;
        this.zeroPosition = this.ZeroPosition(this.currentState);
        Gn = 0;
        previousStep = null;
        depth = 0;
        //nextStep = null;
        //pathToGoal.add(this);
    }


    //this constructor is used for when we get neighbours, it takes a "Step" and a new zero
    //position, and gets neighbour if it can and calculates its new cost,depth
    Step(Step prev, int newZeroPosition){
        this.currentState =  (ArrayList<Integer>)prev.currentState.clone();
        Collections.swap(this.currentState,prev.zeroPosition,newZeroPosition);
        this.zeroPosition = newZeroPosition;
        Gn = prev.Gn + 1;
//this.previousStep.nextStep = this;
        this.previousStep = prev;
        depth = prev.depth +1;
        //pathToGoal.add(this);
    }

    public Step() { }

    public double getFn(){
        return this.Gn;
    }


    //function to get zeroPosition in both step & costly step
    public int ZeroPosition (ArrayList<Integer> list){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0)
                return i;
        }
        return -1;
    }

    //functions to move when it can according to where our Zero position is, returns a new step with a new state
    Step moveUP() {
        return zeroPosition > 2 ? new Step(this, zeroPosition - 3) : null; }
    Step moveDown() {
        return zeroPosition < 6 ? new Step(this, zeroPosition + 3) : null; }
    Step moveLeft() {
        return zeroPosition % 3 > 0 ? new Step(this, zeroPosition - 1) : null; }
    Step moveRight() {
        return zeroPosition % 3 < 2 ? new Step(this, zeroPosition + 1) : null; }
}
