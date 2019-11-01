
package teamsapplication;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Team {
    
    private SimpleIntegerProperty id = new SimpleIntegerProperty();
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleStringProperty city = new SimpleStringProperty();
    private SimpleIntegerProperty players = new SimpleIntegerProperty();
    private SimpleIntegerProperty games = new SimpleIntegerProperty();
    
    public Team() {
        
    }
    
    public Team(int id, String name, String city, int players, int games) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.city = new SimpleStringProperty(city);
        this.players = new SimpleIntegerProperty(players);
        this.games = new SimpleIntegerProperty(games);
    }
    
    public int getId() {
        return id.get();
    }
    
    public String getName() {
        return name.get();
    }
    
    public String getCity() {
        return city.get();
    }
    
    public int getPlayers() {
        return players.get();
    }
    
    public int getGames() {
        return games.get();
    }
    
    public void setId(int id) {
        this.id.set(id);
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    
    public void setCity(String city) {
        this.city.set(city);
    }
    
    public void setPlayers(int players) {
        this.players.set(players);
    }
    
    public void setGames(int games) {
        this.games.set(games);
    }
}
