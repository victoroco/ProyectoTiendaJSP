package models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PedidoModel {

	int id;
	int usuario_id;
	Timestamp fecha;
	int metodoPago;
	String metodoPagoNombre;
	String numfactura;
	double total;

	public PedidoModel(int id, Timestamp timestamp, int metodoPago, String metodoPagoNombre, double total) {
		super();
		this.id = id;
		this.fecha = timestamp;
		this.metodoPago = metodoPago;
		this.metodoPagoNombre = metodoPagoNombre;
		this.total = total;
	}

	public PedidoModel(int usuario_id, int metodoPago, double total) {
		super();
		this.usuario_id = usuario_id;
		this.metodoPago = metodoPago;
		this.total = total;
	}

}
