package zork;

import java.util.ArrayList;

/**
 * Un Aliment dans un jeu d'aventure. <p>
 *
 * Cette classe Hérite de la classe abstraite AlimentClassAb et implimente l'interface Aliment,
 * et elle fait partie du logiciel Zork,un jeu d'aventure simple en mode texte.
 * 
 * un Aliment est considérer comme un objet,
 *
 * Lorsqu'un aliment est mangée elle disparait du jeu.
 * 
 *L'animal qui mange l'aliment gagne sa valeure nutritive. 
 *
 * @invariant getValeurNutritive() > 0;
 * @invariant getNom() != null;
 * @invariant getTypeAliment() != null;
 * 
 * @author     Brahimi Bilal
 * @author     Tabti Habib abdeRaouf 
 * @version    1.2
 * @since      Decembre 2019
 */
public abstract class AlimentClassAb extends Objet implements Aliment{

   
    private int valeurNutritive;
    private TypeAliment tAliment;


    /**
     * inisialiser un Aliment dont le nom et et son poids et  
     * et sa valeur nutritive sont specifier par les paramétres.
	 *
	 * @param p son poids.
	 * @param n son nom.
     * @param valeurN sa valeur nutritive.
     * 
     * @requires p>0;
     * @requires n != null;
     * @requires valeurN >0
     * 
	 */
    public AlimentClassAb( String n,int p,int valeurN){

        super(n,p);
        this.valeurNutritive=valeurN;
    }

    /** 
    * ronvoie le type d'aliment.
    *
    * @return  TypeAliment.
    *
    */
    public TypeAliment getTypeAliment(){
        return this.tAliment;
    }


    /** 
    * modifier le type d'aliment
    *
    * @param t type d'aliment  
    *
    * @requires t!=null
    */
    public void setTypeAliment(TypeAliment t){
        this.tAliment = t;
    }

    /** 
    * ronvoie la valeure nutritive de l'aliment.
    *
    * @return  type int.
    *
    */
    public int getValeurNutritive(){
        return this.valeurNutritive;
    }

    /** 
    * ronvoie le nom de l'aliment.
    *
    * @return  Type String.
    *
    */
    public String getNom(){
        return super.getNom();
    }
}
