drop procedure if exists add_prices_to_pizza;
create procedure add_prices_to_pizza(
    in pizza_name varchar(100),
    in small_price numeric,
    in medium_price numeric,
    in large_price numeric
)
begin
    if exists (select 1 from pizzas where name = pizza_name) then
        select id into @pizza_id from pizzas where name = pizza_name;

        if small_price > 0 then
            if not exists (select 1 from prices where fk_pizza = @pizza_id and size = 'S') then
                insert into prices (size, fk_pizza, price)
                values ('S', @pizza_id, small_price);
            else
                update prices
                set price = small_price
                where fk_pizza = @pizza_id and size = 'S';
            end if;
        end if;

        if medium_price > 0 then
            if not exists (select 1 from prices where fk_pizza = @pizza_id and size = 'M') then
                insert into prices (size, fk_pizza, price)
                values ('M', @pizza_id, medium_price);
            else
                update prices
                set price = medium_price
                where fk_pizza = @pizza_id and size = 'M';
            end if;
        end if;

        if large_price > 0 then
            if not exists (select 1 from prices where fk_pizza = @pizza_id and size = 'L') then
                insert into prices (size, fk_pizza, price)
                values ('L', @pizza_id, large_price);
            else
                update prices
                set price = large_price
                where fk_pizza = @pizza_id and size = 'L';
            end if;
        end if;
    end if;
end;