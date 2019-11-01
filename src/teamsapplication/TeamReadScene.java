
package teamsapplication;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;


public class TeamReadScene {
    
    private static Scene teamReadScene;
    private static ObservableList<Team> data = FXCollections.observableArrayList();
    private static TableView tableView = new TableView();
    
    public static void setData() {
        
        try (Connection conn = TeamsDBConnection.getConnection()) {
            data.clear();
            String sql = "SELECT * FROM teams";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Team team = new Team(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
                data.add(team);
            }
            
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    private static void setTableView() {
        
        TableColumn idColumn = new TableColumn("ID");
        TableColumn nameColumn = new TableColumn("Name");
        TableColumn cityColumn = new TableColumn("City");
        TableColumn playersColumn = new TableColumn("Number Of Players");
        TableColumn gamesColumn = new TableColumn("Games Played");
        
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        playersColumn.setCellValueFactory(new PropertyValueFactory<>("players"));
        gamesColumn.setCellValueFactory(new PropertyValueFactory<>("games"));
        
        tableView.setItems(data);
        tableView.getColumns().addAll(idColumn, nameColumn, cityColumn, playersColumn, gamesColumn);
        
    }
    
    public static void setTeamReadScene(Scene parentScene, Stage parentStage) {
        
        setData();
        setTableView();
        
        BorderPane pane = new BorderPane();
        
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> parentStage.setScene(parentScene));
        
        pane.setCenter(tableView);
        pane.setBottom(cancelButton);
        
        teamReadScene = new Scene(pane, 300, 200);
    }
    
    public static Scene getTeamReadScene() {
        return teamReadScene;
    }
    
}
