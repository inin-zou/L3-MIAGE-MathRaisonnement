package graphe;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

/**
 * Programme principal.
 */
public class AppGraph
{
	/*----------*/
	/* Méthodes */
	/*----------*/


	/*---------------------*/
	/* Programme principal */
	/*---------------------*/
	
	/*----- Parcours en largeur -----*/	
	public static void parcoursLargeur(Graphe g) {
		int nbNoeuds;
		Noeud noeudCourant, noeudRegarde;
		ArrayList<Noeud> marque, non_marque;
		LinkedList<Noeud> file;
		
		file = new LinkedList<Noeud>();
		marque = new ArrayList<Noeud>();
		non_marque = g.createCopieListeNoeuds();

		while (!non_marque.isEmpty()) {
			noeudCourant = non_marque.get(0);
			file.add(noeudCourant);
			
			non_marque.remove(noeudCourant);
			marque.add(noeudCourant);

			while (!file.isEmpty()) {
				// Supprimer le sommet x en tête de la file
				noeudCourant = file.poll();

				nbNoeuds = noeudCourant.nbrSuivants();
				for (int i = 0; i<nbNoeuds; i++) {
					noeudRegarde = noeudCourant.getNoeudSuivant(i);
					if (non_marque.contains(noeudRegarde)) {
						file.add(noeudRegarde);
						non_marque.remove(noeudRegarde);
						marque.add(noeudRegarde);
					}
				}
			}
		}
		System.out.println(marque);
	}
	
	/*----- Parcours en profondeur -----*/	
	public static void parcoursProfondeur(Graphe g) {
		int nbNoeuds;
		Noeud noeudCourant, noeudRegarde;
		ArrayList<Noeud> marque, non_marque;
		Stack<Noeud> pile;
		
		pile = new Stack<Noeud>();
		marque = new ArrayList<Noeud>();
		non_marque = g.createCopieListeNoeuds();
		
		while (!non_marque.isEmpty()) {
			noeudCourant = non_marque.get(0);
			pile.add(noeudCourant);
			
			non_marque.remove(noeudCourant);
			marque.add(noeudCourant);
			
			while (!pile.isEmpty()) {
				noeudCourant = pile.pop();
				nbNoeuds = noeudCourant.nbrSuivants();
				if (nbNoeuds > 0) {
					for (int i = 0; i<nbNoeuds; i++) {
						noeudRegarde = noeudCourant.getNoeudSuivant(i);
						if (non_marque.contains(noeudRegarde)) {
							pile.add(noeudRegarde);
							non_marque.remove(noeudRegarde);
							marque.add(noeudRegarde);
						}
					}
				}
				else {
					pile.remove(noeudCourant);
				}
			}
		}
		System.out.println(marque);
	}
	
	// rechercher les composantes connexes sur un graphe non orienté.
	public static void composantConnexe(Graphe g) {
		int nbNoeuds;
		int count = 0;
		Noeud noeudCourant, noeudRegarde;
		ArrayList<Noeud> marque, non_marque, compConx;
		Stack<Noeud> pile;
		
		pile = new Stack<Noeud>();
		marque = new ArrayList<Noeud>();
		compConx = new ArrayList<Noeud>();
		non_marque = g.createCopieListeNoeuds();
		
		while (!non_marque.isEmpty()) {
			noeudCourant = non_marque.get(0);
			pile.add(noeudCourant);
			
			non_marque.remove(noeudCourant);
			marque.add(noeudCourant);
			compConx.add(noeudCourant);
			
			while (!pile.isEmpty()) {
				noeudCourant = pile.pop();
				nbNoeuds = noeudCourant.nbrSuivants();
				if (nbNoeuds > 0) {
					for (int i = 0; i<nbNoeuds; i++) {
						noeudRegarde = noeudCourant.getNoeudSuivant(i);
						if (non_marque.contains(noeudRegarde)) {
							pile.add(noeudRegarde);
							non_marque.remove(noeudRegarde);
							marque.add(noeudRegarde);
							compConx.add(noeudRegarde);
						}
					}
				}
				else {
					pile.remove(noeudCourant);
				}
			}
			count += 1;
			System.out.println(compConx);
			compConx.clear();
		}
		System.out.println("Nombre de composantes connexes : " + count);
	}
	
