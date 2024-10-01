package io.github.joliver82.jmeios.ios;

import org.robovm.apple.cloudkit.CKShareMetadata;
import org.robovm.apple.coregraphics.CGRect;
import org.robovm.apple.foundation.NSArray;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.foundation.NSCoder;
import org.robovm.apple.foundation.NSData;
import org.robovm.apple.foundation.NSDictionary;
import org.robovm.apple.foundation.NSError;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSPropertyList;
import org.robovm.apple.foundation.NSSet;
import org.robovm.apple.foundation.NSURL;
import org.robovm.apple.foundation.NSUserActivity;
import org.robovm.apple.intents.INIntent;
import org.robovm.apple.intents.INIntentResponse;
import org.robovm.apple.uikit.UIApplication;
import org.robovm.apple.uikit.UIApplicationDelegate;
import org.robovm.apple.uikit.UIApplicationExtensionPointIdentifier;
import org.robovm.apple.uikit.UIApplicationLaunchOptions;
import org.robovm.apple.uikit.UIApplicationOpenURLOptions;
import org.robovm.apple.uikit.UIApplicationShortcutItem;
import org.robovm.apple.uikit.UIBackgroundFetchResult;
import org.robovm.apple.uikit.UIInterfaceOrientation;
import org.robovm.apple.uikit.UIInterfaceOrientationMask;
import org.robovm.apple.uikit.UILocalNotification;
import org.robovm.apple.uikit.UIRemoteNotification;
import org.robovm.apple.uikit.UIResponder;
import org.robovm.apple.uikit.UISceneConfiguration;
import org.robovm.apple.uikit.UISceneConnectionOptions;
import org.robovm.apple.uikit.UISceneSession;
import org.robovm.apple.uikit.UIScreen;
import org.robovm.apple.uikit.UIUserNotificationSettings;
import org.robovm.apple.uikit.UIViewController;
import org.robovm.apple.uikit.UIWindow;
import org.robovm.objc.annotation.Method;
import org.robovm.objc.block.VoidBlock1;
import org.robovm.objc.block.VoidBooleanBlock;

import java.util.List;

public class DummyMain extends NSObject implements UIApplicationDelegate
{

    public static void main(String[] args) {
        Runtime r=Runtime.getRuntime();
        System.out.println("MaxMemory:" + r.maxMemory());
        System.out.println("TotalMemory:" + r.totalMemory());
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(args, null, DummyMain.class);
        pool.close();
    }

    private UIWindow window;

