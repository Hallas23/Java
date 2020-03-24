package application.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import application.model.Betalingsform;
import application.model.Gaveæske;
import application.model.PantProdukt;
import application.model.Pris;
import application.model.Prisliste;
import application.model.Produkt;
import application.model.Produktgruppe;
import application.model.Rabat;
import application.model.Rundvisning;
import application.model.Salg;
import application.model.Salgslinje;
import application.model.Udlejning;
import storage.Storage;

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

	public Produktgruppe createProduktgruppe(String navn, boolean udlejningsprodukt) throws RuntimeException {
		if (navn.length() < 0)
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
	 * @param produkt
	 * @param navn
	 * @param antalPålager
	 * @param ptype
	 */

	public void updateProduktgruppe(Produktgruppe produktgruppe, String navn, boolean udlejningsprodukt) {
		produktgruppe.setNavn(navn);
		produktgruppe.setUdlejningsprodukt(udlejningsprodukt);
	}

	/**
	 * Hvis navn ikke har en længde over 0 kastes en RuntimeException Hvis Hvis
	 * lagerAntal er under 0 kastes en runtime exception ptype opretter et nyt
	 * produktet bliver oprettet, produktet bliver addet til storage og bliver
	 * retuneret Pre: ptype må ikke være null
	 *
	 * @param ptype
	 * @param navn
	 * @param lagerantal
	 */

	public Produkt createProdukt(Produktgruppe ptype, String navn, int lagerAntal) throws RuntimeException {
		Pre.require(ptype != null);
		if (navn.length() < 0)
			throw new IllegalArgumentException("Et produkt skal have et navn");
		if (lagerAntal < 0)
			throw new IllegalArgumentException("Et produkt kan ikke have et lager antal under 0");

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

	public void updateProdukt(Produkt produkt, String navn, int antalPålager, Produktgruppe ptype) {
		produkt.setNavn(navn);
		produkt.setAntalPåLager(antalPålager);
		produkt.getProduktgruppe().removeProdukt(produkt);
		ptype.addProdukt(produkt);
	}

	/**
	 * Hvis navn ikke har en længde over 0 kastes en RuntimeException Hvis Hvis
	 * lagerAntal er under 0 kastes en runtime exception ptype opretter et nyt Hvis
	 * pantpris er under 0 kastes en runtime exception ptype opretter et nyt
	 * produktet bliver oprettet, produktet bliver addet til storage og bliver
	 * retuneret Pre: ptype må ikke være null
	 *
	 * @param ptype
	 * @param navn
	 * @param lagerantal
	 * @param pantpris
	 */

	public PantProdukt createPantProdukt(Produktgruppe ptype, String navn, int lagerAntal, int pantpris)
			throws RuntimeException {
		Pre.require(ptype != null);
		if (navn.length() < 0)
			throw new IllegalArgumentException("Et produkt skal have et navn");
		if (lagerAntal < 0)
			throw new IllegalArgumentException("Et produkt kan ikke have et lager antal under 0");
		if (pantpris <= 0)
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
			int pantpris) {
		produkt.setNavn(navn);
		produkt.setAntalPåLager(antalPålager);
		produkt.setPantPris(pantpris);
		produkt.getProduktgruppe().removeProdukt(produkt);
		ptype.addProdukt(produkt);
	}

	/**
	 * Hvis navn ikke har en længde over 0 kastes en RuntimeException Hvis
	 * lagerAntal er under 0 kastes en runtime exception Hvis glasantal er under 0
	 * kastes en runtime exception Hvis flaskeantal er under 0 kastes en runtime
	 * exception gaveæsken bliver oprettet, gaveæsken bliver addet til storage og
	 * bliver retuneret Pre: ptype må ikke være null
	 *
	 * @param ptype
	 * @param navn
	 * @param lagerantal
	 * @param glasantal
	 * @param flaskeantal
	 */

	public Gaveæske createGaveæske(String navn, int lagerAntal, int glasAntal, int flaskeAntal, Produktgruppe ptype)
			throws RuntimeException {
		Pre.require(ptype != null);
		if (navn.length() < 0)
			throw new IllegalArgumentException("En gaveæske skal have et navn");
		if (lagerAntal < 0)
			throw new IllegalArgumentException("En gaveæske kan ikke have et lager antal under 0");
		if (glasAntal < 0)
			throw new IllegalArgumentException("En gaveæske kan ikke have et glas antal under 0");
		if (flaskeAntal < 0)
			throw new IllegalArgumentException("En gaveæske kan ikke have et flaske antal under 0");

		Gaveæske gaveæske = ptype.CreateGaveæske(navn, lagerAntal, glasAntal, flaskeAntal);
		storage.addProdukt(gaveæske);
		return gaveæske;
	}

	/**
	 * OpdaterGaveæske
	 * 
	 * @param produkt
	 * @param navn
	 * @param antalPålager
	 * @param flaskeantal
	 * @param glasantal
	 * @param ptype
	 */

	public void updateGaveæske(Gaveæske gaveæske, String navn, int antalPålager, int flaskeantal, int glasantal,
			Produktgruppe ptype) {
		gaveæske.setNavn(navn);
		gaveæske.setAntalPåLager(antalPålager);
		gaveæske.setFlaskeAntal(flaskeantal);
		gaveæske.setGlasAntal(glasantal);
		gaveæske.getProduktgruppe().removeProdukt(gaveæske);
		ptype.addProdukt(gaveæske);

	}

	public void addToGaveæske(Gaveæske gaveæske, Produkt produkt) {
		gaveæske.addProdukt(produkt);
	}

	public void removeFromGaveæske(Gaveæske gaveæske, Produkt produkt) {
		gaveæske.removeProdukt(produkt);
	}

	/**
	 * Hvis beksrivelse ikke har en længde over 0 kastes en RuntimeException Hvis
	 * slutTid er før startTid kastes en RuntimeException Hvis prisPrPerson er
	 * lavere end 0 kastes en RuntimeException
	 * 
	 * Pre: dato må ikke være null
	 * 
	 * Metoden opretter en rundvisning og adder den til storage
	 *
	 * @param beksrivelse
	 * @param starTtid
	 * @param slutTid
	 * @param prisPrPerson
	 * @param kontaktNr
	 * @param betalingsform
	 * @param antalPersoner
	 * @param studieRabat
	 * 
	 */

	public Rundvisning createRundvisning(String beskrivelse, LocalDateTime startTid, LocalDateTime slutTid,
			double prisPrPerson, int kontaktNr, Betalingsform betalingsform, int antalPersoner, boolean studieRabat)
			throws RuntimeException {

		if (beskrivelse.length() < 0)
			throw new IllegalArgumentException("En rundvisning skal have en beskrielse");
		if (slutTid.isBefore(startTid))
			throw new IllegalArgumentException("En start tid skal være før en slut tid");
		if (prisPrPerson < 0)
			throw new IllegalArgumentException("En prisPrPerson må ikke være negativ");

		Rundvisning rundvisning = new Rundvisning(beskrivelse, startTid, slutTid, prisPrPerson, kontaktNr,
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
	 * Opdater Rundvisning
	 * 
	 * @param rundvisning
	 * @param beskrivelse
	 * @param startTid
	 * @param slutTid
	 * @param prisPrPerson
	 * @param kontaktNr
	 * @param betalingsform
	 * @param antalpersoner
	 * @param studieRabat
	 */

	public void updateRundvisning(Rundvisning rundvisning, String beskrivelse, LocalDateTime startTid,
			LocalDateTime slutTid, double prisPrPerson, int kontaktNr, Betalingsform betalingsform, int antalPersoner,
			boolean studieRabat, LocalDate dato) {
		rundvisning.setBeskrivelse(beskrivelse);
		rundvisning.setStartTid(startTid);
		rundvisning.setSlutTid(slutTid);
		rundvisning.setPrisPrPerson(prisPrPerson);
		rundvisning.setKontaktNr(kontaktNr);
		rundvisning.setBetalingsform(betalingsform);
		rundvisning.setAntalPersoner(antalPersoner);
		rundvisning.setStudieRabat(studieRabat);
	

	}

	/**
	 * Hvis navn ikke har en længde over 0 kastes en RuntimeException Hvis
	 * telefonnummeret ikke har en længde på 8 kastes en RuntimeException Hvis
	 * telefonnummeret indeholder andre karaktere end [0-9] kastes e
	 * RuntimeException
	 * 
	 * Opretter en udlejning og adder den til storage
	 * 
	 * @param navn
	 * @param tlf
	 * @param udlejningsDato
	 * @param afleveringsdato
	 * @return
	 */

	public Udlejning createUdlejning(String navn, String tlf, LocalDate udlejningsDato, LocalDate afleveringsdato) {
		if (navn.length() < 0)
			throw new IllegalArgumentException("Et udlejning skal have et navn");
		if (tlf.length() != 8)
			throw new IllegalArgumentException("Et telefonnummer skal være 8 tal langt");
		if (tlf.matches("[0-9]+"))
			throw new IllegalArgumentException("Et telfonnumer må kun indeholde tal");
		Udlejning udlejning = new Udlejning(navn, tlf, udlejningsDato, afleveringsdato);
		storage.addUdlejning(udlejning);
		return udlejning;

	}

	/**
	 * Opdatere en Udlejning
	 * 
	 * @param udlejning
	 * @param navn
	 * @param tlf
	 * @param udlejningsDato
	 * @param afleveringsDato
	 * @param betalingsform
	 * @param afleveret
	 */

	public void updateUdlejing(Udlejning udlejning, String navn, String tlf, LocalDate udlejningsDato,
			LocalDate afleveringsDato) {
		udlejning.setNavn(navn);
		udlejning.setTlf(tlf);
		udlejning.setUdlejningsDato(udlejningsDato);
		udlejning.setAfleveringsDato(afleveringsDato);
	}

	/**
	 * Betaler en udlejning
	 * 
	 * @param udlejning
	 * @param betalingsform
	 */

	public void betalUdlejning(Udlejning udlejning, Betalingsform betalingsform) {
		udlejning.setBetalingsform(betalingsform);
		udlejning.setAfleveret(true);
	}

	/**
	 * Sletter en udlejning
	 * 
	 * @param udlejning
	 */

	public void deleteUdlejning(Udlejning udlejning) {
		storage.removeUdlejning(udlejning);
	}

	/**
	 * Hvis navn ikke har en længde over 0 kastes en RuntimeException
	 * 
	 * Opretter en betalingsform og adder den til storage
	 * 
	 * @param navn
	 * @return
	 */

	public Betalingsform createBetalingsform(String navn) {
		if (navn.length() > 0)
			throw new IllegalArgumentException("En betalingsform skal have et navn");

		Betalingsform betalingsform = new Betalingsform(navn);
		storage.addBetalingsform(betalingsform);
		return betalingsform;
	}

	/**
	 * Sletter en betalingsform
	 * 
	 * @param betalingsform
	 */

	public void deleteBetalingsform(Betalingsform betalingsform) {
		storage.removeBetalingsform(betalingsform);
	}

	/**
	 * Opdatere en betalingsform
	 * 
	 * @param betalingsform
	 * @param navn
	 */

	public void updateBetalingsform(Betalingsform betalingsform, String navn) {
		betalingsform.setNavn(navn);
	}

	/**
	 * Opretter et salg
	 * 
	 * @return
	 */

	public Salg createSalg() {
		Salg salg = new Salg();
		storage.addSalg(salg);
		return salg;
	}

	/**
	 * Sletter et salg
	 * 
	 * @param salg
	 */

	public void deleteSalg(Salg salg) {
		storage.removeSalg(salg);
	}

	/**
	 * Opdatere et salg
	 * 
	 * @param salg
	 * @param dato
	 * @param betalingsform
	 */

	public void updateSalg(Salg salg, LocalDate dato, Betalingsform betalingsform) {
		salg.setDato(dato);
		salg.setBetalingsform(betalingsform);
	}

	/**
	 * Opretter en salgslinje for et salg
	 * 
	 * Pre: salg må ikke være null og rabat må ikke være null
	 * 
	 * @param salg
	 * @param antal
	 * @param rabat
	 * @param pris
	 * @return
	 */

	public Salgslinje createSalgslinje(Salg salg, int antal, Rabat rabat, Pris pris) {
		Pre.require(salg != null && rabat != null);
		if (antal < 0)
			throw new IllegalArgumentException("Et antal må ikke være negativ");
		Salgslinje salgslinje = salg.createSalgslinje(pris, antal, rabat);
		return salgslinje;
	}

	/**
	 * Opdatere en salgslinje
	 * 
	 * @param salgslinje
	 * @param antal
	 * @param rabat
	 * @param pris
	 */

	public void updateSalgslinje(Salgslinje salgslinje, int antal, Rabat rabat, Pris pris) {
		salgslinje.setAntal(antal);
		salgslinje.setProdukPris(pris);
		salgslinje.setRabat(rabat);
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
	 * @param navn
	 * @return
	 */

	public Prisliste createPrisliste(String navn) {
		if (navn.length() < 0)
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

	public void updatePrisliste(Prisliste prisliste, String navn) {
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
	 * Opretter en pris for en prisliste
	 * 
	 * Pre: prisliste må ikke være null og produkt må ikke være null
	 * 
	 * @param prisliste
	 * @param pris
	 * @param produkt
	 */

	public Pris createPris(Prisliste prisliste, int pris, Produkt produkt) {
		Pre.require(prisliste != null && produkt != null);
		if (pris < 0)
			throw new IllegalArgumentException("En pris må ikke være negativ");
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

	public void updatePris(Pris prisprodukt, int pris, Produkt produkt) {
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
	 * Returnere et HashSet af alle produkter
	 * 
	 * @return
	 */

	public HashSet<Produkt> getAllProdukter() {
		return storage.getProdukter();
	}

	/**
	 * Retunere et Treeset af alle produktgrupper
	 * 
	 * @return
	 */

	public List<Produktgruppe> getAllProduktgrupper() {
		return storage.getProduktgrupper();
	}

	/**
	 * Retunere en liste af alle prislister
	 * 
	 * @return
	 */

	public List<Prisliste> getAllPrislister() {
		return storage.getPrislister();
	}

	/**
	 * Retunere en liste af alle salg
	 * 
	 * @return
	 */

	public List<Salg> getAllSalg() {
		return storage.getSalgliste();
	}

	/**
	 * Retunere en liste af alle rundvisninger
	 * 
	 * @return
	 */

	public List<Rundvisning> getAllRundvisninger() {
		return storage.getRundvisninger();
	}

	/**
	 * Retunere en liste af alle udlejninger
	 * 
	 * @return
	 */

	public List<Udlejning> getAllUdlejninger() {
		return storage.getUdlejninger();
	}

	/**
	 * Retunere en liste af alle betalingsformer
	 * 
	 * @return
	 */

	public List<Betalingsform> getAllBetalingsformer() {
		return storage.getBetalingsformer();
	}

	/**
	 * retunere en liste af dagens salg
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
	 * retunere hvor mange genstande der er solgt af et valgt produkt, imellem to
	 * valgte datoer
	 * 
	 * @param produkt
	 * @param startdato
	 * @param slutdato
	 * @return
	 */

	public int antalSolgteAfProduktOverPeriode(Produkt produkt, LocalDate startdato, LocalDate slutdato) {
		int solgte = 0;
		if (startdato.isAfter(slutdato))
			throw new IllegalArgumentException("En startdato må ikke være efter en slutdato");
		for (Salg s : storage.getSalgliste()) {
			for (int i = 0; i < s.getSalgslinjer().size(); i++) {
				if (s.getSalgslinjer().get(i).getProdukt() == produkt
						&& ((s.getDato().isBefore(slutdato) && s.getDato().isAfter(startdato))
								|| (s.getDato().equals(startdato) || s.getDato().equals(slutdato)))) {
					solgte += s.getSalgslinjer().get(i).getAntal();
				}
			}
		}
		return solgte;
	}

	/**
	 * Viser alle fremtidge rundvisninger
	 * 
	 * @return
	 */

	public List<Rundvisning> fremtidigeRundvisninger() {
		List<Rundvisning> planlagterundvisninger = new ArrayList<>();
		for (Rundvisning r : storage.getRundvisninger()) {
			if (r.getStartTid().toLocalDate().equals(LocalDate.now())
					|| r.getStartTid().toLocalDate().isAfter(LocalDate.now())) {
				planlagterundvisninger.add(r);
			}
		}
		return planlagterundvisninger;
	}

	// getprodukter osv. via storage

	// remove produkt osv.

	// opdater produkt osv.

	// tilføj produkter til gaveæske
}
