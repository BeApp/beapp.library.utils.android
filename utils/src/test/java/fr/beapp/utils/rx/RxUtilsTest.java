package fr.beapp.utils.rx;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import rx.observers.TestSubscriber;

public class RxUtilsTest {

	private TestSubscriber<Object> testSubscriber;

	@Before
	public void initTest() {
		testSubscriber = new TestSubscriber<>();
	}

	@Test
	public void testJustOrEmpty_null() throws Exception {
		RxUtils.justOrEmpty(null).subscribe(testSubscriber);

		testSubscriber.awaitTerminalEvent();
		testSubscriber.assertCompleted();
		testSubscriber.assertNoErrors();
		testSubscriber.assertNoValues();
	}

	@Test
	public void testJustOrEmpty_value() throws Exception {
		RxUtils.justOrEmpty("test").subscribe(testSubscriber);

		testSubscriber.awaitTerminalEvent();
		testSubscriber.assertCompleted();
		testSubscriber.assertNoErrors();
		testSubscriber.assertValueCount(1);
		testSubscriber.assertValue("test");
	}

	@Test
	public void testFromOrEmpty_null() throws Exception {
		RxUtils.fromOrEmpty(null).subscribe(testSubscriber);

		testSubscriber.awaitTerminalEvent();
		testSubscriber.assertCompleted();
		testSubscriber.assertNoErrors();
		testSubscriber.assertNoValues();
	}

	@Test
	public void testFromOrEmpty_oneItem() throws Exception {
		RxUtils.fromOrEmpty(Collections.singletonList("test")).subscribe(testSubscriber);

		testSubscriber.awaitTerminalEvent();
		testSubscriber.assertCompleted();
		testSubscriber.assertNoErrors();
		testSubscriber.assertValueCount(1);
		testSubscriber.assertValue("test");
	}

	@Test
	public void testFromOrEmpty_multipleItem() throws Exception {
		RxUtils.fromOrEmpty(Arrays.asList(1, 2, 3)).subscribe(testSubscriber);

		testSubscriber.awaitTerminalEvent();
		testSubscriber.assertCompleted();
		testSubscriber.assertNoErrors();
		testSubscriber.assertValueCount(3);
		testSubscriber.assertValues(1, 2, 3);
	}
}