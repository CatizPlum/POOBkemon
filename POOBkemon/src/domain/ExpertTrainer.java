package domain;

import java.awt.Color;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ExpertTrainer extends AbstractTrainer {

    public ExpertTrainer(String name, Color color, List<Pokemon> team, Map<Pokemon, List<Move>> moveMap) {
        super(name, color, team, moveMap);
    }

    @Override
    public void makeMove(Game game) {
        Pokemon enemy = game.getWaitingTrainer().getCurrentPokemon();

        // Cambia si tiene un Pokémon más efectivo
        for (int i = 0; i < team.size(); i++) {
            Pokemon candidate = team.get(i);
            if (!candidate.isFainted() && isEffective(candidate, enemy) && candidate != getCurrentPokemon()) {
                try {
                    switchPokemon(i);
                    System.out.println(name + " cambia estratégicamente a " + candidate.getName());
                    return;
                } catch (PoobkemonException e) {
                    // Sigue
                }
            }
        }

        // Si no cambia, usa el mejor movimiento posible
        Move best = getCurrentPokemon().getMoves().stream()
                .max(Comparator.comparingInt(m -> m.getPower() + evaluateEffectScore(m)))
                .orElse(getCurrentPokemon().getMoves().get(0));

        System.out.println(name + " usa su mejor jugada: " + best.getName());
    }

    private int evaluateEffectScore(Move move) {
        String effect = move.getEffect();
        if (effect.contains("boostAttack") || effect.contains("lowerDefense")) return 10;
        if (effect.contains("boostSpeed")) return 5;
        if (effect.contains("heal")) return 8;
        return 0;
    }

    private boolean isEffective(Pokemon attacker, Pokemon defender) {
        return TypeAdvantageChecker.isSuperEffective(attacker.getPrimaryType(), defender.getPrimaryType());
    }
}
