package atm;

import java.util.Scanner;

public class BicaMadalinaATM {
	public static String[] useri = { "Maria", "Ion", "Marian" };//declararea userilor
	public static String[] pin = { "1234", "2345", "3456" };//declararea pinurilor
	public static double[] sold = { 3000, 4000, 6000 };//declararea soldurilor
	public static int id = -1;//face conectarea intre user,pin si sold in functie de pozitia userului introdus
	public static boolean ok = false;//devine true la logare, daca la a treia incercare nu e true,
	//blocheaza programul
	public static String cerinta = "0";//initial cerinta este 0, se modifica la cererea utilizatorului
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		conectare();// apelarea functiei conectare
		meniu();//apelarea meniul in cazul in care s-a realizat conectarea

	}

	public static void conectare() {
		int incercari = 0;// numaru de incercari este la inceput 0
		do {
			System.out.println("Bine ati venit!");
			System.out.println("Introduceti userul:");
			String user = sc.nextLine();// se intoduce userul
			System.out.println("Introduceti pin-ul:");
			String pinUser = sc.nextLine();// se introduce parola
			incercari++;// numara de cate ori a fost introdus userul si pin-ul

			for (int i = 0; i < useri.length; i++) {//parcurgem array-urile
				if ((user.toLowerCase().equals(useri[i].toLowerCase())) && (pin[i].equals(pinUser))) {
					ok = true;
					// verifica daca user-ul si pin-ul sunt valabile netinand cont de Coding Style
					id = i;// in timpul logarii id primeste valoarea pozitiei userului
				}
			}
			if (id > -1) {
				// daca s-a realizat logarea afiseaza mesajul si continua cu meniul
				System.out.println("Logare efectuata! " + useri[id]+"\n");

				break;
			} else {
				System.out.println("User sau pin-ul este gresit! \n");
				System.out.println("Mai aveti " + (3 - incercari) + " incercari ramase!");
				continue;// programul este reluat de maxim 3 ori
			}
		} while (incercari < 3);

		if (incercari == 3 && ok == false) {// daca datele sunt introduse gresit inclusiv la treia incercare,
			                                //programul se blocheaza
			System.out.println("Card blocat!");
			System.exit(0);
		}
	}

	public static void meniu() {
		System.out.println("Apasati tasta corespunzatoare tranzactiei dorite!\n"
				           +"Puteti efectua oricate tranzactii doriti!");
		System.out.println("1. " + "Vizualizare sold");
		System.out.println("2. " + "Retragere numerar");
		System.out.println("3. " + "Depunere numerar");
		System.out.println("4. " + "Schimbare parola");
		System.out.println("5. " + "Delogare");
		System.out.println("6. " + "Iesire");

		while (!cerinta.equals("6")) {//se executa pana la apelarea cerintei 6
			cerinta = sc.nextLine();
			switch (cerinta) {
			case "1":
				System.out.println("Soldul este in valoare de " + sold[id] + " lei");//afiseaza soldul
				break;

			case "2":
				retragereSume();//apeleaza metoda de retragere sume
				break;
			case "3":
				adaugaSume();//apeleaza metoda de adaugare sume
				break;
			case "4":
				schimbarePin();//apeleaza metoda de schimbare a pinului
				break;
			case "5" :
				id = -1;//se executa delogarea, iar id-ul devine -1 (false)
				conectare();// se apeleaza metoda conectare pentru introducerea unui alt user
				meniu();// se apeleaza meniul, pentru efectuarea tranzactiilor
				break;
			
			case "6":
				System.out.println("O zi buna!");//se iese din program
				System.exit(0);
				break;
			}
		}
      	

	}

	public static void schimbarePin() {
		System.out.println("Introduceti prinul actual!");
		String pinActual = sc.next();
		if (pinActual.equals(pin[id])) {//se verifica daca pinul a fost introdus corect
			System.out.println("Introduceri pin-ul nou din 4 cifre");
			String pinNou = sc.next();
			while (pinNou.length() != 4) {//lungimea noului pin sa fie de maxim 4 cifre
				System.out.println("Pin-ul trebuie sa fie din 4 cifre. Introduceti un pin din 4 cifre!");
				pinNou = sc.next();
			}
			System.out.println("Confirmati noul pin ");
			String confirmarePin = sc.next();
			if (pinNou.equals(confirmarePin)) {//se verifica daca pin-ul nou a fost confirmat corect
				pin[id] = confirmarePin;
				System.out.println("Pin-ul a fost schimbat, noul pin este: " + pinNou);
			} else {
				System.out.println("Pin confirmat incorect");
			}
		} else {
			System.out.println("Pin incorect");
		}
	}

	public static void adaugaSume() {
		System.out.println("Ce suma doriti sa adaugati? ");
		double sumaAdaugata = sc.nextDouble();
		sold[id] +=  sumaAdaugata;//adauga suma introdusa la sold
		System.out.println("Suma adaugata, noul sold este: " + sold[id] + " lei");
	}

	public static void retragereSume() {
		System.out.println("Ce suma doriti sa retrageti? ");
		double sumaRetrasa = sc.nextDouble();
		if (sumaRetrasa < sold[id]) {
			sold[id] -= sumaRetrasa;//scade suma retrasa din sold
			System.out.println("Retragere aprobata, soldul ramas este in valoare de: " + sold[id] + " lei");
		} else {
			System.out.println("Sold insuficient");
		}
	}
}
