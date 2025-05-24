package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Repositorio estático de movimientos disponibles para los Pokémon.
 * Permite registrar, obtener y filtrar movimientos según el tipo y categoría.
 * Implementa Serializable para permitir su almacenamiento si se requiere.
 */
public class MoveRepository implements Serializable {

    /**
     * Mapa estático que contiene los movimientos registrados, indexados por su nombre.
     */
    private static final Map<String, Move> moves = new HashMap<>();

    // Bloque estático que inicializa el repositorio con una colección de movimientos predefinidos.
    static {
        registerMove(new Move("Leer", Type.NORMAL, 0, 100, 30, MoveEffect.DEFENSE_DOWN, 1, MoveCategory.STATUS,100));
        registerMove(new Move("Quick Attack", Type.NORMAL, 40, 100, 30, MoveEffect.NONE, 1, MoveCategory.PHYSICAL,100));
        registerMove(new Move("Double Team", Type.NORMAL, 0, 0, 15, MoveEffect.EVASION_UP, 0, MoveCategory.STATUS,100));
        registerMove(new Move("Knock Off", Type.DARK, 65, 100, 20, MoveEffect.NONE, 0, MoveCategory.PHYSICAL,100));
        registerMove(new Move("Future Sight", Type.PSYCHIC, 120, 100, 10, MoveEffect.NONE, 0, MoveCategory.SPECIAL,100));
        registerMove(new Move("Water Pulse", Type.WATER, 60, 100, 20, MoveEffect.CONFUSION, 0, MoveCategory.SPECIAL,20));
        registerMove(new Move("Tackle", Type.NORMAL, 40, 100,35, MoveEffect.NONE ,0, MoveCategory.PHYSICAL,100));
        registerMove(new Move("Tail Whip", Type.NORMAL, 0, 100,30,MoveEffect.NONE,0, MoveCategory.STATUS,100));
        registerMove(new Move("Water Gun", Type.WATER, 40, 100,25,MoveEffect.NONE,0, MoveCategory.SPECIAL,100));
        registerMove(new Move("Withdraw", Type.WATER, 0, 0,40,MoveEffect.NONE,0, MoveCategory.STATUS,100));
        registerMove(new Move("Water Pulse", Type.WATER, 60, 100,20,MoveEffect.CONFUSION,0, MoveCategory.SPECIAL,20));
        registerMove(new Move("Bite", Type.DARK, 60, 100,25,MoveEffect.FLINCH,0, MoveCategory.PHYSICAL,30));
        registerMove(new Move("Scratch", Type.NORMAL, 40, 100,35,MoveEffect.NONE,0, MoveCategory.PHYSICAL,100));
        registerMove(new Move("Growl", Type.NORMAL, 0, 100, 40,MoveEffect.NONE,0,MoveCategory.STATUS,100));
        registerMove(new Move("Ember", Type.FIRE, 40, 100,25,MoveEffect.BURN,0, MoveCategory.SPECIAL,10));
        registerMove(new Move("Smokescreen", Type.NORMAL, 0, 100,20,MoveEffect.NONE,0, MoveCategory.STATUS,100));
        registerMove(new Move("Flamethrower", Type.FIRE, 90, 100,15,MoveEffect.BURN,0, MoveCategory.SPECIAL,10));
        registerMove(new Move("Slash", Type.NORMAL, 70, 100,20,MoveEffect.NONE,0, MoveCategory.PHYSICAL,100));
        registerMove(new Move("Cross Poison", Type.POISON, 70, 100,20,MoveEffect.POISON,0, MoveCategory.PHYSICAL,10));
        registerMove(new Move("Air Slash", Type.FLYING, 75, 95,15,MoveEffect.FLINCH,0, MoveCategory.SPECIAL,30));
        registerMove(new Move("Bite", Type.DARK, 60, 100,25,MoveEffect.FLINCH,0, MoveCategory.PHYSICAL,30));
        registerMove(new Move("Mean Look", Type.NORMAL, 0, 0,5,MoveEffect.NONE,0, MoveCategory.STATUS,100));
        registerMove(new Move("Screech", Type.NORMAL, 0, 85,40,MoveEffect.NONE,0, MoveCategory.STATUS,100));
        registerMove(new Move("Absorb", Type.PLANT, 20, 100,25,MoveEffect.HP,0, MoveCategory.SPECIAL,100));
    }

    /**
     * Registra un nuevo movimiento en el repositorio.
     * @param move El movimiento a registrar.
     */
    public static void registerMove(Move move) {
        moves.put(move.getName(), move);
    }

    /**
     * Obtiene un movimiento por su nombre.
     * @param name El nombre del movimiento.
     * @return El objeto Move correspondiente o null si no se encuentra.
     */
    public static Move getMove(String name) {
        return moves.get(name);
    }

    public static List<Move> getAllMovesForType(Type primaryType, Type secondaryType) {
        List<Move> typeMoves = moves.values().stream()
                .filter(m -> m.getType() == primaryType ||
                        (secondaryType != null && m.getType() == secondaryType))
                .collect(Collectors.toList());

        // Asegurarnos de que hay suficientes movimientos de cada categoría
        List<Move> physical = typeMoves.stream()
                .filter(m -> m.getCategory() == MoveCategory.PHYSICAL)
                .limit(10) // Limitar para no sobrecargar la selección
                .collect(Collectors.toList());

        List<Move> special = typeMoves.stream()
                .filter(m -> m.getCategory() == MoveCategory.SPECIAL)
                .limit(10)
                .collect(Collectors.toList());

        List<Move> status = typeMoves.stream()
                .filter(m -> m.getCategory() == MoveCategory.STATUS)
                .limit(10)
                .collect(Collectors.toList());

        List<Move> result = new ArrayList<>();
        result.addAll(physical);
        result.addAll(special);
        result.addAll(status);

        return result;
    }
}
