#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <getopt.h>
#include <limits.h>
#include <dirent.h>
#include <string.h>
#include <stdio.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>

#define VARIANT 93399
#define MAGIC "Qf"

void print_help_message()
{
    char *help_message = "./a1 [OPTIONS] [PARAMETERS]\n"\
                         "     Available options:\n"\
                         "      - variant     Shows the variant of Homework assingment \n"\
                         "\n"
                         "      - list [recursive] name_starts_with=/has_perm_execute= path=<dir_path> \n"\
                         "                    Lists the contentent of the current directory.\n"\
                         "                    If recursive is specified, recursively prints\n"\
                         "                    the content of each subdirectory find under path.\n"\
                         "                    If filtering_options is specified, only the paths that match.\n"\
                         "                    the filter are printed.\n"\
                         "\n"
                         "      - parse path=<file_path>\n"\
                         "                    Checks if file specified in path complies to\n"\
                         "                                SF format specified in homework.\n"\
                         "\n"
                         "      - extract path=<file_path> section=<sect_nr> line=<line_nr>\n"\
                         "                     Find and display some part of a certain section of a SF file.\n"
                         "\n"
                         "      - findall path=<dir_path>\n "\
                         "                     Same as list recursive\n";
    printf("%s", help_message);
}


void extract(char *file_path, int section_no, int line_no)
{
    int fd = -1;

    if (!file_path) {
       	printf("ERROR\ninvalid file\n");
        return; 
    }
	if ((fd = open(file_path, O_RDONLY)) < 0)
    {
	    printf("ERROR\ninvalid file\n");
        return;
    }

    int magic;
    int magic_value = 0x6651;
    int magic_valid = 0;

	lseek(fd, -4, SEEK_END);
	int header_size;
	read(fd, &header_size, 2);
	read(fd, &magic, 2);

	if (magic == magic_value)
	{
        magic_valid = 1;
	}

	lseek(fd, -header_size, SEEK_CUR);

    int version;
    int version_valid = 0;

	read(fd, &version, 4);
	if (version >= 123 && version <= 217)
	{   
        version_valid = 1;
	}

    int sect_type_invalid = 0;
    int no_sect = 0;
    char sect_type = 0;
    int no_sect_valid = 0;
    char name[11];
    int offset;
    int size;
	read(fd, &no_sect, 1);
    if (no_sect >= 8 && no_sect <= 12) {
        no_sect_valid = 1 ;
    }

	for (int i = 0; i < no_sect; ++i)
	{
		read(fd, &name, 11);
		read(fd, &sect_type, 1);
		if (sect_type != 60 && sect_type != 57 && sect_type != 51 && sect_type != 25 && sect_type != 40 && sect_type != 98)
		{
            sect_type_invalid = 1;
		}
		read(fd, &offset, 4);
		read(fd, &size, 4);
	}

    read(fd, &header_size, 2);
	read(fd, &magic, 2);

	lseek(fd, -header_size, SEEK_CUR);
	read(fd, &version, 4);
	read(fd, &no_sect, 1);

    if (section_no > no_sect) {
       printf("ERROR\ninvalid section\n");
       close(fd);
       return;
    }

    if (magic_valid && version_valid && no_sect_valid && !sect_type_invalid) {
        for (int i = 0; i < no_sect; ++i)
        {
            read(fd, &name, 11);
            name[11] = '\0';
		    read(fd, &sect_type, 1);
            read(fd, &offset, 4);
		    read(fd, &size, 4);
             if (section_no == i + 1) {
                lseek(fd, offset, SEEK_SET);

                char line[256];
                int line_idx = 1;
                int bytes_read = 0;
                while ((bytes_read += read(fd, &line, 256)) < size) {
                    line[255] = '\0';
                    char *nl = strtok(line, "\n");
                    while (nl != NULL) {
                        if (line_idx == line_no) {
                            printf("SUCCESS\n%s", line);
                            close(fd);
                            return;
                        }
                        nl = strtok(NULL, "\n");
                        line_idx++;
                }
                }
                printf("ERROR\ninvalid line\n");
                }
            }
    }
    close(fd);
}

