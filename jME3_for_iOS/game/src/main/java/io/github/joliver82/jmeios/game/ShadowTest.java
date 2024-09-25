package io.github.joliver82.jmeios.game;

import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.app.state.AppState;
import com.jme3.system.JmeVersion;
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
public class ShadowTest extends SimpleApplication implements TouchListener, ActionListener, ScreenController {

    Nifty nifty;
    FilterPostProcessor fpp;

    public ShadowTest() {
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

    public static final int SHADOWMAP_SIZE = 1024;
    private Spatial[] obj;
    private Material[] mat;
    private DirectionalLightShadowRenderer dlsr;
    private DirectionalLightShadowFilter dlsf;
    private Geometry ground;
    private Material matGroundU;
    private Material matGroundL;
    private AmbientLight al;
    private float frustumSize = 100;

    public void loadScene() {
        obj = new Spatial[2];
        // Setup first view


        mat = new Material[2];
        mat[0] = assetManager.loadMaterial("Common/Materials/RedColor.j3m");
        mat[1] = assetManager.loadMaterial("Textures/Terrain/Pond/Pond.j3m");
        mat[1].setBoolean("UseMaterialColors", true);
        mat[1].setColor("Ambient", ColorRGBA.White);
        mat[1].setColor("Diffuse", ColorRGBA.White.clone());


        obj[0] = new Geometry("sphere", new Sphere(30, 30, 2));
        obj[0].setShadowMode(ShadowMode.CastAndReceive);
        obj[1] = new Geometry("cube", new Box(1.0f, 1.0f, 1.0f));
        obj[1].setShadowMode(ShadowMode.CastAndReceive);
        TangentBinormalGenerator.generate(obj[1]);
        TangentBinormalGenerator.generate(obj[0]);

        Spatial t = obj[0].clone(false);
        t.setLocalScale(10f);
        t.setMaterial(mat[1]);
        rootNode.attachChild(t);
        t.setLocalTranslation(0, 25, 0);

        for (int i = 0; i < 60; i++) {
            t = obj[FastMath.nextRandomInt(0, obj.length - 1)].clone(false);
            t.setLocalScale(FastMath.nextRandomFloat() * 10f);
            t.setMaterial(mat[FastMath.nextRandomInt(0, mat.length - 1)]);
            rootNode.attachChild(t);
            t.setLocalTranslation(FastMath.nextRandomFloat() * 200f, FastMath.nextRandomFloat() * 30f + 20, 30f * (i + 2f));
        }

        Box b = new Box(1000, 2, 1000);
        b.scaleTextureCoordinates(new Vector2f(10, 10));
        ground = new Geometry("soil", b);
        ground.setLocalTranslation(0, 10, 550);
        matGroundU = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        matGroundU.setColor("Color", ColorRGBA.Green);


        matGroundL = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        Texture grass = assetManager.loadTexture("Textures/Terrain/splat/grass.jpg");
        grass.setWrap(WrapMode.Repeat);
        matGroundL.setTexture("DiffuseMap", grass);

        ground.setMaterial(matGroundL);

        ground.setShadowMode(ShadowMode.CastAndReceive);
        rootNode.attachChild(ground);

        l = new DirectionalLight();
        //l.setDirection(new Vector3f(0.5973172f, -0.16583486f, 0.7846725f).normalizeLocal());
        l.setDirection(new Vector3f(-1, -1, -1));
        rootNode.addLight(l);


        al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(0.02f));
        rootNode.addLight(al);

        Spatial sky = SkyFactory.createSky(assetManager,
                "Scenes/Beach/FullskiesSunset0068.dds", EnvMapType.CubeMap);
        sky.setLocalScale(350);

        rootNode.attachChild(sky);
    }
    DirectionalLight l;

    @Override
    public void simpleInitApp() {
        // put the camera in a bad position
//        cam.setLocation(new Vector3f(65.25412f, 44.38738f, 9.087874f));
//        cam.setRotation(new Quaternion(0.078139365f, 0.050241485f, -0.003942559f, 0.9956679f));

        cam.setLocation(new Vector3f(3.3720117f, 42.838284f, -83.43792f));
        cam.setRotation(new Quaternion(0.13833192f, -0.08969371f, 0.012581267f, 0.9862358f));

        flyCam.setMoveSpeed(100);

        loadScene();

        dlsr = new DirectionalLightShadowRenderer(assetManager, SHADOWMAP_SIZE, 3);
        dlsr.setLight(l);
        dlsr.setLambda(0.55f);
        dlsr.setShadowIntensity(0.8f);
        dlsr.setEdgeFilteringMode(EdgeFilteringMode.Nearest);
        dlsr.displayDebug();
        viewPort.addProcessor(dlsr);

        dlsf = new DirectionalLightShadowFilter(assetManager, SHADOWMAP_SIZE, 3);
        dlsf.setLight(l);
        dlsf.setLambda(0.55f);
        dlsf.setShadowIntensity(0.8f);
        dlsf.setEdgeFilteringMode(EdgeFilteringMode.Nearest);
        dlsf.setEnabled(false);

        FilterPostProcessor fpp = new FilterPostProcessor(assetManager);
        fpp.addFilter(dlsf);

        viewPort.addProcessor(fpp);

        initInputs();

        System.out.println("TEST: Initialized!");
    }

