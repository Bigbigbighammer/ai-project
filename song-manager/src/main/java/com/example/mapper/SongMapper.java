package com.example.mapper;

import com.example.model.Song;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper // 标记为 MyBatis Mapper 接口
public interface SongMapper {

    // 查询所有歌曲
    @Select("SELECT id, title, artist, album, genre FROM songs")
    List<Song> findAll();

    // 根据 ID 查询歌曲
    @Select("SELECT id, title, artist, album, genre FROM songs WHERE id = #{id}")
    Song findById(Long id);

    // 插入歌曲 (返回影响的行数)
    // useGeneratedKeys=true 和 keyProperty="id" 让 MyBatis 将数据库生成的自增 ID 赋值给 song 对象的 id 属性
    @Insert("INSERT INTO songs(title, artist, album, genre) VALUES(#{title}, #{artist}, #{album}, #{genre})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Song song);

    // 更新歌曲 (返回影响的行数)
    @Update("UPDATE songs SET title=#{title}, artist=#{artist}, album=#{album}, genre=#{genre} WHERE id=#{id}")
    int update(Song song);

    // 根据 ID 删除歌曲 (返回影响的行数)
    @Delete("DELETE FROM songs WHERE id = #{id}")
    int deleteById(Long id);
}
