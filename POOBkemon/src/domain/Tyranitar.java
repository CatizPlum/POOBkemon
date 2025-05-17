package domain;

import java.util.ArrayList;
import java.util.List;

public class Tyranitar extends AbstractPokemon  {

    private boolean sandstreamActive;  // Estado de tormenta de arena

    public Tyranitar() {

        this.sandstreamActive = false;


        this.moves = new ArrayList<>();
        initializeMoves();
    }

    @Override
    protected void initializeMoves() {
        learnMove("Cross Poison");
        learnMove("Air Slash");
        learnMove("Bite");
        learnMove("Mean Look");
        learnMove("Screech");
        learnMove("Absorb");
    }

    @Override
    public void takeDamage(int amount) {
        // La armadura rocosa de Tyranitar reduce el daño recibido
        int reducedAmount = (int)(amount * 0.9);
        currentHP = Math.max(0, currentHP - reducedAmount);

        // Si recibe mucho daño, puede desatar una tormenta de arena
        if (!sandstreamActive && amount > maxHP * 0.15) {
            sandstreamActive = true;
            this.defense = (int)(this.defense * 1.2);
            System.out.println("¡El ataque enfurece a Tyranitar y desata una tormenta de arena!");
        }
    }

    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);
    }

    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }

    // Métodos específicos de Tyranitar
    public boolean isSandstreamActive() {
        return sandstreamActive;
    }

    public void activateSandstream() {
        if (!sandstreamActive) {
            sandstreamActive = true;
            this.defense = (int)(this.defense * 1.2);
            System.out.println("¡Tyranitar desata una violenta tormenta de arena! Su defensa aumenta.");
        } else {
            System.out.println("La tormenta de arena ya está activa.");
        }
    }

    public void mountainCrusher() {
        // Ataque que puede cambiar el terreno
        System.out.println("¡Tyranitar golpea el suelo con toda su fuerza, modificando el terreno!");
        this.attack = (int)(this.attack * 1.3);

        // En un juego real, esto podría afectar el campo de batalla
        if (sandstreamActive) {
            System.out.println("¡La combinación con la tormenta de arena hace que el impacto sea devastador!");
        }
    }
}