// Task #1
SELECT model, speed, hd
FROM pc
WHERE price > 500

// Task #2
SELECT maker
FROM product
WHERE type='Printer'

//Task #3
SELECT model, ram, screen
FROM laptop
WHERE price > 1000

//Task #4
SELECT * FROM printer
WHERE color = 'y'

//Task #5
SELECT model, speed, hd
FROM pc
WHERE (cd = '12x' OR cd = '24x') AND price < 600

//Task #6
SELECT p.maker, l.speed
FROM product p
INNER JOIN laptop l ON p.model = l.model
WHERE l.hd >= 100

//Task #7
SELECT pc.model, pc.price
FROM pc pc INNER JOIN product p ON pc.model = p.model
WHERE p.maker = 'Apple'
UNION
SELECT l.model, l.price
FROM laptop l INNER JOIN product p ON l.model = p.model
WHERE p.maker = 'Apple'
UNION
SELECT pr.model, pr.price
FROM printer pr INNER JOIN  product p ON pr.model = p.model
WHERE p.maker = 'Apple'

//Task #8
SELECT DISTINCT maker
FROM product
WHERE type = 'PC'
  AND maker NOT IN (SELECT maker FROM product WHERE type = 'Laptop')

//Task #9
SELECT p.maker
FROM product p
INNER JOIN pc pc ON p.model = pc.model
WHERE pc.speed >= 450

//Task #10
SELECT model, price
FROM printer
WHERE price = (SELECT MAX(price) FROM printer)

//Task #11
SELECT AVG(speed) AS avarage_pc_speed FROM pc

//Task #12
SELECT AVG(speed) AS avarage_laptop_price_over_1000_speed
FROM laptop
WHERE price > 1000

//Task #13
SELECT AVG(speed) AS avarage_pc_apple_maker_speed
FROM pc pc INNER JOIN product p ON pc.model = p.model
WHERE p.maker = 'Apple'

//Task #14
SELECT l.speed, AVG(pc.price) AS avarage_pc_price
FROM laptop l
INNER JOIN pc pc ON pc.speed = l.speed
GROUP BY l.speed

//Task #15
SELECT hd
FROM pc
GROUP BY hd
HAVING COUNT(*) > 1

//Task #16
SELECT DISTINCT p1.model, p2.model, p1.speed, p1.ram
FROM pc p1, pc p2
WHERE p1.speed = p2.speed
  AND p1.ram = p2.ram
  AND p1.model > p2.model

//Task #17
SELECT l.model, l.speed
FROM laptop l
WHERE l.speed < (SELECT MIN(speed) FROM pc)

//Task #18
SELECT p.maker, pr.price
FROM product p
INNER JOIN printer pr ON p.model = pr.model
WHERE pr.color = 'y'
  AND pr.price = (SELECT MIN(price) FROM printer)

//Task #19
SELECT p.maker, AVG(l.screen) AS avarage_laptop_screen
FROM laptop l
INNER JOIN product p ON p.model = l.model
GROUP BY p.maker

//Task #20
SELECT maker, COUNT(model)
FROM product
WHERE type = 'PC'
GROUP BY product.maker
HAVING COUNT (DISTINCT model) >= 3

//Task #21
SELECT p.maker, MAX(pc.price) AS max_pc_price
FROM pc pc
INNER JOIN product p ON p.model = pc.model
GROUP BY p.maker

//Task #22
SELECT pc.speed, AVG(pc.price) AS average_pc_price
FROM pc pc
WHERE pc.speed > 600
GROUP BY pc.speed

//Task #23
SELECT DISTINCT maker
FROM product t1 JOIN pc t2 ON t1.model=t2.model
WHERE speed>=750
  AND maker IN ( SELECT maker
                 FROM product t1 JOIN laptop t2 ON t1.model=t2.model
                 WHERE speed>=750 )

//Task #24
SELECT model
FROM ( SELECT model, price
       FROM pc
       UNION
       SELECT model, price
       FROM Laptop
       UNION
       SELECT model, price
       FROM Printer
     ) t1
WHERE price = (
                  SELECT MAX(price)
                  FROM (
                           SELECT price
                           FROM pc
                           UNION
                           SELECT price
                           FROM Laptop
                           UNION
                           SELECT price
                           FROM Printer
                       ) t2
              )

//Task #25
SELECT DISTINCT maker
FROM product
WHERE model IN (
    SELECT model
    FROM pc
    WHERE ram = (
        SELECT MIN(ram)
        FROM pc
    )
      AND speed = (
        SELECT MAX(speed)
        FROM pc
        WHERE ram = (
            SELECT MIN(ram)
            FROM pc
        )
    )
)
  AND
        maker IN (
        SELECT maker
        FROM product
        WHERE type='Printer'
    )