void parse(char *file_path)
{
    int fd = -1;

    if (!file_path) {
       	printf("ERROR\ninvalid file path\n");
        return; 
    }
	if ((fd = open(file_path, O_RDONLY)) < 0)
    {
	    printf("ERROR\ninvalid file path\n");
        return;
    }

    int magic;
    int magic_value = 0x6651;
    int magic_valid = 0;

	lseek(fd, -4, SEEK_END);
	int header_size;
	read(fd, &header_size, 2);
	read(fd, &magic, 2);

	if (magic == magic_value)
	{
        magic_valid = 1;
	}

    if (!magic_valid) {
        printf("ERROR\nwrong magic\n");
        close(fd);
        return;
    }
	lseek(fd, -header_size, SEEK_CUR);

    int version;
    int version_valid = 0;

	read(fd, &version, 4);
	if (version >= 123 && version <= 217)
	{   
        version_valid = 1;
	}

    if (!version_valid) {
        printf("ERROR\nwrong version\n");
        close(fd);
        return;
    }

    int sect_type_invalid = 0;
    int no_sect = 0;
    char sect_type = 0;
    int no_sect_valid = 0;
    char name[11];
    int offset;
    int size;
	read(fd, &no_sect, 1);
    if (no_sect >= 8 && no_sect <= 12) {
        no_sect_valid = 1 ;
    }

    if (!no_sect_valid) {
        printf("ERROR\nwrong sect_nr\n");
        close(fd);
        return;
    }

	for (int i = 0; i < no_sect; ++i)
	{
		read(fd, &name, 11);
		read(fd, &sect_type, 1);
		if (sect_type != 60 && sect_type != 57 && sect_type != 51 && sect_type != 25 && sect_type != 40 && sect_type != 98)
		{
            sect_type_invalid = 1;
            printf("ERROR\nwrong sect_types\n");
            close(fd);
            return;
		}
		read(fd, &offset, 4);
		read(fd, &size, 4);
	}

    read(fd, &header_size, 2);
	read(fd, &magic, 2);

	lseek(fd, -header_size, SEEK_CUR);
	read(fd, &version, 4);
	read(fd, &no_sect, 1);

    if (magic_valid && version_valid && no_sect_valid && !sect_type_invalid) {
        printf("SUCCESS\nversion=%d\nnr_sections=%d\n", version, no_sect);

        for (int i = 0; i < no_sect; ++i)
        {
            read(fd, &name, 11);
            name[11] = '\0';
		    read(fd, &sect_type, 1);
            read(fd, &offset, 4);
		    read(fd, &size, 4);
            printf("section%d: %s %d %d\n", i + 1 , name, sect_type, size); 
        }
    }
    close(fd);
}

void listDir(const char *path , char *filter, int has_perm_execute)
{
    DIR *dir = NULL;
    struct dirent *entry = NULL;
    char fullPath[512];
    dir = opendir(path);
    if (dir == NULL) {
        printf("ERROR");
        return;
    }

    if (strlen(path) == 1) {
        printf("ERROR");
        return;
    }

    printf("SUCCESS\n");
    while((entry = readdir(dir)) != NULL) {
        if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0)
            continue;
        snprintf(fullPath, sizeof(fullPath), "%s/%s", path, entry->d_name);
        if (!filter) {
            if (!has_perm_execute) {
                printf("%s\n", fullPath);
            }  else if (has_perm_execute && access(fullPath, X_OK) == 0){
                printf("%s\n", fullPath);
            }
            continue;
        }
        if (!strncmp(filter, entry->d_name, strlen(filter))) {
              if (!has_perm_execute) {
                  printf("%s\n", fullPath);
               }  else if (has_perm_execute && access(fullPath, X_OK) == 0){
                  printf("%s\n", fullPath);
               }
        }
    }
    closedir(dir);
}


int has_sect_size_smaller(char *file_path)
{
    int fd;

	if ((fd = open(file_path, O_RDONLY)) < 0)
    {
	    printf("ERROR\ninvalid file path\n");
    }

    int magic;

	lseek(fd, -4, SEEK_END);
	int header_size;
	read(fd, &header_size, 2);
	read(fd, &magic, 2);

	lseek(fd, -header_size, SEEK_CUR);

    int version;

	read(fd, &version, 4);

    int no_sect = 0;
    char sect_type = 0;
    char name[11];
    int offset;
    int size;
	read(fd, &no_sect, 1);
	for (int i = 0; i < no_sect; ++i)
	{
		read(fd, &name, 11);
		read(fd, &sect_type, 1);
		read(fd, &offset, 4);
		read(fd, &size, 4);
        if (size  > 1319)
            return 0;
	}
    close(fd);
    return 1;
}

