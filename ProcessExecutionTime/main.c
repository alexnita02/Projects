#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <pthread.h>

#define NUM_THREADS 4
const int arraySize = 10000;
pthread_t staticThread, dynamicThread;

pthread_mutex_t staticMutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t dynamicMutex = PTHREAD_MUTEX_INITIALIZER;

void bubbleSort(int arr[], int n) {
    int i, j;
    int swapped;

    for (i = 0; i < n - 1; i++) {
        swapped = 0;
        for (j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                // interschimb arr[j] cu arr[j+1]
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;

                swapped = 1;
            }
        }

        // daca doua elemente nu au fost interschimbate,break 
        if (!swapped) {
            break;
        }
    }
}


typedef struct {
    int* arr;
    int start;
    int end;
} ThreadData;

void* bubbleSortThreadedStatic(void* arg) {
    ThreadData* threadData = (ThreadData*)arg;

    pthread_mutex_lock(&staticMutex);
    bubbleSort(threadData->arr + threadData->start, threadData->end - threadData->start);
    pthread_mutex_unlock(&staticMutex);

    return NULL;
}

void* bubbleSortThreadedDynamic(void* arg) {
    ThreadData* threadData = (ThreadData*)arg;

    pthread_mutex_lock(&dynamicMutex);
    bubbleSort(threadData->arr + threadData->start, threadData->end - threadData->start);
    pthread_mutex_unlock(&dynamicMutex);

    return NULL;
}


void printArray(int arr[], int size) {
    for (int i = 0; i < size; i++)
        printf("%d ", arr[i]);
    printf("\n");
}

int main() {
   

    int staticArray[10000];  
    int staticArrayThread[10000];

    //dinamic
    int* dynamicArray = (int*)malloc(arraySize * sizeof(int));
    int* dynamicArrayThread = (int*)malloc(arraySize * sizeof(int));
    
    for (int i = 0; i < arraySize; ++i) {
        staticArray[i] = rand() % 10000;
        dynamicArray[i] = rand() % 10000;
        staticArrayThread[i] = rand() % 10000;
        dynamicArrayThread[i] = rand() % 10000;
    }
    printf("Array:\n\n");
    printArray(staticArray,arraySize);
    printf("\n\n\n");


    clock_t startStatic, endStatic;
    startStatic = clock();
    
    for(int i=0;i<arraySize;i++)
        bubbleSort(staticArray, arraySize);
    
    endStatic = clock();

    printf("Sorted Array:\n\n");
    printArray(staticArray,arraySize);
    printf("\n\n\n");

    double timeTakenStatic = ((double)(endStatic - startStatic)) / CLOCKS_PER_SEC;
    printf("C ---- Time taken for static array: %.6f seconds\n", timeTakenStatic);

    
    clock_t startDynamic, endDynamic;
    startDynamic = clock();
    
    for(int i=0;i<arraySize;i++)
        bubbleSort(dynamicArray, arraySize);
    
    endDynamic = clock();

    double timeTakenDynamic = ((double)(endDynamic - startDynamic)) / CLOCKS_PER_SEC;
    printf("C ---- Time taken for dynamic array: %.6f seconds\n\n\n", timeTakenDynamic);

    

//////////////////THREADS///////////////

    pthread_t threads[NUM_THREADS];

    clock_t startStaticThread, endStaticThread;
    startStaticThread = clock();

    ThreadData staticThreadData[NUM_THREADS];
    int staticChunkSize = arraySize / NUM_THREADS;

    for (int i = 0; i < NUM_THREADS; i++) {
        staticThreadData[i].arr = staticArrayThread;
        staticThreadData[i].start = i * staticChunkSize;
        staticThreadData[i].end = (i + 1) * staticChunkSize;

        pthread_create(&threads[i], NULL, bubbleSortThreadedStatic, &staticThreadData[i]);
    }

    for (int i = 0; i < NUM_THREADS; i++) {
        pthread_join(threads[i], NULL);
    }

    endStaticThread = clock();

    double timeTakenStaticThread = ((double)(endStaticThread - startStaticThread)) / CLOCKS_PER_SEC;
    printf("C ---- THREAD : Time taken for static array: %.6f seconds\n", timeTakenStaticThread);

    clock_t startDynamicThread, endDynamicThread;
    startDynamicThread = clock();

    ThreadData dynamicThreadData[NUM_THREADS];
    int dynamicChunkSize = arraySize / NUM_THREADS;

    for (int i = 0; i < NUM_THREADS; i++) {
        dynamicThreadData[i].arr = dynamicArrayThread;
        dynamicThreadData[i].start = i * dynamicChunkSize;
        dynamicThreadData[i].end = (i + 1) * dynamicChunkSize;

        pthread_create(&threads[i], NULL, bubbleSortThreadedDynamic, &dynamicThreadData[i]);
    }

    for (int i = 0; i < NUM_THREADS; i++) {
        pthread_join(threads[i], NULL);
    }

    endDynamicThread = clock();

    double timeTakenDynamicThread = ((double)(endDynamicThread - startDynamicThread)) / CLOCKS_PER_SEC;
    printf("C ---- THREAD : Time taken for dynamic array: %.6f seconds\n\n\n", timeTakenDynamicThread);


    free(dynamicArray);

    return 0;
}
