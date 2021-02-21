package es.ucm.fdi.tp.practica6;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
/**
 * 
 * clase que conecta el servidor con el cliente
 *
 */
public class Connection {
	private volatile Socket s;
	private volatile ObjectInputStream in;
	private volatile ObjectOutputStream out;
	/**
	 * 
	 * @param s
	 * @throws IOException
	 */
	public Connection(Socket s) throws IOException{
		
		this.s=s;
		this.out = new ObjectOutputStream(s.getOutputStream());
		this.in = new ObjectInputStream(s.getInputStream());
		
	}
	/**
	 * para la conexion
	 * @throws IOException
	 */
	public void stop() throws IOException{
		s.close();
	}
	/**
	 * evia un objeto
	 * @param r
	 * @throws IOException
	 */
	public void sendObject(Object r) throws IOException{
		
		out.writeObject(r);
		out.flush();
		out.reset();	
	}
	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @return in.readObject()
	 */
	public Object getObject() throws IOException, ClassNotFoundException {
		return in.readObject();
	}
}
