package zork;

import java.util.ArrayList;

/**
 *  Un Aliment dans un jeu d'aventure. <p>
 *
 * un Aliment est considérer comme un objet,
 *
 * Lorsqu'un aliment est mangée elle disparait du jeu.
 * 
 * @invariant getValeurNutritive() > 0;
 * @invariant getNom() != null;
 * @invariant getTypeAliment() != null;
 * 
 * @author     BRAHIMI BILAL
 * @author     TABTI HABIB ABDRAOUF
 * @version    1.2
 * @since      Decembre 2019
 */

public interface Aliment {

	/** 
    * ronvoie la valeure nutritive de l'aliment.
    *
    * @return  type int.
    *
    */
	public int getValeurNutritive();

	/** 
    * ronvoie le nom de l'aliment.
    *
    * @return  Type String.
    *
    */
	public String getNom();


	/** 
    * ronvoie le type d'aliment.
    *
    * @return  TypeAliment.
    *
    */
	public TypeAliment getTypeAliment();

}