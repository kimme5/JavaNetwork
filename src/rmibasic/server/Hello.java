package rmibasic.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

/* -------------------------------------------------------------------------
 * 1. RMI(Remote Method Invocation) 정의
 * 1) 분산되어 존재하는 객체간의 메시지 전송(메소드 호출하는 것을 포함)을 
 *    가능케 하는 프로토콜
 * 2) 직렬화된 Java 클래스의 전송, 원격 프로시저 호출(RPC)과 같은 
 *    객체지향적인 원격 메소드 호출을 수행하는 API
 * 3) RMI 자체는 분산 객체간의 통신을 구현하는 모든 프로토콜을 의미함
 *    (CORBA, DCOM...)
 * 4) 기존 Java 언어의 장점과 풍부한 API로 인해 분산 객체 기술 사용이 용이함
 * 5) 다른 실행 환경에 있는 객체(분산 객체)의 메소드를 로컬에서 생성한 객체의 메소드와 
 *    동일하게 호출할 수 있도록 하는 "Java의 분산 객체 기술"임
 *    즉, RMI를 사용함으로써 다른 JVM에서 실행중인 객체의 메소드를 호출할 수 있음
 * 6) RMI는 2개의 객체(Client쪽의 Stub과 Server쪽의 Skeleton)을 사용하여 
 *    application간의 원격 통신을 제공함      
 * 2. JAVA RMI를 사용하는 이유
 * 1) 구현의 용이성
 *    JAVA RMI는 Socket 통신 자체를 하부에 숨기고 상위 레벨에서 수행하여
 *    분산 객체간의 데이터 전송을 메소드를 호출하는 것과 같은 방법으로 구현하기 때문에
 *    구현이 용이함
 * 2) 신뢰성의 보장
 *    JAVA 자체에서 제공하는 라이브러리를 사용하여 상위 레벨의 통신계층에서 수행하기 때문에 
 *    신뢰성이 높음
 * 3. RMI 생성 과정
 * 1) 원격 인터페이스 정의 --> Hello.java
 *   - 원격 객체 사이의 메세지 전송을 위한 것
 *   - Java RMI에서는 RMI클라이언트와 RMI서버간의 메세지 전송을 위한 방법으로 Interface를 이용함
 *   - 즉, Interface에서 정의한 메소드로 클라이언트와 서버간의 통신이 이루어짐
 * 2) 원격 인터페이스의 메소드를 구현한 클래스 생성 --> HelloImpl.java
 * 3) RMI 서버Application 구현 --> HelloServer.java
 *   - 원격 인터페이스를 구현한 클래스(원격개체)의 인스턴스를 생성하고 이를 
 *     원격의 RMI클라이언트가 접근하여 구현된 비즈니스 메소를 원격 호출할 수 있도록 함
 * 4) RMI 클라이언트 Application 구현 --> HelloClient.java
 * 4. 원격 인터페이스(Remote Interface)
 * 1) 원격 인터페이스에서 정의한 모든 메소드는 java.rmi.RemoteException이나
 *    부모클래스(java.io.IOExcepion, java.lang.Exception)을 throws해야 함
 *    (RMI spec에서는 RemoteException을 throws하도록 권고하고 있음)  
 * ------------------------------------------------------------------------- */
public interface Hello extends Remote {
	public String sayHello(String name) throws RemoteException;
}
