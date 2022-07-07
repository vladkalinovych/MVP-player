import java.util.*;

public class Player {


    public String playerNick;
    public String playerName;
    public String playerNumber;
    public String teamName;
    public String playerScores;
    public int playerRating;
    public static String sportName;

    public Player(String playerName, String playerNick, String playerNumber, String teamName, String playerScores) {
        this.playerName = playerName;
        this.playerNick = playerNick;
        this.playerNumber = playerNumber;
        this.teamName = teamName;
        this.playerScores = playerScores;

    }

    public String getPlayerNick() {
        return playerNick;
    }

    public void setPlayerNick(String playerNick) {
        this.playerNick = playerNick;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(String playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPlayerScores() {
        return playerScores;
    }

    public void setPlayerScores(String playerScores) {
        this.playerScores = playerScores;
    }

    public int getPlayerRating() {
        return playerRating;
    }

    public void setPlayerRating(int playerRating) {
        this.playerRating = playerRating;
    }

    public static String getSportName() {
        return sportName;
    }

    public static void setSportName(String sportName) {
        Player.sportName = sportName;
    }


    public static List<Player> GetPlayersList(String[] csvFile) {
        List<Player> players = new ArrayList<>();
        sportName = csvFile[0];
        for (int i = 1; i < 7; i++) {
            List<String> currentLine = Arrays.stream(csvFile[i].split(";")).toList();
            String playerScores = String.join(";", currentLine.subList(4, currentLine.size()));

            players.add(new Player
                    (currentLine.get(0),
                            currentLine.get(1),
                            currentLine.get(2),
                            currentLine.get(3),
                            playerScores
                    )
            );
        }
        return players;
    }

    public static int getTeamScoredPoints(List<Player> players) {
        int teamScoredPoints = 0;
        for (Player player : players) {
            String[] onePlayerScores = player.playerScores.split(";");
            int sum = 0;
            for (String element : onePlayerScores) {
                sum += Integer.parseInt(element);
            }
            teamScoredPoints += sum;
        }
        return teamScoredPoints;
    }


    public static String getWinnerTeam(List<Player> players) {
        Set<String> teams = new HashSet<>();
        List<Player> playerTeam = new ArrayList<>();
        List<Player> playerTeam2 = new ArrayList<>();
        for (Player player : players) {
            teams.add(player.teamName);
        }
        String[] team = teams.toArray(new String[teams.size()]);

        for (Player player : players) {
            boolean isTrue = Objects.equals(player.teamName, team[0]);
            if (isTrue) {
                playerTeam.add(player);

            } else {
                playerTeam2.add(player);
            }
        }

        int firstTeamScoredPoints = getTeamScoredPoints(playerTeam);
        int secondTeamScoredPoints = getTeamScoredPoints(playerTeam2);

        if (firstTeamScoredPoints > secondTeamScoredPoints) {
            return team[0];
        } else if (firstTeamScoredPoints < secondTeamScoredPoints) {
            return team[1];
        } else {
            return team[0];
        }


    }

    public static HashMap<String, Integer> calculateBasketballMVP(List<Player> players, String winnerTeam) {
        HashMap<String, Integer> nickAndPoints = new HashMap<>();
        int playerPoints;
        for (Player player : players) {
            String[] playerScores = player.playerScores.split(";");
            playerPoints = player.teamName == winnerTeam
                    ? Integer.parseInt(playerScores[0]) * 2 + Integer.parseInt(playerScores[1]) + Integer.parseInt(playerScores[2]) + 10
                    : Integer.parseInt(playerScores[0]) * 2 + Integer.parseInt(playerScores[1]) + Integer.parseInt(playerScores[2]);
            nickAndPoints.put(player.playerNick, playerPoints);

        }
        System.out.println(nickAndPoints);
        return nickAndPoints;

    }

    public static HashMap<String, Integer> calculateHandballMVP(List<Player> players, String winnerTeam) {
        HashMap<String, Integer> nickAndPoints = new HashMap<>();
        int playerPoints;
        for (Player player : players) {
            String[] playerScores = player.playerScores.split(";");
            playerPoints = player.teamName == winnerTeam
                    ? Integer.parseInt(playerScores[0]) * 2 + Integer.parseInt(playerScores[1]) + 10
                    : Integer.parseInt(playerScores[0]) * 2 + Integer.parseInt(playerScores[1]);
            nickAndPoints.put(player.playerNick, playerPoints);
        }
        return nickAndPoints;
    }

    public static void CalculateMVP(HashMap<String, Integer> mvp) {

        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : mvp.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }

        System.out.println(" The MVP of the game: " +  maxEntry);

            }

        }

