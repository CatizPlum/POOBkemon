package domain;

public class Game {
    private Trainer trainer1;
    private Trainer trainer2;
    private Trainer currentTrainer;
    private Trainer waitingTrainer;

    public Game(Trainer trainer1, Trainer trainer2) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
        this.currentTrainer = trainer1;
        this.waitingTrainer = trainer2;
    }

    public Trainer getTrainer1() {
        return trainer1;
    }

    public Trainer getTrainer2() {
        return trainer2;
    }

    public void nextTurn() {
        Trainer temp = currentTrainer;
        currentTrainer = waitingTrainer;
        waitingTrainer = temp;
    }

    public Trainer getCurrentTrainer() {
        return currentTrainer;
    }

    public Trainer getWaitingTrainer() {
        return waitingTrainer;
    }

    public boolean isOver() {
        return trainer1.getCurrentPokemon().isFainted() || trainer2.getCurrentPokemon().isFainted();
    }

    public void triggerDelibirdGift() throws PoobkemonException {
        Pokemon current = currentTrainer.getCurrentPokemon();
        Pokemon opponent = waitingTrainer.getCurrentPokemon();

        if (current instanceof Delibird) {
            ((Delibird) current).surpriseGift(opponent);
        } else {
            throw new PoobkemonException("El Pokémon activo no es Delibird.");
        }
    }

}
