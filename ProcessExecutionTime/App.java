import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {
    public static void bubbleSort(int arr[],int n){
        n = arr.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true;
                }

                
            }

            
            if (!swapped) {
                break;
            }
        }

    }

    // private static void bubbleSortThreadedStatic(int[] arr) {
    //     for (int i = 0; i < 1000; i++) {
    //         bubbleSort(arr,arr.length);
    //     }
    // }

    private static void bubbleSortThreadedStatic(int[] arr) {
        int numThreads = 4; 
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
    
        for (int i = 0; i < numThreads; i++) {
            int startIndex = i * (arr.length / numThreads);
            int endIndex = (i + 1) * (arr.length / numThreads);
    
            
            int[] subArray = Arrays.copyOfRange(arr, startIndex, endIndex);
    
            
            executor.submit(() -> bubbleSort(subArray, subArray.length));
        }
    
        
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void bubbleSortThreadedDynamic(int[] arr) {
        int numThreads = 4; 
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
    
        for (int i = 0; i < numThreads; i++) {
            int startIndex = i * (arr.length / numThreads);
            int endIndex = (i + 1) * (arr.length / numThreads);
    
            
            int[] subArray = Arrays.copyOfRange(arr, startIndex, endIndex);
    
            
            executor.submit(() -> bubbleSort(subArray, subArray.length));
        }
    
        
        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    static void printArray(int arr[], int size)
    {
        int i;
        for (i = 0; i < size; i++)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    public static void main(String[] args) throws Exception {
       
        final int staticArray[]=new int [100000];  
        
         for (int i = 0; i < 10000; ++i) {
            staticArray[i] = (int) (Math.random() * 10000);  
        }
        

        int[] dynamicArray = Arrays.copyOf(staticArray, staticArray.length);
        
        int[] staticArrayThread = Arrays.copyOf(staticArray, staticArray.length);

        int[] dynamicArrayThread = Arrays.copyOf(staticArray, staticArray.length);

        
        
        
        
        long startStatic = System.nanoTime();
        bubbleSort(staticArray, staticArray.length);
        long endStatic = System.nanoTime();

        double timeTakenStatic = (endStatic - startStatic) / 1e9;
        System.out.printf("Java ---- Time taken for static array: %f seconds%n", timeTakenStatic);

        
        long startDynamic = System.nanoTime();
        bubbleSort(dynamicArray, dynamicArray.length);
        long endDynamic = System.nanoTime();

        double timeTakenDynamic = (endDynamic - startDynamic) / 1e9;
        System.out.printf("Java ---- Time taken for dynamic array: %f seconds%n\n\n", timeTakenDynamic);


//////////////////THREADS

        long startStaticThread = System.nanoTime();
        Thread staticThread = new Thread(() -> bubbleSortThreadedStatic(staticArray));
        staticThread.start();
        bubbleSortThreadedStatic(staticArrayThread);
        staticThread.join();
        long endStaticThread = System.nanoTime();
        double timeTakenStaticThread = (endStaticThread - startStaticThread) / 1e9;
        System.out.printf("Java ---- THREAD : Time taken for static array: %.6f seconds%n", timeTakenStaticThread);

        
        long startDynamicThread = System.nanoTime();
        Thread dynamicThread = new Thread(() -> bubbleSortThreadedDynamic(dynamicArray));
        dynamicThread.start();
        bubbleSortThreadedDynamic(dynamicArrayThread);
        dynamicThread.join();
        long endDynamicThread = System.nanoTime();
        double timeTakenDynamicThread = (endDynamicThread - startDynamicThread) / 1e9;
        System.out.printf("Java ---- THREAD : Time taken for dynamic array: %.6f seconds%n", timeTakenDynamicThread);
    }
    
}



