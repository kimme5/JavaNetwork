package rmidbcon.server;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import rmidbcon.common.AnyThing;

/* -----------------------------------------------------------------------------------
 * 클라이언트가 호출할 원격 메소드 toDo()는 AnyThing 객체 타입이며
 * toDo() 메소드 구현에서는 AnyThing 객체의 메소드를 호출하여 비즈니스 로직을 처리함 
 * 결국 클라이언트가 처리할 비즈니스 로직을 클라이언트가 구현해서
 * AnyThing 객체에 담아 보냄
   ----------------------------------------------------------------------------------- */
public interface RemoteIF extends Remote {
	public Serializable toDo(AnyThing any) throws RemoteException;
}
