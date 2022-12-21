package sk.upjs.fekal.Fianlterm2019_1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import sk.upjs.jpaz2.*;

public class Launcher {

	public static void main(String[] args) {
		// put your code here
		Domacnost d1 = new Domacnost("Jozko", "Jesenna 5, 04001 Kosice", 5, 210.5, 12.43, 8);
		Domacnost d2 = new Domacnost("Miska", "Javovska 7", 4, 100, 9, 10);
		Domacnost d3 = new Domacnost("Barbora", "Jesenna 58, 04001 Polanovce Kosice", 5, 150.5);
		Domacnost d4 = new Domacnost("Daniela", "Jesenna 5, 04001 Kosice", 5, 71.5, 12.43, 8);
		Domacnost d5 = new Domacnost("Robert", "Javovska 7", 4, 394, 9, 10);
		Domacnost d6 = new Domacnost("Denis", "Jesenna 58, 04001 Polanovce Kosice", 5, 28.5);
//		System.out.println(d3.ulica());
//		System.out.println(d3.vratCisloDomu());
		Obec o = new Obec("Kosice");
		o.eviduj(d1);
		o.eviduj(d2);
		o.eviduj(d3);
		o.eviduj(d4);
		o.eviduj(d5);
		o.eviduj(d6);
		System.out.println(o.podozrivaLikvidacia());
//		System.out.println(d1.toString());
//		System.out.println(o.toString());
//		Collections.sort(o.getZoznamDomacnosti());
		System.out.println(o.databazaZump());
//		Map<String, String> prepisy = new HashMap<>();
//		prepisy.put("Daniela", "Margareta");
//		prepisy.put("Miska", "Bianka");
//		o.prepisOdberatelov(prepisy);
//		System.out.println(o.toString());
//		System.out.println(o.usetreneProstriedky("Jesenna", 20, 50));
//		System.out.println(o.najvacsieOdberneMiesto());
		System.out.println(o.domacnostiPodlaOdberu());
		
//		Obec obec = Obec.nacitajEvidenciu("ulozene_domacnosti");
//		System.out.println(obec.getZoznamDomacnosti());
//		System.out.println(obec.toString());
//		obec.ulozEvidenciu("ulozene_domacnosti");
	}
}