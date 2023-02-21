package rmibasic.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

/* -------------------------------------------------------------------------------------------
 * RMI(Remote Method Invocation)
 * 1) 분산되어 존재하는 객체간의 메시지 전송(메소드 호출하는 것을 포함)을 
 *    가능케 하는 프로토콜
 * 2) RMI 자체는 분산객체간의 통신을 구현하는 모든 프로토콜을 의미함
 *    (CORBA, DCOM...)
 * JAVA RMI를 사용하는 이유
 * 1) 구현의 용이성
 *    JAVA RMI는 Socket 통신 자체를 하부에 숨기고 상위 레벨에서 수행하여
 *    분산 객체간의 데이터 전송을 메소드를 호출하는 것과 같은 방법으로 구현하기 때문에
 *    구현이 용이함
 * 2) 신뢰성의 보장
 *    JAVA 자체에서 제공하는 라이브러리를 사용하여 상위 레벨의 통신계층에서 수행하기 때문에 
 *    신뢰성이 높음
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
