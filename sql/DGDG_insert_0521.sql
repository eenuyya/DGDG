-- 0517 수정 사항
-- Star 튜플 양 3배로 늘림
-- Favorites 튜플 양 3배로 늘림


INSERT INTO User (user_name, password) VALUES ('dummyleader', 'dummyleader');         -- dummy leader

-- User_group 테이블
INSERT INTO User_group (group_name, invite_code, leader) VALUES ('CSE23', 86086011, 1000);
INSERT INTO User_group (group_name, invite_code, leader) VALUES ('DGDGdev', 64423434, 1000);
INSERT INTO User_group (group_name, invite_code, leader) VALUES ('ewhajipsa', 41252322, 1000);
INSERT INTO User_group (group_name, invite_code, leader) VALUES ('aespa', 15809797, 1000); 
INSERT INTO User_group (group_name, invite_code, leader) VALUES ('STJandfriends', 10365101, 1000);

-- User 테이블
-- 총 20명 (1001 ~ 1020)

INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('joshuamedina', '#$Y0EFf3CZ', True, 5001, True);         -- leader
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('joycurry', 'RMwF1^Gd&&', True, 5001, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('russelltammy', 'c4NB8TPb+0', False, 5001, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('marshalljoseph', '_3FaHzY9yu', False, 5001, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('zdonaldson', '2K39FIhq&l', True, 5001, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('ob1hnk', '1HHFik%n*6', False, 5002, True);    -- leader
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('eenuyya', '+^88*ZqeB*', True, 5002, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('squeezethelemon', '8G8Lu8Wp+@', True, 5002, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('jade0728', '(39DL0uPb6', False, 5002, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('edmond', '^#5NHHQf&p', True, 5003, True);         -- leader
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('blackeleven', '58I*eUhw(m', False, 5003, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('alexanderbrooke', 'l(9rY4iu_&', True, 5003, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('rodneyrasmussen', 'Qi8u3CAsP!', False, 5003, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('karina', 'tJ5#IuHo$y', False, 5004, True);       -- leader
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('winter', 'PcY(0UZV%!', False, 5004, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('giselle', 'R+Be3%JeHq', True, 5004, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('ningning', 'H+PN_xJx(5', False, 5004, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('seotaeji', '2XpTjg*D+n', False, 5005, True);      -- leader
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('richardsonkathy', '7u2Ixa+nx^', False, 5005, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('zshannon', '*QSmeNf*69', True, 5005, False);


UPDATE User_group SET leader = (SELECT user_id FROM User WHERE user_name = 'joshuamedina') WHERE group_name = 'CSE23';
UPDATE User_group SET leader = (SELECT user_id FROM User WHERE user_name = 'ob1hnk') WHERE group_name = 'DGDGdev';
UPDATE User_group SET leader = (SELECT user_id FROM User WHERE user_name = 'edmond') WHERE group_name = 'ewhajipsa';
UPDATE User_group SET leader = (SELECT user_id FROM User WHERE user_name = 'karina') WHERE group_name = 'aespa';
UPDATE User_group SET leader = (SELECT user_id FROM User WHERE user_name = 'seotaeji') WHERE group_name = 'STJandfriends';



-- Restaurant 테이블
-- 총 50개 (101 ~ 150)

-- 한식 8개 (101 ~ 108)
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('김밥천국', '한식', 700, '08:00:00', '22:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('신촌부추곱창', '한식', 3000, '16:00:00', '01:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('본가 설렁탕', '한식', 1100, '11:00:00', '21:00:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('한옥집', '한식', 1600, '11:00:00', '22:00:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('삼청동 수제비', '한식', 5500, '11:30:00', '20:30:00', '15:30:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('백채김치찌개', '한식', 100, '11:00:00', '21:00:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('하남 돼지집', '한식', 9100, '17:00:00', '01:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('명동교자', '한식', 9900, '10:30:00', '21:30:00', NULL, NULL, False);

-- 일식 5개 (109 ~ 113)
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('연어초밥', '일식', 1000, '11:30:00', '21:30:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('세상끝의라멘', '일식', 5200, '11:00:00', '22:30:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('긴자료코', '일식', 300, '11:30:00', '21:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('모미지식당', '일식', 700, '11:00:00', '22:00:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('스시효', '일식', 2100, '12:00:00', '22:00:00', '15:00:00', '17:30:00', False);

-- 중식 9개 (114 ~ 122)
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('홍반장 중화요리', '중식', 400, '11:00:00', '21:30:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('홍콩반점0410', '중식', 1600, '11:00:00', '22:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('차이나팩토리', '중식', 700, '11:30:00', '22:00:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('화라라 마라탕', '중식', 200, '11:00:00', '23:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('라화쿵푸', '중식', 300, '11:30:00', '21:30:00', '15:30:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('명동 교동짬뽕', '중식', 2500, '11:00:00', '22:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('다래헌', '중식', 900, '11:30:00', '21:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('홍보각', '중식', 8100, '11:00:00', '21:30:00', '15:00:00', '17:30:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('송쉐프 중식당', '중식', 700, '11:00:00', '22:00:00', NULL, NULL, False);

-- 양식 11개 (123 ~ 133)
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('라그릴리아', '양식', 3100, '11:30:00', '22:00:00', '15:30:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('빕스', '양식', 500, '11:00:00', '22:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('브런치브라더스', '양식', 700, '09:00:00', '17:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('마마스 샐러드 & 파니니', '양식', 8700, '10:30:00', '21:00:00', '15:30:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('피자몰', '양식', 2900, '11:00:00', '21:30:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('서가앤쿡', '양식', 2700, '11:30:00', '22:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('롤링파스타', '양식', 500, '11:00:00', '21:30:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('바스버거', '양식', 9100, '11:30:00', '22:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('라루즈', '양식', 2200, '12:00:00', '22:00:00', '15:00:00', '17:30:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('바닐라키친', '양식', 5100, '10:30:00', '21:30:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('쁘띠문래', '양식', 8500, '10:00:00', '20:00:00', '15:00:00', '16:30:00', False);

-- 디저트 6개 (134 ~ 139)
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('도레도레', '디저트', 10000, '11:00:00', '21:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('투썸플레이스', '디저트', 700, '10:00:00', '22:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('설빙 이대점', '디저트', 10, '11:30:00', '22:30:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('리치몬드과자점', '디저트', 600, '09:00:00', '21:00:00', '14:30:00', '16:30:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('케이크드몽슈슈', '디저트', 1600, '11:00:00', '21:30:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('앤티앤스', '디저트', 3, '10:00:00', '22:00:00', NULL, NULL, False);

-- 기타 11개 (140 ~ 150)
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('한남오거리 포케', '기타', 12000, '11:30:00', '21:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('할랄가이즈', '기타', 15000, '11:00:00', '21:00:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('두레국수', '기타', 1800, '11:00:00', '20:30:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('바오밥 바베큐', '기타', 1900, '11:30:00', '22:00:00', '15:30:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('고래사냥 호프', '기타', 100, '17:00:00', '02:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('리틀사이공', '기타', 1700, '11:00:00', '22:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('코지라운지', '기타', 2000, '18:00:00', '02:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('온더보더', '기타', 8700, '11:30:00', '22:00:00', '15:30:00', '17:30:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('갓잇', '기타', 7000, '11:00:00', '21:30:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('낙원타코', '기타', 3900, '11:30:00', '22:00:00', '15:30:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('라운지베트남', '기타', 6100, '11:30:00', '21:30:00', '15:00:00', '17:00:00', False);


-- Menu 테이블

INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (101, '야채김밥', 4500, '한식', True, False, 0), (101, '참치김밥', 5000, '한식', False, False, 0), (101, '라면', 4000, '한식', False, True, 1), (101, '떡볶이', 5000, '한식', False, True, 2), (101, '오므라이스', 6500, '한식', False, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (102, '곱창구이', 15000, '한식', False, False, 2), (102, '부추무침', 4000, '한식', True, False, 1), (102, '볶음밥', 2000, '한식', False, False, 1), (102, '된장찌개', 5000, '한식', False, True, 1);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (103, '설렁탕', 9000, '한식', False, True, 0), (103, '도가니탕', 12000, '한식', False, True, 0), (103, '수육', 15000, '한식', False, False, 0), (103, '깍두기', 0, '한식', True, False, 2);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (104, '수육국밥', 8500, '한식', False, True, 1), (104, '김치찜', 9000, '한식', False, True, 2), (104, '공깃밥', 1000, '한식', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (105, '들깨수제비', 8000, '한식', True, True, 0), (105, '바지락수제비', 8500, '한식', False, True, 1), (105, '김치전', 6000, '한식', False, False, 2);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (106, '돼지고기 김치찌개', 9000, '한식', False, True, 3), (106, '계란말이', 7000, '한식', False, False, 0), (106, '김치볶음밥', 8000, '한식', False, False, 2);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (107, '삼겹살 (200g)', 15000, '한식', False, False, 0), (107, '목살 (200g)', 16000, '한식', False, False, 0), (107, '된장찌개', 5000, '한식', False, True, 1), (107, '공깃밥', 1000, '한식', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (108, '칼국수', 10000, '한식', False, True, 0), (108, '왕만두', 9000, '한식', False, False, 0), (108, '비빔국수', 9500, '한식', False, False, 2), (108, '김치', 0, '한식', True, False, 3);

INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (109, '연어초밥세트', 15000, '일식', False, False, 0), (109, '연어사시미', 17000, '일식', False, False, 0), (109, '미소된장국', 2000, '일식', True, True, 0), (109, '연어덮밥', 13000, '일식', False, False, 0), (109, '에다마메', 3000, '일식', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (110, '돈코츠라멘', 9000, '일식', False, True, 1), (110, '쇼유라멘', 8500, '일식', False, True, 1), (110, '카라미소라멘', 9500, '일식', False, True, 3), (110, '멘마토핑', 1000, '일식', True, False, 0), (110, '반숙계란', 1500, '일식', False, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (111, '사케동', 13000, '일식', False, False, 0), (111, '가츠동', 9000, '일식', False, False, 1), (111, '타마고야끼', 4000, '일식', True, False, 0), (111, '미소된장국', 2000, '일식', True, True, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (112, '오야코동', 8500, '일식', False, False, 0), (112, '가라아게 정식', 11000, '일식', False, False, 0), (112, '야채볶음우동', 9500, '일식', True, False, 1), (112, '연두부 샐러드', 6000, '일식', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (113, '오마카세 런치', 45000, '일식', False, False, 0), (113, '특선 스시세트', 38000, '일식', False, False, 0), (113, '참치 사시미', 30000, '일식', False, False, 0), (113, '계절 된장국', 3000, '일식', True, True, 0);

INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (114, '짜장면', 7000, '중식', False, False, 0), (114, '짬뽕', 8000, '중식', False, True, 2), (114, '탕수육', 14000, '중식', False, False, 1), (114, '군만두', 5000, '중식', False, False, 1);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (115, '차돌짬뽕', 8500, '중식', False, True, 3), (115, '볶음짬뽕', 8500, '중식', False, False, 3), (115, '짬뽕밥', 8000, '중식', False, True, 2), (115, '군만두', 4000, '중식', False, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (116, '팔보채', 16000, '중식', False, False, 2), (116, '마파두부', 10000, '중식', False, True, 3), (116, '칠리새우', 17000, '중식', False, False, 2), (116, '계란볶음밥', 6000, '중식', False, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (117, '마라탕', 11000, '중식', False, True, 4), (117, '꿔바로우', 13000, '중식', False, False, 2), (117, '마라샹궈', 15000, '중식', False, False, 5), (117, '우육면', 10000, '중식', False, True, 2);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (118, '탄탄면', 9000, '중식', False, True, 2), (118, '샤오롱바오', 7000, '중식', False, False, 0), (118, '계란토마토볶음', 8000, '중식', True, False, 1), (118, '사천볶음면', 9500, '중식', False, False, 3);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (119, '해물짬뽕', 9000, '중식', False, True, 3), (119, '차돌짬뽕', 9500, '중식', False, True, 3), (119, '간짜장', 8000, '중식', False, False, 1), (119, '공깃밥', 1000, '중식', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (120, '유산슬', 18000, '중식', False, False, 1), (120, '양장피', 17000, '중식', False, False, 2), (120, '깐풍기', 16000, '중식', False, False, 3), (120, '중국식냉채', 9000, '중식', True, False, 1);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (121, '북경오리 (1인분)', 20000, '중식', False, False, 1), (121, '양장피', 16000, '중식', False, False, 2), (121, '송이불도장탕', 25000, '중식', False, True, 1), (121, '해파리냉채', 12000, '중식', False, False, 1);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (122, 'XO 해물볶음밥', 9500, '중식', False, False, 1), (122, '칠리새우', 16000, '중식', False, False, 2), (122, '고추잡채', 14000, '중식', False, False, 2), (122, '마늘볶음밥', 7000, '중식', False, False, 0);

INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (123, '안심 스테이크', 28000, '양식', False, False, 1), (123, '트러플 크림 파스타', 19000, '양식', False, False, 0), (123, '시저 샐러드', 12000, '양식', False, False, 0), (123, '양송이 수프', 7000, '양식', True, True, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (124, '스테이크 플래터', 33000, '양식', False, False, 1), (124, '그릴드 치킨 샐러드', 15000, '양식', False, False, 0), (124, '크림 파스타', 16000, '양식', False, False, 0), (124, '콘 수프', 5000, '양식', True, True, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (125, '에그 베네딕트', 14000, '양식', False, False, 0), (125, '프렌치토스트 플레이트', 13000, '양식', False, False, 0), (125, '연어 샐러드', 16000, '양식', False, False, 0), (125, '그릭요거트볼', 10000, '양식', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (126, '모짜렐라 파니니', 9500, '양식', False, False, 0), (126, '훈제 연어 샐러드', 14000, '양식', False, False, 0), (126, '토마토 수프', 6000, '양식', True, True, 0), (126, '시금치 치즈 파니니', 10500, '양식', False, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (127, '콤비네이션 피자', 18000, '양식', False, False, 1), (127, '고르곤졸라 피자', 17000, '양식', False, False, 0), (127, '감자피자', 16000, '양식', False, False, 0), (127, '마르게리타 피자', 15000, '양식', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (128, '빠네 파스타', 16500, '양식', False, False, 0), (128, '함박스테이크', 18000, '양식', False, False, 1), (128, '토마토 리조또', 15000, '양식', True, False, 0), (128, '오리엔탈 샐러드', 13000, '양식', True, False, 1);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (129, '알리오올리오', 10500, '양식', True, False, 1), (129, '까르보나라', 12000, '양식', False, False, 0), (129, '로제 파스타', 13000, '양식', False, False, 1), (129, '치즈 오븐 리조또', 14000, '양식', False, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (130, '베이컨치즈버거', 9500, '양식', False, False, 1), (130, '바스버거 클래식', 8900, '양식', False, False, 0), (130, '통감자튀김', 4000, '양식', True, False, 0), (130, '양송이스프', 4500, '양식', True, True, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (131, '비프 스테이크', 35000, '양식', False, False, 1), (131, '랍스터 파스타', 30000, '양식', False, False, 1), (131, '리코타 치즈 샐러드', 16000, '양식', False, False, 0), (131, '구운 가지 카프레제', 12000, '양식', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (132, '트러플 리조또', 17000, '양식', True, False, 0), (132, '크로크무슈', 15000, '양식', False, False, 0), (132, '치즈 토마토 파스타', 16000, '양식', False, False, 0), (132, '오늘의 수프', 5000, '양식', True, True, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (133, '프렌치 브런치 세트', 18000, '양식', False, False, 0), (133, '에그스크램블 팬케이크', 14000, '양식', False, False, 0), (133, '크로와상 샌드위치', 13000, '양식', False, False, 0), (133, '그린샐러드', 10000, '양식', True, False, 0);

INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (134, '무지개 케이크', 8800, '디저트', False, False, 0), (134, '레드벨벳 케이크', 8500, '디저트', False, False, 0), (134, '크림치즈 케이크', 8200, '디저트', False, False, 0), (134, '아메리카노', 4500, '디저트', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (135, '뉴욕치즈 케이크', 6800, '디저트', False, False, 0), (135, '티라미수', 7000, '디저트', False, False, 0), (135, '스트로베리 요거트 쉐이크', 6300, '디저트', False, False, 0), (135, '콜드브루 커피', 4900, '디저트', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (136, '인절미설빙', 8500, '디저트', False, False, 0), (136, '초코브라우니설빙', 9700, '디저트', False, False, 0), (136, '딸기설빙', 10500, '디저트', False, False, 0), (136, '한라봉차', 4800, '디저트', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (137, '몽블랑', 7500, '디저트', False, False, 0), (137, '마들렌', 3500, '디저트', False, False, 0), (137, '프렌치 파운드 케이크', 6000, '디저트', False, False, 0), (137, '밀크티', 5000, '디저트', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (138, '도지마 롤', 8900, '디저트', False, False, 0), (138, '초코 도지마 롤', 9500, '디저트', False, False, 0), (138, '마차 도지마 롤', 9800, '디저트', False, False, 0), (138, '허브티', 5200, '디저트', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (139, '오리지널 프레즐', 3900, '디저트', False, False, 0), (139, '시나몬 슈가 프레즐', 4200, '디저트', False, False, 0), (139, '치즈 딥핑소스', 1000, '디저트', False, False, 0), (139, '레몬에이드', 4700, '디저트', True, False, 0);

INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (140, '연어 포케볼', 12000, '기타', False, False, 1), (140, '참치 포케볼', 13000, '기타', False, False, 2), (140, '두부 포케볼', 11000, '기타', True, False, 1), (140, '아보카도 사이드', 3000, '기타', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (141, '치킨 오버 라이스', 10500, '기타', False, False, 2), (141, '비프 오버 라이스', 11000, '기타', False, False, 2), (141, '팔라펠 오버 라이스', 9500, '기타', True, False, 1), (141, '화이트소스 추가', 1000, '기타', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (142, '잔치국수', 7000, '기타', True, True, 0), (142, '비빔국수', 7500, '기타', True, False, 2), (142, '김치말이국수', 8000, '기타', True, True, 3), (142, '모둠전', 9500, '기타', False, False, 1);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (143, '바베큐 플래터 (2인)', 28000, '기타', False, False, 2), (143, '베이비백 립', 24000, '기타', False, False, 1), (143, '감자튀김', 4000, '기타', True, False, 0), (143, '코울슬로', 3000, '기타', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (144, '후라이드 치킨', 17000, '기타', False, False, 1), (144, '똥집튀김', 12000, '기타', False, False, 1), (144, '오징어땅콩', 8000, '기타', False, False, 1), (144, '생맥주 500cc', 4000, '기타', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (145, '쌀국수', 9500, '기타', False, True, 1), (145, '분짜', 11000, '기타', False, False, 1), (145, '짜조', 7000, '기타', False, False, 2), (145, '고이꾸온', 6500, '기타', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (146, '이화그린', 12000, '기타', False, False, 0), (146, '이화밀크', 12000, '기타', False, False, 1), (146, '연세우유', 12000, '기타', False, False, 0), (146, '감바스 알 아히요', 15000, '기타', False, False, 1), (146, '트러플 감자튀김', 9000, '기타', True, False, 0), (146, '모든소세지', 15000, '기타', False, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (147, '치킨 타코 2P', 11000, '기타', False, False, 2), (147, '비프 브리또', 12000, '기타', False, False, 3), (147, '퀘사디아', 10000, '기타', False, False, 2), (147, '나쵸 플레이트', 9500, '기타', True, False, 1);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (148, '팔라펠 플레이트', 10000, '기타', True, False, 1), (148, '샥슈카', 11000, '기타', False, True, 2), (148, '지중해 피타 샐러드', 9500, '기타', True, False, 0), (148, '후무스 + 피타', 7500, '기타', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (149, '한우 타코 2P', 12000, '기타', False, False, 2), (149, '새우 타코', 11000, '기타', False, False, 2), (149, '치킨 타코 샐러드볼', 11500, '기타', False, False, 1), (149, '채식 타코', 10500, '기타', True, False, 1);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (150, '분짜', 11000, '기타', False, False, 1), (150, '쌀국수', 9800, '기타', False, True, 1), (150, '짜조', 6000, '기타', False, False, 2), (150, '고이꾸온', 5500, '기타', True, False, 0), (150, '베트남 연유커피', 4500, '기타', True, False, 0);



-- Star 테이블

INSERT INTO Star (user_id, rest_id, rating) VALUES 
(1011, 101, 3), (1012, 101, 4), (1007, 101, 2), (1003, 101, 1), (1010, 101, 3),
(1002, 106, 2), (1018, 106, 4), (1001, 106, 1), (1005, 106, 4),
(1014, 107, 4), (1013, 107, 2), (1011, 107, 1), (1012, 107, 4),
(1002, 108, 1), (1008, 108, 5), (1001, 108, 2), (1019, 108, 2), (1013, 108, 3), (1004, 108, 2),
(1005, 109, 4), (1016, 109, 5), (1018, 109, 4),
(1017, 111, 2), (1004, 111, 5),
(1016, 112, 1), (1003, 112, 5), (1007, 112, 2), (1008, 112, 3),
(1010, 113, 5), (1018, 113, 4), (1011, 113, 2), (1001, 113, 5), (1005, 113, 2),
(1007, 117, 1), (1014, 117, 3), (1008, 117, 4), (1005, 117, 5), (1004, 117, 1), (1013, 117, 2),
(1006, 118, 2), (1008, 118, 1), (1011, 118, 3), (1012, 118, 5),
(1019, 119, 4), (1018, 119, 4), (1014, 119, 1), (1001, 119, 1),
(1001, 121, 1), (1006, 121, 5), (1014, 121, 2),
(1002, 124, 2), (1011, 124, 1), (1017, 124, 5), (1009, 124, 1), (1016, 124, 2),
(1016, 128, 2), (1004, 128, 4),
(1015, 129, 2), (1002, 129, 1),
(1007, 132, 5), (1005, 132, 5),
(1003, 134, 4), (1001, 134, 5), (1017, 134, 3),
(1007, 137, 5), (1013, 137, 4),
(1015, 138, 1), (1010, 138, 4), (1006, 138, 4),
(1009, 140, 5), (1004, 140, 3), (1012, 140, 2),
(1019, 143, 5), (1010, 143, 3), (1017, 143, 1);








-- Favorites 테이블
INSERT INTO Favorites (user_id, rest_id) VALUES 
(1001, 101), (1001, 105), (1001, 109), (1001, 111),
(1002, 102), (1002, 106), (1002, 110), (1002, 124),
(1003, 103), (1003, 107), (1003, 108), (1003, 115),
(1004, 104), (1004, 112), (1004, 118), (1004, 125),
(1005, 113), (1005, 117), (1005, 122), (1005, 128),
(1006, 116), (1006, 127), (1006, 131),
(1007, 114), (1007, 123), (1007, 132), (1007, 134),
(1008, 115), (1008, 126), (1008, 135), (1008, 137),
(1009, 120), (1009, 129), (1009, 136),
(1010, 106), (1010, 121), (1010, 133), (1010, 140),
(1011, 107), (1011, 122), (1011, 124),
(1012, 108), (1012, 125), (1012, 126),
(1013, 109), (1013, 127), (1013, 142),
(1014, 110), (1014, 129), (1014, 130),
(1015, 111), (1015, 131), (1015, 132), (1015, 144),
(1016, 112), (1016, 133), (1016, 134), (1016, 145),
(1017, 113), (1017, 135), (1017, 146),
(1018, 114), (1018, 137), (1018, 147),
(1019, 115), (1019, 139), (1019, 148),
(1020, 116), (1020, 141), (1020, 149), (1020, 150);







UPDATE Restaurant r
SET has_vegan = EXISTS (
    SELECT 1
    FROM Menu m
    WHERE m.rest_id = r.rest_id AND m.is_vegan = True
);


