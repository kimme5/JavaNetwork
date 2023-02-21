package rmidbcon.server;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmidbcon.common.AnyThing;

/* --------------------------------------------------------------------------------------------
 * ���ݰ�ü RemoteObjAndServer Ŭ������ ����Ͻ� �޼ҵ� toDo()�� ���� 
 * ����Ͻ� ������ ���ǵǾ� ���� ���� 
 * �ٸ�, �Ķ���ͷ� ���޵� AnyThing ��ü�� doThing() �޼ҵ带 ȣ���ϰ� 
 * �̸� return�ϴ� ��ɸ� ���ǵǾ� ����
 * �̴� Ŭ���̾�Ʈ���� ����Ǿ� ���޵Ǵ� AnyThing ���� ��ü AnyThingImpl Ŭ������ ������ 
 * doThing() �޼ҵ忡 ���� �������� ���� ��ü ����Ͻ� ������ ����� �� ������ ��Ÿ��
 * "�ᱹ Ȯ�尡���� RMI Application"���� ��Ÿ��
   -------------------------------------------------------------------------------------------- */
public class RemoteObjAndServer extends UnicastRemoteObject implements RemoteIF {
	
	protected RemoteObjAndServer() throws RemoteException {
		super();
	}

	// ���� �޼ҵ� toDo()�� ���ڷ� � ��ü�� �޾Ƽ� �� ��ü�� doThing�� ������
	public Serializable toDo(AnyThing any) throws RemoteException {
		return any.doThing();
	}

	public static void main(String[] args) {

		try {
			// ���� ��ü�� ��ü ����
			RemoteIF remoteObj = new RemoteObjAndServer();
			Naming.rebind("rmi://localhost:1099/remoteObjAndServer", remoteObj);
			System.out.println("RemoteObjAndServer registered and ready to service... ");
		} catch(Exception e) {
			System.err.println("Couldn't successfully register remote object " + e);
		}
	
	}

}
