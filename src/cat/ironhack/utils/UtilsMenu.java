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
        Character player1 = battle.getParty1().getAliveCharacters().get(player1Id);//get the players by index received
        Character player2 = battle.getParty1().getAliveCharacters().get(player2Id);

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
        String battleHeader = "\n\n"+"-".repeat(12)+"BATTLE PLAYGROUND"+"-".repeat(12)+"\n";
        String round = "Round :";
        String chooseCharacters = "Choose the character that you want to use, and the opponent to attack:\n";
        String graveyard = "\n"+"-".repeat(61)+"GRAVEYARD"+"-".repeat(61)+"\n";

        FileWriter fileWriter = new FileWriter("src/resources/menu/battleRound.txt", false);
        fileWriter.write(battleHeader);
        fileWriter.write(round+battle.getRound()+"\n");
        fileWriter.write(chooseCharacters);

        if(battle.getRound()%2==1) {
            generateList(fileWriter, battle.getParty1(), battle.getParty2());
        }else{
            generateList(fileWriter, battle.getParty2(), battle.getParty1());
        }
        fileWriter.write(graveyard);
        for (int i = 0; i < battle.getGraveyard().size(); i++) {
            fileWriter.write("-"+battle.getGraveyard().get(i).getName()+"-");
        }
        fileWriter.close();
    }
    //method to generate the list of each party's characters
    private static void generateList(FileWriter fileWriter, Party party1, Party party2) throws IOException {
        String charactersHeader = "\n"+"-".repeat(23)+"PARTY(1)"+"-".repeat(68)+"PARTY(2)"+"-".repeat(23)+"\n";

        fileWriter.write(charactersHeader);
        for (int i = 0; i < Math.max(party1.getCharacters().size(), party2.getCharacters().size()); i++) {
            if(party2.getAliveCharacters().size()<i+1){ //getCharacters
                int index = party1.getIndexFromCharacter(party1.getAliveCharacters().get(i));
                fileWriter.write((index+1) + "-" + party1.getAliveCharacters().get(i).toString().concat("\n"));
            }else if(party1.getAliveCharacters().size()<i+1){
                int index = party2.getIndexFromCharacter(party2.getAliveCharacters().get(i));
                String str = "                        " +(index+1) +" "+
                        party2.getAliveCharacters().get(i).toString().concat("\n");
                fileWriter.write(str);
            }else{
                int index = party1.getIndexFromCharacter(party1.getAliveCharacters().get(i));
                StringBuilder str = new StringBuilder();
                str.append(index + 1).append(" ").append(party1.getAliveCharacters().get(i).toString());
                int j = 0;
                while(j+party1.getAliveCharacters().get(i).getName().length()<22){
                    str.append(' ');
                    j++;
                }
                int index2 = party2.getIndexFromCharacter(party2.getAliveCharacters().get(i));
                str.append((index2+1)+" "+party2.getAliveCharacters().get(i).toString().concat("\n"));
                fileWriter.write(str.toString());
            }
        }
    }
}
