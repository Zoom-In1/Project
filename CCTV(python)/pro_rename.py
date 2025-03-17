import os

# 이미지 파일이 저장된 폴더 경로를 지정
folder_path = "D:/mw/python_ml/workspace/project/data_fol/train_fol"

# 폴더 내 파일 목록을 가져오기
files = os.listdir(folder_path)

#print(files[0])
#print(type(files))

images = [file for file in files if file.lower().endswith(".jpg")]

# 이미지 파일들을 순차적으로 이름 변경
for i,filename in enumerate(images,start=1):
    file_extension = os.path.splitext(filename)[1]
    new_name = f"{i}.jpg"
    old_file = os.path.join(folder_path,filename)
    new_file = os.path.join(folder_path,new_name)
    os.rename(old_file,new_file)
    
print("이미지 파일 이름 변경 성공")
