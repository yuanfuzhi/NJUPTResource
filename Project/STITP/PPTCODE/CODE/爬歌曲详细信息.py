import pymysql.cursors
from selenium import webdriver #导入webdriver模块
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.common.action_chains import ActionChains
import time #导入time模块


conn = pymysql.connect(host='106.14.222.202', port=3306, user='root', passwd='123456',database="STITP",charset='UTF8')
cur=conn.cursor()                              #获取一个游标对象
cur.execute("USE stitp")   #创建数据库

browser = webdriver.Chrome() #打开chrome驱动
browser.maximize_window()


for type in range(1,14):
	for number in range(1,301):
		sql = "SELECT ID FROM songlist WHERE TYPE="+str(type)+" AND NUMBER="+str(number)
		cur.execute(sql)
		result = cur.fetchall() 
		
		id=result[0][0]
		id=id.strip()
		browser.get("http://music.163.com/#/song?id="+str(id)) 
		time.sleep(2)
		iframe = browser.find_element_by_id("g_iframe");
		browser.switch_to_frame(iframe)
		
		music_name = browser.find_element_by_class_name("f-ff2").text;
		music_name = music_name[0:40]

		singer = browser.find_element_by_xpath("//*[@class='cnt']/p[1]/span").get_attribute("title");
		singer = singer[0:30]

		ablum = browser.find_element_by_xpath("//*[@class='cnt']/p[2]/a").get_attribute("href");
		ablum = ablum[30:]

		popularity = browser.find_element_by_xpath("//*[@class='sub s-fc3']/span").text;

		sql = "INSERT INTO songdetails (ID,SongName, Author, Album,Type,Number,Popularity) VALUES ( '%s','%s', '%s', '%s', '%d', '%d','%d')"
		data = (id,music_name, singer, ablum,type,number,int(popularity))

		# cur.execute(sql % data)
		# conn.commit()
		try:
			cur.execute(sql % data)
			conn.commit()
			print('成功插入', type,'  ',number, '条数据')
			print("%s  %s  %s  %s  %d  %d  %d"%(id,music_name, singer, ablum,type,number,int(popularity)));
		except Exception:
			print('插入失败')

		# print(result)
		# print(number)
		# print(music_name)
		# print(singer)
		# print(ablum)
		# print(popularity)