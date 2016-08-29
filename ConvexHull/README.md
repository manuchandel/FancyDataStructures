## Convex Hull Algorithm

### Initialization

* Create an ArrayList of Object type Point.
* Fill the list with some points.
* All points should be unique.

`List<Point> points= new List<Point>();`

* Create a ConvexHull object 

`ConvexHull convexHull = new ConvexHull;`

### Calling ConvexHull

* Call `findConvexHull()` function passing refrence to list of points as parameter.

### Output

* Above program returns a list of points.
* If in a list first point and last point are same then, it is possible to construct convex hull around points and returned set of points is the minimum set which forms convex hull in anticlockwise manner.
* If in a list first and last point are not same then, all the points are collinear and it is not possible to construct convex hull.

### Algorithm and Analysis

* Above program implements Graham Scan algorithm.
* Time Complexity is O(nlogn).
