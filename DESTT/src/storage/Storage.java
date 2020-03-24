package storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import application.model.Betalingsform;
import application.model.Prisliste;
import application.model.Produkt;
import application.model.Produktgruppe;
import application.model.Rundvisning;
import application.model.Salg;
import application.model.Udlejning;

/**
 * Storage for Aarhus Bryghus
 * 
 * @author Sille, Simon og Michelle
 *
 */

public class Storage implements Serializable {

	private final HashSet<Produkt> produkter;
	private final List<Produktgruppe> produktgrupper;
	private final List<Prisliste> prislister;
	private final List<Salg> salgsliste;
	private final List<Rundvisning> rundvisninger;
	private final List<Udlejning> udlejninger;
	private final List<Betalingsform> betalingsformer;

	private static Storage storage;

	private Storage() {
		produkter = new HashSet<>();
		produktgrupper = new ArrayList<>();
		prislister = new ArrayList<>();
		salgsliste = new ArrayList<>();
		rundvisninger = new ArrayList<>();
		udlejninger = new ArrayList<>();
		betalingsformer = new ArrayList<>();
	}

	public static Storage getInstance() {
		if (storage == null) {
			storage = new Storage();
		}
		return storage;
	}

	// -------------------------------------------------------------------------

	public HashSet<Produkt> getProdukter() {
		return new HashSet<>(produkter);
	}

	public void addProdukt(Produkt produkt) {
		produkter.add(produkt);
	}

	public void removeProdukt(Produkt produkt) {
		produkter.remove(produkt);
	}

	// -------------------------------------------------------------------------

	public List<Produktgruppe> getProduktgrupper() {
		return new ArrayList<>(produktgrupper);
	}

	public void addProduktgruppe(Produktgruppe produktgruppe) {
		produktgrupper.add(produktgruppe);
	}

	public void removeProduktgruppe(Produktgruppe produktgruppe) {
		produktgrupper.remove(produktgruppe);
	}

	// -------------------------------------------------------------------------

	public List<Prisliste> getPrislister() {
		return new ArrayList<>(prislister);
	}

	public void addPrisliste(Prisliste prisliste) {
		prislister.add(prisliste);
	}

	public void removePrisliste(Prisliste prisliste) {
		prislister.remove(prisliste);
	}

	// -------------------------------------------------------------------------

	public List<Salg> getSalgliste() {
		return new ArrayList<>(salgsliste);
	}

	public void addSalg(Salg salg) {
		salgsliste.add(salg);
	}

	public void removeSalg(Salg salg) {
		salgsliste.remove(salg);
	}

	// -------------------------------------------------------------------------

	public List<Rundvisning> getRundvisninger() {
		return new ArrayList<>(rundvisninger);
	}

	public void addRundvisning(Rundvisning rundvisning) {
		rundvisninger.add(rundvisning);
	}

	public void removeRundvisning(Rundvisning rundvisning) {
		rundvisninger.remove(rundvisning);
	}

	// -------------------------------------------------------------------------

	public List<Udlejning> getUdlejninger() {
		return new ArrayList<>(udlejninger);
	}

	public void addUdlejning(Udlejning udlejning) {
		udlejninger.add(udlejning);
	}

	public void removeUdlejning(Udlejning udlejning) {
		udlejninger.remove(udlejning);
	}

	// -------------------------------------------------------------------------

	public List<Betalingsform> getBetalingsformer() {
		return new ArrayList<>(betalingsformer);
	}

	public void addBetalingsform(Betalingsform betalingsform) {
		betalingsformer.add(betalingsform);
	}

	public void removeBetalingsform(Betalingsform betalingsform) {
		betalingsformer.remove(betalingsform);
	}

	// -------------------------------------------------------------------------

}
