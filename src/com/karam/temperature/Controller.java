package com.karam.temperature;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
	@FXML
	public Label welcomeLabel;
	@FXML
	public ChoiceBox<String> choiceBox;
	@FXML
	public TextField userInput;
	@FXML
	public Button convertBtn;
	public static final String C_TO_F_TEXT="Celsius to Fahrenheit";
	public static final String F_TO_C_TEXT="Fahrenheit to celsius";
	private boolean isC_T_F=true;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		choiceBox.getItems().add(C_TO_F_TEXT);
		choiceBox.getItems().add(F_TO_C_TEXT);
		choiceBox.setValue(C_TO_F_TEXT);


		choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
			if(newValue.equals(isC_T_F)){//if user has selected "Celsius to Fahrenheit"
				isC_T_F=true;
			}else{
				isC_T_F=false;//if user has selected "Fahrenheit to celsius"
			}

		});

		convertBtn.setOnAction(event -> {
			convert();
		});
	}

	private void convert() {
		String input=userInput.getText();//24==>>"24"
		float enteredTemperature=0.0f;
		try {
			enteredTemperature=Float.parseFloat(input);
		}catch(Exception e){
			warnUser();
			return;
			//after return no code will be executed
		}

		float newTemperature=0.0f;

		if(isC_T_F){
			newTemperature=(enteredTemperature*9/5)+32;
		}else{
			newTemperature=(enteredTemperature-32)*5/9;
		}
		display(newTemperature);
	}

	private void warnUser() {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error Occurred");
		alert.setHeaderText("Invalid Temperature Entered");
		alert.setContentText( "Please enter a valid temperature");
		alert.show();
	}

	private void display(float newTemperature) {
		String unit=isC_T_F? "F":"C";
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Result");
		alert.setContentText( "The New Temperature is: "+newTemperature + unit);
		alert.show();
	}

}
