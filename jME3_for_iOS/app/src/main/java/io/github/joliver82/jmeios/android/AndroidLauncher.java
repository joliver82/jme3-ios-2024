package io.github.joliver82.jmeios.android;

import com.jme3.app.AndroidHarness;
import io.github.joliver82.jmeios.game.JmeForIos;


public class AndroidLauncher extends AndroidHarness {

    public AndroidLauncher() {
        appClass = JmeForIos.class.getCanonicalName();
    }
}
