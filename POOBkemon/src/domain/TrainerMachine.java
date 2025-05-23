package domain;

import java.awt.Color;
import java.util.List;
import java.util.Map;

public class TrainerMachine {

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
    public static AbstractMachine createTrainer(String name, Color color,
                                                List<Pokemon> team,
                                                Map<Pokemon, List<Move>> moves,
                                                String mode) {
        switch (mode.toLowerCase()) {
            case "attacking":
                return new AttackingMachine(name, color, team, moves);
            case "defensive":
                return new DefensiveMachine(name, color, team, moves);
            case "changing":
                return new ChangingMachine(name, color, team, moves);
            case "expert":
                return new ExpertMachine(name, color, team, moves);
            case "human":
            default:
                return new HumanMachine(name, color, team, moves);
        }
    }
}
