package com.karam.temperature;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

import static javafx.application.Application.launch;

public class MyMain extends Application {
	public static void main(String[] args){
		//System.out.println("main");//First the main method will be executed
		launch(args);
	}

	//lifecycleMethod
	@Override
	public void init() throws Exception {
		System.out.println("Init");//Initilize your Application
		super.init();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("Start"); //start your application
		FXMLLoader loader = new FXMLLoader(getClass().getResource("app_layout.fxml"));
		VBox rootNode = loader.load();
		MenuBar menuBar=createMenue();
		rootNode.getChildren().add(0,menuBar);//menu bar at 0th index

		Scene scene = new Scene(rootNode);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Temperature Converter Tool");
		//primaryStage.setResizable(false);//it will make the screen fix
		primaryStage.show();//make the application visible to the user

	}
	public MenuBar createMenue(){
		//Creating File Menu
		Menu fileMenu=new Menu("File");
		MenuItem newmenuItem=new MenuItem("New");

		//LAMBDA FUNCTION
		newmenuItem.setOnAction(event -> {
			System.out.println("New Menu Item Clicked");
			//more code
		});

		//adding seperator
		SeparatorMenuItem separatorMenuItem=new SeparatorMenuItem();
		MenuItem quitmenuItem=new MenuItem("Quit");

		//LAMBDA FUNCTION
		quitmenuItem.setOnAction(event -> {
			Platform.exit();//shutt down the applocation
			System.exit(0);//shutt down the current Virtual machiene
		});
		fileMenu.getItems().addAll(newmenuItem,separatorMenuItem,quitmenuItem);

		//Help menue
		Menu helpMenu=new Menu("Help");
		MenuItem aboutApp=new MenuItem("About");

		//LAMBDA FUNCTION
		aboutApp.setOnAction(event -> aboutapp());
		helpMenu.getItems().addAll(aboutApp);

		//Menue Bar
		MenuBar menuBar=new MenuBar();
		menuBar.getMenus().addAll(fileMenu,helpMenu);
		return menuBar;
	}

	private void aboutapp() {
		Alert alertDialog=new Alert(Alert.AlertType.INFORMATION);
		alertDialog.setTitle("My First DeskTop App");
		alertDialog.setHeaderText("Learning JavaFx");
		alertDialog.setContentText("I am a beginner but soon I will be a pro!!!");


		//Setting up own custom button
		ButtonType yesBtn=new ButtonType("Yes");
		ButtonType noBtn=new ButtonType("No");
		alertDialog.getButtonTypes().setAll(yesBtn,noBtn);

		Optional<ButtonType> clickedBtn=alertDialog.showAndWait();//Very Important
		if (clickedBtn.isPresent() && clickedBtn.get()==yesBtn){
			System.out.println("Yes button is clicked");
		}else{
			System.out.println("No buutton is Clicked");
		}

	}

	@Override
	public void stop() throws Exception {
		System.out.println("Stop");//Stop Your Application and is about to shutdown
		super.stop();
	}
}
