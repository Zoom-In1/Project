# 모듈 임포트
from tensorflow.keras.preprocessing.image import ImageDataGenerator
import numpy as np
train_datagen = ImageDataGenerator(rescale=1/255)
test_datagen = ImageDataGenerator(rescale=1/255)
val_datagen = ImageDataGenerator(rescale=1/255)

train_generator = train_datagen.flow_from_directory(
                    'data_fol/train_fol',
                    target_size=(128, 128),
                    batch_size=1,
                    class_mode='categorical')

val_generator = val_datagen.flow_from_directory(
                    'data_fol/val_fol',
                    target_size=(128, 128),
                    batch_size=1,
                    class_mode='categorical')

test_generator = test_datagen.flow_from_directory(
                    'data_fol/test_fol',
                    target_size=(128, 128),
                    batch_size=1,
                    class_mode='categorical')

# 폴더 순서

x_train_list = []
y_train_list = []
x_val_list = []
y_val_list = []
x_test_list = []
y_test_list = []

# 훈련셋
for i in range(36952) :
    img, label = next(train_generator) # 이미지 1개씩 읽기
    x_train_list.extend(img)
    y_train_list.extend(label)

# 검증 셋
for i in range(4979) :
    img, label = next(val_generator) # 이미지 1개씩 읽기
    x_val_list.extend(img)
    y_val_list.extend(label)

# 테스트셋
for i in range(587) :
    img, label = next(test_generator) # 이미지 1개씩 읽기
    x_test_list.extend(img)
    y_test_list.extend(label)

#print(x_train_list[:7])

# numpy 배열로 변경
x_train = np.array(x_train_list)
y_train = np.array(y_train_list)
x_val = np.array(x_val_list)
y_val = np.array(y_val_list)
x_test = np.array(x_test_list)
y_test = np.array(y_test_list)

x_train = x_train.astype('float16')
x_val = x_val.astype('float16')
x_test = x_test.astype('float16')


#y_train = utils.to_categorical(y_train)
#y_test = utils.to_categorical(y_test)

'''
print(x_train.shape)
print(y_train.shape)
print(x_test.shape)
print(y_test.shape)
'''
# 데이터 확인 : 시각화
'''
plt.rcParams['figure.figsize'] = (8,6)

plt.imshow(x_train[0])
plt.title(y_train[0])
plt.show()

plt.imshow(x_test[0])
plt.title(y_test[0])
plt.show()
'''