	public static int ChoixNoeud(Graphe g) {
		int codeMode = 99;
		Scanner sc = new Scanner(System.in);
		System.out.println("Choix de Noeud dans le gu (options :" + g.createCopieListeNoeuds());
		String Mode = sc.nextLine().toLowerCase();
		while (codeMode == 99) {
			switch (Mode) {
			case "a" : codeMode = 0; break;
			case "b" : codeMode = 1; break;
			case "e" : codeMode = 2; break;
			case "f" : codeMode = 3; break;
			case "i" : codeMode = 4; break;
			case "h" : codeMode = 5; break;
			case "l" : codeMode = 6; break;
			case "c" : codeMode = 7; break;
			case "d" : codeMode = 8; break;
			case "j" : codeMode = 9; break;
			case "g" : codeMode = 10; break;
			case "k" : codeMode = 11; break;
			case "m" : codeMode = 12; break;
			case "n" : codeMode = 13; break;
			case "o" : codeMode = 14; break;
			default : {
				System.out.println("Veuillez saisir une mode valide.");
				System.out.println();
				sc = new Scanner(System.in);
				System.out.println("Choix de Noeud dans le gu (options :" + g.createCopieListeNoeuds());
				Mode = sc.nextLine().toLowerCase();
				break;
				}
			}
		}
		return codeMode;
	}

	// recherche de chemin
	public static void rechercheChemin(Graphe g) {
		int nbNoeuds;
		Noeud noeudCourant, noeudRegarde, noeudDebut, noeudFin;
		ArrayList<Noeud> marque, non_marque, Comp1, Comp2, Comp3;
		LinkedList<Noeud> file;
		
		int debut = ChoixNoeud(g);
		int fin = ChoixNoeud(g);
		
		file = new LinkedList<Noeud>();
		marque = new ArrayList<Noeud>();
		Comp1 = new ArrayList<Noeud>();
		Comp2 = new ArrayList<Noeud>();
		Comp3 = new ArrayList<Noeud>();
		
		non_marque = g.createCopieListeNoeuds();
		noeudDebut = non_marque.get(debut);
		noeudFin = non_marque.get(fin);
		Comp1.add(non_marque.get(0));
		Comp1.add(non_marque.get(1));
		Comp1.add(non_marque.get(2));
		Comp1.add(non_marque.get(3));
		Comp1.add(non_marque.get(4));
		Comp1.add(non_marque.get(5));
		Comp1.add(non_marque.get(6));
		Comp2.add(non_marque.get(7));
		Comp2.add(non_marque.get(8));
		Comp2.add(non_marque.get(9));
		Comp2.add(non_marque.get(10));
		Comp2.add(non_marque.get(11));
		Comp3.add(non_marque.get(12));
		Comp3.add(non_marque.get(13));
		Comp3.add(non_marque.get(14));
		
		if (Comp1.contains(noeudDebut) && !Comp1.contains(noeudFin)) {
			System.out.println("Les deux noeuds ne sont pas connexes.");
		}
		else if (Comp2.contains(noeudDebut) && !Comp2.contains(noeudFin)) {
			System.out.println("Les deux noeuds ne sont pas connexes.");
		}
		else if (Comp3.contains(noeudDebut) && !Comp3.contains(noeudFin)) {
			System.out.println("Les deux noeuds ne sont pas connexes.");
		}
		else {
			while (non_marque.contains(noeudFin)) {
			noeudCourant = noeudDebut;
			file.add(noeudCourant);
			
			non_marque.remove(noeudCourant);
			marque.add(noeudCourant);

				if (!file.isEmpty()) {
					// Supprimer le sommet x en tête de la file
					noeudCourant = file.poll();
					
					nbNoeuds = noeudCourant.nbrSuivants();
					for (int i = 0; i<nbNoeuds; i++) {
						noeudRegarde = noeudCourant.getNoeudSuivant(i);
						if (non_marque.contains(noeudRegarde)) {
							file.add(noeudRegarde);
							non_marque.remove(noeudRegarde);
							marque.add(noeudRegarde);
						}
					}
				}
				System.out.println(marque);
			}
		}
	}
	
