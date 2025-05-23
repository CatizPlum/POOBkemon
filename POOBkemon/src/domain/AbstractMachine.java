package domain;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class AbstractMachine implements Serializable {
    protected String name;
    protected Color color;
    protected List<Pokemon> team;
    protected List<Item> items;
    protected int activePokemonIndex;
    protected Map<Pokemon, List<Move>> movesMap;

    public AbstractMachine(String name, Color color, List<Pokemon> team, Map<Pokemon, List<Move>> moveMap) {
        if (team == null || team.isEmpty()) {
            throw new IllegalArgumentException("El equipo no puede estar vacío.");
        }

        this.name = name;
        this.color = color;
        this.team = team;
        this.items = new java.util.ArrayList<>();
        this.activePokemonIndex = 0;
        this.movesMap = moveMap;

        for (Pokemon p : this.team) {
            List<Move> customMoves = moveMap.get(p);
            if (customMoves != null && !customMoves.isEmpty()) {
                p.getMoves().clear();
                p.getMoves().addAll(customMoves);
            } else {
                // Asignar movimientos por tipo si no se especificaron
                List<Move> defaultMoves = MoveRepository.getAllMovesForType(p.getPrimaryType(), p.getSecondaryType());
                if (!defaultMoves.isEmpty()) {
                    p.getMoves().clear();
                    p.getMoves().addAll(defaultMoves.subList(0, Math.min(4, defaultMoves.size())));
                }
            }
        }
    }

    public Map<Pokemon, List<Move>> getMovesMap() {
        return movesMap;
    }

    public Pokemon getCurrentPokemon() {
        if (team == null || team.isEmpty()) {
            throw new IllegalStateException("El equipo está vacío. No hay Pokémon activos.");
        }
        if (activePokemonIndex < 0 || activePokemonIndex >= team.size()) {
            throw new IndexOutOfBoundsException("Índice de Pokémon activo fuera de rango: " + activePokemonIndex);
        }
        return team.get(activePokemonIndex);
    }

    public void switchPokemon(int index) throws PoobkemonException {
        if (index < 0 || index >= team.size()) {
            throw new PoobkemonException("Invalid Pokémon index!");
        }
        if (team.get(index).isFainted()) {
            throw new PoobkemonException("Cannot switch to a fainted Pokémon!");
        }
        activePokemonIndex = index;
    }

    public void useItem(Item item) throws PoobkemonException {
        item.apply(getCurrentPokemon());
        items.remove(item);
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public List<Pokemon> getTeam() {
        return team;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getHP() {
        return getCurrentPokemon().getCurrentHP();
    }

    public int getMaxHP() {
        return getCurrentPokemon().getMaxHP();
    }

    public void attack(AbstractMachine opponent, Move move) {
        Pokemon attacker = getCurrentPokemon();
        Pokemon defender = opponent.getCurrentPokemon();

        int damage = move.calculateDamage(attacker, defender);
        defender.takeDamage(damage);

        System.out.println(name + " hace que " + attacker.getName() +
                " use " + move.getName() + " y causa " + damage + " de daño a " + defender.getName());
    }

    public abstract void makeMove(Game game);
}
