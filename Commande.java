package zork;
/**
 *  <p>
 *
 *  Cette classe fait partie du logiciel Zork, un jeu d'aventure simple en mode
 *  texte.</p> <p>
 *
 *  Cette classe répertorie les informations liées a une commande entrée par
 *  l'utilisateur. Une commande est constituée de deux chaines de caractères: un
 *  mot-clé de commande et un second mot apportant un complément (un paramètre)
 *  au mot-clé indiquant la commande a exécuter (par exemple, si la commande
 *  entrée par l'utilisateur est "prendre carte", alors les deux chaines de
 *  caractères sont "prendre" et "carte").</p> <p>
 *
 *  Les mots utilisés lors de l'initialisation d'une instance de cette classe
 *  sont supposés venir d'une commande utilisateur dont la validité a déjà été
 *  testée:
 *  <ul>
 *    <li> si le mot commande entré par l'utilisateur ne correspond pas a une
 *    commande valide, alors la valeur du mot commande donné a l'initialisation
 *    doit etre null</li>
 *    <li> si la commande entrée par l'utilisateur ne contient pas d'autre mot
 *    que le mot commande, alors la valeur du second mot donné a
 *    l'initialisation doit etre null</li>
 *  </ul>
 *  La validité du second mot n'est pas testée, sa valeur peut etre quelconque.
 *  </p>
 *
 * @author     Michael Kolling
 * @author     Marc Champesme (pour la traduction francaise)
 * @version    1.0
 * @since      July 1999
 */

public class Commande {
	private String motCommande;
	private String secondMot;


	/**
	 *  Initialise une Commande a partir des deux mots spécifiés. <p>
	 *
	 *  Le premier argument représente un mot commande, sa valeur peut etre null si
	 *  le mot commande ne correspond pas a une commande valide. Le second mot peut
	 *  également etre null dans le cas ou l'utilisateur n'aurait pas fourni de
	 *  second mot dans sa commande.</p>
	 *
	 * @param  motCommande  Le mot commande de la commande utilisateur ou null
	 * @param  secondMot    Le second mot de la commande utilisateur ou null
	 */
	public Commande(String motCommande, String secondMot) {
		this.motCommande = motCommande;
		this.secondMot = secondMot;
	}


	/**
	 *  Renvoie le mot commande (le premier mot) de cette Commande. Si cette
	 *  commande n'est pas une commande valide, la valeur renvoyée est null.
	 *
	 * @return    Le mot commande de cette Commande ou null
	 */
	public String getMotCommande() {
		return motCommande;
	}


	/**
	 *  Renvoie le second mot de cette Commande ou null si cette commande ne
	 *  possède pas de second mot.
	 *
	 * @return    le second mot de cette Commande ou null
	 */
	public String getSecondMot() {
		return secondMot;
	}


	/**
	 *  Teste si cette commande est une commande reconnue par le jeu.
	 *
	 * @return    true si cette commande est valide ; false sinon
	 */
	public boolean estInconnue() {
		return (motCommande == null);
	}


	/**
	 *  Teste si cette commande possède un second mot.
	 *
	 * @return    true si cette commande possède un second mot ; false sinon
	 */
	public boolean aSecondMot() {
		return (secondMot != null);
	}
}

