
package teamsapplication;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;


public class TeamUpdateScene {
    
    private static Scene teamUpdateScene;
    private static Team team;
    
    public static void setTeamUpdateScene(Scene parentScene, Stage stage) {
        GridPane pane = new GridPane();
        pane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setHgap(10);
        pane.setVgap(10);
        
        TextField idText = new TextField();
        idText.setPromptText("ID");
        GridPane.setConstraints(idText, 0, 0);
        
        TextField nameText = new TextField();
        nameText.setPromptText("Name");
        GridPane.setConstraints(nameText, 0, 1);
        
        TextField cityText = new TextField();
        cityText.setPromptText("City");
        GridPane.setConstraints(cityText, 0, 2);
        
        TextField playersText = new TextField();
        playersText.setPromptText("Number Of Players");
        GridPane.setConstraints(playersText, 0, 3);
        
        TextField gamesText = new TextField();
        gamesText.setPromptText("Games Played");
        GridPane.setConstraints(gamesText, 0, 4);
        
        Button updateButton = new Button("Update");
        Button cancelButton = new Button("Cancel");
        GridPane.setConstraints(updateButton, 0, 5);
        GridPane.setConstraints(cancelButton, 1, 5);
        
        updateButton.setOnAction(e -> {
            insertTeam(idText.getText().trim(), nameText.getText().trim(), cityText.getText().trim(), playersText.getText().trim(), gamesText.getText().trim());
            idText.clear();
            nameText.clear();
            cityText.clear();
            playersText.clear();
            gamesText.clear();
            cancelButton.fire();
                });
        cancelButton.setOnAction(e -> stage.setScene(parentScene));
        
        pane.getChildren().addAll(idText, nameText, cityText, playersText, gamesText, updateButton, cancelButton);
        teamUpdateScene = new Scene(pane, 300, 250);
    }
    
    public static Scene getTeamUpdateScene() {
        return teamUpdateScene;
    }
    
    private static void insertTeam(String id, String name, String city, String players, String games) {
        try (Connection conn = TeamsDBConnection.getConnection()) {
            String sql = "SELECT * FROM teams WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                if (name.compareTo("") != 0) {
                    sql = "UPDATE teams SET name=? WHERE id=?";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, name);
                    ps.setString(2, id);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Team's name successfully updated", "Notification", JOptionPane.INFORMATION_MESSAGE);
                }
                if (city.compareTo("") != 0) {
                    sql = "UPDATE teams SET city=? WHERE id=?";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, city);
                    ps.setString(2, id);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Team's city successfully updated", "Notification", JOptionPane.INFORMATION_MESSAGE);
                }
                if (players.matches("[0-9]+")) {
                    sql = "UPDATE teams SET players=? WHERE id=?";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, players);
                    ps.setString(2, id);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Team's number of players successfully updated", "Notification", JOptionPane.INFORMATION_MESSAGE);
                }
                if (games.matches("[0-9]+")) {
                    sql = "UPDATE teams SET games=? WHERE id=?";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, games);
                    ps.setString(2, id);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Team's games played successfully updated", "Notification", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
