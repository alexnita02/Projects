#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include "a2_helper.h"
#include <pthread.h>
#include <sys/sem.h>
#include <semaphore.h>
#include <fcntl.h>

#define NO_THREADS_P3 44
#define NO_THREADS_P4 6
#define NO_THREADS_P6 4
sem_t *sem4_2_6_2;
sem_t *sem4_4_6_2;
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t mutex2 = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;
pthread_cond_t cond2 = PTHREAD_COND_INITIALIZER;
pthread_cond_t cond_p3_11 = PTHREAD_COND_INITIALIZER;
int p3_active_threads = 0;
int p3_11_count = NO_THREADS_P3;
int p3_remaining = NO_THREADS_P3;
int p6_4_wating = 0;

void *thread_func_6(void *arg)
{
    int id = *((int *)arg);
   
    if (id == 2)
    {
         sem_wait(sem4_2_6_2);
    }

    if (id == 4)
    {
        //if (ok == 0)
        pthread_mutex_lock(&mutex);
        if (!p6_4_wating)
            pthread_cond_wait(&cond, &mutex);
        pthread_mutex_unlock(&mutex);
        //sem_post(&sem1); // signal sem1 to unblock thread 1
    }


    info(BEGIN, 6, id);

    if (id == 1)
    {
        //ok++;
        pthread_mutex_lock(&mutex);
        p6_4_wating = 1;
        pthread_cond_signal(&cond);
        pthread_cond_wait(&cond2, &mutex);
        pthread_mutex_unlock(&mutex);
        //sem_wait(&sem2); // wait for thread 4 to signal sem2
    }

    info(END, 6, id);
    if (id == 4)
    {
        pthread_mutex_lock(&mutex);
        pthread_cond_signal(&cond2);
        pthread_mutex_unlock(&mutex);
    }
    
    if (id == 2)
    {
        sem_post(sem4_4_6_2);
    }
 
    return NULL;
}

void *p3_thread_func(void *arg)
{
    int id = *((int *)arg);

    pthread_mutex_lock(&mutex);
    p3_active_threads++;
    if (p3_active_threads > 5)
    {
        int ret = pthread_cond_wait(&cond, &mutex);
        if (ret != 0)
        {
            printf("p3_active_threads: %d p3_11_count: %d p3_remaining: %d ret: %d \n",
                    p3_active_threads, p3_11_count, p3_remaining, ret);
        }
    }
    pthread_mutex_unlock(&mutex);

    info(BEGIN, 3, id);
   // if 11 in critical section. w8 for 4 other threads. fourth thread activates 11
   // if last 4 threads w8 for 11 
    pthread_mutex_lock(&mutex2);
    p3_remaining--;
    if (id == 11)
    {
        p3_11_count = 0;
        if (p3_remaining > 0)
            pthread_cond_wait(&cond_p3_11, &mutex2);
    }
    else
    {
        if (p3_remaining <= 4 || p3_11_count < 5)
        {
            p3_11_count++;
            if (p3_11_count == 4)
            {
                pthread_cond_signal(&cond_p3_11);
            }
            pthread_cond_wait(&cond2, &mutex2);
        }
    }
    pthread_mutex_unlock(&mutex2);
    
    info(END, 3, id);
    if (id == 11)
    {
        pthread_mutex_lock(&mutex2);
        pthread_cond_broadcast(&cond2);
        p3_remaining += 6;
        p3_11_count = 6;
        pthread_mutex_unlock(&mutex2);
    }
 
    pthread_mutex_lock(&mutex);
    p3_active_threads--;
    if (p3_active_threads >= 5)
    {
        pthread_cond_signal(&cond);
    }
    pthread_mutex_unlock(&mutex);
    return NULL;
}

void *p4_thread_func(void *arg)
{
    int id = *((int *)arg);
    if (id == 4)
    {
        sem_wait(sem4_4_6_2);
    }

    
    info(BEGIN, 4, id);
    info(END, 4, id);

    if (id == 2)
    {
        sem_post(sem4_2_6_2);
    }
    return NULL;
}

