package io.github.joliver82.jmeios.game;

import com.jme3.app.SimpleApplication;
import com.jme3.app.state.ScreenshotAppState;
import com.jme3.audio.AudioData.DataType;
import com.jme3.audio.AudioNode;
import com.jme3.audio.LowPassFilter;
import com.jme3.bounding.BoundingSphere;
import com.jme3.collision.CollisionResults;
import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.environment.EnvironmentCamera;
import com.jme3.environment.LightProbeFactory;
import com.jme3.environment.generation.JobProgressAdapter;
import com.jme3.environment.util.EnvMapUtils;
import com.jme3.environment.util.LightsDebugState;
import com.jme3.font.BitmapText;
import com.jme3.input.ChaseCamera;
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
import com.jme3.light.Light;
import com.jme3.light.LightProbe;
import com.jme3.material.Material;
import com.jme3.material.RenderState.BlendMode;
import com.jme3.math.ColorRGBA;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Ray;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.niftygui.NiftyJmeDisplay;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.BloomFilter;
import com.jme3.post.filters.DepthOfFieldFilter;
import com.jme3.post.filters.FXAAFilter;
import com.jme3.post.filters.LightScatteringFilter;
import com.jme3.post.filters.ToneMapFilter;
import com.jme3.renderer.Camera;
import com.jme3.renderer.Caps;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.queue.RenderQueue.Bucket;
import com.jme3.renderer.queue.RenderQueue.ShadowMode;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.instancing.InstancedGeometry;
import com.jme3.scene.instancing.InstancedNode;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.shadow.DirectionalLightShadowFilter;
import com.jme3.shadow.DirectionalLightShadowRenderer;
import com.jme3.shadow.EdgeFilteringMode;
import com.jme3.system.AppSettings;
import com.jme3.terrain.geomipmap.TerrainQuad;
import com.jme3.terrain.heightmap.AbstractHeightMap;
import com.jme3.terrain.heightmap.ImageBasedHeightMap;
import com.jme3.texture.Image;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import com.jme3.texture.Texture2D;
import com.jme3.texture.plugins.ktx.KTXLoader;
import com.jme3.util.MaterialDebugAppState;
import com.jme3.util.SkyFactory;
import com.jme3.util.SkyFactory.EnvMapType;
import com.jme3.util.TangentBinormalGenerator;
import com.jme3.util.mikktspace.MikktspaceTangentGenerator;
import com.jme3.water.WaterFilter;
import de.lessvoid.nifty.Nifty;
import de.lessvoid.nifty.screen.Screen;
import de.lessvoid.nifty.screen.ScreenController;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The JMonkeyEngine game entry, you should only do initializations for your game here, game logic is handled by
 * Custom states {@link com.jme3.app.state.BaseAppState}, Custom controls {@link com.jme3.scene.control.AbstractControl}
 * and your custom entities implementations of the previous.
 *
 */
public class PBRTest extends SimpleApplication implements TouchListener, ActionListener, ScreenController {

    Nifty nifty;
    FilterPostProcessor fpp;

    public PBRTest() {
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

    private Node tex;

    private Geometry model;
    private DirectionalLight dl;
    private Node modelNode;
    private int frame = 0;
    private Material pbrMat;
    private float roughness = 0.2f;

    @Override
    public void simpleInitApp() {

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

        //Load test light probe
        Spatial lpSpat=assetManager.loadModel("lightprobe.j3o");
        for(Light l : lpSpat.getLocalLightList())
        {
            if(l instanceof LightProbe)
            {
                LightProbe lp =(LightProbe)l;
                ((BoundingSphere)lp.getBounds()).setRadius(100);
                lp.getArea().setRadius(100);
                rootNode.addLight(lp);
            }
        }

        assetManager.registerLoader(KTXLoader.class, "ktx");

        viewPort.setBackgroundColor(ColorRGBA.White);
        modelNode = (Node) new Node("modelNode");
        model = (Geometry) assetManager.loadModel("Models/Tank/tank.j3o");
        MikktspaceTangentGenerator.generate(model);
        modelNode.attachChild(model);

        dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-1, -1, -1).normalizeLocal());
        rootNode.addLight(dl);
        dl.setColor(ColorRGBA.White);
        rootNode.attachChild(modelNode);

        // Adding any FPP seems to break rendering
        fpp = new FilterPostProcessor(assetManager);
//        fpp.addFilter(new FXAAFilter());
        //fpp.addFilter(new ToneMapFilter(Vector3f.UNIT_XYZ.mult(4.0f)));
//        fpp.addFilter(new SSAOFilter(0.5f, 3, 0.2f, 0.2f));
        viewPort.addProcessor(fpp);

