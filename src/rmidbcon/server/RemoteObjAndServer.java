package rmidbcon.server;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmidbcon.common.AnyThing;

/* --------------------------------------------------------------------------------------------
 * 원격객체 RemoteObjAndServer 클래스는 비즈니스 메소드 toDo()에 대해 
 * 비즈니스 로직이 정의되어 있지 않음 
 * 다만, 파라미터로 전달된 AnyThing 객체의 doThing() 메소드를 호출하고 
 * 이를 return하는 기능만 정의되어 있음
 * 이는 클라이언트에서 복사되어 전달되는 AnyThing 구현 객체 AnyThingImpl 클래스가 정의한 
 * doThing() 메소드에 따라 서버측의 원격 객체 비즈니스 로직이 변경될 수 있음을 나타냄
 * "결국 확장가능한 RMI Application"임을 나타냄
   -------------------------------------------------------------------------------------------- */
public class RemoteObjAndServer extends UnicastRemoteObject implements RemoteIF {
	
	protected RemoteObjAndServer() throws RemoteException {
		super();
	}

	// 원격 메소드 toDo()는 인자로 어떤 객체를 받아서 그 객체의 doThing을 실행함
	public Serializable toDo(AnyThing any) throws RemoteException {
		return any.doThing();
	}

	public static void main(String[] args) {

		try {
			// 원격 객체의 객체 생성
			RemoteIF remoteObj = new RemoteObjAndServer();
			Naming.rebind("rmi://localhost:1099/remoteObjAndServer", remoteObj);
			System.out.println("RemoteObjAndServer registered and ready to service... ");
		} catch(Exception e) {
			System.err.println("Couldn't successfully register remote object " + e);
		}
	
	}

}
