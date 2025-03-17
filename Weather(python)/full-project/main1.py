# 기후와 주가변동 비교하는 프로젝트

import numpy as np
from pandas import read_csv
import matplotlib.pyplot as plt
from pandas import merge


# 랜덤시드 설정
np.random.seed(5)

# 1. 데이터 준비하기 (빙그레) - 성찬
df_temp = read_csv('온도평균.csv', encoding='euc-kr')
df_stock = read_csv('빙그레_월봉.csv')
stock_name = '빙그레'

# 1.1 (태경케미컬) - 민우
df_s = read_csv('taekyungchemical.csv', encoding ='euc-kr')
stock_name2 = '태경케미컬'

# 1.2 (파세코) - 정훈
df_s_pa = read_csv('파세코_월봉.csv', encoding ='euc-kr')
stock_name3 = '파세코'

#print(df_temp)
#print(df_stock)
# 데이터 형태 확인
#df.boxplot()
#plt.show()

# 인덱스 컬럼명 변경
df_stock = df_stock.rename(columns={'Date':'날짜',
                                    'Close':'종가'})
df_temp = df_temp.rename(columns={'평균기온(℃)':'평균기온',
                                  '년월':'날짜'})
df_s = df_s.rename(columns={'Date':'날짜', 'Close':'종가'})
df_s_pa = df_s_pa.rename(columns={'Date':'날짜', 'Close':'종가'})

# 20xx-xx-xx에서 뒤에 -xx 제거
df_s['날짜'] = df_s['날짜'].str.slice(0,7)
df_s_pa['날짜'] = df_s_pa['날짜'].str.slice(0,7)
print(df_s)
# 온도 데이터프레임 정리

#print(df_temp.dtypes)
df_temp['날짜'] = df_temp['날짜'].str.replace('\t\t\t', '')
#print(df_temp)

# 양쪽 데이터프레임 결합
df = merge(df_temp, df_stock, how='outer')
df_tk = merge(df_temp, df_s, how='outer')
df_pa = merge(df_temp, df_s_pa, how='outer')
print(df)
print(df_tk)
print(df_pa)

# 컬럼 정리
df = df.rename(index=df['날짜']).drop(columns='날짜')
df = df.drop(columns=['지점', '평균최저기온(℃)', '평균최고기온(℃)', 'Open', 'High', 'Low', 'Adj Close', 'Volume'])
df_tk = df_tk.rename(index=df_tk['날짜']).drop(columns='날짜')
df_tk = df_tk.drop(columns=['지점', '평균최저기온(℃)', '평균최고기온(℃)', 'Open', 'High', 'Low', 'Adj Close', 'Volume'])
df_pa = df_pa.rename(index=df_pa['날짜']).drop(columns='날짜')
df_pa = df_pa.drop(columns=['지점', '평균최저기온(℃)', '평균최고기온(℃)', 'Open', 'High', 'Low', 'Adj Close', 'Volume'])

#print(df)
#print('df_test=',df_test)

# 종가 정규화
df['종가'] = df['종가'] / 1000
df_tk['종가'] = df_tk['종가'] / 1000
df_pa['종가'] = df_pa['종가'] / 1000

# 결측치 확인
print(df.isna().sum())
print(df_tk.isna().sum())
print(df_pa.isna().sum())
# 결측치 제거 (종가는 주말,공휴일이 존재 하지 않기 때문에 제거)
df = df.dropna()
df_tk = df_tk.dropna()
df_pa = df_pa.dropna()
print(df)
print(df_tk)
print(df_pa)
print('=' * 22)

start_date = input('시작 년월을 입력해주세요 (예: 20xx-xx) : ')
end_date = input('끝 년월을 입력해주세요 (예: 20xx-xx) : ')
df_bing_print = df[start_date:end_date]
df_tk_print = df_tk[start_date:end_date]
df_pa_print = df_pa[start_date:end_date]

