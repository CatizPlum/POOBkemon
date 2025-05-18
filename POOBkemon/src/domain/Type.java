package domain;

/**
 * Enumeración que representa los tipos de Pokémon disponibles en el juego.
 * Cada tipo tiene fortalezas y debilidades contra otros tipos según las mecánicas
 * tradicionales de Pokémon.
 */
public enum Type {
    /** Tipo Fuego - Efectivo contra Plant, Bug, Ice, Steel */
    FIRE,

    /** Tipo Volador - Efectivo contra Fighting, Bug, Grass */
    FLYING,

    /** Tipo Agua - Efectivo contra Fire, Ground, Rock */
    WATER,

    /** Tipo Planta - Efectivo contra Water, Ground, Rock */
    PLANT,

    /** Tipo Fantasma - Efectivo contra Psychic, Ghost */
    GHOST,

    /** Tipo Dragón - Efectivo contra otros Dragones */
    DRAGON,

    /** Tipo Hada - Efectivo contra Fighting, Dragon, Dark */
    FAIRY,

    /** Tipo Roca - Efectivo contra Fire, Ice, Flying, Bug */
    ROCK,

    /** Tipo Normal - Sin ventajas particulares */
    NORMAL,

    /** Tipo Eléctrico - Efectivo contra Water, Flying */
    ELECTRIC,

    /** Tipo Psíquico - Efectivo contra Fighting, Poison */
    PSYCHIC,

    /** Tipo Hielo - Efectivo contra Plant, Ground, Flying, Dragon */
    ICE,

    /** Tipo Lucha - Efectivo contra Normal, Ice, Rock, Dark, Steel */
    FIGHTING,

    /** Tipo Tierra - Efectivo contra Fire, Electric, Poison, Rock, Steel */
    GROUND,

    /** Tipo Acero - Efectivo contra Ice, Rock, Fairy */
    STEEL,

    /** Tipo Siniestro - Efectivo contra Psychic, Ghost */
    DARK,

    /** Tipo Veneno - Efectivo contra Plant, Fairy */
    POISON,

    /** Tipo Bicho - Efectivo contra Plant, Psychic, Dark */
    BUG
}