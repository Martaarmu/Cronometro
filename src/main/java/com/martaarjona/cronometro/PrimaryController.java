package com.martaarjona.cronometro;

import java.io.IOException;
import java.util.Observable;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.SolicitarSuspender;
import model.cronometro;

public class PrimaryController {

	@FXML
	private Button btn_iniciar;
	@FXML
	private Button btn_parar;
	@FXML
	private Label label_segundo;
	@FXML
	private Label label_minuto;
	@FXML
	private Label label_hora;

	cronometro c = new cronometro();
	Thread t = new Thread();

	/**
	 * Método que inicializa la escena
	 */
	@FXML
	void initialize() {
		
		this.btn_parar.setDisable(true);
		this.label_segundo.textProperty().bind(c.getTiempo_segundo());
		this.label_minuto.textProperty().bind(c.getTiempo_minuto());
		this.label_hora.textProperty().bind(c.getTiempo_hora());
	}
	
	/**
	 * Método que se ejecutará cuando se pulse el BOTÓN INICIAR
	 * @param event
	 */
	@FXML
	void iniciar(ActionEvent event) {
		
		c.getSuspendido().setSuspendido(false);
		t = new Thread(c);
		t.start();

		this.label_segundo.textProperty().bind(c.getTiempo_segundo());
		this.label_minuto.textProperty().bind(c.getTiempo_minuto());
		this.label_hora.textProperty().bind(c.getTiempo_hora());
		
		this.btn_parar.setDisable(false);

		this.btn_iniciar.setDisable(true);
		this.btn_iniciar.setText("CONTANDO");

	}
	
	/**
	 * Método que se ejecutará cuando se pulse el BOTÓN PARAR
	 * @param event
	 */
	@FXML
	void parar(ActionEvent event) {
		
		if(this.btn_parar.getText().equals("PARAR")) {
			c.getSuspendido().setSuspendido(true);

			this.btn_iniciar.setText("DETENIDO");
			this.btn_parar.setText("REANUDAR");
		}else {
			c.getSuspendido().setSuspendido(false);

			this.btn_iniciar.setText("CONTANDO");
			this.btn_parar.setText("PARAR");
		}
		

	}
	/**
	 * Método que se ejecutará cuando se pulse el BOTÓN REINICIAR
	 * @param event
	 */
	@FXML
	void reiniciar(ActionEvent event) {

		t.interrupt();
		c.getSuspendido().setSuspendido(true);
		this.btn_iniciar.setDisable(false);
		this.btn_iniciar.setText("INICIAR");
		this.btn_parar.setText("PARAR");
		this.btn_parar.setDisable(true);
		
	}

}
