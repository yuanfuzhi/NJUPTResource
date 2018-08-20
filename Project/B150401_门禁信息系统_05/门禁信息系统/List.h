#ifndef LIST_H_INCLUDED
#define LIST_H_INCLUDED
#include"Action.h"
#include<iostream>
#include<fstream>
using namespace std;
class List//链表结构
{
public:
    List()
    {
        head=NULL;
        n=0;
    }
    ~List();
    bool Insert(string na,string ct,string cn,string il,string ira,string io,
        string ol,
    int iy,int im,int id,int ih,int imi,int oy,int om,int od,int oh,int omi);
    bool InsertOreder(string na,string ct,string cn,string il,string ira,string io,
        string ol,
        int iy,int im,int id,int ih,int imi,int oy,int om,int od,int oh,int omi);
    void ReadFile();
    void Save();
    void Print();
    void SearchInName(string name);
    void SearchInNumber(string number);
    void PrintOne(Action *p);
    void Delete(string name);
    void UpDate(string name);
    void ShowIOPlaceTimes();//各出入点的出入次数并显示
    void ShowServeTimes();//各部门的接待时间并显示
    void ShoeIOReasonTimes();//按出入事由统计对应出入次数并显示
    void Sort();//按滞留时间排序
    int n;
    Action *head;

};
List::~List()
{
    Action *p;
    while(head)
    {
        p=head->next;
        delete head;
        head=p;
    }
}
bool List::Insert(string na,string ct,string cn,string il,string ira,string io,
        string ol,
        int iy,int im,int id,int ih,int imi,int oy,int om,int od,int oh,int omi)
        //插入算法，把一个新建结点插入链表的尾部
{
    Action *e=new Action(na,ct,cn,il,ira,io,ol,iy,im,id,ih,imi,oy,om,od,oh,omi);
    if(head)
    {
        Action *p=head;
        while(p->next)
        {
            p=p->next;
        }
        e->next=NULL;
        p->next=e;
        n++;
    }
    else
    {
        e->next=NULL;
        head=e;
        n++;
    }
    return true;
}
bool List::InsertOreder(string na,string ct,string cn,string il,string ira,string io,
        string ol,
        int iy,int im,int id,int ih,int imi,int oy,int om,int od,int oh,int omi)
//按顺序插入算法，把新建的结点按由小到大的顺序插入链表中
{
    Action *e=new Action(na,ct,cn,il,ira,io,ol,iy,im,id,ih,imi,oy,om,od,oh,omi);
    if(head)
    {
        Action *p1=head;
        Action *p2;
        while(p1&&e->StayTime>p1->StayTime)//确定插入位置
        {
            p2=p1;
            p1=p1->next;
        }
        p2->next=e;
        e->next=p1;

    }
    else
    {
        e->next=NULL;
        head=e;
        n++;
    }
    return true;
}

