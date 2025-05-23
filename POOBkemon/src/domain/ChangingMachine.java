package domain;

import java.awt.Color;
import java.util.List;
import java.util.Map;

public class ChangingMachine extends AbstractMachine {

    public ChangingMachine(String name, Color color, List<Pokemon> team, Map<Pokemon, List<Move>> moveMap) {
        super(name, color, team, moveMap);
    }

    @Override
    public void makeMove(Game game) {
        Pokemon enemy = game.getWaitingTrainer().getCurrentPokemon();

        for (int i = 0; i < team.size(); i++) {
            Pokemon ally = team.get(i);
            if (!ally.isFainted() && isEffective(ally, enemy) && ally != getCurrentPokemon()) {
                try {
                    switchPokemon(i);
                    System.out.println(name + " cambia a " + ally.getName() + " por ventaja de tipo.");
                    return;
                } catch (PoobkemonException e) {
                    // Continúa buscando otro
                }
            }
        }

        // Si no cambia, ataca con movimiento más fuerte
        Move strongest = getCurrentPokemon().getMoves().stream()
                .max((a, b) -> Integer.compare(a.getPower(), b.getPower()))
                .orElse(getCurrentPokemon().getMoves().get(0));

        System.out.println(name + " se queda con su Pokémon actual y ataca con: " + strongest.getName());
    }

    private boolean isEffective(Pokemon attacker, Pokemon defender) {
        // Necesitas tener Type.getPrimaryType(), ajusta si usas otro método
        Type atkType = attacker.getPrimaryType();
        Type defType = defender.getPrimaryType();
        return TypeAdvantageChecker.isSuperEffective(atkType, defType);
    }
}
