drop procedure if exists add_topping_to_pizza;
create procedure add_topping_to_pizza(
    in pizza_name varchar(100),
    in topping_name varchar(100)
)
begin
    call insert_pizza(pizza_name, @pizza_id);
    call insert_topping(topping_name, @topping_id);

   if not exists (select 1 from pizzas_toppings where pizza_id = @pizza_id and toppings_id = @topping_id) then
       insert into pizzas_toppings (pizza_id, toppings_id)
       values (@pizza_id, @topping_id);
   end if;
end;