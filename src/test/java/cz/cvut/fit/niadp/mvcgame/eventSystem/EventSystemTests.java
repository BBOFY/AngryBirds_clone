package cz.cvut.fit.niadp.mvcgame.eventSystem;

import org.junit.Assert;
import org.junit.Test;

public class EventSystemTests {

    private int testingValue = 0;

    @Test
    public void testOneEvent() {
        testingValue = 0;
        MyEvent eventTestEvent = new MyEvent();
        MyEventObject incValueEO = new MyEventObject(this::incValueByOne);

        Assert.assertEquals(0, testingValue);
        eventTestEvent.invoke();
        Assert.assertEquals(0, testingValue);
        eventTestEvent.addListener(incValueEO);
        Assert.assertEquals(0, testingValue);
        eventTestEvent.invoke();
        Assert.assertEquals(1, testingValue);
        eventTestEvent.removeListener(incValueEO);
        Assert.assertEquals(1, testingValue);
        eventTestEvent.invoke();
        Assert.assertEquals(1, testingValue);
    }

    @Test
    public void testMultipleEvents() {
        testingValue = 0;
        int value = 5;
        MyEvent_1<Integer> eventTestEvent = new MyEvent_1<>();
        MyEventObject_1<Integer> incValueEO_1 = new MyEventObject_1<>(this::incValue);
        MyEventObject_1<Integer> incValueEO_2 = new MyEventObject_1<>(this::incValue);

        Assert.assertEquals(0, testingValue);
        eventTestEvent.invoke(value);
        Assert.assertEquals(0, testingValue);
        eventTestEvent.addListener(incValueEO_1);
        Assert.assertEquals(0, testingValue);
        eventTestEvent.invoke(value);
        Assert.assertEquals(5, testingValue);
        eventTestEvent.addListener(incValueEO_2);
        Assert.assertEquals(5, testingValue);
        eventTestEvent.invoke(value);
        Assert.assertEquals(15, testingValue);
        eventTestEvent.removeListener(incValueEO_1);
        Assert.assertEquals(15, testingValue);
        eventTestEvent.invoke(value);
        Assert.assertEquals(20, testingValue);
        eventTestEvent.removeListener(incValueEO_2);
        Assert.assertEquals(20, testingValue);
        eventTestEvent.invoke(value);
        Assert.assertEquals(20, testingValue);
    }

    @Test
    public void addingSameEvent() {
        testingValue = 0;
        MyEvent eventTestEvent = new MyEvent();
        MyEventObject incValueEO = new MyEventObject(this::incValueByOne);

        Assert.assertEquals(0, testingValue);
        eventTestEvent.invoke();
        Assert.assertEquals(0, testingValue);
        eventTestEvent.addListener(incValueEO);
        Assert.assertEquals(0, testingValue);
        eventTestEvent.invoke();
        Assert.assertEquals(1, testingValue);
        eventTestEvent.removeListener(incValueEO);
        Assert.assertEquals(1, testingValue);
        eventTestEvent.invoke();
        Assert.assertEquals(1, testingValue);
        eventTestEvent.removeListener(incValueEO);
        Assert.assertEquals(1, testingValue);
    }

    @Test
    public void removingSameEvent() {
        testingValue = 0;
        MyEvent eventTestEvent = new MyEvent();
        MyEventObject incValueEO = new MyEventObject(this::incValueByOne);

        Assert.assertEquals(0, testingValue);
        eventTestEvent.invoke();
        Assert.assertEquals(0, testingValue);
        eventTestEvent.addListener(incValueEO);
        Assert.assertEquals(0, testingValue);
        eventTestEvent.invoke();
        Assert.assertEquals(1, testingValue);
        eventTestEvent.removeListener(incValueEO);
        Assert.assertEquals(1, testingValue);
        eventTestEvent.invoke();
        Assert.assertEquals(1, testingValue);
        eventTestEvent.removeListener(incValueEO);
        Assert.assertEquals(1, testingValue);

    }

    MyEvent forNextTestEvent;
    @Test
    public void eventSubscribesAnotherEventListener() {
        testingValue = 0;
        forNextTestEvent = new MyEvent();
        // This event should be delayed by one invoke call, since it adds listener to already invoked event.
        MyEventObject addingAnotherEO = new MyEventObject(this::addingAnotherEventListener);

        Assert.assertEquals(0, testingValue);
        forNextTestEvent.invoke();
        Assert.assertEquals(0, testingValue);
        forNextTestEvent.addListener(addingAnotherEO);
        Assert.assertEquals(0, testingValue);
        forNextTestEvent.invoke(); // Adds inc as listener
        Assert.assertEquals(0, testingValue);
        forNextTestEvent.invoke(); // Adds inc as listener and incs already added
        Assert.assertEquals(1, testingValue);
        forNextTestEvent.invoke(); // Adds inc as listener and incs already added
        Assert.assertEquals(3, testingValue);
        forNextTestEvent.removeListener(addingAnotherEO);
        Assert.assertEquals(3, testingValue);
        forNextTestEvent.invoke();
        Assert.assertEquals(6, testingValue);
    }

    private void incValueByOne() {
        testingValue++;
    }

    private void incValue(int i) {
        testingValue += i;
    }

    private void addingAnotherEventListener() {
        MyEventObject incValueEO = new MyEventObject(this::incValueByOne);
        forNextTestEvent.addListener(incValueEO);
    }

}
