/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Objetos;

import java.util.Random;

/**
 *
 * @author orveodiluca
 */
public class StudioCharacter {
    // Los personajes funcionan como un nodo en la cola, por lo que tendrán un siguiente. 
    // attribute
    private StudioCharacter next;
    // El Id que va a tener el personaje que es lo que se busca.
    private String id;

    // Contador de cada personaje
    private int counter;

    // Las estadisticas del personaje que van a estar del 0 al 100. 
    private int skillPoints;
    private int healthPoints;
    private int strengthPoints;
    private int agilityPoints;
    // La calidad de los personajes depende del nivel de cada estadistica, para determinar la calidad depende de cada stat.  
    private int overallQuality; //

    // Ruta de la imagen de cada personaje. 
    private String[] imageCollection;

    private String name;
    
    public StudioCharacter(String id, int skillPoints, int healthPoints, int strengthPoints, int agilityPoints,
            int overallQuality, String imageCollection) {
        this.id = id;
        this.next = null;
        this.counter = 0;
        this.skillPoints = skillPoints;
        this.healthPoints = healthPoints;
        this.strengthPoints = strengthPoints;
        this.agilityPoints = agilityPoints;

        this.overallQuality = overallQuality;

        this.imageCollection = randomCharacter(imageCollection);

        this.name = id + " " + this.imageCollection[1];
    }

    public StudioCharacter getNext() {
        return next;
    }

    public void setNext(StudioCharacter next) {
        this.next = next;
    }

    public String getId() {
        return id;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getStrengthPoints() {
        return strengthPoints;
    }

    public int getAgilityPoints() {
        return agilityPoints;
    }

    public int getOverallQuality() {
        return overallQuality;
    }

    public void setOverallQuality(int overallQuality) {
        this.overallQuality = overallQuality;
    }

    public String[] getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(String[] imageCollection) {
        this.imageCollection = imageCollection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void printStatitics() {
        System.out.println("----------------------------");
        System.out.println("Id: " + getId());
        System.out.println("SkillPoints: " + getSkillPoints());
        System.out.println("HealthPoints: " + getHealthPoints());
        System.out.println("StrengthPoints: " + getStrengthPoints());
        System.out.println("AgilityPoints: " + getAgilityPoints());
        System.out.println("Overall Quality: " + getOverallQuality());
        System.out.println("----------------------------");
    }

    private String[] randomCharacter(String images) {
        String[][] array = {};
        if ("SW".equals(images)) { //SW=Star Wars
            array = starwarsCharacters;
        } else if ("ST".equals(images)) { // ST=Star Trek. 
            array = startrekCharacters;
        }

        // Agarrando un personaje random de la lista 
        Random random = new Random();
        String[] randomElement = array[random.nextInt(array.length)];

        return randomElement;
    }

    // Pruebas
    String[][] starwarsCharacters = {
        {"src/Interface/Images/Starwars/Obi-Wan_Kenobi.png", "Obi wan"},
        {"src/Interface/Images/Starwars/Vader.png", "Vader"},
        {"src/Interface/Images/Starwars/Yoda.png", "Yoda"},
        {"src/Interface/Images/Starwars/bobafett.png", "Boba fett"},
        {"src/Interface/Images/Starwars/R2-D2_Droid.png", "R2 D2"},
        {"src/Interface/Images/Starwars/Han-Solo-01-icon.png", "Han solo"},
        {"src/Interface/Images/Starwars/Chewbacca.png", "Chewbacca"},
        {"src/Interface/Images/Starwars/C-3PO.png", "C-3PO"}
    };

    String[][] startrekCharacters = {
        {"src/Interface/Images/StartTrek/Adira.png", "Adira"},
        {"src/Interface/Images/StartTrek/Data.png", "Data"},
        {"src/Interface/Images/StartTrek/HughCulber.png", "Hugh Culer"},
        {"src/Interface/Images/StartTrek/JamesTKirk.png", "James T kirk"},
        {"src/Interface/Images/StartTrek/LRell.png", "L'Rell"},
        {"src/Interface/Images/StartTrek/Lorca.png", "Lorca"},
        {"src/Interface/Images/StartTrek/NyotaUhura.png", "Nyota Uhura"},
        {"src/Interface/Images/StartTrek/Pavel-Chekov-pavel.png", "Pavel Chekov"},
        {"src/Interface/Images/StartTrek/Sarek.png", "Sarek"},
        {"src/Interface/Images/StartTrek/Saru.png", "Saru"},
        {"src/Interface/Images/StartTrek/Spocks.png", "Spocks"}
    };
}
