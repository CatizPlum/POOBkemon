package domain;

import java.util.List;

public interface Pokemon {
    String getName();
    Type getPrimaryType();
    Type getSecondaryType();
    int getMaxHP();
    int getCurrentHP();
    int getAttack();
    int getDefense();
    int getSpecialAttack();
    int getSpecialDefense();
    int getSpeed();
    List<Move> getMoves();
    
    void takeDamage(int amount);
    void heal(int amount);
    boolean isFainted();
}
