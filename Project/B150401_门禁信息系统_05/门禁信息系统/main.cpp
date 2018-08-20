#include <iostream>
#include"List.h"
using namespace std;
List list1;
List list2;
void menuBase()
{
	printf("%%%%%%%% 1. �����Ž���¼ %%%%%%%%\n");
	printf("%%%%%%%% 2. ɾ���Ž���¼ %%%%%%%%\n");
	printf("%%%%%%%% 3. ���浱ǰ���� %%%%%%%%\n");
	printf("%%%%%%%% 0. �����ϲ�˵� %%%%%%%%\n");
}//���������˵�
void ManageBase()//�����������ܵ�ʵ��
{
    int choice;
    do
    {
        menuBase();
        cout<<"��ѡ��Ҫִ�е����\n";
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
                cout<<"���������� ֤������ ֤������:\n";
                cin>>na>>ct>>cn;
                cout<<"���������ص㣬�������ɣ��Ӵ����� ��ȥ�ص�:\n";
                cin>>il>>ira>>io>>ol;
                cout<<"���������ʱ����뿪ʱ�䣨������ʱ�֣���\n";
                cin>>iy>>im>>id>>ih>>imi>>oy>>om>>od>>oh>>omi;
                list1.Insert(na,ct,cn,il,ira,io,ol,iy,im,id,ih,imi,oy,om,od,oh,omi);
                cout<<"����ɹ���\n";
                break;
            }
        case 2:
            {
                string name;
                cout<<"������Ҫɾ����������\n";
                cin>>name;
                list1.Delete(name);
                break;
            }
        case 3:
            {
                list1.Save();
                cout<<"����ɹ���\n";
            }
        case 0:
            break;
        }

    }while(choice);
}
void menuCount()//ͳ�ƹ��ܵ�ʵ��
{
	printf("&&&&&&&& 1. ��ʾ������������� &&&&&&&&\n");
	printf("&&&&&&&& 2. ��ʾ�����ŽӴ�ʱ�� &&&&&&&&\n");
	printf("&&&&&&&& 3. ��ʾ�����ɶ�Ӧ�ĳ������ &&&&&&&&\n");
    printf("@@@@@@@@ 4. ��������ʱ������   @@@@@@@@\n");
	printf("&&&&&&&& 0. �����ϲ�˵� &&&&&&&&\n");
}
void ManageCount()
{
    int choice;
    do
    {
        menuCount();
        cout<<"��������Ҫִ�еĲ���";
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
    printf("@@@@@@@@ 1. ��ѯ���� @@@@@@@@\n");
	printf("@@@@@@@@ 2. ��ѯ֤����� @@@@@@@@\n");
	printf("@@@@@@@@ 0. �����ϲ�˵� @@@@@@@@\n");
}
void ManageSearch()
//��ѯ���ܵ�ʵ��
{
    int choice;
    do
    {
        menusearch();
        cout<<"������Ҫִ�еĲ�����\n";
        cin>>choice;
        switch(choice)
        {
        case 1:
            {
                string name;
                cout<<"������Ҫ��ѯ��������\n";
                cin>>name;
                list1.SearchInName(name);
                break;
            }
        case 2:
            {
                string number;
                cout<<"������Ҫ��ѯ�ı�ţ�\n";
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
    printf("%6s%8s%20s","����","֤������","֤�����");
    cout<<' '<<"���"<<' '<<"���� "<<' '<<"���� "<<' '<<"����"<<' ';
    printf("%13s%13s","����ʱ��","�뿪ʱ��\n");

}
//��ӡ����
void menu()
{
    printf("****��ӭʹ���Ž���Ϣϵͳ*********\n");
    printf("*************��ѡ��Ҫִ�еĲ�����\n");
	printf("******** 1. ��ʾ�Ž���Ϣ ********\n");
	printf("******** 2. ������Ϣ���� ********\n");
	printf("******** 3. �Ž���Ϣͳ��********\n");
	printf("******** 4. ����������ѯ���޸� ********\n");
	printf("******** 0. �˳�         ********\n");
}
//��ӡ���˵�
void  ManageMenu(int choice)//���˵�����ʵ��
{
    switch(choice)
    {
        case 1:
        {
            PrintHead();
            list1.Print();
            cout<<"�ܹ��У�"<<list1.n<<"����¼\n"<<endl;
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
        cout<<"��ѡ����Ҫִ�еĲ�����\n";
        cin>>choice;
        ManageMenu(choice);
    }while(choice);
    return 0;
}
