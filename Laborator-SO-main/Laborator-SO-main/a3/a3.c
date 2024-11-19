#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

#define FIFO_NAME_READING "REQ_PIPE_93399"
#define FIFO_NAME_WRITING "RESP_PIPE_93399"

#define PING_REQUEST "PING"
#define PING_RESPONSE "PING PONG 93399"

int main(void) {
    int fd1, fd2;

    if (mkfifo(FIFO_NAME_WRITING, 0600) == -1) {
        perror("Error creating FIFO");
        exit(1);
    }

    fd1 = open(FIFO_NAME_READING, O_RDONLY);
    if (fd1 == -1) {
        perror("Could not open FIFO");
        exit(1);
    }

    fd2 = open(FIFO_NAME_WRITING, O_WRONLY);
    if (fd2 == -1) {
        perror("Could not open FIFO");
        exit(1);
    }

    write(fd2, "HELLO#", strlen("HELLO#"));

    close(fd1);
    close(fd2);
    unlink(FIFO_NAME_WRITING);

    return 0;
}
