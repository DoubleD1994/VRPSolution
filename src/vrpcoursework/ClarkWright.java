
package vrpcoursework;

import java.util.*;

public class ClarkWright
{
    public static ArrayList<List<Customer>> solver(ArrayList<Customer> customers,
                                                    int maxCustomers,
                                                    Customer depot)
    {
        ArrayList<List<Customer>> solution = new ArrayList<List<Customer>>();		
        ArrayList<SavingsNode> savings = new ArrayList<SavingsNode>();
        ArrayList<Customer> inRoutes = new ArrayList<>();
        ArrayList<Route> routes = new ArrayList<>();
        //Calculate savings
        
        int capacity = depot.c;
        
        for(int i = 0; i<maxCustomers; i++)
        {
            for(int j=0; j < maxCustomers; j++)
            {
                Customer ci = customers.get(i);
                Customer cj = customers.get(j);
                if(i!=j)
                {
                    double saving = (depot.distance(ci) + depot.distance(cj) - ci.distance(cj));
                    savings.add(new SavingsNode(ci, cj, saving));
                }
            }
        }
        Collections.sort(savings);

        
        for(SavingsNode sn:savings)
        {
            //Neither customers are in a route already
            if(!(inRoutes.contains(sn.from) || inRoutes.contains(sn.to)))
            {
                if(sn.from.c + sn.to.c <= capacity)
                {
                    Route newR = new Route();
                    newR.add(sn.from);
                    newR.add(sn.to);
                    
                    //add the new route
                    routes.add(newR);
                    inRoutes.add(sn.from);
                    inRoutes.add((sn.to));
                }
            }
            else
            {     
                //Find a route that ends at 'from'
                if(!(inRoutes.contains(sn.to)))
                {
                    for(Route route: routes)
                    {
                        if(route.lastDelivery().equals(sn.from))
                        {
                            if(route.hasCapacity(sn.to.c, capacity))
                            {
                                route.add(sn.to);
                                inRoutes.add(sn.to);
                                break;
                            }
                        }
                    }
                }
                //Find a route that starts at 'to'
                if(!(inRoutes.contains(sn.from)))
                {
                    
                    for(Route route: routes)
                    {
                        if(route.lastDelivery().equals(sn.to))
                        {
                            if(route.hasCapacity(sn.from.c, capacity))
                            {
                                route.addToStart(sn.from);
                                inRoutes.add(sn.from);
                                break;
                            }
                        }
                    }
                }
                //Check for the case of two routes that can be merged
                Route merged = null;
                for(Route routeX: routes)
                {
                    if(merged != null)break;
                    if(routeX.lastDelivery() == sn.from)
                    {
                        for(Route routeY: routes)
                        {                            
                            if(routeY.first() == sn.to)
                            {
                                if(routeX != routeY)
                                {
                                    if((routeX.goods() + routeY.goods())<=capacity)
                                    {
                                        routeX.merge(routeY);
                                        merged = routeY;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                if(merged!=null)
                {
                    routes.remove(merged);
                }
               
            }
            
        }
        //now allocate any remaining customers to individual routes
        for(Customer c:customers)
        {
            if(!(inRoutes.contains(c)))
            {                
                Route r = new Route();
                r.add(c);
                routes.add(r);
                inRoutes.add(c);
            }
        }
        
        //output
		for(Route r:routes)
                {
			ArrayList<Customer> soln = new ArrayList<Customer>();
			soln.addAll(r.al);
			solution.add(soln);
		}
        
        return solution;
    }
    

}
