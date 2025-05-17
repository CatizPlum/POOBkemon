package domain;

public abstract class Item {
    private String name;

    public Item(String name) {
        this.name = name;
    }

    public abstract void apply(Pokemon target) throws PoobkemonException;

    public String getName() {
        return name;
    }
}