void List::ReadFile()//从文件中读取数据
{
    ifstream in("data.txt");
    if(!in)
    {
        cout<<"cannot open the file!\n";
        return ;
    }//打开文件状态判断
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
    head=NULL;
    while(in>>na>>ct>>cn>>il>>ira>>io>>ol>>iy>>im>>id>>ih>>imi>>oy>>om>>od>>oh>>omi)
    {
        Insert(na,ct,cn,il,ira,io,ol,iy,im,id,ih,imi,oy,om,od,oh,omi);
        //读取一个新节点并插入链表中
    }
    in.close();
}
void List::Save()//保存当前的数据，把链表的内容写入当前文件
{
    ofstream out("Info_1.txt");
    if(!out)
    {
        cout<<"cannot open the file!\n";
        return ;
    }
    Action *p=head;
    string str;
    while(p)
    {
        str=p->name;
        out<<str<<' '<<p->CredentialType<<' '<<p->CredentialNumber<<' '<<p->InLoc<<' ';
        out<<p->InReason<<' '<<' '<<p->InOffice<<' '<<p->OutLoc;
        out<<' '<<p->inyear<<' '<<p->inmonth<<' '<<p->inday<<' '<<p->inhour<<' '<<p->inminute<<' ';
        out<<p->outyear<<' '<<p->outmonth<<' '<<p->outday<<' '<<p->outhour<<' '<<p->outminute<<endl;
        p=p->next;
    }
    out.close();
}
void List::Print()//打印链表的内容
{
    Action *p=head;
    while(p)
    {
        PrintOne(p);
        p=p->next;
    }
}
void List:: SearchInName(string name)
{//按名称查找，若链表中存在这个人，就打印出来；
    Action *p=head;
    int mon,day;
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
    while(p)
    {
        if(p->name==name)
        {
        PrintOne(p);
        }
        p=p->next;
    }
    cout<<"关于"<<name<<"的记录如下：,请选择查询的日期：\n";
    cin>>mon>>day;
    p=head;
    while(p)
    {
        if(p->name==name&&mon==p->inmonth&&p->inday==day)
        {
        PrintOne(p);
        cout<<"请输入要修改的时间：\n:";
        cin>>iy>>im>>id>>ih>>imi>>oy>>om>>od>>oh>>omi;
        p->inyear=iy;
        p->inmonth=im;
        p->inday=id;
        p->inhour=ih;
        p->inminute=imi;
        p->outyear=oy;
        p->outmonth=om;
        p->outday=od;
        p->outhour=oh;
        p->outminute=omi;
        p->StayTime=oh*60+omi-ih*60-imi;
        PrintOne(p);
        cout<<"修改成功!\n";
        return ;
        }
        p=p->next;
    }

}
void List::SearchInNumber(string number)
{//按证件编号查找，若存在，就打印
    Action *p=head;
    int mon,day;
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
    while(p)
    {
        if(p->CredentialNumber==number)
        {
        PrintOne(p);
        }
        p=p->next;
    }
    cout<<"关于"<<number<<"的记录如下：,请选择查询的日期：\n";
    cin>>mon>>day;
    p=head;
    while(p)
    {
        if(p->CredentialNumber==number&&mon==p->inmonth&&p->inday==day)
        {
        PrintOne(p);
        cout<<"请输入要修改的时间：\n:";
        cin>>iy>>im>>id>>ih>>imi>>oy>>om>>od>>oh>>omi;
        p->inyear=iy;
        p->inmonth=im;
        p->inday=id;
        p->inhour=ih;
        p->inminute=imi;
        p->outyear=oy;
        p->outmonth=om;
        p->outday=od;
        p->outhour=oh;
        p->outminute=omi;
        p->StayTime=oh*60+omi-ih*60-imi;
        PrintOne(p);
        cout<<"修改成功!\n";
        return ;
        }
        p=p->next;
    }
}

void List:: ShowIOPlaceTimes()
{//统计个出入点进出次数
    string a="东门";
    string b="南门";
    string c="西门";
    string d="北门";
    int a1=0,a2=0,b1=0,b2=0,c1=0,c2=0,d1=0,d2=0;//各出入点对应次数初始化
    Action*p=head;
    while(p)//将当前节点对应的出入点次数+1；
    {
        if(p->InLoc==a)
            a1++;
        else
        {
            if(p->InLoc==b)
                b1++;
            else
            {
                if(p->InLoc==c)
                    c1++;
                else
                    d1++;
            }
        }
        if(p->OutLoc==a)
            a2++;
        else
        {
            if(p->OutLoc==b)
                b2++;
            else
            {
                if(p->OutLoc==c)
                    c2++;
                else
                    d2++;
            }
        }
        p=p->next;
    }
    cout<<"各门的出入情况如下：\n";
    cout<<"东门进入:"<<a1<<"次,东门离开"<<a2<<"次；\n";
    cout<<"南门进入:"<<b1<<"次,南门离开"<<b2<<"次；\n";
    cout<<"西门进入:"<<c1<<"次,西门离开"<<c2<<"次；\n";
    cout<<"北门进入:"<<d1<<"次,北门离开"<<d2<<"次；\n";
}
void List:: ShoeIOReasonTimes()
{
    string a="工作";
    string b="访问";
    string c="学习";
    string d="其他";
    int a1=0,b1=0,c1=0,d1=0;
    Action *p=head;
    while(p)////将当前节点对应的出入点次数+1；
    {
        if(p->InReason==a)
            a1++;
        else
        {
            if(p->InReason==b)
                b1++;
            else
            {
                if(p->InReason==c)
                    c1++;
                else
                    d1++;
            }
        }
        p=p->next;
    }
    cout<<"各事由对应的出入次数如下:\n";
    cout<<"因为工作而进出共计"<<a1<<"次\n";
    cout<<"因为访问而进出共计"<<b1<<"次\n";
    cout<<"因为学习而进出共计"<<c1<<"次\n";
    cout<<"因为其他事情而进出共计"<<d1<<"次\n";
}

