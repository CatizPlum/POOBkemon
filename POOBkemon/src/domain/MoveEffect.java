package domain;

/**
 * Efectos secundarios que pueden aplicar los movimientos.
 * Incluye cambios de estado, modificadores de stats, efectos de campo, etc.
 */
public enum MoveEffect {
    // === Estados alterados (Status Conditions) ===
    BURN,           // Quemadura (reduce Ataque físico, daño por turno)
    FREEZE,         // Congelación (inmoviliza)
    PARALYZE,       // Parálisis (reduce Velocidad, puede inmovilizar)
    POISON,         // Envenenamiento (daño por turno)
    TOXIC,          // Envenenamiento grave (daño incremental)
    SLEEP,          // Sueño (inmoviliza 1-3 turnos)
    CONFUSION,      // Confusión (puede atacarse a sí mismo)
    FLINCH,         // Retroceso (pierde turno si es golpeado primero)

    // === Modificadores de estadísticas ===
    ATTACK_UP,      // Aumenta Ataque (1 nivel)
    ATTACK_DOWN,    // Reduce Ataque
    DEFENSE_UP,     // Aumenta Defensa
    DEFENSE_DOWN,   // Reduce Defensa
    SP_ATTACK_UP,   // Aumenta Ataque Especial
    SP_ATTACK_DOWN, // Reduce Ataque Especial
    SP_DEFENSE_UP,  // Aumenta Defensa Especial
    SP_DEFENSE_DOWN,// Reduce Defensa Especial
    SPEED_UP,       // Aumenta Velocidad
    SPEED_DOWN,     // Reduce Velocidad
    ACCURACY_UP,    // Aumenta Precisión
    ACCURACY_DOWN,  // Reduce Precisión
    EVASION_UP,     // Aumenta Evasión
    EVASION_DOWN,   // Reduce Evasión

    // === Efectos de drenaje/retroceso ===
    DRAIN_HP,       // Drena vida (ej: Megaagotar)
    RECOIL,         // Daño por retroceso (ej: Latigazo)
    HEAL,           // Cura al usuario (ej: Síntesis)

    // === Efectos de campo ===
    SET_WEATHER_SUN,      // Activa sol
    SET_WEATHER_RAIN,     // Activa lluvia
    SET_WEATHER_SANDSTORM,// Tormenta arena
    SET_WEATHER_HAIL,     // Granizo
    SET_SPIKES,           // Coloca púas
    SET_STEALTH_ROCK,     // Coloca rocas afiladas
    SET_TOXIC_SPIKES,     // Coloca púas tóxicas

    // === Efectos especiales ===
    TRAP_TARGET,    // Atrapa al objetivo (ej: Telaraña)
    FORCE_SWITCH,   // Fuerza cambio (ej: Viento Huracanado)
    CRITICAL_BOOST, // Aumenta probabilidad de crítico
    CHARGE,         // Carga el turno (ej: Rayo Solar)
    PROTECT,        // Bloquea el siguiente ataque
    MAGIC_COAT,     // Refleja efectos de estado
    SNATCH,         // Roba efectos de curación/mejora

    // === Efectos únicos ===
    RECHARGE,       // Requiere turno de recarga (ej: Hiperrayo)
    FAINT_USER,     // Usuario se debilita (ej: Autodestrucción)
    DESTINY_BOND,   // Si el usuario cae, el objetivo también
    PERISH_SONG,    // Ambos Pokémon se debilitan en 3 turnos
    HP, //El usuario recupera el 50% del HP drenado
    NONE;           // Sin efecto
}