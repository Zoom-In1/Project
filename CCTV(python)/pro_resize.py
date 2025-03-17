import os
from PIL import Image

def resize_images_in_directory(directory, size):
    # 디렉터리 내 파일 목록을 가져옵니다.
    files = os.listdir(directory)

    # JPG 파일을 순회합니다.
    for filename in files:
        if filename.lower().endswith('.jpg') or filename.lower().endswith('.jpeg'):
            old_path = os.path.join(directory, filename)
            with Image.open(old_path) as img:
                # 이미지를 RGB 모드로 변환하고 새로운 크기로 변경합니다.
                img = img.convert('RGB')
                img = img.resize(size, Image.LANCZOS)
                
                # 새로운 파일 경로를 설정합니다.
                new_path = os.path.join(directory, f"resized_{filename}")
                
                # 이미지를 JPEG 형식으로 저장합니다.
                img.save(new_path, 'JPEG')
                print(f"Resized and saved: {new_path}")

# 사용자가 입력한 디렉터리 경로와 새로운 크기를 가져옵니다.
directory_path = input("이미지 크기를 변경할 디렉터리 경로를 입력하세요: ")
width = int(input("새로운 이미지의 너비를 입력하세요: "))
height = int(input("새로운 이미지의 높이를 입력하세요: "))

# 디렉터리가 존재하는지 확인합니다.
if os.path.isdir(directory_path):
    resize_images_in_directory(directory_path, (width, height))
else:
    print("입력한 경로가 유효한 디렉터리가 아닙니다.")