void List:: ShowServeTimes()
{
    string a="宿管科";
    string b="保卫处";
    string c="图书馆";
    int a1=0,b1=0,c1=0,d1=0;
    Action *p=head;
    while(p)//将当前节点对应的部门的招待时间加上当前节点的滞留时间；
    {
        if(p->InOffice==a)
            a1+=p->StayTime;
        else
        {
            if(p->InOffice==b)
                b1+=p->StayTime;
            else
            {
                if(p->InOffice==c)
                    c1+=p->StayTime;
                else
                    d1+=p->StayTime;
            }
        }
        p=p->next;
    }
    cout<<"各部门的接待时间如下：（单位：分钟）\n";
    cout<<"宿管科共计接待"<<a1<<"分钟\n";
    cout<<"保卫处共计接待"<<b1<<"分钟\n";
    cout<<"图书馆共计接待"<<c1<<"分钟\n";
    cout<<"其他部门共计接待"<<d1<<"分钟\n";
}
void List::Sort()
{
    ifstream in("data.txt");
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
    head=NULL;
    while(in>>na>>ct>>cn>>il>>ira>>io>>ol>>iy>>im>>id>>ih>>imi>>oy>>om>>od>>oh>>omi)
    {
        InsertOreder(na,ct,cn,il,ira,io,ol,iy,im,id,ih,imi,oy,om,od,oh,omi);
        //从文件中读取一条记录，按顺序插入链表中。
    }
    in.close();
    ofstream out("Info_2.txt");
    if(!out)
    {
        cout<<"cannot open the file!\n";
        return ;
    }
    Action *p2=head;
    while(p2)
    {
        cout<<p2->name<<' '<<p2->CredentialNumber<<' '<<p2->StayTime<<endl;
        out<<p2->name<<' '<<p2->CredentialNumber<<' '<<p2->StayTime<<endl;
        p2=p2->next;
    }//将链表中的内容写入文件中
    out.close();
    cout<<"排序成功！\n";


}
void List:: Delete(string name)
{
    Action*p=head,*q=head;
    for(int j=0;j<n;j++)
    {
        if(q->next->name==name)
            break;
        q=q->next;
    }
    if(p->name==name)
    {
        head=head->next;
        cout<<"删除成功!\n";
    }
    else
    {
        p=q->next;
        q->next=p->next;
        cout<<"删除成功!\n";
    }
    delete p;
    n--;
    return ;
}
void List::PrintOne(Action *p)
{
    printf("%6s%8s%20s",p->name.c_str(),p->CredentialType.c_str(),p->CredentialNumber.c_str());
    cout<<' '<<p->InLoc<<' '<<p->InReason<<' '<<' '<<p->InOffice<<' '<<p->OutLoc;
    cout<<' '<<p->inyear<<' '<<p->inmonth<<' '<<p->inday<<' '<<p->inhour<<' '<<p->inminute<<' ';
    cout<<p->outyear<<' '<<p->outmonth<<' '<<p->outday<<' '<<p->outhour<<' '<<p->outminute<<endl;
}
#endif // LIST_H_INCLUDED
