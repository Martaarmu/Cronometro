package model;

public class SolicitarSuspender {
private boolean suspendido; //false -> hilo esta corriendo      true -> hilo detenido
	
	public boolean getSuspendido() {
		return suspendido;
	}

	public synchronized void setSuspendido (boolean b) {
		this.suspendido=b;
		notifyAll();
	}
	
	public synchronized void waitReanudar() throws InterruptedException {
		while(this.suspendido) {
			wait();
		}
	}
}
