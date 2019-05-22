import java.util.Scanner;

public class Tekstiseikkailu {
	
	// Scannerit ja varit
	Scanner lukija = new Scanner(System.in); // Scanner valinnoille
	Scanner enter = new Scanner(System.in); // Scanner pelkästään enteriä varten -- helpottaa koodin seuraamista
	int elama, valinta, trolliElama, bossElama;
	String nimi, ase;
	int trolli = 0; // Tarvitaan silta-osioon katsomaan onko trolli elossa vai ei
	
	public static void main(String[] args) {

		// Luodaan uusi tekstiseikkailu nimeltä tag (text adventure game)
		Tekstiseikkailu tag;
		tag = new Tekstiseikkailu();	
		tag.aloitus(); // Siirrytään aloitukseen
	}
	
	// Aloituksessa valitaan pelaajan nimi ja siirrytään aukioon Enterin jälkeen
	public void aloitus(){
		
		// Annetaan pelaajalle, trollille ja jättiläiselle elämät.
		// Vakioaseena voiveitsi, joka pitää korvata miekalla jatkaakseen tarinaa
		elama = 10;
		trolliElama = 15;
		bossElama = 20;
		ase = "Voiveitsi";
				
		System.out.println("Syötä nimi:");
		nimi = lukija.nextLine();
		
		System.out.println("Hei " + nimi + "! Aloita peli painamalla Enter!");	
		enter.nextLine();
		aukio(); // Siirrytään aukioon, mistä peli alkaa
	}	
	
