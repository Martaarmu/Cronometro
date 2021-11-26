package model;

public class SolicitarSuspender {
private boolean suspendido; //false -> hilo esta corriendo      true -> hilo detenido
	/**
	 * Obtiene el valor de suspendido
	 * @return
	 */
	public boolean getSuspendido() {
		return suspendido;
	}

	/**
	 * Método que modifica el valor de suspendido
	 * @param b
	 */
	public synchronized void setSuspendido (boolean b) {
		this.suspendido=b;
		notifyAll();
	}
	
	/**
	 * Método que espera que se le notifique un cambio en la variable suspendido
	 * @throws InterruptedException
	 */
	public synchronized void waitReanudar() throws InterruptedException {
		while(this.suspendido) {
			wait();
		}
	}
}
