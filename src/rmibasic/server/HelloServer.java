package rmibasic.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/* -------------------------------------------------------------------------------------------
 * RMI서버 Application : HelloServer
 * 1) RMI에서 원격 객체를 생성하고 이를 이름으로 등록한 후 
 *    원격 객체에 대한 클라이언트의 서비스 요청을 기다림
 * 2) RMI서버 application은 원격 인터페이스 구현 클래스의 인스턴스를 생성하여 
 *    이를 rmiregistry에 이름으로 등록함
 * 3) 원격 객체는 자신을 원격의 클라이언트가 접근할 수 있도록 등록할 수 있어야 하며
 *    클라이언트에서 원격 메소드 요청이 발생했는지 지속적으로 모니터링할 수 있어야 함
 *    하지만, 원격 인터페이스에는 이러한 기능은 정의되지 않았음
 * ------------------------------------------------------------------------------------------- */
public class HelloServer {

	public static void main(String[] args) {
		
		try {
			// 원격 객체를 생성하여 이를 rmiregistry에 "HelloRemote"라는 이름으로 등록함
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