	// Aukiosta alkaa peli
	public void aukio() {
		System.out.println("\n=====================================\n");
		System.out.println("Saavuit tyhjälle aukiolle.");
		System.out.println("Minne haluat jatkaa matkaasi?\n");
		System.out.println("1: Iso tammi");
		System.out.println("2: Silta");
		System.out.println("3: Tiheä metsä");
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
	
	// Tammen alta löytyy miekka, joka tuplaa damagen ja jolla pystyy tappamaan trollin
	public void miekka() {
		System.out.println("\n=====================================\n");
		System.out.println("Ison tammen alla loistaa jokin.");
		System.out.println("Löysit miekan! Voiveitsi korvattu miekalla!");
		ase = "Miekka";
		System.out.println("Aseesi on nyt: " + ase);
		System.out.println("\n1: Palaa takaisin");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		if (valinta == 1){
			aukio();
		}
	}
	
	// Sillalla on trolli ja tappamiseen tarvitaan tammen alta löytyvä miekka
	public void silta() {
		// Katsotaan onko trolli kuollut vai ei -- jos on kuollut niin ei spawnaa uusiksi sillalle
		if (trolli == 1) {
			System.out.println("\n=====================================\n");
			System.out.println("Saavuit sillalle.\n");
			System.out.println("Minne haluat jatkaa matkaasi?\n");
			System.out.println("1: Kohti kylää");
			System.out.println("2: Tyhjä aukio");
			System.out.println("\n=====================================\n");
			
			valinta = lukija.nextInt();
			
			if (valinta == 1) {
				matkalla();
			}
			else if (valinta == 2) {
				aukio();
			}
		}
		// Jos ei ole kuollut niin nämä valinnat
		else {
			System.out.println("\n=====================================\n");
			System.out.println("Saavuit sillalle.");
			System.out.println("Siltaa vartioi järkyttävän ruma trolli.\n");
			System.out.println("Mitä haluat tehdä?\n");
			System.out.println("1: Yritä neuvotella");
			System.out.println("2: Hyökkää");
			System.out.println("3: Palaa takaisin");
			System.out.println("\n=====================================\n");
			
			valinta = lukija.nextInt();
			
			if (valinta == 1) {
				elama = elama-1;
				System.out.println("Trollin kanssa ei neuvotella.");
				System.out.println("Sinulla on nyt " + elama + " HP:ta jäljellä!");
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
	
	// Metsä on "täytealue", mistä ei pääse tarinaa jatkamaan
	public void metsa() {
		System.out.println("\n=====================================\n");
		System.out.println("Saavuit tiheään metsään.");
		System.out.println("Mitä haluat tehdä?\n");
		System.out.println("1: Aukio");
		System.out.println("2: Ranta");
		System.out.println("3: Jatka tiheään metsään");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		if (valinta == 1) {
			aukio();
		}
		else if (valinta == 2) {
			ranta();
		}
		else if (valinta == 3) { // Kuolema
			System.out.println("Tipuit kielekkeeltä alas.");
			enter.nextLine();
			kuolema();
		}
	}
	
	// Ranta myös turha, joskin tappaa pelaajan, jos ei ota varoitusta (kova virtaus) tosissaan
	public void ranta() {
		System.out.println("\n=====================================\n");
		System.out.println("Saavuit rannalle.");
		System.out.println("Joessa on liian kova virtaus ylittääksesi sen.");
		System.out.println("Mitä haluat tehdä?\n");
		System.out.println("1: Palaa takaisin");
		System.out.println("2: Ylitä joki");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		if (valinta == 1) {
			metsa();
		}
		else if (valinta == 2) { // Kuolema
			kuolema();
		}
	}
	
	// Sillan jälkeinen aukio, matkalla kohti kylää
	public void matkalla() {
		System.out.println("\n=====================================\n");
		System.out.println("Olet matkalla kohti pientä kylää.\n");
		System.out.println("Minne haluat jatkaa matkaasi?");
		System.out.println("1: Silta");
		System.out.println("2: Pohjoinen");
		System.out.println("3: Lähde");
		System.out.println("4: Kylä");
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
		System.out.println("Täällä ei ole mitään nähtävää.");
		System.out.println("1: Palaa takaisin");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		if (valinta == 1) {
			matkalla();
		}
	}
	
	// Lähde
	public void lahde() {
		System.out.println("\n=====================================\n");
		System.out.println("Saavuit kauniin vesilähteen äärelle.\n");
		System.out.println("Mitä haluat tehdä?");
		System.out.println("1: Pulahda lähteessä");
		System.out.println("2: Palaa takaisin");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		// Jos pulahtaa lähteessä niin elämät latautuu 20 HP:seen (10 - trollin dmg sijaan)
		if (valinta == 1) {
			elama = 20;
			System.out.println("\n=====================================\n");
			System.out.println("Pulahdit lähteessä ja elämäsi latautuivat täysin.");
			System.out.println("Elämäsi: " + elama);
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
		System.out.println("Saavuit pieneen kylään.");
		System.out.println("Kyläläinen pyytää sankarin apua jättiläisen tappamisessa.");
		System.out.println("Viimeksi jättiläinen on pongattu vuoren uumenissa.");
		System.out.println("\nMitä teet?");
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
		System.out.println("Vuoren läpi kulkee pimeä tunneli.");
		System.out.println("\nMitä teet?");
		System.out.println("1: Jatka tunneliin");
		System.out.println("2: Palaa takaisin");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		if (valinta == 1) {
			System.out.println("\n=====================================\n");
			System.out.println("Pääsit pimeän tunnelin päähän.");
			System.out.println("Tunnelin päätä vartioi jättiläinen.");
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
	
	// JÄTTILÄINEN
	// JÄTTILÄINEN
	// JÄTTILÄINEN
	
	// Jatketaanko taistelua vai "karataanko"
	public void taisteleBoss() {
		System.out.println("\n=====================================\n");
		System.out.println("Sinun elama: "+ elama);
		System.out.println("Jättiläisen elama: " + bossElama);
		System.out.println("\n1: Hyökkää");
		System.out.println("2: Karkaa");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		if(valinta == 1){
			hyokkaaBoss();
		} 
		else if(valinta == 2){
			elama = elama - 1; // Jättiläistä ei voi karata, pelaaja ottaa 1 dmg
			System.out.println("Et pysty karata. Otit 1 dmg.");
			System.out.println("Paina Enter.");
			enter.nextLine();
			taisteleBoss();
		}
	}
	
	// Hyökkää
	public void hyokkaaBoss() {
		int isku = 0;
		
		// Dmg määrä riippuen onko voiveitsi käytössä vai onko pelaaja hakenut miekan
		if (ase.equals("Voiveitsi")) {
			isku = 1;
		}
		else if (ase.equals("Miekka")) {
			isku = 2;
		}
		
		// Dmg viesti + isku miinustus
		System.out.println("Teit " + isku + " HP:ta vahinkoa jättiläiseen!");
		bossElama = bossElama - isku;
		System.out.println("Jättiläisen elämä: " + bossElama);
		
		// Katsotaan paljonko HP:ta jäljellä ja sen mukaan jatketaan
		if (bossElama < 1) {
			tappoBoss();
		}
		else if (bossElama > 0) {
			int bossDmg;
			bossDmg = 2;
			
			// Trollin tekemä dmg
			System.out.println("Jättiläinen teki " + bossDmg + " HP:ta vahinkoa!");
			elama = elama - bossDmg;
			System.out.println("Pelaajan elama: " + elama);
			
			// Onko pelaajalla HP:ta jäljellä
			if (elama < 1) {
				kuolema();
			}
			else if (elama > 0) {
				taisteleBoss();
			}
		}
	}
	
	// Tappaa jättiläisen
	public void tappoBoss() {
		System.out.println("\n=====================================\n");
		System.out.println("Voitit jättiläisen!");
		System.out.println("Elämäsi: " + elama);
		System.out.println("\nKyläläinen haluaa palkita sinut!");
		System.out.println("1: Palaa kylään");
		System.out.println("\n=====================================\n");
		
		valinta = lukija.nextInt();
		
		// Palkinto jättiläisen tappamisesta
		if (valinta == 1) {
			System.out.println("\n=====================================\n");
			System.out.println("Kyläläinen kiittää sinua uroteostasi!");
			System.out.println("Saat palkinnoksi uuden miekan!");
			ase = "Katana";
			System.out.println("Miekka: " + ase);
			System.out.println("\n=====================================\n");
			enter.nextLine();
			voitto();
		}
	}
	
	// Jos voittaa jättiläisen, voittaa pelin
	public void voitto() {
		System.out.println("\n=====================================\n");
		System.out.println("VOITIT PELIN!");
		System.out.println("\n=====================================\n");
		enter.nextLine();
	}
	
	// TROLLI
	// TROLLI
	// TROLLI
	
	// Hyökkää
	public void hyokkaa() {
		int isku = 0;
		
		// Dmg määrä riippuen onko voiveitsi käytössä vai onko pelaaja hakenut miekan
		if (ase.equals("Voiveitsi")){
			isku = 1;
		}
		else if (ase.equals("Miekka")){
			isku = 2;
		}
		
		// Dmg viesti + isku miinustus
		System.out.println("Teit " + isku + " HP:ta vahinkoa trolliin!");
		trolliElama = trolliElama - isku;
		System.out.println("Trollin elämä: " + trolliElama);
		
		// Katsotaan paljonko HP:ta jäljellä ja sen mukaan jatketaan
		if (trolliElama < 1) {
			tappo();
		}
		else if (trolliElama > 0) {
			int trolliDmg;
			trolliDmg = 1;
			
			// Trollin tekemä dmg
			System.out.println("Trolli teki " + trolliDmg + " HP:ta vahinkoa!");
			elama = elama - trolliDmg;
			System.out.println("Pelaajan elama: " + elama);
			
			// Onko pelaajalla HP:ta jäljellä
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
		System.out.println("\n1: Hyökkää");
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
		System.out.println("Elämäsi: " + elama);
		System.out.println("\nMinne haluat nyt mennä?");
		System.out.println("1: Kohti kylää");
		System.out.println("2: Tyhjä aukio");
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
