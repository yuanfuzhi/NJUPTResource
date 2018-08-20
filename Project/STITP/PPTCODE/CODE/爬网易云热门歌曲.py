import pymysql.cursors
from selenium import webdriver #导入webdriver模块
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.common.action_chains import ActionChains
import time #导入time模块

browser = webdriver.Chrome() #打开chrome驱动
browser.maximize_window()

conn = pymysql.connect(host='106.14.222.202', port=3306, user='root', passwd='123456',database="STITP",charset='UTF8')
cur=conn.cursor()                              #获取一个游标对象
cur.execute("USE stitp")   #创建数据库

fr = open("C:/python/popularsong.txt")

success_num=410
for i in range(1,1147):
	fr.readline()
while 1:
	url=fr.readline()
	if url=='end':
		break

	browser.get(url) 
	time.sleep(2)

	iframe = browser.find_element_by_id("g_iframe");
	browser.switch_to_frame(iframe)

	popularity = browser.find_element_by_xpath("//*[@class='sub s-fc3']/span").text;
	popularity = int(popularity)
	print(popularity)

	if popularity>15000:
		id = browser.find_element_by_xpath("//*[@id='content-operation']").get_attribute("data-rid")
		music_name = browser.find_element_by_class_name("f-ff2").text;
		music_name = music_name[0:40]
		singer = browser.find_element_by_xpath("//*[@class='cnt']/p[1]/span").get_attribute("title");
		singer = singer[0:30]

		sql = "INSERT INTO forweibo (id,songname, singer, hotdegree,number) VALUES ( '%s','%s', '%s','%d','%d')"
		data = (id,music_name, singer,popularity,success_num+1)
		# cur.execute(sql % data)
		# conn.commit()
		# print('成功插入', type,'  ',number, '条数据')
		# print("%s  %s  %s  %d"%(id,music_name, singer, popularity));
		# success_num=success_num+1
		try:
			cur.execute(sql % data)
			conn.commit()
			print('成功插入', success_num+1, '条数据')
			print("%s  %s  %s  %d"%(id,music_name, singer, popularity));
			success_num=success_num+1
		except Exception:
			print('插入失败')

