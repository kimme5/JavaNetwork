package rmidbcon.server;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import rmidbcon.common.AnyThing;

/* -----------------------------------------------------------------------------------
 * Ŭ���̾�Ʈ�� ȣ���� ���� �޼ҵ� toDo()�� AnyThing ��ü Ÿ���̸�
 * toDo() �޼ҵ� ���������� AnyThing ��ü�� �޼ҵ带 ȣ���Ͽ� ����Ͻ� ������ ó���� 
 * �ᱹ Ŭ���̾�Ʈ�� ó���� ����Ͻ� ������ Ŭ���̾�Ʈ�� �����ؼ�
 * AnyThing ��ü�� ��� ����
   ----------------------------------------------------------------------------------- */
public interface RemoteIF extends Remote {
	public Serializable toDo(AnyThing any) throws RemoteException;
}
