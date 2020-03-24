package application.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import application.model.Betalingsform;
import application.model.Gaveæske;
import application.model.IngenRabat;
import application.model.PantProdukt;
import application.model.Pris;
import application.model.PrisRabat;
import application.model.Prisliste;
import application.model.ProcentRabat;
import application.model.Produkt;
import application.model.Produktgruppe;
import application.model.Rabat;
import application.model.Rundvisning;
import application.model.Salg;
import application.model.Salgslinje;
import application.model.Udlejning;
import storage.Storage;

/**
 * 
 * @author Sille, Simon og Michelle
 *
 */

public class Controller {

	private static Controller controller;
	private Storage storage;

	public static Controller getController() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	private Controller() {
		storage = Storage.getInstance();
	}

	/**
	 * Hvis navn ikke har en længde over 0 kastes en RuntimeException Hvis Opretter
	 * en produktgruppe, produktgruppen bliver addet til storage og bliver retuneret
	 *
	 * @param navn
	 * @param udlejningsprodukt
	 */
	public Produktgruppe createProduktgruppe(String navn, boolean udlejningsprodukt) throws IllegalArgumentException {
		if (navn.length() == 0)
			throw new IllegalArgumentException("En produktgruppe skal have et navn");
		Produktgruppe produktgruppe = new Produktgruppe(navn, udlejningsprodukt);
		storage.addProduktgruppe(produktgruppe);
		return produktgruppe;
	}

	/**
	 * Sletter en produktgruppe
	 * 
	 * @param produktgruppe
	 */
	public void deleteProduktgruppe(Produktgruppe produktgruppe) {
		storage.removeProduktgruppe(produktgruppe);
	}

	/**
	 * OpdaterProduktgruppe
	 * 
	 * @param produktgruppe
	 * @param navn
	 * @param udlejningsprodukt
	 */
	public void updateProduktgruppe(Produktgruppe produktgruppe, String navn, boolean udlejningsprodukt)
			throws IllegalArgumentException {
		if (navn.length() == 0) {
			throw new IllegalArgumentException("Produktgruppen skal have et navn");
		}
		produktgruppe.setNavn(navn);
		produktgruppe.setUdlejningsprodukt(udlejningsprodukt);
	}

	/**
	 * Opretter et produkt Hvis navn og/eller lagerantal ikke matcher kravene kastes
	 * en IllegalArgumentException retuneret
	 * 
	 * Pre: ptype må ikke være null
	 *
	 * @param ptype
	 * @param navn
	 * @param lagerAntal
	 */
	public Produkt createProdukt(Produktgruppe ptype, String navn, int lagerAntal) throws IllegalArgumentException {
		Pre.require(ptype != null);
		if (navn.length() == 0)
			throw new IllegalArgumentException("Et produkt skal have et navn");
		if (lagerAntal == 0)
			throw new IllegalArgumentException("Et produkt kan ikke have et lager antal på 0");
		Produkt produkt = ptype.CreateProdukt(navn, lagerAntal);
		storage.addProdukt(produkt);
		return produkt;
	}

	/**
	 * Sletter et produkt
	 * 
	 * @param produkt
	 */

	public void deleteProdukt(Produkt produkt) {
		produkt.getProduktgruppe().removeProdukt(produkt);
		storage.removeProdukt(produkt);
	}

	/**
	 * OpdaterProdukt
	 * 
	 * @param produkt
	 * @param navn
	 * @param antalPålager
	 * @param ptype
	 */
	public void updateProdukt(Produkt produkt, String navn, int antalPålager, Produktgruppe ptype)
			throws IllegalArgumentException {
		if (navn.length() == 0) {
			throw new IllegalArgumentException("Produktet skal have et navn");
		}
		if (antalPålager == 0) {
			throw new IllegalArgumentException("Der skal være produkter på lager");
		}
		if (ptype == null) {
			throw new IllegalArgumentException("Produktet skal være tilknyttet en produktgruppe");
		}
		produkt.setNavn(navn);
		produkt.setAntalPåLager(antalPålager);
		produkt.getProduktgruppe().removeProdukt(produkt);
		ptype.addProdukt(produkt);
	}

	/**
	 * Opretter et pantprodukt Hvis navn, lagerantal og/eller pantpris ikke matcher
	 * kravene, kastes en IllegalArgumentException retuneret
	 * 
	 * Pre: ptype må ikke værenull
	 *
	 * @param ptype
	 * @param navn
	 * @param lagerAntal
	 * @param pantpris
	 */
	public PantProdukt createPantProdukt(Produktgruppe ptype, String navn, int lagerAntal, double pantpris)
			throws IllegalArgumentException {
		Pre.require(ptype != null);
		if (navn.length() == 0)
			throw new IllegalArgumentException("Et produkt skal have et navn");
		if (lagerAntal == 0)
			throw new IllegalArgumentException("Et produkt kan ikke have et lager antal på 0");
		if (pantpris == 0)
			throw new IllegalArgumentException("Pantprisen skal være større end 0");
		PantProdukt produkt = ptype.CreatePantProdukt(navn, lagerAntal, pantpris);
		storage.addProdukt(produkt);
		return produkt;

	}

	/**
	 * OpdaterPantProdukt
	 * 
	 * @param produkt
	 * @param navn
	 * @param antalPålager
	 * @param ptype
	 * @param pantpris
	 */
	public void updatePantProdukt(PantProdukt produkt, String navn, int antalPålager, Produktgruppe ptype,
			double pantpris) throws IllegalArgumentException {
		if (navn.length() == 0)
			throw new IllegalArgumentException("Et produkt skal have et navn");
		if (antalPålager == 0)
			throw new IllegalArgumentException("Et produkt kan ikke have et lager antal på 0");
		if (pantpris == 0)
			throw new IllegalArgumentException("Pantprisen skal være større end 0");
		produkt.setNavn(navn);
		produkt.setAntalPåLager(antalPålager);
		produkt.setPantPris(pantpris);
		produkt.getProduktgruppe().removeProdukt(produkt);
		ptype.addProdukt(produkt);
	}

