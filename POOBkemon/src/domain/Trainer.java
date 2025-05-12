package domain;

import java.awt.Color;
import java.util.List;

public class Trainer {
    private String name;
    private Color color;
    private List<Pokemon> team;
    private List<Item> items;
    private int activePokemonIndex;

    public Trainer(String name, Color color, List<Pokemon> team, List<Item> items) {
        this.name = name;
        this.color = color;
        this.team = team;
        this.items = items;
        this.activePokemonIndex = 0;

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
}
