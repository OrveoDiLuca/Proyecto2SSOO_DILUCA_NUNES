/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto_diluca_nunes;
//import Interface.BattleInterface;
import Objetos.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 *
 * @author orveodiluca
 */
public class Main {

//    public static Simulation simulation = new Simulation("CN", "NK", 10000);
    public static Simulation simulation = new Simulation("ST","SW",10000);
    public static Studio StarWars = simulation.getStudio1();
    public static Studio StarTrek = simulation.getStudio2();

    public static BattleInterface battleInterface = new BattleInterface();

    public static void main(String[] args) {
        simulation.start();
        battleInterface.getResult().setText("");
        
    }

    
}
