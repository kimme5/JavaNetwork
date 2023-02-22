package rmibasic.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/* -------------------------------------------------------------------------------------------
 * RMI���� Application : HelloServer
 * 1) RMI���� ���� ��ü�� �����ϰ� �̸� �̸����� ����� �� 
 *    ���� ��ü�� ���� Ŭ���̾�Ʈ�� ���� ��û�� ��ٸ�
 * 2) RMI���� application�� ���� �������̽� ���� Ŭ������ �ν��Ͻ��� �����Ͽ� 
 *    �̸� rmiregistry�� �̸����� �����
 * 3) ���� ��ü�� �ڽ��� ������ Ŭ���̾�Ʈ�� ������ �� �ֵ��� ����� �� �־�� �ϸ�
 *    Ŭ���̾�Ʈ���� ���� �޼ҵ� ��û�� �߻��ߴ��� ���������� ����͸��� �� �־�� ��
 *    ������, ���� �������̽����� �̷��� ����� ���ǵ��� �ʾ���
 * ------------------------------------------------------------------------------------------- */
public class HelloServer {

	public static void main(String[] args) {
		
		try {
			// ���� ��ü�� �����Ͽ� �̸� rmiregistry�� "HelloRemote"��� �̸����� �����
			HelloImpl remoteObj = new HelloImpl();
			Naming.rebind("rmi://localhost:1099/HelloRemote", remoteObj);
			System.out.println("Hello Remote Object bound to the registry and ready to service incoming client call... ");
		} catch(RemoteException ex) {
			System.err.println("Exception occured during processing incoming method call");
		} catch(MalformedURLException ex) {
			System.err.println("Check the url String... ");
		}

	}

}
