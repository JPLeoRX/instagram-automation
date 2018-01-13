package instagram;

public interface RandomInterface<E> {
    E getRandom();

    E getRandom(int bound);
}
