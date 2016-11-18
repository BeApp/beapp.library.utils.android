package fr.beapp.utils.rx;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Collection;

import rx.Observable;
import rx.schedulers.Schedulers;

public class RxUtils {

	@NonNull
	public static <T> Observable<T> justOrEmpty(@Nullable T item) {
		return (item == null ? Observable.<T>empty() : Observable.just(item))
				.subscribeOn(Schedulers.computation());
	}

	@NonNull
	public static <T> Observable<T> fromOrEmpty(@Nullable Collection<T> items) {
		return (items == null ? Observable.<T>empty() : Observable.from(items))
				.subscribeOn(Schedulers.computation());
	}

}
