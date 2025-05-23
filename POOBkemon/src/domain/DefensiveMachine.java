package domain;

import java.awt.Color;
import java.util.List;
import java.util.Map;

public class DefensiveMachine extends AbstractMachine {

    public DefensiveMachine(String name, Color color, List<Pokemon> team, Map<Pokemon, List<Move>> moveMap) {
        super(name, color, team, moveMap);
    }

    @Override
    public void makeMove(Game game) {
        Pokemon myPokemon = getCurrentPokemon();
        List<Move> moves = myPokemon.getMoves();
        if (moves == null || moves.isEmpty()) return;

        for (Item item : items) {
            if (myPokemon.getCurrentHP() < myPokemon.getMaxHP() / 2) {
                try {
                    useItem(item);
                    System.out.println(name + " usó el ítem: " + item.getName());
                    return;
                } catch (PoobkemonException e) {}
            }
        }

        for (Move move : moves) {
            String effect = move.getEffect();
            if (effect != null && (effect.contains("boostDefense") || effect.contains("boostSpecialDefense")
                    || effect.contains("lowerAttack") || effect.contains("lowerSpecialAttack") || effect.contains("protect"))) {
                game.machineAttack(this, move);
                return;
            }
        }

        Move weakest = moves.stream()
                .min((a, b) -> Integer.compare(a.getPower(), b.getPower()))
                .orElse(null);
        if (weakest != null) {
            game.machineAttack(this, weakest);
        }
    }
}
