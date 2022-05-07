package taller1;

import java.io.*;
import java.util.Scanner;


public class appXiaomiau {
//static scanner used on the whole program
	static Scanner leer = new Scanner(System.in);
	
	
/** main part of the program that starts everything, beginning with the read of the archives and login in, 
 * it will thorw an exception if the file names are not changed to the correct location btw
 * 
 * @param args
 * @throws IOException
 */
	public static void main(String[] args) throws IOException {
		listaClientes listaClientes = new listaClientes(1000);
		listaProductos listaProductos = new listaProductos(1000);
		
		
		leerClientes(listaClientes);
		leerProductos(listaProductos);
		
		inicio(listaClientes, listaProductos);
		
		System.out.println("Saliendo del sistema");
		salirSobreescribir(listaClientes,listaProductos);
	}
	


/** this method leerClientes reads the txt Clientes by creating a object named cliente and doing an 
 * input in the listaClientes
 * 	
 * @param lc list of clients
 * @throws FileNotFoundException
 */
	public static void leerClientes(listaClientes lc) throws FileNotFoundException {
		
		File cliente = new File("Clientes.txt");
		Scanner leerC = new Scanner(cliente);
		while(leerC.hasNextLine()) {		
			String linea =leerC.nextLine();
			String[] partes =linea.split(",");
			String nom = partes[0];
			String pass = partes[1];
			int saldo = Integer.parseInt(partes[2]);
			String mail = partes[3];
			
			Cliente C = new Cliente(nom,pass,saldo,mail);
			lc.ingresarCliente(C);
	        }
		leerC.close();
	}

/** this method leerProductos reads the txt Productos by creating a object named producto and doing 
 * an input in the lp and also reads the txt Ventas where does a setCantVendido for each item in the txt.
 * 
 * @param lp list of products
 * @throws FileNotFoundException
 */
	public static void leerProductos(listaProductos lp) throws FileNotFoundException {
			
		File producto = new File("Productos.txt");
		Scanner leerP = new Scanner(producto);	
		while(leerP.hasNextLine()) {		
			String linea =leerP.nextLine();
			String[] partes =linea.split(",");
			String nom = partes[0];
			int precio = Integer.parseInt(partes[1]);
			int stock = Integer.parseInt(partes[2]);
			
			Producto P = new Producto(nom,precio,stock);
			lp.ingresarProducto(P);
	        }
		leerP.close();
		
		File ventas = new File("Ventas.txt");
		Scanner leerV = new Scanner(ventas);	
		while(leerV.hasNextLine()) {		
			String linea =leerV.nextLine();
			String[] partes =linea.split(",");
			String nom = partes[0];
			Producto p = lp.buscarProducto(nom);
			int cant = Integer.parseInt(partes[1]);
			p.setCantVendido(cant);
			
	        }
		leerV.close();
		
	}     
	       
/** inicio is where the user can select the menu
 * 
 * @param lc list of clients
 * @param lp list of products
 */
	
