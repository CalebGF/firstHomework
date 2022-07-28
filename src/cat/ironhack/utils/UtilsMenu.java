package cat.ironhack.utils;
import cat.ironhack.battle.Battle;
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

    /**
     *
     * @param battle
     * @throws IOException
     */
    public static void refreshBattleTxt(Battle battle) throws IOException {
        String battleHeader = "\n\n------------BATTLE PLAYGROUND------------\n";
        String round = "Round :";
        String chooseCharacters = "Choose the character that you want to use, and the opponent to attack:\n";
        String graveyard = "------------GRAVEYARD------------\n";

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
            fileWriter.write(battle.getGraveyard().get(i).getName());
        }
        fileWriter.close();
    }

    private static void generateList(FileWriter fileWriter, Party party1, Party party2) throws IOException {
        String charactersHeader = "\nPARTY(1)----------------PARTY(2)\n";

        fileWriter.write(charactersHeader);
        for (int i = 0; i < Math.max(party1.getCharacters().size(), party2.getCharacters().size()); i++) {
            if(party2.getAliveCharacters().size()<i+1){
                fileWriter.write((i + 1) + "-" + party1.getAliveCharacters().get(i).getName() + "\n");
            }else if(party1.getAliveCharacters().size()<i+1){
                String str = "                        " +(i + 1) +" "+
                        party2.getAliveCharacters().get(i).getName() + "\n";
                fileWriter.write(str);
            }else{
                StringBuilder str = new StringBuilder();
                str.append(i + 1).append(" ").append(party1.getAliveCharacters().get(i).getName());
                int j = 0;
                while(j+party1.getAliveCharacters().get(i).getName().length()<22){
                    str.append(' ');
                    j++;
                }
                str.append((i+1)+" "+party2.getAliveCharacters().get(i).getName()).append("\n");
                fileWriter.write(str.toString());
            }
        }
    }
}
