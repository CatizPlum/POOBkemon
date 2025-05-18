package domain;

import java.io.Serializable;

public class Table implements Serializable {

    public static double adv(Type damage, Type a, Type b) {
        double primary = 1;
        double secondary = 1;

        if (damage == Type.STEEL) {
            if (a == Type.STEEL || a == Type.WATER || a == Type.ELECTRIC || a == Type.FIRE) {
                primary = 0.5;
            }
            if (b == Type.STEEL || b == Type.WATER || b == Type.ELECTRIC || b == Type.FIRE) {
                secondary = 0.5;
            }
            if (a == Type.FAIRY || a == Type.ICE || a == Type.ROCK) {
                primary = 2;
            }
            if (b == Type.FAIRY || b == Type.ICE || b == Type.ROCK) {
                secondary = 2;
            }
        } else if (damage == Type.WATER) {
            if (a == Type.WATER || a == Type.DRAGON || a == Type.PLANT) {
                primary = 0.5;
            }
            if (b == Type.WATER || b == Type.DRAGON || b == Type.PLANT) {
                secondary = 0.5;
            }
            if (a == Type.FIRE || a == Type.ROCK || a == Type.GROUND) {
                primary = 2;
            }
            if (b == Type.FIRE || b == Type.ROCK || b == Type.GROUND) {
                secondary = 2;
            }
        } else if (damage == Type.BUG) {
            if (a == Type.STEEL || a == Type.GHOST || a == Type.FIRE || a == Type.FAIRY || a == Type.FIGHTING || a == Type.POISON || a == Type.FLYING) {
                primary = 0.5;
            }
            if (b == Type.STEEL || b == Type.GHOST || b == Type.FIRE || b == Type.FAIRY || b == Type.FIGHTING || b == Type.POISON || b == Type.FLYING) {
                secondary = 0.5;
            }
            if (a == Type.PLANT || a == Type.PSYCHIC || a == Type.DARK) {
                primary = 2;
            }
            if (b == Type.PLANT || b == Type.PSYCHIC || b == Type.DARK) {
                secondary = 2;
            }
        } else if (damage == Type.DRAGON) {
            if (a == Type.WATER) {
                primary = 0.5;
            }
            if (b == Type.WATER) {
                secondary = 0.5;
            }
            if (a == Type.DRAGON) {
                primary = 2;
            }
            if (b == Type.DRAGON) {
                secondary = 2;
            }
            if (a == Type.FAIRY) {
                primary = 0;
            }
            if (b == Type.FAIRY) {
                secondary = 0;
            }
        } else if (damage == Type.ELECTRIC){
            if (a == Type.DRAGON || a == Type.ELECTRIC || a == Type.PLANT){
                primary = 0.5;
            }
            if (b == Type.DRAGON || b == Type.ELECTRIC || b == Type.PLANT){
                secondary = 0.5;
            }
            if (a == Type.WATER || a == Type.FLYING){
                primary = 2;
            }
            if (b == Type.WATER || b == Type.FLYING){
                secondary = 2;
            }
            if (a == Type.GROUND){
                primary = 0;
            }
            if (b == Type.GROUND){
                secondary = 0;
            }
        }else if (damage == Type.GHOST){
            if (a == Type.DARK){
                primary = 0.5;
            }
            if (b == Type.DARK){
                secondary = 0.5;
            } 
            if (a == Type.GHOST || a == Type.PSYCHIC){
                primary = 2;
            }
            if (b == Type.GHOST || b == Type.PSYCHIC){
                secondary = 2;
            }
            if (a == Type.DARK){
                primary = 0.5;
            }
            if (b == Type.DARK){
                secondary = 0.5;
            } 
            if (a == Type.NORMAL){
                primary = 0;
            }
            if (b == Type.NORMAL){
                secondary = 0;
            }
        }else if (damage == Type.FIRE){
            if (a == Type.WATER || a == Type.DRAGON || a == Type.FIRE || a == Type.ROCK){
                primary = 0.5;
            }
            if (b == Type.WATER || b == Type.DRAGON || b == Type.FIRE || b == Type.ROCK){
                secondary = 0.5;
            }
            if (a == Type.STEEL || a == Type.BUG || a == Type.ICE || a == Type.PLANT){
                primary = 2;
            }
            if (b == Type.WATER || b == Type.DRAGON || b == Type.FIRE || b == Type.ROCK){
                secondary = 2;
            }
        }else if (damage == Type.FAIRY){
            if (a == Type.STEEL || a == Type.FIRE || a == Type.POISON){
                primary = 0.5;
            }
            if (b == Type.STEEL || b == Type.FIRE || b == Type.POISON){
                secondary = 0.5;
            }
            if (a == Type.DRAGON || a == Type.FIGHTING || a == Type.PLANT){
                primary = 2;
            }
            if (b == Type.STEEL || b == Type.FIGHTING || b == Type.PLANT){
                secondary = 2;
            }
        }else if (damage == Type.ICE){
            if (a == Type.STEEL || a == Type.WATER || a == Type.FIRE || a == Type.ICE){
                primary = 0.5;
            }
            if (b== Type.STEEL || b == Type.WATER || b == Type.FIRE || b == Type.ICE){
                secondary = 0.5;
            }
            if (a == Type.DRAGON || a == Type.PLANT || a == Type.GROUND || a == Type.FLYING){
                primary = 2;
            }
            if (b == Type.STEEL || b == Type.FIGHTING || b == Type.PLANT){
                secondary = 2;
            }
        }else if (damage == Type.FIGHTING){
            if (a == Type.BUG || a == Type.FAIRY || a == Type.PSYCHIC || a == Type.POISON || a == Type.FLYING){
                primary = 0.5;
            }
            if (b== Type.BUG || b == Type.FAIRY || b == Type.PSYCHIC || b == Type.POISON || b == Type.FLYING){
                secondary = 0.5;
            }
            if (a == Type.STEEL || a == Type.ICE || a == Type.NORMAL || a == Type.ROCK || a == Type.DARK){
                primary = 2;
            }
            if (b == Type.STEEL || b == Type.ICE || b == Type.NORMAL || b == Type.ROCK || b == Type.DARK){
                secondary = 2;
            }
        }else if (damage == Type.NORMAL){
            if (a == Type.STEEL || a == Type.ROCK){
                primary = 0.5;
            }
            if (b == Type.STEEL || b == Type.ROCK){
                secondary = 0.5;
            }
            if (a == Type.GHOST){
                primary = 0;
            }
            if (b == Type.GHOST){
                secondary = 0;
            }
        }else if (damage == Type.PLANT){
            if (a == Type.STEEL || a == Type.BUG || a == Type.DRAGON || a == Type.DRAGON || a == Type.PLANT || a == Type.POISON || a == Type.FLYING){
                primary = 0.5;
            }
            if (b == Type.STEEL || b == Type.BUG || b == Type.DRAGON || b == Type.DRAGON || b == Type.PLANT || b == Type.POISON || b == Type.FLYING){
                secondary = 0.5;
            }
            if (a == Type.WATER || a == Type.ROCK || a == Type.GROUND){
                primary = 2;
            }
            if (b == Type.WATER || b == Type.ROCK || b == Type.GROUND){
                secondary = 2;
            }
        }else if (damage == Type.PSYCHIC){
            if (a == Type.STEEL || a == Type.PSYCHIC){
                primary = 0.5;
            }
            if (b == Type.STEEL || b == Type.PSYCHIC){
                secondary = 0.5;
            }
            if (a == Type.FIGHTING || a == Type.POISON){
                primary = 2;
            }
            if (b == Type.FIGHTING || b == Type.POISON){
                secondary = 2;
            }
        }else if (damage == Type.ROCK){
            if (a == Type.STEEL || a == Type.FIGHTING || a == Type.GROUND){
                primary = 0.5;
            }
            if (b == Type.STEEL || b == Type.FIGHTING || b == Type.GROUND){
                secondary = 0.5;
            }
            if (a == Type.BUG || a == Type.FIRE || a == Type.ICE || a == Type.FLYING){
                primary = 2;
            }
            if (b == Type.BUG || b == Type.FIRE ||b == Type.ICE || b == Type.FLYING){
                secondary = 2;
            }
        }else if (damage == Type.DARK){
            if (a == Type.GHOST || a == Type.FIGHTING || a == Type.DARK){
                primary = 0.5;
            }
            if (b == Type.GHOST  || b == Type.FIGHTING || b == Type.DARK){
                secondary = 0.5;
            }
            if (a == Type.GHOST || a == Type.PSYCHIC ){
                primary = 2;
            }
            if (b == Type.GHOST || b == Type.PSYCHIC ){
                secondary = 2;
            }
        }else if (damage == Type.GROUND){
            if (a == Type.BUG || a == Type.PLANT){
                primary = 0.5;
            }
            if (b == Type.BUG  || b == Type.PLANT){
                secondary = 0.5;
            }
            if (a == Type.STEEL || a == Type.ELECTRIC || a == Type.FIRE || a == Type.ROCK || a == Type.POISON){
                primary = 2;
            }
            if (b == Type.STEEL || b == Type.ELECTRIC || b == Type.FIRE || b == Type.ROCK || b == Type.POISON){
                secondary = 2;
            }
            if(a == Type.FLYING){
                primary = 0;
            }
            if(b == Type.FLYING){
                secondary = 0;
            }
        }else if (damage == Type.POISON){
            if (a == Type.GHOST || a == Type.ROCK || a == Type.GROUND || a == Type.POISON){
                primary = 0.5;
            }
            if (b == Type.GHOST || b == Type.ROCK || b == Type.GROUND || b == Type.POISON){
                secondary = 0.5;
            }
            if (a == Type.FAIRY || a == Type.PLANT){
                primary = 2;
            }
            if (b == Type.FAIRY || b == Type.PLANT){
                secondary = 2;
            }
            if(a == Type.STEEL){
                primary = 0;
            }
            if(b == Type.STEEL){
                secondary = 0;
            }
        }else if (damage == Type.POISON){
            if (a == Type.STEEL || a == Type.ELECTRIC || a == Type.ROCK){
                primary = 0.5;
            }
            if (b == Type.STEEL || b == Type.ELECTRIC || b == Type.ROCK){
                secondary = 0.5;
            }
            if (a == Type.BUG || a == Type.FIGHTING || a == Type.PLANT){
                primary = 2;
            }
            if (b == Type.BUG || b == Type.FIGHTING || b == Type.PLANT){
                secondary = 2;
            }
        }
    
        return primary * secondary;
    }
}