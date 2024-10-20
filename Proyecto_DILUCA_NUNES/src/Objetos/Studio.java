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
public class Studio extends Thread {
    //La clase estudio contiene toda la informacion de cada estudio(pelicula) en la simulacion
    //incluyendo la cola de prioridades el administrador y el IDList. 
    private String studioName;
    
    //seccion de la cola
    private Queue prior1Queue;
    private Queue prior2Queue;
    private Queue prior3Queue;
    private Queue reinfQueue;
    
    //contador de los ids
    private int idCounter;

    public Studio(String name) {
        this.studioName = name;
        this.prior1Queue = new Queue();
        this.prior2Queue = new Queue();
        this.prior3Queue = new Queue();
        this.reinfQueue = new Queue();
        this.idCounter = 0;
    }
    
    public StudioCharacter generateCharacter(){ 
        /*
        El proceso para generar un personaje:
        SECCIÓN 1: genera un id único concatenando el nombre del estudio y el idContador, luego añade 1 al contador
        SECCIÓN 2:genera un número aleatorio entre 0 y 100 para cada estadística      
        SECCIÓN 3: la Calidad global de un personaje será la suma de las estadísticas consideradas de calidad
        
        overallQuality = 0 o 1 ; El personaje es deficiente
        overallQuality = 2 ; El personaje es mediocre
        overallQuality = 3 o 4 ; El personaje es excepcional   
        al final la overallQuality se redondeará a sólo 1, 2 o 3, para que sea más fácil de leer más tarde
        SECCIÓN 4: Devuelve el nuevo carácter generado
        */
        
        //Section 1
        String id = getStudioName() + getIdCounter();  
        setIdCounter(getIdCounter() + 1);
        
        //Section 2
        Random random = new Random();
        int randomNumber = random.nextInt(101);
        int skillPoints = randomNumber;
        
        random = new Random();
        randomNumber = random.nextInt(101);
        int healthPoints = randomNumber;
        
        random = new Random();
        randomNumber = random.nextInt(101);
        int strengthPoints = randomNumber;
        
        random = new Random();
        randomNumber = random.nextInt(101);
        int agilityPoints = randomNumber;
        
        //Section 3
        int overallQuality = 0;
        if (skillPoints >40) { overallQuality++;}   // 60% Chance of being skilled
        if (healthPoints > 30) { overallQuality++;} // 70% Chance of being healthy
        if (strengthPoints > 50) {overallQuality++;}    // 50% Chance of being strong
        if (agilityPoints > 60) {overallQuality++;}     // 40% Chance of being agile 
        
        if (overallQuality  == 4) {overallQuality = 3;} //El personaje es Excepcional
        if (overallQuality  == 0) {overallQuality = 1;} // El personaje es Deficiente
        //Esta ya esta cumpliendo con lo de excepcional,promedio y deficiente.
         
        String imageCollection = getStudioName();
        //Section 4
        StudioCharacter character = new StudioCharacter(id, skillPoints, healthPoints, strengthPoints, agilityPoints, overallQuality, imageCollection);
        return character;
    }


    public String getStudioName() {
        return studioName;
    }

    public Queue getPrior1Queue() {
        return prior1Queue;
    }

    public Queue getPrior2Queue() {
        return prior2Queue;
    }

    public Queue getPrior3Queue() {
        return prior3Queue;
    }

    public Queue getReinfQueue() {
        return reinfQueue;
    }


    public int getIdCounter() {
        return idCounter;
    }

    public void setIdCounter(int idCounter) {
        this.idCounter = idCounter;
    }

    public void queueInanitionUpdate(){
        //Priority 3 queue
        StudioCharacter pointer = getPrior3Queue().getHead();
        StudioCharacter tempPointer = null;
        while (pointer != null) {
            pointer.setCounter(pointer.getCounter()+1);
            if (pointer.getCounter() == 8){
                tempPointer = pointer;
                getPrior3Queue().deleteFromQueue(pointer);
                getPrior2Queue().enqueue(tempPointer);
                tempPointer.setCounter(0);
            }
            pointer = (StudioCharacter) pointer.getNext();
        }
        //Priority 2 queue
        pointer = getPrior2Queue().getHead();
        tempPointer = null;
        while (pointer != null) {
            pointer.setCounter(pointer.getCounter()+1);
            if (pointer.getCounter() == 8){
                tempPointer = pointer;
                getPrior2Queue().deleteFromQueue(pointer);
                getPrior1Queue().enqueue(tempPointer);
                tempPointer.setCounter(0);
            }
            pointer = (StudioCharacter) pointer.getNext();
        }
        
    }
}
