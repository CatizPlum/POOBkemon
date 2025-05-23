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

    // ✅ NUEVO CAMPO
    protected Map<Pokemon, List<Move>> movesMap;

    public AbstractMachine(String name, Color color, List<Pokemon> team, Map<Pokemon, List<Move>> moveMap) {
        this.name = name;
        this.color = color;
        this.team = team;
        this.items = new java.util.ArrayList<>();
        this.activePokemonIndex = 0;
        this.movesMap = moveMap;  // ✅ Guardar directamente el mapa

        for (Pokemon p : this.team) {
            List<Move> customMoves = moveMap.get(p);
            if (customMoves != null && !customMoves.isEmpty()) {
                p.getMoves().clear();
                p.getMoves().addAll(customMoves);
            }
        }
    }

    // ✅ MÉTODO GETTER NECESARIO
    public Map<Pokemon, List<Move>> getMovesMap() {
        return movesMap;
    }

    public Pokemon getCurrentPokemon() {
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

    public abstract void makeMove(Game game);
}
