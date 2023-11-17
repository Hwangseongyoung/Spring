package com.example.crud.mappers;

import com.example.crud.dto.ItemsDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ItemMapper {
    @Insert("insert into items values(null, #{itemName}, #{itemPrice})")
    public void setAddItem(ItemsDto itemsDto);

    @Select("select count(*) from items")
    public int getCount();

    @Select("select * from items order by item_id desc")
    public List<ItemsDto> getItemList();

    @Delete("delete from items where item_id = #{id}")
    public void deleteItem(int id);

    @Select("select * from items where item_id = #{id}")
    public ItemsDto viewItem(int id);

    @Update("update items set item_name = #{itemName}, item_price = #{itemPrice} where item_id = #{itemId}")
    public void setUpdateItem(ItemsDto itemsDto);
}
