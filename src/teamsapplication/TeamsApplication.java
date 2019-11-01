
package teamsapplication;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;


public class TeamsApplication {
    
    private static Scene teamsApplication;
    
    public static void setTeamsApplication(Stage parentStage) {
        VBox pane1 = new VBox(10);
        pane1.setPadding(new Insets(10, 10, 10, 10));
        pane1.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        

        teamsApplication = new Scene(pane1, 300, 250);
        
        TeamCreateScene.setTeamCreateScene(teamsApplication, parentStage);
        Scene teamCreateScene = TeamCreateScene.getTeamCreateScene();
        
        Button createButton = new Button("Add team");
        createButton.setOnAction((ActionEvent event) -> {
            parentStage.setScene(teamCreateScene);
        });
        
        TeamReadScene.setTeamReadScene(teamsApplication, parentStage);
        Scene teamReadScene = TeamReadScene.getTeamReadScene();
        
        Button readButton = new Button("View all teams");
        readButton.setOnAction(e -> {
            TeamReadScene.setData();
            parentStage.setScene(teamReadScene);
        });
        
        TeamUpdateScene.setTeamUpdateScene(teamsApplication, parentStage);
        Scene teamUpdateScene = TeamUpdateScene.getTeamUpdateScene();
        
        Button updateButton = new Button("Update team's info");
        updateButton.setOnAction(e -> parentStage.setScene(teamUpdateScene));
        
        TeamDeleteScene.setTeamDeleteScene(teamsApplication, parentStage);
        Scene teamDeleteScene = TeamDeleteScene.getTeamDeleteScene();
        
        Button deleteButton = new Button("Delete team");
        deleteButton.setOnAction(e -> parentStage.setScene(teamDeleteScene));
        
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> parentStage.close());
        
        pane1.getChildren().addAll(createButton, readButton, updateButton, deleteButton, closeButton);
        
        /**parentStage.setTitle("TeamsApplication");
        parentStage.setScene(teamsApplication);
        parentStage.show();**/
        
        
    }
    
    public static Scene getTeamsApplication() {
        return teamsApplication;
    }

}
