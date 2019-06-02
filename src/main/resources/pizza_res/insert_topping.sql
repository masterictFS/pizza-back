drop procedure if exists insert_topping;
create procedure insert_topping(
    in topping_name varchar(100),
	out topping_id int
)
begin
    if topping_name not in (select name from toppings) then
        insert into toppings (name)
        values (topping_name);
    end if;

    select id into topping_id from toppings where name = topping_name;
end;