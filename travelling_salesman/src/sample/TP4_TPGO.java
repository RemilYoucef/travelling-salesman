/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

/**
 *
 * @author Youcef
 */
public class TP4_TPGO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int n = 6 ;
        Graphe g = new Graphe(6);
        
        // Remplir le graphe : 
        g.setArc(0, 1, 2); 
        g.setArc(0, 2, 5); 
        g.setArc(0, 3, 1); 
        g.setArc(0, 4, 4); 
        g.setArc(0, 5, 10);
        
        g.setArc(1, 2, 2); 
        g.setArc(1, 3, 2); 
        g.setArc(1, 4, 2); 
        g.setArc(1, 5, 7);
        
        g.setArc(2, 3, 1); 
        g.setArc(2, 4, 2); 
        g.setArc(2, 5, 5); 
        
        g.setArc(3, 4, 3);
        g.setArc(3, 5, 6);
        
        g.setArc(4, 5, 1); 
        
        SolutGlutonTrie solution = new SolutGlutonTrie(n);
        solution.GlutonTrie(g, 2);
        System.out.println(solution.cycle_opt.get_cout());
        for(int i = 0 ; i <= n ; i++)
        {
            System.out.println(solution.cycle_opt.get_sommmetChemin(i));
        }
        
               
    }
    
}
