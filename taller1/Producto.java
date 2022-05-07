package taller1;

public class Producto {
	private String name;
	private int price;
	private int stock;
	private int cantVendido;
/** class product creates a product for each entry and gives them also a amount sold 
 * 	
 * @param name of each product
 * @param price of each product
 * @param stock of each product
 */
	public Producto(String name, int price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.cantVendido = 0;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public int getCantVendido() {
		return cantVendido;
	}

	public void setCantVendido(int cantVendido) {
		this.cantVendido = cantVendido;
	}

	public void añadirStock(int cant) {
		this.stock = this.stock + cant;
	}
	
	public void añadirCantVendido(int cant) {
		this.stock = this.stock + cant;
	}
	
	public void eliminarStock(int cant) {
		this.stock = this.stock - cant;
	}
	
	public void eliminarCantVneido(int cant) {
		this.cantVendido = this.cantVendido - cant;
	}
	
}
