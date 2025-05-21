package domain;

import java.util.List;

public class PokemonDefault extends AbstractPokemon {
    public PokemonDefault() {
        this.name = "MissingNo";
        this.primaryType = Type.NORMAL;
        this.maxHP = 100;
        this.currentHP = 100;
        this.attack = 50;
        this.defense = 50;
        this.specialAttack = 50;
        this.specialDefense = 50;
        this.speed = 50;
        this.moves = List.of(
                new Move("Tackle", Type.NORMAL, 40, 35, 0, MoveEffect.NONE, 100, MoveCategory.PHYSICAL, 12),
                new Move("Growl", Type.NORMAL, 0, 40, 0, MoveEffect.NONE, 100, MoveCategory.STATUS, 15),
                new Move("Quick Attack", Type.NORMAL, 40, 30, 1, MoveEffect.NONE, 100, MoveCategory.PHYSICAL, 32),
                new Move("Defense Curl", Type.NORMAL, 0, 40, 0, MoveEffect.NONE, 100, MoveCategory.STATUS, 18)
        );
    }

    @Override
    public void initializeMoves() {
        // Movimientos ya inicializados en el constructor
    }
}