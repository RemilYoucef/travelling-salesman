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
public class SolutionExpo {
    
    public Chemin cycle_opt ;
    public int cout_opt ;
    
    public SolutionExpo (int nb_Sommets)
    {
        cycle_opt = new Chemin (nb_Sommets) ;
        cout_opt = 10000 ;
    }
    
    void bfs (Graphe graphe , int depart)
    {    
        int n = graphe.get_NbSommets();
        Chemin nouv ;
        Chemin ch = new Chemin (n) ;
        
        ch.set_nsChemin(1);
        ch.set_chemin(0, depart);
        ch.set_cout(0);
        
        FileChemin File = new FileChemin () ; // Creer la file 
        File.enfiler(ch);
        while (!File.vide())
        {
            ch = File.defiler();
            if (ch.get_nsChemin() == n)
            {
                ch.set_nsChemin(n+1);
                ch.set_chemin(n, depart);
                ch.set_cout(ch.get_cout()+graphe.get_matAdj()[ch.get_sommmetChemin(n-1)][depart]);
                
                if (ch.get_cout() < this.cout_opt)
                {
                    this.cout_opt = ch.get_cout() ;
                    for (int i = 0 ; i <= n ; i++)
                    {
                        cycle_opt.set_chemin(i,ch.get_sommmetChemin(i));
                        cycle_opt.set_nsChemin(n+1);
                        cycle_opt.set_cout(ch.get_cout());
                    }
                }
            }
            
            else 
            {
                int dernier = ch.get_sommmetChemin(ch.get_nsChemin()-1); // dernier sommet du chemin actuel 
                for (int s = 0 ; s < n  ; s++)
                {
                    if(!ExisteChemin(s,ch))
                    {
                        nouv = new Chemin (n);
                        for (int i = 0 ; i < ch.get_nsChemin() ; i++)
                        {
                            nouv.set_chemin(i,ch.get_sommmetChemin(i));
                        }
                        nouv.set_chemin(ch.get_nsChemin(),s);
                        nouv.set_nsChemin(ch.get_nsChemin()+1);
                        nouv.set_cout(ch.get_cout()+ graphe.get_matAdj()[dernier][s]);
                        File.enfiler(nouv);
                    }
                }   
            }
        }
    }
    
    boolean ExisteChemin( int j, Chemin ch)
    {
        boolean trouv = false ;
        for (int i=0 ; i < ch.get_nsChemin() && !trouv ; i++)
            if ( j == ch.get_sommmetChemin(i) ) trouv = true ;
        return trouv;
    }
}
