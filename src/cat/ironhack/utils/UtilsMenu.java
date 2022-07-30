package cat.ironhack.utils;
import cat.ironhack.battle.Battle;
import cat.ironhack.character.Character;
import cat.ironhack.party.Party;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UtilsMenu {
    public static String readMenu(String menu) throws IOException {
        return Files.readString(Paths.get("src/resources/menu/"+menu+".txt"));
    }

    public static String readOption(String option) throws IOException {
        return Files.readString(Paths.get("src/resources/option/"+option+".txt"));
    }

    public static String refreshBattleScreen(String battleRound) throws IOException {
        return Files.readString(Paths.get("src/resources/battleRound.txt"));
    }
    //method to generate the menu shown after a fight between two characters is over
    public static void roundMenu(Battle battle, String id1, String id2) throws IOException {
        String roundHeader = "-".repeat(17)+"ROUND IS OVER"+"-".repeat(17)+"\n";
        FileWriter fileWriter = new FileWriter("src/resources/menu/roundScreen.txt", false);
        fileWriter.write(roundHeader);
        int player1Id = Integer.parseInt(id1);
        int player2Id = Integer.parseInt(id2);
        Character player1 = battle.getParty1().getCharacters().get(player1Id-1);//get the players by index received
        Character player2 = battle.getParty2().getCharacters().get(player2Id-1);

        String killed = "====KILLED===>>";
        String dead = "<<===YOU ARE DEAD===>>";
        if (player1.isAlive()){//if player 1 won the fight, we build the string showing him as winner, or otherwise.
            fileWriter.write("         "+player1.getName().toUpperCase()+
                    killed+player2.getName().toUpperCase()+"\n");
            fileWriter.write("\nStats: "+player1);
        }else if(player2.isAlive()){
            fileWriter.write(player2.getName().toUpperCase()+
                    killed+player1.getName().toUpperCase());
            fileWriter.write("\nStats: "+player2);
        }else{
            fileWriter.write(player2.getName().toUpperCase()+
                    dead+player1.getName().toUpperCase());
        }
        fileWriter.close();
    }

    //method to refresh the battle status and show current characters listed by party and dead characters
    public static void refreshBattleTxt(Battle battle) throws IOException {
        FileWriter fileWriter = new FileWriter("src/resources/menu/battleRound.txt", false);
        generateList(fileWriter,battle);
        fileWriter.close();
    }
    //method to generate the list of each party's characters
    private static void generateList(FileWriter fileWriter, Battle battle) throws IOException {
        String charactersHeader = "\n"+"-".repeat(40)+"PARTY(1)"+"-".repeat(84)+"PARTY(2)"+"-".repeat(40)+"\n"; //180 ->90
        StringBuilder sb = new StringBuilder();
        sb.append(charactersHeader);
        int j = Math.min(battle.getParty1().getCharacters().size(), battle.getParty2().getCharacters().size());
        boolean isParty1Min = j==battle.getParty1().getCharacters().size();
        for (int i = 0; i < Math.max(battle.getParty1().getCharacters().size(), battle.getParty2().getCharacters().size()); i++) {
            String char1 = "";
            String char2 = "";
            if (isParty1Min) { //Party 1 is the minimum
                if (i <= j - 1) {
                    if (battle.getParty1().getCharacters().get(i).isAlive())
                        char1 = (i+1)+"-"+battle.getParty1().getCharacters().get(i).toString();
                }
                if (battle.getParty2().getCharacters().get(i).isAlive())
                    char2 = (i+1)+"-"+battle.getParty2().getCharacters().get(i).toString();

            } else { //Party 2 is the minimum
                if (battle.getParty1().getCharacters().get(i).isAlive())
                    char1 = (i+1)+"-"+battle.getParty1().getCharacters().get(i).toString();
                if (i <= j - 1) {
                    if (battle.getParty2().getCharacters().get(i).isAlive())
                        char2 = (i+1)+"-"+battle.getParty2().getCharacters().get(i).toString();
                }
            }
            int countSpaces = 180 - (char1.length() + char2.length());
            if(countSpaces<180) sb.append(char1).append(" ".repeat(countSpaces)).append(char2).append("\n");
        }

        StringBuilder sb2 = new StringBuilder();
        String graveyard = "\n"+"-".repeat(85)+"GRAVEYARD"+"-".repeat(86)+"\n";
        sb2.append(graveyard);
        for (int i = 0; i < battle.getGraveyard().size(); i++) {
            sb2.append("-").append(battle.getGraveyard().get(i).getName()).append("-\n");
        }
        sb2.append("\n\n\n\n");
        sb.append(sb2);
        fileWriter.write(sb.toString());

    }
}
