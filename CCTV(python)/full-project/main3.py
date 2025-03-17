import numpy as np
import matplotlib.pyplot as plt
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Conv2D
from tensorflow.keras.layers import MaxPooling2D, Flatten, Dropout, BatchNormalization
from tensorflow.keras.models import load_model
#import main2
'''
x_train = main2.x_train
y_train = main2.y_train
x_val = main2.x_val
y_val = main2.y_val
x_test = main2.x_test
y_test = main2.y_test

#print(x_train.shape)

# 2. 모델 구성하기

model = Sequential()
model.add(Conv2D(32, (3,3), activation='relu',input_shape=(128, 128, 3)))
model.add(MaxPooling2D((2,2)))
model.add(Flatten())
model.add(Dense(32, activation='relu'))
model.add(Dense(5, activation='softmax'))

# 3. 모델 학습과정 설정하기
model.compile(loss='categorical_crossentropy', optimizer='adam',
              metrics=['accuracy'])

# 4. 모델 훈련시키기
# 모델 조기종료 객체 생성
from tensorflow.keras.callbacks import EarlyStopping
early_stopping = EarlyStopping(patience = 30)
callbacks=[early_stopping]
hist = model.fit(x_train, y_train, epochs=100, batch_size=16, validation_data=(x_val,y_val),
                 callbacks=[early_stopping])

# 5. 모델 학습과정 살펴보기

fig, loss_ax = plt.subplots()
acc_ax = loss_ax.twinx()

loss_ax.plot(hist.history['loss'], 'y', label='loss train')
loss_ax.plot(hist.history['val_loss'], 'r', label='loss val')
acc_ax.plot(hist.history['accuracy'], 'b', label='acc train')
acc_ax.plot(hist.history['val_accuracy'], 'g', label='acc val')

loss_ax.set_xlabel('epoch')
loss_ax.set_ylabel('loss')
acc_ax.set_ylabel('accuracy')

loss_ax.legend(loc='upper left')
acc_ax.legend(loc='lower left')
plt.show()

# 6. 모델 평가하기
scores = model.evaluate(x_test, y_test)
print('손실 :', scores[0])
print('정확도 %.2f%%' %(scores[1] * 100))
'''

# 7) 모델 사용하기
x_test = x_test.astype('float32')

yhat = model.predict(x_test)

# 8) 정답데이터와 예측값을 일부 시각화
plt.rcParams['figure.figsize'] = (12, 6)
fig, ax_arr = plt.subplots(5, 5)

for i in range(25) :
    sub_plt = ax_arr[i//5, i%5] # 보조창 1개 선택
    sub_plt.axis('off')
    sub_plt.imshow(x_test[i])
    title = 'R: %s, P: %s' %(np.argmax(y_test[i]),
                             np.argmax(yhat[i]))
    sub_plt.set_title(title)

plt.show()

