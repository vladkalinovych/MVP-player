import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Reader {
    public static  String [] reader() throws FileNotFoundException {
        String[] csvFile;
        File basketballFile = new File("Basketball.csv");
        File handballFile = new File("Handball.csv");
        File file = null;
        System.out.println("Choose the game:");
        System.out.println("1 - Basketball");
        System.out.println("2 - Handball");
        Scanner sc = new Scanner(System.in);
        int resultNumber = sc.nextInt();
            if(resultNumber == 1){
                file = basketballFile;
            }
            else if(resultNumber == 2 ){
                file = handballFile;
            }
            else{
                System.out.println("There is no such answer");
            }
        Scanner scanner = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
         csvFile = lines.toArray(new String[lines.size()]);
        System.out.println(Arrays.toString(csvFile));
        return csvFile;
    }

    public static void main(String[] args) throws IOException {
        String[] csvFile = reader();
        List <Player> players = Player.GetPlayersList(csvFile);
        String winnerTeam = Player.getWinnerTeam(players);
        String bc = csvFile[0];
        if (!"Handball".equalsIgnoreCase(csvFile[0])){
            Player.CalculateMVP(Player.calculateHandballMVP(players,winnerTeam));
        }
        else if (!"Basketball".equalsIgnoreCase(csvFile[0])) {
            Player.CalculateMVP(Player.calculateBasketballMVP(players,winnerTeam ));
        }
    }
}