	/**
	 * Opretter en gaveæske Hvis det indtastede information ikke opfylder et af
	 * kravene kastes en illegalArgumentException
	 * 
	 * @param navn
	 * @param lagerAntal
	 * @param glasAntal
	 * @param flaskeAntal
	 * @param ptype
	 */
	public Gaveæske createGaveæske(String navn, int lagerAntal, int glasAntal, int flaskeAntal, Produktgruppe ptype)
			throws IllegalArgumentException {
		Pre.require(ptype != null);
		if (navn.length() == 0)
			throw new IllegalArgumentException("En gaveæske skal have et navn");
		if (lagerAntal <= 0)
			throw new IllegalArgumentException("Lager antal skal være over 0");
		if (glasAntal < 0)
			throw new IllegalArgumentException("En gaveæske kan ikke have et glas antal under 0");
		if (flaskeAntal < 0)
			throw new IllegalArgumentException("En gaveæske kan ikke have et flaske antal under 0");

		Gaveæske gaveæske = ptype.CreateGaveæske(navn, lagerAntal, glasAntal, flaskeAntal);
		storage.addProdukt(gaveæske);
		return gaveæske;
	}

	/**
	 * Tilføjer et produkt til en gaveæske
	 * 
	 * @param gaveæske
	 * @param produkt
	 */
	public void addToGaveæske(Gaveæske gaveæske, Produkt produkt) {
		gaveæske.addProdukt(produkt);
	}

	public void addListToGaveæske(Gaveæske gaveæske, List<Produkt> produkter) {
		gaveæske.addProdukter(produkter);
	}

	/**
	 * Fjerner et produkt fra en gaveæske
	 * 
	 * @param gaveæske
	 * @param produkt
	 */
	public void removeFromGaveæske(Gaveæske gaveæske, Produkt produkt) {
		gaveæske.removeProdukt(produkt);
	}

	/**
	 *
	 * Opretter en rundvisning og adder den til storage Kaster
	 * IllegalArgumentException hvis kravene ikke er opfyldt
	 * 
	 * Pre: dato må ikke være null
	 * 
	 * @param beskrivelse
	 * @param startTid
	 * @param slutTid
	 * @param dato
	 * @param prisPrPerson
	 * @param kontaktNr
	 * @param betalingsform
	 * @param antalPersoner
	 * @param studieRabat
	 * 
	 */
	public Rundvisning createRundvisning(String beskrivelse, LocalTime startTid, LocalTime slutTid, LocalDate dato,
			double prisPrPerson, int kontaktNr, Betalingsform betalingsform, int antalPersoner, boolean studieRabat)
			throws IllegalArgumentException {

		if (beskrivelse.length() == 0 || startTid == null || slutTid == null || dato == null || prisPrPerson == 0
				|| kontaktNr == 0 || antalPersoner == 0) {
			throw new IllegalArgumentException("Manglende information");
		}
		if (slutTid.isBefore(startTid))
			throw new IllegalArgumentException("En start tid skal være før en slut tid");
		Rundvisning rundvisning = new Rundvisning(beskrivelse, startTid, slutTid, dato, prisPrPerson, kontaktNr,
				betalingsform, antalPersoner, studieRabat);
		storage.addRundvisning(rundvisning);
		return rundvisning;
	}

	/**
	 * Sletter en rundvisning
	 * 
	 * @param rundvisning
	 */
	public void deleteRundvisning(Rundvisning rundvisning) {
		storage.removeRundvisning(rundvisning);
	}

	/**
	 * Opdater Rundvisning Kaster IllegalArgumentException hvis kravene ikke
	 * eropfyldt
	 * 
	 * @param rundvisning
	 * @param beskrivelse
	 * @param startTid
	 * @param slutTid
	 * @param dato
	 * @param prisPrPerson
	 * @param kontaktNr
	 * @param betalingsform
	 * @param antalPersoner
	 * @param studieRabat
	 */
	public void updateRundvisning(Rundvisning rundvisning, String beskrivelse, LocalTime startTid, LocalTime slutTid,
			LocalDate dato, double prisPrPerson, int kontaktNr, Betalingsform betalingsform, int antalPersoner,
			boolean studieRabat) {
		if (beskrivelse.length() == 0 || startTid == null || slutTid == null || dato == null || prisPrPerson == 0
				|| kontaktNr == 0 || antalPersoner == 0) {
			throw new IllegalArgumentException("Manglende information");
		}
		if (slutTid.isBefore(startTid))
			throw new IllegalArgumentException("En start tid skal være før en slut tid");

		rundvisning.setBeskrivelse(beskrivelse);
		rundvisning.setStartTid(startTid);
		rundvisning.setSlutTid(slutTid);
		rundvisning.setDato(dato);
		rundvisning.setPrisPrPerson(prisPrPerson);
		rundvisning.setKontaktNr(kontaktNr);
		rundvisning.setBetalingsform(betalingsform);
		rundvisning.setAntalPersoner(antalPersoner);
		rundvisning.setStudieRabat(studieRabat);
	}

	/**
	 * Betaler for en rundvisning
	 * 
	 * @param rundvisning
	 * @param betalingsform
	 */
	public void betalRundvisning(Rundvisning rundvisning, Betalingsform betalingsform) {
		if (betalingsform.getNavn() == "Klippekort") {
			throw new IllegalArgumentException("Du kan ikke betale en rundvisning med Klippekort");
		}
		rundvisning.setBetalingsform(betalingsform);
	}

	/**
	 * Finder den samlede pris for en rundvisning
	 * 
	 * @param rundvisning
	 * @return
	 */
	public double rundvisningSamletPris(Rundvisning rundvisning) {
		return rundvisning.getSamletPris();
	}

	/**
	 * Opretter en udlejning og adder den til storage
	 * 
	 * @return
	 */
	public Udlejning createUdlejning() {
		Udlejning udlejning = new Udlejning();
		return udlejning;
	}

	/**
	 * Betaler en udlejning
	 * 
	 * @param udlejning
	 * @param betalingsform
	 */
	public void betalUdlejning(Udlejning udlejning, Betalingsform betalingsform) {
		if (betalingsform.getNavn().equals("Klippekort")) {
			throw new IllegalArgumentException("Du kan ikke betale en udlejning med Klippekort");
		}
		udlejning.setBetalingsform(betalingsform);
		udlejning.setAfleveret(true);
	}

	/**
	 * Hvis navn ikke har en længde over 0 kastes en RuntimeException
	 * 
	 * Opretter en betalingsform og adder den til storage
	 * 
	 * @param navn
	 * @return
	 */
	public Betalingsform createBetalingsform(String navn) throws IllegalArgumentException {
		if (navn.length() == 0)
			throw new IllegalArgumentException("En betalingsform skal have et navn");

		Betalingsform betalingsform = new Betalingsform(navn);
		storage.addBetalingsform(betalingsform);
		return betalingsform;
	}

	/**
	 * Opretter et salg
	 * 
	 * @return
	 */
	public Salg createSalg() {
		Salg salg = new Salg();
		return salg;
	}

