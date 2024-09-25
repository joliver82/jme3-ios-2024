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
public class InstancingTest extends SimpleApplication implements TouchListener, ActionListener, ScreenController {

    Nifty nifty;
    FilterPostProcessor fpp;

    public InstancingTest() {
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

    private Mesh mesh1;
    private Mesh mesh2;
    private final Material[] materials = new Material[6];
    private Node instancedNode;
    private float time = 0;
    private boolean INSTANCING = true;


    private Geometry createInstance(float x, float z) {
        Mesh mesh;
        if (FastMath.nextRandomInt(0, 1) == 1) mesh = mesh2;
        else mesh = mesh1;
        Geometry geometry = new Geometry("randomGeom", mesh);
        geometry.setMaterial(materials[FastMath.nextRandomInt(0, materials.length - 1)]);
        geometry.setLocalTranslation(x, 0, z);
        return geometry;
    }

    @Override
    public void simpleInitApp() {
        mesh1 = new Sphere(13, 13, 0.4f, true, false);
        mesh2 = new Box(0.4f, 0.4f, 0.4f);

        materials[0] = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        materials[0].setBoolean("UseInstancing", INSTANCING);
        materials[0].setColor("Color", ColorRGBA.Red);

        materials[1] = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        materials[1].setBoolean("UseInstancing", INSTANCING);
        materials[1].setColor("Color", ColorRGBA.Green);

        materials[2] = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        materials[2].setBoolean("UseInstancing", INSTANCING);
        materials[2].setColor("Color", ColorRGBA.Blue);

        materials[3] = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        materials[3].setBoolean("UseInstancing", INSTANCING);
        materials[3].setColor("Color", ColorRGBA.Cyan);

        materials[4] = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        materials[4].setBoolean("UseInstancing", INSTANCING);
        materials[4].setColor("Color", ColorRGBA.Magenta);

        materials[5] = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        materials[5].setBoolean("UseInstancing", INSTANCING);
        materials[5].setColor("Color", ColorRGBA.Yellow);

        instancedNode = new InstancedNode("instanced_node");

        rootNode.attachChild(instancedNode);

        int extent = 30;

        for (int y = -extent; y < extent; y++) {
            for (int x = -extent; x < extent; x++) {
                Geometry instance = createInstance(x, y);

                float height = (smoothstep(0, 1, FastMath.nextRandomFloat()) * 2.5f) - 1.25f;
                instance.setUserData("height", height);
                instance.setUserData("dir", 1f);

                instancedNode.attachChild(instance);
            }
        }

        if (INSTANCING) {
            ((InstancedNode)instancedNode).instance();
        }

        //instancedNode = (InstancedNode) instancedNode.clone();
        //instancedNode.move(0, 5, 0);
        //rootNode.attachChild(instancedNode);

        cam.setLocation(new Vector3f(38.373516f, 6.689055f, 38.482082f));
        cam.setRotation(new Quaternion(-0.04004206f, 0.918326f, -0.096310444f, -0.38183528f));
        flyCam.setMoveSpeed(15);
        flyCam.setEnabled(false);
    }

    private float smoothstep(float edge0, float edge1, float x) {
        // Scale, bias and saturate x to 0..1 range
        x = FastMath.clamp((x - edge0) / (edge1 - edge0), 0.0f, 1.0f);
        // Evaluate polynomial
        return x * x * (3 - 2 * x);
    }


    @Override
    public void simpleUpdate(float tpf) {
        time += tpf;

        if (time > 1f) {
            time = 0f;

            for (Spatial instance : instancedNode.getChildren()) {
                if (!(instance instanceof InstancedGeometry)) {
                    Geometry geom = (Geometry) instance;
                    geom.setMaterial(materials[FastMath.nextRandomInt(0, materials.length - 1)]);

                    Mesh mesh;
                    if (FastMath.nextRandomInt(0, 1) == 1) mesh = mesh2;
                    else mesh = mesh1;
                    geom.setMesh(mesh);
                }
            }
        }

        for (Spatial child : instancedNode.getChildren()) {
            if (!(child instanceof InstancedGeometry)) {
                float val = ((Float)child.getUserData("height")).floatValue();
                float dir = ((Float)child.getUserData("dir")).floatValue();

                val += (dir + ((FastMath.nextRandomFloat() * 0.5f) - 0.25f)) * tpf;

                if (val > 1f) {
                    val = 1f;
                    dir = -dir;
                } else if (val < 0f) {
                    val = 0f;
                    dir = -dir;
                }

                Vector3f translation = child.getLocalTranslation();
                translation.y = (smoothstep(0, 1, val) * 2.5f) - 1.25f;

                child.setUserData("height", val);
                child.setUserData("dir", dir);

                child.setLocalTranslation(translation);
            }
        }
    }
}