	public static void inicio(listaClientes lc, listaProductos lp) {
		System.out.println("Bienvenido a Xiaomiau");
		
		//login in
		int login=0;
		while(login==0){						
			System.out.println("Ingrese su correo: ");
			String mail = leer.next();
	 		Cliente cliente = lc.buscarCliente(mail);
	 		
	 		//Admin case
			if(mail == "Admin") {
				int correctA = 1;
				while(correctA ==1) {
					System.out.println("Ingrese su contraseña: ");
					String pass = leer.next();
					if("NYAXIO"==pass) {
						System.out.println("Contraseña correcta");
						menuAdmin(lc,lp);
					}
					else if(pass=="1") {
						login++;
						break;
					}
					System.out.println("Contraseña incorrecta intente denuevo, escriba 1 para salir");
				}
			}
			
			//player found 
			else if(!(cliente==null)) {
				int correct =1;
				while(correct ==1) {
					System.out.println("Ingrese su contraseña: ");
					String pass = leer.next();
					if(cliente.getPass().equals(pass)) {
						System.out.println("Contraseña correcta");
						menuPlayer(lc, lp, cliente);
					}
					else if(pass=="1") {
						login++;
						break;
					}
					System.out.println("Contraseña incorrecta intente denuevo, escriba 1 para salir");
				}
			
			}
			
			//player not found
			else if(cliente==null) {
				System.out.println("Jugador no encontrado");
				System.out.println("Escriba 1 para reintentar");
				System.out.println("Escriba 2 para registrarse");
				System.out.println("Escriba 3 para salir");
				System.out.println("Ingrese opcion: ");
				int eleccion = leer.nextInt();
				switch (eleccion) {
				case 1: {					
				}
				case 2: {
					registrar(lc,lp);		
				}
				case 3: {
					login++;
					break;
				}
				default:
					throw new IllegalArgumentException("Opcion no valida");
				}
			}
		}
	}

	
/**registrar registers a new user in to the system by asking them for an name, mail, and password
 * 
 * @param lc list of clients
 * @param lp list of products
 */
	private static void registrar(listaClientes lc, listaProductos lp) {
		System.out.println("Indique su nombre: ");
		String newName = leer.next();
		System.out.println("Indique su password: ");
		String newPass = leer.next();
		System.out.println("Indique su correo: ");
		String newMail = leer.next();
		
		Cliente c = new Cliente(newName, newPass, 0, newMail);
		lc.ingresarCliente(c);		
	}

	
/** menuPlayer the user can make use of his options as a player
 * 	
 * @param lc list clients
 * @param lp list products
 * @param cliente
 */
	private static void menuPlayer(listaClientes lc, listaProductos lp, Cliente cliente) {
		
		int seguir =0;
		while(seguir ==0) {
		System.out.println("Bienvenido "+cliente.getName());
		System.out.println("Estas son tus opciones"
				+ "\n1 Elegir un producto"
				+ "\n2 Ver catalogo de productos"
				+ "\n3 Ver saldo"
				+ "\n4 Recargar saldo"
				+ "\n5 Ver carrito"
				+ "\n6 Quitar del carrito"
				+ "\n7 Pagar carrito"
				+ "\n8 Salir");
		int opcion = leer.nextInt();
		switch (opcion) {
		
		
		case 1: {
			System.out.println("Que producto desea añadir al carro");
			String producto = leer.next();
			Producto P = lp.buscarProducto(producto);
			if(!(P == null)) {
				System.out.println("Cuantos desea comprar?"
						+ "\nCantidad restante: "+P.getStock());
				int cant = leer.nextInt();
				if(!(cant > P.getStock())) {
					P.añadirCantVendido(cant);
					P.eliminarStock(cant);
					Producto entrada = new Producto(P.getName(), P.getPrice(), cant);
					cliente.getListaProducto().ingresarProducto(entrada);
				}else if(cant > P.getStock()) {
					System.out.println("cantidad supera al stock");
				}	
			}		
		}
		
		
		case 2: {
			System.out.println("Catalogo");
			for (int i = 0; i < lp.getCant(); i++) {
				System.out.println(lp.getProducto(i).getName());
			}
		}
		
		
		case 3: {
			System.out.println("Saldo actual: "+cliente.getSaldo());
		}
		
		
		case 4: {
			System.out.println("Cuanto saldo desea añadir: ");
			int saldo = leer.nextInt();
			cliente.añadirSaldo(saldo);
		}
		
		
		case 5: {
			System.out.println("Mostrando carrito");
			for (int i = 0; i < cliente.getListaProducto().getCant(); i++) {
				System.out.println("Nombre: "+ cliente.getListaProducto().getProducto(i).getName()
						+" Cantidad: "+cliente.getListaProducto().getProducto(i).getStock()
						+" Precio individual: "+cliente.getListaProducto().getProducto(i).getPrice());
			}
		}
		
		
		case 6: {
			System.out.println("Ingrese el nombre de lo que desea eleminar: ");
			String nombre = leer.next();
			
			
			int cant = cliente.getListaProducto().buscarProducto(nombre).getStock();
			cliente.getListaProducto().eleminarProducto(nombre);
			lp.buscarProducto(nombre).añadirStock(cant);
			lp.buscarProducto(nombre).eliminarCantVneido(cant);
		}
		
		case 7: {
			int total=0;
			System.out.println("Pagar carrito");
			for (int i = 0; i < cliente.getListaProducto().getCant(); i++) {
				int precio = cliente.getListaProducto().getProducto(i).getPrice();
				int cantidad = cliente.getListaProducto().getProducto(i).getStock();
				
				total = total + (precio*cantidad);
			}
		}
		
		case 8: {
			System.out.println("Volviendo al inicio");
			seguir++;
		}
		
		default:
			throw new IllegalArgumentException("Opcion no valida");		
		}
		}	
	}
	
	
/** menuAdmin is the menu that the admin can use to manage the stock unites, remove a client 
 * 
 * @param lc list of clients
 * @param lp list of products
 */
	private static void menuAdmin(listaClientes lc, listaProductos lp) {
		
		int seguir =0;
		while(seguir ==0) {
		System.out.println("Bienvenido ");
		System.out.println("Estas son tus opciones en el menu de admin"
				+ "\n1 Bloquear Usuario"
				+ "\n2 Ver historial de compras"
				+ "\n3 Agregar producto"
				+ "\n4 Agregar stock"
				+ "\n5 Actualizar datos"
				+ "\n6 Salir");
		int opcion = leer.nextInt();
		switch (opcion) {
		case 1: {
			System.out.println("Ingrese el correo del cliente a eleminiar: ");
			String objetivoEliminar = leer.next();
			if(lc.eleminarCliente(objetivoEliminar) == true) {
				System.out.println("Cliente eleminado");
			}else if(lc.eleminarCliente(objetivoEliminar) == false){
				System.out.println("Cliente no encontrado");
			}
			
			
		}
		case 2: {
			System.out.println("Mostrando historial de compras");
			for (int i = 0; i < lp.getCant(); i++) {
				Producto producto = lp.getProducto(i);
				System.out.println("Nombre: "+producto.getName()
				+" Cantidad vendida: "+producto.getCantVendido());
			}	
		}
		
		
		case 3: {
			System.out.println("Ingrese el nombre del producto que desea añadir: ");
			String añadir = leer.next();
			System.out.println("Ingrese el precio del producto que desea añadir: ");
			int precio = leer.nextInt();
			System.out.println("Ingrese el stock del producto que desea añadir: ");
			int cant = leer.nextInt();
			
			Producto p = new Producto(añadir, precio, cant);
			lp.ingresarProducto(p);
		}
		
		
		case 4: {
			System.out.println("Ingrese el nombre del producto que desea añadir stock: ");
			String añadir = leer.next();
			System.out.println("Ingrese la cantidad del producto que desea añadir: ");
			int cant = leer.nextInt();
			Producto producto = lp.buscarProducto(añadir);
			producto.añadirStock(cant);
		}
		
		case 5:{
			System.out.println("Ingrese el nombre del producto que desea añadir stock: ");
			String añadir = leer.next();
			System.out.println("Ingrese el nuevo precio del producto que desea añadir: ");
			int precio = leer.nextInt();
			Producto producto = lp.buscarProducto(añadir);
			producto.setPrice(precio);;
		}
		
		
		case 6: {
			System.out.println("Volviendo al inicio");
			seguir++;
		}
		
		
		default:
			throw new IllegalArgumentException("Unexpected value: " + opcion);
		}
		}
		
	}
	
/** salirSobreescribir uses the file and the matrix provided to overwrite the information in the place that it should be
 * 	
 * @param lc list of clients
 * @param lp list of products
 * @throws IOException
 */
	private static void salirSobreescribir(listaClientes lc, listaProductos lp) throws IOException {
		//player overwrite 
		FileWriter escritorC = new FileWriter("Clientes");
		for (int i = 0; i < lc.getCant(); i++) {
			escritorC.write(lc.getCliente(i).getName()+", "+lc.getCliente(i).getPass()+", "+lc.getCliente(i).getSaldo()+", "+lc.getCliente(i).getMail()+"\n");
		}
		
		//Spell overwrite
		FileWriter escritorP = new FileWriter("Productos");
		for(int i = 0; i < lp.getCant(); i++) {
			escritorP.write(lp.getProducto(i).getName()+","+lp.getProducto(i).getPrice()+","+lp.getProducto(i).getStock()+"\n");
		}
		
		//Owners overwrite
		FileWriter escritorV = new FileWriter("Ventas");
		for(int i = 0; i < lp.getCant(); i++) {
			
			escritorV.write(lp.getProducto(i).getName()+","+lp.getProducto(i).getCantVendido()+"\n");
			
		}
		
		//close every writer
		escritorC.close();
		escritorP.close();
		escritorV.close();
		
		//system close
		System.exit(0);
	}

}
