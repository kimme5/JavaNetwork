package rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/* ---------------------------------------------------------------------------------------
 * 원격 인터페이스를 구현한 클래스
 * 1) HelloImpl.java는 원격 인터페이스 Hello를 구현한 클래스이며
 *    HelloImpl 클래스의 인스턴스가 원격 객체로서 RMI서버에 의해 이름으로 등록됨
 * 2) java.rmi.UnicastRemoteObject를 상속하여 원격 인터페이스를 구현함
 * 3) HelloImpl 객체가 원격 객체로 작동할 수 있는 것은 원격 메소드를 구현해서가 아니라
 *    UnicastRemoteObject 클래스를 상속했기 때문임
 * 4) stub 클래스 생성 : C:\Users\seong\workspace\JavaNetwork\bin> rmic rmiserver.HelloImpl 
 *    --> HelloImpl_Stub.class 생성 
 * --------------------------------------------------------------------------------------- */
public class HelloImpl extends UnicastRemoteObject implements Hello {

	public HelloImpl() throws RemoteException {
		super();
	}
	// 원격 메소드 구현
	public String sayHello(String name) {
		return "Hello World " + name + "!";
	}

}
