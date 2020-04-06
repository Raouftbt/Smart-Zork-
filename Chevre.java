package zork;

import java.lang.Iterable;
import java.util.Iterator;

import java.util.ArrayList;

/**
 *  Une Chevre dans un jeu d'aventure. <p>
 *
 *  Cette classe Hérite de la classe abstraite ProieClassAb .
 *  un jeu d'aventure simple en mode texte.</p> <p>
 * 
 *  Une Chevre est caracteriser par son nom , son type  , la piece où se trouve , son poidMax et son capital de vie 
 *  et sa valeur nutritive.
 *
 * @author     Tabti Habib abdeRaouf
 * @version    1.2
 * @since      Decembre 2019
 */

public class Chevre extends ProieClassAb{
    public TypeChevre type ; 

    /**
     * inisialiser une chevre dont le nom et la pieceCourante et son poidMax et son capital de vie 
     * et sa valeur nutritive sont specifier par les paramétres.
	 * 
	 * @param pieceCourante la piece où se trouve l'animal.
	 * @param poidMax son poids maximum qu'il peut prndre avec lui.
	 * @param nom son nom.
	 * @param capitalVie son capitale de vie.
     * @param valeurN sa valeur nutritive.
     * @param type son type . 
     * @requires poidMax>0;
     * @requires nom != null;
     * @requires pieceCourante != null;
     * @requires capitaleVie >0;
     * @requires valeurN >0
     * @requires type !=null ;
	 */
    public Chevre(Piece pieceCourante,int poidMax,String nom,int capitalVie,int valeurN, TypeChevre type){
        super( pieceCourante, poidMax, nom,capitalVie,valeurN);
        this.type=type;

    }

    /**
     * Renvoie le type de la chevre.
     * 
     * @return type,
     * 
     * @pure
     */
    public TypeChevre getRace(){

        return this.type;
    }

    public String getRaceByNom() {
		String a="";
		if(this.getRace().equals(TypeChevre.Alpine)){
			a="Alpine";
			
		}else if(this.getRace().equals(TypeChevre.Corse)){
			a="Corse";
			
		}else if(this.getRace().equals(TypeChevre.Rove)){
			a="Rove";
			
		}else if(this.getRace().equals(TypeChevre.Saanen)){
			a="Saanen";
		
		}
		return a;
	}

    /**
	 * retourne un itérateur sur l’ensembles des éléments : un itérateur est un objet qui implémente l’interface Iterator, 
     * et qui a pour but de permettre le parcours des éléments d’un conteneur <Aliment>,
     * sans avoir besoin de savoir de quelle nature est ce conteneur <Aliment>.
     * 
     */    
    public Iterator<Aliment> iterator() {        
        Iterator<Aliment> iprof = this.getAliments().iterator();
        return iprof; 
    }
}