package automation.unfollow_service;

public class UnfollowServiceRunnable implements Runnable {
    private UnfollowService unfollowService;

    public UnfollowServiceRunnable(UnfollowService unfollowService) {
        this.unfollowService = unfollowService;
    }

    @Override
    public void run() {
        // Try to call the iteration
        try {
            unfollowService.iteration();
        }

        // If any exception occurred - keep rerunning the iteration
        catch (Exception e) {
            e.printStackTrace();
            run();
        }
    }
}
