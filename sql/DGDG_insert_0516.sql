INSERT INTO User (user_name, password) VALUES ('dummyleader', 'dummyleader');         -- dummy leader

-- User_group 테이블
INSERT INTO User_group (group_name, invite_code, leader) VALUES ('group01', 86086011, 1000);
INSERT INTO User_group (group_name, invite_code, leader) VALUES ('group02', 64423434, 1000);
INSERT INTO User_group (group_name, invite_code, leader) VALUES ('group03', 41252322, 1000);
INSERT INTO User_group (group_name, invite_code, leader) VALUES ('group04', 15809797, 1000); 
INSERT INTO User_group (group_name, invite_code, leader) VALUES ('group05', 10365101, 1000);

-- -- User 테이블

INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('joshuamedina', '#$Y0EFf3CZ', True, 5001, True);         -- leader
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('joycurry', 'RMwF1^Gd&&', True, 5001, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('russelltammy', 'c4NB8TPb+0', False, 5001, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('marshalljoseph', '_3FaHzY9yu', False, 5001, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('zdonaldson', '2K39FIhq&l', True, 5001, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('barajaskatherine', '1HHFik%n*6', False, 5002, True);    -- leader
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('nwebster', '+^88*ZqeB*', True, 5002, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('ronaldwilson', '^#5NHHQf&p', True, 5003, True);         -- leader
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('vsullivan', '(39DL0uPb6', False, 5003, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('davidgood', '8G8Lu8Wp+@', True, 5003, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('matthew35', '58I*eUhw(m', False, 5003, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('alexanderbrooke', 'l(9rY4iu_&', True, 5003, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('rodneyrasmussen', 'Qi8u3CAsP!', False, 5003, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('gonzalezkelly', 'tJ5#IuHo$y', False, 5004, True);       -- leader
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('abaker', 'PcY(0UZV%!', False, 5004, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('sean87', 'R+Be3%JeHq', True, 5004, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('maria56', 'H+PN_xJx(5', False, 5004, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('margaretthomas', '2XpTjg*D+n', False, 5005, True);      -- leader
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('richardsonkathy', '7u2Ixa+nx^', False, 5005, False);
INSERT INTO User (user_name, password, is_available, group_id, is_leader) VALUES ('zshannon', '*QSmeNf*69', True, 5005, False);


UPDATE User_group SET leader = (SELECT user_id FROM User WHERE user_name = 'joshuamedina') WHERE group_name = 'group01';
UPDATE User_group SET leader = (SELECT user_id FROM User WHERE user_name = 'barajaskatherine') WHERE group_name = 'group02';
UPDATE User_group SET leader = (SELECT user_id FROM User WHERE user_name = 'ronaldwilson') WHERE group_name = 'group03';
UPDATE User_group SET leader = (SELECT user_id FROM User WHERE user_name = 'gonzalezkelly') WHERE group_name = 'group04';
UPDATE User_group SET leader = (SELECT user_id FROM User WHERE user_name = 'margaretthomas') WHERE group_name = 'group05';



-- Restaurant 테이블

INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('김밥천국', 'Korean', 700, '08:00:00', '22:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('신촌부추곱창', 'Korean', 3000, '16:00:00', '01:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('본가 설렁탕', 'Korean', 1100, '11:00:00', '21:00:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('한옥집', 'Korean', 1600, '11:00:00', '22:00:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('삼청동 수제비', 'Korean', 5500, '11:30:00', '20:30:00', '15:30:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('백채김치찌개', 'Korean', 100, '11:00:00', '21:00:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('하남 돼지집', 'Korean', 9100, '17:00:00', '01:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('명동교자', 'Korean', 9900, '10:30:00', '21:30:00', NULL, NULL, False);

INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('연어초밥', 'Japanese', 1000, '11:30:00', '21:30:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('세상끝의라멘', 'Japanese', 5200, '11:00:00', '22:30:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('긴자료코', 'Japanese', 300, '11:30:00', '21:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('모미지식당', 'Japanese', 700, '11:00:00', '22:00:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('스시효', 'Japanese', 2100, '12:00:00', '22:00:00', '15:00:00', '17:30:00', False);

INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('홍반장 중화요리', 'Chinese', 400, '11:00:00', '21:30:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('홍콩반점0410', 'Chinese', 1600, '11:00:00', '22:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('차이나팩토리', 'Chinese', 700, '11:30:00', '22:00:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('화라라 마라탕', 'Chinese', 200, '11:00:00', '23:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('라화쿵푸', 'Chinese', 300, '11:30:00', '21:30:00', '15:30:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('명동 교동짬뽕', 'Chinese', 2500, '11:00:00', '22:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('다래헌', 'Chinese', 900, '11:30:00', '21:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('홍보각', 'Chinese', 8100, '11:00:00', '21:30:00', '15:00:00', '17:30:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('송쉐프 중식당', 'Chinese', 700, '11:00:00', '22:00:00', NULL, NULL, False);

INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('라그릴리아', 'Western', 3100, '11:30:00', '22:00:00', '15:30:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('빕스', 'Western', 500, '11:00:00', '22:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('브런치브라더스', 'Western', 700, '09:00:00', '17:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('마마스 샐러드 & 파니니', 'Western', 8700, '10:30:00', '21:00:00', '15:30:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('피자몰', 'Western', 2900, '11:00:00', '21:30:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('서가앤쿡', 'Western', 2700, '11:30:00', '22:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('롤링파스타', 'Western', 500, '11:00:00', '21:30:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('바스버거', 'Western', 9100, '11:30:00', '22:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('라루즈', 'Western', 2200, '12:00:00', '22:00:00', '15:00:00', '17:30:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('바닐라키친', 'Western', 5100, '10:30:00', '21:30:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('쁘띠문래', 'Western', 8500, '10:00:00', '20:00:00', '15:00:00', '16:30:00', False);

INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('도레도레', 'Dessert', 10000, '11:00:00', '21:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('투썸플레이스', 'Dessert', 700, '10:00:00', '22:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('설빙 이대점', 'Dessert', 10, '11:30:00', '22:30:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('리치몬드과자점', 'Dessert', 600, '09:00:00', '21:00:00', '14:30:00', '16:30:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('케이크드몽슈슈', 'Dessert', 1600, '11:00:00', '21:30:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('앤티앤스', 'Dessert', 3, '10:00:00', '22:00:00', NULL, NULL, False);

INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('한남오거리 포케', 'Other', 12000, '11:30:00', '21:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('할랄가이즈', 'Other', 15000, '11:00:00', '21:00:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('두레국수', 'Other', 1800, '11:00:00', '20:30:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('바오밥 바베큐', 'Other', 1900, '11:30:00', '22:00:00', '15:30:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('고래사냥 호프', 'Other', 100, '17:00:00', '02:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('리틀사이공', 'Other', 1700, '11:00:00', '22:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('코지라운지', 'Other', 2000, '18:00:00', '02:00:00', NULL, NULL, False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('온더보더', 'Other', 8700, '11:30:00', '22:00:00', '15:30:00', '17:30:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('갓잇', 'Other', 7000, '11:00:00', '21:30:00', '15:00:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('낙원타코', 'Other', 3900, '11:30:00', '22:00:00', '15:30:00', '17:00:00', False);
INSERT INTO Restaurant (rest_name, category, distance, open_time, close_time, break_start, break_end, has_vegan) VALUES ('라운지베트남', 'Other', 6100, '11:30:00', '21:30:00', '15:00:00', '17:00:00', False);


-- Menu 테이블

INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (101, '야채김밥', 4500, 'Korean', True, False, 0), (101, '참치김밥', 5000, 'Korean', False, False, 0), (101, '라면', 4000, 'Korean', False, True, 1), (101, '떡볶이', 5000, 'Korean', False, True, 2), (101, '오므라이스', 6500, 'Korean', False, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (102, '곱창구이', 15000, 'Korean', False, False, 2), (102, '부추무침', 4000, 'Korean', True, False, 1), (102, '볶음밥', 2000, 'Korean', False, False, 1), (102, '된장찌개', 5000, 'Korean', False, True, 1);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (103, '설렁탕', 9000, 'Korean', False, True, 0), (103, '도가니탕', 12000, 'Korean', False, True, 0), (103, '수육', 15000, 'Korean', False, False, 0), (103, '깍두기', 0, 'Korean', True, False, 2);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (104, '수육국밥', 8500, 'Korean', False, True, 1), (104, '김치찜', 9000, 'Korean', False, True, 2), (104, '공깃밥', 1000, 'Korean', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (105, '들깨수제비', 8000, 'Korean', True, True, 0), (105, '바지락수제비', 8500, 'Korean', False, True, 1), (105, '김치전', 6000, 'Korean', False, False, 2);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (106, '돼지고기 김치찌개', 9000, 'Korean', False, True, 3), (106, '계란말이', 7000, 'Korean', False, False, 0), (106, '김치볶음밥', 8000, 'Korean', False, False, 2);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (107, '삼겹살 (200g)', 15000, 'Korean', False, False, 0), (107, '목살 (200g)', 16000, 'Korean', False, False, 0), (107, '된장찌개', 5000, 'Korean', False, True, 1), (107, '공깃밥', 1000, 'Korean', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (108, '칼국수', 10000, 'Korean', False, True, 0), (108, '왕만두', 9000, 'Korean', False, False, 0), (108, '비빔국수', 9500, 'Korean', False, False, 2), (108, '김치', 0, 'Korean', True, False, 3);

INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (109, '연어초밥세트', 15000, 'Japanese', False, False, 0), (109, '연어사시미', 17000, 'Japanese', False, False, 0), (109, '미소된장국', 2000, 'Japanese', True, True, 0), (109, '연어덮밥', 13000, 'Japanese', False, False, 0), (109, '에다마메', 3000, 'Japanese', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (110, '돈코츠라멘', 9000, 'Japanese', False, True, 1), (110, '쇼유라멘', 8500, 'Japanese', False, True, 1), (110, '카라미소라멘', 9500, 'Japanese', False, True, 3), (110, '멘마토핑', 1000, 'Japanese', True, False, 0), (110, '반숙계란', 1500, 'Japanese', False, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (111, '사케동', 13000, 'Japanese', False, False, 0), (111, '가츠동', 9000, 'Japanese', False, False, 1), (111, '타마고야끼', 4000, 'Japanese', True, False, 0), (111, '미소된장국', 2000, 'Japanese', True, True, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (112, '오야코동', 8500, 'Japanese', False, False, 0), (112, '가라아게 정식', 11000, 'Japanese', False, False, 0), (112, '야채볶음우동', 9500, 'Japanese', True, False, 1), (112, '연두부 샐러드', 6000, 'Japanese', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (113, '오마카세 런치', 45000, 'Japanese', False, False, 0), (113, '특선 스시세트', 38000, 'Japanese', False, False, 0), (113, '참치 사시미', 30000, 'Japanese', False, False, 0), (113, '계절 된장국', 3000, 'Japanese', True, True, 0);

INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (114, '짜장면', 7000, 'Chinese', False, False, 0), (114, '짬뽕', 8000, 'Chinese', False, True, 2), (114, '탕수육', 14000, 'Chinese', False, False, 1), (114, '군만두', 5000, 'Chinese', False, False, 1);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (115, '차돌짬뽕', 8500, 'Chinese', False, True, 3), (115, '볶음짬뽕', 8500, 'Chinese', False, False, 3), (115, '짬뽕밥', 8000, 'Chinese', False, True, 2), (115, '군만두', 4000, 'Chinese', False, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (116, '팔보채', 16000, 'Chinese', False, False, 2), (116, '마파두부', 10000, 'Chinese', False, True, 3), (116, '칠리새우', 17000, 'Chinese', False, False, 2), (116, '계란볶음밥', 6000, 'Chinese', False, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (117, '마라탕', 11000, 'Chinese', False, True, 4), (117, '꿔바로우', 13000, 'Chinese', False, False, 2), (117, '마라샹궈', 15000, 'Chinese', False, False, 5), (117, '우육면', 10000, 'Chinese', False, True, 2);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (118, '탄탄면', 9000, 'Chinese', False, True, 2), (118, '샤오롱바오', 7000, 'Chinese', False, False, 0), (118, '계란토마토볶음', 8000, 'Chinese', True, False, 1), (118, '사천볶음면', 9500, 'Chinese', False, False, 3);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (119, '해물짬뽕', 9000, 'Chinese', False, True, 3), (119, '차돌짬뽕', 9500, 'Chinese', False, True, 3), (119, '간짜장', 8000, 'Chinese', False, False, 1), (119, '공깃밥', 1000, 'Chinese', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (120, '유산슬', 18000, 'Chinese', False, False, 1), (120, '양장피', 17000, 'Chinese', False, False, 2), (120, '깐풍기', 16000, 'Chinese', False, False, 3), (120, '중국식냉채', 9000, 'Chinese', True, False, 1);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (121, '북경오리 (1인분)', 20000, 'Chinese', False, False, 1), (121, '양장피', 16000, 'Chinese', False, False, 2), (121, '송이불도장탕', 25000, 'Chinese', False, True, 1), (121, '해파리냉채', 12000, 'Chinese', False, False, 1);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (122, 'XO 해물볶음밥', 9500, 'Chinese', False, False, 1), (122, '칠리새우', 16000, 'Chinese', False, False, 2), (122, '고추잡채', 14000, 'Chinese', False, False, 2), (122, '마늘볶음밥', 7000, 'Chinese', False, False, 0);

INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (123, '안심 스테이크', 28000, 'Western', False, False, 1), (123, '트러플 크림 파스타', 19000, 'Western', False, False, 0), (123, '시저 샐러드', 12000, 'Western', False, False, 0), (123, '양송이 수프', 7000, 'Western', True, True, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (124, '스테이크 플래터', 33000, 'Western', False, False, 1), (124, '그릴드 치킨 샐러드', 15000, 'Western', False, False, 0), (124, '크림 파스타', 16000, 'Western', False, False, 0), (124, '콘 수프', 5000, 'Western', True, True, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (125, '에그 베네딕트', 14000, 'Western', False, False, 0), (125, '프렌치토스트 플레이트', 13000, 'Western', False, False, 0), (125, '연어 샐러드', 16000, 'Western', False, False, 0), (125, '그릭요거트볼', 10000, 'Western', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (126, '모짜렐라 파니니', 9500, 'Western', False, False, 0), (126, '훈제 연어 샐러드', 14000, 'Western', False, False, 0), (126, '토마토 수프', 6000, 'Western', True, True, 0), (126, '시금치 치즈 파니니', 10500, 'Western', False, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (127, '콤비네이션 피자', 18000, 'Western', False, False, 1), (127, '고르곤졸라 피자', 17000, 'Western', False, False, 0), (127, '감자피자', 16000, 'Western', False, False, 0), (127, '마르게리타 피자', 15000, 'Western', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (128, '빠네 파스타', 16500, 'Western', False, False, 0), (128, '함박스테이크', 18000, 'Western', False, False, 1), (128, '토마토 리조또', 15000, 'Western', True, False, 0), (128, '오리엔탈 샐러드', 13000, 'Western', True, False, 1);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (129, '알리오올리오', 10500, 'Western', True, False, 1), (129, '까르보나라', 12000, 'Western', False, False, 0), (129, '로제 파스타', 13000, 'Western', False, False, 1), (129, '치즈 오븐 리조또', 14000, 'Western', False, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (130, '베이컨치즈버거', 9500, 'Western', False, False, 1), (130, '바스버거 클래식', 8900, 'Western', False, False, 0), (130, '통감자튀김', 4000, 'Western', True, False, 0), (130, '양송이스프', 4500, 'Western', True, True, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (131, '비프 스테이크', 35000, 'Western', False, False, 1), (131, '랍스터 파스타', 30000, 'Western', False, False, 1), (131, '리코타 치즈 샐러드', 16000, 'Western', False, False, 0), (131, '구운 가지 카프레제', 12000, 'Western', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (132, '트러플 리조또', 17000, 'Western', True, False, 0), (132, '크로크무슈', 15000, 'Western', False, False, 0), (132, '치즈 토마토 파스타', 16000, 'Western', False, False, 0), (132, '오늘의 수프', 5000, 'Western', True, True, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (133, '프렌치 브런치 세트', 18000, 'Western', False, False, 0), (133, '에그스크램블 팬케이크', 14000, 'Western', False, False, 0), (133, '크로와상 샌드위치', 13000, 'Western', False, False, 0), (133, '그린샐러드', 10000, 'Western', True, False, 0);

INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (134, '무지개 케이크', 8800, 'Dessert', False, False, 0), (134, '레드벨벳 케이크', 8500, 'Dessert', False, False, 0), (134, '크림치즈 케이크', 8200, 'Dessert', False, False, 0), (134, '아메리카노', 4500, 'Dessert', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (135, '뉴욕치즈 케이크', 6800, 'Dessert', False, False, 0), (135, '티라미수', 7000, 'Dessert', False, False, 0), (135, '스트로베리 요거트 쉐이크', 6300, 'Dessert', False, False, 0), (135, '콜드브루 커피', 4900, 'Dessert', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (136, '인절미설빙', 8500, 'Dessert', False, False, 0), (136, '초코브라우니설빙', 9700, 'Dessert', False, False, 0), (136, '딸기설빙', 10500, 'Dessert', False, False, 0), (136, '한라봉차', 4800, 'Dessert', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (137, '몽블랑', 7500, 'Dessert', False, False, 0), (137, '마들렌', 3500, 'Dessert', False, False, 0), (137, '프렌치 파운드 케이크', 6000, 'Dessert', False, False, 0), (137, '밀크티', 5000, 'Dessert', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (138, '도지마 롤', 8900, 'Dessert', False, False, 0), (138, '초코 도지마 롤', 9500, 'Dessert', False, False, 0), (138, '마차 도지마 롤', 9800, 'Dessert', False, False, 0), (138, '허브티', 5200, 'Dessert', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (139, '오리지널 프레즐', 3900, 'Dessert', False, False, 0), (139, '시나몬 슈가 프레즐', 4200, 'Dessert', False, False, 0), (139, '치즈 딥핑소스', 1000, 'Dessert', False, False, 0), (139, '레몬에이드', 4700, 'Dessert', True, False, 0);

INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (140, '연어 포케볼', 12000, 'Other', False, False, 1), (140, '참치 포케볼', 13000, 'Other', False, False, 2), (140, '두부 포케볼', 11000, 'Other', True, False, 1), (140, '아보카도 사이드', 3000, 'Other', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (141, '치킨 오버 라이스', 10500, 'Other', False, False, 2), (141, '비프 오버 라이스', 11000, 'Other', False, False, 2), (141, '팔라펠 오버 라이스', 9500, 'Other', True, False, 1), (141, '화이트소스 추가', 1000, 'Other', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (142, '잔치국수', 7000, 'Other', True, True, 0), (142, '비빔국수', 7500, 'Other', True, False, 2), (142, '김치말이국수', 8000, 'Other', True, True, 3), (142, '모둠전', 9500, 'Other', False, False, 1);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (143, '바베큐 플래터 (2인)', 28000, 'Other', False, False, 2), (143, '베이비백 립', 24000, 'Other', False, False, 1), (143, '감자튀김', 4000, 'Other', True, False, 0), (143, '코울슬로', 3000, 'Other', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (144, '후라이드 치킨', 17000, 'Other', False, False, 1), (144, '똥집튀김', 12000, 'Other', False, False, 1), (144, '오징어땅콩', 8000, 'Other', False, False, 1), (144, '생맥주 500cc', 4000, 'Other', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (145, '쌀국수', 9500, 'Other', False, True, 1), (145, '분짜', 11000, 'Other', False, False, 1), (145, '짜조', 7000, 'Other', False, False, 2), (145, '고이꾸온', 6500, 'Other', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (146, '이화그린', 12000, 'Other', False, False, 0), (146, '이화밀크', 12000, 'Other', False, False, 1), (146, '연세우유', 12000, 'Other', False, False, 0), (146, '감바스 알 아히요', 15000, 'Other', False, False, 1), (146, '트러플 감자튀김', 9000, 'Other', True, False, 0), (146, '모든소세지', 15000, 'Other', False, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (147, '치킨 타코 2P', 11000, 'Other', False, False, 2), (147, '비프 브리또', 12000, 'Other', False, False, 3), (147, '퀘사디아', 10000, 'Other', False, False, 2), (147, '나쵸 플레이트', 9500, 'Other', True, False, 1);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (148, '팔라펠 플레이트', 10000, 'Other', True, False, 1), (148, '샥슈카', 11000, 'Other', False, True, 2), (148, '지중해 피타 샐러드', 9500, 'Other', True, False, 0), (148, '후무스 + 피타', 7500, 'Other', True, False, 0);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (149, '한우 타코 2P', 12000, 'Other', False, False, 2), (149, '새우 타코', 11000, 'Other', False, False, 2), (149, '치킨 타코 샐러드볼', 11500, 'Other', False, False, 1), (149, '채식 타코', 10500, 'Other', True, False, 1);
INSERT INTO Menu (rest_id, menu_name, price, category, is_vegan, is_soup, spicy) VALUES (150, '분짜', 11000, 'Other', False, False, 1), (150, '쌀국수', 9800, 'Other', False, True, 1), (150, '짜조', 6000, 'Other', False, False, 2), (150, '고이꾸온', 5500, 'Other', True, False, 0), (150, '베트남 연유커피', 4500, 'Other', True, False, 0);



-- Star 테이블

INSERT INTO Star (user_id, rest_id, rating) VALUES 
(1001, 108, 4),
(1001, 125, 5),
(1002, 130, 3),
(1002, 137, 4),
(1002, 144, 2),
(1004, 112, 3),
(1005, 101, 4),
(1006, 109, 2),
(1006, 110, 3),
(1006, 111, 3),
(1007, 115, 5),
(1008, 128, 4),
(1009, 140, 1),
(1009, 146, 2),
(1010, 102, 4),
(1011, 103, 5),
(1011, 104, 5),
(1012, 106, 2),
(1013, 145, 3),
(1013, 148, 4),
(1014, 107, 3),
(1015, 113, 4),
(1015, 118, 4),
(1015, 119, 4),
(1016, 117, 5),
(1017, 138, 2),
(1018, 136, 5),
(1018, 142, 4),
(1018, 143, 4),
(1019, 105, 3),
(1019, 149, 2),
(1020, 147, 5);



-- Favorites 테이블
INSERT INTO Favorites (user_id, rest_id) VALUES 
(1001, 108),
(1001, 125),
(1001, 131),
(1002, 130),
(1002, 137),
(1003, 150),
(1004, 112),
(1005, 101),
(1006, 109),
(1006, 110),
(1007, 115),
(1007, 126),
(1007, 127),
(1008, 128),
(1008, 129),
(1010, 102),
(1010, 103),
(1011, 104),
(1011, 105),
(1011, 106),
(1012, 107),
(1013, 145),
(1013, 148),
(1014, 107),
(1014, 108),
(1014, 109),
(1015, 113),
(1016, 117),
(1016, 118),
(1017, 138),
(1017, 139),
(1017, 140),
(1018, 136),
(1019, 149);





UPDATE Restaurant r
SET has_vegan = EXISTS (
    SELECT 1
    FROM Menu m
    WHERE m.rest_id = r.rest_id AND m.is_vegan = True
);


