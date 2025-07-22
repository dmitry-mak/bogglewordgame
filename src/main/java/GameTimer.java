import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class GameTimer {

    private final int durationSeconds; // длительность таймера
    private final Consumer<String> onTick; // действия на каждую секунду
    private final Runnable onExpire; // действие по истечении таймера

    private ScheduledExecutorService scheduler; // поток, управляющий таймером
    private AtomicInteger remaining; // счетчик оставшегося времени

    private boolean running = true;

    public GameTimer(int durationSeconds, Consumer<String> onTick, Runnable onExpire) {
        this.durationSeconds = durationSeconds;
        this.onTick = onTick;
        this.onExpire = onExpire;
        this.remaining = new AtomicInteger(durationSeconds);
    }

    public void startTimer() {
//        remaining = new AtomicInteger(durationSeconds);
//        scheduler = Executors.newSingleThreadScheduledExecutor(); // однопоточный таймер, выполняет задачи в фоне
//
//
//        scheduler.scheduleAtFixedRate(() -> {
//                    if (remaining.get() <= 0) {
//                        scheduler.shutdown();
//                        onExpire.run();
//                        return;
//                    }
////            onTick.accept("Осталось времени: " + remaining.getAndDecrement() + " сек.");
////            remaining.decrementAndGet();
////        }, 0, 1, TimeUnit.SECONDS);
//                    String message = "Осталось времени: " + remaining.getAndDecrement() + " сек.";
//                    onTick.accept(message);
//                },
//                0, 1, TimeUnit.SECONDS);
//        int secondsLeft = durationSeconds;
        while (remaining.get() > 0 && running) {
//            onTick.accept(String.format("Осталось времени: %d сек.", secondsLeft));
            try {
                Thread.sleep(1000);
                remaining.decrementAndGet();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        running = false;
        onExpire.run();
    }

    public void stopTimer() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public int getRemainingTime() {
        return remaining.get();
    }
}
