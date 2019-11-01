
package teamsapplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javax.swing.JOptionPane;


public class TeamDeleteScene {
    
    private static Scene teamDeleteScene;
    
    private static void deleteTeam(String id) {
        try (Connection conn = TeamsDBConnection.getConnection()) {
            String sql = "SELECT * FROM teams WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sql = "DELETE FROM teams WHERE id=?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Team successfully deleted", "Notification", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public static void setTeamDeleteScene(Scene parentScene, Stage stage) {
        GridPane pane = new GridPane();
        pane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setVgap(10);
        pane.setHgap(10);
        
        TextField deleteField = new TextField();
        deleteField.setPromptText("ID");
        GridPane.setConstraints(deleteField, 0, 0);
        
        final Label label = new Label();
        GridPane.setConstraints(label, 0, 3);
        label.setText("(Type in the ID of a team you want to delete)");
        GridPane.setColumnSpan(label, 2);
        
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> stage.setScene(parentScene));        
        
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            deleteTeam(deleteField.getText());
            deleteField.clear();
            cancelButton.fire();
                });

        GridPane.setConstraints(deleteButton, 0, 1);
        GridPane.setConstraints(cancelButton, 1, 1);
        pane.getChildren().addAll(deleteField, deleteButton, cancelButton, label);
        
        teamDeleteScene = new Scene(pane, 300, 200);
    }
    
    public static Scene getTeamDeleteScene() {
        return teamDeleteScene;
    }
}
