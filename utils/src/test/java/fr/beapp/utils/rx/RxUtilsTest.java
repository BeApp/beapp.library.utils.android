package fr.beapp.utils.rx;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;

public class RxUtilsTest {

	private TestSubscriber<Object> testSubscriber;
	private TestObserver<Object> testObserver;

	@Before
	public void initTest() {
		testSubscriber = TestSubscriber.create();
		testObserver = TestObserver.create();
	}

	@Test
	public void testJustOrEmpty_null() throws Exception {
		RxUtils.justOrEmpty(null).subscribe(testObserver);

		testObserver.awaitTerminalEvent();
		testObserver.assertComplete();
		testObserver.assertNoErrors();
		testObserver.assertNoValues();
	}

	@Test
	public void testJustOrEmpty_value() throws Exception {
		RxUtils.justOrEmpty("test").subscribe(testObserver);

		testObserver.awaitTerminalEvent();
		testObserver.assertComplete();
		testObserver.assertNoErrors();
		testObserver.assertValueCount(1);
		testObserver.assertValue("test");
	}

	@Test
	public void testFromOrEmpty_null() throws Exception {
		RxUtils.fromOrEmpty((Collection<Object>) null).subscribe(testSubscriber);

		testSubscriber.awaitTerminalEvent();
		testSubscriber.assertComplete();
		testSubscriber.assertNoErrors();
		testSubscriber.assertNoValues();
	}

	@Test
	public void testFromOrEmpty_oneItem() throws Exception {
		RxUtils.fromOrEmpty(Collections.singletonList("test")).subscribe(testSubscriber);

		testSubscriber.awaitTerminalEvent();
		testSubscriber.assertComplete();
		testSubscriber.assertNoErrors();
		testSubscriber.assertValueCount(1);
		testSubscriber.assertValue("test");
	}

	@Test
	public void testFromOrEmpty_multipleItem() throws Exception {
		RxUtils.fromOrEmpty(Arrays.asList(1, 2, 3)).subscribe(testSubscriber);

		testSubscriber.awaitTerminalEvent();
		testSubscriber.assertComplete();
		testSubscriber.assertNoErrors();
		testSubscriber.assertValueCount(3);
		testSubscriber.assertValues(1, 2, 3);
	}
}