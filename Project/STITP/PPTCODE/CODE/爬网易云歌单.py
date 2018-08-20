# -*-coding=utf-8-*-
from selenium import webdriver #导入webdriver模块
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver.common.action_chains import ActionChains
import time #导入time模块
import pymysql.cursors


connect = pymysql.connect("localhost","root","","STITP",charset='utf8' )
cursor = connect.cursor()


browser = webdriver.Chrome() #打开chrome驱动
browser.maximize_window()

listarr = ('758106745','799008144','786207109','782866014','732043074','731727738','709699149','798825958','775940264','755413393','760595064','779135629','763203574','747977586','777654259','689478660','769180416','758176730','783508687','736595860','646914244','739071258')
utfarr = ('%E9%99%88%E5%A5%95%E8%BF%85','%E6%9E%97%E4%BF%8A%E6%9D%B0','%E5%91%A8%E6%9D%B0%E4%BC%A6','%E7%8E%8B%E8%8F%B2','%E5%AD%99%E7%87%95%E5%A7%BF','%E6%80%80%E6%97%A7','%E6%B8%85%E6%96%B0','%E6%B5%AA%E6%BC%AB','%E6%80%A7%E6%84%9F','%E4%BC%A4%E6%84%9F','%E6%B2%BB%E6%84%88','%E6%94%BE%E6%9D%BE','%E5%AD%A4%E7%8B%AC','%E6%84%9F%E5%8A%A8','%E5%85%B4%E5%A5%8B','%E5%BF%AB%E4%B9%90','%E5%AE%89%E9%9D%99','%E6%80%9D%E5%BF%B5')
lablearr = ('怀旧','清新','清新','清新','浪漫','浪漫','性感','性感','伤感','治愈','治愈','治愈','放松','孤独','感动','兴奋','快乐','快乐','安静','安静','安静','思念')
listnum = [300,40,40,35,38,21,25,30,190,86,30,45,129,35,70,47,100,71,59,61,41,101]

succeed_num = 83
## 9,22
for k in range(3,9,1):
	lable = lablearr[k]
	if(k==4 or k==6 or k==8):
		succeed_num = 0
	if(k==3):
		ii=6
	else:
		ii=1
	## 1,listnum[k]+1
	for i in range(ii,listnum[k]+1):
		print("k=%d,i=%d"%(k,i))
		browser.get("http://music.163.com/#/playlist?id="+listarr[k])
		time.sleep(1)	
		# elem = browser.find_element_by_xpath("//*[@class='updn']/div[1]/a")
		# elem.click()

		iframe = browser.find_element_by_id("g_iframe");
		browser.switch_to_frame(iframe)
		#time.sleep(1)
		browser.execute_script("window.scrollBy(0,100000)")
		time.sleep(1)

		number = browser.find_element_by_xpath("//*[@class='m-table ']/tbody/tr["+str(i)+"]/td[1]/div/span[1]").get_attribute("data-res-id")
		music_name = browser.find_element_by_xpath("//*[@class='m-table ']/tbody/tr["+str(i)+"]/td[2]/div/div/div/span/a/b").get_attribute("title")
		music_name = music_name[0:40]
		singer = browser.find_element_by_xpath("//*[@class='m-table ']/tbody/tr["+str(i)+"]/td[4]/div/span").get_attribute("title")
		singer = singer[0:30]
		try:
			browser.find_element_by_xpath("//*[@class='m-table ']/tbody/tr["+str(i)+"]/td[5]/div/a").click()
		except Exception:
			print("扫描记录k=%d，i=%d时出错"%(k,i))
			continue

		#elem.click()

		# elem = browser.find_element_by_xpath("//*[@class='m-table ']/tbody/tr["+str(i)+"]/td[2]/div/div/div/span/a/b")
		# elem.click()


		time.sleep(2)

