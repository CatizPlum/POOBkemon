package domain;

import java.awt.Color;
import java.util.List;
import java.util.Map;

public class TrainerFactory {

    /**
     * Crea un entrenador del tipo apropiado según el modo indicado.
     *
     * @param name Nombre del entrenador
     * @param color Color del entrenador
     * @param team Equipo de Pokémon
     * @param moves Mapa de movimientos
     * @param mode Tipo de entrenador: "human", "attacking", "defensive", "changing", "expert"
     * @return Una instancia de Trainer adecuada
     */
    public static AbstractTrainer createTrainer(String name, Color color,
                                        List<Pokemon> team,
                                        Map<Pokemon, List<Move>> moves,
                                        String mode) {
        switch (mode.toLowerCase()) {
            case "attacking":
                return new AttackingTrainer(name, color, team, moves);
            case "defensive":
                return new DefensiveTrainer(name, color, team, moves);
            case "changing":
                return new ChangingTrainer(name, color, team, moves);
            case "expert":
                return new ExpertTrainer(name, color, team, moves);
            case "human":
            default:
                return new HumanTrainer(name, color, team, moves);
        }
    }
}
