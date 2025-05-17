package domain;

import java.util.ArrayList;

public class Glalie extends AbstractPokemon {

    public Glalie() {
        this.name = "Glalie";
        this.primaryType = Type.ICE;
        this.secondaryType = null;
        this.maxHP = 364;
        this.currentHP = maxHP;
        this.attack = 284;
        this.defense = 284;
        this.specialAttack = 284;
        this.specialDefense = 284;
        this.speed = 284;
        this.moves = new ArrayList<>();

        initializeMoves();
    }

    @Override
    protected void initializeMoves() {
        learnMove("Leer");
        learnMove("Quick Attack");
        learnMove("Double Team");
        learnMove("Knock Off");
        learnMove("Future Sight");
        learnMove("Water Pulse");
    }
    // Habilidad pasiva: Congelación instantánea
    public void instantFreeze() {
        System.out.println("Glalie congela instantáneamente la humedad del aire.");
        // Aquí podrías implementar un efecto de congelación simple si el juego lo permite.
    }


}
