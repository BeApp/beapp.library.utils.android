package fr.beapp.utils.rx;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Pair;

import org.reactivestreams.Publisher;

import java.util.Collection;
import java.util.concurrent.Callable;

import io.reactivex.Emitter;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {

	private RxUtils() {
	}

	/**
	 * If the given value is non-<code>null</code>, returns a {@link Maybe} that emits a single item and then completes.
	 * <p/>
	 * It the value is <code>null</code>, returns a {@link Maybe} that emits no items to the {@link Observer} and immediately invokes its {@link Observer#onComplete onCompleted} method.
	 *
	 * @param item the item to emit
	 * @param <T>  the type of the item (ostensibly) emitted by the Observable
	 * @return a {@link Maybe} that emits none or one item
	 */
	@NonNull
	public static <T> Maybe<T> justOrEmpty(@Nullable T item) {
		return (item == null ? Maybe.<T>empty() : Maybe.just(item))
				.subscribeOn(Schedulers.computation());
	}

	/**
	 * If the given value is non-<code>null</code>, returns a {@link Single} that emits a single item and then completes.
	 * <p/>
	 * It the value is <code>null</code>, returns an {@link Single} that emits no items to the {@link Observer} and immediately invokes its {@link Observer#onComplete onCompleted} method.
	 *
	 * @param item the item to emit
	 * @param <T>  the type of the item (ostensibly) emitted by the Observable
	 * @return a {@link Single} that emits one item or complete directly
	 */
	@NonNull
	public static <T> Single<T> justOrNever(@Nullable T item) {
		return (item == null ? Single.<T>never() : Single.just(item))
				.subscribeOn(Schedulers.computation());
	}

	/**
	 * If the given value is non-<code>null</code>, returns a {@link Flowable} that emits all items of the list and then completes.
	 * <p/>
	 * It the value is <code>null</code>, returns a {@link Flowable} that emits no items to the {@link Observer} and immediately invokes its {@link Observer#onComplete onCompleted} method.
	 *
	 * @param items the source {@link Iterable} sequence
	 * @param <T>   the type of the items (ostensibly) emitted by the Observable
	 * @return a {@link Flowable} that emits all items of the {@link Iterable}
	 */
	@NonNull
	public static <T> Flowable<T> fromOrEmpty(@Nullable Collection<T> items) {
		return (items == null ? Flowable.<T>empty() : Flowable.fromIterable(items))
				.subscribeOn(Schedulers.computation());
	}

	/**
	 * If the given value is non-<code>null</code>, returns a {@link Flowable} that emits all items of the list and then completes.
	 * <p/>
	 * It the value is <code>null</code>, returns a {@link Flowable} that emits no items to the {@link Observer} and immediately invokes its {@link Observer#onComplete onCompleted} method.
	 *
	 * @param items the source arguments
	 * @param <T>   the type of the items (ostensibly) emitted by the Observable
	 * @return a {@link Flowable} that emits all items of the {@link Iterable}
	 */
	@NonNull
	public static <T> Flowable<T> fromOrEmpty(T... items) {
		return (items == null ? Flowable.<T>empty() : Flowable.fromArray(items))
				.subscribeOn(Schedulers.computation());
	}

	public static <T> FlowableTransformer<T, Pair<Integer, T>> toIndexedValues() {
		return new FlowableTransformer<T, Pair<Integer, T>>() {
			@Override
			public Publisher<Pair<Integer, T>> apply(Flowable<T> observable) {
				return observable.zipWith(infiniteIterator(0), new BiFunction<T, Integer, Pair<Integer, T>>() {
					@Override
					public Pair<Integer, T> apply(T value, Integer index) throws Exception {
						return Pair.create(index, value);
					}
				});
			}
		};
	}

	public static Flowable<Integer> infiniteIterator(final int start) {
		return Flowable.generate(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return start;
			}
		}, new BiConsumer<Integer, Emitter<Integer>>() {
			@Override
			public void accept(Integer lastCount, Emitter<Integer> tEmitter) throws Exception {
				tEmitter.onNext(lastCount + 1);
			}
		});
	}

}