#print(df_Bing_print)

plt.rcParams['font.family'] = 'Malgun Gothic'
plt.rcParams['font.size'] = 14
plt.rcParams['figure.figsize'] = (10, 6)

# 빙그레 주가 출력
df_bing_print.plot(marker='*')
plt.title('%s ~ %s 평균온도와 %s주가변화' %(start_date, end_date, stock_name))
plt.xlabel('년, 월')
plt.ylabel('평균온도 및 종가')
plt.grid()
plt.show()
# 빙그레 상관관계
# 상관관계 계산 # corr() : 상관관계 비교하는 것
correlation = df_bing_print['평균기온'].corr(df_bing_print['종가'])

# 상관관계 출력 및 결과 제시
if abs(correlation) >= 0.5:  # 상관계수 절대값 기준 설정  0.5보다 크면 1, 0.5 보다 작으면 0
    print(f"기온과 주식 종가의 상관관계: {correlation:.2f}, 관계가 있습니다 (1)")
else:
    print(f"기온과 주식 종가의 상관관계: {correlation:.2f}, 관계가 없습니다 (0)")

# 태경케미컬 주가 출력
df_tk_print.plot.barh()
plt.title('%s ~ %s 평균온도와 %s주가변화' %(start_date, end_date, stock_name2))
plt.xlabel('월별 평균기온')
plt.ylabel( '종가')
plt.legend()
plt.grid()

for i,v in enumerate(df_tk_print['평균기온']) :
    txt = '%d℃' %v
    plt.text(v, i, txt ,fontsize = 12, color = 'black', ha = 'left')
for i,v in enumerate(df_tk_print['종가']) :
    txt = '%d000원' %v
    plt.text(v, i, txt ,fontsize = 12, color = 'red', ha = 'left')

# 1월 부터 12월까지 순서 변경
plt.gca().invert_yaxis()
plt.show()

# 태경케미컬 상관관계
# 상관관계 계산 # corr() : 상관관계 비교하는 것
correlation = df_tk_print['평균기온'].corr(df_tk_print['종가'])

# 상관관계 출력 및 결과 제시
if abs(correlation) >= 0.5:  # 상관계수 절대값 기준 설정  0.5보다 크면 1, 0.5 보다 작으면 0
    print(f"기온과 주식 종가의 상관관계: {correlation:.2f}, 관계가 있습니다 (1)")
else:
    print(f"기온과 주식 종가의 상관관계: {correlation:.2f}, 관계가 없습니다 (0)")

# 파세코 주가 출력
df_pa_print.plot.bar()
plt.title('%s ~ %s 평균온도와 %s주가변화' %(start_date, end_date, stock_name3))
plt.xlabel('월별 평균기온')
plt.ylabel( '종가')
plt.legend()
plt.grid()

for i,v in enumerate(df_pa_print['평균기온']) :
    txt = '%d℃' %v
    plt.text(i, v, txt ,fontsize = 12, color = 'black', ha = 'left')
for i,v in enumerate(df_pa_print['종가']) :
    txt = '%d000원' %v
    plt.text(i, v, txt ,fontsize = 12, color = 'red', ha = 'left')

# 1월 부터 12월까지 순서 변경
#plt.gca().invert_yaxis()
plt.xticks(rotation=45)
plt.show()


# 파세코 상관관계
# 상관관계 계산 # corr() : 상관관계 비교하는 것
correlation = df_pa_print['평균기온'].corr(df_pa_print['종가'])

# 상관관계 출력 및 결과 제시
if abs(correlation) >= 0.5:  # 상관계수 절대값 기준 설정  0.5보다 크면 1, 0.5 보다 작으면 0
    print(f"기온과 주식 종가의 상관관계: {correlation:.2f}, 관계가 있습니다 (1)")
else:
    print(f"기온과 주식 종가의 상관관계: {correlation:.2f}, 관계가 없습니다 (0)")



