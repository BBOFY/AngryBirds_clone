package cz.cvut.fit.niadp.mvcgame;

import cz.cvut.fit.niadp.mvcgame.eventSystem.EventSystemTests;
import cz.cvut.fit.niadp.mvcgame.memento.CareTakerTests;
import cz.cvut.fit.niadp.mvcgame.model.GameModelTestSuit;
import cz.cvut.fit.niadp.mvcgame.singleton.SingletonTests;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@SuiteDisplayName("All tests suite")
@Suite.SuiteClasses({GameModelTestSuit.class, SingletonTests.class, CareTakerTests.class, EventSystemTests.class})
public class AllTestsSuite {
}
