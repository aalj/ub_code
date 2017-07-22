#include<stdio.h>
#include "head.h"
int main(int arg,int *argArray[])
{
        int age = 10;
        int *pointer = &age;
        printf("指针地址 %p\n",pointer);
        printf("调用函数\n");
        firstPointer(pointer);
        printf("---------------------------------------------------\n");
        
        int array[5]={12,11,13,14};
        int arraySize = 5;
        int *result = 0;
        printf("调用有返回值的函数\n");
        printf("\n");
        printf("计算平均值%lf\n",arrayAverageReturnDouble(array,arraySize));

        printf("\n");
        printf("调用带有指针的函数\n");
//       arrayAverageNotReturn(array,arraySize,result);
//     printf("输出指针指向的内容%d",*result);
//      printf("输出指针指向的内容%p",result);
        printf("\n");
        printf("----------------------------------------------------\n");
        printf("数组的复制\n");
       int copyArrayParam[4] = {0};
       copyArray(array,copyArrayParam,4);
       int i = 0 ; 
       for(i =0 ;i<4;i++)
       {
            printf("%d   ",copyArrayParam[i]);
       }

        printf("\n");

}


void firstPointer(int *pointer)
{
        printf("指针指向的内容 %d \n",*pointer);
}

void arrayAverageNotReturn(int array[], int arraySize,int *result)
{
        int i = 0 ;
        int count = 0;
        for(i=0;i<arraySize;i++)
        {
                count += array[i];
        }
        double relust = count/arraySize;
        *result = relust;
}


double arrayAverageReturnDouble(int array[], int arraySize)
{
        int i = 0 ;
        int count  = 0;
        for (i = 0; i < arraySize; i++)
        {
                count += array[i];
        }

        return count/arraySize;
}



void copyArray(int array[],int copyArray[],int arraySize)
{
        int i = 0 ; 
        for (i = 0 ; i < arraySize; i++)
        {
                copyArray[i]=array[i];
        }
}


