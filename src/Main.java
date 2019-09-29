import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("select 1 for a new input");
        System.out.println("Select 2 to see the current KD-Ratio");

        Scanner scanner = new Scanner(System.in);
        String getInput = scanner.nextLine();

        if (getInput.equals("1")){
            System.out.print("kills: ");
            int kills = scanner.nextInt();
            System.out.print("deaths: ");
            int deaths = scanner.nextInt();
            DBConnector.insert(kills,deaths);

        } else if (getInput.equals("2")){
            DBConnector.getSumKills();
            DBConnector.getSumDeaths();
            DBConnector.getKdRatio();

        } else {
            System.out.println("No valid option selected.");
        }
    }
}