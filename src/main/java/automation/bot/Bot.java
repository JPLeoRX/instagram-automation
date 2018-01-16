package automation.bot;

public interface Bot {
    // Must be kept as randomized as possible since it might get caught up in an infinite loop
    void iteration();

    // In this method we should start all the threads of this bot
    void start();
}
