package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LineaPedidoModel {

	int id;
	int pedido_id;
	int producto_id;
	int unidades;
	double preciounidad;
	double precioEnvio;
	double impuesto;
	double total;
	ArticuloModel articulo;
	
	public LineaPedidoModel(int pedido_id, int producto_id, int unidades, double preciounidad, double precioEnvio,
			double impuesto, double total) {
		super();
		this.pedido_id = pedido_id;
		this.producto_id = producto_id;
		this.unidades = unidades;
		this.preciounidad = preciounidad;
		this.precioEnvio = precioEnvio;
		this.impuesto = impuesto;
		this.total = total;
	}

	public LineaPedidoModel(int pedido_id, int producto_id, int unidades, double preciounidad, double precioEnvio,
			double impuesto, double total,ArticuloModel articulo) {
		super();
		this.pedido_id = pedido_id;
		this.producto_id = producto_id;
		this.unidades = unidades;
		this.preciounidad = preciounidad;
		this.precioEnvio = precioEnvio;
		this.impuesto = impuesto;
		this.total = total;
		this.articulo = articulo;
	}
	
}