        //Spatial sky = SkyFactory.createSky(assetManager, "Textures/Sky/Sky_Cloudy.hdr", SkyFactory.EnvMapType.EquirectMap);
        Spatial sky = SkyFactory.createSky(assetManager, "Textures/Sky/Path.hdr", SkyFactory.EnvMapType.EquirectMap);
        //Spatial sky = SkyFactory.createSky(assetManager, "Textures/Sky/Bright/BrightSky.dds", SkyFactory.EnvMapType.CubeMap);
        //Spatial sky = SkyFactory.createSky(assetManager, "Textures/Sky/road.hdr", SkyFactory.EnvMapType.EquirectMap);
        rootNode.attachChild(sky);

        try
        {
            pbrMat = assetManager.loadMaterial("Models/Tank/tank.j3m");
            pbrMat.setFloat("Roughness", roughness);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        model.setMaterial(pbrMat);


        final EnvironmentCamera envCam = new EnvironmentCamera(256, new Vector3f(0, 3f, 0));
        stateManager.attach(envCam);

//        EnvironmentManager envManager = new EnvironmentManager();
//        stateManager.attach(envManager);

        //       envManager.setScene(rootNode);

        LightsDebugState debugState = new LightsDebugState();
        stateManager.attach(debugState);

        ChaseCamera chaser = new ChaseCamera(cam, modelNode, inputManager);
        chaser.setDragToRotate(true);
        chaser.setMinVerticalRotation(-FastMath.HALF_PI);
        chaser.setMaxDistance(1000);
        chaser.setSmoothMotion(true);
        chaser.setRotationSensitivity(10);
        chaser.setZoomSensitivity(5);
        flyCam.setEnabled(false);
        //flyCam.setMoveSpeed(100);

        inputManager.addListener(new ActionListener() {
            @Override
            public void onAction(String name, boolean isPressed, float tpf) {
                if (name.equals("debug") && isPressed) {
                    if (tex == null) {
                        return;
                    }
                    if (tex.getParent() == null) {
                        guiNode.attachChild(tex);
                    } else {
                        tex.removeFromParent();
                    }
                }

                if (name.equals("rup") && isPressed) {
                    roughness = FastMath.clamp(roughness + 0.1f, 0.0f, 1.0f);
                    pbrMat.setFloat("Roughness", roughness);
                }
                if (name.equals("rdown") && isPressed) {
                    roughness = FastMath.clamp(roughness - 0.1f, 0.0f, 1.0f);
                    pbrMat.setFloat("Roughness", roughness);
                }


                if (name.equals("up") && isPressed) {
                    model.move(0, tpf * 100f, 0);
                }

                if (name.equals("down") && isPressed) {
                    model.move(0, -tpf * 100f, 0);
                }
                if (name.equals("left") && isPressed) {
                    model.move(0, 0, tpf * 100f);
                }
                if (name.equals("right") && isPressed) {
                    model.move(0, 0, -tpf * 100f);
                }
                if (name.equals("light") && isPressed) {
                    dl.setDirection(cam.getDirection().normalize());
                }
            }
        }, "toggle", "light", "up", "down", "left", "right", "debug", "rup", "rdown");

        inputManager.addMapping("toggle", new KeyTrigger(KeyInput.KEY_RETURN));
        inputManager.addMapping("light", new KeyTrigger(KeyInput.KEY_F));
        inputManager.addMapping("up", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("down", new KeyTrigger(KeyInput.KEY_DOWN));
        inputManager.addMapping("left", new KeyTrigger(KeyInput.KEY_LEFT));
        inputManager.addMapping("right", new KeyTrigger(KeyInput.KEY_RIGHT));
        inputManager.addMapping("debug", new KeyTrigger(KeyInput.KEY_D));
        inputManager.addMapping("rup", new KeyTrigger(KeyInput.KEY_T));
        inputManager.addMapping("rdown", new KeyTrigger(KeyInput.KEY_G));

    }

    boolean done=false;

    @Override
    public void simpleUpdate(float tpf) {
        frame++;

        // Not creating light probe, just loading one
        if (frame == -1) {
            modelNode.removeFromParent();
            final LightProbe probe = LightProbeFactory.makeProbe(stateManager.getState(EnvironmentCamera.class), rootNode, new JobProgressAdapter<LightProbe>() {

                @Override
                public void done(LightProbe result) {
                    System.out.println("Done rendering env maps");
                    done=true;
                    try
                    {
                        //tex = EnvMapUtils.getCubeMapCrossDebugViewWithMipMaps(result.getPrefilteredEnvMap(), assetManager);
                    }
                    catch(Exception e)
                    {
                        System.out.println("Exception on setting env map " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            });
            ((BoundingSphere) probe.getBounds()).setRadius(100);
            rootNode.addLight(probe);
            //getStateManager().getState(EnvironmentManager.class).addEnvProbe(probe);

        }

        if (frame > 10 && modelNode.getParent() == null) {
            rootNode.attachChild(modelNode);
        }

        System.out.println("Frame: " + frame);
        if(done)
            System.out.println("Env done");
    }

    @Override
    public void update() {
        try
        {
            super.update();
        }
        catch(Exception e)
        {
            System.out.println("Exception on update: " + e);
            e.printStackTrace();
        }
    }
}
