DELIMITER //

CREATE TRIGGER set_reward_points_before_insert
BEFORE INSERT ON t_goods
FOR EACH ROW
BEGIN
    -- 如果是寻物启事(goodsstatus=1)，设置reward_points=1
    IF NEW.goodsstatus = 1 THEN
        SET NEW.reward_points = 1;
    END IF;
END //

DELIMITER ;
