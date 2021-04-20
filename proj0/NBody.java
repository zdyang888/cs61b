public class NBody {
    public static double readRadius(String path){
        In in = new In(path);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    public static Planet [] readPlanets(String path){
        In in = new In(path);
        int length = in.readInt();
        in.readDouble();
        Planet [] planets = new Planet[length];
        int i = 0;
        while (i!=length){
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            Planet p = new Planet(xP, yP, xV, yV, m, img);
            planets[i++] = p;
        }
        return planets;
    }
    public static void main(String [] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        String imageToDraw = "images/starfield.jpg";
        double r = readRadius(filename);
        StdDraw.setScale(-r, r);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw);
        Planet [] planets = readPlanets(filename);
        for (Planet p: planets)
            p.draw();
        StdDraw.enableDoubleBuffering();
        double t = 0;

        while (t<=T){
            double [] xForces=new double[planets.length];
            double [] yForces=new double[planets.length];
            for (int i=0;i< planets.length;++i){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i=0;i< planets.length;++i){
                planets[i].update(dt,xForces[i],yForces[i]);
            }

            StdDraw.clear();
            StdDraw.picture(0, 0, imageToDraw);
            for (Planet p: planets)
                p.draw();
            StdDraw.show();
            StdDraw.pause(10);
            t += dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
