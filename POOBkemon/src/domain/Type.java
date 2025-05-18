package domain;

import java.io.Serializable;

/**
 * Enumeración que representa los tipos elementales en el sistema Pokémon.
 * Implementa Serializable para permitir la serialización de objetos que lo contengan.
 *
 * <p>Los tipos determinan fortalezas, debilidades e inmunidades en los combates,
 * siguiendo las reglas estándar de la franquicia Pokémon.</p>
 */
public enum Type implements Serializable {
    /** Tipo Fuego - Efectivo contra Planta, Hielo, Bicho, Acero */
    FIRE,

    /** Tipo Volador - Efectivo contra Planta, Lucha, Bicho */
    FLYING,

    /** Tipo Agua - Efectivo contra Fuego, Tierra, Roca */
    WATER,

    /** Tipo Planta - Efectivo contra Agua, Tierra, Roca */
    PLANT,

    /** Tipo Fantasma - Efectivo contra Fantasma, Psíquico */
    GHOST,

    /** Tipo Dragón - Efectivo contra Dragón */
    DRAGON,

    /** Tipo Hada - Efectivo contra Lucha, Dragón, Siniestro */
    FAIRY,

    /** Tipo Roca - Efectivo contra Fuego, Hielo, Volador, Bicho */
    ROCK,

    /** Tipo Normal - Sin ventajas particulares */
    NORMAL,

    /** Tipo Eléctrico - Efectivo contra Agua, Volador */
    ELECTRIC,

    /** Tipo Psíquico - Efectivo contra Lucha, Veneno */
    PSYCHIC,

    /** Tipo Hielo - Efectivo contra Planta, Tierra, Volador, Dragón */
    ICE,

    /** Tipo Lucha - Efectivo contra Normal, Hielo, Roca, Siniestro, Acero */
    FIGHTING,

    /** Tipo Tierra - Efectivo contra Fuego, Eléctrico, Veneno, Roca, Acero */
    GROUND,

    /** Tipo Acero - Efectivo contra Hielo, Roca, Hada */
    STEEL,

    /** Tipo Siniestro - Efectivo contra Fantasma, Psíquico */
    DARK,

    /** Tipo Veneno - Efectivo contra Planta, Hada */
    POISON,

    /** Tipo Bicho - Efectivo contra Planta, Psíquico, Siniestro */
    BUG
}