package com.jsfcourse.calc;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private String kwota;
	private String oprocentowanie;
	private String lata;
	private Double rata;

	@Inject
	FacesContext ctx;


	public String getKwota() {
		return kwota;
	}


	public void setKwota(String kwota) {
		this.kwota = kwota;
	}


	public String getOprocentowanie() {
		return oprocentowanie;
	}


	public void setOprocentowanie(String oprocentowanie) {
		this.oprocentowanie = oprocentowanie;
	}



	public String getLata() {
		return lata;
	}


	public void setLata(String lata) {
		this.lata = lata;
	}
	
	
	public Double getRata() {
		return rata;
	}


	public void setRata(Double rata) {
		this.rata = rata;
	}

	public boolean doTheMath() {
		try {
			double k = Double.parseDouble(kwota);
			double p = Double.parseDouble(oprocentowanie);
			double n = Double.parseDouble(lata) * 12;

			if (k <= 0 || p <= 0 || n <= 0) {
				ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wszystkie wartości muszą być dodatnie", null));
				return false;
			}
			
			if (p > 100 || p <= 0) {
	                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
	                    "Oprocentowanie musi mieścić się w zakresie 1-100", null));
	                return false;
	            }

	        if (n < 12) { 
	                ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, 
	                    "Okres spłaty musi wynosić co najmniej 1 rok.", null));
	                return false;
	            }

			double r = p / 100 / 12;

			rata = k * r * Math.pow(1 + r, n) / (Math.pow(1 + r, n) - 1);

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Obliczono poprawnie", null));
			return true;

		} catch (NumberFormatException e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wprowadzono błędne dane", null));
			return false;
		}
	}

	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}


	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Miesięczna rata wynosi: " + String.format("%.2f zł", rata), null));
		}
		return null;
	}
	

}
	