	/**
	 * Gemmer et salg i storage
	 * 
	 * @param salg
	 * @param betalingsform
	 */
	public void saveSalg(Salg salg, Betalingsform betalingsform) {
		if (betalingsform.getNavn().equals("Klippekort")) {
			for (Salgslinje sl : salg.getSalgslinjer()) {
				if (!(sl.getProdukt().getProduktgruppe().getNavn().equals("flaske"))
						&& !(sl.getProdukt().getProduktgruppe().getNavn().equals("Fadøl, 40 cl"))) {
					throw new IllegalArgumentException(
							"Kun produkter af flaske/Fadøl gruppen kan betales med Klippekort");
				}
			}
		}
		if (salg instanceof Udlejning) {
			storage.addUdlejning((Udlejning) salg);
		} else {
			storage.addSalg(salg);
			salg.setBetalingsform(betalingsform);
		}
	}

	/**
	 * Opretter en salgslinje for et salg Pre: salg må ikke være null og rabat må
	 * ikke være null
	 * 
	 * @param salg
	 * @param antal
	 * @param rabat
	 * @param pris
	 * @return
	 */
	public Salgslinje createSalgslinje(Salg salg, int antal, Rabat rabat, Pris pris) {
		Pre.require(salg != null && rabat != null);
		Salgslinje salgslinje = salg.createSalgslinje(pris, antal, rabat);
		return salgslinje;
	}

	/**
	 * Sletter en salgslinje fra et salg
	 * 
	 * @param salg
	 * @param salgslinje
	 */
	public void removeSalgslinje(Salg salg, Salgslinje salgslinje) {
		salg.removeSalgslinje(salgslinje);
	}

	/**
	 * Opretter en prisliste
	 * 
	 * Kaster IllegalArgumentException hvis kravene ikke er opfyldt
	 * 
	 * @param navn
	 * @return
	 */
	public Prisliste createPrisliste(String navn) throws IllegalArgumentException {
		if (navn.length() == 0)
			throw new IllegalArgumentException("En prisliste skal have et navn");
		Prisliste prisliste = new Prisliste(navn);
		storage.addPrisliste(prisliste);
		return prisliste;
	}

	/**
	 * Opdatere en prisliste
	 * 
	 * @param prisliste
	 * @param navn
	 */
	public void updatePrisliste(Prisliste prisliste, String navn) throws IllegalArgumentException {
		if (navn.length() == 0) {
			throw new IllegalArgumentException("En prisliste skal have et navn");
		}
		prisliste.setNavn(navn);
	}

	/**
	 * Sletter en prisliste
	 * 
	 * @param prisliste
	 */
	public void deletePrisliste(Prisliste prisliste) {
		storage.removePrisliste(prisliste);
	}

	/**
	 * Opretter en pris for en prisliste Kaster IllegalArgumentException hvis
	 * kravene ikke er opfyldt
	 * 
	 * Pre: prisliste må ikke være null og produkt må ikke være null
	 * 
	 * @param prisliste
	 * @param pris
	 * @param produkt
	 */
	public Pris createPris(Prisliste prisliste, double pris, Produkt produkt) throws IllegalArgumentException {
		Pre.require(prisliste != null && produkt != null);
		if (pris == 0)
			throw new IllegalArgumentException("En pris må ikke være 0");
		Pris prisprodukt = prisliste.CreatePris(pris, produkt);
		return prisprodukt;
	}

	/**
	 * Opdatere en Produktpris
	 * 
	 * @param prisprodukt
	 * @param pris
	 * @param produkt
	 */
	public void updatePris(Pris prisprodukt, int pris, Produkt produkt) throws IllegalArgumentException {
		if (pris == 0)
			throw new IllegalArgumentException("En pris må ikke være 0");
		prisprodukt.setProdukt(produkt);
		prisprodukt.setPris(pris);
	}

	/**
	 * Sletter en pris fra en prisliste
	 * 
	 * @param pris
	 * @param prisliste
	 */
	public void removePris(Pris pris, Prisliste prisliste) {
		prisliste.removePris(pris);
	}

	/**
	 * Opretter ny IngenRabat
	 * 
	 * @return
	 */
	public Rabat createIngenRabat() {
		Rabat r = new IngenRabat();
		return r;
	}

	/**
	 * Opretter en ny PrisRabat
	 * 
	 * @param pris
	 * @return
	 */
	public Rabat createPrisRabat(double pris) throws RuntimeException {
		if (pris == 0) {
			throw new RuntimeException("Ugyldig rabat");
		}
		Rabat r = new PrisRabat(pris);
		return r;
	}

	/**
	 * Opretter en ny ProcentRabat
	 * 
	 * @param procent
	 * @return
	 */
	public Rabat createProcentRabat(double procent) throws RuntimeException {
		if (procent <= 0) {
			throw new RuntimeException("Ugyldig rabat");
		}
		Rabat r = new ProcentRabat(procent);
		return r;
	}

	/**
	 * Returnerer et HashSet af alle produkter
	 * 
	 * @return
	 */

	public HashSet<Produkt> getAllProdukter() {
		return storage.getProdukter();
	}

	/**
	 * Retunerer et Treeset af alle produktgrupper
	 * 
	 * @return
	 */

	public List<Produktgruppe> getAllProduktgrupper() {
		return storage.getProduktgrupper();
	}

	/**
	 * Retunerer en liste af alle prislister
	 * 
	 * @return
	 */
	public List<Prisliste> getAllPrislister() {
		return storage.getPrislister();
	}

	/**
	 * Retunerer en liste af alle salg
	 * 
	 * @return
	 */
	public List<Salg> getAllSalg() {
		return storage.getSalgliste();
	}

	/**
	 * Returnerer en liste af alle rundvisninger sorteret
	 * 
	 * @return
	 */
	public List<Rundvisning> getAllRundvisningerSorted() {
		List<Rundvisning> sorterederundvisninger = new ArrayList<>();
		sorterederundvisninger.addAll(storage.getRundvisninger());
		Collections.sort(sorterederundvisninger);
		return sorterederundvisninger;
	}

	/**
	 * Retunerer en liste af alle udlejninger
	 * 
	 * @return
	 */
	public List<Udlejning> getAllUdlejninger() {
		return storage.getUdlejninger();
	}

	/**
	 * Retunerer en liste af alle betalingsformer
	 * 
	 * @return
	 */
	public List<Betalingsform> getAllBetalingsformer() {
		return storage.getBetalingsformer();
	}

