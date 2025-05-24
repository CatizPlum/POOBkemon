package domain;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Blaziken - Pokémon tipo Fuego/Lucha con poderosos ataques físicos.
 * Puede activar llamas intensas para aumentar su poder temporalmente.
 */
public class Blaziken extends AbstractPokemon implements Serializable {

    public Blaziken() {
        this.name = "Blaziken";
        this.primaryType = Type.FIRE;
        this.secondaryType = Type.FIGHTING;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 372;
        this.defense = 262;
        this.specialAttack = 350;
        this.specialDefense = 262;
        this.speed = 284;
        this.moves = new ArrayList<>();

        initializeMoves();
    }

    // Movimientos característicos de Blaziken
    @Override
    public void initializeMoves() {
        learnMove("Cross Poison");
        learnMove("Air Slash");
        learnMove("Bite");
        learnMove("Mean Look");
        learnMove("Screech");
        learnMove("Absorb");
    }



    // Copia este Blaziken (incluye estado de llamas y turnos restantes)
    @Override
    public Blaziken clone() {
        return (Blaziken) super.clone();
    }
}