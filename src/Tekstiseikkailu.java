import java.util.Scanner;

public class Tekstiseikkailu {
	
	// Scannerit ja varit
	Scanner lukija = new Scanner(System.in); // Scanner valinnoille
	Scanner enter = new Scanner(System.in); // Scanner pelk�st��n enteri� varten -- helpottaa koodin seuraamista
	int elama, valinta, trolliElama, bossElama;
	String nimi, ase;
	int trolli = 0; // Tarvitaan silta-osioon katsomaan onko trolli elossa vai ei
	
	public static void main(String[] args) {

		// Luodaan uusi tekstiseikkailu nimelt� tag (text adventure game)
		Tekstiseikkailu tag;
		tag = new Tekstiseikkailu();	
		tag.aloitus(); // Siirryt��n aloitukseen
	}
	
	// Aloituksessa valitaan pelaajan nimi ja siirryt��n aukioon Enterin j�lkeen
	public void aloitus(){
		
		// Annetaan pelaajalle, trollille ja j�ttil�iselle el�m�t.
		// Vakioaseena voiveitsi, joka pit�� korvata miekalla jatkaakseen tarinaa
		elama = 10;
		trolliElama = 15;
		bossElama = 20;
		ase = "Voiveitsi";
				
		System.out.println("Sy�t� nimi:");
		nimi = lukija.nextLine();
		
		System.out.println("Hei " + nimi + "! Aloita peli painamalla Enter!");	
		enter.nextLine();
		aukio(); // Siirryt��n aukioon, mist� peli alkaa
	}	
	
	// Aukiosta alkaa peli
	public void aukio() {
		System.out.println("\n=====================================\n");
		System.out.println("Saavuit tyhj�lle aukiolle.");
		System.out.println("Minne haluat jatkaa matkaasi?\n");
		System.out.println("1: Iso tammi");
		System.out.println("2: Silta");
		System.out.println("3: Tihe� mets�");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		if (valinta==1) {
			miekka();
		}
		else if (valinta==2) {
			silta();
		}
		else if (valinta==3) {
			metsa();
		}
	}
	
	// Tammen alta l�ytyy miekka, joka tuplaa damagen ja jolla pystyy tappamaan trollin
	public void miekka() {
		System.out.println("\n=====================================\n");
		System.out.println("Ison tammen alla loistaa jokin.");
		System.out.println("L�ysit miekan! Voiveitsi korvattu miekalla!");
		ase = "Miekka";
		System.out.println("Aseesi on nyt: " + ase);
		System.out.println("\n1: Palaa takaisin");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		if (valinta == 1){
			aukio();
		}
	}
	
	// Sillalla on trolli ja tappamiseen tarvitaan tammen alta l�ytyv� miekka
	public void silta() {
		// Katsotaan onko trolli kuollut vai ei -- jos on kuollut niin ei spawnaa uusiksi sillalle
		if (trolli == 1) {
			System.out.println("\n=====================================\n");
			System.out.println("Saavuit sillalle.\n");
			System.out.println("Minne haluat jatkaa matkaasi?\n");
			System.out.println("1: Kohti kyl��");
			System.out.println("2: Tyhj� aukio");
			System.out.println("\n=====================================\n");
			
			valinta = lukija.nextInt();
			
			if (valinta == 1) {
				matkalla();
			}
			else if (valinta == 2) {
				aukio();
			}
		}
		// Jos ei ole kuollut niin n�m� valinnat
		else {
			System.out.println("\n=====================================\n");
			System.out.println("Saavuit sillalle.");
			System.out.println("Siltaa vartioi j�rkytt�v�n ruma trolli.\n");
			System.out.println("Mit� haluat tehd�?\n");
			System.out.println("1: Yrit� neuvotella");
			System.out.println("2: Hy�kk��");
			System.out.println("3: Palaa takaisin");
			System.out.println("\n=====================================\n");
			
			valinta = lukija.nextInt();
			
			if (valinta == 1) {
				elama = elama-1;
				System.out.println("Trollin kanssa ei neuvotella.");
				System.out.println("Sinulla on nyt " + elama + " HP:ta j�ljell�!");
				enter.nextLine();
				silta();
			}
			else if (valinta == 2) {
				hyokkaa();
			}
			else if (valinta == 3) {
				aukio();
			}
		}
	}
	
	// Mets� on "t�ytealue", mist� ei p��se tarinaa jatkamaan
	public void metsa() {
		System.out.println("\n=====================================\n");
		System.out.println("Saavuit tihe��n mets��n.");
		System.out.println("Mit� haluat tehd�?\n");
		System.out.println("1: Aukio");
		System.out.println("2: Ranta");
		System.out.println("3: Jatka tihe��n mets��n");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		if (valinta == 1) {
			aukio();
		}
		else if (valinta == 2) {
			ranta();
		}
		else if (valinta == 3) { // Kuolema
			System.out.println("Tipuit kielekkeelt� alas.");
			enter.nextLine();
			kuolema();
		}
	}
	
