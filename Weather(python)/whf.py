import pandas as pd
import numpy as np
from tensorflow.keras import utils
from pandas import read_csv
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
import matplotlib.pyplot as plt
from pandas import merge

# 1. 데이터 준비하기
df_w = read_csv('weather.csv', encoding='euc-kr')
df_s = read_csv('taekyungchemical.csv', encoding ='euc-kr')
#print(df_w)
#print(df_s)

# 데이터 형태 확인
df_w['년월'] = df_w['년월'].str.strip()
#print(df_w)

# 인덱스, 컬럼명 변경
df_w = df_w.rename(index=df_w['년월'])#print(df_w)
df_s = df_s.rename(columns={'Date':'날짜', 'Close':'종가'})
#print(df_s)

# 주식데이터 yyyy-mm-dd 에서 dd를 슬라이스로 제거
df_s['날짜'] = df_s['날짜'].str.slice(0,7)
#print(df_s)

# 종가 금액 축소
df_s['종가'] = df_s['종가'] / 1000

# 인덱스, 컬럼명 변경
df_w = df_w.rename(index=df_w['년월'])#print(df_w)
df_s = df_s.rename(columns={'Date':'날짜', 'Close':'종가'})
df_s = df_s.rename(index=df_s['날짜'])
#print(df_s)

# 불필요한 컬럼 제거
df_w = df_w.drop(columns = ['년월', '지점', '평균최저기온(℃)', '평균최고기온(℃)'])
df_s = df_s.drop(columns = ['날짜','Open', 'High', 'Low', 'Adj Close', 'Volume'])
#print(df_w)
#print(df_s)

# 1) 결측치 확인
print(df_w.isna().sum())
print('-' * 20)

# 2) 결측치가 포함된 행 삭제하기
df_w  =  df_w.dropna()
#print(df_w)
#print('-' * 20)

# 양쪽 데이터프레임 결합
df = merge(df_w, df_s, left_index= True, right_index= True, how='outer') #print(df)
#print(df)

#df = df.dropna()
#print(df)

# 데이터 시각화
plt.rcParams['font.family'] = 'Malgun gothic'
plt.rcParams['font.size'] = 12
plt.rcParams['figure.figsize'] = (10, 10)

# 양쪽 데이터프레임 결합
df = pd.merge(df_w, df_s, left_index=True, right_index=True, how='outer')

# 입력받은 시작 년월과 끝 년월
start_date = input('시작할 년월을 yyyy-mm 형식으로 입력해주세요: ')
end_date = input('끝날 년월을 yyyy-mm 형식으로 입력해주세요: ')

# 시작 년월부터 끝 년월까지 데이터 추출
df_f = df[start_date:end_date]

#fig = plt.figure()
#ax1 = fig.add_subplot(2, 1, 1) # 첫번째 영역
#ax2 = fig.add_subplot(2, 1, 2) # 두번째 영역

df_f.plot.barh()
plt.title(f'{start_date}부터 {end_date}까지의 기온과 주식 종가의 관계')
plt.xlabel('월별 평균기온')
plt.ylabel( '종가')
plt.legend()
plt.grid()

for i,v in enumerate(df_f['평균기온(℃)']) :
    txt = '%d℃' %v
    plt.text(v, i, txt ,fontsize = 12, color = 'black', ha = 'left')
for i,v in enumerate(df_f['종가']) :
    txt = '%d000원' %v
    plt.text(v, i, txt ,fontsize = 12, color = 'red', ha = 'left')

# 1월 부터 12월까지 순서 변경
plt.gca().invert_yaxis()
plt.show()

# 상관관계 계산 # corr() : 상관관계 비교하는 것
correlation = df_f['평균기온(℃)'].corr(df_f['종가'])

# 상관관계 출력 및 결과 제시
if abs(correlation) >= 0.5:  # 상관계수 절대값 기준 설정  0.5보다 크면 1, 0.5 보다 작으면 0
    print(f"기온과 주식 종가의 상관관계: {correlation:.2f}, 관계가 있습니다 (1)")
else:
    print(f"기온과 주식 종가의 상관관계: {correlation:.2f}, 관계가 없습니다 (0)")

#ax1.title.set_text('기온에 따른 주식변동')
#ax1.set(xlabel = '년도별 평균기온', ylabel = '종가')
#ax1.legend()
#ax1.grid()
# 산점도 그래프
#df.plot.scatter(x= '평균기온(℃)', y = '종가',  c = 'orange', s =100, label = '판매수량')
#plt.xticks(df_w['평균기온(℃)'], df_w['평균기온(℃)'])
#plt.title('기온과 아이스크림 판매량의 상간관계')
#plt.grid()
#plt.show()




