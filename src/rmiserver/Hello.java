package rmiserver;

import java.rmi.Remote;
import java.rmi.RemoteException;

/* -------------------------------------------------------------------------
 * ���� �������̽�(Remote Interface)
 * ���� �������̽����� ������ ��� �޼ҵ�� java.rmi.RemoteException�̳�
 * �θ�Ŭ����(java.io.IOExcepion, java.lang.Exception)�� throws�ؾ� ��
 * (RMI spec������ RemoteException�� throws�ϵ��� �ǰ��ϰ� ����)  
 * ------------------------------------------------------------------------- */
public interface Hello extends Remote {
	public String sayHello(String name) throws RemoteException;
}
