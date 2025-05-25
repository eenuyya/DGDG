-- 0524 수정사항
-- 별점 인덱스 삭제
-- 메뉴 이름 인덱스 추가
-- Star의 기본키였던 star_id 삭제, 기본키 (user_id, rest_id)로 변경
-- Menu의 기본키를 menu_id에서 (menu_id, rest_id)로 변경

-- ----------------------------
-- 사용자 그룹 테이블
-- ----------------------------
CREATE TABLE User_group (
  group_id INT PRIMARY KEY AUTO_INCREMENT,
  group_name VARCHAR(255) NOT NULL,
  invite_code INT UNIQUE NOT NULL,
  leader INT NOT NULL
) AUTO_INCREMENT = 5001;

-- ----------------------------
-- 사용자 테이블
-- ----------------------------
CREATE TABLE User (
  user_id INT PRIMARY KEY AUTO_INCREMENT,
  user_name VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  is_available BOOLEAN DEFAULT TRUE,
  group_id INT,
  is_leader BOOLEAN DEFAULT FALSE,
  FOREIGN KEY (group_id) REFERENCES User_group(group_id)
) AUTO_INCREMENT = 1000;


CREATE VIEW User_view AS
SELECT
  user_id,
  user_name,
  group_id,
  is_available,
  is_leader
FROM User;


-- ----------------------------
-- 식당 테이블
-- ----------------------------
CREATE TABLE Restaurant (
  rest_id INT PRIMARY KEY AUTO_INCREMENT,
  rest_name VARCHAR(255) NOT NULL,
  category ENUM('한식', '일식', '중식', '양식', '디저트', '기타') NOT NULL,
  distance INT,
  open_time TIME,
  close_time TIME,
  break_start TIME,
  break_end TIME,
  has_vegan BOOLEAN DEFAULT FALSE
) AUTO_INCREMENT = 101;

-- ----------------------------
-- 메뉴 테이블
-- ----------------------------
CREATE TABLE Menu (
  menu_id INT NOT NULL AUTO_INCREMENT,
  rest_id INT NOT NULL,
  category ENUM('한식', '일식', '중식', '양식', '디저트', '기타') NOT NULL,
  menu_name VARCHAR(255) NOT NULL,
  price INT NOT NULL,
  is_vegan BOOLEAN DEFAULT FALSE,
  is_soup BOOLEAN DEFAULT FALSE,
  spicy INT DEFAULT 0 CHECK (spicy BETWEEN 0 AND 5),
  PRIMARY KEY (menu_id, rest_id),
  FOREIGN KEY (rest_id) REFERENCES Restaurant(rest_id)
) AUTO_INCREMENT = 20001;

-- ----------------------------
-- 별점 테이블
-- ----------------------------
CREATE TABLE Star (
  user_id INT NOT NULL,
  rest_id INT NOT NULL,
  rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
  PRIMARY KEY (user_id, rest_id),
  FOREIGN KEY (user_id) REFERENCES User(user_id),
  FOREIGN KEY (rest_id) REFERENCES Restaurant(rest_id)
);

-- ----------------------------
-- 즐겨찾기 테이블
-- ----------------------------
CREATE TABLE Favorites (
  user_id INT NOT NULL,
  rest_id INT NOT NULL,
  PRIMARY KEY (user_id, rest_id),
  FOREIGN KEY (user_id) REFERENCES User(user_id),
  FOREIGN KEY (rest_id) REFERENCES Restaurant(rest_id)
);

-- ----------------------------
-- 순환 참조 해결
-- ----------------------------
ALTER TABLE User_group
ADD FOREIGN KEY (leader) REFERENCES User(user_id);

-- ----------------------------
-- 식당 이름 컬럼 인덱스 추가
-- ----------------------------
CREATE INDEX idx_restaurant_name ON Restaurant(rest_name);

-- ----------------------------
-- 메뉴 이름 컬럼 인덱스 추가
-- ----------------------------
CREATE INDEX idx_menu_name ON Menu(menu_name);

-- ----------------------------
-- 사용자 이름 컬럼 인덱스 추가
-- ----------------------------
CREATE INDEX idx_user_name ON User(user_name);

