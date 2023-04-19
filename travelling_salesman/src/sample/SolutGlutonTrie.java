/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import sample.Chemin;
import sample.FileChemin;
import sample.Graphe;

import java.util.Arrays;

/**
 *
 * @author Youcef
 */
public class SolutGlutonTrie {
    
    Chemin cycle_opt ;
    
    public SolutGlutonTrie (int nb_Sommets)
    {
        cycle_opt = new Chemin (nb_Sommets) ;
    }
    
    public void GlutonTrie (Graphe g, int depart)
    {
       int n = g.get_NbSommets() ;
       int nb_aretes = n*(n-1)/2 ;
       
       Arete [] aretes = new Arete [nb_aretes] ; // Ensembles des aretes totales 
       
       int cpt = 0 ;
       for (int i= 0 ; i < n-1 ; i++)
       {
           for (int j = i+1 ; j < n ; j++)
           {
               aretes[cpt] = new Arete(i,j,g.get_matAdj()[i][j]);
               cpt ++ ;
           }
       }
       Arrays.sort(aretes); // trier les aretes 
       
       int tmp = 0 ; // nombre d'aretes selectionnées 
       int i = 0 ;  // iterateur 
       int [] degree = new int [n] ;
       int [][] grapheReduit = new int [n][n] ; // matrice qui va contenir que les aretes minimales 
       
        while (i < nb_aretes && tmp < n)
        {              
            grapheReduit[aretes[i].source][aretes[i].destination] = aretes[i].get_cout() ;
            grapheReduit[aretes[i].destination][aretes[i].source] = aretes[i].get_cout() ;
            
            if (!existeCycle(grapheReduit,n)&& degree[aretes[i].source] < 2 && degree[aretes[i].destination] < 2) 
            {
                tmp ++ ;
                degree[aretes[i].source]++ ;
                degree[aretes[i].destination]++;              
            }
            else 
            {
                grapheReduit[aretes[i].source][aretes[i].destination] = 0 ;
                grapheReduit[aretes[i].destination][aretes[i].source] = 0 ;
            }   
            i ++ ;        
        }
        
        cycle_opt.set_chemin(0, depart);
        cycle_opt.set_nsChemin(1);
        boolean [] visite = new boolean [n] ;
        Arrays.fill(visite,false); 
        visite[depart]= true ;
        int j = depart ;
        for(int l = 1 ; l < n ; l++)
        {
            boolean trouve = false ;
            int s = 0 ;
            while (!trouve && s < n)
            {
                if(grapheReduit[j][s] != 0 && !visite[s])
                {
                    trouve = true ;
                }
                else s++ ;
            }
            visite[s] = true ;
            cycle_opt.set_chemin(l, s);
            cycle_opt.set_nsChemin(l+1);
            cycle_opt.set_cout(cycle_opt.get_cout() + grapheReduit[j][s]);
            j = s ;
        }
        cycle_opt.set_chemin(n,depart);
        cycle_opt.set_nsChemin(n+1);
        cycle_opt.set_cout(cycle_opt.get_cout()+ grapheReduit[j][depart]);               
    }
    
    public boolean existeCycle (int [][] mat , int n)
    {
        FileChemin file = new FileChemin ();
        int i = 0 ;
        boolean existe = false ;
        
        while((i < n) && (!existe)) // Faire pour tout sommet
        {
            Chemin ch = new Chemin(n);
            ch.set_chemin(0, i);
            ch.set_nsChemin(1);
            file.enfiler(ch);
            
            while (!file.vide()&& !existe)
            {
                ch = file.defiler();
 
                if (ch.get_nsChemin() >= 2 && ch.get_nsChemin() < n)
                {
                    int dernier = ch.get_sommmetChemin(ch.get_nsChemin()-1);
                    int s = 0 ;
                    while (s < n && !existe)
                    {
                        if (mat[dernier][s] != 0 && ExisteChemin(s,ch)) 
                        {
                            existe = true ;
                        }
                        else if (mat[dernier][s] != 0 && s != ch.get_sommmetChemin(ch.get_nsChemin()-2))
                        {
                            Chemin nouv = new Chemin(n);
                            for (int j = 0 ; j < ch.get_nsChemin() ; j++)
                            {
                                nouv.set_chemin(j,ch.get_sommmetChemin(j));
                            }
                            nouv.set_chemin(ch.get_nsChemin(),s);
                            nouv.set_nsChemin(ch.get_nsChemin()+1);
                            file.enfiler(nouv);
                        }
                        s ++ ;
                    }
                            
                }
                else if (ch.get_nsChemin() == 1)
                {
                    Chemin nouv = new Chemin(n);
                    nouv.set_chemin(0, i);
                    for(int j = 0 ; j < n  ; j++)
                    {
                        if (mat[i][j] != 0)
                        {
                            nouv.set_chemin(1, j);
                            nouv.set_nsChemin(2);
                            file.enfiler(nouv);                         
                        }
                    }
                }                                   
            }
            i ++ ;
        }
        return existe ;
        
    }
    
    boolean ExisteChemin( int j, Chemin ch)
    {
        boolean trouv = false ;
        for (int i=0 ; i < ch.get_nsChemin()-2 && !trouv ; i++)
            if ( j == ch.get_sommmetChemin(i) ) trouv = true ;
        return trouv;
    }
    
    class Arete implements Comparable <Arete>
    {
        private int source ; 
        private int destination ; 
        private int cout ;
        
        public Arete (int s, int d , int val)
        {
            this.source = s ;
            this.destination = d ;
            this.cout = val ;
        }
        
        public int get_source ()
        {
            return this.source ; 
        }
        
        public int get_destination ()
        {
            return this.destination ; 
        }
        
        public int get_cout ()
        {
            return this.cout ; 
        }   

        public int compareTo(Arete a) {
            return this.cout - a.get_cout() ;
        }
    }
    
}
