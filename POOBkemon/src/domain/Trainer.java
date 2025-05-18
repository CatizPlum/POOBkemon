package domain;

import java.awt.Color;
import java.io.Serializable;
import java.util.List;

/**
 * La clase Trainer representa a un entrenador en un juego de Pokémon.
 * Cada entrenador tiene un nombre, color distintivo, equipo de Pokémon,
 * colección de ítems y un Pokémon activo en batalla.
 */
public class Trainer implements Serializable {
    private String name;               // Nombre del entrenador
    private Color color;              // Color asociado al entrenador
    private List<Pokemon> team;       // Lista de Pokémon en el equipo (máx 6)
    private List<Item> items;         // Lista de ítems en posesión
    private int activePokemonIndex;   // Índice del Pokémon activo en batalla

    /**
     * Constructor para crear un nuevo entrenador.
     *
     * @param name Nombre del entrenador
     * @param color Color asociado al entrenador
     * @param team Lista de Pokémon que forman el equipo
     * @param items Lista de ítems que posee el entrenador
     */
    public Trainer(String name, Color color, List<Pokemon> team, List<Item> items) {
        this.name = name;
        this.color = color;
        this.team = team;
        this.items = items;
        this.activePokemonIndex = 0;  // Primer Pokémon activo por defecto
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
        try {
            item.apply(getCurrentPokemon());
            items.remove(item);
        } catch (PoobkemonException e) {
            throw e; // Relanza la excepción para manejo en la GUI
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
        return items;
    }
}