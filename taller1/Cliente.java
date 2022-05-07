package taller1;


public class Cliente {

	private String name;
	private String pass;
	private int saldo;
	private String mail;
	private listaProductos listaProductos;
	
/**class client creates a client for each person on the system, and gives them a list to be used as "carrito"
 * 
 * @param name of each client
 * @param pass password of each client
 * @param saldo balance of each client
 * @param mail of each client
 */
	public Cliente(String name, String pass, int saldo, String mail) {
		this.name = name;
		this.pass = pass;
		this.saldo = saldo;
		this.mail = mail;
		this.listaProductos = new listaProductos(100);
	}
	
	
	
	public listaProductos getListaProducto() {
		return listaProductos;
	}

	public void setListaProducto(listaProductos listaProductos) {
		this.listaProductos = listaProductos;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	
	public void añadirSaldo(int saldo) {
		this.saldo = this.saldo + saldo;
	}
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	
}
