package com.liceolapaz.des.gbl;

import java.util.Date;

public class Factura {
	private int idFactura;
	private Date fecha;
	private int numeroCliente;
	private double importe;
	private String pago;
	
	
	
	public Factura(int idFactura, Date fecha, int numeroCliente, double importe, String pago) {
		super();
		this.idFactura = idFactura;
		this.fecha = fecha;
		this.numeroCliente = numeroCliente;
		this.importe = importe;
		this.pago = pago;
	}



	@Override
	public String toString() {
		return "Facturas [idFactura=" + idFactura + ", fecha=" + fecha + ", numeroCliente=" + numeroCliente
				+ ", importe=" + importe + ", pago=" + pago + "]";
	}



	public int getIdFactura() {
		return idFactura;
	}



	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public int getNumeroCliente() {
		return numeroCliente;
	}



	public void setNumeroCliente(int numeroCliente) {
		this.numeroCliente = numeroCliente;
	}



	public double getImporte() {
		return importe;
	}



	public void setImporte(double importe) {
		this.importe = importe;
	}



	public String getPago() {
		return pago;
	}



	public void setPago(String pago) {
		this.pago = pago;
	}
}
