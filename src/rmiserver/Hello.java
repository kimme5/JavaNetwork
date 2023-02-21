package rmiserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

/* -------------------------------------------------------------------------
 * 원격 인터페이스(Remote Interface)
 * 원격 인터페이스에서 정의한 모든 메소드는 java.rmi.RemoteException이나
 * 부모클래스(java.io.IOExcepion, java.lang.Exception)을 throws해야 함
 * (RMI spec에서는 RemoteException을 throws하도록 권고하고 있음)  
 * ------------------------------------------------------------------------- */
public interface Hello extends Remote {
	public String sayHello(String name) throws RemoteException;
}
