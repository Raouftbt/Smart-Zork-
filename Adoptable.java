package zork;

import java.util.ArrayList;
/**
 * Animaux pouvrant être adoptés.
 * <p>
 * Un animal adoptable, peut faire l'objet de propositions pour ses
 * déplacements, lorsqu'il est adopté (et donc non libre) les choix effectués
 * par l'animal adopté pour ses déplacements sont conformes aux propositions qui
 * lui sont faites.
 * </p>
 * 
 * @invariant !estLibre() ==> (\forall Piece p; p != null; choisirPiece(p) == getSortieProposee(p));
 * 
 * @author Marc Champesme
 * @since 15/11/2019
 * @version 29/11/2019
 *
 */
public interface Adoptable extends Animal {

	/**
	 * Renvoie true si cet animal est libre.
	 * 
	 * @return true si cet animal est libre, false sinon.
	 * 
	 * @pure
	 */
	public boolean estLibre();

	/**
	 * Fait de cet animal un animal adopté.
	 * 
	 * @return true si cet animal était libre, false sinon.
	 * 
	 * @ensures !estLibre();
	 * @ensures \result <==> (estLibre() != \old(estLibre()));
	 */
	public boolean adoption();

	/**
	 * Libère cet animal.
	 * 
	 * @return true si cet animal était précédemment adopté, false sinon.
	 * 
	 * @ensures estLibre();
	 * @ensures \result <==> (estLibre() != \old(estLibre()));
	 */
	public boolean liberation();

	/**
	 * Propose ou impose la sortie spécifiée pour la pièce spécifiée. Lorsque cet
	 * animal est adopté cette sortie proposée sera la sortie choisie (par la
	 * méthode choisirSortie) pour le prochain déplacement de cet animal, si ce
	 * déplacement se fait à partir de cette pièce.
	 * 
	 * @param p la pièce pour laquelle une sortie est proposée
	 * @param d sortie proposée pour la pièce spécifiée
	 * 
	 * @throws NullPointerException si l'un des arguments spécifiés est null
	 * @throws IllegalArgumentException si la pièce spécifiée ne possède pas de 
	 * 			sortie dans la direction spécifiée
	 * 
	 * @requires p != null;
	 * @requires d != null;
	 * @requires p.pieceSuivante(d) != null;
	 * @ensures getSortieProposee(p) == d;
	 * 
	 */
	public void proposerSortie(Piece p, Direction d);

	/**
	 * Renvoie une sortie proposée pour la pièce spécifiée. La sortie renvoyée est
	 * celle proposée par le dernier appel à proposerSortie pour la pièce spécifiée,
	 * ou null si cet animal s'est déplacé (appel à la méthode deplacerDepuis)
	 * depuis le dernier appel à proposerSortie.
	 * 
	 * @param p pièce dont on veut connaitre la sortie proposée.
	 * @return sortie proposée pour la pièce spécifiée ou null si aucune sortie n'a
	 *         été proposée depuis le dernier déplacement.
	 * 
	 * @throws NullPointerException si l'un des arguments spécifiés est null
	 * 
	 * @requires p != null;
	 * @ensures (\result != null) ==> (p.pieceSuivante(\result) != null);
	 * @ensures !estLibre() ==> (\result == choisirSortie(p));
	 * 
	 * @pure
	 */
	public Direction getSortieProposee(Piece p);

	/**
	 * {@inheritDoc}
	 * <p>
	 * Lorsque cet animal est adopté, la sortie choisie doit impérativement être
	 * celle renvoyée par la méthode getSortieProposee, l'entitée ayant adopté cet
	 * animal doit donc imposer le choix en appelant la méthode proposerSortie avant
	 * d'effectuer un déplacement à l'aide de la méthode deplacerDepuis. Si cet
	 * animal est libre, il n'y a aucune obligation à ce que le choix effectué
	 * prenne en compte la proposition de sortie renvoyée par la méthode
	 * getSortieProposee.
	 * </p>
	 * 
	 * @requires p != null;
	 * @ensures !estLibre() ==> (\result == getSortieProposee(p));
	 * 
	 * @pure
	 */
	@Override
	public Direction choisirSortie(Piece p);

	/**
	 * {@inheritDoc}
	 * @param p la pièce à partir de laquelle a lieu le déplacement
	 * @return la nouvelle pièce dans laquelle se trouve cet animal ou null si le
	 *         déplacement n'a pas eu lieu.
	 * 
	 * @throws NullPointerException     si l'argument spécifié est null
	 * @throws IllegalStateException    si cet animal est mort
	 * @throws IllegalArgumentException si cet animal n'est pas présent dans la
	 *                                  pièce spécifiée avant appel de cette méthode
	 * 
	 * @requires p != null;
	 * @requires estVivant();
	 * @requires p.contientAnimal(this);
	 * @ensures getSortieProposee(p) == null;
	 */
	@Override
	public Piece deplacerDepuis(Piece p);

}