    private void initInputs() {

        inputManager.addMapping("ThicknessUp", new KeyTrigger(KeyInput.KEY_Y));
        inputManager.addMapping("ThicknessDown", new KeyTrigger(KeyInput.KEY_H));
        inputManager.addMapping("lambdaUp", new KeyTrigger(KeyInput.KEY_U));
        inputManager.addMapping("lambdaDown", new KeyTrigger(KeyInput.KEY_J));
        inputManager.addMapping("switchGroundMat", new KeyTrigger(KeyInput.KEY_M));
        inputManager.addMapping("debug", new KeyTrigger(KeyInput.KEY_X));
        inputManager.addMapping("stabilize", new KeyTrigger(KeyInput.KEY_B));
        inputManager.addMapping("distance", new KeyTrigger(KeyInput.KEY_N));


        inputManager.addMapping("up", new KeyTrigger(KeyInput.KEY_NUMPAD8));
        inputManager.addMapping("down", new KeyTrigger(KeyInput.KEY_NUMPAD2));
        inputManager.addMapping("right", new KeyTrigger(KeyInput.KEY_NUMPAD6));
        inputManager.addMapping("left", new KeyTrigger(KeyInput.KEY_NUMPAD4));
        inputManager.addMapping("fwd", new KeyTrigger(KeyInput.KEY_PGUP));
        inputManager.addMapping("back", new KeyTrigger(KeyInput.KEY_PGDN));
        inputManager.addMapping("pp", new KeyTrigger(KeyInput.KEY_P));
        inputManager.addMapping("backShadows", new KeyTrigger(KeyInput.KEY_K));


        inputManager.addListener(this, "lambdaUp", "lambdaDown", "ThicknessUp", "ThicknessDown",
                "switchGroundMat", "debug", "up", "down", "right", "left", "fwd", "back", "pp", "stabilize", "distance", "ShadowUp", "ShadowDown", "backShadows");

        inputManager.addListener(this, "Size+", "Size-");
        inputManager.addMapping("Size+", new KeyTrigger(KeyInput.KEY_W));
        inputManager.addMapping("Size-", new KeyTrigger(KeyInput.KEY_S));

    }

    public void onAction(String name, boolean keyPressed, float tpf) {


        if (name.equals("pp") && keyPressed) {
            if (cam.isParallelProjection()) {
                cam.setFrustumPerspective(45, (float) cam.getWidth() / cam.getHeight(), 1, 1000);
            } else {
                cam.setParallelProjection(true);
                float aspect = (float) cam.getWidth() / cam.getHeight();
                cam.setFrustum(-1000, 1000, -aspect * frustumSize, aspect * frustumSize, frustumSize, -frustumSize);

            }
        }

        if (name.equals("lambdaUp") && keyPressed) {
            dlsr.setLambda(dlsr.getLambda() + 0.01f);
            dlsf.setLambda(dlsr.getLambda() + 0.01f);
            System.out.println("Lambda : " + dlsr.getLambda());
        } else if (name.equals("lambdaDown") && keyPressed) {
            dlsr.setLambda(dlsr.getLambda() - 0.01f);
            dlsf.setLambda(dlsr.getLambda() - 0.01f);
            System.out.println("Lambda : " + dlsr.getLambda());
        }
        if ((name.equals("ShadowUp") || name.equals("ShadowDown")) && keyPressed) {
            al.setColor(ColorRGBA.White.mult((1 - dlsr.getShadowIntensity()) * 0.2f));
        }

        if (name.equals("debug") && keyPressed) {
            dlsr.displayFrustum();
        }

        if (name.equals("backShadows") && keyPressed) {
            dlsr.setRenderBackFacesShadows(!dlsr.isRenderBackFacesShadows());
            dlsf.setRenderBackFacesShadows(!dlsf.isRenderBackFacesShadows());
        }

        if (name.equals("stabilize") && keyPressed) {
            dlsr.setEnabledStabilization(!dlsr.isEnabledStabilization());
            dlsf.setEnabledStabilization(!dlsf.isEnabledStabilization());
        }
        if (name.equals("distance") && keyPressed) {
            if (dlsr.getShadowZExtend() > 0) {
                dlsr.setShadowZExtend(0);
                dlsr.setShadowZFadeLength(0);
                dlsf.setShadowZExtend(0);
                dlsf.setShadowZFadeLength(0);

            } else {
                dlsr.setShadowZExtend(500);
                dlsr.setShadowZFadeLength(50);
                dlsf.setShadowZExtend(500);
                dlsf.setShadowZFadeLength(50);
            }

        }

        if (name.equals("switchGroundMat") && keyPressed) {
            if (ground.getMaterial() == matGroundL) {
                ground.setMaterial(matGroundU);
            } else {
                ground.setMaterial(matGroundL);
            }
        }

        if (name.equals("up")) {
            up = keyPressed;
        }
        if (name.equals("down")) {
            down = keyPressed;
        }
        if (name.equals("right")) {
            right = keyPressed;
        }
        if (name.equals("left")) {
            left = keyPressed;
        }
        if (name.equals("fwd")) {
            fwd = keyPressed;
        }
        if (name.equals("back")) {
            back = keyPressed;
        }

    }
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    boolean fwd = false;
    boolean back = false;
    float time = 0;
    float s = 1f;

    @Override
    public void simpleUpdate(float tpf) {

        if (up) {
            Vector3f v = l.getDirection();
            v.y += tpf / s;
            setDir(v);
        }
        if (down) {
            Vector3f v = l.getDirection();
            v.y -= tpf / s;
            setDir(v);
        }
        if (right) {
            Vector3f v = l.getDirection();
            v.x += tpf / s;
            setDir(v);
        }
        if (left) {
            Vector3f v = l.getDirection();
            v.x -= tpf / s;
            setDir(v);
        }
        if (fwd) {
            Vector3f v = l.getDirection();
            v.z += tpf / s;
            setDir(v);
        }
        if (back) {
            Vector3f v = l.getDirection();
            v.z -= tpf / s;
            setDir(v);
        }

        System.out.println("TEST: update");
    }

    private void setDir(Vector3f v) {
        l.setDirection(v);
    }
}
