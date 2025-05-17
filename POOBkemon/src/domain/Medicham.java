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

        moves.add(new Move("Confusion", Type.PSYCHIC, 50, 100, MoveCategory.SPECIAL));
        moves.add(new Move("Detect", Type.FIGHTING, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Hi Jump Kick", Type.FIGHTING, 130, 90, MoveCategory.PHYSICAL));
        moves.add(new Move("Mind Reader", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Calm Mind", Type.PSYCHIC, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Recover", Type.NORMAL, 0, 0, MoveCategory.STATUS));
        moves.add(new Move("Psychic", Type.PSYCHIC, 90, 100, MoveCategory.SPECIAL));
    }

    // Habilidad pasiva: Visión del aura
    public void auraVision() {
        System.out.println("Medicham utiliza su visión del aura para anticipar los movimientos del oponente.");

    }
}
