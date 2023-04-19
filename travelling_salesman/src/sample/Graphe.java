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
public class Graphe {
    
    private int nb_Sommets;
    private int mat_Adj[][];
    
        public Graphe(int n) {
        this.nb_Sommets = n;
        this.mat_Adj = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++)
            {
                if(i!=j)
                this.mat_Adj[i][j]=1;
            }
        }       
    }
        
    public void setArc(int i,int j, int val){
        this.mat_Adj[i][j]= val;
        this.mat_Adj[j][i]= val;
    }
     
    public int [][] get_matAdj ()
    {
        return this.mat_Adj ; 
    }
    
    public int get_NbSommets()
    {
        return this.nb_Sommets ;
    }
        
        
}