	// Ranta my�s turha, joskin tappaa pelaajan, jos ei ota varoitusta (kova virtaus) tosissaan
	public void ranta() {
		System.out.println("\n=====================================\n");
		System.out.println("Saavuit rannalle.");
		System.out.println("Joessa on liian kova virtaus ylitt��ksesi sen.");
		System.out.println("Mit� haluat tehd�?\n");
		System.out.println("1: Palaa takaisin");
		System.out.println("2: Ylit� joki");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		if (valinta == 1) {
			metsa();
		}
		else if (valinta == 2) { // Kuolema
			kuolema();
		}
	}
	
	// Sillan j�lkeinen aukio, matkalla kohti kyl��
	public void matkalla() {
		System.out.println("\n=====================================\n");
		System.out.println("Olet matkalla kohti pient� kyl��.\n");
		System.out.println("Minne haluat jatkaa matkaasi?");
		System.out.println("1: Silta");
		System.out.println("2: Pohjoinen");
		System.out.println("3: L�hde");
		System.out.println("4: Kyl�");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		if (valinta == 1) {
			silta();
		}
		else if (valinta == 2) {
			umpikuja();
		}
		else if (valinta == 3) {
			lahde();
		}
		else if (valinta == 4) {
			kyla();
		}
	}
	
	// Umpikuja
	public void umpikuja() {
		System.out.println("\n=====================================\n");
		System.out.println("Saavuit umpikujaan.\n");
		System.out.println("T��ll� ei ole mit��n n�ht�v��.");
		System.out.println("1: Palaa takaisin");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		if (valinta == 1) {
			matkalla();
		}
	}
	
	// L�hde
	public void lahde() {
		System.out.println("\n=====================================\n");
		System.out.println("Saavuit kauniin vesil�hteen ��relle.\n");
		System.out.println("Mit� haluat tehd�?");
		System.out.println("1: Pulahda l�hteess�");
		System.out.println("2: Palaa takaisin");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		// Jos pulahtaa l�hteess� niin el�m�t latautuu 20 HP:seen (10 - trollin dmg sijaan)
		if (valinta == 1) {
			elama = 20;
			System.out.println("\n=====================================\n");
			System.out.println("Pulahdit l�hteess� ja el�m�si latautuivat t�ysin.");
			System.out.println("El�m�si: " + elama);
			System.out.println("\n=====================================\n");
			enter.nextLine();
			lahde();
		}
		else if (valinta == 2) {
			matkalla();
		}
	}
	public void kyla() {
		System.out.println("\n=====================================\n");
		System.out.println("Saavuit pieneen kyl��n.");
		System.out.println("Kyl�l�inen pyyt�� sankarin apua j�ttil�isen tappamisessa.");
		System.out.println("Viimeksi j�ttil�inen on pongattu vuoren uumenissa.");
		System.out.println("\nMit� teet?");
		System.out.println("1: Kohti vuoren uumenia");
		System.out.println("2: Palaa takaisin");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		if (valinta == 1) {
			vuori();
		}
		else if (valinta == 2) {
			matkalla();
		}
	}
	
	// Vuori
	public void vuori() {
		System.out.println("\n=====================================\n");
		System.out.println("Saavuit vuorelle.");
		System.out.println("Vuoren l�pi kulkee pime� tunneli.");
		System.out.println("\nMit� teet?");
		System.out.println("1: Jatka tunneliin");
		System.out.println("2: Palaa takaisin");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		if (valinta == 1) {
			System.out.println("\n=====================================\n");
			System.out.println("P��sit pime�n tunnelin p��h�n.");
			System.out.println("Tunnelin p��t� vartioi j�ttil�inen.");
			System.out.println("Ainoa vaihtoehtosi on taistella.");
			System.out.println("\nPaina Enter.");
			System.out.println("\n=====================================\n");
		    enter.nextLine();
			taisteleBoss();
		}
		else if (valinta == 2) {
			matkalla();
		}
	}
	
	// J�TTIL�INEN
	// J�TTIL�INEN
	// J�TTIL�INEN
	
	// Jatketaanko taistelua vai "karataanko"
	public void taisteleBoss() {
		System.out.println("\n=====================================\n");
		System.out.println("Sinun elama: "+ elama);
		System.out.println("J�ttil�isen elama: " + bossElama);
		System.out.println("\n1: Hy�kk��");
		System.out.println("2: Karkaa");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		if(valinta == 1){
			hyokkaaBoss();
		} 
		else if(valinta == 2){
			elama = elama - 1; // J�ttil�ist� ei voi karata, pelaaja ottaa 1 dmg
			System.out.println("Et pysty karata. Otit 1 dmg.");
			System.out.println("Paina Enter.");
			enter.nextLine();
			taisteleBoss();
		}
	}
	
