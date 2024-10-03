package lecture5and6;

import java.util.function.Consumer;

public class CodeTimer {
	public static <T> long timeMethod(Consumer<T> method,T input) {
		long startTime = System.nanoTime();
		method.accept(input);
		long endTime = System.nanoTime();
		long timeElapsed = (endTime - startTime) / 1000000;
		System.out.println("Time elapsed: "+timeElapsed+"ms");
		return timeElapsed;
	}
}
