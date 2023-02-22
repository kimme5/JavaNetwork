package rmiwas.client;

import java.rmi.Naming;

import rmidbcon.server.RemoteObjAndServer;
import rmiwas.server.WasControl;
/* -----------------------------------------------------------------------------------------------------------------------------------
 * ������
 * 1) Stub Ŭ���� ���� : C:\Users\seong\workspace\JavaNetwork\bin> rmic rmiwas.server.WasControlImpl
 * 2) rmi service ���� : C:\Users\seong\workspace\JavaNetwork\bin> start rmiregistry 1099
 * 3) rmi ���� application ���� : C:\Users\seong\workspace\JavaNetwork\bin> java -classpath . rmiwas.server.WasControlServer
 * 4) rmi Ŭ���̾�Ʈ application ���� : C:\Users\seong\workspace\JavaNetwork\bin> java -classpath . rmiwas.server.WasControlClient
   ----------------------------------------------------------------------------------------------------------------------------------- */
public class WasControlClient {

	public static void main(String[] args) {
		
		try {
			WasControl remoteObj = (WasControl) Naming.lookup("rmi://localhost:1099/WasControl");
			// Start Tomcat
			String msg = remoteObj.startTomcat();
			System.out.println(msg);
			Thread.currentThread().sleep(10000);
			// Stop Tomcat
			msg = remoteObj.stopTomcat();
			System.out.println(msg);
		} catch(Exception e) {
			System.out.println("Something has gone wrong during remote method call... ");
		}

	}

}
