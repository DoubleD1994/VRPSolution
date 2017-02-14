
package vrpcoursework;


public class VRPCourseWork 
{   
    public static void main(String[] args) throws Exception
    {
        
        
        VRProblem p = new VRProblem("problems/rand00090prob.csv");
        //Load problem
        
        VRSolution s = new VRSolution(p);
        //create blank solution
        
        s.runSolution();
        //Use the ClarkWright problem solver to build a solution
        
        System.out.println("Cost = " + s.solnCost());
        //Print out the cost of the solution
        
        s.writeOut("MySolution.csv");
        //Save the solution file for verification
        
        s.writeSVG("rand00090prob.svg", "MyPictureSolution.svg");
        //Create pictures of the problem and the solution
        
        //////////////////////////////////////////////////////////////
        
        s.verify();
        
        //Calculate the time taken to solve
        double startTime = System.currentTimeMillis();
        //Time started
        
        s.runSolution();
        //Use the existing problem solver to build a solution
        
        double endTime = System.currentTimeMillis();
        //Time finished
        
        System.out.println("The time taken was " + (endTime - startTime));
    } 
}
