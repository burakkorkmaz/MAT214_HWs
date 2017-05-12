#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define ARGUMENTS 5
#define ERROR -1

//Type Definitions
typedef double **Matrix;
typedef double *Row;
typedef double *Col;

//Function Prototypes

Matrix allocateMatrix(int n);

double* allocateRowCol(int n);

void freeMatrix(Matrix M, int n);

void printMatrix(Matrix M, int n);
void printColumn(Col column);

Col scaleFactor(Matrix A, int size); // s_i & size -> n-1

void partialPivot(Matrix A, int n, Col scaleArr); // a_ij / s_p

void swapRows(Row *mRow, Row *nRow);
void eliminate(Matrix M, int n);


void applyGaussian(Matrix A, int n);




// $./solver ­i system.txt ­m GESP

int main(int argc, char **argv) {

    FILE *fp;
    int i,j;
    int matrixSize = 0;
    Matrix A;
    char ch = ' ';
    double element = 0.0;


    if (argc != ARGUMENTS) {
        printf("Usage: ./solver -i <filename> -m <Method>\n Methods:\n1. GESP\n2. JCB\n");
        return ERROR;
    }


    fp = fopen(argv[2], "r");
    if (fp == NULL) {
        perror("File Open Error");
        return ERROR;
    }

    while (ch != '\n' && fscanf(fp, "%c", &ch)!= EOF) {

        if (ch == ',') {
            ++matrixSize;
        }
    }
    rewind(fp);
    if (matrixSize < 1) {
        printf("Error: Inappropriate Matrix!\n");
        return ERROR;
    }

    printf("Augmented Matrix []_%dx%d \n", matrixSize, matrixSize + 1);

    A = allocateMatrix(matrixSize);

    for (i = 0; i < matrixSize; ++i) {
        for (j = 0; j <= matrixSize && fscanf(fp, "%lf,", &element) != EOF ; ++j) {
            A[i][j] = element;
        }
    }
    printMatrix(A, matrixSize);



    applyGaussian(A, matrixSize);
    fclose(fp);
    return 0;
}

Matrix allocateMatrix(int n) {
    Matrix A;
    int i;
    A = (Matrix) malloc(n * sizeof(Row));
    if (!A) {
        perror("\nMatrix Allocation Error\n");
        exit(1);
    }

    for (i = 0; i < n; ++i) {
        A[i] = (Row) malloc((n + 1) * sizeof(double));
        if (!A[i]) {
            perror("\nMatrix Allocation Error\n");
            exit(1);
        }
    }
    return A;
}

void freeMatrix(Matrix M, int n) {
    int i;
    for (i = 0; i < n; ++i)
        free(M[i]);
    free(M);
}

double * allocateRowCol(int n) {
    Col a;

    a = (Col) malloc(n * sizeof(double));
    if (!a) {
        perror("\nMatrix Allocation Error\n");
        exit(1);
    }

    return a;
}

void printMatrix(Matrix M, int n) {

    for (int i = 0; i < n; ++i) {
        printf("| ");
        for (int j = 0; j <= n; ++j) {
            printf("%5.2f ", M[i][j]);
        }
        printf("|\n");
    }
    printf("\n");
}

void printColumn(Col column) {
    printf("[");
    for (int i = 0; column[i] != '\0' ; ++i) {
        printf("%.2f ",column[i]);
    }
    printf("]\n\n");
}

Col scaleFactor(Matrix A, int size) {

    Col scale;
    double max;
    double element;
    int i, j = 0;

    scale = allocateRowCol(size);

    for (i = 0; i < size; ++i) {
        max = fabs(A[i][0]);
        for (j = 0; j < size; ++j) {
            element = fabs(A[i][j]);
//            printf("ele:%f\n",element);
            if (max < element )
                max = element;
        }
        scale[i] = max;
    }

    return scale;
}

void partialPivot(Matrix A, int n, Col scaleArr) {
    double temp;
    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < n; ++j) {
            if (scaleArr[i] == 0) {
                printf("Error: No Unique Solution!\n");
                exit(1);
            } else if (fabs(A[i][j] / scaleArr[i]) > fabs(A[j][j] / scaleArr[j])) {
                swapRows(&A[i], &A[j]);
                temp = scaleArr[i];
                scaleArr[i] = scaleArr[j];
                scaleArr[j] = temp;
            }
        }
    }
}

void swapRows(Row *mRow, Row *nRow) {
    Row temp = *mRow;
    *mRow = *nRow;
    *nRow = temp;

}

void eliminate(Matrix A, int n) {

    int i,j,k;
    double m;

    for(k = 0; k < n; ++k)
    {
        for(i = k+1; i < n; ++i)
        {
            m =  A[i][k] / A[k][k];
            for(j = k; j < n; ++j)
            {
                A[i][j] -= m * A[k][j];
            }
        }
    }

}

void applyGaussian(Matrix A, int n) {
    Col scale = scaleFactor(A,n);
    printf("S = ");
    printColumn(scale);
    partialPivot(A,n,scale);
    eliminate(A,n);

    printf("\n");
    printMatrix(A, n);

    freeMatrix(A, n);
    free(scale);
}





