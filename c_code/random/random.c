#include<stdio.h>
#include<stdlib.h>
#include<time.h>
int mRandom(int x, int y );


/**

这是一个关于随机数的程序
*/
void main(int a , int arg[])
    {
    
        srand(time(NULL));
        //random();
        int index =  0 ; 
        int max=10;
        int min=0;
        for(index = 0; index<100;index++)
        {
            printf("第%d次随机数是%d\n",index,mRandom(max,min));
        }
        printf("hello world\n");
    }


int  mRandom(int max,int min)
    {
        //printf("%l",time(NULL));
      return  (rand()%(max-min+1)+min);
    }
