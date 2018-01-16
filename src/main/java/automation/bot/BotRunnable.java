package automation.bot;

public class BotRunnable implements Runnable {
    private Bot bot;

    public BotRunnable(Bot bot) {
        this.bot = bot;
    }

    @Override
    public void run() {
        // Try to call the bot's iteration
        try {
            bot.iteration();
        }

        // If any exception occurred - keep rerunning the iteration
        catch (Exception e) {
            e.printStackTrace();
            run();
        }
    }
}