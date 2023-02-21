package rmidbcon.client;

import java.rmi.Naming;
import java.util.ArrayList;

import rmidbcon.common.AnyThing;
import rmidbcon.common.AnyThingImpl;
import rmidbcon.server.RemoteIF;

/* ---------------------------------------------------------------------------------------------
 * RMIŬ���̾�Ʈ - RmiClient 
 * 1) RmiClient Ŭ������ RMI���� Application���� ������ ���ݰ�ü RemoteObjAndServer�� 
 *    remoteObjAndServer��� �̸����� ã�� ���� �޼ҵ� toDo()�� ȣ���ϴ� ���� ������
 * 2) RmiClient Ŭ������ ���� reference remoteObj�� ���� ���� �޼ҵ� toDo()�� ȣ���ϱ� ����
 *    AnyThingImpl ��ü�� �����ϰ� �� ��ü�� ���� �޼ҵ� toDo() ȣ��ÿ� �Ķ���ͷ� �����
 * 3) ���� �޼ҵ��� toDo()�� �Ķ���� Ÿ���� AnyThing �������̽��̰�, 
 *    ������ �̰��� ����ü�� AnyThingImpl�� �ν��Ͻ��� ���ڷ� ���޵�
 * ������
 * 1) �ܼ�â�� ���� stub Ŭ���� ���� 
 *  - C:\Users\seong\workspace\JavaNetwork\bin>rmic rmidbcon.server.RemoteObjAndServer ����
 *  - C:\Users\seong\workspace\JavaNetwork\bin\rmidbcon\server\RemoteObjAndServer_Stub.class �������� Ȯ��
 * 2) C:\Users\seong\workspace\JavaNetwork\bin\rmidbcon\server>start rmiregistry 1099 ����
 * 3) eclipse���� RemoteObjAndServer ���� �� Run As../Java Application ����
 * 4) eclipse���� RmiClient ���� �� Run As../Java Application ����
 * 5) Result for Query : SELECT CITY_NAME FROM CITY ���� ��� ���
   --------------------------------------------------------------------------------------------- */
public class RmiClient {

	public static void main(String[] args) {
		
		String rmiString = null;
//		String sql = "select ename from emp";
		// PostgreSQL DB���
		String sql = "SELECT CITY_NAME FROM CITY";
		try {
			RemoteIF remoteObj = (RemoteIF) Naming.lookup("rmi://localhost:1099/remoteObjAndServer");
			AnyThing anyThing = new AnyThingImpl(sql);
			
			System.out.println("Result for Query : " + sql);
			ArrayList<String> arrayList = (ArrayList<String>) remoteObj.toDo(anyThing);
			
			for(String s : arrayList) {
				System.out.println(s);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
