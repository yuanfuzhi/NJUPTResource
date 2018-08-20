#ifndef LIST_H_INCLUDED
#define LIST_H_INCLUDED
#include"Action.h"
#include<iostream>
#include<fstream>
using namespace std;
class List//����ṹ
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
    void ShowIOPlaceTimes();//�������ĳ����������ʾ
    void ShowServeTimes();//�����ŵĽӴ�ʱ�䲢��ʾ
    void ShoeIOReasonTimes();//����������ͳ�ƶ�Ӧ�����������ʾ
    void Sort();//������ʱ������
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
        //�����㷨����һ���½������������β��
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
//��˳������㷨�����½��Ľ�㰴��С�����˳�����������
{
    Action *e=new Action(na,ct,cn,il,ira,io,ol,iy,im,id,ih,imi,oy,om,od,oh,omi);
    if(head)
    {
        Action *p1=head;
        Action *p2;
        while(p1&&e->StayTime>p1->StayTime)//ȷ������λ��
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

void List::ReadFile()//���ļ��ж�ȡ����
{
    ifstream in("data.txt");
    if(!in)
    {
        cout<<"cannot open the file!\n";
        return ;
    }//���ļ�״̬�ж�
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
        //��ȡһ���½ڵ㲢����������
    }
    in.close();
}
void List::Save()//���浱ǰ�����ݣ������������д�뵱ǰ�ļ�
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
void List::Print()//��ӡ���������
{
    Action *p=head;
    while(p)
    {
        PrintOne(p);
        p=p->next;
    }
}
void List:: SearchInName(string name)
{//�����Ʋ��ң��������д�������ˣ��ʹ�ӡ������
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
    cout<<"����"<<name<<"�ļ�¼���£�,��ѡ���ѯ�����ڣ�\n";
    cin>>mon>>day;
    p=head;
    while(p)
    {
        if(p->name==name&&mon==p->inmonth&&p->inday==day)
        {
        PrintOne(p);
        cout<<"������Ҫ�޸ĵ�ʱ�䣺\n:";
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
        cout<<"�޸ĳɹ�!\n";
        return ;
        }
        p=p->next;
    }

}
void List::SearchInNumber(string number)
{//��֤����Ų��ң������ڣ��ʹ�ӡ
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
    cout<<"����"<<number<<"�ļ�¼���£�,��ѡ���ѯ�����ڣ�\n";
    cin>>mon>>day;
    p=head;
    while(p)
    {
        if(p->CredentialNumber==number&&mon==p->inmonth&&p->inday==day)
        {
        PrintOne(p);
        cout<<"������Ҫ�޸ĵ�ʱ�䣺\n:";
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
        cout<<"�޸ĳɹ�!\n";
        return ;
        }
        p=p->next;
    }
}

void List:: ShowIOPlaceTimes()
{//ͳ�Ƹ�������������
    string a="����";
    string b="����";
    string c="����";
    string d="����";
    int a1=0,a2=0,b1=0,b2=0,c1=0,c2=0,d1=0,d2=0;//��������Ӧ������ʼ��
    Action*p=head;
    while(p)//����ǰ�ڵ��Ӧ�ĳ�������+1��
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
    cout<<"���ŵĳ���������£�\n";
    cout<<"���Ž���:"<<a1<<"��,�����뿪"<<a2<<"�Σ�\n";
    cout<<"���Ž���:"<<b1<<"��,�����뿪"<<b2<<"�Σ�\n";
    cout<<"���Ž���:"<<c1<<"��,�����뿪"<<c2<<"�Σ�\n";
    cout<<"���Ž���:"<<d1<<"��,�����뿪"<<d2<<"�Σ�\n";
}
void List:: ShoeIOReasonTimes()
{
    string a="����";
    string b="����";
    string c="ѧϰ";
    string d="����";
    int a1=0,b1=0,c1=0,d1=0;
    Action *p=head;
    while(p)////����ǰ�ڵ��Ӧ�ĳ�������+1��
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
    cout<<"�����ɶ�Ӧ�ĳ����������:\n";
    cout<<"��Ϊ��������������"<<a1<<"��\n";
    cout<<"��Ϊ���ʶ���������"<<b1<<"��\n";
    cout<<"��Ϊѧϰ����������"<<c1<<"��\n";
    cout<<"��Ϊ�����������������"<<d1<<"��\n";
}

void List:: ShowServeTimes()
{
    string a="�޹ܿ�";
    string b="������";
    string c="ͼ���";
    int a1=0,b1=0,c1=0,d1=0;
    Action *p=head;
    while(p)//����ǰ�ڵ��Ӧ�Ĳ��ŵ��д�ʱ����ϵ�ǰ�ڵ������ʱ�䣻
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
    cout<<"�����ŵĽӴ�ʱ�����£�����λ�����ӣ�\n";
    cout<<"�޹ܿƹ��ƽӴ�"<<a1<<"����\n";
    cout<<"���������ƽӴ�"<<b1<<"����\n";
    cout<<"ͼ��ݹ��ƽӴ�"<<c1<<"����\n";
    cout<<"�������Ź��ƽӴ�"<<d1<<"����\n";
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
        //���ļ��ж�ȡһ����¼����˳����������С�
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
    }//�������е�����д���ļ���
    out.close();
    cout<<"����ɹ���\n";


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
        cout<<"ɾ���ɹ�!\n";
    }
    else
    {
        p=q->next;
        q->next=p->next;
        cout<<"ɾ���ɹ�!\n";
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
