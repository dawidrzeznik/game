package wojnaswiatow;
// Generated 2014-05-14 13:30:52 by Hibernate Tools 3.6.0

public class Players {

     private int playerId;
     private String name;
     private String password;
     private int level;
     private int score;
     private int games;
     private String mail;

    public Players() {
    }

    public Players(int playerId, String name, String password, int level, int score, int games, String mail) {
       this.playerId = playerId;
       this.name = name;
       this.password = password;
       this.level = level;
       this.score = score;
       this.games = games;
       this.mail = mail;
    }
   
    public int getPlayerId() {
        return this.playerId;
    }
    
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public int getLevel() {
        return this.level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    public int getScore() {
        return this.score;
    }
    
    public void setScore(int score) {
        this.score = score;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}