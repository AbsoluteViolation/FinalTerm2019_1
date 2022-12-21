package sk.upjs.fekal.Fianlterm2019_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Obec implements Comparator<Domacnost>{

	// zoznam domacnosti
	private List<Domacnost> zoznamDomacnosti;
	
	final String nazovObce;
	
	public Obec(String nazovObce) {
		super();
		this.zoznamDomacnosti = new ArrayList<>();
		this.nazovObce = nazovObce;
	}

	public List<Domacnost> getZoznamDomacnosti() {
		return zoznamDomacnosti;
	}

	public void eviduj(Domacnost domacnost) throws NeplatnyNazovObceException {
		if (!domacnost.mesto().equals(nazovObce)) {
			throw new NeplatnyNazovObceException("Neplatny nazov obce.");
		} else {
			zoznamDomacnosti.add(domacnost);
		}
	}

	public static Obec nacitajEvidenciu(String nazovSuboru, String nazovObce) {
		Obec obec = new Obec(nazovObce);
		try (Scanner sc = new Scanner(new File(nazovSuboru))) {
			while (sc.hasNextLine()) {
				obec.eviduj(Domacnost.zoStringu(sc.nextLine()));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obec;
	}

	public void ulozEvidenciu(String nazovSuboru) {

		try (PrintWriter pw = new PrintWriter(new File(nazovSuboru))) {
			for (Domacnost d : zoznamDomacnosti) {
				pw.write(d.toString() + "\n");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\nZoznam domacnsoti:\n");
		for (Domacnost d : zoznamDomacnosti) {
			sb.append(d.toString() + "\n");
		}
		sb.append("\n");
		return sb.toString();
	}

	public double pokrytie() {
		int sKanalizaciou = 0;
		for (Domacnost d : zoznamDomacnosti) {
			if (d.getKapacitaZumpy() != 0) {
				sKanalizaciou += 1;
			}
		}
		return (sKanalizaciou / (double) (zoznamDomacnosti.size())) * 100;
	}

	public boolean kompletnaEvidencia(int pocetObyvatelov) {
		int scitanyPocetObyvatelov = 0;
		for (Domacnost d : zoznamDomacnosti) {
			scitanyPocetObyvatelov += d.getPocetObyvatelov();
		}

		return (pocetObyvatelov == scitanyPocetObyvatelov) ? true : false;
	}

	public int kapacitaCisterny() {
		double maxObjemZumpy = Double.MIN_VALUE;
		for (Domacnost d : zoznamDomacnosti) {
			if (d.getKapacitaZumpy() > maxObjemZumpy) {
				maxObjemZumpy = d.getKapacitaZumpy();
			}
		}
		return (int) (maxObjemZumpy * 1000);
	}

	public List<Domacnost> aktivneDomacnosti() {
		List<Domacnost> aktivneDomacnosti = new ArrayList<>();

		for (Domacnost d : zoznamDomacnosti) {
			if (d.getPocetObyvatelov() > 0 && (d.getRocnaSpotreba() >= 10 || d.getPocetVyvozov() >= 3))
				aktivneDomacnosti.add(d);
		}

		return null;
	}

	public Domacnost najvacsieOdberneMiesto() {

		Domacnost najD = zoznamDomacnosti.get(0);

		for (Domacnost d : zoznamDomacnosti) {
			if (d.getRocnaSpotreba() > najD.getRocnaSpotreba()) {
				najD = d;
			}
		}

		return najD;
	}

	public double celkovaProdukcia(String ulica, int odCislaDomu, int poCisloDomu) {
		double celkovaProdukcia = 0;
		for (Domacnost d : zoznamDomacnosti) {
			if (d.ulica2().equals(ulica)) {
				if (odCislaDomu <= d.vratCisloDomu() && d.vratCisloDomu() <= poCisloDomu) {
					celkovaProdukcia += d.getRocnaSpotreba();
				}
			}
		}

		return celkovaProdukcia;
	}

	public List<String> podozrivaLikvidacia() {
		List<String> podozrivi = new ArrayList<>();

		for (Domacnost d : zoznamDomacnosti) {
			if (d.getKapacitaZumpy() > 0 && 0.9 * d.getRocnaSpotreba() > (d.getKapacitaZumpy() * d.getPocetVyvozov())) {
				podozrivi.add(d.getMeno());
			}
		}
		return podozrivi;
	}

	public List<String> setriaceRodiny() {
		List<String> setriaceRodiny = new ArrayList<>();

		Collections.sort(zoznamDomacnosti);
		for (int i = 0; i < zoznamDomacnosti.size(); i++) {
			setriaceRodiny.add(zoznamDomacnosti.get(i).getMeno());
			if (i + 1 == 5) {
				return setriaceRodiny;
			}
		}

		return setriaceRodiny;
	}

	public Map<Double, Integer> databazaZump() {
		Map<Double, Integer> databazaZump = new HashMap<>();

		for (Domacnost d : zoznamDomacnosti) {
			double kapacita = d.getKapacitaZumpy();
			if (databazaZump.containsKey(kapacita)) {
				databazaZump.put(kapacita, databazaZump.get(kapacita) + 1);
			} else {
				databazaZump.put(kapacita, 1);
			}
		}

		return databazaZump;
	}

	public double usetreneProstriedky(String ulica, double stocne, double cenaZaVyvoz) {
		double usetrene = 0;

		for (Domacnost d : zoznamDomacnosti) {
			if (d.ulica2().equals(ulica) && d.getKapacitaZumpy() != 0) {
				usetrene += d.getPocetVyvozov() * cenaZaVyvoz - d.getKapacitaZumpy() * stocne;
			}
		}

		return usetrene;
	}

	public void prepisOdberatelov(Map<String, String> prepisy) {
		for (Domacnost d : zoznamDomacnosti) {
			if (prepisy.containsKey(d.getMeno())) {
				d.setMeno(prepisy.get(d.getMeno()));
			}
		}
	}
	
	public Set<String> velkeFirmy() {
		Map<String, Integer> velkeFirmy = new HashMap<>();
		
		for (Domacnost d:zoznamDomacnosti) {
			if (d.getPocetObyvatelov() == 0) {
				String meno = d.getMeno();
				if (velkeFirmy.containsKey(meno)) {
					velkeFirmy.put(meno, velkeFirmy.get(meno) + 1);
				} else {
					velkeFirmy.put(meno, 1);
				}
			}
		}
		
		Set<String> firmySViecerymiOdbernymiMiestami = new HashSet<>();
		
		for (String meno:velkeFirmy.keySet()) {
			if (velkeFirmy.get(meno) >= 2) {
				firmySViecerymiOdbernymiMiestami.add(meno);
			}
		}
		
		return firmySViecerymiOdbernymiMiestami;
	}
	
	public List<Domacnost> domacnostiPodlaOdberu() {
		List<Domacnost> domacnostiPodlaOdberu = new ArrayList<>(zoznamDomacnosti);
		
		Collections.sort(domacnostiPodlaOdberu);
		
		return domacnostiPodlaOdberu;
	}

	@Override
	public int compare(Domacnost d1, Domacnost d2) {
		double spotreba1 = SpotrebaNaObyvatela(d1.getRocnaSpotreba(), d1.getPocetObyvatelov());
		double spotreba2 = SpotrebaNaObyvatela(d2.getRocnaSpotreba(), d2.getPocetObyvatelov());
		if (spotreba1 < spotreba2) 
			return -1;
		else if (spotreba1 > spotreba2)
			return 1;
		else
			return 0;
	}
	
	public double SpotrebaNaObyvatela(double rocnaSpotreba, double pocetObyvatelov) {
		return (pocetObyvatelov != 0) ? rocnaSpotreba / pocetObyvatelov : rocnaSpotreba;
	}
	
	public Map<String, Double> vyuctovanie(double stocne, double cenaZaVyvoz) {
		ImplementMap im = new ImplementMap();
		
		for (Domacnost d:zoznamDomacnosti) {
			String meno = d.getMeno();
			if (im.containsKey(meno)) {
				im.put(meno, im.get(meno) + d.vypocetPlatby(stocne, cenaZaVyvoz));
			} else {
				im.put(meno, d.vypocetPlatby(stocne, cenaZaVyvoz));
			}
		}
		return im;
	}
}
