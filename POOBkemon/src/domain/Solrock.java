package domain;

import java.util.ArrayList;

public class Solrock extends AbstractPokemon {

    private boolean solarEnergy;

    public Solrock() {
        this.name = "Solrock";
        this.primaryType = Type.ROCK;
        this.secondaryType = Type.PSYCHIC;
        this.maxHP = 344;
        this.currentHP = maxHP;
        this.attack = 317;
        this.defense = 295;
        this.specialAttack = 229;
        this.specialDefense = 251;
        this.speed = 262;

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
        super.takeDamage(amount);
        if (amount > maxHP * 0.25) {
            solarEnergy = false;
            System.out.println("¡Solrock pierde su energía solar por el daño recibido!");
        }
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        if (currentHP > maxHP * 0.7) {
            solarEnergy = true;
            System.out.println("¡Solrock recarga su energía solar y se siente revitalizado!");
        }
    }

    public boolean hasSolarEnergy() {
        return solarEnergy;
    }

    public void solarBeam() {
        if (solarEnergy) {
            System.out.println("¡Solrock desata un rayo de energía solar!");
        } else {
            System.out.println("Solrock necesita recargar energía solar.");
        }
    }

    // Método adicional para utilizar Sunny Day y potenciar Solar Beam
    public void sunnyDay() {
        System.out.println("¡Solrock invoca el sol con Sunny Day!");
        solarEnergy = true; // Asegura que Solrock siempre tiene energía solar mientras el sol está activo.
    }
}