	// rechercher si un graphe est acyclique et donner ses arêtes de retour
	public static boolean retour(Graphe g){
		ArrayList<Noeud> lstNodes = g.createCopieListeNoeuds();
		Map<Noeud, Boolean> mapVisited = new HashMap<Noeud, Boolean>();
		for (Noeud n : lstNodes){
			mapVisited.put(n, Boolean.FALSE);
		}
		ArrayDeque<Noeud> Nodes = new ArrayDeque<Noeud>();
		while (lstNodes.size() > 0){
			Nodes.add(lstNodes.get(0));
			boolean isCycle = true;
			int numArc = 0;
			int numNode = 0;
			ArrayDeque<Noeud> Visited = new ArrayDeque<Noeud>();

			while (Nodes.size() > 0){
				Noeud n = Nodes.poll();
				if (mapVisited.get(n) == Boolean.TRUE){
					continue;
				}
				
				++numNode;
				Visited.add(n);
				lstNodes.remove(n);
				mapVisited.put(n, Boolean.TRUE);

				int c = n.nbrSuivants();
				if (isCycle && (c > 2))
					isCycle = false;

				for (int i = c - 1; i >= 0; --i){
					Noeud nl = n.getNoeudSuivant(i);
					++ numArc;
					Nodes.addFirst(nl);
				}
			}
			if (isCycle && (numArc == (numNode << 1))){
				int pos = 0;
				for (Noeud n : Visited){
					if (pos > 0)
						System.out.print(" -> ");
					System.out.print(n.getNom());
					++ pos;
				}
				System.out.println();
				return true;
			}
		}
		return false;
	}
	
	// Algorithme de Dijkstra
	public static void DIJKSTRA(Graphe g, String debut){
		Noeud a = g.getNoeud(debut);
		Map<Noeud, Map<Noeud, Double> > maps2 = new HashMap<Noeud, Map<Noeud, Double> >();
		ArrayList<Noeud> Nodes = g.createCopieListeNoeuds();
		for (Noeud n : Nodes){
			Map<Noeud, Double> maps1 = new HashMap<Noeud, Double>();
			maps2.put(n, maps1);
			for(Noeud m : Nodes){
				maps1.put(m, Double.MAX_VALUE);
			}
			int c = n.nbrSuivants();
			for (int i=0; i<c; ++i){
				Noeud m = n.getNoeudSuivant(i);
				double dis = n.getValeur(i);
				maps1.put(m, dis);
			}
			maps1.put(n, 0.0);
		}

		ArrayList<Noeud> Visited = new ArrayList<Noeud>();
		Visited.add(a);
		Nodes.remove(a);
		Map<Noeud, Noeud> mapsArc = new HashMap<Noeud, Noeud>();

		while (Nodes.size() > 0){
			Noeud nMin = null;
			Noeud mMin = null;
			double disMin = Double.MAX_VALUE;
			for (Noeud n : Visited){
				Map<Noeud, Double> maps1 = maps2.get(n);
				for (Noeud m : Nodes){
					double dis = maps1.get(m);
					if (dis == Double.MAX_VALUE)
						continue;
					dis += maps2.get(a).get(n);
					if (dis < disMin){
						disMin = dis;
						nMin = n;
						mMin = m;
					}
				}
			}
			maps2.get(a).put(mMin, disMin);
			mapsArc.put(mMin, nMin);
			Visited.add(mMin);
			Nodes.remove(mMin);
		}

		for (Noeud n : Visited){
			double dis = maps2.get(a).get(n);
			System.out.print("de " + debut + " à " + n.getNom() + ": valeur: " + dis + ", chemin: ");
			ArrayDeque<Noeud> deqArc = new ArrayDeque<Noeud>();
			deqArc.addFirst(n);
			for(;;){
				Noeud m = mapsArc.get(n);
				if(m == null)
					break;
				deqArc.addFirst(m);
				n = m;
			}
			int pos = 0;
			for(Noeud arc : deqArc){
				if(pos > 0)
					System.out.print(" -> ");
				System.out.print(arc.getNom());
				++pos;
			}
			System.out.println();
		}
	}

