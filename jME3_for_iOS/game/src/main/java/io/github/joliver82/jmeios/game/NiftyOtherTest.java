package io.github.joliver82.jmeios.game;

import com.jme3.app.SimpleApplication;
import com.jme3.collision.CollisionResults;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.TouchInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.input.controls.TouchListener;
import com.jme3.input.controls.TouchTrigger;
import com.jme3.input.event.TouchEvent;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.ToneMapFilter;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.shadow.DirectionalLightShadowFilter;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.shadow.EdgeFilteringMode;
import com.jme3.texture.Image;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.util.SkyFactory;
import com.jme3.util.SkyFactory.EnvMapType;
import com.jme3.util.TangentBinormalGenerator;

import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;

/**
 * The JMonkeyEngine game entry, you should only do initializations for your game here, game logic is handled by
 * Custom states {@link com.jme3.app.state.BaseAppState}, Custom controls {@link com.jme3.scene.control.AbstractControl}
 * and your custom entities implementations of the previous.
 *
 */
public class NiftyOtherTest extends SimpleApplication implements TouchListener, ActionListener, ScreenController {

    Nifty nifty;
    FilterPostProcessor fpp;

    public NiftyOtherTest() {
    }

    @Override
    public void onTouch(String name, TouchEvent event, float tpf) {
        System.out.println("Touched at: " + event.getX() + " - " + event.getY());

        Vector3f orig = cam.getWorldCoordinates(new Vector2f(event.getX(), event.getY()), 0f);
        Vector3f dir = cam.getWorldCoordinates(new Vector2f(event.getX(), event.getY()), 1f);
        dir.subtractLocal(orig).normalizeLocal();

        Ray ray = new Ray(orig, dir);
        CollisionResults results = new CollisionResults();
        rootNode.collideWith(ray, results);

        if(results.size()>0)
        {
            System.out.println("Touch collided with: " + results.getClosestCollision().getGeometry().getName());
        }
    }

    @Override
    public void onAction(String name, boolean value, float tpf) {
        System.out.println(inputManager.getCursorPosition().x + " - " + inputManager.getCursorPosition().y);
    }

    public void setupTouch() {
        getInputManager().addMapping("touch", new TouchTrigger(TouchInput.ALL));
        getInputManager().addListener(this, new String[]{"touch"});
    }

    public void setupMouse() {
        getInputManager().addMapping("click", new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        getInputManager().addListener(this, new String[]{"click"});
    }

    @Override
    public void bind(Nifty nifty, Screen screen) {
        System.out.println("bind( " + screen.getScreenId() + ")");
    }

    @Override
    public void onStartScreen() {
        System.out.println("onStartScreen");
    }

    @Override
    public void onEndScreen() {
        System.out.println("onEndScreen");
    }

    public void quit(){
        nifty.gotoScreen("end");
    }

    ToneMapFilter tmf;
    MyScreenshotAppState scrAS;
    int frame=0;

    @Override
    public void simpleInitApp() {

        flyCam.setDragToRotate(true);

        reshape(this.getContext().getSettings().getWidth(), this.getContext().getSettings().getWidth());

        setupTouch();
        setupMouse();


        // Force memory
        try
        {
            Class c = Class.forName("java.nio.Bits");
            Field maxMemory = c.getDeclaredField("maxMemory");
            maxMemory.setAccessible(true);
            Field reservedMemory = c.getDeclaredField("reservedMemory");
            reservedMemory.setAccessible(true);
            synchronized (c) {
                maxMemory.setLong(null, 1610612736L);
                Long maxMemoryValue = (Long)maxMemory.get(null);
                Long reservedMemoryValueLong=0L;
                if(reservedMemory.get(null) instanceof java.util.concurrent.atomic.AtomicLong)
                {
                    java.util.concurrent.atomic.AtomicLong reservedMemoryValue = (java.util.concurrent.atomic.AtomicLong)reservedMemory.get(null);
                    reservedMemoryValueLong=reservedMemoryValue.longValue();
                }
                else
                {
                    reservedMemoryValueLong=(Long)reservedMemory.get(null);
                }

                System.out.println("Memory: " + reservedMemoryValueLong + " / " + maxMemoryValue );
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }

        //Nifty
        NiftyJmeDisplay niftyDisplay = new NiftyJmeDisplay(assetManager,
                inputManager,
                audioRenderer,
                guiViewPort);
        nifty = niftyDisplay.getNifty();

        nifty.registerScreenController(this);
        nifty.fromXml("Interface/test.xml", "start");

        // attach the nifty display to the gui view port as a processor
        guiViewPort.addProcessor(niftyDisplay);


        //Scene
        Box b = new Box(1, 1, 1);
        Geometry geom = new Geometry("Box", b);

        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        rootNode.attachChild(geom);

        Spatial sky = SkyFactory.createSky(assetManager, "Textures/Sky/Path.hdr", SkyFactory.EnvMapType.EquirectMap);
        //Spatial sky = SkyFactory.createSky(assetManager, "Textures/Sky/Bright/BrightSky.dds", SkyFactory.EnvMapType.CubeMap);
        rootNode.attachChild(sky);

        fpp=new FilterPostProcessor(assetManager);
        fpp.setFrameBufferFormat(Image.Format.RGBA8);
        //fpp.addFilter(new BloomFilter());
        tmf=new ToneMapFilter(Vector3f.UNIT_XYZ.mult(4.0f));
        fpp.addFilter(tmf);
        fpp.setNumSamples(1);
        viewPort.addProcessor(fpp);

    }

    @Override
    public void simpleUpdate(float tpf) {
        if(frame==2)
        {
            //System.exit(0);
        }
        if(frame==10)
        {
            // Commented, breaks the app on iOS, TODO: should check
            //System.out.println("Taking screenshot");
            //scrAS.takeScreenshot();
        }
        ++frame;
        testNetwork();
    }

    public void testNetwork()
    {
        //Test URL
        try {
            URL url = new URL("https://www.asdf.com");
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("HEAD");
            long urlSize=(long)con.getContentLength();
            con.disconnect();
            System.out.println("File size: "+urlSize);

        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            System.out.println("File exception: " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }

    }

}
