
package vrpcoursework;

import java.util.ArrayList;


public class Route 
{
    ArrayList<Customer> al = new ArrayList<>();
    
    public void add(Customer c)
    {
        al.add(c);
    }
    
    public Customer lastDelivery()
    {
        return al.get(al.size()-1);
    }
    
    public Customer first()
    {
        return al.get(0);
    }
    
    public void addToStart(Customer c)
    {
        al.add(0, c);
    }
    
    public boolean hasCapacity(int customerCapacity, int truckCapacity)
    {
        int currentRouteCapacity = 0;
        
        for(Customer c: al)
        {
            currentRouteCapacity = currentRouteCapacity + c.c;
        }
        
        return currentRouteCapacity + customerCapacity <= truckCapacity;
        
    }
    
    public int goods()
    {
        int goods = 0;
        for (Customer c : al) 
        {
            goods = goods + c.c;
        }
        return goods;
    }
    
    public void merge(Route r)
    {
        al.addAll(r.al);
    }

    
}