	/**
	 * retunerer en liste af dagens salg
	 * 
	 * @return
	 */
	public List<Salg> getDagensSalg() {
		List<Salg> dagenssolgte = new ArrayList<>();
		for (Salg s : storage.getSalgliste()) {
			if (s.getDato() != null && s.getDato().equals(LocalDate.now())) {
				dagenssolgte.add(s);
			}
		}
		return dagenssolgte;
	}

	/**
	 * Finder antal klip i alt
	 * 
	 * @param startdato
	 * @param slutdato
	 * @return
	 */
	public int antalKlipiAlt(LocalDate startdato, LocalDate slutdato) {
		if (startdato.isAfter(slutdato))
			throw new IllegalArgumentException("En startdato må ikke være efter en slutdato");
		int klip = 0;
		for (Salg s : storage.getSalgliste()) {
			for (Salgslinje sl : s.getSalgslinjer()) {
				if (sl.getProdukt().getNavn().equals("Klippekort")
						&& ((s.getDato() != null && s.getDato().isBefore(slutdato) && s.getDato().isAfter(startdato))
								|| (s.getDato() != null && s.getDato().equals(startdato)
										|| s.getDato() != null && s.getDato().equals(slutdato)))) {
					klip = klip + 4 * sl.getAntal();
				}
			}
		}
		return klip;
	}

	/**
	 * Finder antallet af klip som er brugt i en periode
	 * 
	 * @param startdato
	 * @param slutdato
	 * @return
	 */
	public int antalBrugteKlip(LocalDate startdato, LocalDate slutdato) {
		int klip = 0;
		for (Salg s : storage.getSalgliste()) {
			if (s.getBetalingsform() != null && s.getBetalingsform().getNavn().equals("Klippekort")
					&& ((s.getDato() != null && s.getDato().isBefore(slutdato) && s.getDato().isAfter(startdato))
							|| (s.getDato() != null && s.getDato().equals(startdato)
									|| s.getDato() != null && s.getDato().equals(slutdato)))) {
				for (Salgslinje sl : s.getSalgslinjer()) {
					klip = klip + sl.getAntal();
				}
			}
		}
		return klip;
	}

	/**
	 * Returnerer en liste over solgte klippekort
	 * 
	 * @param startdato
	 * @param slutdato
	 * @return
	 */
	public List<Salg> getSalgafklippekort(LocalDate startdato, LocalDate slutdato) {
		List<Salg> klipsalg = new ArrayList<>();
		for (Salg s : storage.getSalgliste()) {
			for (Salgslinje sl : s.getSalgslinjer()) {
				if (sl.getProdukt().getNavn().equals("Klippekort")
						&& ((s.getDato() != null && s.getDato().isBefore(slutdato) && s.getDato().isAfter(startdato))
								|| (s.getDato() != null && s.getDato().equals(startdato)
										|| s.getDato() != null && s.getDato().equals(slutdato)))) {
					klipsalg.add(s);
					break;

				}
			}
		}
		return klipsalg;
	}

	/**
	 * Returnerer en liste over salg som er betalt med klippekort
	 * 
	 * @param startdato
	 * @param slutdato
	 * @return
	 */
	public List<Salg> getBetalteSalgmedKlippekort(LocalDate startdato, LocalDate slutdato) {
		List<Salg> klipsalg = new ArrayList<>();
		for (Salg s : storage.getSalgliste()) {
			if (s.getBetalingsform() != null && s.getBetalingsform().getNavn().equals("Klippekort")
					&& ((s.getDato() != null && s.getDato().isBefore(slutdato) && s.getDato().isAfter(startdato))
							|| (s.getDato() != null && s.getDato().equals(startdato)
									|| s.getDato() != null && s.getDato().equals(slutdato)))) {

				klipsalg.add(s);
			}
		}
		return klipsalg;
	}

	/**
	 * Finder antal klip i omløb
	 * 
	 * @param fra
	 * @param til
	 * @return
	 */
	public int getKlipIOmløb(LocalDate fra, LocalDate til) {
		return antalKlipiAlt(fra, til) - antalBrugteKlip(fra, til);
	}

	/**
	 * Finder antallet af klip i omløb
	 * 
	 * @return
	 */
	public int getAlleklipIomløb() {
		int brugteklip = 0;
		int solgteklip = 0;
		for (Salg s : storage.getSalgliste()) {
			if (s.getBetalingsform() != null && s.getBetalingsform().getNavn().equals("Klippekort")) {
				for (Salgslinje sl : s.getSalgslinjer()) {
					brugteklip = brugteklip + sl.getAntal();
				}
			}
		}

		for (Salg s : storage.getSalgliste()) {
			for (Salgslinje sl : s.getSalgslinjer()) {
				if (sl.getProdukt().getNavn().equals("Klippekort")) {
					solgteklip = solgteklip + 4 * sl.getAntal();
				}
			}
		}
		return solgteklip - brugteklip;
	}

	/**
	 * Returnerer brugbare betalingsformer til betaling af en rundvisning
	 * 
	 * @return
	 */
	public List<Betalingsform> rundvisningsBetalingsformer() {
		List<Betalingsform> betalingsformer = new ArrayList<>();
		for (Betalingsform b : storage.getBetalingsformer()) {
			if (!b.getNavn().equals("Klippekort") && !b.getNavn().equals("Regning"))
				betalingsformer.add(b);
		}
		return betalingsformer;
	}

	/**
	 * Returnerer brugbare betalingsformer til betaling af en udlejning
	 * 
	 * @return
	 */
	public List<Betalingsform> udlejningsbetalingsformer() {
		List<Betalingsform> betalingsformer = new ArrayList<>();
		for (Betalingsform b : storage.getBetalingsformer()) {
			if (!b.getNavn().equals("Klippekort")) {
				betalingsformer.add(b);
			}
		}
		return betalingsformer;
	}

	/**
	 * Viser alle fremtidge rundvisninger
	 * 
	 * @return
	 */
	public List<Rundvisning> fremtidigeRundvisninger() {
		List<Rundvisning> planlagterundvisninger = new ArrayList<>();
		for (Rundvisning r : storage.getRundvisninger()) {
			if (r.getDato().equals(LocalDate.now()) || r.getDato().isAfter(LocalDate.now())) {
				planlagterundvisninger.add(r);
			}
		}
		return planlagterundvisninger;
	}

	/**
	 * Returnerer alle priserne fra en prisliste
	 * 
	 * @param prisliste
	 * @return
	 */
	public List<Pris> AllePriserfraPrisliste(Prisliste prisliste) {
		return prisliste.getPriser();
	}

