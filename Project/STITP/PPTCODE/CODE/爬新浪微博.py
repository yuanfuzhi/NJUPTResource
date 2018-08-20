import pymysql.cursors
from selenium import webdriver #导入webdriver模块
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.common.action_chains import ActionChains
import time,re,random #导入time模块

from time import sleep, ctime
import threading
#import threads


#3 #100 #169  #201
start = [1258,1271,1281,1291,1301,1311]
end = [1270,1280,1290,1300,1310,1336]


def pa(user, password,number):
	browser = webdriver.Chrome() #打开chrome驱动
	browser.maximize_window()

	browser.get('https://weibo.cn/search/mblog?hideSearchFrame=&keyword=%E9%82%93%E7%B4%AB%E6%A3%8B%E3%80%8A%E5%96%9C%E6%AC%A2%E4%BD%A0%E3%80%8B+%40QQ%E9%9F%B3%E4%B9%90&page=2')
	time.sleep(2)
	elem = browser.find_element_by_id("loginName")
	elem.clear()
	elem.send_keys(user)
	time.sleep(1)
	elem = browser.find_element_by_id("loginPassword")
	elem.clear()
	elem.send_keys(password)
	time.sleep(1)
	browser.find_element_by_id("loginAction").click()
	time.sleep(80)


	for i in range(start[number],end[number]+1):
		sstr="C:/stitp/song"+str(i)+".txt"
		f = open(sstr, "a+",encoding='utf-8')
		sql = "SELECT * FROM forweibo WHERE number="+str(i)
		cur.execute(sql)
		result = cur.fetchall() 
		for row in result:
			songname=row[1]
			singer=row[2]
		print(songname+"  "+singer)
		url="https://weibo.cn/search/mblog?hideSearchFrame=&keyword="+singer+"《"+songname+"》 @QQ音乐&page=1"
		browser.get(url)
		time.sleep(2)
		try:
			count = browser.find_element_by_xpath("//*[@class='cmt']").text
		except Exception:
			print("失败  "+str(i))
			continue
		count = re.sub("\D", "", count)
		count = int(count)
		print("i="+str(i)+"  count="+str(count))
		yehao=0
		if count>1000:
			yehao=100
		else:
			yehao=int(count/10)
		for j in range (1,yehao+1):
			url="https://weibo.cn/search/mblog?hideSearchFrame=&keyword="+singer+"《"+songname+"》 @QQ音乐&page="+str(j)
			browser.get(url)
			time.sleep(1)
			html = browser.page_source
			f.write(html+'\n')
			time.sleep(10+random.randint(1,9))
		f.close()

if __name__ == '__main__':

	conn = pymysql.connect(host='106.14.222.202', port=3306, user='root', passwd='123456',database="STITP",charset='UTF8')
	cur=conn.cursor()                              #获取一个游标对象
	cur.execute("USE stitp")   #创建数据库

	user=['wv1576798yalan076@163.com','nnen8717134@163.com','ixzydfua69922@163.com','fvb752025@163.com','sfe74271879@163.com','jia5767185jiao@163.com']
	password=['0bmfmbfjy5','5viy5zy1dl','6g4xduh4f0','8h7dpf5p7b','5qs5or7xe7','0t0qu3j2wg']

	threads = []

	for i in range(1,7):
		t = threading.Thread(target=pa, args=(user[i-1], password[i-1],i-1))
		threads.append(t)

	for i in range(1,7):
		threads[i-1].start()

	for i in range(1,7):
		threads[i-1].join()