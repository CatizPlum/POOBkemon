package domain;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * La clase Trainer representa a un entrenador en un juego de Pokémon.
 * Cada entrenador tiene un nombre, color distintivo, equipo de Pokémon,
 * colección de ítems y un Pokémon activo en batalla.
 */
public class Trainer implements Serializable {
    private String name;               // Nombre del entrenador
    private Color color;              // Color asociado al entrenador
    private List<Pokemon> team;       // Lista de Pokémon en el equipo (máx 6)
    private Map<String, List<Item>> itemMap = new java.util.HashMap<>();

    // Lista de ítems en posesión
    private int activePokemonIndex;   // Índice del Pokémon activo en batalla

    /**
     * Constructor para crear un nuevo entrenador.
     *
     * @param name Nombre del entrenador
     * @param color Color asociado al entrenador
     * @param team Lista de Pokémon que forman el equipo
     * @param items Lista de ítems que posee el entrenador
     */
    public Trainer(String name, Color color, List<Pokemon> team, List<Item> items) throws PoobkemonException {
        this.name = name;
        this.color = color;
        this.team = team;
        this.itemMap = new java.util.HashMap<>();
        this.activePokemonIndex = 0;

        for (Item item : items) {
            addItem(item); // usa el método que impone límites (2 pociones, 1 revive)
        }
    }


    public Trainer(String name, Color color,
                   List<Pokemon> team,
                   Map<Pokemon, List<Move>> moveMap) {
        this.name = name;
        this.color = color;
        this.team = team;
        this.itemMap = new java.util.HashMap<>();
        this.activePokemonIndex = 0;

        // Asignar los movimientos personalizados a cada Pokémon
        for (Pokemon p : this.team) {
            List<Move> customMoves = moveMap.get(p);
            if (customMoves != null && !customMoves.isEmpty()) {
                p.getMoves().clear();
                p.getMoves().addAll(customMoves);
            }
        }
    }

    public void addItem(Item item) throws PoobkemonException {
        String key = item.getClass().getSimpleName();
        List<Item> list = itemMap.getOrDefault(key, new java.util.ArrayList<>());

        int maxAllowed = (item instanceof Revive) ? 1 : 2;
        if (list.size() >= maxAllowed) {
            throw new PoobkemonException("No puedes tener más de " + maxAllowed + " " + key + "(s).");
        }

        list.add(item);
        itemMap.put(key, list);
    }




    /**
     * Obtiene el Pokémon actualmente activo en batalla.
     *
     * @return El Pokémon activo actual
     */
    public Pokemon getCurrentPokemon() {
        return team.get(activePokemonIndex);
    }

    /**
     * Cambia el Pokémon activo por otro del equipo.
     *
     * @param index Índice del Pokémon al que se quiere cambiar
     * @throws PoobkemonException Si el índice es inválido o el Pokémon está debilitado
     */
    public void switchPokemon(int index) throws PoobkemonException {
        if (index < 0 || index >= team.size()) {
            throw new PoobkemonException("Invalid Pokémon index!");
        }
        if (team.get(index).isFainted()) {
            throw new PoobkemonException("Cannot switch to a fainted Pokémon!");
        }
        activePokemonIndex = index;
    }

    /**
     * Usa un ítem en el Pokémon activo actual.
     *
     * @param item Ítem que se desea usar
     * @throws PoobkemonException Si ocurre un error al aplicar el ítem
     */
    public void useItem(Item item) throws PoobkemonException {
        item.apply(getCurrentPokemon());

        String key = item.getClass().getSimpleName();
        List<Item> list = itemMap.get(key);
        if (list != null) {
            list.remove(item);
            if (list.isEmpty()) {
                itemMap.remove(key);
            }
        }
    }


    // Métodos getters

    /**
     * @return Nombre del entrenador
     */
    public String getName() {
        return name;
    }

    /**
     * @return Color asociado al entrenador
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return Lista de Pokémon en el equipo
     */
    public List<Pokemon> getTeam() {
        return team;
    }

    /**
     * @return Lista de ítems en posesión del entrenador
     */
    public List<Item> getItems() {
        List<Item> all = new java.util.ArrayList<>();
        for (List<Item> group : itemMap.values()) {
            all.addAll(group);
        }
        return all;
    }

}