	/**
	 * Viser alle salg fra en specifik prisliste og i en bestemt periode, hvis dette
	 * er valgt
	 * 
	 * @param prisliste
	 * @param datoFra
	 * @param datoTil
	 * @return
	 */
	public List<Salg> alleSalgStatistik(Prisliste prisliste, LocalDate datoFra, LocalDate datoTil) {
		List<Salg> salgsliste = new ArrayList<>();
		for (Salg s : storage.getSalgliste()) {
			for (Salgslinje sl : s.getSalgslinjer()) {
				Pris p = sl.getProdukPris();
				if (p.getPrisliste().equals(prisliste)) {
					// hvis der er valgt en dato
					if (datoFra != null && datoTil != null) {
						if (datoFra.isAfter(datoTil) || datoTil.isBefore(datoFra)) {
							throw new IllegalArgumentException("Ugyldig periode");
						} else {
							if (s.getDato().isBefore(datoTil) && s.getDato().isAfter(datoFra)
									|| s.getDato().equals(datoFra) || s.getDato().equals(datoTil)) {
								salgsliste.add(s);
								break;
							}
						}

					} // hvis der ikke er valgt en dato
					else {
						salgsliste.add(s);
						break;
					}
				}
			}
		}
		return salgsliste;
	}

	/**
	 * Viser antal x produkter på lager
	 * 
	 * @return
	 */
	public List<String> lagerStatus() {
		List<String> result = new ArrayList<>();
		String s;
		for (Produkt p : storage.getProdukter()) {
			s = p.getAntalPåLager() + "x, " + p.getNavn();
			result.add(s);
		}
		Collections.sort(result);
		return result;
	}

	/**
	 * Checker lagerstatus for et produkt
	 * 
	 * @param produkt
	 * @param antal
	 * @return
	 */
	public boolean checkAntalPåLager(Produkt produkt, int antal) {
		if (produkt.getAntalPåLager() >= antal) {
			return true;
		}
		return false;
	}

	/**
	 * Viser kun sampakning ud af alle produktgrupper
	 * 
	 * @return
	 */

	public Produktgruppe getSampkningsProduktGruppe() {
		for (Produktgruppe pg : Controller.getController().getAllProduktgrupper()) {
			if (pg.getNavn().equals("Sampakninger")) {
				return pg;
			}
		}
		return null;

	}

	/**
	 * Viser hvor mange øl der er solgt fra gaveæskerne
	 * 
	 * @return
	 */
	public List<String> ølSolgtFraGaveæsker() {
		int antal = 0;
		List<String> produkter = new ArrayList<>();
		List<String> result = new ArrayList<>();
		for (Salg s : getAllSalg()) {
			for (Salgslinje sl : s.getSalgslinjer()) {
				Produkt p = sl.getProdukt();
				if (p instanceof Gaveæske) {
					for (Produkt pp : ((Gaveæske) p).getProdukter()) {
						if (pp.getProduktgruppe().getNavn().equals("flaske")) {
							produkter.add(pp.getNavn());
						}
					}
				}
			}
		}
		Collections.sort(produkter);
		for (int i = 0; i < produkter.size(); i++) {
			antal++;
			if (i + 1 == produkter.size()) {
				result.add(produkter.get(i) + " " + antal);
			} else {
				if (!produkter.get(i).equals(produkter.get(i + 1))) {
					result.add(produkter.get(i) + " " + antal);
					antal = 0;
				}
			}
		}

		return result;
	}

	/**
	 * Viser alle salgsprodukter
	 * 
	 * @return
	 */

	public List<Produktgruppe> getSalgssprodukter() {
		List<Produktgruppe> grupper = new ArrayList<>();
		for (Produktgruppe pg : Controller.getController().getAllProduktgrupper()) {
			if (!pg.isUdlejningsprodukt()) {
				grupper.add(pg);
			}
		}
		return grupper;
	}

	/**
	 * Viser alle klippekort produkter
	 * 
	 * @return
	 */

	public List<Produktgruppe> getKlippekortprodukter() {
		List<Produktgruppe> grupper = new ArrayList<>();
		for (Produktgruppe pg : Controller.getController().getAllProduktgrupper()) {
			if (pg.getNavn().equals("Klippekort")) {
				grupper.add(pg);
			}
		}
		return grupper;
	}

	/**
	 * Viser alle udlejningsprodukter
	 * 
	 * @return
	 */

	public List<Produktgruppe> getUdlejningsprodukter() {
		List<Produktgruppe> grupper = new ArrayList<>();
		for (Produktgruppe pg : Controller.getController().getAllProduktgrupper()) {
			if (pg.isUdlejningsprodukt()) {
				grupper.add(pg);
			}
		}
		return grupper;
	}

	/**
	 * Viser antal solgte øl i procent
	 * 
	 * @return
	 */

	public List<String> procentSolgteproukter() {
		double antal = 0;
		List<String> produkter = new ArrayList<>();
		List<String> result = new ArrayList<>();
		for (Salg s : getAllSalg()) {
			for (Salgslinje sl : s.getSalgslinjer()) {
				Produkt p = sl.getProdukt();
				for (int i = 0; i < sl.getAntal(); i++) {
					produkter.add(p.getNavn());
				}
			}
		}
		Collections.sort(produkter);
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		for (int i = 0; i < produkter.size(); i++) {
			antal++;
			if (i + 1 == produkter.size()) {
				antal = antal / produkter.size() * 100;
				result.add("[" + produkter.get(i) + "] " + numberFormat.format(antal) + " %");
			} else {
				if (!produkter.get(i).equals(produkter.get(i + 1))) {
					antal = antal / produkter.size() * 100;
					result.add("[" + produkter.get(i) + "] " + numberFormat.format(antal) + " %");
					antal = 0;
				}
			}
		}
		return result;

	}

	/**
	 * Viser de ikke afleverede udlejninger
	 * 
	 * @return
	 */

	public List<Udlejning> ikkeAfleveredeUdlejninger() {
		List<Udlejning> result = new ArrayList<>();
		for (Udlejning u : this.getAllUdlejninger()) {
			if (!u.isAfleveret()) {
				result.add(u);
			}
		}
		return result;
	}

	/**
	 * Returnere alle afleverede udlejninger
	 * 
	 * @return afleverede udlejninger
	 */
	public List<Udlejning> afleveredeUdlejninger() {
		List<Udlejning> result = new ArrayList<>();
		for (Udlejning u : this.getAllUdlejninger()) {
			if (u.isAfleveret()) {
				result.add(u);
			}
		}
		return result;
	}

