package domain;

import java.util.ArrayList;
import java.util.List;

public class Charizard extends AbstractPokemon  {

    private boolean flameLit;  // Estado de la llama de la cola

    public Charizard() {
        this.name = "Charizard";
        this.primaryType = Type.FIRE;
        this.secondaryType = Type.FLYING;
        this.maxHP = 360;
        this.currentHP = maxHP;
        this.attack = 293;
        this.defense = 280;
        this.specialAttack = 348;
        this.specialDefense = 295;
        this.speed = 328;
        this.flameLit = true;

        this.moves = new ArrayList<>();
        initializeMoves();

    }

    @Override
    protected void initializeMoves() {
        learnMove("Scratch");
        learnMove("Growl");
        learnMove("Ember");
        learnMove("Smokescreen");
        learnMove("Flamethrower");
        learnMove("Slash");
    }

    @Override
    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);

        // Si los PS bajan demasiado, la llama de la cola podría debilitarse
        if (currentHP < maxHP * 0.1) {
            flameLit = false;
        }

        // Si la llama se apaga y los PS llegan a 0, Charizard no puede revivir
        if (!flameLit && currentHP == 0) {
            System.out.println("¡La llama de Charizard se ha apagado! No puede ser revivido.");
        }
    }

    @Override
    public void heal(int amount) {
        if (!flameLit && currentHP == 0) {
            System.out.println("No se puede revivir a Charizard, su llama se ha apagado.");
            return;
        }

        currentHP = Math.min(maxHP, currentHP + amount);

        // Si se recupera lo suficiente, la llama vuelve a encenderse
        if (currentHP > maxHP * 0.3) {
            flameLit = true;
        }
    }


    // Métodos específicos de Charizard
    public boolean isFlameActive() {
        return flameLit;
    }

    public void powerUpFireMoves() {
        // Si ha participado en duros combates, sus ataques de fuego se fortalecen
        for (Move move : moves) {
            if (move.getType() == Type.FIRE) {
                // Incrementa el poder de los movimientos de fuego
                move.setPower((int)(move.getPower() * 1.2));
            }
        }
    }

    public void dragonRage() {
        // Habilidad especial que incrementa temporalmente el ataque
        this.attack = (int)(this.attack * 1.5);
        System.out.println("¡Charizard entra en estado de furia! Su ataque aumenta considerablemente.");
    }
}