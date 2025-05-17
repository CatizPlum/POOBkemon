package domain;

import java.util.ArrayList;

public class Blastoise extends AbstractPokemon  {

    private boolean cannonsPowered;  // Estado de los cañones

    public Blastoise() {
        this.name = "Blastoise";
        this.primaryType = Type.WATER;
        this.secondaryType = null;
        this.maxHP = 362;
        this.currentHP = maxHP;
        this.attack = 291;
        this.defense = 328;
        this.specialAttack = 295;
        this.specialDefense = 339;
        this.speed = 280;
        this.cannonsPowered = true;

        this.moves = new ArrayList<>();

        initializeMoves();
    }

    @Override
    protected void initializeMoves() {
        learnMove("Tackle");
        learnMove("Tail Whip");
        learnMove("Water Gun");
        learnMove("Withdraw");
        learnMove("Water Pulse");
        learnMove("Bite");
    }

    @Override
    public void takeDamage(int amount) {
        currentHP = Math.max(0, currentHP - amount);
        // Si los PS bajan demasiado, la potencia de los cañones disminuye
        if (currentHP < maxHP * 0.2) {
            cannonsPowered = false;
            System.out.println("¡Los cañones de Blastoise pierden presión!");
        }
    }

    @Override
    public void heal(int amount) {
        currentHP = Math.min(maxHP, currentHP + amount);

        // Si se recupera lo suficiente, los cañones recuperan potencia
        if (currentHP > maxHP * 0.5) {
            cannonsPowered = true;
            System.out.println("¡Los cañones de Blastoise recuperan su presión normal!");
        }
    }

    @Override
    public boolean isFainted() {
        return currentHP == 0;
    }
    // Métodos específicos de Blastoise
    public boolean areCannonsPowered() {
        return cannonsPowered;
    }

    public void shellDefense() {
        // Incrementa temporalmente la defensa
        this.defense = (int)(this.defense * 1.5);
        System.out.println("¡Blastoise se refugia en su caparazón! Su defensa aumenta considerablemente.");
    }

    public void hydroCannon() {
        if (cannonsPowered) {
            // Disparo concentrado que aumenta el poder de ataque de agua
            for (Move move : moves) {
                if (move.getType() == Type.WATER) {
                    move.setPower((int)(move.getPower() * 1.3));
                }
            }
            System.out.println("¡Blastoise prepara sus cañones para un disparo concentrado!");
        } else {
            System.out.println("Los cañones de Blastoise no tienen suficiente presión para un disparo concentrado.");
        }
    }
}