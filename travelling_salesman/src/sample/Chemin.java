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
public class Chemin {
    
    private int ns_Chemin = 0 ;
    private int cout = 0;
    private int [] chem ; // tableau des sommets qui constituent le chemin
    
    public Chemin (int nb_sommets)
    {
        chem = new int [nb_sommets+1] ;
    }
    
    public int get_nsChemin ()
    {
        return this.ns_Chemin ;
    }
    
    public void set_nsChemin(int ns)
    {
        this.ns_Chemin = ns ;
    }
    
    public int get_cout ()
    {
        return this.cout ;
    }
    
    public void set_cout(int ns)
    {
        this.cout = ns ;
    }
    
    public int [] get_chem ()
    {
        return chem ;
    }
    
    public void set_chemin (int indice , int sommet)
    {
        this.chem[indice] = sommet ;
    }
    
    public int get_sommmetChemin(int indice)
    {
        return chem[indice];
    }
    
}
