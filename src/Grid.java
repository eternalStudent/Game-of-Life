import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grid {
	
	private final Set<Point> points = new HashSet<>();
	private final Random random = new Random();
	public int size=20;
	
	public void copy(Grid other){
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
	
	public void negation(int x, int y){
		set(x, y, !get(x, y));
	}
	
	private int count(int x, int y){
		int sum=0;
		for (int i=-1; i<2; i++)
			for (int j=-1; j<2; j++)
				if ( points.contains(new Point(x+i,y+j)) && (i!=0 || j!=0) )
					sum++;
		return sum;
	}
	
	private int random(int x0, int x1){	
		return x0+random.nextInt(x1-x0+1);
	}
	
	private Set<Point> actionZone(){
		Set<Point> set = new HashSet<>();
		for (Point p: points)
			for (int i=-1; i<2; i++)
				for (int j=-1; j<2; j++)
					set.add(new Point(p.x+i,p.y+j));
		return set;
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
	
	public Grid next(){
		Grid temp = new Grid();
		temp.copy(this);
		for (Point p: actionZone()){
			boolean b= get(p.x, p.y);
			int count = count(p.x,p.y);
			if (b && (count<2 || count> 3))
				temp.set(p.x, p.y, false);
			if (!b && count==3)
				temp.set(p.x, p.y, true);	
		}
		return temp;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("{");
		for (Point p: points)
			sb.append(" ("+p.x+", "+p.y+")");
		sb.append(" }");
		return sb.toString();
	}
	
	public void writeFile(File file) throws IOException{
		FileWriter writer = new FileWriter(file);
    	writer.write(toString());
    	writer.close();
	}
	
	private String fileToString(File file) throws FileNotFoundException{
		Scanner sc = new Scanner(file);
		StringBuilder sb = new StringBuilder();
		while(sc.hasNextLine())
			sb.append(sc.nextLine());
		sc.close();
		return sb.toString();
	}
	
	public void readFile(File file) throws Exception{
		String s = fileToString(file);
		String pattern = "({0,number,integer}, {1,number,integer})";
		Pattern p = Pattern.compile("\\(-?\\d+, -?\\d+\\)");
		Matcher m = p.matcher(s);
		int i=0;
		clear();
		while (m.find(i)){
			Object[] values;
			values = new MessageFormat(pattern).parse(m.group());
			set(Integer.parseInt(values[0].toString()), Integer.parseInt(values[1].toString()), true);
			i = m.end();
		}
	}

}
