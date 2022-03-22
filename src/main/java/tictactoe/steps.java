package tictactoe;

public class steps{
    int id;
    int playerId;
    int cellId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getCellId() {
        return cellId;
    }

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }

    public steps(){
        this.id = 0;
        this.playerId = 0;
        this.cellId = 0;
    }

    public steps(int id, int pid, int cid){
        this.id = id;
        this.playerId = pid;
        this.cellId = cid;
    }
}
