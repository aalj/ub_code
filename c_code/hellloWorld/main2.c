#include<stdio.h>
#include<stdlib.h>
#include<time.h>
//int mRandom(int x, int y );


/**

这是一个关于随机数的程序
*/
void main3(int a , int arg[])
    {
        //random();
        int index =  0 ;
        int max=10;
        int min=1;
        for(index = 0; index<100;index++)
        {
            printf("第  次随机数是 %d \n",index,mRandom2(max,min));
        }
        printf("hello world\n");
        return 0;
    }


int  mRandom2(int max,int min)
    {
        srand(time(NULL));
        //printf("%l",time(NULL));
      return  (rand()%(max-min+1)+min);
    }
