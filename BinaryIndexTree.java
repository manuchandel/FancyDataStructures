/**
* The BinaryIndexTree program implements various functions of binary indexed trees.
*
* @author  Manu Chandel
* @version 1.0
* @since   2016-08-25 
*/
public class BinaryIndexTree{

	// costructor specifying size of binary tree
	public BinaryIndexTree(int N){
		this.size=N+1;
		this.mainBIT=new long [N+1];
		this.auxBIT=new long [N+1];
		this.reset();

	}
	// method to update value at index
	private void updateAtIndex(int index, long value, long bit[]){

		int i=index+1;

		// update all nodes of BIT which contains sum value of index i
		while(i<this.size){
			bit[i]+=value;
			i=i+(i&(-i));
		}
	}

	// method to query sum value from [0,index]
	private long queryAtIndex(int index, long bit[]){

		long sum=0;
		int i=index+1;

		// all values from node to root
		while(i>0){
			sum+=bit[i];
			i=i-(i&(-i));
		}

		return sum;
	}

	// public method to query sum range [l,r]
	public long queryRange(int l, int r){

		// sum from [0,r]
		long rrange=this.queryAtIndex(r,this.mainBIT)*r-this.queryAtIndex(r,this.auxBIT);
		if(l==0)
			return rrange;

		// sum from [0,l-1]
		long lrange=this.queryAtIndex(l-1,this.mainBIT)*(l-1)-this.queryAtIndex(l-1,this.auxBIT);
		
		return rrange-lrange;
	}

	// public method to query value at index
	public long query(int i){

		return this.queryAtIndex(i,this.mainBIT);
	}

	// public method to update value in range [l,r]
	public void updateRange(int l, int r, long value){

		this.updateAtIndex(l,value,this.mainBIT);
		this.updateAtIndex(r+1,0-value,this.mainBIT);
		this.updateAtIndex(l,(l-1)*value,this.auxBIT);
		this.updateAtIndex(r+1,0-r*value,this.auxBIT);

	}

	// public method to update value at index i
	public void update(int i,long value){

		this.updateRange(i,i,value);
		
	}

	// public void reset
	public void reset(){
		
		// set all BIT values to zero
		for(int i=0;i<this.size;i++){
			this.mainBIT[i]=0;
			this.auxBIT[i]=0;
		}
	}
	// private internal data structure main Binary Indexed Tree
	private long mainBIT[];

	// private internal data structure auxilary Binary Indexed Tree
	private long auxBIT[];

	// private variable size of BIT
	private int size;

}
