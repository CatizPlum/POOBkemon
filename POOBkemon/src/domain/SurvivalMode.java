package domain;

import java.io.Serializable;
import java.util.*;

/**
 * Clase que gestiona la generación de equipos de Pokémon en el modo Supervivencia.
 * Permite crear equipos aleatorios de seis Pokémon con estadísticas escaladas al nivel 100,
 * asegurando que cada Pokémon tenga exactamente cuatro movimientos.
 */
public class SurvivalMode implements Serializable {
    /**
     * Lista de clases de Pokémon disponibles para el modo Supervivencia.
     */
    private static final List<Class<? extends AbstractPokemon>> POKEMON_CLASSES = Arrays.asList(
            Absol.class, Altaria.class, Banette.class, Blastoise.class, Blaziken.class,
            Charizard.class, Crobat.class, Delibird.class, Donphan.class, Dragonite.class,
            Flygon.class, Gardevoir.class, Gengar.class, Glalie.class, Granbull.class,
            Grumpig.class, Machamp.class, Manectric.class, Masquerain.class, Mawile.class,
            Medicham.class, Metagross.class, Moltres.class, Ninjask.class, Pidgeot.class,
            Raichu.class, Sceptile.class, Seviper.class, Snorlax.class, Solrock.class,
            Swampert.class, Togetic.class, Tyranitar.class, Umbreon.class, Venusaur.class,
            Zangoose.class
    );

    /**
     * Genera un equipo aleatorio de seis Pokémon para el modo Supervivencia.
     * Cada Pokémon tiene sus estadísticas escaladas al nivel 100 y exactamente cuatro movimientos.
     * @return Lista con seis Pokémon generados aleatoriamente.
     */
    public static List<Pokemon> generateRandomTeam() {
        List<Class<? extends AbstractPokemon>> shuffledClasses = new ArrayList<>(POKEMON_CLASSES);
        Collections.shuffle(shuffledClasses);

        List<Pokemon> team = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 6 && i < shuffledClasses.size(); i++) {
            try {
                AbstractPokemon pokemon = shuffledClasses.get(i).getDeclaredConstructor().newInstance();
                scalePokemonToLevel100(pokemon);
                ensureFourMoves(pokemon);
                team.add(pokemon);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return team;
    }

    /**
     * Escala las estadísticas de un Pokémon al nivel 100.
     * @param pokemon El Pokémon cuyas estadísticas serán escaladas.
     */
    private static void scalePokemonToLevel100(AbstractPokemon pokemon) {
        // Escalar estadísticas a nivel 100 (simplificado)
        double scale = 2.0;

        pokemon.maxHP = (int)(pokemon.maxHP * scale);
        pokemon.currentHP = pokemon.maxHP;
        pokemon.attack = (int)(pokemon.attack * scale);
        pokemon.defense = (int)(pokemon.defense * scale);
        pokemon.specialAttack = (int)(pokemon.specialAttack * scale);
        pokemon.specialDefense = (int)(pokemon.specialDefense * scale);
        pokemon.speed = (int)(pokemon.speed * scale);
    }

    /**
     * Asegura que un Pokémon tenga exactamente cuatro movimientos.
     * Si tiene menos, se agregan movimientos aleatorios; si tiene más, se eliminan.
     * @param pokemon El Pokémon al que se ajustarán los movimientos.
     */
    private static void ensureFourMoves(AbstractPokemon pokemon) {
        // Asegurar que el Pokémon tenga exactamente 4 movimientos
        while (pokemon.getMoves().size() < 4) {
            // Agregar movimientos aleatorios si no tiene suficientes
            pokemon.learnMove(getRandomMoveName());
        }

        // Si tiene más de 4, reducir a 4
        while (pokemon.getMoves().size() > 4) {
            pokemon.getMoves().remove(pokemon.getMoves().size() - 1);
        }
    }

    /**
     * Obtiene el nombre de un movimiento aleatorio de una lista predefinida.
     * @return Nombre de un movimiento aleatorio.
     */
    private static String getRandomMoveName() {
        // Movimientos disponibles (simplificado)
        String[] moves = {
                "Leer", "Quick Attack", "Double Team", "Knock Off", "Future Sight", "Water Pulse",
                "Tackle", "Tail Whip", "Water Gun", "Withdraw", "Bite", "Scratch", "Growl",
                "Ember", "Smokescreen", "Flamethrower", "Slash", "Cross Poison", "Air Slash",
                "Mean Look", "Screech", "Absorb"
        };
        return moves[new Random().nextInt(moves.length)];
    }
}
