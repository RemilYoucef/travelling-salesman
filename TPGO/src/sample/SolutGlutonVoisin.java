/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.util.Arrays;

/**
 *
 * @author Youcef
 */
public class SolutGlutonVoisin {
    
    public Chemin cycle_opt ;
         
    public SolutGlutonVoisin (int nb_Sommets)
    {
        cycle_opt = new Chemin (nb_Sommets) ;
    }
    
    public void GlutonVoisin (Graphe g, int depart)
    {
        int n = g.get_NbSommets() ;
        
        boolean existe [] = new boolean [n] ;        
        Arrays.fill(existe, false); 
        existe[depart] = true ;
        
        cycle_opt.set_chemin(0, depart);
        cycle_opt.set_nsChemin(1);
        
        int j = depart ;  
        int s = depart  ; // indice sommet prochain
        for (int i = 1 ; i < n ; i++) // on fait n-1 itérations car le dernier sommet sera lié au sommet départ
        {    
            int k = 0 ;
            while (existe[k]) k++ ;
            int inter = g.get_matAdj()[j][k];
            s = k ;
            k ++ ;
            while (k < n)
            {
                if ((!existe[k]) && (g.get_matAdj()[j][k] < inter))
                {                  
                    s = k ;
                    inter = g.get_matAdj()[j][k] ;
                }
                k ++ ;
            }  
            j = s ;
            existe[s] = true ;
            cycle_opt.set_chemin(i,s);
            cycle_opt.set_cout(cycle_opt.get_cout()+inter);
        }
        
        // le dernier sommet du cycle = depart 
        
        cycle_opt.set_chemin(n,depart);
        cycle_opt.set_nsChemin(n+1);
        cycle_opt.set_cout(cycle_opt.get_cout()+ g.get_matAdj()[s][depart]);

    }
    
    
    
}