    @Override
    @Method(selector = "application:didFinishLaunchingWithOptions:")
    public boolean didFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions) {
        UIScreen screen = UIScreen.getMainScreen();
        CGRect bounds = screen.getBounds();
        window = new UIWindow(bounds);
        UIViewController vc = new MoeIosJmeAppHarness();
        window.setRootViewController(vc);
        window.makeKeyAndVisible();
        System.out.println("DummyMain applicationDidFinishLaunchingWithOptions");
        return true;
    }

    @Override
    public void didBecomeActive(UIApplication application)
    {

    }

    @Override
    public void willResignActive(UIApplication application)
    {

    }

    @Override
    public boolean handleOpenURL(UIApplication application, NSURL url)
    {
        return false;
    }

    @Override
    public boolean openURL(UIApplication application, NSURL url, String sourceApplication, NSPropertyList annotation)
    {
        return false;
    }

    @Override
    public boolean openURL(UIApplication app, NSURL url, UIApplicationOpenURLOptions options)
    {
        return false;
    }

    @Override
    public void didReceiveMemoryWarning(UIApplication application)
    {

    }

    @Override
    public void willTerminate(UIApplication application)
    {

    }

    @Override
    public void significantTimeChange(UIApplication application)
    {

    }

    @Override
    public void willChangeStatusBarOrientation(UIApplication application, UIInterfaceOrientation newStatusBarOrientation, double duration)
    {

    }

    @Override
    public void didChangStatusBarOrientation(UIApplication application, UIInterfaceOrientation oldStatusBarOrientation)
    {

    }

    @Override
    public void willChangeStatusBarFrame(UIApplication application, CGRect newStatusBarFrame)
    {

    }

    @Override
    public void didChangStatusBarFrame(UIApplication application, CGRect oldStatusBarFrame)
    {

    }

    @Override
    public void didRegisterUserNotificationSettings(UIApplication application, UIUserNotificationSettings notificationSettings)
    {

    }

    @Override
    public void didRegisterForRemoteNotifications(UIApplication application, NSData deviceToken)
    {

    }

    @Override
    public void didFailToRegisterForRemoteNotifications(UIApplication application, NSError error)
    {

    }

    @Override
    public void didReceiveRemoteNotification(UIApplication application, UIRemoteNotification userInfo)
    {

    }

    @Override
    public void didReceiveLocalNotification(UIApplication application, UILocalNotification notification)
    {

    }

    @Override
    public void handleLocalNotificationAction(UIApplication application, String identifier, UILocalNotification notification, Runnable completionHandler)
    {

    }

    @Override
    public void handleRemoteNotificationAction(UIApplication application, String identifier, UIRemoteNotification userInfo, NSDictionary<?, ?> responseInfo, Runnable completionHandler)
    {

    }

    @Override
    public void handleRemoteNotificationAction(UIApplication application, String identifier, UIRemoteNotification userInfo, Runnable completionHandler)
    {

    }

    @Override
    public void handleLocalNotificationAction(UIApplication application, String identifier, UILocalNotification notification, NSDictionary<?, ?> responseInfo, Runnable completionHandler)
    {

    }

    @Override
    public void didReceiveRemoteNotification(UIApplication application, UIRemoteNotification userInfo, VoidBlock1<UIBackgroundFetchResult> completionHandler)
    {

    }

    @Override
    public void performFetch(UIApplication application, VoidBlock1<UIBackgroundFetchResult> completionHandler)
    {

    }

    @Override
    public void performAction(UIApplication application, UIApplicationShortcutItem shortcutItem, VoidBooleanBlock completionHandler)
    {

    }

    @Override
    public void handleEventsForBackgroundURLSession(UIApplication application, String identifier, Runnable completionHandler)
    {

    }

    @Override
    public void handleWatchKitExtensionRequest(UIApplication application, NSDictionary<?, ?> userInfo, VoidBlock1<NSDictionary<?, ?>> reply)
    {

    }

    @Override
    public void shouldRequestHealthAuthorization(UIApplication application)
    {

    }

    @Override
    public NSObject getHandlerForIntent(UIApplication application, INIntent intent)
    {
        return null;
    }

    @Override
    public void handleIntent(UIApplication application, INIntent intent, VoidBlock1<INIntentResponse> completionHandler)
    {

    }

    @Override
    public void didEnterBackground(UIApplication application)
    {

    }

    @Override
    public void willEnterForeground(UIApplication application)
    {

    }

    @Override
    public void protectedDataWillBecomeUnavailable(UIApplication application)
    {

    }

    @Override
    public void protectedDataDidBecomeAvailable(UIApplication application)
    {

    }

    @Override
    public UIInterfaceOrientationMask getSupportedInterfaceOrientations(UIApplication application, UIWindow window)
    {
        return null;
    }

    @Override
    public boolean shouldAllowExtensionPointIdentifier(UIApplication application, UIApplicationExtensionPointIdentifier extensionPointIdentifier)
    {
        return false;
    }

    @Override
    public UIViewController getViewController(UIApplication application, List<String> identifierComponents, NSCoder coder)
    {
        return window.getRootViewController();
    }

    @Override
    public boolean shouldSaveSecureApplicationState(UIApplication application, NSCoder coder)
    {
        return false;
    }

    @Override
    public boolean shouldRestoreSecureApplicationState(UIApplication application, NSCoder coder)
    {
        return false;
    }

    @Override
    public void willEncodeRestorableState(UIApplication application, NSCoder coder)
    {

    }

    @Override
    public void didDecodeRestorableState(UIApplication application, NSCoder coder)
    {

    }

    @Override
    public boolean shouldSaveApplicationState(UIApplication application, NSCoder coder)
    {
        return false;
    }

    @Override
    public boolean shouldRestoreApplicationState(UIApplication application, NSCoder coder)
    {
        return false;
    }

    @Override
    public boolean willContinueUserActivity(UIApplication application, String userActivityType)
    {
        return false;
    }

    @Override
    public boolean continueUserActivity(UIApplication application, NSUserActivity userActivity, VoidBlock1<NSArray<UIResponder>> restorationHandler)
    {
        return false;
    }

    @Override
    public void didFailToContinueUserActivity(UIApplication application, String userActivityType, NSError error)
    {

    }

    @Override
    public void didUpdateUserActivity(UIApplication application, NSUserActivity userActivity)
    {

    }

    @Override
    public void didAcceptCloudKitShare(UIApplication application, CKShareMetadata cloudKitShareMetadata)
    {

    }

    @Override
    public UISceneConfiguration getConfigurationForConnectingSceneSession(UIApplication application, UISceneSession connectingSceneSession, UISceneConnectionOptions options)
    {
        return null;
    }

    @Override
    public void didDiscardSceneSessions(UIApplication application, NSSet<UISceneSession> sceneSessions)
    {

    }

    @Override
    public boolean applicationShouldAutomaticallyLocalizeKeyCommands(UIApplication application)
    {
        return false;
    }


    @Override
    public void setWindow(UIWindow value) {
        window = value;
    }

    @Override
    public void didFinishLaunching(UIApplication application)
    {
        didFinishLaunching(application, null);
    }

    @Override
    public boolean willFinishLaunching(UIApplication application, UIApplicationLaunchOptions launchOptions)
    {
        return false;
    }

    @Override
    public UIWindow getWindow() {
        return window;
    }
}
