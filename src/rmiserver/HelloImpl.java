package rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/* ---------------------------------------------------------------------------------------
 * ���� �������̽��� ������ Ŭ����
 * 1) HelloImpl.java�� ���� �������̽� Hello�� ������ Ŭ�����̸�
 *    HelloImpl Ŭ������ �ν��Ͻ��� ���� ��ü�μ� RMI������ ���� �̸����� ��ϵ�
 * 2) java.rmi.UnicastRemoteObject�� ����Ͽ� ���� �������̽��� ������
 * 3) HelloImpl ��ü�� ���� ��ü�� �۵��� �� �ִ� ���� ���� �޼ҵ带 �����ؼ��� �ƴ϶�
 *    UnicastRemoteObject Ŭ������ ����߱� ������
 * 4) stub Ŭ���� ���� : C:\Users\seong\workspace\JavaNetwork\bin> rmic rmiserver.HelloImpl 
 *    --> HelloImpl_Stub.class ���� 
 * --------------------------------------------------------------------------------------- */
public class HelloImpl extends UnicastRemoteObject implements Hello {

	public HelloImpl() throws RemoteException {
		super();
	}
	// ���� �޼ҵ� ����
	public String sayHello(String name) {
		return "Hello World " + name + "!";
	}

}
