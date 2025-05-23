package domain;

import java.awt.Color;
import java.util.List;
import java.util.Map;

public class ExpertMachine extends AbstractMachine {

    public ExpertMachine(String name, Color color, List<Pokemon> team, Map<Pokemon, List<Move>> moveMap) {
        super(name, color, team, moveMap);
    }

    @Override
    public void makeMove(Game game) {
        Pokemon myPokemon = getCurrentPokemon();
        Pokemon enemy = game.getWaitingTrainer().getCurrentPokemon();
        List<Move> moves = myPokemon.getMoves();
        if (moves == null || moves.isEmpty()) return;

        for (Item item : items) {
            if (myPokemon.getCurrentHP() < myPokemon.getMaxHP() / 3) {
                try {
                    useItem(item);
                    System.out.println(name + " usó el ítem: " + item.getName());
                    return;
                } catch (PoobkemonException e) {}
            }
        }

        for (int i = 0; i < team.size(); i++) {
            Pokemon ally = team.get(i);
            if (!ally.isFainted() && ally != myPokemon && isEffective(ally, enemy)) {
                try {
                    switchPokemon(i);
                    System.out.println(name + " cambia a " + ally.getName() + " por ventaja de tipo.");
                    return;
                } catch (PoobkemonException e) {}
            }
        }

        Move best = moves.stream()
                .max((a, b) -> Integer.compare(
                        a.calculateDamage(myPokemon, enemy),
                        b.calculateDamage(myPokemon, enemy)))
                .orElse(null);
        if (best != null) {
            game.machineAttack(this, best);
        }
    }

    private boolean isEffective(Pokemon attacker, Pokemon defender) {
        return TypeAdvantageChecker.isSuperEffective(attacker.getPrimaryType(), defender.getPrimaryType());
    }
}
