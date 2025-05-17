package domain;

import java.util.ArrayList;

public class Medicham extends AbstractPokemon {

    public Medicham() {
        this.name = "Medicham";
        this.primaryType = Type.FIGHTING;
        this.secondaryType = Type.PSYCHIC;
        this.maxHP = 284;
        this.currentHP = maxHP;
        this.attack = 295;
        this.defense = 229;
        this.specialAttack = 229;
        this.specialDefense = 229;
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

    // Habilidad pasiva: Visión del aura
    public void auraVision() {
        System.out.println("Medicham utiliza su visión del aura para anticipar los movimientos del oponente.");

    }
}
