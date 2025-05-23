package domain;

import java.util.List;

public class TrainerAdapter extends Trainer {
    private final AbstractMachine machine;

    public TrainerAdapter(AbstractMachine machine) {
        super(machine.getName(), machine.getColor(), machine.getTeam(), machine.getMovesMap());
        this.machine = machine;
    }

    @Override
    public Pokemon getCurrentPokemon() {
        return machine.getCurrentPokemon();
    }

    @Override
    public void switchPokemon(int index) throws PoobkemonException {
        machine.switchPokemon(index);
    }


    public List<Item> getItems() {
        return machine.getItems();
    }


    public int getHP() {
        return machine.getHP();
    }


    public int getMaxHP() {
        return machine.getMaxHP();
    }
}
