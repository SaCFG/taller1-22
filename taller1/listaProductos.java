package taller1;

public class listaProductos {
	private int max;
	private int cant;
	private Producto[] listaProductos;
/** list of products can add a product, get the product, delete the product (used on the list carrit for clients)
 * , get the index, search for it.
 * 	
 * @param max maximum amount of items
 */
	
	public listaProductos(int max) {
		this.max = max;
		this.cant = 0;
		this.listaProductos = new Producto[max];
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getCant() {
		return cant;
	}
	public void setCant(int cant) {
		this.cant = cant;
	}
	public Producto[] getListaProducto() {
		return listaProductos;
	}
	public void setListaProducto(Producto[] listaProducto) {
		this.listaProductos = listaProducto;
	}
	
	public boolean ingresarProducto(Producto producto) {
		if(!(cant>=max)) {
			listaProductos[cant] = producto;
			cant++;
			return true;
		}
		return false;
	}
	
	public int indexProducto(String nombre){
		for (int i = 0; i < cant; i++) {
			if(listaProductos[i].getName().equals(nombre)) {
				return i;
			}
		}
		return -1;
	}
	
	
	public Producto buscarProducto(String nombre) {
		for (int i = 0; i < cant; i++) {
			if(listaProductos[i].getName().equals(nombre)) {
				return listaProductos[i];
			}
		}
		return null;
	}
	
	public Producto getProducto(int i) {
		return listaProductos[i];
	}
	
public boolean eleminarProducto(String nombre) {
		
		int i;
		for(i=0; i<cant; i++) {
			if(listaProductos[i].getName().equals(nombre)) {
				break;
			}
		}
		
		if(i == cant) {
			return false;
			
		}else {
			
			for(int j=i; j<cant-1; j++) {
				listaProductos[j] = listaProductos[j+1];
			}
			cant--;
			return true;
		}
	}
	
}