	public static void main (String[] s)
		{
		/**
		 * AVERTISSEMENT :
		 * NE MODIFIER PAS LE CODE CI-DESSOUS PERMETTANT
		 * D'INITIALISER LES GRAPHES.
		 */
		/*----- Graphe non orienté -----*/
		Graphe gu = new Graphe("Graphe non orienté", false, false);

		/*----- Liste des arêtes -----*/
		gu.addArete("A","B");
		gu.addArete("A","E");
		gu.addArete("A","F");
		gu.addArete("B","F");
		gu.addArete("B","I");
		gu.addArete("F","H");
		gu.addArete("E","H");
		gu.addArete("H","I");
		gu.addArete("I","L");
		gu.addArete("C","D");
		gu.addArete("C","J");
		gu.addArete("C","G");
		gu.addArete("D","G");
		gu.addArete("D","K");
		gu.addArete("J","K");
		gu.addArete("M","N");
		gu.addArete("M","O");
		gu.addArete("N","O");

		System.out.println(gu);

		/*----- Graphe orienté -----*/
		Graphe gd = new Graphe("Graphe orienté", true, false);

		/*----- Liste des arcs -----*/
		gd.addArc("E","A");
		gd.addArc("E","C");
		gd.addArc("A","B");
		gd.addArc("A","F");
		gd.addArc("B","F");
		gd.addArc("B","G");
		gd.addArc("F","C");
		gd.addArc("C","G");
		gd.addArc("G","D");
		gd.addArc("M","N");
		gd.addArc("M","O");
		gd.addArc("N","O");
		gd.addArc("O","P");

		System.out.println(gd);

		/*----- Graphe valué non orienté -----*/
		Graphe guv = new Graphe("Graphe valué non orienté", false, true);

		guv.addArete("1","2",4);
		guv.addArete("1","3",7);
		guv.addArete("1","5",10);
		guv.addArete("2","4",5);
		guv.addArete("2","5",7);
		guv.addArete("3","5",2);
		guv.addArete("3","6",6);
		guv.addArete("4","5",3);
		guv.addArete("4","7",5);
		guv.addArete("4","8",7);
		guv.addArete("5","6",4);
		guv.addArete("5","7",8);
		guv.addArete("6","7",4);
		guv.addArete("6","9",2);
		guv.addArete("7","8",1);
		guv.addArete("7","9",5);
		guv.addArete("10","12",100);
		guv.addArete("10","11",1);
		guv.addArete("11","12",1);

		System.out.println(guv);

		/*----- Graphe valué orienté -----*/
		Graphe gdv = new Graphe("Graphe valué orienté", true, true);

		gdv.addArc("1","2",1);
		gdv.addArc("1","4",5);
		gdv.addArc("1","6",7);
		gdv.addArc("4","2",1);
		gdv.addArc("6","4",2);
		gdv.addArc("4","6",3);
		gdv.addArc("2","3",3);
		gdv.addArc("3","4",0);
		gdv.addArc("4","5",1);
		gdv.addArc("5","3",1);
		gdv.addArc("6","5",2);
		gdv.addArc("4","7",9);
		gdv.addArc("6","7",4);
		gdv.addArc("5","7",4);
		gdv.addArc("3","8",8);
		gdv.addArc("5","8",7);
		gdv.addArc("7","8",2);
		gdv.addArc("8","7",1);

		System.out.println(gdv);

		/**
		 * VOUS POUVEZ ECRIRE EN DESSOUS.
		 */
		
		/*----- Parcours en largeur -----*/
		System.out.println();
		System.out.println("Exercise 3.1 : parcourir un graphe en largeur");
		System.out.println("Pour ce graphe non-orienté :");
		parcoursLargeur(gu);
		System.out.println("Pour ce graphe orienté :");
		parcoursLargeur(gd);
		
		/*----- Parcours en profondeur -----*/
		System.out.println();
		System.out.println("Exercise 3.2 : parcourir un graphe en profondeur");
		System.out.println("Pour ce graphe non-orienté :");
		parcoursProfondeur(gu);
		System.out.println("Pour ce graphe orienté :");
		parcoursProfondeur(gd);
		
		// Ex 4.1
		System.out.println();
		System.out.println("Exercise 4.1 : rechercher les composantes connexes sur un graphe non orienté");
		composantConnexe(gu);
		
		// Ex 4.2
		System.out.println();
		System.out.println("Exercise 4.2 : rechercher s’il existe un chemin entre 2 nœuds donnés et afficher ce chemin");
		rechercheChemin(gu);
		
		
		// Ex 4.3
		System.out.println();
		System.out.println("Exercise 4.3 : rechercher si un graphe est acyclique et donner ses arêtes de retour");
		System.out.println("Pour ce graphe non-orienté gu :");
		retour(gu);
		
		// Ex 5
		System.out.println();
		System.out.println("Exercise 5 : recherche les chemins de moindre coût à partir d’un nœud donné");
		DIJKSTRA(gdv, "1");
		
		}

} /*----- Fin de la classe AppGraph -----*/
