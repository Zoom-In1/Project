from selenium import webdriver
from bs4 import BeautifulSoup
import time
import requests

# 1. 브라우저 제어를 위한 객체 생성
driver = webdriver.Chrome()
driver.implicitly_wait(10)

# 네이버
# 2. 담배피는 모습 사진 수집
url = "https://search.naver.com/search.naver"
param = "??ssc=tab.image.all&where=image&sm=tab_jum&query={keyword}"
keyword = "담배피는 모습"
api_url = url + param.format(keyword=keyword)

driver.get(api_url)
time.sleep(3)
 # 3. 담배피는 모습  train 이미지 수집
 # 스크롤 
for i in range(10) :
    driver.execute_script('window.scrollTo(0, document.body.scrollHeight)')
    time.sleep(2)
  
# beautiful Soup 객체 생성
soup = BeautifulSoup(driver.page_source, 'html.parser')
# img 태그 추출
img = soup.select('img[src]')

# 이미지 url을 저장할 리스트
img_list = []    
for item in img : 
    if not 'data:' in item.attrs['src'] :
        img_list.append(item.attrs['src']) 

# url 중복 데이터 제거
img_list = list(set(img_list))

# 3-1. 이미지 저장    
# 1) user-agent 준비
user_agent = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36 Edg/125.0.0.0'
headers = {'User-Agent' : user_agent, 'Referer' : None}

# 2) 이미지 저장하기
cnt = 1
for i, url in enumerate(img_list) :
    print(i)
    #if i < 200 :
        # 파일이 저장될 경로
    path = 'data_fol/train_fol/%03d.jpg' %(cnt)
    print('[%s] >> %s' %(path, url))
    #elif  i >= 200 and i < 250 :
        #path = 'data_fol/train_fol/%03d.jpg' %(cnt)
        #print('[%s] >> %s' %(path, url))   
    #else :
        #path = 'data_fol/test_fol/%03d.jpg'  %(cnt)
        #print('[%s] >> %s' %(path, url))
        
    try : 
        r = requests.get(url, headers=headers, stream=True)
        if r.status_code != 200 :
            raise Exception()       #예외를 발생시킴
    
        with open(path, 'wb') as f:
            f.write(r.raw.read())
            print('-----> 저장 성공')
            cnt += 1
            
    except Exception as e:
        print('-----> 저장 실패')
        continue

# 1. 브라우저 제어를 위한 객체 생성
driver = webdriver.Chrome()
driver.implicitly_wait(10)

# 네이버
# 2. 담배피는 모습 사진 수집
url = "https://search.daum.net/search"
param = "?w=img&nil_search=btn&DA=NTB&enc=utf8&q={keyword}"
keyword = "담배피는 모습"
api_url = url + param.format(keyword=keyword)

driver.get(api_url)
time.sleep(3)
 # 3. 담배피는 모습  train 이미지 수집
 # 스크롤 
for i in range(30) :
    driver.execute_script('window.scrollTo(0, document.body.scrollHeight)')
    time.sleep(2)
  
# beautiful Soup 객체 생성
soup = BeautifulSoup(driver.page_source, 'html.parser')
# img 태그 추출
img = soup.select('img[src]')

# 이미지 url을 저장할 리스트
img_list = []    
for item in img : 
    if not 'data:' in item.attrs['src'] :
        img_list.append(item.attrs['src']) 

# url 중복 데이터 제거
img_list = list(set(img_list))

# 3-1. 이미지 저장    
# 1) user-agent 준비
user_agent = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36 Edg/125.0.0.0'
headers = {'User-Agent' : user_agent, 'Referer' : None}

# 2) 이미지 저장하기

for i, url in enumerate(img_list) :
    print(i)
    #if i < 900 :
        # 파일이 저장될 경로
    path = 'data_fol/train_fol/%03d.jpg' %(cnt)
    print('[%s] >> %s' %(path, url))
    #elif  i >= 900 and i < 1200 :
        #path = 'data_fol/val_fol/%03d.jpg' %(cnt)
        #print('[%s] >> %s' %(path, url))   
    #else :
       # path = 'data_fol/test_fol/%03d.jpg'  %(cnt)
       # print('[%s] >> %s' %(path, url))
        
    try : 
        r = requests.get(url, headers=headers, stream=True)
        if r.status_code != 200 :
            raise Exception()       #예외를 발생시킴
    
        with open(path, 'wb') as f:
            f.write(r.raw.read())
            print('-----> 저장 성공')
            cnt += 1
            
    except Exception as e:
        print('-----> 저장 실패')
        continue






























