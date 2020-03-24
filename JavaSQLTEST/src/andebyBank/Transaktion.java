package andebyBank;

import java.util.Date;

public class Transaktion {

private int ktonr;
private Date dato;
private String tekst;
private double beløb;
private int regnr;

public Transaktion(int regnr, int ktonr, Date dato, String tekst, double beløb) {
	super();
	this.regnr = regnr;
	this.ktonr = ktonr;
	this.dato = dato;
	this.tekst = tekst;
	this.beløb = beløb;
}



public int getKtonr() {
	return ktonr;
}

public void setKtonr(int ktonr) {
	this.ktonr = ktonr;
}

public Date getDato() {
	return dato;
}

public void setDato(Date dato) {
	this.dato = dato;
}

public String getTekst() {
	return tekst;
}

public void setTekst(String tekst) {
	this.tekst = tekst;
}

public double getBeløb() {
	return beløb;
}

public void setBeløb(double beløb) {
	this.beløb = beløb;
}

public int getRegnr() {
	return regnr;
}

public void setRegnr(int regnr) {
	this.regnr = regnr;
}

@Override
public String toString() {
	return "Transaktion [ktonr=" + ktonr + ", dato=" + dato + ", tekst=" + tekst + ", beløb=" + beløb + ", regnr="
			+ regnr + "]";
}




}
