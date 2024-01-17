package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ArticuloModel {
	private int id;
	private String nombre;
	private String descripcion;
	private CategoriaModel categoriaModel;
	private double precio;
	private String imagen;
	private int cantidad;
	private double impuesto;
	private int stock;
	private boolean baja;

	public ArticuloModel() {
	}

	// Constructor
	public ArticuloModel(int id, String nombre, String descripcion, double precio, String imagen) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.imagen = imagen;
	}

	// Constructor
	public ArticuloModel(int id, String nombre, String descripcion, double precio, String imagen, int cantidad) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.imagen = imagen;
		this.cantidad = cantidad;
	}

	// Constructor
		public ArticuloModel(int id, String nombre, String descripcion, double precio, String imagen, int cantidad,double impuesto) {
			this.id = id;
			this.nombre = nombre;
			this.descripcion = descripcion;
			this.precio = precio;
			this.imagen = imagen;
			this.cantidad = cantidad;
			this.impuesto = impuesto;
		}
	
	// Constructor
	public ArticuloModel(int id, String nombre, String descripcion, double precio, String imagen, int cantidad,
			int stock) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.imagen = imagen;
		this.cantidad = cantidad;
		this.stock = stock;
	}

	public ArticuloModel(int id, String nombre, String descripcion, double precio, String imagen, int cantidad,
			int stock, boolean baja) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.imagen = imagen;
		this.cantidad = cantidad;
		this.stock = stock;
		this.baja = baja;
	}
	
	
	
}
