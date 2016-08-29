/**
* The ConvexHull Program implements an application that returns convex hull of points on 2D plane
*
* @author  Manu Chandel
* @version 1.0
* @since   2016-08-29
*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class ConvexHull{

	// sets first vertex i.e bottomost left vertex
	public void setFirstVertex(){

		int index=0;
		Point farDownLeft=this.points.get(0);

		for(int i=1;i<this.points.size();i++){

			// get reference to object
			Point point=this.points.get(i);

			// search for bottomost
			if(point.y<farDownLeft.y){
				farDownLeft=point;
				index=i;
			}
			// leftmost if y is same
			else if(point.y==farDownLeft.y){

				if(point.x<farDownLeft.x){
					farDownLeft=point;
					index=i;
				}
			}else;
		}
		this.firstVertex=new Point(farDownLeft);

		// remove firstVertex
		this.points.remove(index);

	}

	// sorts vertices according to polar angle
	private void sortOnPolarAngle(){


		for(int i=0;i<this.points.size();i++){

			Point point=this.points.get(i);

			// change origin
			point.x=point.x-this.firstVertex.x;
			point.y=point.y-this.firstVertex.y;
		}

		// sort on  polar angle and distance
		Collections.sort(points,new Point.SlopeComparison());

		for(int i=0;i<this.points.size();i++){

			Point point=this.points.get(i);

			// restore origin
			point.x=point.x+this.firstVertex.x;
			point.y=point.y+this.firstVertex.y;
			
		}

	}	

	// filters collinear vertices
	private void filterCollinearVertices(){

		List<Point> currentList=this.points;

		// create new list
		this.points=new ArrayList<Point>();

		this.points.add(currentList.get(0));

		// loop for every element remove collinears
		for(int i=1;i<currentList.size();i++){

			// check for orientation for first vertex, last element in current list and element at index i
			// if orientation not zero then add 
			if(Point.orientation(this.firstVertex,this.points.get(this.points.size()-1),currentList.get(i))!=0)
				
				this.points.add(currentList.get(i));
		}
	}

	// returns point just below top
	private Point getBelowTop(Stack<Point> stack){

		// save top element
		Point top=stack.peek();
		stack.pop();

		// get element below top
		Point belowTop=stack.peek();

		// restore top element
		stack.push(top);

		return belowTop;
	}

	// finds convex hull
	public List<Point> findConvexHull(List<Point> points){
		
		// more than two points required
		if(points.size()<2){
			return points;
		}
		// initialize points
		this.points=points;
		this.setFirstVertex();

		// preprocess
		this.sortOnPolarAngle();
		this.filterCollinearVertices();

		Stack<Point> stack =new Stack<Point>();

		// add first vertex to last of filtered points
		this.points.add(this.firstVertex);

		// only more than 3 noncollinear points can make complete hull
		if(this.points.size()==2){

			// reverse elements of a list tp form counter clockwise order
			Collections.reverse(this.points);
			return this.points;
		}
			

		// push first vertex and two elements
		stack.push(this.firstVertex);
		stack.push(this.points.get(0));
		stack.push(this.points.get(1));

		// run for all elements
		for(int i=2;i<this.points.size();i++){

			// whicle orientation of element at i and previous two elements is not counterclockwise
			while(Point.orientation(this.getBelowTop(stack),stack.peek(),this.points.get(i))!=1){

				//keep popping from stack
				stack.pop();
			}
			stack.push(this.points.get(i));
		}

		List<Point> convexHull=new ArrayList<Point>();

		// add elements to convexHull
		while(!stack.empty()){

			convexHull.add(stack.pop());

		}

		// reverse elements of a list to form counter clockwise order
		Collections.reverse(convexHull);

		// return convexHull
		return convexHull;

	}

	public List<Point> points;
	public Point firstVertex;

}

class Point{

	//default constructor
	public Point(){}

	// constructor
	public Point(long x, long y){
		this.x=x;
		this.y=y;
	}

	// copy constructor
	public Point(Point p){
		this(p.x,p.y);
	}

	// returns orientation of three points
	public static int orientation(Point p1, Point p2, Point p3){

		long slopeDifference=(p3.y-p1.y)*(p2.x-p1.x)-(p2.y-p1.y)*(p3.x-p1.x);

		// counter clockwise
		if(slopeDifference>0)
			return 1;
		// clockwise
		else if(slopeDifference<0)
			return -1;
		// collinear
		else return 0;
	}

	// returns euclidean distance between two points
	public static double euclideanDistance(Point p1, Point p2){
		double xDistance=p1.x-p2.x;
		double yDistance=p1.y-p2.y;

		return Math.sqrt(xDistance*xDistance+yDistance*yDistance);
	}

	// returns comparing slope after slope compares distance from origin
	public static class SlopeComparison implements Comparator<Point>{
		
		@Override
        public int compare(Point p1, Point p2) {
            
            Point origin=new Point(0,0);
            
            int ccw=Point.orientation(origin,p1,p2);

            // collinear give high priority to farthest point
            if(ccw==0){
            	double p1Distance=Point.euclideanDistance(origin,p1);
            	double p2Distance=Point.euclideanDistance(origin,p2);

            	if(p1Distance> p2Distance)
            		return -1;
            	else if(p1Distance< p2Distance)
            		return 1;
            	else return 0;
            }

            return 0-ccw;

        }

	}
	public long x;
	public long y;
}

