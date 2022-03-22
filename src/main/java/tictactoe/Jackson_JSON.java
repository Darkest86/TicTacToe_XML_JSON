package tictactoe;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Jackson_JSON implements parser{

    @Override
    public void parse(String path) {
        Gameplay game;
        ObjectMapper objMapper = new ObjectMapper();
        try {
            GameMap map = new GameMap();
            objMapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
            File jsonF = new File(path);
            game = objMapper.readValue(jsonF, Gameplay.class);
            for (steps s : game.steps) {
                map.setMark(s.cellId - 1);
                map.out();
            }
            if (!game.winner.equals("\"Draw\"")) {
                Player winner;
                winner = objMapper.readValue(game.winner, Player.class);
                System.out.println(winner.getName() + " as Player " + winner.getId() + "(" + winner.getMark() + ") is winner");
            }
            else
                System.out.println("Draw between players " + game.players[0].getName() + " and " + game.players[1].getName());
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Gameplay {
        Player[] players;
        ArrayList<steps> steps;
        String winner;

        @JsonProperty(value = "Players")
        public Player[] getPlayers() {
            return players;
        }

        public void setPlayers(Player[] players) {
            this.players = players;
        }
        @JsonProperty(value = "Steps")
        public ArrayList<steps> getSteps() {
            return steps;
        }

        public void setSteps(ArrayList<steps> steps) {
            this.steps = steps;
        }
        @JsonProperty(value = "Game Result")
        public String getWinner() {
            return winner;
        }

        public void setWinner(String winner) {
            this.winner = winner;
        }
    }

    public static Player[] players = new Player[2];
    public static ArrayList<steps> game = new ArrayList<>();
    public static Player gameResult = new Player();

    public static void serialize() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        Gameplay g = new Gameplay();
        g.setPlayers(players);
        g.setSteps(game);
        if (gameResult != null)
            g.setWinner(mapper.writeValueAsString(gameResult));
        else
            g.setWinner("\"Draw\"");
        File f;

        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        File dir = new File(System.getProperty("user.dir") + "\\LogsJSON\\");
        if (!dir.exists())
            dir.mkdir();
        f = new File(dir.getPath() + "\\" + formatForDateNow.format(new Date()) + "_" + players[0].getName() + "_vs_" + players[1].getName() + ".json");
        if (!f.exists())
            try {
                f.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        mapper.writerWithDefaultPrettyPrinter().writeValue(f, g);
    }
}
