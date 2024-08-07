package cz.cvut.fit.niadp.mvcgame.model;

import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@SuiteDisplayName("GameModel suite")
@Suite.SuiteClasses({GameModelBasicTest.class, GameModelMockedTest.class, GameModelMissilesTest.class})
public class GameModelTestSuit { }
