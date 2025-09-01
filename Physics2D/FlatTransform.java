package Physics2D;
public class FlatTransform {
     public final float x,y,sin,cos;
     public final static FlatTransform standardZero=new FlatTransform(0f,0f,0f);
     public FlatTransform(Vector2D vector2D, float angle){
         this.x= vector2D.x;
         this.y= vector2D.y;
         this.sin=(float) Math.sin(angle);
         this.cos=(float) Math.cos(angle);
     }
     public FlatTransform(float x,float y,float angle){
         this.x=x;
         this.y=y;
         this.sin=(float) Math.sin(angle);
         this.cos=(float) Math.cos(angle);
     }
}
