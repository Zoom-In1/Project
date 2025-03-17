import numpy as np
from pandas import read_csv
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
import matplotlib.pyplot as plt

np.random.seed(5)

df = read_csv('bank_fraud.csv', encoding='euc-kr')
print(df)
#print(df.columns)
df = df.drop(columns = ['payment_type', 'employment_status', 'housing_status', 'source','device_os'])

df = df/df.max()
df.boxplot()
plt.show()

dataset = df.values
print(dataset)
print(df)

x_train = dataset[:900000, :26]
y_train = dataset[:900000, 0]
x_test = dataset[900000:, :26]
y_test = dataset[900000:, 0]

print(x_train.shape)
print(y_train.shape)
print('-' *20)

model = Sequential()
model.add(Dense(512, activation='relu', input_shape=(26,)))
model.add(Dense(128, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(1, activation='sigmoid'))

model.compile(loss='binary_crossentropy',optimizer='adam', metrics=['accuracy'])

hist = model.fit(x_train, y_train, epochs = 100, batch_size = 32)

plt.rcParams['font.family'] = 'Malgun Gothic'
plt.rcParams['font.size'] = 16
plt.rcParams['figure.figsize'] = (10, 8)

fig, loss_ax = plt.subplots()
acc_ax = loss_ax.twinx()

loss_ax.plot(hist.history['loss'], 'y', label = 'train loss')
acc_ax.plot(hist.history['accuracy'], 'b', label = 'train acc')

loss_ax.set_xlabel('epochs')
loss_ax.set_ylabel('loss')
acc_ax.set_ylabel('accuracy')

loss_ax.legend(loc ='upper left')
acc_ax.legend(loc = 'lower left')
plt.show()

# 7. 모델 평가
scores = model.evaluate(x_test, y_test)
print("손실 :", scores[0])
print("정확도 : %.2f%%" %(scores[1]*100))
