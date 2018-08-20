#ifndef ACTION_H_INCLUDED
#define ACTION_H_INCLUDED
#include<string>
using namespace std;
class Action//门禁记录类
{
public:
    string name;//姓名
    string CredentialType;//证件编号
    string CredentialNumber;//证件类型
    string InLoc;//进入点
    string InReason;//事由
    string InOffice;//接待人
    string OutLoc;//离开点
    int inyear;//进入时间
    int inmonth;
    int inday;
    int inhour;
    int inminute;
    int outyear;//离开时间
    int outmonth;
    int outday;
    int outhour;
    int outminute;
    int StayTime;//滞留时间
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
    }//构造函数
    Action *next;//指向下一个结点的指针
    friend class List;//友元类
};
#endif // ACTION_H_INCLUDED
