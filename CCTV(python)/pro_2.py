import numpy as np
import matplotlib.pyplot as plt
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.preprocessing.image import load_img

# 랜덤 시드 구성
np.random.seed(5)


train_datagen = ImageDataGenerator(rescale=1/255, rotation_range=15,
                                   width_shift_range=0.1,
                                   height_shift_range=0.1,
                                   shear_range=0.5, zoom_range=[0.8, 2.0],
                                   horizontal_flip=True,
                                   vertical_flip=True,
                                   fill_mode='nearest')

import os
#폴더 경로 지정하기
path='D:/mw/python_ml/workspace/project/data_fol/train_fol'
#해당 폴더 안에 있는 파일 리스트 불러오기
files = os.listdir(path)
# 이미지를 불러올 리스트 만들기
#img_list = []
# 이미지 30개 생성

x_train_list = []
for i in range(1634) :
    #if ('1 (%s)' %(i+1)) not in files :
        #continue
    #else :
    img = load_img('data_fol/train_fol/%s.jpg' %(i+1))

    # 이미지 1개만 읽어오기
    #img = load_img('data_fol/_fol/1 (1).jpg')
    
    #print(type(img))
    #print('=' * 22)
    # numpy 배열로 변환
    x = np.array(img)
    #print(type(x))
    #print(x.shape)
    #print('=' * 22)
    
    # 이미지 확인
    #plt.imshow(x)
    #plt.show()
  
    # ImageDataGenerator.flow() 함수에 사용하기위해 차원 변경
    x = x.reshape(-1, 300, 300, 3)
    #print(x.shape)
    #print('=' * 22)
    
    # 테스트용으로 이미지 1개 생성
    tx = next(train_datagen.flow(x, batch_size=1, save_to_dir='preview',
                            save_prefix='tri',
                            save_format='jpg'))[0]
    
    #print(tx.shape)
    #print('-' * 22)
       
    for j in range(20) :
        temp = next(train_datagen.flow(x, batch_size=1, save_to_dir='preview',
                                    save_prefix='tri',
                                    save_format='jpg'))[0]
        x_train_list.append(temp)
        
# numpy 배열로 변경
x_train = np.array(x_train_list)
#print(x_train.shape)
#print('=' * 22)

# 이미지 확인
plt.subplot(1, 3, 1)
plt.imshow(x_train[0])
plt.subplot(1, 3, 2)
plt.imshow(x_train[1])
plt.subplot(1, 3, 3)
plt.imshow(x_train[2])
plt.show()
