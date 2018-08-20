#include <iostream>
#include"List.h"
using namespace std;
List list1;
List list2;
void menuBase()
{
	printf("%%%%%%%% 1. 插入门禁纪录 %%%%%%%%\n");
	printf("%%%%%%%% 2. 删除门禁纪录 %%%%%%%%\n");
	printf("%%%%%%%% 3. 保存当前操作 %%%%%%%%\n");
	printf("%%%%%%%% 0. 返回上层菜单 %%%%%%%%\n");
}//基本操作菜单
void ManageBase()//基本操作功能的实现
{
    int choice;
    do
    {
        menuBase();
        cout<<"请选择要执行的命令：\n";
        cin>>choice;
        switch(choice)
        {
        case 1:
            {
                string na;
                string ct;
                string cn;
                string il;
                string ira;
                string io;
                string ol;
                int iy;
                int im;
                int id;
                int ih;
                int imi;
                int oy;
                int om;
                int od;
                int oh;
                int omi;
                cout<<"请输入姓名 证件类型 证件号码:\n";
                cin>>na>>ct>>cn;
                cout<<"请输入进入地点，进入事由，接待部门 出去地点:\n";
                cin>>il>>ira>>io>>ol;
                cout<<"请输入进入时间和离开时间（年月日时分）：\n";
                cin>>iy>>im>>id>>ih>>imi>>oy>>om>>od>>oh>>omi;
                list1.Insert(na,ct,cn,il,ira,io,ol,iy,im,id,ih,imi,oy,om,od,oh,omi);
                cout<<"插入成功！\n";
                break;
            }
        case 2:
            {
                string name;
                cout<<"请输入要删除的姓名：\n";
                cin>>name;
                list1.Delete(name);
                break;
            }
        case 3:
            {
                list1.Save();
                cout<<"保存成功！\n";
            }
        case 0:
            break;
        }

    }while(choice);
}
void menuCount()//统计功能的实现
{
	printf("&&&&&&&& 1. 显示各出入点出入次数 &&&&&&&&\n");
	printf("&&&&&&&& 2. 显示各部门接待时间 &&&&&&&&\n");
	printf("&&&&&&&& 3. 显示各事由对应的出入次数 &&&&&&&&\n");
    printf("@@@@@@@@ 4. 按总滞留时间排序   @@@@@@@@\n");
	printf("&&&&&&&& 0. 返回上层菜单 &&&&&&&&\n");
}
void ManageCount()
{
    int choice;
    do
    {
        menuCount();
        cout<<"请输入你要执行的操作";
        cin>>choice;
        switch(choice)
        {
        case 1:
            {
                list1.ShowIOPlaceTimes();
                break;
            }
        case 2:
            {
                list1.ShowServeTimes();
                break;
            }
        case 3:
            {
                list1.ShoeIOReasonTimes();
                break;
            }
        case 4:
            {
                list2.Sort();
                break;
            }
        case 0:
            break;
        }

    }while(choice);
}

void menusearch()
{
    printf("@@@@@@@@ 1. 查询姓名 @@@@@@@@\n");
	printf("@@@@@@@@ 2. 查询证件编号 @@@@@@@@\n");
	printf("@@@@@@@@ 0. 返回上层菜单 @@@@@@@@\n");
}
void ManageSearch()
//查询功能的实现
{
    int choice;
    do
    {
        menusearch();
        cout<<"请输入要执行的操作：\n";
        cin>>choice;
        switch(choice)
        {
        case 1:
            {
                string name;
                cout<<"请输入要查询的姓名：\n";
                cin>>name;
                list1.SearchInName(name);
                break;
            }
        case 2:
            {
                string number;
                cout<<"请输入要查询的编号：\n";
                cin>>number;
                list1.SearchInNumber(number);
                break;
            }
        case 0:
            break;
        }

    }while(choice);
}
void PrintHead()
{
    printf("%6s%8s%20s","姓名","证件类型","证件编号");
    cout<<' '<<"入点"<<' '<<"事由 "<<' '<<"部门 "<<' '<<"出口"<<' ';
    printf("%13s%13s","进入时间","离开时间\n");

}
//打印标题
void menu()
{
    printf("****欢迎使用门禁信息系统*********\n");
    printf("*************请选择要执行的操作：\n");
	printf("******** 1. 显示门禁信息 ********\n");
	printf("******** 2. 基本信息管理 ********\n");
	printf("******** 3. 门禁信息统计********\n");
	printf("******** 4. 根据条件查询并修改 ********\n");
	printf("******** 0. 退出         ********\n");
}
//打印主菜单
void  ManageMenu(int choice)//主菜单管理实现
{
    switch(choice)
    {
        case 1:
        {
            PrintHead();
            list1.Print();
            cout<<"总共有："<<list1.n<<"条记录\n"<<endl;
            break;
        }
        case 2:
            {
                ManageBase();
                break;
            }
        case 3:
            {
                ManageCount();
                break;
            }
        case 4:
            {
                ManageSearch();
                break;
            }
        case 0:
            break;

    }
}
int main()
{
    int choice;
    list1.ReadFile();
    do
    {
        menu();
        cout<<"请选择你要执行的操作：\n";
        cin>>choice;
        ManageMenu(choice);
    }while(choice);
    return 0;
}