# 		music_name = browser.find_element_by_class_name("f-ff2").text;
# 		music_name = music_name[0:40]
# 		##########################################################################################
# ##########################################################################找歌手名应该改用attribute title
# 		singer = browser.find_element_by_xpath("//*[@class='cnt']/p[1]/span").get_attribute("title");
# 		singer = singer[0:30]
	#	album = browser.find_element_by_xpath("//*[@class='cnt']/p[2]/a").text;
		# number = browser.find_element_by_xpath("//*[@id='content-operation']").get_attribute("data-rid")

		# browser.find_element_by_xpath("//*[@class='out s-fc3']/a").click()
		# time.sleep(1)
		# yesorno = False
		# try:
		# 	browser.find_element_by_xpath("//*[@class='lyct f-cb f-tc']/div[1]")
		# except Exception:
		# 	yesorno = True

		#if yesorno :
		# browser.get("http://music.163.com/#/song?id="+str(number)) 
		# iframe = browser.find_element_by_id("g_iframe");
		# browser.switch_to_frame(iframe)
		# browser.find_element_by_xpath("//*[@class='cnt']/p[2]/a").click()
		# time.sleep(2);
		# iframe = browser.find_element_by_id("g_iframe");
		# browser.switch_to_frame(iframe)
		date = browser.find_element_by_xpath("//*[@class='topblk']/p[2]").text
		date = date[5:]
		

		sql = "INSERT INTO stitp (Number,ID, SongName, Author,Date,Lable) VALUES ( '%d','%s', '%s', '%s', '%s', '%s')"
		data = (succeed_num+1,number, music_name, singer,date,lable)

		try:

			cursor.execute(sql % data)
			connect.commit()
			succeed_num = succeed_num+1
			print('成功插入',lable,'第', succeed_num, '条数据')
			print("%s  %s  %s  %s  %s"%(music_name,singer,date,number,lable));
		except Exception:
			print('插入失败')
			# cursor.execute(sql % data)
			# connect.commit()
			# succeed_num = succeed_num+1
			# print('成功插入第', succeed_num, '条数据')
			# print("%s  %s  %s  %s  %s"%(music_name,singer,date,number,lable));
		# 	print("%s"%(singer));
		# #	print("%s"%(album));
		# 	print("%s"%(date));
		# 	print("%s"%(number));
		# 	print("%s"%(lable));
		# else :
		# 	browser.find_element_by_xpath("//*[@class='lybtn']/a").click()
		# 	#browser.find_element_by_class_name("u-btn2 u-btn2-2 u-btn2-w2").click()
			
		# 	continue




# for k in range(7,13):
# 	lable = lablearr[k]
# 	for i in range(1,61):
# 		browser.get("http://music.163.com/#/search/m/?s="+utfarr[k]+"&type=1")

# 		if i <31:
# 			time.sleep(1)
# 			for j in range(1,i):
# 				browser.find_element_by_id("g_iframe").send_keys(Keys.DOWN) 
				
# 			time.sleep(3)
# 			iframe = browser.find_element_by_id("g_iframe");
# 			browser.switch_to_frame(iframe)

# 			elem = browser.find_element_by_xpath("//*[@class='srchsongst']/div["+str(i)+"]/div[2]/div/div/a[1]/b")
# 			elem.click();
			
# 			time.sleep(1)
# 		else:
# 			# js="var q=document.documentElement.scrollTop=10000"  
# 			# browser.execute_script(js)
# 			# js="var q=document.documentElement.scrollTop=10"
# 			# browser.execute_script(js)
# 			time.sleep(1)
# 			browser.find_element_by_id("g_iframe").send_keys(Keys.PAGE_DOWN) 
# 			time.sleep(1)
# 			browser.find_element_by_id("g_iframe").send_keys(Keys.PAGE_DOWN) 
# 			time.sleep(1)
# 			browser.find_element_by_id("g_iframe").send_keys(Keys.PAGE_DOWN) 
# 			# for j in range(1,i):
# 			# 	browser.find_element_by_id("g_iframe").send_keys(Keys.DOWN) 
# 			# browser.find_element_by_id("g_iframe").send_keys(Keys.PAGE_DOWN) 
# 			# browser.find_element_by_id("g_iframe").send_keys(Keys.PAGE_DOWN) 
# 			# browser.find_element_by_id("g_iframe").send_keys(Keys.PAGE_DOWN) 
# 			time.sleep(2)

# 			iframe = browser.find_element_by_id("g_iframe");
# 			browser.switch_to_frame(iframe)

# 			browser.find_element_by_xpath("//*[@id='m-search']/div[3]/div/a[11]").click()

# 			time.sleep(2)

# 			# js="var q=document.documentElement.scrollTop="+str((i-31)*5)  
# 			# browser.execute_script(js)

# 			# for j in range(31,i):
# 			# 	#browser.find_element_by_id("m-search").send_keys(Keys.UP) 
# 			# 	browser.find_element_by_xpath("//*[@class='g-bd']/div").send_keys(Keys.UP) 
# 			# js="var q=document.documentElement.scrollTop=0"  
# 			# browser.execute_script(js)
# 			# if i>45:
# 			# 	js="var q=document.documentElement.scrollTop=10000"  
# 			# 	browser.execute_script(js)
				
