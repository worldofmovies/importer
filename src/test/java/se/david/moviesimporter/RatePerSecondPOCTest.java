package se.david.moviesimporter;

import static org.slf4j.LoggerFactory.getLogger;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class RatePerSecondPOCTest {
	private static final Logger log = getLogger(RatePerSecondPOCTest.class);

	@Disabled("Test should not be run always as it takes time unnecessary")
	@Test
	public void tryingToFindAWayToLogSpeed() {
		final AtomicInteger seconds = new AtomicInteger(0);
		final AtomicInteger previousSecond = new AtomicInteger(0);
		final AtomicInteger progress = new AtomicInteger(0);

		final AtomicBoolean terminate = new AtomicBoolean(false);
		final AtomicInteger total = new AtomicInteger(0);
		Flux.interval(Duration.ofSeconds(1))
				.takeUntil(a -> terminate.get())
				.doOnEach(a -> seconds.incrementAndGet())
				.parallel(1)
				.runOn(Schedulers.elastic())
				.concatMap(a -> Flux.fromStream(IntStream.range(0, 500).boxed()))
				.doOnEach(a -> {
					if(total.incrementAndGet() >= 500) {
						terminate.set(true);
					}
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				})
				.sequential()
				.filter(a -> {
					progress.incrementAndGet();
					if (seconds.get() != previousSecond.getPlain()) {
						log.info("{} per second", progress.getAndSet(0));
						previousSecond.set(seconds.get());
						return true;
					}
					return false;
				})
				.blockLast(Duration.ofSeconds(10));
	}
}
