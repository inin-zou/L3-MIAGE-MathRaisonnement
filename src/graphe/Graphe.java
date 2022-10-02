package graphe;

import java.util.ArrayList;


/**
 * Classe Graphe.
 */
public class Graphe
{
	/*------------*/
	/* Propriétés */
	/*------------*/

	/*----- Nom du graphe -----*/
	private String nom_graphe = null;

	/*----- Liste des noeuds du graphe -----*/
	private final ArrayList<Noeud> liste_noeud = new ArrayList<>();

	/*----- Type du graphe -----*/
	private final boolean oriente;
	private final boolean value;


	/*---------------*/
	/* Constructeurs */
	/*---------------*/

	public Graphe (String nom, boolean oriente, boolean value)
		{
		this.nom_graphe = nom;
		this.oriente = oriente;
		this.value = value;
		}


	/*----------*/
	/* Méthodes */
	/*----------*/

	/**
	 * Retourne le nom du graphe 'this'.
	 */
	public String getNom () { return this.nom_graphe; }


	/**
	 * Retourne le noeud de 'nom' s'il existe.
	 */
	public Noeud getNoeud (String nom)
		{
		int i = 0;
		while (i < this.liste_noeud.size() &&
			   !this.liste_noeud.get(i).getNom().equals(nom)) i++;

		if (i != this.liste_noeud.size())
			return this.liste_noeud.get(i);
		else
			return null;
		}


	/**
	 * Retourne le type du graphe.
	 */
	public boolean isGrapheOriente () { return this.oriente; }

	public boolean isGrapheValue () { return this.value; }


	/**
	 * Ajoute un noeud.
	 */
	public boolean addNoeud (Noeud n)
		{
		/*----- On teste si le noeud n'existe pas déjà dans le graphe -----*/
		if (this.liste_noeud.contains(n))
			return false;
		else
			{
			/*----- On ajoute le noeud -----*/
			this.liste_noeud.add(n);

			return true;
			}
		}

	public boolean addNoeud (String nom) { return this.addNoeud(new Noeud(nom)); }


	/**
	 * Ajoute une arête.
	 */
	public void addArete (String nom_a, String nom_b)
		{
		/*----- Existence des noeuds 'nom_a' et 'nom_b' -----*/
		Noeud n1 = this.getNoeud(nom_a);
		if (n1 == null)
			{
			n1 = new Noeud(nom_a);
			this.addNoeud(n1);
			}

		Noeud n2 = this.getNoeud(nom_b);
		if (n2 == null)
			{
			n2 = new Noeud(nom_b);
			this.addNoeud(n2);
			}

		/*----- Ajout de l'arête (similaire à 2 arcs) -----*/
		n1.addNoeudSuivant(n2);
		n2.addNoeudSuivant(n1);
		}

	public void addArete (String nom_a, String nom_b, double valeur)
		{
		/*----- Existence des noeuds 'nom_a' et 'nom_b' -----*/
		Noeud n1 = this.getNoeud(nom_a);
		if (n1 == null)
			{
			n1 = new Noeud(nom_a);
			this.addNoeud(n1);
			}

		Noeud n2 = this.getNoeud(nom_b);
		if (n2 == null)
			{
			n2 = new Noeud(nom_b);
			this.addNoeud(n2);
			}

		/*----- Ajout de l'arête (similaire à 2 arcs) -----*/
		n1.addNoeudSuivant(n2, valeur);
		n2.addNoeudSuivant(n1, valeur);
		}


	/**
	 * Ajoute une arc.
	 */
	public void addArc (String nom_a, String nom_b)
		{
		/*----- Existence des noeuds 'nom_a' et 'nom_b' -----*/
		Noeud n1 = this.getNoeud(nom_a);
		if (n1 == null)
			{
			n1 = new Noeud(nom_a);
			this.addNoeud(n1);
			}

		Noeud n2 = this.getNoeud(nom_b);
		if (n2 == null)
			{
			n2 = new Noeud(nom_b);
			this.addNoeud(n2);
			}

		/*----- Ajout de l'arc -----*/
		n1.addNoeudSuivant(n2);
		}


	public void addArc (String nom_a, String nom_b, double valeur)
		{
		/*----- Existence des noeuds de 'nom_a' et 'nom_b' -----*/
		Noeud n1 = this.getNoeud(nom_a);
		if (n1 == null)
			{
			n1 = new Noeud(nom_a);
			this.addNoeud(n1);
			}

		Noeud n2 = this.getNoeud(nom_b);
		if (n2 == null)
			{
			n2 = new Noeud(nom_b);
			this.addNoeud(n2);
			}

		/*----- Ajout de l'arc -----*/
		n1.addNoeudSuivant(n2,valeur);
		}


	/**
	 * Retourne une copie de la liste des noeuds du graphe.
	 */
	public ArrayList<Noeud> createCopieListeNoeuds ()
		{
		ArrayList<Noeud> l = new ArrayList<>();

		for (int i=0; i<this.liste_noeud.size(); i++)
			l.add(this.liste_noeud.get(i));

		return l;
		}


	/**
	 * Affiche le graphe 'this'.
	 */
	@Override
	public String toString()
		{
		StringBuilder sb = new StringBuilder("----- " + this.nom_graphe + "\n");

		/*----- Liste des noeuds -----*/
		sb.append("Noeuds : X {");
		for (int i=0; i<this.liste_noeud.size()-1; i++)
			sb.append(this.liste_noeud.get(i).getNom()).append(",");
		sb.append(this.liste_noeud.get(this.liste_noeud.size()-1).getNom()).append("} ").append(this.liste_noeud.size()).append("\n");

		if (this.isGrapheOriente())
			{
			/*----- Liste des arcs -----*/
			sb.append("Arcs   : U {");
			Noeud ni;
			int cpt = 0;
			for (int i=0; i<this.liste_noeud.size(); i++)
				{
				ni = this.liste_noeud.get(i);
				for (int j=0; j<ni.nbrSuivants(); j++)
					{
					sb.append("(").append(ni.getNom()).append(",").append(ni.getNoeudSuivant(j).getNom()).append((this.value ? ":" + ni.getValeur(j) : "")).append(")");
					cpt++;
					}
				}
			sb.append("} ").append(cpt);
			}
		else
			{
			/*----- Liste des arêtes -----*/
			sb.append("Arêtes : U {");
			Noeud ni, nj;
			int cpt = 0;
			for (int i=0; i<this.liste_noeud.size(); i++)
				{
				ni = this.liste_noeud.get(i);
				for (int j=i+1; j<this.liste_noeud.size(); j++)
					{
					nj = this.liste_noeud.get(j);
					if (ni.aLeSuivant(nj))
						{
						sb.append("(").append(ni.getNom()).append(",").append(nj.getNom()).append((this.value ? ":" + ni.getValeur(nj) : "")).append(")");
						cpt++;
						}
					}
				}
			sb.append("} ").append(cpt);
			}

		return sb.toString();
		}

} /*----- Fin de la classe Graphe -----*/
