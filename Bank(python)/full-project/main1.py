import numpy as np
from pandas import read_csv
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
import matplotlib.pyplot as plt

# 랜덤시드 설정
np.random.seed(5)

# 1. 데이터 준비하기
df = read_csv('alzheimers_disease_data.csv')
print(df)
print('=' * 22)

# 데이터 형태 확인
df.boxplot()
plt.show()

print(df.columns)

df = df.set_index(df['PatientID']).drop(columns='PatientID')
df = df.drop(columns='DoctorInCharge')

# 데이터 정규화
# => 0~1 사이값으로 수정
df = df/df.max()

df.boxplot()
plt.show()

print(df)

# 2. 데이터셋 준비하기
dataset = df.values
print(dataset)
print('=' * 22)

x_train = dataset[:1800, :32]
y_train = dataset[:1800, 32]
x_test = dataset[1800:, :32]
y_test = dataset[1800:, 32]
print(x_train)
print(x_train.shape)
print(y_train.shape)
print(x_test.shape)
print(y_test.shape)
print('=' * 22)

# 3. 모델 구성하기
model = Sequential()
model.add(Dense(128, activation='relu', input_shape=(32,)))
model.add(Dense(256, activation='relu'))
model.add(Dense(1, activation='sigmoid'))

# 4. 모델 학습과정 설정하기
# accuracy 생략시 epoch가 끝날때 마다 loss값만 사용한다는 거고 metrics 사용시 accuracy값도 같이 구한다는 뜻
# loss : 손실 함수
# optimizer : 최적화 알고리즘
model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])

# 5. 모델 학습시키기
hist = model.fit(x_train, y_train, epochs=200, batch_size=16)

# 6. 모델 학습과정 살펴보기
plt.rcParams['font.family'] = 'Malgun Gothic'
plt.rcParams['font.size'] = 16
plt.rcParams['figure.figsize'] = (12, 8)

fig, loss_ax = plt.subplots()
acc_ax = loss_ax.twinx()

loss_ax.plot(hist.history['loss'], 'y', label='train loss')
acc_ax.plot(hist.history['accuracy'], 'b', label='train accuracy')

loss_ax.set_title('33가지의 데이터셋으로 학습시킨 알츠하이머병 진단 머신러닝')
loss_ax.set_xlabel('epoch')
loss_ax.set_ylabel('loss')
acc_ax.set_ylabel('accuracy')

loss_ax.legend(loc='upper left')
acc_ax.legend(loc='lower left')
plt.show()

# 7. 모델 평가하기
scores = model.evaluate(x_test, y_test)
print('손실 :', scores[0])
print('정확도 : %.2f%%' %(scores[1] * 100))



