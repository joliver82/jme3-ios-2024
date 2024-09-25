package io.github.joliver82.jmeios.ios;

import org.moe.natj.general.Pointer;
import org.moe.natj.general.ann.RegisterOnStartup;
import org.moe.natj.objc.ann.Selector;

import apple.NSObject;
import apple.foundation.NSDictionary;
import apple.corefoundation.struct.CGRect;
import apple.uikit.UIApplication;
import apple.uikit.UIColor;
import apple.uikit.UIImage;
import apple.uikit.UINavigationController;
import apple.uikit.UIScreen;
import apple.uikit.UIViewController;
import apple.uikit.UIWindow;
import apple.uikit.c.UIKit;
import apple.uikit.enums.UIBarStyle;
import apple.uikit.protocol.UIApplicationDelegate;

@RegisterOnStartup
public class DummyMain extends NSObject implements UIApplicationDelegate
{

    public static void main(String[] args) {
        Runtime r=Runtime.getRuntime();
        System.out.println("MaxMemory:" + r.maxMemory());
        System.out.println("TotalMemory:" + r.totalMemory());
        UIKit.UIApplicationMain(0, null, null, DummyMain.class.getName());
    }

    @Selector("alloc")
    public static native DummyMain alloc();

    protected DummyMain(Pointer peer) {
        super(peer);
    }

    private UIWindow window;

    @Override
    public boolean applicationDidFinishLaunchingWithOptions(UIApplication application, NSDictionary launchOptions) {
        UIViewController vc = MoeIosJmeAppHarness.alloc().init();
        UINavigationController navigationController = UINavigationController.alloc().init();

        UIColor moeBlue = UIColor.alloc().initWithRedGreenBlueAlpha(0.0, 113/255.f, 197/255.f, 1.0);

        navigationController.initWithRootViewController(vc);

        UIScreen screen = UIScreen.mainScreen();

        CGRect bounds = screen.bounds();
        window = UIWindow.alloc().initWithFrame(bounds);

        window.setRootViewController(navigationController);

        navigationController.navigationBar().setBarStyle(UIBarStyle.Black);
        navigationController.navigationBar().setBarTintColor(moeBlue);
        navigationController.navigationBar().setShadowImage(UIImage.alloc().init());
        navigationController.navigationBar().setTranslucent(false);
        navigationController.navigationBar().setTintColor(UIColor.whiteColor());

        window.makeKeyAndVisible();

        System.out.println("DummyMain applicationDidFinishLaunchingWithOptions");

        return true;
    }

    @Override
    public void setWindow(UIWindow value) {
        window = value;
    }

    @Override
    public UIWindow window() {
        return window;
    }
}
