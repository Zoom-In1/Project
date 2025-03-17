from selenium import webdriver
from bs4 import BeautifulSoup
import time
import datetime as dt
import requests
import os

# 1. 브라우저 제어 객체 생성
driver = webdriver.Chrome()
driver.implicitly_wait(10)

url = 'https://search.naver.com/search.naver'
param = '?where=image&sm=tab_jum&query={keyword}'
keyword = '하품하는 사람 얼굴'
api_url = url + param.format(keyword=keyword)

driver.get(api_url)
time.sleep(3)

# 2) 스크롤을 진행하면서 이미지 컨텐츠의 url 수집하기
print('>>> 이미지 수집 시작 >>>')

# 15번 스크롤 (여유있게 수집하기)
for i in range(10) :
    # 스크롤을 맨 아래로 이동
    driver.execute_script('window.scrollTo(0, document.body.scrollHeight)')
    time.sleep(3)

soup = BeautifulSoup(driver.page_source, 'html.parser')

# img 태그 추출
img = soup.select('img[src]')

# 사진 저장할 리스트
url_list = []

for item in img :
    if 'http' in item.attrs['src'] :
        url_list.append(item.attrs['src'])

print(len(url_list))
print('=' * 22)
# data: 로 시작하는 태그 제거 
url_list = [url for url in url_list if 'data:' not in url]
print(len(url_list))
print('=' * 22)
# url 중복 데이터 제거
urlJang_list = list(set(url_list))
print(len(url_list))
print('=' * 22)

# 4. 이미지 저장
# 1) 수집한 이미지 정리하기전 저장해둘 폴더 만들기
datetime = dt.datetime.now().strftime('%y%m%d_%H%M')
imgdata = '하품 네이버%s' %datetime
# 폴더 생성하기
if not os.path.exists(imgdata) :
    os.mkdir(imgdata)

# 2) user-agent 준비
user_agent = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36'
# Referer : 이전에 방문했던 페이지 주소를 저장
#           None : 현재 페이지에 처음 방문한다는 뜻
headers = {'User-Agent' : user_agent, 'Referer' : None}

# 3) 이미지 저장하기
for i, url in enumerate(url_list) :
    # 파일이 저장될 경로
    path = '%s/%04d.jpg' %(imgdata, i+1)
    print('[%s] >> %s' %(path, url))
    
    
    try :
        r = requests.get(url, headers=headers, stream = True)
        
        if r.status_code != 200 :
            raise Exception() # 예외를 발생시킴            
    
        with open(path, 'wb') as f:
            f.write(r.raw.read())
            print('-----> 저장 성공')
            
    except Exception as e :
        print('-----> 저장 실패')
    
print('=' * 22)

# 하품 사진이 모잘라서 추가적으로 서칭

url = 'https://search.naver.com/search.naver'
param = '?where=image&sm=tab_jum&query={keyword}'
keyword = '눈감고 있는 사진'
api_url = url + param.format(keyword=keyword)

driver.get(api_url)
time.sleep(3)

# 2) 스크롤을 진행하면서 이미지 컨텐츠의 url 수집하기
print('>>> 이미지 수집 시작 >>>')

# 15번 스크롤 (여유있게 수집하기)
for i in range(10) :
    # 스크롤을 맨 아래로 이동
    driver.execute_script('window.scrollTo(0, document.body.scrollHeight)')
    time.sleep(3)

soup = BeautifulSoup(driver.page_source, 'html.parser')

# img 태그 추출
img = soup.select('img[src]')

# 사진 저장할 리스트
url_list = []

for item in img :
    if 'http' in item.attrs['src'] :
        url_list.append(item.attrs['src'])

print(len(url_list))
print('=' * 22)
# data: 로 시작하는 태그 제거 
url_list = [url for url in url_list if 'data:' not in url]
print(len(url_list))
print('=' * 22)
# url 중복 데이터 제거
urlJang_list = list(set(url_list))
print(len(url_list))
print('=' * 22)

# 4. 이미지 저장
# 1) 수집한 이미지 정리하기전 저장해둘 폴더 만들기
datetime = dt.datetime.now().strftime('%y%m%d_%H%M')
imgdata = '눈감기 네이버%s' %datetime
# 폴더 생성하기
if not os.path.exists(imgdata) :
    os.mkdir(imgdata)

# 2) user-agent 준비
user_agent = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36'
# Referer : 이전에 방문했던 페이지 주소를 저장
#           None : 현재 페이지에 처음 방문한다는 뜻
headers = {'User-Agent' : user_agent, 'Referer' : None}

# 3) 이미지 저장하기
for i, url in enumerate(url_list) :
    # 파일이 저장될 경로
    path = '%s/%04d.jpg' %(imgdata, i+1)
    print('[%s] >> %s' %(path, url))
    
    
    try :
        r = requests.get(url, headers=headers, stream = True)
        
        if r.status_code != 200 :
            raise Exception() # 예외를 발생시킴            
    
        with open(path, 'wb') as f:
            f.write(r.raw.read())
            print('-----> 저장 성공')
            
    except Exception as e :
        print('-----> 저장 실패')
    
print('=' * 22)

# 다음에서 서칭

