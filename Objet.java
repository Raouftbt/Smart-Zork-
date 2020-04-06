package zork;
/**
 *  Un objet dans un jeu d'aventure. <p>
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> <p>
 * 
 *  Un objet dans ce jeu est caracterisé par un nom et le poids,aussi si on peut 
 *  le transporter .</p>
 *
 * @author     Brahimi Bilal
 * @author     Tabti habib abedraouf
 * @version    1.2
 * @since      Octobre 2019
 */
public class Objet{
    
    private int poids;
    private String nom;
    /**
    *inisialiser un objet dont le nom et le poids
    *sont specifier par les paramétres.
    @param poids le poids de l'objet
    @param nom le nom de l'objet
    @requires poids>=0;
    @requires nom!=null;
    @ensures getPoids()==poids;
    @ensures getNom()==poids;
    @ensures equals(objet);
    @*/
    public Objet( String n,int p){
        this.poids=p;
        this.nom=n;
    }



    /** 
    *ronvoie le poids de l'objet(i.e 1 kg)
    @return   Le poids de l'objet
    */
    public int getPoids(){
        return this.poids;
    }


    /** 
    *ronvoie le nom de l'objet(i.e chaise,tableau..)
    @return   Le nom de l'objet
    */
    public String getNom(){
        return this.nom;
    }

    /** 
    *ronvoie true si les deux objet sont identique ,false sinon
    @return   true ou false
    */
    public boolean equals(Objet obj){
        return (obj.getNom().equals(this.getNom()) && obj.getPoids()==this.getPoids());
    } 
}