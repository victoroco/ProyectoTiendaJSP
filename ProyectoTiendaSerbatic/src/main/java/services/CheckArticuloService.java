package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.ArticuloDAO;
import models.ArticuloModel;

public class CheckArticuloService {

	public String comprobarFiltros(String[] filtrosCategoria) {

		String sqlCategoria = "";
		String filtros = "";
		if (filtrosCategoria.length > 0) {
			sqlCategoria = "where id_categoria in (";
			for (int i = 0; i < filtrosCategoria.length; i++) {
				if (i == (filtrosCategoria.length - 1)) {
					filtros.concat(filtrosCategoria[i]);
				} else {
					filtros.concat(filtrosCategoria[i] + ",");
				}
			}
			filtros.concat(")");
			sqlCategoria.concat(filtros);
		}
		return sqlCategoria;
	}

	public ArticuloModel getArticulo(int articuloId) {
		ArticuloModel articulo;

		articulo = ArticuloDAO.obtenerArticulo(articuloId);

		return articulo;
	}

	// ACTUALIZAR INFO PROD CARRITO
	public HashMap<Integer, ArticuloModel> actualizarProdCarrito(HashMap<Integer, ArticuloModel> carrito) {

		System.out.println("RedirCarrito");
		for (Map.Entry<Integer, ArticuloModel> entry : carrito.entrySet()) {

			ArticuloModel articulo = entry.getValue();
			int cantidad = articulo.getCantidad();
			articulo = ArticuloDAO.obtenerArticulo(articulo.getId());
			articulo.setCantidad(cantidad);
		}
		return carrito;
	}

	// PRECIO TOTAL CARRITO
	public double calcularTotalCarrito(HashMap<Integer, ArticuloModel> carrito) {

		double precioTotal = 0;
		for (Map.Entry<Integer, ArticuloModel> entry : carrito.entrySet()) {

			ArticuloModel articulo = entry.getValue();

			precioTotal += (articulo.getPrecio() * articulo.getCantidad());

		}
		return precioTotal;
	}

	// Devolvemos una lista con los prod sin stock
	public ArrayList<Integer> comprobaStockCarrito(HashMap<Integer, ArticuloModel> carrito) {

		// HashMap<Integer, String> mapProdSinStock = new HashMap<Integer, String>();

		ArrayList<Integer> listaProdSinStock = new ArrayList<Integer>();

		for (Map.Entry<Integer, ArticuloModel> entry : carrito.entrySet()) {

			ArticuloModel articulo = entry.getValue();

			if (!sufStockDeBaja(articulo.getId(), articulo.getCantidad())) {

				listaProdSinStock.add(articulo.getId());
			}

		}
		return listaProdSinStock;
	}

	// Quitamos del carrito prod sin stock
	public HashMap<Integer, ArticuloModel> quitarProdSinStockCarrito(HashMap<Integer, ArticuloModel> carrito,
			ArrayList<Integer> prodSinStock) {

		for (int i = 0; i < prodSinStock.size(); i++) {

			carrito.remove(prodSinStock.get(i));

		}

		/*
		 * for (Map.Entry<Integer, String> entry : prodSinStock.entrySet()) {
		 * 
		 * Integer key = entry.getKey();
		 * 
		 * carrito.remove(key);
		 * 
		 * }
		 */

		return carrito;
	}

	public boolean sufStockDeBaja(int id, int cantidad) {

		if (suficienteStock(id, cantidad) && articuloDeBaja(id) == false) {
			return true;
		}
		return false;
	}

	public boolean suficienteStock(int idProd, int cantidad) {

		// ArticuloDAO aDao = new ArticuloDAO();
		ArticuloModel articulo = ArticuloDAO.obtenerArticulo(idProd);

		if (articulo.getId() == idProd)
			if (articulo.getStock() >= cantidad)
				return true;

		return false;
	}

	public boolean articuloDeBaja(int idProd) {

		// ArticuloDAO aDao = new ArticuloDAO();
		ArticuloModel articulo = ArticuloDAO.obtenerArticulo(idProd);

		if (articulo.getId() == idProd)
			if (articulo.isBaja())
				return true;

		return false;
	}

	/*
	 * public static void removeArticulos() {
	 * 
	 * ArticuloDAO aDao = new ArticuloDAO(); ArticuloModelo articulo =
	 * aDao.obtenerArticulo(idProd);
	 * 
	 * for (int i = 0; i < ArticuloDAO.catalogo.size(); i++) { if
	 * (ArticuloDAO.catalogo.get(i).getStock() <= 0 ||
	 * ArticuloDAO.catalogo.get(i).isBaja()) { ArticuloDAO.catalogo.remove(i); } }
	 * 
	 * }
	 */

}
