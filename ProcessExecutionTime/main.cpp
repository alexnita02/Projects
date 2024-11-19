#include <iostream>
#include <iomanip>
#include <cstdlib>
#include <ctime>
#include <chrono>
#include <thread>


using namespace std;
using namespace chrono;


#define NUM_THREADS 4
const int arraySize = 10000;

void bubbleSort(int arr[], int n) {
    int i, j;
    int swapped;

    for (i = 0; i < n - 1; i++) {
        swapped = 0;

        for (j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {

                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;

                swapped = 1;
            }
        }


        if (!swapped) {
            break;
        }
    }
}

struct ThreadData {
    int* arr;
    int start;
    int end;
};


void bubbleSortStaticThread(int arr[]) {
    for (int i = 0; i < arraySize; i++) {
        bubbleSort(arr, arraySize);
    }
}

void bubbleSortDynamicThread(int arr[]) {
    for (int i = 0; i < arraySize; i++) {
        bubbleSort(arr, arraySize);
    }
}

void* bubbleSortThreadedStatic(void* arg) {
    ThreadData* threadData = static_cast<ThreadData*>(arg);

    for (int i = threadData->start; i < threadData->end; i++)
        bubbleSort(threadData->arr,arraySize);

    return nullptr;
}

void* bubbleSortThreadedDynamic(void* arg) {
    ThreadData* threadData = static_cast<ThreadData*>(arg);

    for (int i = 0; i < 1000; i++)
        bubbleSort(threadData->arr,arraySize);

    return nullptr;
}

void print_array(int* array,int array_size) {
    for (int i = 0; i < array_size; i++) {
        cout<<array[i]<<" ";

    }
    cout<<"\n\n\n";
}

int main() {
    
    const int n = 10000;
    int static_array[n];
    int static_array_thread[n];

    int* dynamic_array = new int[arraySize];
    int* dynamic_array_thread = new int[arraySize];

    for (int i = 0; i < arraySize; ++i) {
        static_array[i] = rand() % 10000;
        dynamic_array[i] = rand() % 10000;
        static_array_thread[i] = rand() % 10000;
       dynamic_array_thread[i] = rand() % 10000;
    }

    std::cout << "Array:\n\n";
    print_array(static_array, arraySize);

    std::cout << "\n\n\n";

    clock_t startStatic, endStatic;
    startStatic = clock();

    for (int i = 0; i < arraySize; i++)
        bubbleSort(static_array, arraySize);

    endStatic = clock();

    std::cout << "Sorted Array:\n\n";
    print_array(static_array, arraySize);

    std::cout << "\n\n\n";

    double timeTakenStatic = ((double)(endStatic - startStatic)) / CLOCKS_PER_SEC;
    std::cout << "C++ ---- Time taken for static array: " << std::fixed <<std::setprecision(6) << timeTakenStatic << " seconds\n";

    clock_t startDynamic, endDynamic;
    startDynamic = clock();

    for (int i = 0; i < arraySize; i++)
        bubbleSort(dynamic_array, arraySize);

    endDynamic = clock();

    double timeTakenDynamic = ((double)(endDynamic - startDynamic)) / CLOCKS_PER_SEC;
    cout << "C++ ---- Time taken for dynamic array: " << timeTakenDynamic << " seconds\n\n\n";

    //////////////////THREADS///////////////

    std::thread threads[NUM_THREADS];

    clock_t startStaticThread, endStaticThread;
    startStaticThread = clock();

    ThreadData staticThreadData[NUM_THREADS];
    int staticChunkSize = arraySize / NUM_THREADS;

    for (int i = 0; i < NUM_THREADS; i++) {
        staticThreadData[i].arr = static_array_thread;
        staticThreadData[i].start = i * staticChunkSize;
        staticThreadData[i].end = (i + 1) * staticChunkSize;

        threads[i] = std::thread(bubbleSortThreadedStatic, &staticThreadData[i]);
    }

    for (int i = 0; i < NUM_THREADS; i++) {
        threads[i].join();
    }

    endStaticThread = clock();

    double timeTakenStaticThread = ((double)(endStaticThread - startStaticThread)) / CLOCKS_PER_SEC;
    std::cout << "C++ ---- THREAD : Time taken for static array: " << timeTakenStaticThread << " seconds\n";

    clock_t startDynamicThread, endDynamicThread;
    startDynamicThread = clock();

    ThreadData dynamicThreadData[NUM_THREADS];
    int dynamicChunkSize = arraySize / NUM_THREADS;

    for (int i = 0; i < NUM_THREADS; i++) {
        dynamicThreadData[i].arr = reinterpret_cast<int*>(dynamic_array_thread);
        dynamicThreadData[i].start = i * dynamicChunkSize;
        dynamicThreadData[i].end = (i + 1) * dynamicChunkSize;

        threads[i] = std::thread(bubbleSortThreadedDynamic, &dynamicThreadData[i]);
    }

    for (int i = 0; i < NUM_THREADS; i++) {
        threads[i].join();
    }

    endDynamicThread = clock();

    double timeTakenDynamicThread = ((double)(endDynamicThread - startDynamicThread)) / CLOCKS_PER_SEC;
    std::cout << "C++ ---- THREAD : Time taken for dynamic array: " << timeTakenDynamicThread << " seconds\n";

    delete[] dynamic_array;
    delete[] dynamic_array_thread;

    return 0;
}






















