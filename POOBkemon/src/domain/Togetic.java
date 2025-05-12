package domain;

import java.util.ArrayList;
import java.util.List;

public class Togetic extends AbstractPokemon  {

    private boolean happyMood;  // Estado de felicidad
    
    public Togetic() {
        this.name = "Togetic";
        this.primaryType = Type.FAIRY;
        this.secondaryType = Type.FLYING;
        this.maxHP = 314;
        this.currentHP = maxHP;
        this.attack = 196;
        this.defense = 295;
        this.specialAttack = 284;
        this.specialDefense = 339;
        this.speed = 196;
        this.happyMood = true;

        this.moves = new ArrayList<>();
        moves.add(new Move("Growl", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Charm", Type.FAIRY, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Metronome", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Sweet Kiss", Type.FAIRY, 0, 75, MoveCategory.STATUS));
        moves.add(new Move("Yawn", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Encore", Type.NORMAL, 0, 100, MoveCategory.STATUS));
        moves.add(new Move("Follow Me", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Wish", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Ancient Power", Type.ROCK, 60, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Safeguard", Type.NORMAL, 0, 0, MoveCategory.STATUS));
    }

    
    @Override
    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);
        // Si recibe mucho daño, pierde su estado de felicidad
        if (amount > maxHP * 0.25) {
            happyMood = false;
            System.out.println("¡Togetic se entristece por el daño recibido!");
        }
    }
    
    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);
        
        // Si se recupera considerablemente, vuelve a estar feliz
        if (currentHP > maxHP * 0.7) {
            happyMood = true;
            System.out.println("¡Togetic se siente mejor y recupera su felicidad!");
        }
    }
    
    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }
    
    // Métodos específicos de Togetic
    public boolean isHappy() {
        return happyMood;
    }
    
    public void spreadJoyPowder() {
        if (happyMood) {
            // Esparce polvillo de alegría para mejorar el estado de los aliados
            System.out.println("¡Togetic esparce polvillo de alegría, mejorando el ánimo de todos!");
            
            // Aumenta temporalmente sus propias estadísticas defensivas
            this.defense = (int)(this.defense * 1.2);
            this.specialDefense = (int)(this.specialDefense * 1.2);
        } else {
            System.out.println("Togetic está demasiado triste para esparcir polvillo de alegría.");
        }
    }
    
    public void detectKindness() {
        // Simula la capacidad de Togetic para detectar personas de buen corazón
        System.out.println("Togetic busca personas de buen corazón en los alrededores.");
        
        // En un juego real, esto podría tener efectos sobre la aparición de eventos o personajes
        if (happyMood) {
            System.out.println("¡Togetic ha detectado un corazón puro cerca!");
        }
    }
}