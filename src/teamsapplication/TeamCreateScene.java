
package teamsapplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class TeamCreateScene {

    private static Scene teamCreateScene;
    private static Team team = new Team();
    
    public static void setTeamCreateScene(Scene parentScene, Stage parentStage) {
        team = new Team();
        GridPane pane = new GridPane();
        pane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(10);
        pane.setHgap(10);
        
        TextField nameText = new TextField();
        nameText.setPromptText("Name");
        GridPane.setConstraints(nameText, 0, 0);
        
        TextField cityText = new TextField();
        cityText.setPromptText("City");
        GridPane.setConstraints(cityText, 0, 1);
        
        TextField playersText = new TextField();
        playersText.setPromptText("Number Of Players");
        GridPane.setConstraints(playersText, 0, 2);
        
        TextField gamesText = new TextField();
        gamesText.setPromptText("Games Played");
        GridPane.setConstraints(gamesText, 0, 3);
        
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            parentStage.setScene(parentScene);
                });
        
        Button submitButton = new Button("Submit");
        submitButton.setOnAction((ActionEvent e) -> {
            try {
                String name = nameText.getText();
                String city = cityText.getText();
                int players = Integer.parseInt(playersText.getText());
                int games = Integer.parseInt(gamesText.getText());
                if (name != null && city != null) {
                    team.setName(name);
                    team.setCity(city);
                    team.setPlayers(players);
                    team.setGames(games);
                    insertTeam(team);
                    nameText.clear();
                    cityText.clear();
                    playersText.clear();
                    gamesText.clear();
                    cancelButton.fire();
                }
            } catch (Exception ex) {
                System.out.println(e);
            }
        });

        
        GridPane.setConstraints(submitButton, 0, 4);
        GridPane.setConstraints(cancelButton, 1, 4);
        
        pane.getChildren().addAll(nameText, cityText, playersText, gamesText, submitButton, cancelButton);
        
        teamCreateScene = new Scene(pane, 300, 200);
    }
    
    public static Scene getTeamCreateScene() {
        return teamCreateScene;
    }
    
    private static void insertTeam(Team team) {
        try (Connection conn = TeamsDBConnection.getConnection()) {
            String sql = "INSERT INTO teams (name, city, players, games) VALUES (?, ?, ?, ?)";
        
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, team.getName());
            statement.setString(2, team.getCity());
            statement.setString(3, Integer.valueOf(team.getPlayers()).toString());
            statement.setString(4, Integer.valueOf(team.getGames()).toString());
            
            int rowsInserted = statement.executeUpdate();
            
            if (rowsInserted > 0){
                JOptionPane.showMessageDialog(null, "Team added successfully", "Notification", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
}