# 			time.sleep(2)
# 			elem = browser.find_element_by_xpath("//*[@class='srchsongst']/div["+str(i-30)+"]/div[2]/div/div/a[1]/b")
# 			elem.click();
			
# 			time.sleep(1)
# 		# elem = browser.find_element_by_xpath("//*[@id='g_search']/div[1]/span/input")
# 		# elem.clear()
# 		# elem.send_key('陈奕迅')
# 		#elem.send_keys(Keys.ENTER)


# 		time.sleep(2)
# 		#browser.get("http://music.163.com/#/song?id=65355") 
# 		#time.sleep(5)

# 		#musicname =  browser.find_element_by_class_name("f-ff2").text
# 		#browser.find_element_by_xpath('/html/body/iframe/body/div[3]/div[1]/div/div/div[1]/div[1]/div[2]/div[1]/div/em')
# 	#iframe = browser.find_element_by_id("g_iframe");
# 	#browser.switch_to_frame(iframe)
# 		music_name = browser.find_element_by_class_name("f-ff2").text;
# 		music_name = music_name[0:40]
# 		##########################################################################################
# ##########################################################################找歌手名应该改用attribute title
# 		singer = browser.find_element_by_xpath("//*[@class='cnt']/p[1]/span/a").text;
# 		singer = singer[0:30]
# 	#	album = browser.find_element_by_xpath("//*[@class='cnt']/p[2]/a").text;
# 		number = browser.find_element_by_xpath("//*[@id='content-operation']").get_attribute("data-rid")

# 		browser.find_element_by_xpath("//*[@class='out s-fc3']/a").click()
# 		time.sleep(1)
# 		yesorno = False
# 		try:
# 			browser.find_element_by_xpath("//*[@class='lyct f-cb f-tc']/div[1]")
# 		except Exception:
# 			yesorno = True

# 		if yesorno :
# 			browser.get("http://music.163.com/#/song?id="+str(number)) 
# 			iframe = browser.find_element_by_id("g_iframe");
# 			browser.switch_to_frame(iframe)
# 			browser.find_element_by_xpath("//*[@class='cnt']/p[2]/a").click()
# 			time.sleep(2);
# 			# iframe = browser.find_element_by_id("g_iframe");
# 			# browser.switch_to_frame(iframe)
# 			date = browser.find_element_by_xpath("//*[@class='topblk']/p[2]").text
# 			date = date[5:]
# 			sql = "INSERT INTO stitp (ID, SongName, Author,Date,Lable) VALUES ( '%s', '%s', '%s', '%s', '%s')"
# 			data = (number, music_name, singer,date,lable)
# 			cursor.execute(sql % data)
# 			connect.commit()
# 			print('成功插入', cursor.rowcount, '条数据')
# 			print("%s"%(music_name));
# 			print("%s"%(singer));
# 		#	print("%s"%(album));
# 			print("%s"%(date));
# 			print("%s"%(number));
# 			print("%s"%(lable));
# 		else :
# 			browser.find_element_by_xpath("//*[@class='lybtn']/a").click()
# 			#browser.find_element_by_class_name("u-btn2 u-btn2-2 u-btn2-w2").click()
			
# 			continue
# browser.get("http://music.163.com/#/outchain/2/27646205/") 
# iframe = browser.find_element_by_id("g_iframe");
# browser.switch_to_frame(iframe)
# iframe = browser.find_element_by_xpath("//*[@id='module-root']/div[2]/div/div/div[1]/iframe")
# browser.switch_to_frame(iframe)
# time = browser.find_element_by_id("time").text
# print("%s"%(time));

# browser.find_element_by_id("fromStationText").clear()
# browser.find_element_by_id("fromStationText").click()
# browser.find_element_by_id("form_cities2")
# browser.find_element_by_xpath(".//*[@id='ul_list1']/li[2]").click()
# #time.sleep(2)

# #定位选择目的地
# browser.find_element_by_id("toStationText").clear()
# browser.find_element_by_id("toStationText").click()
# browser.find_element_by_id("form_cities2")
# browser.find_element_by_xpath(".//*[@id='ul_list1']/li[3]").click()
# #time.sleep(2)

# #点击查询按钮
# browser.find_element_by_id("a_search_ticket").click()
#time.sleep(2)

#退出浏览器
#browser.quit()
