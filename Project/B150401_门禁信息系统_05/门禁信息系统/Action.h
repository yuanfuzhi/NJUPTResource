#ifndef ACTION_H_INCLUDED
#define ACTION_H_INCLUDED
#include<string>
using namespace std;
class Action//�Ž���¼��
{
public:
    string name;//����
    string CredentialType;//֤�����
    string CredentialNumber;//֤������
    string InLoc;//�����
    string InReason;//����
    string InOffice;//�Ӵ���
    string OutLoc;//�뿪��
    int inyear;//����ʱ��
    int inmonth;
    int inday;
    int inhour;
    int inminute;
    int outyear;//�뿪ʱ��
    int outmonth;
    int outday;
    int outhour;
    int outminute;
    int StayTime;//����ʱ��
    Action(string na,string ct,string cn,string il,string ira,string io,string ol,
           int iy,int im,int id,int ih,int imi,int oy,int om,int od,int oh,int omi)
    {
        name=na;
        CredentialType=ct;
        CredentialNumber=cn;
        InLoc=il;
        InReason=ira;
        InOffice=io;
        OutLoc=ol;
        inyear=iy;
        inmonth=im;
        inday=id;
        inhour=ih;
        inminute=imi;
        outyear=oy;
        outmonth=om;
        outday=od;
        outhour=oh;
        outminute=omi;
        StayTime=oh*60+omi-ih*60-imi;
    }//���캯��
    Action *next;//ָ����һ������ָ��
    friend class List;//��Ԫ��
};
#endif // ACTION_H_INCLUDED
