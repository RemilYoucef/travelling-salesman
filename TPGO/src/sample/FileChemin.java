/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import java.util.*;

/**
 *
 * @author Youcef
 */
public class FileChemin {
    
    List<Chemin> f = new ArrayList<>();
    
    public void enfiler(Chemin chem)
    {
        f.add(chem);
    }
    
    public Chemin defiler()
    {    
        Chemin chem = f.get(0);
        f.remove(0);
        return chem ;
    }
    
    public boolean vide()
    {
        return f.size() == 0 ;
    }
    
}
