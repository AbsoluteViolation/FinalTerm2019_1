package sk.upjs.fekal.Fianlterm2019_1;

import java.util.*;

public class Domacnost implements Comparable<Domacnost>{

	private String meno;
	private String adresa;
	private int pocetObyvatelov;
	private double rocnaSpotreba;
	private double kapacitaZumpy;
	private int pocetVyvozov;

	public Domacnost(String meno, String adresa, int pocetObyvatelov, double rocnaSpotreba) {
		super();
		this.meno = meno;
		this.adresa = adresa;
		this.pocetObyvatelov = pocetObyvatelov;
		this.rocnaSpotreba = rocnaSpotreba;
//		this.kapacitaZumpy = 0;
//		this.pocetVyvozov = 0;
	}

	public Domacnost(String meno, String adresa, int pocetObyvatelov, double rocnaSpotreba, double kapacitaZumpy,
			int pocetVyvozov) {
		super();
		this.meno = meno;
		this.adresa = adresa;
		this.pocetObyvatelov = pocetObyvatelov;
		this.rocnaSpotreba = rocnaSpotreba;
		this.kapacitaZumpy = kapacitaZumpy;
		this.pocetVyvozov = pocetVyvozov;
	}
	
	public String getMeno() {
		return meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public int getPocetObyvatelov() {
		return pocetObyvatelov;
	}

	public void setPocetObyvatelov(int pocetObyvatelov) {
		this.pocetObyvatelov = pocetObyvatelov;
	}

	public double getRocnaSpotreba() {
		return rocnaSpotreba;
	}

	public void setRocnaSpotreba(double rocnaSpotreba) {
		this.rocnaSpotreba = rocnaSpotreba;
	}

	public double getKapacitaZumpy() {
		return kapacitaZumpy;
	}

	public void setKapacitaZumpy(double kapacitaZumpy) {
		this.kapacitaZumpy = kapacitaZumpy;
	}

	public int getPocetVyvozov() {
		return pocetVyvozov;
	}

	public void setPocetVyvozov(int pocetVyvozov) {
		this.pocetVyvozov = pocetVyvozov;
	}
	
	@Override
	public String toString() {
		return meno + "\t" + adresa + "\t" + pocetObyvatelov + "\t" + rocnaSpotreba + "\t" + kapacitaZumpy + "\t"
				+ pocetVyvozov;
	}
	
	public static Domacnost zoStringu(String popis) {
		Domacnost d;
		Scanner sc = new Scanner(popis);
		sc.useDelimiter("\t");
		sc.useLocale(Locale.ENGLISH);
		String meno = sc.next();
		String adresa = sc.next();
		int pocetObyvatelov = sc.nextInt();
		double rocnaSpotreba = sc.nextDouble();
		double kapacitaZumpy = 0;
		int pocetVyvozov = 0;
		if (sc.hasNext()) {
			kapacitaZumpy = sc.nextDouble();
			pocetVyvozov = sc.nextInt();
			
		}
		sc.close();
		d = new Domacnost(meno, adresa, pocetObyvatelov, rocnaSpotreba, kapacitaZumpy, pocetVyvozov);
		return d;
	}
	
	public double vypocetPlatby(double stocne, double cenaZaVyvoz) {
		
		double vypocetPlatby;
		
		if (kapacitaZumpy == 0) {
			vypocetPlatby = rocnaSpotreba * stocne;
		} else {
			vypocetPlatby = pocetVyvozov * cenaZaVyvoz;
		}
		
		return vypocetPlatby;		
	}
	
	public String ulica() {

		String[] adresy = adresa.split(" ");
		
		String ulica = "";
		String mesto = "";
		
		for (int i = 0; i < adresy.length; i++) {
			if (adresy[i].matches(".*\\d.*")) {
				return ulica;
			}
			ulica = (ulica.isEmpty()) ? ulica + adresy[i] : ulica + " " + adresy[i];
			mesto = adresy[adresy.length-(i+1)] + " " + mesto;
//			System.out.println(ulica);
//			System.out.println(mesto);
			if (ulica.equals(mesto)) {
				return null;
			}
		}
		
		System.out.println(adresy);

		return "";
	}
	
	public String ulica2() {

		String[] adresy = adresa.split(" ");
		
		String ulica = "";
		
		for (int i = 0; i < adresy.length; i++) {
			if (adresy[i].matches(".*\\d.*")) {
				return ulica;
			}
			ulica = (ulica.isEmpty()) ? ulica + adresy[i] : ulica + " " + adresy[i];
		}

		return "";
	}
	
	public String mesto() {

		String[] adresy = adresa.split(" ");
		
		String mesto = "";
		
		for (int i = 0; i < adresy.length; i++) {
			if (adresy[adresy.length-(i+1)].matches(".*\\d.*")) {
				return mesto;
			}
			mesto = adresy[adresy.length-(i+1)] + " " + mesto;
		}
		
		System.out.println(adresy);

		return "";
	}

	@Override
	public int compareTo(Domacnost d) {
		double spotreba1 = vypocitajSpotrebu(rocnaSpotreba);
		double spotreba2 = vypocitajSpotrebu(d.rocnaSpotreba);

		if (spotreba1 < spotreba2)
			return -1;
		else if (spotreba2 > spotreba1)
			return 1;
		else
			return 0;
	}
	
	public double vypocitajSpotrebu(double rocnaSpotreba) {
		return (pocetObyvatelov == 0) ? rocnaSpotreba : rocnaSpotreba / pocetObyvatelov;
	}

	public int vratCisloDomu() {
		String cisloDomu = "";
		for (int i = 0; i < adresa.length(); i++) {
			if ('0' <= adresa.charAt(i) && adresa.charAt(i) <= '9') {
				cisloDomu += adresa.charAt(i);
			}
			if (Character.valueOf(adresa.charAt(i)).compareTo(',') == 0) {
				return Integer.parseInt(cisloDomu);
			}
			
		}
		
		return 0;
	}
	
	
}
