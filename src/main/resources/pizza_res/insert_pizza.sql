drop procedure if exists insert_pizza;
create procedure insert_pizza(
    in pizza_name varchar(100),
    out pizza_id int
)
begin
    if pizza_name not in (select name from pizzas) then
        insert into pizzas (name, user_id)
        values (pizza_name, -1);
    end if;

    select id into pizza_id from pizzas where name = pizza_name;
end;