	/**
	 * Opretter alle data som skal være i systemet fra start
	 */
	public void initStorage() {
		Produktgruppe flaske = createProduktgruppe("flaske", false);
		Produkt klosterbryg = createProdukt(flaske, "Klosterbryg", 100);
		Produkt SGB = createProdukt(flaske, "Sweet Georgia Brown", 100);
		Produkt EP = createProdukt(flaske, "Extra Pilsner", 100);
		Produkt celebration = createProdukt(flaske, "Celebration", 100);
		Produkt blondie = createProdukt(flaske, "Blondie", 100);
		Produkt forårsbryg = createProdukt(flaske, "Forårsbryg", 100);
		Produkt IPA = createProdukt(flaske, "India Pale Ale", 100);
		Produkt julebryg = createProdukt(flaske, "Julebryg", 100);
		Produkt juletønden = createProdukt(flaske, "Juletønden", 100);
		Produkt OSA = createProdukt(flaske, "Old Strong Ale", 100);
		Produkt fregatten = createProdukt(flaske, "Fregatten Jylland", 100);
		Produkt imperial = createProdukt(flaske, "Imperial Stout", 100);
		Produkt tribute = createProdukt(flaske, "Tribute", 100);
		Produkt BM = createProdukt(flaske, "Black Monster", 100);

		Produktgruppe fadøl40cl = createProduktgruppe("Fadøl, 40 cl", false);
		Produkt kp = createProdukt(fadøl40cl, "Klosterbryg", 100);
		Produkt jazz = createProdukt(fadøl40cl, "Jazz Classic", 100);
		Produkt ep = createProdukt(fadøl40cl, "Extra Pilsner", 100);
		Produkt cele = createProdukt(fadøl40cl, "Celebration", 100);
		Produkt blond = createProdukt(fadøl40cl, "Blondie", 100);
		Produkt forår = createProdukt(fadøl40cl, "Forårsbryg", 100);
		Produkt ipa = createProdukt(fadøl40cl, "India Pale Ale", 100);
		Produkt juleb = createProdukt(fadøl40cl, "Julebryg", 100);
		Produkt is = createProdukt(fadøl40cl, "Imperial Stout", 100);
		Produkt special = createProdukt(fadøl40cl, "Special", 100);
		Produkt æblebrus = createProdukt(fadøl40cl, "Æblebrus", 100);
		Produkt chips = createProdukt(fadøl40cl, "Chips", 100);
		Produkt peanuts = createProdukt(fadøl40cl, "Peanuts", 100);
		Produkt cola = createProdukt(fadøl40cl, "Cola", 100);
		Produkt niko = createProdukt(fadøl40cl, "Nikoline", 100);
		Produkt up = createProdukt(fadøl40cl, "7-Up", 100);
		Produkt vand = createProdukt(fadøl40cl, "vand", 100);

		Produktgruppe spiritus = createProduktgruppe("Spiritus", false);
		Produkt soa = createProdukt(spiritus, "Spirit of Aarhus", 100);
		Produkt soamp = createProdukt(spiritus, "SOA med pind", 100);
		Produkt whisky = createProdukt(spiritus, "Whisky", 100);
		Produkt loa = createProdukt(spiritus, "Liquor of Aarhus", 100);

		Produktgruppe fustage = createProduktgruppe("Fustage", true);
		Produkt fkp = createPantProdukt(fustage, "Klosterbryg, 20 liter", 100, 200);
		Produkt fjazz = createPantProdukt(fustage, "Jazz Classic, 25 liter", 100, 200);
		Produkt fep = createPantProdukt(fustage, "Extra Pilsner, 25 liter", 100, 200);
		Produkt fcele = createPantProdukt(fustage, "Celebration, 20 liter", 100, 200);
		Produkt fblond = createPantProdukt(fustage, "Blondie, 25 liter", 100, 200);
		Produkt fforår = createPantProdukt(fustage, "Forårsbryg, 20 liter", 100, 200);
		Produkt fipa = createPantProdukt(fustage, "India Pale Ale, 20 liter", 100, 200);
		Produkt fjule = createPantProdukt(fustage, "Julebryg, 20 liter", 100, 200);
		Produkt fis = createPantProdukt(fustage, "Imperial Stout", 100, 200);

		Produktgruppe beklædning = createProduktgruppe("Beklædning", false);
		Produkt tshirt = createProdukt(beklædning, "t-shirt", 100);
		Produkt polo = createProdukt(beklædning, "polo", 100);
		Produkt cap = createProdukt(beklædning, "cap", 100);

		Produktgruppe anlæg = createProduktgruppe("Anlæg", true);
		Produkt enhane = createProdukt(anlæg, "1-hane", 100);
		Produkt tohaner = createProdukt(anlæg, "2-haner", 100);
		Produkt bar = createProdukt(anlæg, "Bar med flere haner", 100);
		Produkt levering = createProdukt(anlæg, "Levering", 100);
		Produkt krus = createProdukt(anlæg, "Krus", 100);

		Produktgruppe glas = createProduktgruppe("Glas", false);
		Produkt glasp = createProdukt(glas, "glas", 100);

		Produktgruppe kulsyre = createProduktgruppe("Kulsyre", false);
		Produkt firekg = createPantProdukt(kulsyre, "Kulsyre, 4kg", 5, 1000);
		Produkt sekskg = createPantProdukt(kulsyre, "Kulsyre, 6kg", 5, 1000);
		Produkt tikg = createPantProdukt(kulsyre, "Kulsyre, 10kg", 5, 1000);

		Produktgruppe malt = createProduktgruppe("Malt", false);
		Produkt maltsæk = createProdukt(malt, "25 kg sæk", 25);

		Produktgruppe sampakninger = createProduktgruppe("Sampakninger", false);
		Gaveæske g1 = createGaveæske("Gaveæske 2 klosterbryg, 2 glas", 100, 2, 2, sampakninger);
		g1.addProdukt(klosterbryg);
		g1.addProdukt(klosterbryg);
		g1.addProdukt(glasp);
		g1.addProdukt(glasp);
		Gaveæske g2 = createGaveæske("Gaveæske med 4 klosterbryg", 100, 0, 4, sampakninger);
		g2.addProdukt(klosterbryg);
		g2.addProdukt(klosterbryg);
		g2.addProdukt(klosterbryg);
		g2.addProdukt(klosterbryg);

		Gaveæske g3 = createGaveæske("Trækasse med 6 øl", 100, 0, 6, sampakninger);
		g3.addProdukt(EP);
		g3.addProdukt(celebration);
		g3.addProdukt(klosterbryg);
		g3.addProdukt(klosterbryg);
		g3.addProdukt(klosterbryg);
		g3.addProdukt(forårsbryg);

		Gaveæske g4 = createGaveæske("Gavekurv 6 øl, 2 glas", 100, 2, 6, sampakninger);
		g4.addProdukt(klosterbryg);
		g4.addProdukt(klosterbryg);
		g4.addProdukt(klosterbryg);
		g4.addProdukt(klosterbryg);
		g4.addProdukt(klosterbryg);
		g4.addProdukt(klosterbryg);
		g4.addProdukt(glasp);
		g4.addProdukt(glasp);

		Gaveæske g5 = createGaveæske("Trækasse 6 øl, 6 glas", 100, 6, 6, sampakninger);
		g5.addProdukt(klosterbryg);
		g5.addProdukt(klosterbryg);
		g5.addProdukt(klosterbryg);
		g5.addProdukt(klosterbryg);
		g5.addProdukt(klosterbryg);
		g5.addProdukt(klosterbryg);
		g5.addProdukt(glasp);
		g5.addProdukt(glasp);
		g5.addProdukt(glasp);
		g5.addProdukt(glasp);
		g5.addProdukt(glasp);
		g5.addProdukt(glasp);

		Gaveæske g6 = createGaveæske("Trækasse 12 øl", 100, 0, 12, sampakninger);
		g6.addProdukt(klosterbryg);
		g6.addProdukt(klosterbryg);
		g6.addProdukt(klosterbryg);
		g6.addProdukt(klosterbryg);
		g6.addProdukt(klosterbryg);
		g6.addProdukt(klosterbryg);
		g6.addProdukt(klosterbryg);
		g6.addProdukt(klosterbryg);
		g6.addProdukt(klosterbryg);
		g6.addProdukt(klosterbryg);
		g6.addProdukt(klosterbryg);
		g6.addProdukt(klosterbryg);

		Gaveæske g7 = createGaveæske("Papkasse 12 øl", 100, 0, 12, sampakninger);
		g7.addProdukt(klosterbryg);
		g7.addProdukt(klosterbryg);
		g7.addProdukt(klosterbryg);
		g7.addProdukt(klosterbryg);
		g7.addProdukt(klosterbryg);
		g7.addProdukt(klosterbryg);
		g7.addProdukt(klosterbryg);
		g7.addProdukt(klosterbryg);
		g7.addProdukt(klosterbryg);
		g7.addProdukt(klosterbryg);
		g7.addProdukt(klosterbryg);
		g7.addProdukt(klosterbryg);

		Betalingsform kontant = createBetalingsform("Kontant");
		Betalingsform dankort = createBetalingsform("Dankort");
		Betalingsform kk = createBetalingsform("Klippekort");
		Betalingsform mobilepay = createBetalingsform("MobilePay");
		Betalingsform regning = createBetalingsform("Regning");

		createRundvisning("EAA rundvisning", LocalTime.of(12, 30), LocalTime.of(14, 30), LocalDate.of(2019, 03, 28),
				100, 12345678, kontant, 50, true);

		createRundvisning("Bibliotek", LocalTime.of(10, 30), LocalTime.of(12, 30), LocalDate.of(2019, 05, 03), 100,
				87654321, kontant, 30, false);

		createRundvisning("Skoletur", LocalTime.of(14, 00), LocalTime.of(15, 00), LocalDate.of(2019, 07, 05), 100,
				12341234, dankort, 30, true);

		createRundvisning("Uni rundvisning", LocalTime.of(18, 00), LocalTime.of(20, 00), LocalDate.of(2019, 10, 10),
				100, 99887766, mobilepay, 20, true);

		Prisliste pl1 = createPrisliste("Fredagsbar");
		// Flaske
		Pris p1 = pl1.CreatePris(50, klosterbryg);
		pl1.CreatePris(50, SGB);
		pl1.CreatePris(50, EP);
		pl1.CreatePris(50, celebration);
		pl1.CreatePris(50, blondie);
		pl1.CreatePris(50, forårsbryg);
		pl1.CreatePris(50, IPA);
		pl1.CreatePris(50, julebryg);
		pl1.CreatePris(50, juletønden);
		pl1.CreatePris(50, OSA);
		pl1.CreatePris(50, fregatten);
		pl1.CreatePris(50, imperial);
		pl1.CreatePris(50, tribute);
		pl1.CreatePris(50, BM);

		// Fadøl
		pl1.CreatePris(30, kp);
		pl1.CreatePris(30, jazz);
		pl1.CreatePris(30, ep);
		pl1.CreatePris(30, cele);
		pl1.CreatePris(30, blond);
		pl1.CreatePris(30, forår);
		pl1.CreatePris(30, ipa);
		pl1.CreatePris(30, juleb);
		pl1.CreatePris(30, is);
		pl1.CreatePris(30, special);
		pl1.CreatePris(15, æblebrus);
		pl1.CreatePris(10, chips);
		pl1.CreatePris(10, peanuts);
		pl1.CreatePris(15, cola);
		pl1.CreatePris(15, niko);
		pl1.CreatePris(15, up);
		pl1.CreatePris(10, vand);

		// Spiritus
		pl1.CreatePris(300, soa);
		pl1.CreatePris(350, soamp);
		pl1.CreatePris(500, whisky);
		pl1.CreatePris(175, loa);

		// Beklædning
		pl1.CreatePris(70, tshirt);
		pl1.CreatePris(100, polo);
		pl1.CreatePris(30, cap);

		// Sampakninger
		pl1.CreatePris(100, g1);
		pl1.CreatePris(130, g2);
		pl1.CreatePris(240, g3);
		pl1.CreatePris(250, g4);
		pl1.CreatePris(290, g5);
		pl1.CreatePris(390, g6);
		pl1.CreatePris(360, g7);

		// Kulsyre
		pl1.CreatePris(200, firekg);
		pl1.CreatePris(400, sekskg);
		pl1.CreatePris(600, tikg);

		Prisliste pl2 = createPrisliste("Butik");
		// Flaske
		pl2.CreatePris(36, klosterbryg);
		pl2.CreatePris(36, SGB);
		pl2.CreatePris(36, EP);
		pl2.CreatePris(36, celebration);
		pl2.CreatePris(36, blondie);
		pl2.CreatePris(36, forårsbryg);
		pl2.CreatePris(36, IPA);
		pl2.CreatePris(36, julebryg);
		pl2.CreatePris(36, juletønden);
		pl2.CreatePris(36, OSA);
		pl2.CreatePris(36, fregatten);
		pl2.CreatePris(36, imperial);
		pl2.CreatePris(36, tribute);
		pl2.CreatePris(36, BM);

		// Spiritus
		pl2.CreatePris(300, soa);
		pl2.CreatePris(350, soamp);
		pl2.CreatePris(500, whisky);
		pl2.CreatePris(175, loa);

		// Fustage
		pl2.CreatePris(775, fkp);
		pl2.CreatePris(625, fjazz);
		pl2.CreatePris(575, fep);
		pl2.CreatePris(775, fcele);
		pl2.CreatePris(700, fblond);
		pl2.CreatePris(775, fforår);
		pl2.CreatePris(775, fipa);
		pl2.CreatePris(775, fjule);
		pl2.CreatePris(775, fis);

		// Beklædning
		pl2.CreatePris(70, tshirt);
		pl2.CreatePris(100, polo);
		pl2.CreatePris(30, cap);

		// Anlæg
		pl2.CreatePris(250, enhane);
		Pris p2 = pl2.CreatePris(400, tohaner);
		pl2.CreatePris(500, bar);
		pl2.CreatePris(500, levering);
		pl2.CreatePris(60, krus);

		// Glas
		pl2.CreatePris(15, glasp);

		// Sampakninger
		pl2.CreatePris(100, g1);
		pl2.CreatePris(130, g2);
		pl2.CreatePris(240, g3);
		Pris gaveæske1 = pl2.CreatePris(250, g1);
		Pris gaveæske3 = pl2.CreatePris(290, g3);
		pl2.CreatePris(390, g6);
		pl2.CreatePris(360, g7);

		// Kulsyre
		pl2.CreatePris(200, firekg);
		pl2.CreatePris(400, sekskg);
		pl2.CreatePris(600, tikg);

		// Malt
		pl2.CreatePris(300, maltsæk);

		Rabat r1 = createIngenRabat();

		Produktgruppe klippekort = createProduktgruppe("Klippekort", false);

		Prisliste pl3 = createPrisliste("Klippekort");

		Produkt klippekorte = createProdukt(klippekort, "Klippekort", 100);

		Pris k1 = pl3.CreatePris(100, klippekorte);

		Salg s1 = createSalg();

		s1.createSalgslinje(p1, 3, r1);

		s1.setDato(LocalDate.of(2019, 03, 15));

		saveSalg(s1, dankort);

		Salg s2 = createSalg();

		s2.createSalgslinje(k1, 2, r1);

		s2.createSalgslinje(k1, 1, r1);

		s2.setDato(LocalDate.of(2019, 03, 04));

		saveSalg(s2, dankort);

		Salg s3 = createSalg();

		s3.createSalgslinje(p1, 5, r1);

		s3.setDato(LocalDate.of(2019, 03, 15));

		saveSalg(s3, kk);

		Salg s4 = createSalg();
		s4.createSalgslinje(gaveæske1, 1, r1);

		saveSalg(s4, dankort);

		Salg s5 = createSalg();
		s5.createSalgslinje(gaveæske1, 1, r1);

		saveSalg(s5, regning);

		Salg s6 = createSalg();
		s6.createSalgslinje(gaveæske3, 1, r1);

		saveSalg(s6, dankort);

		Salg s7 = createSalg();
		s7.createSalgslinje(gaveæske3, 1, r1);

		saveSalg(s7, dankort);

		Udlejning u1 = createUdlejning();
		u1.createSalgslinje(p2, 1, r1);
		u1.setNavn("Jens Jensen");
		u1.setDato(LocalDate.now());
		u1.setTlf("12345678");
		u1.setUdlejningsDato(LocalDate.of(2019, 10, 10));
		u1.setAfleveringsDato(LocalDate.of(2019, 10, 30));

		saveSalg(u1, dankort);

		Udlejning u2 = createUdlejning();
		u2.createSalgslinje(p2, 1, r1);
		u2.setNavn("Hans Hansen");
		u2.setDato(LocalDate.now());
		u2.setTlf("87654321");
		u2.setUdlejningsDato(LocalDate.of(2019, 02, 01));
		u2.setAfleveringsDato(LocalDate.of(2019, 02, 20));
		u2.setAfleveret(true);

		saveSalg(u2, dankort);

		Udlejning u3 = createUdlejning();
		u3.createSalgslinje(p2, 1, r1);
		u3.setNavn("Ole Olesen");
		u3.setDato(LocalDate.now());
		u3.setTlf("12344321");
		u3.setUdlejningsDato(LocalDate.of(2019, 12, 10));
		u3.setAfleveringsDato(LocalDate.of(2019, 12, 28));

		saveSalg(u3, dankort);

		Udlejning u4 = createUdlejning();
		u4.createSalgslinje(p2, 1, r1);
		u4.setNavn("Marie Mariesen");
		u4.setDato(LocalDate.now());
		u4.setTlf("12341234");
		u4.setUdlejningsDato(LocalDate.of(2019, 10, 01));
		u4.setAfleveringsDato(LocalDate.of(2019, 10, 10));

		saveSalg(u4, dankort);

		Udlejning u5 = createUdlejning();
		u5.createSalgslinje(p2, 1, r1);
		u5.setNavn("Sofie Sofiesen");
		u5.setDato(LocalDate.now());
		u5.setTlf("98766789");
		u5.setUdlejningsDato(LocalDate.of(2019, 03, 05));
		u5.setAfleveringsDato(LocalDate.of(2019, 04, 05));
		u5.setAfleveret(true);

		saveSalg(u5, dankort);

	}

	/**
	 * Serializable storage
	 */

	public void loadStorage() {
		try (FileInputStream fileIn = new FileInputStream("AarhusBryg")) {
			try (ObjectInputStream in = new ObjectInputStream(fileIn);) {
				storage = (Storage) in.readObject();

				System.out.println("Storage loaded fra AarhusBryg");
			} catch (ClassNotFoundException ex) {
				System.out.println("Error loading storage object.");
				throw new RuntimeException(ex);
			}
		} catch (IOException ex) {
			System.out.println("Error loading storage object.");
			throw new RuntimeException(ex);
		}

	}

	/**
	 * Gemmer storage
	 */

	public void saveStorage() {
		try (FileOutputStream fileOut = new FileOutputStream("AarhusBryg")) {
			try (ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
				out.writeObject(storage);
				System.out.println("Storage gemt i AarhusBryg");
			}
		} catch (IOException ex) {
			System.out.println("Error saving storage object.");
			throw new RuntimeException(ex);
		}
	}

}
