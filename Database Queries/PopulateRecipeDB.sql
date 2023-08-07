-- Insert sample recipes
INSERT INTO recipes (recipename, instructions, cookingtime)
VALUES
    ('Apple Pie', 'Instructions for Apple Pie', 45.00),
    ('Chocolate Cake', 'Instructions for Chocolate Cake', 60.00),
    ('Chicken Alfredo Pasta', 'Instructions for Chicken Alfredo Pasta', 30.00);

-- Insert sample ingredients
INSERT INTO ingredients (ingredientname, quantity, quantitytype)
VALUES
    ('Apple', 2.5, 'pcs'),
    ('Pie Crust', 1, 'pack'),
    ('Flour', 500, 'g'),
    ('Sugar', 200, 'g'),
    ('Chocolate', 300, 'g'),
    ('Butter', 100, 'g'),
    ('Chicken', 500, 'g'),
    ('Alfredo Sauce', 400, 'ml'),
    ('Pasta', 300, 'g');

-- Insert sample recipes_ingredients (linking recipes to ingredients)
INSERT INTO recipes_ingredients (recipeid, ingredientid)
VALUES
    (1, 1), -- Apple Pie - Apple
    (1, 2), -- Apple Pie - Pie Crust
    (2, 5), -- Chocolate Cake - Chocolate
    (2, 6), -- Chocolate Cake - Butter
    (3, 7), -- Chicken Alfredo Pasta - Chicken
    (3, 8), -- Chicken Alfredo Pasta - Alfredo Sauce
    (3, 9); -- Chicken Alfredo Pasta - Pasta
