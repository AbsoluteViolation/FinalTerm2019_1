package zadanie8;

import java.util.ArrayList;
import java.util.List;

public class Launcher {

	public static void main(String[] args) {
		
		
		
		Odber o1 = new Odber("elo", "12.12.2020", 4, true);
        Odber o2 = new Odber("zuzka", "12.12.2020", 2, false);
        Odber o3 = new Odber("mato", "13.12.2020", 1, true);
        Odber o4 = new Odber("timka", "13.12.2020", 1, true);
        Odber o5 = new Odber("rastik", "14.12.2020", 4, false);
        Odber o6 = new Odber("michal", "14.12.2020", 4, false);
        Odber o7 = new Odber("fero", "15.12.2020", 3, true);
        Odber o8 = new Odber("terka", "15.12.2020", 4, true);
        Odber o9 = new Odber("elo", "16.12.2020", 3, false);
        Odber o10 = new Odber("zuzka", "16.12.2020", 1, true);
        Odber o11 = new Odber("timka", "16.12.2020", 1, true);
        Odber o12 = new Odber("mato", "16.12.2020", 1, true);
        ZoznamOdberov zoznam = new ZoznamOdberov();
        zoznam.pridaj(o8);
        zoznam.pridaj(o9);
        zoznam.pridaj(o10);
        zoznam.pridaj(o11);
        zoznam.pridaj(o12);
        zoznam.pridaj(o1);
        zoznam.pridaj(o2);
        zoznam.pridaj(o3);
        zoznam.pridaj(o4);
        zoznam.pridaj(o5);
        zoznam.pridaj(o6);
        zoznam.pridaj(o7);
        
       
 
        System.out.println(zoznam.pocetOdberovNaMieste(1));
        System.out.println(zoznam.pozitivneTestovani());
        System.out.println(zoznam.priemernyPocetTestovZaDenTestovania());
        System.out.println(zoznam.testovaniOdDo("13.12.2020", "16.12.2020"));
        System.out.println(zoznam.prekonalNakazu("elo"));
        
        
        System.out.println(zoznam.pocetTestovOsoby());
        System.out.println(zoznam.opakovanePozitivny());
        zoznam.prekonalNakazu("timka");
        List<String> list = new ArrayList<>();
        list.add("simon");
        list.add("lucka");
        list.add("elo");
        list.add("zuzka");
        list.add("rastik");
        list.add("bojo");
        System.out.println(zoznam.bezOdberu(list));
        System.out.println(zoznam.najvytazenejsiePracovisko());
		

	}

}
