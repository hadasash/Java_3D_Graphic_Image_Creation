//Adi and Hadasa
package unittests;
import org.junit.Test;
import elements.*;
import geometries.Sphere;
import geometries.Triangle;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene scene = new Scene("Test scene");

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVpSize(150, 150).setVpDistance(1000);

		scene.geometries.add( //
				new Sphere(new Point3D(0, 0, -50),50) //
				.setEmission(new Color(java.awt.Color.BLUE)) //
				.setMaterial(new Material().set_kD(0.4).set_kS(0.3).set_nShininess(100).set_kT(0.3)),
				new Sphere(new Point3D(0, 0, -50),25) //
				.setEmission(new Color(java.awt.Color.RED)) //
				.setMaterial(new Material().set_kD(0.5).set_kS(0.5).set_nShininess(100)));
		scene.lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point3D(-100, -100, 500), new Vector(-1, -1, -2)) //
				.setKl(0.0004).setKq(0.0000006));

		Render render = new Render() //
				.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setCamera(camera) //
				.setRayTracerBase(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVpSize(2500, 2500).setVpDistance(10000); //

		scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		scene.geometries.add( //
				new Sphere(new Point3D(-950, -900, -1000),400) //
				.setEmission(new Color(0, 0, 100)) //
				.setMaterial(new Material().set_kD(0.25).set_kS(0.25).set_nShininess(20).set_kT(0.5)),
				new Sphere(new Point3D(-950, -900, -1000),200) //
				.setEmission(new Color(100, 20, 20)) //
				.setMaterial(new Material().set_kD(0.25).set_kS(0.25).set_nShininess(20)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(670, 670, 3000)) //
				.setEmission(new Color(20, 20, 20)) //
				.setMaterial(new Material().set_kR(1)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
						new Point3D(-1500, -1500, -2000)) //
				.setEmission(new Color(20, 20, 20)) //
				.setMaterial(new Material().set_kR(0.5)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setKl(0.00001).setKq(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracerBase(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVpSize(200, 200).setVpDistance(1000);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add( //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
				.setMaterial(new Material().set_kD(0.5).set_kS(0.5).set_nShininess(60)), //
				new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
				.setMaterial(new Material().set_kD(0.5).set_kS(0.5).set_nShininess(60)), //
				new Sphere(new Point3D(60, 50, -50),30) //
				.setEmission(new Color(java.awt.Color.BLUE)) //
				.setMaterial(new Material().set_kD(0.2).set_kS(0.2).set_nShininess(30).set_kT(0.6)));

		scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracerBase(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of a three spheres lighted by a spot light, the red one is a mirror and the smallest is transparency
	 *  
	 */
	@Test
	public void ourPicture() {
		Camera camera = new Camera(new Point3D(0,0,0), new Vector(0, 0, -1), new Vector(0, -1, 0)) //
				.setVpSize(300, 300).setVpDistance(400);

		scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.geometries.add( //
				new Sphere(new Point3D(0,50,-800),180) //the purple sphere
				.setEmission(new Color(100,0,120)) //
				.setMaterial(new Material().set_kD(0.6).set_kS(0.9).set_nShininess(1000).set_kT(0).set_kR(1)),
				new Sphere(new Point3D(-210,110,-900),300) //the lightblue sphere
				.setEmission(new Color(0,30,50)) //
				.setMaterial(new Material().set_kD(0).set_kS(0.2).set_nShininess(1000).set_kT(0.6).set_kR(0.2)),
				new Sphere(new Point3D(10, 30, -120),12) //the green sphere
				.setEmission(new Color(0,100,30)) //
				.setMaterial(new Material().set_kD(0.25).set_kS(0.25).set_nShininess(500).set_kT(0).set_kR(0.8)),
				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),//the triangle
				new Point3D(-1500, -1500, -2000))
				.setMaterial(new Material().set_kD(0.25).set_kS(0.9).set_nShininess(500).set_kT(0).set_kR(1)), 
		        new Sphere(new Point3D(110,140,-500),30) //the orange sphere
		        .setEmission(new Color(250,70,0)) //
        		.setMaterial(new Material().set_kD(0.6).set_kS(0.9).set_nShininess(1000).set_kT(0).set_kR(0.2)));


		scene.lights.add(new SpotLight(new Color(100,100,100), new Point3D(1000,-550,1000), new Vector(0,-1,0)) //
				.setKl(4E-5).setKq(2E-7));
		scene.lights.add(new PointLight(new Color(500, 500, 500), new Point3D(-50, -50, 50))//
				.setKl(0.00001).setKq(0.000001));
		
		ImageWriter imageWriter = new ImageWriter("ourPictureRefrectionRefraction", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracerBase(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}
}