void listDirRecursive(const char *path , char *filter, int has_perm_execute)
{
    DIR *dir = NULL;
    struct dirent *entry = NULL;
    char fullPath[512];
    dir = opendir(path);
    if (dir == NULL) {
        printf("ERROR");
        return;
    }

    static int print = 0;
    if (print == 0) {
        printf("SUCCESS\n");
        print =1;
    }

    while((entry = readdir(dir)) != NULL) {
        if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0)
            continue;
        snprintf(fullPath, sizeof(fullPath), "%s/%s", path, entry->d_name);
        if (entry->d_type != DT_DIR) {
            if (filter) {
                if (!strncmp(filter, entry->d_name, strlen(filter))) {
                    if (!has_perm_execute) {
                        printf("%s\n", fullPath);
                    }  else if (has_perm_execute &&  access(fullPath, X_OK) == 0){
                             printf("%s\n", fullPath);
                    }
                }
            } else if (filter == NULL) {
                    if (!has_perm_execute) {
                        printf("%s\n", fullPath);
                    }   else if (has_perm_execute && access(fullPath, X_OK) == 0){
                             printf("%s\n", fullPath);
                    }
            }
         } else {
            if (filter) {
                if (!strncmp(filter, entry->d_name, strlen(filter))) {
                    if (!has_perm_execute) {
                        printf("%s\n", fullPath);
                    }  else if (has_perm_execute && access(fullPath, X_OK) == 0){
                             printf("%s\n", fullPath);
                    }
                }
            } else if (filter == NULL ){
                    if (!has_perm_execute) {
                        printf("%s\n", fullPath);
                    }  else if (has_perm_execute && access(fullPath, X_OK) == 0){
                             printf("%s\n", fullPath);
                    }

            }
            listDirRecursive(fullPath, filter, has_perm_execute);
        }
    }
    closedir(dir);
}


void findall(const char *path)
{
    DIR *dir = NULL;
    struct dirent *entry = NULL;
    char fullPath[512];
    dir = opendir(path);
    if (dir == NULL) {
        printf("ERROR");
        return;
    }

    static int print = 0;
    if (print == 0) {
        printf("SUCCESS\n");
        print =1;
    }

    while((entry = readdir(dir)) != NULL) {
        if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0)
            continue;
        snprintf(fullPath, sizeof(fullPath), "%s/%s", path, entry->d_name);
        if (entry->d_type != DT_DIR) {
            if (has_sect_size_smaller(fullPath))
                printf("%s\n", fullPath);
         } else { 
            findall(fullPath);
        }
    }
    closedir(dir);
}

int main(int argc, char **argv){

    int list_cmd = 0;
    int list_recursive_cmd = 0;
    int parse_cmd = 0;
    int extract_cmd = 0;
    int findall_cmd = 0;
    int has_perm_execute = 0;
    char *path = NULL;
    char *name_starts_with_filter = NULL;
    int line_number = 0;
    int section_number = 0;

    if (argc < 1) {
        printf("Please provide and option\n");
        print_help_message();
        return EXIT_SUCCESS;
    }

    for (int i = 1; i < argc; ++i) {
        if (!strcmp(argv[i], "variant")) {
            printf("%d\n", VARIANT);
            return EXIT_SUCCESS;
        } 
        if (!strcmp(argv[i], "list")) {
            list_cmd = 1;
            continue;
        } 
        if (!strcmp(argv[i], "recursive")) {
            list_recursive_cmd = 1;
            continue;
        }
        if (!strcmp(argv[i], "parse")) {
            parse_cmd = 1;
            continue;
        } 
        if (!strcmp(argv[i], "extract")) {
            extract_cmd = 1;
        } 
        if (!strcmp(argv[i], "findall")) {
            findall_cmd = 1;
            continue;
        } 
        if (!strcmp(argv[i], "has_perm_execute")) {
            has_perm_execute = 1;
        }
        if (!strncmp(argv[i], "name_starts_with", strlen("name_starts_with"))) {
            name_starts_with_filter = argv[i] +  strlen("name_starts_with=");
            continue;
        }
        if (!strncmp(argv[i], "path", strlen("path"))) {
            path = argv[i] +  strlen("path=");
            continue;
        } 
        if (!strncmp(argv[i], "section", strlen("section"))) {
            section_number = atoi((char*)(argv[i] + strlen("section=")));
            continue;
        } 
        if (!strncmp(argv[i], "line", strlen("line"))) {
            line_number = atoi((char*)(argv[i] +  strlen("line=")));
        } 
    }
    if (extract_cmd && path && section_number && line_number) {
        extract(path, section_number, line_number);
        return EXIT_SUCCESS;
    }
    if (parse_cmd && path) {
        parse(path);
        return EXIT_SUCCESS;
    }
    if (list_recursive_cmd && path) {
        listDirRecursive(path, name_starts_with_filter, has_perm_execute);
        return EXIT_SUCCESS;
    }

    if ((list_cmd) && path) {
        listDir(path, name_starts_with_filter, has_perm_execute);
        return EXIT_SUCCESS;
    }

    if (findall_cmd && path) {
        findall(path);
        return EXIT_SUCCESS;
    }
    return EXIT_SUCCESS;
}