url = 'https://search.daum.net/search?nil_suggest=btn&w=img&DA=SBC&q=%ED%95%98%ED%92%88%ED%95%98%EB%8A%94+%EC%82%AC%EC%A7%84'
#param = '?where=image&sm=tab_jum&query={keyword}'
#keyword = '하품 유발 사진'
#api_url = url + param.format(keyword=keyword)

driver.get(url)
time.sleep(3)

# 2) 스크롤을 진행하면서 이미지 컨텐츠의 url 수집하기
print('>>> 이미지 수집 시작 >>>')

# 15번 스크롤 (여유있게 수집하기)
for i in range(20) :
    # 스크롤을 맨 아래로 이동
    driver.execute_script('window.scrollTo(0, document.body.scrollHeight)')
    time.sleep(3)

soup = BeautifulSoup(driver.page_source, 'html.parser')

# img 태그 추출
img = soup.select('img[src]')

# 사진 저장할 리스트
url_list = []

for item in img :
    if 'http' in item.attrs['src'] :
        url_list.append(item.attrs['src'])

print(len(url_list))
print('=' * 22)
# data: 로 시작하는 태그 제거 
url_list = [url for url in url_list if 'data:' not in url]
print(len(url_list))
print('=' * 22)
# url 중복 데이터 제거
urlJang_list = list(set(url_list))
print(len(url_list))
print('=' * 22)

# 4. 이미지 저장
# 1) 수집한 이미지 정리하기전 저장해둘 폴더 만들기
datetime = dt.datetime.now().strftime('%y%m%d_%H%M')
imgdata = '하품 다음%s' %datetime
# 폴더 생성하기
if not os.path.exists(imgdata) :
    os.mkdir(imgdata)

# 2) user-agent 준비
user_agent = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36'
# Referer : 이전에 방문했던 페이지 주소를 저장
#           None : 현재 페이지에 처음 방문한다는 뜻
headers = {'User-Agent' : user_agent, 'Referer' : None}

# 3) 이미지 저장하기
for i, url in enumerate(url_list) :
    # 파일이 저장될 경로
    path = '%s/%04d.jpg' %(imgdata, i+1)
    print('[%s] >> %s' %(path, url))
    
    
    try :
        r = requests.get(url, headers=headers, stream = True)
        
        if r.status_code != 200 :
            raise Exception() # 예외를 발생시킴            
    
        with open(path, 'wb') as f:
            f.write(r.raw.read())
            print('-----> 저장 성공')
            
    except Exception as e :
        print('-----> 저장 실패')
    
print('=' * 22)

# 다음에서 서칭

url = 'https://search.daum.net/search?nil_suggest=btn&w=img&DA=SBC&q=%EB%88%88+%EA%B0%90%EA%B3%A0+%EC%9E%88%EB%8A%94+%EC%82%AC%EC%A7%84'
#param = '?where=image&sm=tab_jum&query={keyword}'
#keyword = '하품 유발 사진'
#api_url = url + param.format(keyword=keyword)

driver.get(url)
time.sleep(3)

# 2) 스크롤을 진행하면서 이미지 컨텐츠의 url 수집하기
print('>>> 이미지 수집 시작 >>>')

# 15번 스크롤 (여유있게 수집하기)
for i in range(20) :
    # 스크롤을 맨 아래로 이동
    driver.execute_script('window.scrollTo(0, document.body.scrollHeight)')
    time.sleep(3)

soup = BeautifulSoup(driver.page_source, 'html.parser')

# img 태그 추출
img = soup.select('img[src]')

# 사진 저장할 리스트
url_list = []

for item in img :
    if 'http' in item.attrs['src'] :
        url_list.append(item.attrs['src'])

print(len(url_list))
print('=' * 22)
# data: 로 시작하는 태그 제거 
url_list = [url for url in url_list if 'data:' not in url]
print(len(url_list))
print('=' * 22)
# url 중복 데이터 제거
urlJang_list = list(set(url_list))
print(len(url_list))
print('=' * 22)

# 4. 이미지 저장
# 1) 수집한 이미지 정리하기전 저장해둘 폴더 만들기
datetime = dt.datetime.now().strftime('%y%m%d_%H%M')
imgdata = '눈감기 다음%s' %datetime
# 폴더 생성하기
if not os.path.exists(imgdata) :
    os.mkdir(imgdata)

# 2) user-agent 준비
user_agent = 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36'
# Referer : 이전에 방문했던 페이지 주소를 저장
#           None : 현재 페이지에 처음 방문한다는 뜻
headers = {'User-Agent' : user_agent, 'Referer' : None}

# 3) 이미지 저장하기
for i, url in enumerate(url_list) :
    # 파일이 저장될 경로
    path = '%s/%04d.jpg' %(imgdata, i+1)
    print('[%s] >> %s' %(path, url))
    
    
    try :
        r = requests.get(url, headers=headers, stream = True)
        
        if r.status_code != 200 :
            raise Exception() # 예외를 발생시킴            
    
        with open(path, 'wb') as f:
            f.write(r.raw.read())
            print('-----> 저장 성공')
            
    except Exception as e :
        print('-----> 저장 실패')
    
print('=' * 22)
