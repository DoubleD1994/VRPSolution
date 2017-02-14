
package vrpcoursework;


public class SavingsNode  implements Comparable<SavingsNode>
{
    Customer to;
    Customer from;
    double savings;
    
    public SavingsNode(Customer from, Customer to, double saving)
    {
        this.from = from;
        this.to = to;
        this.savings = saving;
    }
    
    public double getSavings()
    {
        return savings;
    }
    
    public int compareTo(SavingsNode sn) 
    {
            return Double.compare(sn.getSavings(), this.savings);
    }
}
