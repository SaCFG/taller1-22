package taller1;

public class listaClientes {
	private int max;
	private int cant;
	private Cliente[] listaClientes;
/** list of clients can add a client, get the client, delete the client
 * , get the index, search for it.
 * 	
 * @param max maximum amount of clients
 */
	
	public listaClientes(int max) {
		this.max = max;
		this.cant = 0;
		this.listaClientes = new Cliente[max];
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
	public Cliente[] getListaCliente() {
		return listaClientes;
	}
	public void setListaCliente(Cliente[] listaCliente) {
		this.listaClientes = listaCliente;
	}
	
	public boolean ingresarCliente(Cliente cliente) {
		if(!(cant>=max)) {
			listaClientes[cant] = cliente;
			cant++;
			return true;
		}
		return false;
	}
	
	public int indexCliente(String mail){
		for (int i = 0; i < cant; i++) {
			if(listaClientes[i].getName().equals(mail)) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean eleminarCliente(String mail) {
		
		int i;
		for(i=0; i<cant; i++) {
			if(listaClientes[i].getMail().equals(mail)) {
				break;
			}
		}
		
		if(i == cant) {
			return false;
			
		}else {
			
			for(int j=i; j<cant-1; j++) {
				listaClientes[j] = listaClientes[j+1];
			}
			cant--;
			return true;
		}
	}
	
	public Cliente buscarCliente(String mail) {
		for (int i = 0; i < cant; i++) {
			if(listaClientes[i].getMail().equals(mail)) {
				return listaClientes[i];
			}
		}
		return null;
	}
	
	public Cliente getCliente(int i) {
		return listaClientes[i];
	}
	
}
