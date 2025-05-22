package domain;

import java.awt.Color;
import java.util.List;
import java.util.Map;

public class DefensiveTrainer extends AbstractTrainer {

    public DefensiveTrainer(String name, Color color, List<Pokemon> team, Map<Pokemon, List<Move>> moveMap) {
        super(name, color, team, moveMap);
    }

    @Override
    public void makeMove(Game game) {
        Pokemon myPokemon = getCurrentPokemon();
        List<Move> moves = myPokemon.getMoves();

        // Prioriza movimientos que suben defensa o bajan ataque enemigo
        for (Move move : moves) {
            String effect = move.getEffect();
            if (effect.contains("boostDefense") || effect.contains("boostSpecialDefense") || effect.contains("lowerAttack") || effect.contains("lowerSpecialAttack") || effect.contains("protect")) {
                System.out.println(name + " se defiende con: " + move.getName());
                return;
            }
        }

        // Usa el de menor poder si no hay defensivo (para simular jugar pasivo)
        Move weakest = moves.stream()
                .min((a, b) -> Integer.compare(a.getPower(), b.getPower()))
                .orElse(moves.get(0));

        System.out.println(name + " no tiene movimiento defensivo, usa: " + weakest.getName());
    }
}
