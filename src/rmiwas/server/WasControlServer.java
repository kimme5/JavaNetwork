package rmiwas.server;

import java.rmi.Naming;

public class WasControlServer {

	public static void main(String[] args) {
		
		try {
			WasControlImpl remoteObj = new WasControlImpl();
			Naming.rebind("rmi://localhost:1099/WasControl", remoteObj);
			System.out.println(">>> [Server] Start, incoming client calls... ");
		} catch(Exception e) {
			System.err.println(e);
		}

	}

}