int main()
{
    pid_t p2 = -1, p3 = -1, p4 = -1, p5 = -1, p6 = -1, p7 = -1, p8 = -1;

    init();

    sem4_2_6_2 = sem_open("sem4_2_6_2", O_CREAT, 0644, 0);
    if (!sem4_2_6_2)
    {
        perror("sem4_2_6_2 not created! \n");
        exit(1);
    }
    sem4_4_6_2 = sem_open("sem4_4_6_2", O_CREAT, 0644, 0);
    if (!sem4_4_6_2)
    {
        perror("sem4_4_6_2 not created! \n");
        exit(1);
    }

    info(BEGIN, 1, 0);
    p2 = fork();
    if (p2 == 0)
    {
        pthread_t threads[NO_THREADS_P4];
        int thread_args[NO_THREADS_P4];

        info(BEGIN, 2, 0);
        p4 = fork();
        if (p4 == 0)
        {
            info(BEGIN, 4, 0);
            p5 = fork();
            if (p5 == 0)
            {
                info(BEGIN, 5, 0);
                info(END, 5, 0);
                exit(0);
            }

            p7 = fork();
            if (p7 == 0)
            {
                info(BEGIN, 7, 0);
                info(END, 7, 0);
                exit(0);
            }
            for(int i = 0; i < NO_THREADS_P4; i++)
       	    {
               thread_args[i] = i + 1;
               if (pthread_create(&threads[i], NULL, p4_thread_func, &thread_args[i]))
               {
                  exit(1);
               }
             }

            for(int i = 0; i < NO_THREADS_P4; i++)
            {
               if(pthread_join(threads[i], NULL))
               {
                   perror("pthread_join");
                   exit(1);
               }
           }

            waitpid(p5, NULL, 0);
            waitpid(p7, NULL, 0);

            info(END, 4, 0);
            exit(0);
        }

        waitpid(p4, NULL, 0);
        info(END, 2, 0);

        exit(0);
    }

    p3 = fork();

    if (p3 == 0)
    {
        pthread_t threads[NO_THREADS_P3];
        int thread_args[NO_THREADS_P3];

        info(BEGIN, 3, 0);
        p8 = fork();
        if (p8 == 0)
        {
            info(BEGIN, 8, 0);
            info(END, 8, 0);
            exit(0);
        }

        for(int i = 0; i < NO_THREADS_P3; i++)
        {
            thread_args[i] = i + 1;
            if (pthread_create(&threads[i], NULL, p3_thread_func, &thread_args[i]))
            {
                exit(1);
            } 
        }
            
        for(int i = 0; i < NO_THREADS_P3; i++)
        {
            if(pthread_join(threads[i], NULL))
            {
                perror("pthread_join");
                exit(1);
            }
        }

        waitpid(p8, NULL, 0);
        info(END, 3, 0);
        exit(0);
    }

    p6 = fork();
    if (p6 == 0)
    {
        pthread_t threads[NO_THREADS_P6];
        int thread_args[NO_THREADS_P6];

        info(BEGIN, 6, 0);
        // create threads
        for (int i = 0; i < NO_THREADS_P6; i++)
        {
            thread_args[i] = i + 1;
            if (pthread_create(&threads[i], NULL, thread_func_6, &thread_args[i]))
            {
                perror("pthread_create p6");
                exit(1);
            }
        }

        // join threads
        for (int i = 0; i < NO_THREADS_P6; i++)
        {
            if (pthread_join(threads[i], NULL))
            {
                perror("pthread_join p6");
                exit(1);
            }
        }

        // pthread_mutex_destroy(&mutex);
        // pthread_cond_destroy(&cond);

        info(END, 6, 0);
        exit(0);
    }

    waitpid(p2, NULL, 0);
    waitpid(p3, NULL, 0);
    waitpid(p6, NULL, 0);

    info(END, 1, 0);
    sem_close(sem4_2_6_2);
    sem_close(sem4_4_6_2);
    sem_unlink("./sem4_2_6_2");
    sem_unlink("./sem4_4_6_2");
    return 0;
}
