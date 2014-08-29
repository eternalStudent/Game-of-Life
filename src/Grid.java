import java.awt.Point;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Grid {
	
	public Set<Point> points = new HashSet<>();
	private Random random = new Random();
	public final int width=20;
	public final int height=20;
	
	public Grid(){		
	}
	
	public void CopyGrid(Grid other){
		clear();
		points.addAll(other.points);
	}
	
	public boolean equals(Grid other){
		return points.equals(other.points);
	}
	
	public boolean get(int x, int y){
		return points.contains(new Point(x,y));
	}
	
	public void set(int x, int y, boolean b){
		if (b)
			points.add(new Point(x,y));
		else
			points.remove(new Point(x,y));
	}
	
	public int count(int x, int y){
		int sum=0;
		for (int i=-1; i<2; i++)
			for (int j=-1; j<2; j++)
				if ( points.contains(new Point(x+i,y+j)) && (i!=0 || j!=0) )
					sum++;
		return sum;
	}
	
	public int random(int x0, int x1){	
		return x0+random.nextInt(x1-x0+1);
	}
	
	public void clear(){
		points.clear();
		
	}
	
	public void randomGrid(int width, int height){
		clear();
		int x0=random(0,width/2-2);
		int dx=2*(width/2-x0)+(width%2);
		int y0=random(0,height/2-2);
		int dy=2*(height/2-y0)+(height%2);
		for (int x=x0; x<dx+x0; x++)
			for (int y=y0; y<dy+y0; y++)
				set(x,y,random.nextBoolean());
		
	}
	
	public Set<Point> actionZone(){
		Set<Point> set = new HashSet<>();
		for (Point p: points)
			for (int i=-1; i<2; i++)
				for (int j=-1; j<2; j++)
					set.add(new Point(p.x+i,p.y+j));
		return set;
	}

}
