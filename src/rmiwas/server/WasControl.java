package rmiwas.server;

import java.rmi.Remote;

public interface WasControl extends Remote {
	
	public String startTomcat() throws Exception;
	public String stopTomcat() throws Exception;
	
}
