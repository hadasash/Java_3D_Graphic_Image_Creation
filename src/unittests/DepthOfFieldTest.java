package unittests;
import org.junit.Test;
import elements.*;
import geometries.Plane;
import geometries.Sphere;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * 
 * 
 * @author Adi and Hadasa
 */
public class DepthOfFieldTest 
{
	private Scene scene = new Scene("Test scene");

	/**
	 * Produce a picture of spheres with improve of depth of field
	 */
	@Test
	public void WithDepthOfField() {
		Camera camera =new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0))
				.setVpSize(600, 600).setVpDistance(1000)
				.setApHeight(1).setApWidth(1).setFocalPlaneDistance(1010);//set aperture

		scene.backGround=new Color(java.awt.Color.PINK);
		scene.ambientLight=	new AmbientLight(new Color(java.awt.Color.WHITE), 0);
		
		scene.geometries.add( //
				new Sphere(new Point3D(0, 0,20),20).setEmission(new Color(java.awt.Color.BLUE)).setMaterial(new Material().set_kD(0.3).set_kS(0.7).set_nShininess(100).set_kR(0.5).set_kT(0)),
				new Sphere(new Point3D(30,35,30),20).setEmission(new Color(java.awt.Color.GREEN)).setMaterial(new Material().set_kD(0.3).set_kS(0.7).set_nShininess(100).set_kR(0.5).set_kT(0)),
				new Sphere(new Point3D(60,70,50),20).setEmission(new Color(java.awt.Color.ORANGE)).setMaterial(new Material().set_kD(0.3).set_kS(0.7).set_nShininess(100).set_kR(0.5).set_kT(0)),
				new Sphere(new Point3D(-30,-35,30),20).setEmission(new Color(java.awt.Color.GREEN)).setMaterial(new Material().set_kD(0.3).set_kS(0.7).set_nShininess(100).set_kR(0.5).set_kT(0)),
				new Sphere(new Point3D(-60,-70,50),20).setEmission(new Color(java.awt.Color.ORANGE)).setMaterial(new Material().set_kD(0.3).set_kS(0.7).set_nShininess(100).set_kR(0.5).set_kT(0)),
				new Sphere(new Point3D(90,105,70),20).setEmission(new Color(java.awt.Color.RED)).setMaterial(new Material().set_kD(0.3).set_kS(0.7).set_nShininess(100).set_kR(0.5).set_kT(0)),
				new Sphere(new Point3D(-90,-105,70),20).setEmission(new Color(java.awt.Color.RED)).setMaterial(new Material().set_kD(0.3).set_kS(0.7).set_nShininess(100).set_kR(0.5).set_kT(0)),
				new Plane(new Point3D(0,0,200),new Vector(0,0,1)).setEmission(new Color(0,40,60)).setMaterial((new Material().set_kD(0.5).set_kS(0.5).set_nShininess(1200).set_kR(0).set_kT(0))));
		
				scene.lights.add( //
				new SpotLight(new Color(700, 400, 400), new Point3D(40, -40, -115), new Vector(0,0, 1)) //
				.setKc(1).setKl(4E-4).setKq(2E-5));

		Render render = new Render() //
				.setImageWriter(new ImageWriter("MiniProject-part1-DepthOfField", 600, 600)) //
				.setCamera(camera) //
				.setRayTracerBase(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Produce a picture of spheres without improve of depth of field
	 */
	@Test
	public void WithoutDepthOfField() {
		Camera camera =new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0))
				.setVpSize(600, 600).setVpDistance(1000)
				.setApHeight(1).setApWidth(1);//setFocalPlaneDistance(1010);//dont set aperture- no improvization

		scene.backGround=new Color(java.awt.Color.PINK);
		scene.ambientLight=	new AmbientLight(new Color(java.awt.Color.WHITE), 0);
		
		scene.geometries.add( //
				new Sphere(new Point3D(0, 0,20),20).setEmission(new Color(java.awt.Color.BLUE)).setMaterial(new Material().set_kD(0.3).set_kS(0.7).set_nShininess(100).set_kR(0.5).set_kT(0)),
				new Sphere(new Point3D(30,35,30),20).setEmission(new Color(java.awt.Color.GREEN)).setMaterial(new Material().set_kD(0.3).set_kS(0.7).set_nShininess(100).set_kR(0.5).set_kT(0)),
				new Sphere(new Point3D(60,70,50),20).setEmission(new Color(java.awt.Color.ORANGE)).setMaterial(new Material().set_kD(0.3).set_kS(0.7).set_nShininess(100).set_kR(0.5).set_kT(0)),
				new Sphere(new Point3D(-30,-35,30),20).setEmission(new Color(java.awt.Color.GREEN)).setMaterial(new Material().set_kD(0.3).set_kS(0.7).set_nShininess(100).set_kR(0.5).set_kT(0)),
				new Sphere(new Point3D(-60,-70,50),20).setEmission(new Color(java.awt.Color.ORANGE)).setMaterial(new Material().set_kD(0.3).set_kS(0.7).set_nShininess(100).set_kR(0.5).set_kT(0)),
				new Sphere(new Point3D(90,105,70),20).setEmission(new Color(java.awt.Color.RED)).setMaterial(new Material().set_kD(0.3).set_kS(0.7).set_nShininess(100).set_kR(0.5).set_kT(0)),
				new Sphere(new Point3D(-90,-105,70),20).setEmission(new Color(java.awt.Color.RED)).setMaterial(new Material().set_kD(0.3).set_kS(0.7).set_nShininess(100).set_kR(0.5).set_kT(0)),
				new Plane(new Point3D(0,0,200),new Vector(0,0,1)).setEmission(new Color(0,40,60)).setMaterial((new Material().set_kD(0.5).set_kS(0.5).set_nShininess(1200).set_kR(0).set_kT(0))));
		
				scene.lights.add( //
				new SpotLight(new Color(700, 400, 400), new Point3D(40, -40, -115), new Vector(0,0, 1)) //
				.setKc(1).setKl(4E-4).setKq(2E-5));

		Render render = new Render() //
				.setImageWriter(new ImageWriter("MiniProject-part1-NoDepthOfField", 600, 600)) //
				.setCamera(camera) //
				.setRayTracerBase(new RayTracerBasic(scene));
		render.renderImage();
		render.writeToImage();
	}

}
