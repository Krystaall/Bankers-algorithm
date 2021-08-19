package bankers;

import java.util.*;


public class bankersAlgo {
	
	  int[] givenRes=new int[10];
	  int[][] max=new int[10][10];
	  int[][]alloc=new int[10][10];
	  int[][]need=new int[10][10];
	  int[] avail=new int[10];
	  int[] allocated = new int[10];
	  int[] total_alloc=new int[10];
	  int[] safeSequence=new int[10];
	  int i,j,m,n;
	
	void accept() {
		Scanner sc= new Scanner(System.in);
		
		System.out.println("Enter number of processes : ");
		n=sc.nextInt();
		System.out.println("Enter number of resources : ");
		m=sc.nextInt();	
		
		System.out.println("Enter the number of instances for each resource: ");
		for(i=0; i<m; i++) {
			System.out.println("For resource "+i+" ");
			givenRes[i] = sc.nextInt();
		}
		
		System.out.println("Enter the elements of maximum matrix :");
		for(i=0;i<n;i++){
			System.out.println("For process P"+i+": ");
			for( j=0;j<m;j++){
				max[i][j]=sc.nextInt(); 
				}
			}
		
		System.out.println("Enter the elements of allocation matrix :");
		for(i=0;i<n;i++){
			System.out.println("For process P"+i+": ");
			for(j=0;j<m;j++){
				alloc[i][j]=sc.nextInt();
				}
			}
		
		
		//need matrix calculation
		for( i=0;i<n;i++) {
			for( j=0; j<m; j++) {
				need[i][j]=max[i][j]-alloc[i][j];
			}
		}
		
		
		//allocated matrix calculation
		for( i=0;i<m;i++) {
			int sum=0;
			for( j=0;j<n;j++) {
				sum=sum+alloc[j][i];	
			}	
			allocated[i]=sum;
		}
		
		
		 //calculate available resources
        for (i = 0; i < n; i++) {
            avail[i] = givenRes[i] - allocated[i];
            }
        }
		
	
	void display() {
		System.out.println("Process\t\tAllocation\tMaximum\t\tNeed");
        for (i = 0; i < n; i++) {
            System.out.print("P" + i + "\t\t");
            for (j = 0; j < m; j++) {
                System.out.print(alloc[i][j] + " ");
            }
            

            System.out.print("\t\t");
            for (j = 0; j < m; j++) {
                System.out.print(max[i][j] + " ");
            }

            
            System.out.print("\t\t");
            for (j = 0; j < m; j++) {
                System.out.print(need[i][j] + " ");
            }
            System.out.println();

        }

        System.out.println("The available matrix is : ");
        for (i = 0; i < m; i++) {
            System.out.print(avail[i] + " ");
        }

        System.out.println();

 }
		
	
	
	 void isSafe(){
		 
	    boolean visited[] = new boolean[n]; 
	    int[] safeSequenceArray = new int[n];      
	    int work[] = new int[m];         //work array to store the copy of available resources
	    for (int i = 0;i < m; i++)
	    {
	        work[i] = avail[i];
	    }
	    int count=0;
	  
	    while (count<n)
	    {
	        boolean flag = false;
	        for (int i = 0;i < n; i++)
	        {
	            if (visited[i] == false)
	             {
	            int j;
	            
	            for ( j = 0;j < m; j++)
	            {        
	                if (need[i][j] > work[j])
	                break;
	            }
	            
	            if (j == m){
	            	for(int k=0; k<m;k++) {
	            		 work[k] = work[k]+alloc[i][k];
	            	}
	               safeSequence[count++]=i;
	               visited[i]=true;
	               flag=true;
	            }
	             }
	        }
	        if (flag == false) {
	        	System.out.print("The system is not in the safe state because lack of resources");
	        }
	        }
	            
	   
	        System.out.println("The given System is safe and following is the SAFE Sequence : ");
	                for ( i = 0;i < n; i++) {
	            System.out.print("P" + safeSequence[i] +" ");    
	        }
	    }
	     
	    
	
	
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		bankersAlgo b= new bankersAlgo();
		int no;
		
		do {
		System.out.println("\n********MENU**********");
		System.out.println("\n1. ACCEPT DETAILS\n2. DISPLAY DETAILS\n3. DISPLAY SAFE SEQUENCE\n4. EXIT");
		System.out.println("\n*************************");
		System.out.println("\nEnter your choice: ");
		no=sc.nextInt();
		switch(no)
		{
		case 1:
			b.accept();
		break;
		
		case 2:
			b.display();
		break;
		
		case 3:
			b.isSafe();
		break;
			
		case 4:
		break;

		}
		
	}while(no!=4);
	

}
}

/*
 OUTPUT:
 
 
********MENU**********

1. ACCEPT DETAILS
2. DISPLAY DETAILS
3. DISPLAY SAFE SEQUENCE
4. EXIT

*************************

Enter your choice: 
1
Enter number of processes : 
5
Enter number of resources : 
3
Enter the number of instances for each resource: 
For resource 0 
10
For resource 1 
5
For resource 2 
7
Enter the elements of maximum matrix :
For process P0: 
7
5
3
For process P1: 
3
2
2
For process P2: 
9
0
2
For process P3: 
2
2
2
For process P4: 
4
3
3
Enter the elements of allocation matrix :
For process P0: 
0
1
0
For process P1: 
2
0
0
For process P2: 
3
0
2
For process P3: 
2
1
1
For process P4: 
0
0
2

********MENU**********

1. ACCEPT DETAILS
2. DISPLAY DETAILS
3. DISPLAY SAFE SEQUENCE
4. EXIT

*************************

Enter your choice: 
2
Process		Allocation	Maximum		Need
P0		0 1 0 		7 5 3 		7 4 3 
P1		2 0 0 		3 2 2 		1 2 2 
P2		3 0 2 		9 0 2 		6 0 0 
P3		2 1 1 		2 2 2 		0 1 1 
P4		0 0 2 		4 3 3 		4 3 1 
The available matrix is : 
3 3 2 

********MENU**********

1. ACCEPT DETAILS
2. DISPLAY DETAILS
3. DISPLAY SAFE SEQUENCE
4. EXIT

*************************

Enter your choice: 
3
The given System is safe and following is the SAFE Sequence : 
P1 P3 P4 P0 P2 
********MENU**********

1. ACCEPT DETAILS
2. DISPLAY DETAILS
3. DISPLAY SAFE SEQUENCE
4. EXIT

*************************

Enter your choice: 
4

*/
