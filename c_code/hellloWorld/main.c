#include <stdio.h>
#include <stdlib.h>
#include<float.h>

int main(int aa, int arg[])
{
   /* jisuan();
    int param = 0;
    int param2= 0;
    float param3 = 0;
    printf("输入第一个数\n");
    scanf("%d",&param);

    printf("输入第一个数  %d\n",param);
    printf("输入第二数\n");
    scanf("%d",&param2);

    printf("输入第二个数  %d\n",param2);
    printf("输入第三数\n");
    scanf("%f",&param3);

    printf("输入第三个数  %f\n",param3);

    printf("%d / %d = %d\n",param,param2,param/param2);
    printf("%d / %d = %f\n",param,param2,param/param2);
    printf("%d / %f = %f\n",param,param3,param/param3);*/

int age =2;
  switch (age)
{
  case 2:
    printf("宝宝，你好 !");
    //跳出该次的执行， 如果不写则会继续判断下一行的条件是否满足 知道遇到 break 语句  该switch执行结束
    //break;
  case 6:
    printf("小朋友，你好 !");
   // break;
  case 12:
    printf("少年，你好 !");
   // break;
  case 16:
    printf("青少年，你好 !");
   // break;
  case 18:
    printf("成年人，你好 !");
   // break;
  case 67:
    printf("爷爷，你好 !");
   // break;
  default:// 以上条件都没有符合的执行这个条语句。   在没有break 语句的时候这句也会执行；
    printf("对你的年龄我还没有对应的问候方式 ");
    break;
}

    return 0;
}

/*void jisuan(){

    int num=0;
    for(int i =0;i<10;i++)
    {
        ++num;
    }


    printf("%d",num);
}
*/
