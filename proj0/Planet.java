public class Planet {
    public  double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;
    private static double G = 6.67e-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet (Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(dx*dx + dy*dy);
    }
    public  double calcForceExertedBy(Planet p){
        double dist = this.calcDistance(p);
        return Planet.G*this.mass*p.mass/(dist*dist);
    }
    public double calcForceExertedByX(Planet p){
        double dist = this.calcDistance(p);
        double force = this.calcForceExertedBy(p);
        double dx = p.xxPos - this.xxPos;
        return dx*force/dist;
    }
    public double calcForceExertedByY(Planet p){
        double dist = this.calcDistance(p);
        double force = this.calcForceExertedBy(p);
        double dy = p.yyPos - this.yyPos;
        return dy*force/dist;
    }
    public double calcNetForceExertedByX (Planet [] allPlanets){
        double netForce = 0;
        for (Planet p: allPlanets)
            if (!this.equals(p))
                netForce += this.calcForceExertedByX(p);
        return netForce;
    }
    public double calcNetForceExertedByY (Planet [] allPlanets){
        double netForce = 0;
        for (Planet p: allPlanets) {
            if (!this.equals(p))
                netForce += this.calcForceExertedByY(p);
        }
        return netForce;
    }
    public void update(double dt,double fx,double fy){
        double ax=  fx / mass;
        double ay = fy / mass;
        xxVel += ax * dt;
        yyVel += ay * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }
    public void draw(){
        StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
    }
}
