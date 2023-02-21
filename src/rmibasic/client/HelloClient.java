package rmibasic.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import rmibasic.server.Hello;

/* ------------------------------------------------------------------------------------------------------------------------
 * RMI Ŭ���̾�Ʈ Application : HelloClient
 * 1) ���� ��ü�� lookup �޼ҵ带 �̿��Ͽ� �̸����� ã�Ƴ� �� 
 *    �ش� ��ü�� �����ϴ� ���� �޼ҵ带 ȣ���ϴ� ���α׷�
 * 2) RMI�� ���� ��ü�� ���� ������ stub�� �����
 *    stub�� �Ķ���͸� skeleton���� ����ȭ�Ͽ� ���� �޼ҵ带 ȣ���ϰ�
 *    ����� �ٽ� ����ȭ�Ͽ� stub���� ������
 *    ��, RMIŬ���̾�Ʈ Application���� ����ϴ� ���� ��ü�� reference��
 *    ������ stub�� ���� reference��
 * 3) RMIŬ���̾�Ʈ���� RMI������ �޼ҵ� ȣ�� 
 *    --> ���� stub��ü ���� 
 *    --> ����(Remote Serve)�� skeleton ��ü�� ���� 
 *    --> ���� ��ü�� �޼ҵ� ȣ�� ������ ����
 * 4) Java2 ���ĺ��ʹ� skeleton�� ��ġ�� �ʰ� stub�� ���� ��ü�� ���� ã�ư�
 * RMI ������ 
 * - ���� ����� �ܼ�â ����
 * 1) stub Ŭ���� ���� : C:\Users\seong\workspace\JavaNetwork\bin> rmic rmiserver.HelloImpl --> HelloImpl_Stub.class ����
 * 2) rmiregistry ���� : C:\Users\seong\workspace\JavaNetwork\bin> start rmiregistry 1099
 * 3) rmi���� ���� : C:\Users\seong\workspace\JavaNetwork\bin> java rmiserver.HelloServer
 * - Ŭ���̾�Ʈ ����� �ܼ�â ����
 * 4) rmiŬ���̾�Ʈ ���� : C:\Users\seong\workspace\JavaNetwork\bin> java rmiclient.HelloClient SeongwooKim
 * ------------------------------------------------------------------------------------------------------------------------ */
public class HelloClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Object obj = Naming.lookup("rmi://localhost:1099/HelloRemote");
			// remoteObj Ŭ������������ HelloImpl_Stub Ŭ���� ��ü�� return��
			Hello remoteObj = (Hello) obj;
			String message = remoteObj.sayHello(args[0]);
			System.out.println(message);
		} catch(RemoteException ex) {
			System.out.println("Something has gone wrong during remote mothod call... ");
		} catch(MalformedURLException ex) {
			System.out.println("Check URL String... ");
		} catch(NotBoundException ex) {
			System.out.println("Could Not bound... ");
		}
	}

}
