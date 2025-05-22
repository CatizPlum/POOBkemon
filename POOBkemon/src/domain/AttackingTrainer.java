package domain;

import java.awt.Color;
import java.util.List;
import java.util.Map;

public class AttackingTrainer extends AbstractTrainer {

    public AttackingTrainer(String name, Color color, List<Pokemon> team, Map<Pokemon, List<Move>> moveMap) {
        super(name, color, team, moveMap);
    }

    @Override
    public void makeMove(Game game) {
        Pokemon myPokemon = getCurrentPokemon();
        List<Move> moves = myPokemon.getMoves();

        // Prioriza movimientos que potencian ataque o bajan defensa enemiga
        for (Move move : moves) {
            String effect = move.getEffect();
            if (effect.contains("boostAttack") || effect.contains("boostSpecialAttack") || effect.contains("lowerDefense") || effect.contains("lowerSpecialDefense")) {
                System.out.println(name + " usa un movimiento ofensivo: " + move.getName());
                return;
            }
        }

        // Si no hay uno ofensivo, usa el de mayor poder
        Move strongest = moves.stream()
                .max((a, b) -> Integer.compare(a.getPower(), b.getPower()))
                .orElse(moves.get(0));

        System.out.println(name + " ataca con su movimiento más fuerte: " + strongest.getName());
    }
}
