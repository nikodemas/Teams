
package teamsapplication;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Background;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

 
public class RegistrationFormApplication extends Application {
 
    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root, 300, 150);
        stage.setScene(scene);
        stage.setTitle("Log In");
 
        GridPane grid = new GridPane();
        grid.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
 
        scene.setRoot(grid);
 
        TextField name = new TextField();
        name.setPromptText("Username");
        GridPane.setConstraints(name, 0, 0);
        grid.getChildren().add(name);
 
        Label label1 = new Label("Password");
        PasswordField pb = new PasswordField(); 
        pb.setPromptText("Password");
        GridPane.setConstraints(pb, 0, 2);
        grid.getChildren().add(pb);
 
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 0, 3);
        grid.getChildren().add(submit);
 
 
        final Label label = new Label();
        GridPane.setConstraints(label, 0, 4);
        GridPane.setColumnSpan(label, 2);
        grid.getChildren().addAll(label);
        
        TeamsApplication.setTeamsApplication(stage);
        Scene teamsApplication = TeamsApplication.getTeamsApplication();
 
        submit.setOnAction((ActionEvent e) -> {
            if(!pb.getText().equals("password")||!name.getText().equals("admin")){
                label.setText("Incorrect username or password");
            } else {
                stage.setScene(teamsApplication);
                stage.setTitle("Application");
            }
        });

 
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}


