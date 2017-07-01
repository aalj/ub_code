#include <stdio.h>
#include <stdlib.h>
#include<float.h>
int mRandom(int x,int y);

int main1(int aa, int arg[])
{
    int index = 0;
    int max=10;
    int min=0;
    for(index = 0; index<100;index++)
    {
        printf("第%d次随机数是%d\n",index,mRandom(max,min));
    }
    printf("return");
    return 0;
}

int main()
{
   //srand(time(NULL));
   srand(time(NULL));
    for (int i=0; i<10; i++)
    {
        printf("%d ", rand()%10);
    }

    for (int i=0; i<10; i++)
    {
        printf("%d \n", mRandom(100,1));
    }


  /* 程序的循环部分， 如果用户没猜中数字，就一直进行循环 */
}



int  mRandom(int max,int min)
    {
        //srand(time(NULL));
        printf("%d\n",time(NULL));
      return  (rand()%(max-min+1)+min);
      //return  (rand()%(max-min+1)+min);
    }
