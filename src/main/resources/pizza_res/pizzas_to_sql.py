# use this to generate the sql instructions to populate the db
pizzas = [line.rstrip('\n') for line in open('pizzas.txt', 'r', encoding='utf-8')]
pizzas_sql = open('pizzas_sql.sql', 'w')

for pizza in pizzas:
    pizza = pizza.split('; ')

    name = pizza[0].strip()
    print(name)

    toppings = pizza[1].split(', ')
    print(toppings)

    prices = pizza[2].split(', ')
    print(prices)

    for topping in toppings:
        topping_sql = f'call add_topping_to_pizza(\'{name}\',\'{topping.strip()}\');\n'
        pizzas_sql.write(topping_sql)
        print(topping_sql)

    price_sql = f'call add_prices_to_pizza(\'{name}\', {prices[0]}, {prices[1]}, {prices[2]});\n'
    pizzas_sql.write(price_sql)
    print(price_sql)