	// Hy�kk��
	public void hyokkaaBoss() {
		int isku = 0;
		
		// Dmg m��r� riippuen onko voiveitsi k�yt�ss� vai onko pelaaja hakenut miekan
		if (ase.equals("Voiveitsi")) {
			isku = 1;
		}
		else if (ase.equals("Miekka")) {
			isku = 2;
		}
		
		// Dmg viesti + isku miinustus
		System.out.println("Teit " + isku + " HP:ta vahinkoa j�ttil�iseen!");
		bossElama = bossElama - isku;
		System.out.println("J�ttil�isen el�m�: " + bossElama);
		
		// Katsotaan paljonko HP:ta j�ljell� ja sen mukaan jatketaan
		if (bossElama < 1) {
			tappoBoss();
		}
		else if (bossElama > 0) {
			int bossDmg;
			bossDmg = 2;
			
			// Trollin tekem� dmg
			System.out.println("J�ttil�inen teki " + bossDmg + " HP:ta vahinkoa!");
			elama = elama - bossDmg;
			System.out.println("Pelaajan elama: " + elama);
			
			// Onko pelaajalla HP:ta j�ljell�
			if (elama < 1) {
				kuolema();
			}
			else if (elama > 0) {
				taisteleBoss();
			}
		}
	}
	
	// Tappaa j�ttil�isen
	public void tappoBoss() {
		System.out.println("\n=====================================\n");
		System.out.println("Voitit j�ttil�isen!");
		System.out.println("El�m�si: " + elama);
		System.out.println("\nKyl�l�inen haluaa palkita sinut!");
		System.out.println("1: Palaa kyl��n");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		// Palkinto j�ttil�isen tappamisesta
		if (valinta == 1) {
			System.out.println("\n=====================================\n");
			System.out.println("Kyl�l�inen kiitt�� sinua uroteostasi!");
			System.out.println("Saat palkinnoksi uuden miekan!");
			ase = "Katana";
			System.out.println("Miekka: " + ase);
			System.out.println("\n=====================================\n");
			enter.nextLine();
			voitto();
		}
	}
	
	// Jos voittaa j�ttil�isen, voittaa pelin
	public void voitto() {
		System.out.println("\n=====================================\n");
		System.out.println("VOITIT PELIN!");
		System.out.println("\n=====================================\n");
		enter.nextLine();
	}
	
	// TROLLI
	// TROLLI
	// TROLLI
	
	// Hy�kk��
	public void hyokkaa() {
		int isku = 0;
		
		// Dmg m��r� riippuen onko voiveitsi k�yt�ss� vai onko pelaaja hakenut miekan
		if (ase.equals("Voiveitsi")){
			isku = 1;
		}
		else if (ase.equals("Miekka")){
			isku = 2;
		}
		
		// Dmg viesti + isku miinustus
		System.out.println("Teit " + isku + " HP:ta vahinkoa trolliin!");
		trolliElama = trolliElama - isku;
		System.out.println("Trollin el�m�: " + trolliElama);
		
		// Katsotaan paljonko HP:ta j�ljell� ja sen mukaan jatketaan
		if (trolliElama < 1) {
			tappo();
		}
		else if (trolliElama > 0) {
			int trolliDmg;
			trolliDmg = 1;
			
			// Trollin tekem� dmg
			System.out.println("Trolli teki " + trolliDmg + " HP:ta vahinkoa!");
			elama = elama - trolliDmg;
			System.out.println("Pelaajan elama: " + elama);
			
			// Onko pelaajalla HP:ta j�ljell�
			if (elama < 1){
				kuolema();
			}
			else if (elama > 0){
				taistele();
			}
		}
	}
	
	// Jatketaanko taistelua vai karataanko
	public void taistele(){
		System.out.println("\n=====================================\n");
		System.out.println("Sinun elama: "+ elama);
		System.out.println("Trollin elama: " + trolliElama);
		System.out.println("\n1: Hy�kk��");
		System.out.println("2: Karkaa");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		if(valinta==1){
			hyokkaa();
		}
		else if(valinta==2){
			aukio();
		}
		else{
			taistele();
		}
	}
	
	// Jos voittaa trollin
	public void tappo() {
		trolli = 1;
		System.out.println("\n=====================================\n");
		System.out.println("Voitit trollin!");
		System.out.println("El�m�si: " + elama);
		System.out.println("\nMinne haluat nyt menn�?");
		System.out.println("1: Kohti kyl��");
		System.out.println("2: Tyhj� aukio");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		if (valinta == 1){
			matkalla();
		}
		else if (valinta == 2) {
			aukio();
		}	
	}
	
	// Kuolema - kaikki tilanteet
	public void kuolema() {
		System.out.println("\n=====================================\n");
		System.out.println("Kuolit!");
		System.out.println("\n=====================================\n");
		enter.nextLine();
	}
}
