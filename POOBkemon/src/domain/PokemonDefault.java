package domain;

import java.util.List;

/**
 * Representación por defecto de un Pokémon genérico llamado "MissingNo".
 * Útil para pruebas o valores por defecto.
 */
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
                new Move("Tackle", Type.NORMAL, 40, 100, 35, MoveEffect.NONE, 0, MoveCategory.PHYSICAL, 0),
                new Move("Growl", Type.NORMAL, 0, 100, 40, MoveEffect.NONE, 0, MoveCategory.STATUS, 0),
                new Move("Quick Attack", Type.NORMAL, 40, 100, 30, MoveEffect.NONE, 1, MoveCategory.PHYSICAL, 0),
                new Move("Defense Curl", Type.NORMAL, 0, 100, 40, MoveEffect.DEFENSE_UP, 0, MoveCategory.STATUS, 0)
        );
    }

    @Override
    public void initializeMoves() {
        // Los movimientos ya están inicializados en el constructor
    }
}
