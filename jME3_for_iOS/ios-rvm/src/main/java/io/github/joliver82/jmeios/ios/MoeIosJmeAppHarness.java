package io.github.joliver82.jmeios.ios;

import com.jme3.app.LegacyApplication;
import com.jme3.system.JmeSystem;
import com.jme3.system.ios.JmeIosSystem;
import com.jme3.input.ios.IosInputHandler;
import com.jme3.math.Vector2f;
import com.jme3.renderer.opengl.GLRenderer;
import com.jme3.system.JmeContext;
import com.jme3.system.AppSettings;

import org.robovm.apple.coregraphics.CGPoint;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.coregraphics.CGSize;
import org.robovm.apple.foundation.NSEnumerator;
import org.robovm.apple.foundation.NSNotification;
import org.robovm.apple.foundation.NSNotificationCenter;
import org.robovm.apple.foundation.NSSet;
import org.robovm.apple.glkit.GLKView;
import org.robovm.apple.glkit.GLKViewController;
import org.robovm.apple.glkit.GLKViewDelegate;
import org.robovm.apple.glkit.GLKViewDrawableDepthFormat;
import org.robovm.apple.glkit.GLKViewDrawableMultisample;
import org.robovm.apple.opengles.EAGLContext;
import org.robovm.apple.opengles.EAGLRenderingAPI;
import org.robovm.apple.uikit.UIEvent;
import org.robovm.apple.uikit.UIRectEdge;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UITouch;
import org.robovm.apple.uikit.UIViewControllerTransitionCoordinator;
import org.robovm.objc.Selector;
import org.robovm.objc.annotation.Method;
import org.robovm.rt.bro.annotation.ByVal;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoeIosJmeAppHarness extends GLKViewController implements GLKViewDelegate
{
    // Content from previous JmeAppHarness+IosHarness
    protected LegacyApplication app;
    private static final Logger logger = Logger.getLogger(MoeIosJmeAppHarness.class.getName());
    protected GLRenderer renderer;
    protected IosInputHandler input;
    protected boolean autoFlush = true;
    protected Vector2f resizePending = null;

    // Content based on ios-moe
    private EAGLContext context;
    private GLKView glkView;

    public MoeIosJmeAppHarness()
    {
        final NSNotificationCenter notificationCenter = NSNotificationCenter.getDefaultCenter();
        notificationCenter.addObserver(this, Selector.register("didRotate"), "UIDeviceOrientationDidChangeNotification", null);
    }

    // Methods from previous JmeAppHarness+IosHarness
    public void appPaused() {
        logger.log(Level.FINE, "JmeAppHarness appPaused");
    }

    public void appReactivated() {
        logger.log(Level.FINE, "JmeAppHarness appReactivated");
    }

    public void appClosed() {
        logger.log(Level.FINE, "JmeAppHarness appClosed");
        app.stop();
    }

    public void appUpdate() {
        logger.log(Level.FINE, "JmeAppHarness appUpdate");
        //app.update();
    }

    public void appDraw() {
        logger.log(Level.FINE, "JmeAppHarness appDraw");
        if (renderer == null) {
            JmeContext iosContext = app.getContext();
            renderer = (GLRenderer)iosContext.getRenderer();
            renderer.initialize();
            input = (IosInputHandler)iosContext.getTouchInput();
            input.initialize();
        } else {
            if(resizePending != null) {
                appReshape((int)resizePending.x, (int)resizePending.y);
                resizePending = null;
            }
            app.update();
            if (autoFlush) {
                renderer.postFrame();
            }
        }
    }

    public void appReshape(int width, int height) {
        logger.log(Level.FINE, "JmeAppHarness reshape");
        AppSettings settings = app.getContext().getSettings();
        settings.setResolution(width, height);
        if (renderer != null) {
            app.reshape(width, height);
            resizePending = null;
        } else {
            resizePending = new Vector2f(width, height);
        }

        if (input != null) {
            input.loadSettings(settings);
        }
    }

    public void injectTouchBegin(int pointerId, long time, float x, float y) {
        if (input != null) {
            logger.log(Level.FINE, "JmeAppHarness injectTouchBegin");
            input.injectTouchDown(pointerId, time, x, y);
        }
    }

    public void injectTouchMove(int pointerId, long time, float x, float y) {
        if (input != null) {
            logger.log(Level.FINE, "JmeAppHarness injectTouchMove");
            input.injectTouchMove(pointerId, time, x, y);
        }
    }

    public void injectTouchEnd(int pointerId, long time, float x, float y) {
        if (input != null) {
            logger.log(Level.FINE, "JmeAppHarness injectTouchEnd");
            input.injectTouchUp(pointerId, time, x, y);
        }
    }

    // Methods based on ios-moe
    @Override
    public void viewDidLoad() {
        super.viewDidLoad();

        setEdgesForExtendedLayout(UIRectEdge.None);

        try
        {
            context = new EAGLContext(EAGLRenderingAPI.OpenGLES3);
        }
        catch (Exception eGL3)
        {
            try
            {
                context = new EAGLContext(EAGLRenderingAPI.OpenGLES2);
            }
            catch (Exception eGL2)
            {
                System.out.println("Failed to create ES context");
                System.exit(-1);
            }
        }
        glkView = (GLKView) getView();
        glkView.setContext(context);
        glkView.setDrawableMultisample(GLKViewDrawableMultisample._4X);
        glkView.setDrawableDepthFormat(GLKViewDrawableDepthFormat._24);

        setPreferredFramesPerSecond(60); // TODO: set desired FPS
        setPaused(false);

        setupGL();

        // Initialization from previous JmeAppHarness+IosHarness
        JmeSystem.setSystemDelegate(new JmeIosSystem());
        // TODO: set desired test to run
        int testIdx=0;
        switch(testIdx)
        {
            case 1:
                app = new io.github.joliver82.jmeios.game.InstancingTest(); //OK with Allocator patch
                break;
            case 2:
                app = new io.github.joliver82.jmeios.game.NiftyOtherTest(); //OK
                break;
            case 3:
                app = new io.github.joliver82.jmeios.game.PBRTest(); //OK with memory increased (see main.cpp)
                break;
            case 4:
                app = new io.github.joliver82.jmeios.game.ShadowTest(); //OK
                break;
            case 5:
                app = new io.github.joliver82.jmeios.game.WaterTest(); //OK
                break;
            case 6:
                app = new io.github.joliver82.jmeios.game.PhysicsTest();
                break;
            case 0:
            default:
                app = new io.github.joliver82.jmeios.game.JmeForIos(); //OK
                break;
        }
        AppSettings settings = new AppSettings(true);
        this.app.setSettings(settings);
        app.start();
        app.gainFocus();

        //Force a didRotate (jme3 reshape) event after having initialized everything so it renders properly from the beginning
        didRotate((NSNotification) null);
    }

    @Override
    public void viewWillTransitionToSize(@ByVal CGSize size, UIViewControllerTransitionCoordinator coordinator)
    {
        super.viewWillTransitionToSize(size, coordinator);
        if (isPaused())
            glkView.setNeedsDisplay();
    }

    @Override
    public void viewDidDisappear(boolean animated) {
        super.viewDidDisappear(animated);
        tearDownGL();
        if (EAGLContext.getCurrentContext() != null && EAGLContext.getCurrentContext().equals(context)) {
            EAGLContext.setCurrentContext(null);
        }
        context = null;
    }

    @Override
    public void didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning();
        if (isViewLoaded() && (getView().getWindow() == null)) {
            setView(null);
            tearDownGL();
            if (EAGLContext.getCurrentContext().equals(context)) {
                EAGLContext.setCurrentContext(null);
            }
            context = null;
        }
        // Dispose of any resources that can be recreated.
    }

    private void setupGL() {
        EAGLContext.setCurrentContext(context);
        // Nothing to do here, there's no gl stuff created in this class
    }

    private void tearDownGL() {
        EAGLContext.setCurrentContext(context);
        // Nothing to do here, there's no gl stuff created in this class
    }

    @Override
    public void draw(GLKView view, CGRect rect) {
        // TODO: call jme draw here???
        appDraw();
    }

    @Method(selector = "update")
    public void update() {
        appUpdate();
        appUpdate();
    }

    @Override
    public void touchesBegan(NSSet touches, UIEvent event)
    {
        for (Object o : touches)
        {
            UITouch touch = (UITouch) o;
            CGPoint position = touch.getLocationInView(null);
            float scale = (float) glkView.getContentScaleFactor();
            injectTouchBegin(touch.hashCode(), (long)touch.getTimestamp(), (float) (position.getX() * scale), (float) (position.getY() * scale));
        }
    }

    @Override
    public void touchesCancelled(NSSet touches, UIEvent event)
    {
        // Not implemented in jME3
    }

    @Override
    public void touchesEnded(NSSet touches, UIEvent event)
    {
        for (Object o : touches)
        {
            UITouch touch = (UITouch) o;
            CGPoint position = touch.getLocationInView(null);
            float scale = (float) glkView.getContentScaleFactor();
            injectTouchEnd(touch.hashCode(), (long)touch.getTimestamp(), (float) (position.getX() * scale), (float) (position.getY() * scale));
        }
    }

    @Override
    public void touchesMoved(NSSet touches, UIEvent event)
    {
        for (Object o : touches)
        {
            UITouch touch = (UITouch) o;
            CGPoint position = touch.getLocationInView(null);
            float scale = (float) glkView.getContentScaleFactor();
            injectTouchMove(touch.hashCode(), (long) touch.getTimestamp(), (float) (position.getX() * scale), (float) (position.getY() * scale));
        }
    }

    // java code equivalent to old jmeAppDelegate.m
    @Method(selector = "didRotate")
    public void didRotate(NSNotification notification)
    {
        UIScreen screen = UIScreen.getMainScreen();
        CGRect originalFrame = screen.getBounds();
        if(glkView!=null)
        {
            CGRect frame = glkView.convertRectToView(originalFrame, null);
            float scale = (float) glkView.getContentScaleFactor();
            appReshape((int) (frame.getSize().getWidth() * scale), (int) (frame.getSize().getHeight() * scale));
        }
        System.out.println("App Rotated");
    }
}
