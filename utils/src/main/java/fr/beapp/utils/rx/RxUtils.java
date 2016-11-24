package fr.beapp.utils.rx;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collection;

import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

public class RxUtils {

	private RxUtils() {
	}

	/**
	 * If the given value is non-<code>null</code>, returns an {@link Observable} that emits a single item and then completes if the given value is not null.
	 * <p/>
	 * It the value is <code>null</code>, Returns an Observable that emits no items to the {@link Observer} and immediately invokes its {@link Observer#onCompleted onCompleted} method.
	 *
	 * @param item the item to emit
	 * @param <T>  the type of the item (ostensibly) emitted by the Observable
	 * @return an Observable that emits none or one item
	 */
	@NonNull
	public static <T> Observable<T> justOrEmpty(@Nullable T item) {
		return (item == null ? Observable.<T>empty() : Observable.just(item))
				.subscribeOn(Schedulers.computation());
	}

	/**
	 * If the given value is non-<code>null</code>, returns an {@link Observable} that emits a single item and then completes if the given value is not null.
	 * <p/>
	 * It the value is <code>null</code>, Converts the given {@link Iterable} sequence into an {@link Observable} that emits the items in the sequence.
	 *
	 * @param items the source {@link Iterable} sequence
	 * @param <T>   the type of the items (ostensibly) emitted by the Observable
	 * @return an Observable that emits none or all items of the {@link Iterable}
	 */
	@NonNull
	public static <T> Observable<T> fromOrEmpty(@Nullable Collection<T> items) {
		return (items == null ? Observable.<T>empty() : Observable.from(items))
				.subscribeOn(Schedulers.computation());
	}

}
