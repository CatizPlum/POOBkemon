package domain;

import java.awt.Color;
import java.util.List;
import java.util.Map;

public class AttackingMachine extends AbstractMachine {

    public AttackingMachine(String name, Color color, List<Pokemon> team, Map<Pokemon, List<Move>> moveMap) {
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
            if (effect != null && (effect.contains("boostAttack") || effect.contains("boostSpecialAttack")
                    || effect.contains("lowerDefense") || effect.contains("lowerSpecialDefense"))) {
                game.machineAttack(this, move);
                return;
            }
        }

        Move strongest = moves.stream()
                .max((a, b) -> Integer.compare(a.getPower(), b.getPower()))
                .orElse(null);
        if (strongest != null) {
            game.machineAttack(this, strongest);
        }
    }
}
