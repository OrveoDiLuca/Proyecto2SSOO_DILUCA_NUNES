/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import proyecto_diluca_nunes.Main;
import static proyecto_diluca_nunes.Main.battleInterface;
import static proyecto_diluca_nunes.Main.StarWars;
import static proyecto_diluca_nunes.Main.StarTrek;
/**
 *
 * @author orveodiluca
 */
public class Simulation extends Thread{
    private Studio studio1;
    private Studio studio2;
    private Admin admin;
    private Queue winnersQueue;
    private int battleDuration;
    private AI AI;

    public Simulation(String studio1Name, String studio2Name, int battleDuration) {
        this.studio1 = new Studio(studio1Name);
        this.studio2 = new Studio(studio2Name);
        this.admin = new Admin(studio1, studio2, this); //Hacer Admin
        this.winnersQueue = new Queue();
        this.battleDuration = battleDuration;
        this.AI = new AI(this); //Hacer AI
    }

    public void operate() {

        while (true) {
            StudioCharacter[] chosenCharacters = getAdmin().chooseCharactersBattle(); //Funcion de admin. 
            //Each cycle, the admin will start by choosing both characters that are going to battle
            if (chosenCharacters == null) {
                System.out.println("-------Game ended--------"); //If no characters were found in one studio, then the game will end
                break;
            } else {
                battleInterface.getLevel1RegularShow().setText(cartoonNetwork.getPrior1Queue().printQueue()); //BattleInterface
                battleInterface.getLevel2RegularShow().setText(cartoonNetwork.getPrior2Queue().printQueue());//RegularSHOW es startrek
                battleInterface.getLevel3RegularShow().setText(cartoonNetwork.getPrior3Queue().printQueue());
                battleInterface.getReinforceRegularShow().setText(cartoonNetwork.getReinfQueue().printQueue());

                battleInterface.getLevel1Avatar().setText(nickelodeon.getPrior1Queue().printQueue()); //Cosas de avatar que serian para mi StarWars
                battleInterface.getLevel2Avatar().setText(nickelodeon.getPrior2Queue().printQueue());
                battleInterface.getLevel3Avatar().setText(nickelodeon.getPrior3Queue().printQueue());
                battleInterface.getReinforceAvatar().setText(nickelodeon.getReinfQueue().printQueue());
                int tenth = (int) (getBattleDuration() * 0.10);
                AI.setStatus("waiting");
                try {
                    Main.battleInterface.getAiStatus().setText("Esperando");
                    sleep(1000);
                    Main.battleInterface.getResult().setText("");

                } catch (InterruptedException ex) {
                    Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
                }
                StudioCharacter character1 = chosenCharacters[0]; //Studio 1 Character
                StudioCharacter character2 = chosenCharacters[1]; //Studio 2 character

                try {
                    battleInterface.getRegularShowFighter().setIcon(new ImageIcon(ImageIO.read(new File(character1.getImageCollection()[0]))));
                    battleInterface.getHealthValueRegularShow().setText(String.valueOf(character1.getHealthPoints()));
                    battleInterface.getAgilityValueRegularShow().setText(String.valueOf(character1.getAgilityPoints()));
                    battleInterface.getStrengthValueRegularShow().setText(String.valueOf(character1.getStrengthPoints()));
                    battleInterface.getSkillValueRegularShow().setText(String.valueOf(character1.getSkillPoints()));
                    battleInterface.getCharacterNameRegularShow().setText(String.valueOf(character1.getName()));

                    battleInterface.getAvatarFighter().setIcon(new ImageIcon(ImageIO.read(new File(character2.getImageCollection()[0]))));
                    battleInterface.getHealthValueAvatar().setText(String.valueOf(character2.getHealthPoints()));
                    battleInterface.getAgilityValueAvatar().setText(String.valueOf(character2.getAgilityPoints()));
                    battleInterface.getStrengthValueAvatar().setText(String.valueOf(character2.getStrengthPoints()));
                    battleInterface.getSkillValueAvatar().setText(String.valueOf(character2.getSkillPoints()));
                    battleInterface.getCharacterNameAvatar().setText(String.valueOf(character2.getName()));

                } catch (IOException ex) {
                    Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
                }
                AI.setStatus("Processing results");
                Main.battleInterface.getAiStatus().setText("Procesando");

                AI.performBattle(character1, character2);
                inanitionAvoider();
                AI.setStatus("Announcing results");
                try {
                    Main.battleInterface.getAiStatus().setText("Anunciando resultados");

                    sleep(1000);

                } catch (InterruptedException ex) {
                    Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 21; i++) { //When the simulation is started, it generates the first 20 characters
            StudioCharacter character1 = studio1.generateCharacter();
            StudioCharacter character2 = studio2.generateCharacter();
            //Then the admin adds them to their respective queues
            getAdmin().EnqueueCharacters(character1, character2);

        }
        operate(); //After the 20 initial characters have been created and enqueued, the operate cycle starts
    }

    public Queue getWinnersQueue() {
        return winnersQueue;
    }

    public int getBattleDuration() {
        return battleDuration;
    }

    public void setBattleDuration(int battleDuration) {
        this.battleDuration = battleDuration;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void inanitionAvoider() {
        studio1.queueInanitionUpdate();
        studio2.queueInanitionUpdate();
    }

    public Studio getStudio1() {
        return studio1;
    }

    public void setStudio1(Studio studio1) {
        this.studio1 = studio1;
    }

    public Studio getStudio2() {
        return studio2;
    }

    public void setStudio2(Studio studio2) {
        this.studio2 = studio2;
    }

}
