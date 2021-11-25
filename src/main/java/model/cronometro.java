package model;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class cronometro implements Runnable {

	private IntegerProperty hora;
	private IntegerProperty minuto;
	private IntegerProperty segundo;
	private SolicitarSuspender suspendido = new SolicitarSuspender();
	private StringProperty tiempo_hora;
	private StringProperty tiempo_minuto;
	private StringProperty tiempo_segundo;

	public cronometro() {
		this.hora = new SimpleIntegerProperty(0);
		this.minuto = new SimpleIntegerProperty(0);
		this.segundo = new SimpleIntegerProperty(0);
		this.tiempo_hora = new SimpleStringProperty("00");
		this.tiempo_minuto = new SimpleStringProperty("00");
		this.tiempo_segundo = new SimpleStringProperty("00");
		this.suspendido.setSuspendido(false);
	}

	

	public StringProperty getTiempo_hora() {
		return tiempo_hora;
	}



	public void setTiempo_hora(StringProperty tiempo_hora) {
		this.tiempo_hora = tiempo_hora;
	}



	public StringProperty getTiempo_minuto() {
		return tiempo_minuto;
	}



	public void setTiempo_minuto(StringProperty tiempo_minuto) {
		this.tiempo_minuto = tiempo_minuto;
	}



	public StringProperty getTiempo_segundo() {
		return tiempo_segundo;
	}



	public void setTiempo_segundo(StringProperty tiempo_segundo) {
		this.tiempo_segundo = tiempo_segundo;
	}



	public IntegerProperty getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora.set(hora);
	}

	public IntegerProperty getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto.set(minuto);
	}

	public IntegerProperty getSegundo() {
		return segundo;
	}

	public void setSegundo(int segundo) {
		this.segundo.set(segundo);
	}

	public SolicitarSuspender getSuspendido() {
		return suspendido;
	}

	public void setSuspendido(SolicitarSuspender suspendido) {
		this.suspendido = suspendido;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (!this.suspendido.getSuspendido()) {
			
			Platform.runLater(() -> {
				segundo.set(segundo.get() + 1);
				if(segundo.get()<10) {
					tiempo_segundo.set("0"+segundo.get());
				}else {
					tiempo_segundo.set(segundo.get()+"");
				}
				
			});

			if (segundo.get() == 59) {
				Platform.runLater(() -> {
					minuto.set(minuto.get() + 1);
					if(minuto.get()<10) {
						tiempo_minuto.set("0"+minuto.get());
					}else {
						tiempo_minuto.set(minuto.get()+"");
					}
					segundo.set(0);
					tiempo_segundo.set(segundo.get()+"0");
				});

				if (minuto.get() == 59) {
					Platform.runLater(() -> {
						minuto.set(0);
						tiempo_minuto.set(minuto.get()+"");
						hora.set(hora.get() + 1);
						tiempo_hora.set(hora.get()+"");
					});

					if (hora.get() == 24) {
						Platform.runLater(() -> {
							hora.set(0);
							tiempo_hora.set(hora.get()+"");
						});

					}
				}
			}
			try {
				
				Thread.sleep(1000);
				this.suspendido.waitReanudar();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//System.exit(0);
				Platform.runLater(() -> {
					segundo.set(0);
					tiempo_segundo.set(segundo.get()+"0");
					
					minuto.set(0);
					tiempo_minuto.set(minuto.get()+"0");
					
					hora.set(0);
					tiempo_hora.set(hora.get()+"0");
				});
				
			}
			
			
			
			

		
		}
	}

	

}
