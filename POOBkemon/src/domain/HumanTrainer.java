package domain;

import java.awt.Color;
import java.util.List;
import java.util.Map;

/**
 * Representa a un entrenador controlado por el jugador humano.
 * No toma decisiones automáticas, por lo que su método makeMove lanza excepción.
 */
public class HumanTrainer extends AbstractTrainer {

    public HumanTrainer(String name, Color color, List<Pokemon> team, Map<Pokemon, List<Move>> moveMap) {
        super(name, color, team, moveMap);
    }

    @Override
    public void makeMove(Game game) {
        // Este método no debe usarse en entrenadores humanos.
        throw new UnsupportedOperationException("El entrenador humano no usa lógica automática.");
    }
}
